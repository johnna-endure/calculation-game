package com.springboot.calculationgame.web.controller.page;

import com.springboot.calculationgame.domain.score.Score;
import com.springboot.calculationgame.domain.user.User;
import com.springboot.calculationgame.web.service.user.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class GameControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    UserService userService;

    @Test
    public void loadGamePage_페이지_로딩_및_model검증_테스트() throws Exception {

        when(userService.findUserById(anyLong()))
                .thenReturn(Optional.of(new User("name", "pw", new Score())));

        mockMvc.perform(get("/game/1"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("username", "problem"));
    }
}
