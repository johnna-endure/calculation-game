package com.springboot.calculationgame.domain.gamecard;

import com.springboot.calculationgame.domain.gamecard.execption.GameCardException;
import com.springboot.calculationgame.domain.gamecard.execption.ProblemException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class ProblemCheckerTest {

    private ProblemChecker problemChecker ;

    @BeforeEach
    public void beforeEach() {
        problemChecker = new ProblemChecker();
    }

    @Test
    public void getAnswerTest() throws GameCardException {

        int exp1 = problemChecker.getAnswer("3 + 5");
        assertThat(exp1).isEqualTo(8);

        int exp2 = problemChecker.getAnswer("3 - 5");
        assertThat(exp2).isEqualTo(-2);

        int exp3 = problemChecker.getAnswer("(-10) + (-5)");
        assertThat(exp3).isEqualTo(-15);

        int exp4 = problemChecker.getAnswer("5 + (-3)");
        assertThat(exp4).isEqualTo(2);

    }

    @Test
    public void getAnswer_식에서_숫자부분_잘못된_경우() {
        assertThatThrownBy(() -> problemChecker.getAnswer("a + 2"))
                .isInstanceOf(ProblemException.class)
                .hasMessage("잘못된 형식의 식입니다.");
    }

    @Test
    public void getAnswer_식이_잘못된_경우() {
        assertThatThrownBy(() -> problemChecker.getAnswer("1 ? 2"))
                .isInstanceOf(ProblemException.class)
                .hasMessage("잘못된 형식의 식입니다.");
    }


}
