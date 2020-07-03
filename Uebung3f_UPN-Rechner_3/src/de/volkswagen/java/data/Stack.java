package de.volkswagen.java.data;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

public class Stack<T> extends Observable {

	private List<T> data;
	
	public Stack() {
		this.data = new ArrayList<>();
	}

	public void push(T element) {
		this.data.add(0, element);
		this.setChanged();
		this.notifyObservers();
	}
	
	public T pop() {
		if(this.data.size() > 0) {
			T result = this.data.remove(0);
			this.setChanged();
			this.notifyObservers();
			return result;
		} else {
			return null;
		}
	}
	
	public int size() {
		return this.data.size();
	}

	public T get(int index) {
		return this.data.get(index);
	}
	
}