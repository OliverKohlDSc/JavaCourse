package com.company;

import java.math.BigDecimal;
import java.util.List;

public class Calculate {
    public static double add1(List<? extends Number> list) {
        double result = 0;
        for (Number number : list) {
            result += number.doubleValue();
        }

        return result;
    }

    public static <T extends Number> T add2(List<T> list) {
        T result = (T)(Number)new BigDecimal("0");

        for (T number : list) {
            result = (T)(Number)new BigDecimal(number.toString())
                    .add(new BigDecimal(result.toString()));
        }

        return result;
    }
}
