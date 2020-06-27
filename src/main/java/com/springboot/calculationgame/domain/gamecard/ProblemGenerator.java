package com.springboot.calculationgame.domain.gamecard;

import lombok.NoArgsConstructor;

import java.util.Random;
import java.util.stream.Stream;

@NoArgsConstructor
public class ProblemGenerator {

    private int include_min = -5;
    private int include_max = 5;

    private Random random = new Random();

    public ProblemGenerator(int include_min, int include_max) {
        this.include_min = include_min;
        this.include_max = include_max;
    }

    public String createExpression(){
        String a = getRandomIntAsString();
        String b = getRandomIntAsString();
        String operator = Operator.getRandomOperator().getOperatorSign();

        return Stream.of(a,operator,b)
                .reduce((first, second) -> first.concat(" "+second)).get().trim();

    }

    protected String getRandomIntAsString() {
        int randomInt = getRandomInt(include_min, include_max);

        if(randomInt < 0) return "(" + randomInt + ")";
        return String.valueOf(randomInt);
    }

    private int getRandomInt(int include_min, int include_max) {
        int length = include_max - include_min + 1;
        return random.nextInt(length) + include_min;
    }
}
