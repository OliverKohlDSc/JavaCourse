package sample;

public interface A {
    default void display() {
        System.out.println("Displaying A");
    }
}
