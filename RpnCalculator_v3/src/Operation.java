import java.util.function.BiFunction;
import java.util.stream.Stream;

// Generic Stack -> Integer, Float, Double, Long
// Implementation eines Stacks, ExpressionTree
// Push & Pop
// RPN (UPN) ... reverse polish notation, umgekehrte polnische Notation
// (7+3)*2
// +, -, *, /
// Operand1 (7) Operator (*) Operand2 (3)
// 1. Operanden: ! Fakultät
// x ^ 3
// x ^ 2
// 0. Operanden: PI
// 7 3 PLUS 2 MAL
// RechnenOperationen in einer Klasse
// Lambda Expressions, Functional Interface
// +------------------+
// +    7    8    9
// +    4    5    6
// +    1    2    3
// String -> repeat, split
// Default Method -> Formattierung -> Ausgabe
// For -> Streams -> .forEach
// Functional Interfaces -> überlegen -> vorgefertigte Interfaces gibt, die ich verwenden kann.

public enum Operation {
    Sum("Addition", "+", (x, y) -> x + y),
    Difference("Subtraction", "-", (x, y) -> x - y),
    Product ("Multiplication", "*", (x,y) -> x * y),
    Quotient("Division", "/", (x,y) -> x / y),
    Exponent("Exponent", "^", Math::pow); // Methodenreferenz

    private final String operator;
    private final String title;
    private final BiFunction<Double, Double, Double> equation;

    public static Operation getOperationFromOperator(String operator) {
        /*
        for (Operation currentOperation : Operation.values()) {
            if (operator.equals(currentOperation.getOperator())) {
                return currentOperation;
            }
        }
        return Operation.Sum;
        */
        return Stream.of(Operation.values()).filter(x -> x.operator.equals(operator)).findFirst().get();
    }

    private Operation(String title, String operator, BiFunction<Double, Double, Double> equation) {
        this.title = title;
        this.operator = operator;
        this.equation = equation;
    }

    public Double getResult(Double operand1, Double operand2) {
        return equation.apply(operand1, operand2);
    }

    @Override
    public String toString() {
        return title;
    }
}