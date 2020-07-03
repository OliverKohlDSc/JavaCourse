package de.volkswagen.java.gui;

import java.util.function.Function;

import de.volkswagen.java.data.Stack;
import javafx.scene.control.Button;

public class CalcButton<T> extends Button {
	
	private Stack<T> stack;
	private int numberOfOperands;

	public CalcButton(String text, Stack<T> stack, StackDisplay display, int numberOfOperands, Function<Stack<T>, T> calculation) {
		super(text);
		this.stack = stack;
		this.numberOfOperands = numberOfOperands;
		
		this.setOnAction(e -> {
			stack.push(calculation.apply(stack));
			display.update();
			this.enable();
		});
	}

	public void enable() {
		this.setDisable(this.stack.size() < this.numberOfOperands);
	}

	
	
}
