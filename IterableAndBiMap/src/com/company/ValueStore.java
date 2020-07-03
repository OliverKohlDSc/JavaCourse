package com.company;

public class ValueStore {
    public final int i;
    public final String s;
    public final String data;

    public ValueStore(int i, String s, String data) {
        this.i = i;
        this.s = s;
        this.data = data;
    }

    @Override
    public String toString() {
        return "ValueStore [i = " + i + ", s = " + s + ", data = " + data + "]";
    }
}
