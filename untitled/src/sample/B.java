package sample;

public interface B extends  A {
    default void display() {
        System.out.println("Displaying B");
    }
}
