package de.volkswagen.java.gui;

import javafx.scene.control.TextField;

public class DigitInputButton extends InputButton {

	public DigitInputButton(TextField textField, String digit) {
		super(digit, textField, s -> {
			if(s.equals("0")) {
				return digit;
			} else {
				return s + digit;
			}
		});
		
	}
	
}
