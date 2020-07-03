package de.volkswagen.java.gui;

import java.util.function.Function;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class InputButton extends Button {

	public InputButton(String label, TextField textField, Function<String, String> editFunction) {
		super(label);
		this.setOnAction(e -> {
			textField.setText(editFunction.apply(textField.getText()));
		});
	}
	
}
