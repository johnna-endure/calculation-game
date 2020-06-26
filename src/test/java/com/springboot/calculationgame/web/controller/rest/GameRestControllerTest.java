package com.springboot.calculationgame.web.controller.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.springboot.calculationgame.domain.gamecard.GameCard;
import com.springboot.calculationgame.domain.gamecard.Problem;
import com.springboot.calculationgame.domain.gamecard.ProblemChecker;
import com.springboot.calculationgame.domain.gamecard.ProblemGenerator;
import com.springboot.calculationgame.domain.gamecard.execption.GameCardException;
import com.springboot.calculationgame.domain.score.Score;
import com.springboot.calculationgame.domain.user.User;
import com.springboot.calculationgame.web.dto.GameCardDto;
import com.springboot.calculationgame.web.dto.GameResponse;
import com.springboot.calculationgame.web.dto.GameResult;
import com.springboot.calculationgame.web.dto.ProblemDto;
import com.springboot.calculationgame.web.service.gamecard.GameCardService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class GameRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ApplicationContext context;

    @MockBean
    private GameCardService gameCardService;

    private ObjectMapper jsonMapper = new ObjectMapper();

    @Test
    public void beanIsNotNull() {
        assertThat(context.getBean(GameRestController.class)).isNotNull();
    }

    @Test
    public void sendExpression_식_생성에_성공한_경우() throws Exception {
        when(gameCardService.createExpression()).thenReturn("1 + 1");

        mockMvc.perform(
                get("/game")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("1 + 1"));
    }

    @Test
    public void saveGameCard_답을_맞춘_경우() throws Exception {
        ProblemDto userInput = new ProblemDto(1l,"1 + 1", 2);
        User dummyUser = new User("name", "1234", new Score());
        String expectedResponse = jsonMapper.writeValueAsString(new GameResponse(true,0));

        GameCard gameCard = new GameCard(dummyUser, userInput.toProblem());
        when(gameCardService.saveGameCard(any(ProblemDto.class))).thenReturn(Optional.of(gameCard));

        mockMvc.perform(
                post("/game")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonMapper.writeValueAsString(userInput)))
                .andExpect(status().isOk())
                .andExpect(content().json(expectedResponse));
    }

    @Test
    public void saveGameCard_답을_틀린_경우() throws Exception {
        ProblemDto userInput = new ProblemDto(1l,"1 + 1", 1);
        User dummyUser = new User("name", "1234", new Score());
        String expectedResponse = jsonMapper.writeValueAsString(new GameResponse(false,0));

        GameCard gameCard = new GameCard(dummyUser, userInput.toProblem());
        when(gameCardService.saveGameCard(any(ProblemDto.class))).thenReturn(Optional.of(gameCard));

        mockMvc.perform(
                post("/game")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonMapper.writeValueAsString(userInput)))
                .andExpect(status().isOk())
                .andExpect(content().json(expectedResponse));
    }

    @Test
    public void saveGameCard_답_처리중_에러발생한_경우() throws Exception {
        ProblemDto userInput = new ProblemDto(1l,"1 + 1", 2);

        when(gameCardService.saveGameCard(any(ProblemDto.class))).thenThrow(RuntimeException.class);

        mockMvc.perform(
                post("/game")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonMapper.writeValueAsString(userInput)))
                .andExpect(status().isOk());
    }

    @Test
    public void getGameCards_기본테스트() throws Exception {
        User dummyUser = new User("name", "1234", new Score());
        List<GameCardDto> cards = IntStream.range(0,10).boxed()
                .map(i -> new GameCard(dummyUser,new Problem("1 + 1", i)))
                .map(card -> new GameCardDto(card))
                .collect(Collectors.toList());

        when(gameCardService.get5GameCards(anyLong())).thenReturn(cards);

        String content = mockMvc.perform(get("/games/1"))
                .andReturn()
                .getResponse().getContentAsString();

        assertThat(content).isEqualTo(new ObjectMapper().writeValueAsString(cards));
    }
}
