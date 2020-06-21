package com.springboot.calculationgame.domain.gamecard;

import org.springframework.data.jpa.repository.JpaRepository;

public interface GameCardRepository extends JpaRepository<GameCard, Long> {
}
