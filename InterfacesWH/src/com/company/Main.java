package com.company;

import java.io.BufferedWriter;
import java.io.File;
import java.util.function.Function;

public class Main {
    static Logger logger = null;

    enum LogType {
        CONSOLE,
        FILE
    }

    public static void main(String[] args) {

        LogType logType = LogType.CONSOLE;

        if (logType == LogType.CONSOLE)
            /*
            logger = new Logger() {
                @Override
                public void log(Number number) {
                    System.out.println(number);
                }
            };
             */
            //logger = number -> System.out.println(number);
            logger = System.out::println;
        else {
            //BufferedWriter
        }

        logger.log(3.1415f);

        // Schritt 1 - anonymous inner class
        System.out.println(new Person("Albert", "Einstein", new PersonenJob() {
            @Override
            public String getJob() {
                return "Physiker";
            }
        }) );

        // Lambda Expression
        System.out.println(new Person("Peter", "Maier",
            () -> {
                return "Verkäufer";
            }
        ));

        // Lambda expression
        System.out.println(new Person("Albert", "Einstein", () ->
                "Physiker"
          ) );

        // Lambda expression
        System.out.println(new Person("Albert", "Einstein", () -> "Physiker"));

        System.out.println(new Person("Albert", "Einstein", () -> getJobName()));

        System.out.println(new Person("Albert", "Einstein", Main::getJobName));

        tryCatch((o) -> {
            System.out.println("Doing something ...");
            return "TEST";
        });

        tryCatch(new Function<Integer, String>() {
            @Override
            public String apply(Integer s) {
                System.out.println("Doing something ...");
                return "TEST";
            }
        });
    }

    public static <T,R> R tryCatch(Function<T, R> function) {
        try {
            return function.apply(null);
        }
        catch (Exception ex) {
            System.err.println(ex.getMessage());
            return null;
        }
    }

    public static String getJobName() {
        return "Reporter";
    }
}