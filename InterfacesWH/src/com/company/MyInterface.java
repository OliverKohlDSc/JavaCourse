package com.company;

public interface MyInterface {
    /* public abstract */ double addNumbers(double number1, double number2);

    public default double addNumber2(double numer1, double number2) {
        return 0;
    }

    public default double substract(double number1, double number2) {
        System.out.println("Subtract -> MyInterface");
        return 0;
    }
}
