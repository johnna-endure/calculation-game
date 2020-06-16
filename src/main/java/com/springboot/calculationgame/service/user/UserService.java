package com.springboot.calculationgame.service.user;

import com.springboot.calculationgame.domain.user.User;
import com.springboot.calculationgame.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;

    @Transactional
    public Long create(User user) {
        return userRepository.save(user).getId();
    }
}
