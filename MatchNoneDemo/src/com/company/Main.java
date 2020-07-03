package com.company;

import java.util.function.Predicate;
import java.util.List;

public class Main {

    public static void main(String[] args) {
	    Predicate<Employee> predicate1 = e -> e.id < 10 && e.name.startsWith("A");
	    Predicate<Employee> predicate2 = e -> e.sal < 1000;

        List<Employee> list = Employee.getEmpList();
        //using allMatch
        boolean b1 = list.stream().allMatch(predicate1);
        System.out.println(b1);
        boolean b2 = list.stream().allMatch(predicate2);
        System.out.println(b2);
        //using anyMatch
        boolean b3 = list.stream().anyMatch(predicate2);
        System.out.println(b3);
        boolean b4 = list.stream().anyMatch(predicate1);
        System.out.println(b4);
        //using noneMatch
        boolean b5 = list.stream().noneMatch(predicate2);
        System.out.println(b5);
    }
}
