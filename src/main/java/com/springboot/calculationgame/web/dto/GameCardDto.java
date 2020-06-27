package com.springboot.calculationgame.web.dto;

import com.springboot.calculationgame.domain.gamecard.GameCard;
import com.springboot.calculationgame.domain.gamecard.Problem;
import lombok.Getter;
import lombok.ToString;

/*
리스트뷰에 보여지기 위한 GameCardDto

이름이 딱히 마음에 들지 않지만 일단은...
 */
@Getter
@ToString
public class GameCardDto {
    private Problem problem;
    private boolean solved;

    public GameCardDto(GameCard gameCard) {
        this.problem = gameCard.getProblem();
        this.solved = gameCard.isSolved();
    }
}
