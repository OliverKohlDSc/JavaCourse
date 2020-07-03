package de.volkswagen.java.data;

import java.util.ArrayList;
import java.util.List;

public class Stack<T> {

	private List<T> data;
	
	public Stack() {
		this.data = new ArrayList<>();
	}

	public void push(T element) {
		this.data.add(0, element);
	}
	
	public T pop() {
		if(this.data.size() > 0) {
			return this.data.remove(0);
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