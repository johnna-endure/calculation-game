package com.springboot.calculationgame.domain.gamecard;

import org.junit.jupiter.api.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.assertj.core.api.Assertions.assertThat;

public class ProblemGeneratorTest extends ProblemGenerator {

    private int include_min = -5;
    private int include_max = 5;


    @Test
    public void createExpressionTest() {
        String expression = createExpression();

        boolean ret = expression.matches("\\(?-?\\d+\\)? [\\+\\-] \\(?-?\\d+\\)?");
        assertThat(ret).isTrue();
    }

    /*
    createExpressionTest에 쓰인 정규 표현식 패턴 테스트
     */
    @Test
    public void patternTest(){
        String pattern = "\\(?-?\\d+\\)? [\\+\\-] \\(?-?\\d+\\)?";

        String test1 = "(-23) + (3)";
        assertThat(test1.matches(pattern)).isTrue();

        String test2 = "(-23) - (3)";
        assertThat(test2.matches(pattern)).isTrue();

        String test3 = "(-23) ? (3)";
        assertThat(test3.matches(pattern)).isFalse();

        String test4 = "(-23) + (-3)";
        assertThat(test4.matches(pattern)).isTrue();

        String test5 = "23 + 3";
        assertThat(test5.matches(pattern)).isTrue();

        Matcher matcher = Pattern.compile("\\(?(-?\\d+)\\)? ([\\+\\-]) \\(?(-?\\d+)\\)?").matcher("23 ? 3");
        assertThat(matcher.matches()).isFalse();
    }

    @Test
    public void getRandomIntAsStringTest() {
        String randomIntString = getRandomIntAsString();
        assertThat(randomIntString.matches("\\(?-?\\d+\\)?")).isTrue();
    }
}
