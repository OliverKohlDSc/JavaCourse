package com.company;

public abstract class MyHandler<T> {

    private T content;

    public MyHandler(T content) {
        this.content = content;
        System.out.println("constructor for MyHandler with content '" + content.toString() +  "' called.");
    }

    public T getcontent() {
        return content;
    }

    public void setContent(T content) {
        this.content = content;
    }

    abstract void handle();
}
