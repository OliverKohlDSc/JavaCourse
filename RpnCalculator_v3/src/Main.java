public class Main {
    public static void main(String[] args) {
       String inputOperation = "*";
       double inputOperand1 = 3;
       double inputOperand2 = 4;

        Operation operation = Operation.getOperationFromOperator(inputOperation);

        System.out.println("The result of your " + operation.toString() + " is " + operation.getResult(inputOperand1,
                inputOperand2));
    }
}