package com.springboot.calculationgame.domain.gamecard;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Getter @ToString
@NoArgsConstructor
@Embeddable
public class Problem {

    @Builder
    public Problem(String expression, int answer) {
        this.expression = expression;
        this.answer = answer;
    }

    @Column(nullable = false)
    private String expression;

    @Column(nullable = false)
    private int answer;
}
