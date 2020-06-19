package com.springboot.calculationgame.controller.rest;

import com.springboot.calculationgame.dto.UserInfo;
import com.springboot.calculationgame.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class LoginRestController {

    private final UserService userService;

    @PostMapping("/user/check")
    public Long loginCheck(@RequestBody UserInfo userInfo) {
        return userService.checkUser(userInfo.toEntity());
    }

}
