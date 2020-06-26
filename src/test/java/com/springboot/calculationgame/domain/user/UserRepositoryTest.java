package com.springboot.calculationgame.domain.user;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;

    @AfterEach
    public void clearDB() {
        userRepository.deleteAll();
    }

    @Test
    public void save_test(){
        User user = User.builder()
                .username("username")
                .password("password")
                .build();

        userRepository.save(user);
        User foundUser = userRepository.findAll().get(0);
        assertThat(foundUser).isEqualToIgnoringGivenFields(user, "id");
    }

    @Test
    public void findUserByUsername_테스트() {
        //given
        User user = User.builder()
                .username("username")
                .password("password")
                .build();

        userRepository.save(user);

        Optional<User> userOpt = userRepository.findUserByUsernameAndPassword("username", "password");
        assertThat(userOpt.get()).isEqualToIgnoringGivenFields(user,"id");
    }
}
