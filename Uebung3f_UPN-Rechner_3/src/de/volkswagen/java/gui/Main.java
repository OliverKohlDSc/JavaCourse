package de.volkswagen.java.gui;

import java.util.ArrayList;
import java.util.List;

import de.volkswagen.java.data.Stack;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {

	private Stack<Double> stack = new Stack<>();
	private Button popButton;
	private Button pushButton;
	private List<CalcButton<Double>> calcButtons;
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("UPN-Rechner");

		HBox dataInput = new HBox(2.0);
		
		TextField input = new TextField();
		input.setEditable(false);
		dataInput.getChildren().add(input);
		HBox.setHgrow(input, Priority.SOMETIMES);
		
		this.pushButton = new Button("Push");
		dataInput.getChildren().add(this.pushButton);
		HBox.setHgrow(this.pushButton, Priority.NEVER);
		
		this.popButton = new Button("Pop");
		dataInput.getChildren().add(this.popButton);
		HBox.setHgrow(this.popButton, Priority.NEVER);
		
		
		GridPane digitsGrid = new GridPane();
		for(int i = 9; i > 0; i--) {
			Button button = new DigitInputButton(input, Integer.toString(i));
			digitsGrid.add(button, 2 - (9 - i) % 3, (9 - i) / 3);
			this.setupButtonLayout(button);
		}
		
		Button plusMinusButton = new InputButton("+/-", input, s -> {
			if(!s.isEmpty() && !s.equals("0")) {
				if(s.startsWith("-")) {
					return s.substring(1);
				} else {
					return "-" + s;
				}
			} else {
				return s;
			}
		});
		this.setupButtonLayout(plusMinusButton);
		digitsGrid.add(plusMinusButton, 0, 4);
		
		Button zeroButton = new DigitInputButton(input, "0");
		this.setupButtonLayout(zeroButton);
		digitsGrid.add(zeroButton, 1, 4);

		Button decimalButton = new InputButton(",", input, s -> {
			if(s.isEmpty()) {
				return "0.";
			} else if(s.contains(".")) {
				return s;
			} else {
				return s + ".";
			}
		});
		this.setupButtonLayout(decimalButton);
		digitsGrid.add(decimalButton, 2, 4);

		StackDisplay stackDisplay = new StackDisplay(this.stack);

		this.calcButtons = new ArrayList<>();
		this.calcButtons.add(new CalcButton<>("+", this.stack, 2, s -> s.pop() + s.pop()));
		this.calcButtons.add(new CalcButton<>("-", this.stack, 2, s -> s.pop() - s.pop()));
		this.calcButtons.add(new CalcButton<>("*", this.stack, 2, s -> s.pop() * s.pop()));
		this.calcButtons.add(new CalcButton<>("/", this.stack, 2, s -> s.pop() / s.pop()));
		this.calcButtons.add(new CalcButton<>("x²", this.stack, 1, s -> Math.pow(s.pop(), 2)));
		this.calcButtons.add(new CalcButton<>("x³", this.stack, 1, s -> Math.pow(s.pop(), 3)));
		this.calcButtons.add(new CalcButton<>("\u221A", this.stack, 1, s -> Math.sqrt(s.pop())));
		this.calcButtons.add(new CalcButton<>("\u03C0", this.stack, 0, s -> Math.PI));
		
		GridPane calcGrid = new GridPane();
		int i = 0;
		for(CalcButton<Double> button: this.calcButtons) {
			calcGrid.add(button, i % 3, i / 3);
			this.setupButtonLayout(button);
			i++;
		}
		
		VBox vbox = new VBox(5.0, dataInput, digitsGrid, calcGrid);
	
		this.pushButton.setOnAction(e -> {
			this.stack.push(Double.parseDouble(input.getText()));
			input.setText("");
		});
		this.popButton.setOnAction(e -> {
			input.setText(Double.toString(this.stack.pop()));
		});
		
		BorderPane root = new BorderPane(vbox, null, stackDisplay, null, null);
		Scene scene = new Scene(root, 600, 300);

		this.stack.addObserver((o, arg) -> this.enableButtons()) ;
		
		this.enableButtons();
		
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	private void setupButtonLayout(Button button) {
		button.setMaxWidth(Double.MAX_VALUE);
		GridPane.setHgrow(button, Priority.ALWAYS);
	}

	private void enableButtons() {
		this.popButton.setDisable(this.stack.size() == 0);
		for(CalcButton<Double> calcButton: this.calcButtons) {
			calcButton.enable();
		}
	}
	
	public static void main(String[] args) {
		
		Application.launch();
		
	}
	
}
