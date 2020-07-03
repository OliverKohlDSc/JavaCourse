package gmbh.conteco;

public interface Handler<T> {
    public abstract void handle(T value);
}