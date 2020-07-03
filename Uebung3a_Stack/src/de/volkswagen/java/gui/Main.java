package de.volkswagen.java.gui;

import de.volkswagen.java.data.Stack;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
	
	@Override
	public void start(Stage primaryStage) throws Exception {

		HBox dataInput = new HBox(2.0);
		
		TextField input = new TextField();
		dataInput.getChildren().add(input);
		HBox.setHgrow(input, Priority.SOMETIMES);
	
		Button pushButton = new Button("Push");
		dataInput.getChildren().add(pushButton);
		HBox.setHgrow(pushButton, Priority.NEVER);
		
		Button popButton = new Button("Pop");
		dataInput.getChildren().add(popButton);
		HBox.setHgrow(popButton, Priority.NEVER);
		
		VBox vbox = new VBox(dataInput);
		
		StackDisplay stackDisplay = new StackDisplay(this.stack);

		pushButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				Main.this.stack.push(Double.parseDouble(input.getText()));
				stackDisplay.update();
				input.setText("");
			}
		});
		popButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				input.setText(Double.toString(Main.this.stack.pop()));
				stackDisplay.update();
			}
		});
		
		BorderPane root = new BorderPane(vbox, null, stackDisplay, null, null);
		Scene scene = new Scene(root, 600, 300);

		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public static void main(String[] args) {
		
		Application.launch();
		
	}
	
}
