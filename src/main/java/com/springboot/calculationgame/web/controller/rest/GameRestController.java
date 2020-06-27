package com.springboot.calculationgame.web.controller.rest;

import com.springboot.calculationgame.domain.gamecard.GameCard;
import com.springboot.calculationgame.domain.gamecard.execption.GameCardException;
import com.springboot.calculationgame.web.dto.GameCardDto;
import com.springboot.calculationgame.web.dto.GameResponse;
import com.springboot.calculationgame.web.dto.GameResult;
import com.springboot.calculationgame.web.dto.ProblemDto;
import com.springboot.calculationgame.web.service.gamecard.GameCardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
public class GameRestController {

    private final GameCardService gameCardService;

    @GetMapping("/game")
    public String sendExpression() {
        return gameCardService.createExpression();
    }

    @PostMapping("/game")
    public GameResponse saveGameCard(@RequestBody ProblemDto problemDto) {
        try{
            GameCard gameCard = gameCardService.saveGameCard(problemDto)
                    .orElseThrow(() -> new GameCardException("GameCard 저장에 실패했습니다."));
            return new GameResponse(gameCard);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @GetMapping("/games/{id}")
    public List<GameCardDto> getGameCards(@PathVariable Long id) {
        return gameCardService.get5GameCards(id);
    }
}
