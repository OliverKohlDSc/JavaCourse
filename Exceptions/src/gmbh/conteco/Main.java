package gmbh.conteco;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) throws ClassNotFoundException {
	    //            Object
        //              |
        //             Throwable
        //     Exception                       Error
        //  Unchecked Exception
        //       Runtime exceptions                 Virtual machine error
        //       NullPointer exceptions
        //  Checked Exception                       Assert error
        //      IO Exception
        //      Compile time exception



        //String str = null;
        //System.out.println(str.length());

        /*
        int a = 1;
        int b = 0;
        int result = 0;

        try {
            result = computeDivision(a,b);
            System.out.println(result);
        }
        catch (ArithmeticException ex)
        {
            System.err.println(ex.getMessage());
        }
        */

        //String input = "TEST";
        //int i = Integer.parseInt(input);

        /*
                      // 0,1,2 indices  -> length 3
                      // ArrayIndexOutOfBoundsException
        int[] numbers = {1,2,3};
        System.out.println(numbers[4]);

        // java.lang.InterruptedException
        Class.forName("org.h2.Driver");

        // InsufficientFundsException
        java.sql.Connection connection;
        try {
            new FileInputStream("");
        } catch (FileNotFoundException | SecurityException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }
*/
        // Streams Java 1.0 - 1.7
            // System.out
            // System.in
            // System.err
            // FileInputStream
            // FileOutputStream
        // Java 8 Streams = Java Stream API
        java.util.List<String> names = java.util.List.of("Albert", "Ahmed", "Jenny", "Lisa");

        java.util.List<String> names_sorted = names.stream().sorted().skip(1).sorted().collect(Collectors.toList());

        Stream<Integer> infiniteStream = Stream.iterate(0,i -> i +2);

        List<Integer> result = infiniteStream.limit(10).collect(Collectors.toList());

        System.out.println(result);
    }

    static int divideByZero(int a, int b) {
        int i = a / b;

        return i;
    }

    static int computeDivision(int a, int b) {
        int result = 0;

        try {
            result = divideByZero(a,b);
        }
        catch (NumberFormatException ex)
        {
            System.err.println(ex.getMessage());
        }

        return result;
    }
}
