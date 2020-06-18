package com.springboot.calculationgame.service;

import com.springboot.calculationgame.domain.user.User;
import com.springboot.calculationgame.domain.user.UserRepository;
import com.springboot.calculationgame.dto.UserInfo;
import com.springboot.calculationgame.service.user.UserService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class UserServiceTest {

    private UserService userService;

    @Autowired
    UserRepository userRepository;

    @BeforeEach
    public void init(){
        userService = new UserService(userRepository);
    }

    @AfterEach
    public void clearRepository(){
        userRepository.deleteAll();
    }

    @Test
    public void userRepository_주입여부확인(){
        assertThat(userRepository).isNotNull();
    }
    
    @Test
    public void create_유저_생성테스트() {
        User user = User.builder()
                .username("username")
                .password("password")
                .build();
        long id = userService.create(user);
        assertThat(id).isGreaterThan(0);

        User saved = userRepository.findById(id).orElseThrow(NoSuchElementException::new);
        assertThat(saved).isEqualToIgnoringGivenFields(user,"id");
    }

    @Test
    public void checkUser_로그인정보가_일치하는경우(){
        //given
        UserInfo loginUserInfo = new UserInfo("user","1234");
        userRepository.save(loginUserInfo.toEntity());

        //when
        boolean ret = userService.checkUser(loginUserInfo.toEntity());

        //then
        assertThat(ret).isTrue();
    }

    @Test
    public void checkUser_로그인정보가_일치하는않는경우(){
        //given
        UserInfo loginUserInfo = new UserInfo("whoareyou","1234");

        //when
        boolean ret = userService.checkUser(loginUserInfo.toEntity());

        //then
        assertThat(ret).isFalse();
    }

}
