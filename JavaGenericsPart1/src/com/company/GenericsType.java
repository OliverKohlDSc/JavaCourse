package com.company;

public class GenericsType<T> {
    private T t;

    public T get() {
        return t;
    }

    public void set(T t) {
        this.t = t;
    }

    @Override
    public String toString() {
        return t.toString();
    }
}