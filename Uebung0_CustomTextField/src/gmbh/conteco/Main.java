package gmbh.conteco;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        MyOwnTextField textField1 = new MyOwnTextField();
        MyOwnTextField textField2 = new MyOwnTextField();
        MyOwnTextField textField3 = new MyOwnTextField();
        Button button = new Button("Click me");

        HBox hBox = new HBox();
        hBox.getChildren().addAll(textField1, new Label("+"), textField2, new Label("="), textField3, button);

        button.setOnAction( event -> {
            int firstNum = Integer.parseInt(textField1.getText());
            int secondNum = Integer.parseInt(textField2.getText());
            textField3.setText(Integer.toString(firstNum + secondNum));
        });

        Scene scene = new Scene(hBox, 400, 50);
        primaryStage.setTitle("´´ Sample App ``");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}