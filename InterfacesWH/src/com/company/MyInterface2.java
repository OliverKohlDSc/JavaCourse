package com.company;

public interface MyInterface2 {
    int test = 3;

    private void meth() {

    }

    public default double addNumber2(double numer1, double number2) {
        meth();
        return 0;
    }

    public default double substract(double number1, double number2) {
        System.out.println("Subtract -> MyInterface2");
        return 0;
    }
}
