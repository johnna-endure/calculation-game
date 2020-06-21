package com.springboot.calculationgame.domain.gamecard;

import com.springboot.calculationgame.domain.gamecard.execption.GameCardException;
import com.springboot.calculationgame.domain.gamecard.execption.OperatorException;
import com.springboot.calculationgame.domain.gamecard.execption.ProblemException;
import lombok.NoArgsConstructor;

import java.util.Optional;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

@NoArgsConstructor
public class ProblemGenerator {

    private int include_min;
    private int include_max;

    private Random random = new Random();

    public ProblemGenerator(int include_min, int include_max) {
        this.include_min = include_min;
        this.include_max = include_max;
    }

    public Optional<Problem> createProblem() {
        String expression = createExpression();
        try {
            int answer = getAnswer(expression);
            Problem problem = new Problem(expression,answer);
            return Optional.of(problem);
        } catch (GameCardException e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

    protected String createExpression(){
        String a = getRandomIntAsString();
        String b = getRandomIntAsString();
        String operator = Operator.getRandomOperator().getOperatorSign();

        return Stream.of(a,operator,b)
                .reduce((first, second) -> first.concat(" "+second)).get();

    }

    protected int getAnswer(String expression) throws GameCardException {
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
