package com.springboot.calculationgame.controller.rest;

import com.springboot.calculationgame.dto.UserInfo;
import com.springboot.calculationgame.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class RegisterRestController {

    private final UserService userService;

    @PostMapping("/user")
    public Long createUser(UserInfo userInfo) {
        return userService.create(userInfo.toEntity());
    }
}
