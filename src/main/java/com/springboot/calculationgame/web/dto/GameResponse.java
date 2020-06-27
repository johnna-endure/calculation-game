package com.springboot.calculationgame.web.dto;

import com.springboot.calculationgame.domain.gamecard.GameCard;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
public class GameResponse {
    private boolean solved;
    private int score;

    public GameResponse(GameCard gameCard) {
        this.solved = gameCard.isSolved();
        this.score = gameCard.getUser().getScore().getTotal();
    }

    public GameResponse(boolean solved, int score) {
        this.solved = solved;
        this.score = score;
    }
}

