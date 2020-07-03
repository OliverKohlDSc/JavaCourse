package com.company;

import java.io.IOException;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class Main {

    public void myPublicMethod() {

    }

    private void myPrivateMethod() {

    }

    class Nested {
        public void myNestedPublicMethod() {
            myPrivateMethod();
        }
    }

    public static void main(String[] args) {
        String s = "Winter";
        System.out.println(s.isBlank());

        String s2 = "";
        System.out.println(s2.isBlank());

        String s3 = "OK\nOK\nLine3";
        System.out.println(s3);
        System.out.println(s3.lines().collect(Collectors.toList()));

        String s4 = "    mein Name     ";
        s4.trim();
        System.out.print(">");
        System.out.print(s4.strip());
        System.out.println("<");

        System.out.print(">");
        System.out.print(s4.stripLeading());
        System.out.println("<");

        System.out.print(">");
        System.out.print(s4.stripTrailing());
        System.out.println("<");

        String str = "=".repeat(3);
        System.out.println(str);

        // Local type inference in Java 10
        var list = new ArrayList<String>();

        try {
            Path path = Files.writeString(
                    Files.createTempFile("meineDatei", ".txt"),
                    "This is my content");
        } catch (IOException e) {
            e.printStackTrace();
        }

        String result = "";
        String day = "M";

        switch(day) {
            case "M":
            case "W":
            case "F":
                result = "MWF";
                break;
        }

        String result2 = switch (day) {
            case "M", "W", "F" -> "MWF";
            case "T", "TH", "S" -> "TTS";
            default -> {
                if (day.isEmpty())
                    break "Please insert a valid day.";
                else
                    break "Look like a Sunday.";
            }
        }


        // JEP 323 : Local-Variable Syntax for Lambda Parameters
        //(s1, s2) -> s1 + s2;

        Main main = new Main();
        Method m = main.getClass().getDeclareMethod("myPrivateMethod");
        m.invoke(main);

    }
}