package com.springboot.calculationgame.domain.gamecard;

import com.springboot.calculationgame.domain.gamecard.execption.OperatorException;
import com.springboot.calculationgame.domain.gamecard.execption.ProblemException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ProblemChecker {
    public static int getAnswer(String expression) {
        Matcher matcher = Pattern.compile("\\(?(-?\\d+)\\)? ([\\+\\-]) \\(?(-?\\d+)\\)?").matcher(expression);

        if(!matcher.matches()) {
            throw new ProblemException("잘못된 형식의 식입니다.");
        }

        int a = Integer.parseInt(matcher.group(1));
        Operator op = Operator.getOperatorBy(matcher.group(2))
                .orElseThrow(() -> new OperatorException("지원하지 않는 연산자입니다."));
        int b = Integer.parseInt(matcher.group(3));
        return op.calculate(a,b);
    }
}
