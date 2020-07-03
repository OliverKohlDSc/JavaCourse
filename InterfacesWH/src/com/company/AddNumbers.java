package com.company;

public class AddNumbers implements  MyInterface, MyInterface2 {
    @Override
    public double addNumbers(double number1, double number2) {
        return 0;
    }

    @Override
    public double addNumber2(double numer1, double number2) {
        return 0;
    }

    @Override
    public double substract(double number1, double number2) {
        System.out.println("Subtract -> AddNumbers");
        MyInterface.super.substract(number1, number2);
        return 0;
    }
}