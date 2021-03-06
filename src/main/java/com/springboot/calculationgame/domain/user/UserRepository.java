package com.springboot.calculationgame.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    @Query("select u from User u where u.username = :username and u.password = :password")
    Optional<User> findUserByUsernameAndPassword(String username, String password);

}
