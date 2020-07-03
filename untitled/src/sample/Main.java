package sample;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    boolean clicked = true;

    @Override
    public void start(Stage primaryStage) throws Exception{
        Button button = new Button();
        Scene scene = new Scene(button);

        button.setText("Click mich!");
        button.setOnAction(event -> {
            if (clicked) {
                button.setText("Clicked");
                clicked = false;
            }
            else {
                button.setText("Click mich!");
                clicked = true;
            }
        });

        primaryStage.setTitle("TEST");
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
