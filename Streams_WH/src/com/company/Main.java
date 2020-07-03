package com.company;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.function.IntConsumer;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
	    // Streams
        //   ->
        //    System.out
        //    System.in
        //    System.err
        //   -> File I/O Streams

        // Java 8 Streams -> Stream(s) API
        // Package java.util.stream
        //           references
        //               java.util.functions (@FunctionalInterface) functional interfaces

        // asList
        List<String> names = Arrays.asList("Albert", "Brad", "Conner", "David", "Emily");

        // Filter String
        System.out.println(names.stream().filter(string -> string.contains("a")).collect(Collectors.toList()));

        // Sorted
        List<Integer> numbers = Arrays.asList(9,7,1,2,5,3,8,4,6,0);
        System.out.println(numbers.stream().sorted().collect(Collectors.toList()));

        // Filter Integer
        List<Integer> evenNumbers = numbers.stream().filter(number -> number % 2 == 0).collect(Collectors.toList());
        System.out.println(evenNumbers);

        // Reduce
        int sumOfEvenNumbers = evenNumbers.stream().reduce(0,(ans,i) -> ans+i);
        System.out.println(sumOfEvenNumbers);

        // Map
        System.out.println(numbers.stream().map(element -> element*element).collect(Collectors.toList()));

        // Sorted + Distinct
        List<Integer> duplicateNumbers = Arrays.asList(0,1,2,3,0,0,4,5,6,0,9,8,9,7,2);
        System.out.println(duplicateNumbers.stream().sorted().distinct().collect(Collectors.toList()));

        // IntStream von Random
        // Limit
        // ForEach
        Random random = new Random();
        //random.ints().limit(10).forEach(value -> System.out.println(value));
        random.ints().limit(10).forEach(System.out::println);

        // parallelStream
        // count
        List<String> strings = Arrays.asList("abc", "", "bc", "", "def", "efg", "", "jklm");
        long count = strings.parallelStream().filter(string -> string.isEmpty()).count();
        System.out.println(count);


    }
}
