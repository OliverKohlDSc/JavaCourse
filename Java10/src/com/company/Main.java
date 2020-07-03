package com.company;

import java.util.List;

public class Main {

    public static void main(String[] args) {
	    // JEP 286
        // Local variable type inference
        var numbers = List.of(1,2,3,4,5);  // inferred value ArrayList<Integer>

        for (var number : numbers ) {
            System.out.println(number);
        }

        // -XX:+UnlockkExperimentalVMOptions -XX:UseJVMCICompiler

        //  -Xshare:on -XX:+UseAppCDS -XX:DumpLoadedClassList=hello_world:lst -cp hello_world.jar HelloWorld
        // -Xshare:off -XX:+UseAppCDS
    }
}
