package com.springboot.calculationgame.domain.gamecard;


public class Problem {

    public Problem(String expression, int answer) {
        this.expression = expression;
        this.answer = answer;
    }

    private String expression;
    private int answer;

    public String getExpression() {
        return expression;
    }

    public int getAnswer() {
        return answer;
    }

    @Override
    public String toString() {
        return "Problem{" +
                "expression='" + expression + '\'' +
                ", answer=" + answer +
                '}';
    }
}
