package com.company;

import java.util.ArrayList;
import java.util.List;

public class Employee {
    public int id;
    public String name;
    public int sal;

    public Employee (int id, String name, int sal) {
        this.id = this.id;
        this.name = name;
        this.sal = sal;
    }

    public static List<Employee> getEmpList(){
        List<Employee> list = new ArrayList<>();
        list.add(new Employee(1, "A", 2000));
        list.add(new Employee(2, "B", 3000));
        list.add(new Employee(3, "C", 4000));
        list.add(new Employee(4, "D", 5000));
        return list;
    }
}
