package com.springboot.calculationgame.web.controller.rest;

import com.springboot.calculationgame.web.dto.UserInfo;
import com.springboot.calculationgame.web.service.user.UserService;
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
