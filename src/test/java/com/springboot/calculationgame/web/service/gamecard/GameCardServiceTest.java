package com.springboot.calculationgame.web.service.gamecard;

import com.springboot.calculationgame.domain.gamecard.GameCard;
import com.springboot.calculationgame.domain.gamecard.GameCardRepository;
import com.springboot.calculationgame.domain.gamecard.Problem;
import com.springboot.calculationgame.domain.score.Score;
import com.springboot.calculationgame.domain.user.User;
import com.springboot.calculationgame.domain.user.UserRepository;
import com.springboot.calculationgame.web.dto.ProblemDto;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class GameCardServiceTest {

    private GameCardService gameCardService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private GameCardRepository gameCardRepository;

    @BeforeEach
    public void beforeEach() {
        gameCardService = new GameCardService(userRepository, gameCardRepository);
    }

    @AfterEach
    public void afterEach(){
        userRepository.deleteAll();
    }

    @Test
    public void saveGameCard_정답인_경우_게임카드_저장하기() {
        User user = new User("user", "1234", new Score());
        user.setScore(new Score());
        user = userRepository.save(user);
        ProblemDto problemDto = new ProblemDto(user.getId(), "1 + 1",2);
        GameCard gameCard = gameCardService.saveGameCard(problemDto).get();

        //연관관계 확인
        assertThat(gameCard.getUser()).isEqualToComparingFieldByField(user);
        //점수 올라갔는지 확인
        assertThat(gameCard.getUser().getScore().getTotal()).isEqualTo(10);
    }

    @Test
    public void saveGameCard_틀린_경우_게임카드_저장하기() {
        User user = new User("user", "1234", new Score());
        user = userRepository.save(user);
        ProblemDto problemDto = new ProblemDto(user.getId(), "1 + 1",1);
        GameCard gameCard = gameCardService.saveGameCard(problemDto).get();

        //연관관계 확인
        assertThat(gameCard.getUser()).isEqualToComparingFieldByField(user);
        //점수 올라갔는지 확인
        assertThat(gameCard.getUser().getScore().getTotal()).isEqualTo(0);
    }

    @Test
    public void saveGameCard_없는_유저_아이디를_조회하는_경우() {
        ProblemDto problemDto = new ProblemDto(1l, "1 + 1",2);
        Optional<GameCard> gameCardOpt = gameCardService.saveGameCard(problemDto);

        assertThat(gameCardOpt).isEqualTo(Optional.empty());
    }

    @Test
    public void getGameCards_id에_해당유저가_있는_경우() {
        User user = User.builder()
                .username("name")
                .password("1234")
                .score(new Score())
                .build();
        user = userRepository.save(user);
        Long id = user.getId();
        GameCard gameCard1 = new GameCard(user, new Problem("1 + 1", 2));
        gameCardRepository.save(gameCard1);


        userRepository.findById(id).get()
                .getGameCards()
                .stream().forEach(card -> System.out.println(card));
    }
}
