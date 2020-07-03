public class Main {

    // Generic Stack -> Integer, Float, Double, Long
    // Implementation eines Stacks
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

    public double process(double operand1, double operand2, Calculator calculator) {
        return calculator.calculate(operand1, operand2);
    }

    public static void main(String[] args) {
        Main calculator = new Main();

        // The traditional way using anonymous class
        System.out.println("Addition: " + calculator.process(3, 4, new Calculator() {
            @Override
            public Double calculate(Double operand1, Double operand2) {
                return operand1 + operand2;
            }
        }));

        // the lambda way; how simplified the code became
        Calculator calcSubtraction = (Double operand1, Double operand2) -> {
          return operand1 - operand2;
        };
        System.out.println("Subtraction: " + calculator.process(3, 4, calcSubtraction));

        // the lambda way; further simplification using 'Type Inference'
        System.out.println("Multiplication: " +
                calculator.process(3,4, (num1, num2) -> {
                    return num1 * num2;
                }));

        // the lamba way; the simplified approach
        System.out.println("Divide: " + calculator.process(3,4, (num1, num2) -> num1 / num2));
    }
}