package com.company;

import java.util.Map;

public class Main {

    public static void main(String[] args) {
        final BiMap<Integer, String, ValueStore> map = new BiMap<>((i, s) -> new ValueStore(i,s, i + " " + s.toUpperCase()));

        ValueStore value1 = map.get(1, "one");
        System.out.println(value1);

        ValueStore value2 = map.get(1, "one");
        System.out.println(value1 == value2);

        map.get(1, "two");
        map.get(5, "red");
        map.get(5, "blue");
        map.get(5, "green");

        Map<String, ValueStore> map1 = map.get(1);
        for (Map.Entry<String, ValueStore> e : map1.entrySet()) {
            System.out.println(e.getKey() + " ==> " + e.getValue());
        }

        Map<String, ValueStore> map2 = map.get(5);
        for (Map.Entry<String, ValueStore> e : map2.entrySet()) {
            System.out.println(e.getKey() + " ==> " + e.getValue());
        }
    }
}
