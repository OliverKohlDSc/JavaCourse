package de.volkswagen.java.gui;

import de.volkswagen.java.data.Stack;
import javafx.scene.control.ListView;

public class StackDisplay extends ListView<String> {

	private Stack<?> stack;

	public StackDisplay(Stack<?> stack) {
		this.stack = stack;
	}

	public void update() {
		this.getItems().clear();
		for(int i = 0; i < this.stack.size(); i++) {
			this.getItems().add(this.stack.get(i).toString());
		}
	}
	
}
