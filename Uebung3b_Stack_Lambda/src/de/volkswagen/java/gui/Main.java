package de.volkswagen.java.gui;

import de.volkswagen.java.data.Stack;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {

	private Stack<Double> stack = new Stack<>();
	private Button popButton;
	private Button pushButton;
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		HBox dataInput = new HBox(2.0);
		
		TextField input = new TextField();
		dataInput.getChildren().add(input);
		HBox.setHgrow(input, Priority.SOMETIMES);
		
		this.pushButton = new Button("Push");
		dataInput.getChildren().add(this.pushButton);
		HBox.setHgrow(this.pushButton, Priority.NEVER);
		
		this.popButton = new Button("Pop");
		dataInput.getChildren().add(this.popButton);
		HBox.setHgrow(this.popButton, Priority.NEVER);
		
		VBox vbox = new VBox(dataInput);
		
		StackDisplay stackDisplay = new StackDisplay(this.stack);

		this.pushButton.setOnAction(e -> {
			this.stack.push(Double.parseDouble(input.getText()));
			stackDisplay.update();
			input.setText("");
			this.enableButtons();
		});
		this.popButton.setOnAction(e -> {
			input.setText(Double.toString(this.stack.pop()));
			stackDisplay.update();
			this.enableButtons();
		});
		
		BorderPane root = new BorderPane(vbox, null, stackDisplay, null, null);
		Scene scene = new Scene(root, 600, 300);

		this.enableButtons();
		
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	private void enableButtons() {
		this.popButton.setDisable(this.stack.size() == 0);
	}
	
	public static void main(String[] args) {
		
		Application.launch();
		
	}
	
}
