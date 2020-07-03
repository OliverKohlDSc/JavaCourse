package sample;

public interface C extends  A {
    default void display() {
        System.out.println("Displaying C");
    }
}