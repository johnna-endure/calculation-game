package com.springboot.calculationgame.web.dto;

import com.springboot.calculationgame.domain.gamecard.Problem;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter @ToString
@RequiredArgsConstructor
public class ProblemDto {
    private final Long id;
    private final String expression;
    private final int answer;

    public Problem toProblem() {
        return Problem.builder()
                .expression(expression)
                .answer(answer)
                .build();
    }
}
