package com.springboot.calculationgame.domain.gamecard;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface GameCardRepository extends JpaRepository<GameCard, Long> {
    //서브쿼리를 이용해서 일단 구현해놓음. 나중에 다른 방법으로 해볼것
    @Query(value = "select * from (select * from game_card WHERE user_id = ?1) as t2 order by card_id DESC limit 5"
            ,nativeQuery = true)
    List<GameCard> get5GameCardsByUser_Id(Long userId);
}
