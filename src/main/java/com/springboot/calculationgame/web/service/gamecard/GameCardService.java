package com.springboot.calculationgame.web.service.gamecard;

import com.springboot.calculationgame.domain.gamecard.GameCard;
import com.springboot.calculationgame.domain.gamecard.GameCardRepository;
import com.springboot.calculationgame.domain.gamecard.ProblemGenerator;
import com.springboot.calculationgame.domain.user.User;
import com.springboot.calculationgame.domain.user.UserRepository;
import com.springboot.calculationgame.web.dto.GameCardDto;
import com.springboot.calculationgame.web.dto.ProblemDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;


@RequiredArgsConstructor
@Service
public class GameCardService {

    private final UserRepository userRepository;
    private final GameCardRepository gameCardRepository;
    private ProblemGenerator generator = new ProblemGenerator();

    @Transactional
    public Optional<GameCard> saveGameCard(ProblemDto problemDto) {
        Optional<User> userOpt = userRepository.findById(problemDto.getId());
        try{
            User user = userOpt.orElseThrow(() -> new NoSuchElementException("id와 일치하는 사용자가 없습니다."));
            GameCard gameCard = new GameCard(user, problemDto.toProblem());
            gameCard = gameCardRepository.save(gameCard);
            if(gameCard.isSolved()) user.getScore().scoreUp();
            return Optional.of(gameCard);
        }catch(Exception e) {
            return Optional.empty();
        }
    }

    public String createExpression() {
        return generator.createExpression();
    }

    public List<GameCardDto> get5GameCards(Long id) {
        return gameCardRepository.get5GameCardsByUser_Id(id).stream()
                .map(card -> new GameCardDto(card))
                .collect(Collectors.toList());
    }
}
