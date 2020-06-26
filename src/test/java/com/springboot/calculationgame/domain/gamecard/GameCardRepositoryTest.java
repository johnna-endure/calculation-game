package com.springboot.calculationgame.domain.gamecard;

import com.springboot.calculationgame.domain.score.Score;
import com.springboot.calculationgame.domain.user.User;
import com.springboot.calculationgame.domain.user.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class GameCardRepositoryTest {

    @Autowired
    GameCardRepository gameCardRepository;

    @Autowired
    UserRepository userRepository;

    @Test
    public void getGameCardsByUser_Id_아이디에_맞는_카드가_필요이상_존재하는_경우() {
        User user = User.builder()
                .score(new Score())
                .username("name")
                .password("1234")
                .build();
        User savedUser = userRepository.save(user);

        List<GameCard> existCards = IntStream.range(0,10)
                .boxed()
                .map(i -> new GameCard(savedUser, new Problem("1 + 1", i)))
                .collect(Collectors.toList());

        gameCardRepository.saveAll(existCards);

        List<GameCard> cards = gameCardRepository.get5GameCardsByUser_Id(savedUser.getId());
        assertThat(cards.size()).isEqualTo(5);
    }

    @Test
    public void getGameCardsByUser_Id_아이디에_맞는_카드가_필요미만_존재하는_경우() {
        User user = User.builder()
                .score(new Score())
                .username("name")
                .password("1234")
                .build();
        User savedUser = userRepository.save(user);

        List<GameCard> existCards = IntStream.range(0,3)
                .boxed()
                .map(i -> new GameCard(savedUser, new Problem("1 + 1", i)))
                .collect(Collectors.toList());

        gameCardRepository.saveAll(existCards);

        List<GameCard> cards = gameCardRepository.get5GameCardsByUser_Id(savedUser.getId());
        assertThat(cards.size()).isEqualTo(3);
    }

    @Test
    public void getGameCardsByUser_Id_아이디에_맞는_카드가_없는_경우() {
        User user = User.builder()
                .score(new Score())
                .username("name")
                .password("1234")
                .build();
        User savedUser = userRepository.save(user);

        List<GameCard> cards = gameCardRepository.get5GameCardsByUser_Id(savedUser.getId());
        assertThat(cards.size()).isEqualTo(0);
    }
    
}
