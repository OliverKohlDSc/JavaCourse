package com.company;
import java.util.*;

public class Main {
    private static class Person {
        public String vorname;
        public String nachname;

        public Person(String vorname, String nachname) {
            this.vorname = vorname;
            this.nachname = nachname;
        }

        @Override
        public String toString() {
            return vorname + " " + nachname;
        }
    }

    // Bounded Wildcard (Upper Bound)
    // -> Gebunden (eingschränkt)
    // Wildcard ?
    // Upper Bound
    public static double sum(List<? extends Number> list) {
        double sum = 0;

        for (Number n : list) {
            sum += n.doubleValue();
        }

        return sum;
    }

    // Unbounded Wildcard
    // Ungebunden (uneingeschränkt, frei)
    // Wildcard ?
    //                           List<? extends Object>
    public static void printData(List<?> list) {
        for (Object obj : list) {
            System.out.println(obj + "::");
        }
    }

    public static void main(String[] args) {
        /*
	    List list = new ArrayList();
	    list.add("Elvis");
	    list.add("Peter");
	    list.add(3);
	    list.add(3.1415f);
	    list.add(new ArrayList());
	    //list.add(new Float(3.1415f));
	    //list.add((Object)new Float(3.1415f));
	    list.add(new Person("John", "Doe"));

	    for (Object obj : list) {
            System.out.println(obj);
        }
	    */

        List<String> stringsList = new ArrayList</*String*/>();

        stringsList.add("Elvis");
        stringsList.add("Peter");
        //stringsList.add(new Person("John", "Doe"));

        List<Person> personList = new ArrayList<>();
        personList.add(new Person("Jane", "Doe"));

        System.out.println();

        GenericsType<Person> type = new GenericsType<>();
        type.set(new Person("Albert", "Einstein"));

        //String str = (String)type.get();
        System.out.println(type);


        /*
        Generic Buchstaben:

        T = type 1 (= 1 parameter oder einziger) (Iterator<T>, Comparable<T>, Class<T> wie z.B. Integer, Float, String, eigener Datentyp (z.B. Person)
        U = type 2 (= 2 parameter)
        S = type 2 (= 2 parameter), type 3 (= 3 parameter)

        N = Number

        S,U,V etc. - 2nd, 3rd, 4th type

        L & R = left, right (linker Wert, rechter Wert)

        E = enum (https://docs.oracle.com/javase/8/docs/api/java/lang/Enum.html) bei Enums
        E = element (https://docs.oracle.com/javase/8/docs/api/java/util/Collection.html) bei Collections

        K=  key (Schlüssel) (https://docs.oracle.com/javase/8/docs/api/java/util/Map.html)
        V=  value (Wert) (https://docs.oracle.com/javase/8/docs/api/java/util/Map.html)
        R=  return/result type (Rückgabetype) (https://docs.oracle.com/javase/8/docs/api/java/util/function/BiFunction.html)
        O=  object
        ?=
         */

        List<Integer> ints = new ArrayList<>();
        ints.add(3);
        ints.add(10);
        ints.add(8);

        System.out.println(sum(ints));

        List<Float> floats = new ArrayList<>();
        floats.add(3.1415f);
        floats.add(1f);

        System.out.println(sum(floats));

        List<String> myDataList = new ArrayList<>();
        myDataList.add("Integer");
        myDataList.add("Float");

        printData(myDataList);

        /**
         *
         *  Bounded Wildcard - Upper Bound
         *      <? extends Number>
         *          MyInteger -> Integer -> Number
         *                       Integer -> Number
         *                       Float   -> Number
         *                       Double  -> Number
         *                       Long    -> Number
         *    Höchste Hierachieebene in der
         *
         *
         *                            +------------+
         *                            |   Number   |
         *                            +-----------+
         *                            |          |
         *                 +------------+       +------------+
         *                 |  Integer   +       |  Float   +
         *                 +------------+       +------------+
         */

         /*
            Bounded Wildcard - Lower Bound
                <? super Integer>

            Was ist das untereste Level der Vererbung
                                Integer
                                        ... Integer


                                Integer
                                        ... Number

                +------------+
                |   Number   |
                +-----------+
                    |
                +------------+
                |  Integer   +
                +------------+
        */

        List<GenericsType<?>> list = new ArrayList<>();

        GenericsType<Integer> myInt = new GenericsType<>();
        myInt.set(30);

        GenericsType<String> myString = new GenericsType<>();
        myString.set("Hallo");

        list.add(myInt);
        list.add(myString);

        for (GenericsType<?> t : list) {
            if (t.get() instanceof String)
            {

            }
            else
            {

            }
        }

        List<Integer> myInts = new ArrayList<>();
        myInts.add(5);
        myInts.add(3);
        myInts.add(7);

        /*
        double f1 = 0.1;
        double f2 = 0.2;

        double f3 = f1 + f2;
        System.out.println(f3);
        */

        System.out.println(Calculate.add1(myInts));
        System.out.println(Calculate.add2(myInts));

        List<Float> myFloats = new ArrayList<>();
        myFloats.add(5f);
        myFloats.add(3f);
        myFloats.add(7f);

        System.out.println(Calculate.add2(myFloats));

        /*
        System.out.println(Arrays.asList("Hello", "World")
                .stream()
                .reduce((acc, i) -> acc+i).get());
        */
    }
}