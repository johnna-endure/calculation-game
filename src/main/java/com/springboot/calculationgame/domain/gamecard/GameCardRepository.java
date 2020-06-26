package com.springboot.calculationgame.domain.gamecard;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface GameCardRepository extends JpaRepository<GameCard, Long> {
    //서브쿼리를 이용해서 일단 구현해놓음. 나중에 다른 방법으로 해볼것
    @Query(value = "SELECT * FROM (SELECT * FROM GAME_CARD WHERE USER_ID = ?1) ORDER BY CARD_ID DESC LIMIT 5"
            ,nativeQuery = true)
    List<GameCard> get5GameCardsByUser_Id(Long userId);
}
