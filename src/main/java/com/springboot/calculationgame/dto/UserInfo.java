package com.springboot.calculationgame.dto;

import com.springboot.calculationgame.domain.user.User;
import lombok.*;

@Getter
@ToString
@RequiredArgsConstructor
public class UserInfo {

    private final String username;
    private final String password;

    public User toEntity() {
        return User.builder()
                .username(username)
                .password(password)
                .build();
    }
}
