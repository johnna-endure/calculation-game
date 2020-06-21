package com.springboot.calculationgame.domain.gamecard;

import com.springboot.calculationgame.domain.gamecard.execption.GameCardException;
import com.springboot.calculationgame.domain.gamecard.execption.ProblemException;
import org.junit.jupiter.api.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class ProblemGeneratorTest extends ProblemGenerator{

    private int include_min = -5;
    private int include_max = 5;

    @Test
    public void getAnswerTest() throws GameCardException {
        int exp1 =  getAnswer("3 + 5");
        assertThat(exp1).isEqualTo(8);

        int exp2 = getAnswer("3 - 5");
        assertThat(exp2).isEqualTo(-2);

        int exp3 = getAnswer("(-10) + (-5)");
        assertThat(exp3).isEqualTo(-15);

        int exp4 = getAnswer("5 + (-3)");
        assertThat(exp4).isEqualTo(2);

    }

    @Test
    public void getAnswer_식에서_숫자부분_잘못된_경우() {
        assertThatThrownBy(() -> getAnswer("a + 2"))
                .isInstanceOf(ProblemException.class)
                .hasMessage("잘못된 형식의 식입니다.");
    }

    @Test
    public void getAnswer_식이_잘못된_경우() {
        assertThatThrownBy(() -> getAnswer("1 ? 2"))
                .isInstanceOf(ProblemException.class)
                .hasMessage("잘못된 형식의 식입니다.");
    }


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
