package com.springboot.calculationgame.domain.gamecard;

import com.springboot.calculationgame.domain.gamecard.execption.OperatorException;

import java.util.Optional;
import java.util.Random;

public enum Operator {
    PLUS("+"),
    MINUS("-");

    private final String operatorSign;

    Operator(String operatorSign) {
        this.operatorSign = operatorSign;
    }

    public static Operator getRandomOperator() {
        Operator[] operators = Operator.values();
        int length = operators.length;
        int randomIndex = new Random().nextInt(length);
        return Operator.values()[randomIndex];
    }

    public static Optional<Operator> getOperatorBy(String operatorSign) {
        if(operatorSign.equals("+")) return Optional.of(Operator.PLUS);
        if(operatorSign.equals("-")) return Optional.of(Operator.MINUS);
        return Optional.empty();
    }

    public String getOperatorSign() {
        return this.operatorSign;
    }

    public int calculate(int left, int right) throws OperatorException {
        if(operatorSign.equals("+")) return left + right;
        if(operatorSign.equals("-")) return left - right;
        throw new OperatorException("이 인스턴스는 지원하지 않는 연산자를 가지고 있습니다.");
    }
}

