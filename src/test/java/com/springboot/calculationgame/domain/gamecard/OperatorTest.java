package com.springboot.calculationgame.domain.gamecard;

import org.junit.jupiter.api.Test;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;

public class OperatorTest {

    @Test
    public void getRandomOperatorTest() {
        Operator[] operators = Operator.values();
        int testLoopNum = operators.length * 5;

        Set<Operator> operatorSet = IntStream.range(0, testLoopNum)
                .mapToObj(i -> Operator.getRandomOperator())
                .collect(Collectors.toSet());

        assertThat(operatorSet).isSubsetOf(operators);
    }
}
