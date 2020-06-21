package com.springboot.calculationgame.testutil;

import com.springboot.calculationgame.domain.gamecard.ProblemGenerator;
import org.springframework.lang.Nullable;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class PrivateMethodTest {
    public static Object invokePrivateMethod(String methodName, Class<?> methodParameterType,
                                           Object workingObject, Object... methodParameter) {
        try {
            Method method = ProblemGenerator.class.getDeclaredMethod(methodName, methodParameterType);
            method.setAccessible(true);
            return method.invoke(workingObject, methodParameter);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
            return null;
        }
    }
}

