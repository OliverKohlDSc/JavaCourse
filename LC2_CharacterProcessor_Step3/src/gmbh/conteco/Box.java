package gmbh.conteco;

public class Box<T> {
    public T value;
    public Box(T start) {
        this.value = start;
    }
    public String toString() {
        return this.value.toString();
    }
}