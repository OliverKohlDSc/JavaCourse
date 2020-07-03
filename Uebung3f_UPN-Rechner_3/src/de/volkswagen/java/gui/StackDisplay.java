package de.volkswagen.java.gui;

import java.util.Observable;
import java.util.Observer;

import de.volkswagen.java.data.Stack;
import javafx.scene.control.ListView;

public class StackDisplay extends ListView<String> implements Observer{

	private Stack<?> stack;

	public StackDisplay(Stack<?> stack) {
		this.stack = stack;
		stack.addObserver(this);
	}

	public void update() {
		this.getItems().clear();
		for(int i = 0; i < this.stack.size(); i++) {
			this.getItems().add(this.stack.get(i).toString());
		}
	}

	@Override
	public void update(Observable o, Object arg) {
		this.update();
	}
	
}
