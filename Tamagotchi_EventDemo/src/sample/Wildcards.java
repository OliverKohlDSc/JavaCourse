package sample;

import java.util.List;

public class Wildcards {
    /*
    // Number, Integer, Float, Double, Long
    public static <T extends Number> double add(List<T> list) {
        double result = 0.0;
        for (Number n : list) {
            result += n.doubleValue();
        }
        return result;
    }

    // Number, Integer, Float, Double, Long - UPPER BOUND
    public static double add2(List<? extends Number> list) {
        double result = 0.0;
        for (Number n : list) {
            result += n.doubleValue();
        }
        return result;
    }

    // Integer, Number, Object - LOWER BOUND
    public static double add3(List<? super Integer> list) {
        double result = 0.0;
        for (Number n : list) {
            result += n.doubleValue();
        }
        return result;
    }

    // Achtung -> kein Generic mehr -> Generic wird weiterverwendet
    public static double add4(List<Number> list) {
        double result = 0.0;
        for (Number n : list) {
            result += n.doubleValue();
        }
        return result;
    }
    */
}
