import java.util.function.BiFunction;
import java.util.function.BinaryOperator;

public class Main {
    // two-arity specialization
    public Long process(long operand1, long operand2, BiFunction<Long, Long, Long> biFunc) {
        return biFunc.apply(operand1, operand2);
    }

    public static void main(String[] args) {
	    Main calculator = new Main();

        BinaryOperator<Long> add = (x, y) -> x + y;
        System.out.println("Addition: " + calculator.process(4,5, add));

        BinaryOperator<Long> subtract = (x, y) -> x - y;
        System.out.println("Subtract: " + calculator.process(4,5, subtract));

        BinaryOperator<Long> multiply = (x, y) -> x * y;
        System.out.println("Multiplication: " + calculator.process(4,5, multiply));

        BinaryOperator<Long> divide = (x, y) -> x / y;
        System.out.println("Division: " + calculator.process(4,5, divide));
    }
}