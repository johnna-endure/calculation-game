package com.springboot.calculationgame.service.user;

import com.springboot.calculationgame.domain.user.User;
import com.springboot.calculationgame.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;

    /**
     *
     * @param user User 엔티티
     * @return 생성한 User 엔티티의 id를 반환합니다.
     */
    @Transactional
    public Long create(User user) {
        return userRepository.save(user).getId();
    }

    /**
     * 유저가 등록된 유저인지 판별
     * @param user User 엔티티
     * @return 등록된 유저라면 true, 아니라면 false
     */
    public boolean checkUser(User user) {
        Optional<User> userOpt = userRepository.findUserByUsername(user.getUsername());
        return userOpt.map(u -> true).orElseGet(() -> false);
    }

}
