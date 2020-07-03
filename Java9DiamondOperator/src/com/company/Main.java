package com.company;

public class Main {

    public static void main(String[] args) {

        MyHandler<Integer> intHandler = new MyHandler<Integer>(0) {
            @Override
            void handle() {

            }
        };

        // ‘<>‘ cannot be used with anonymous classes.
        // MyHandler<Integer> intHandler2 = new MyHandler<>(10) { /* Anonymous Class */ };
        // MyHandler<?> handler = new MyHandler<>("One hundred") { /* Anonymous Class */ };

        MyHandler<Integer> intHandler3 = new MyHandler<Integer>(1) {
            @Override
            void handle() {

            }
        };

        MyHandler<? extends Integer> intHandler4 = new MyHandler<>(10) {
                @Override
                void handle() {

                }
        };

        MyHandler<?> handler2 = new MyHandler<>("One thousand") {
            @Override
            void handle() {

            }
        };
    }
}
