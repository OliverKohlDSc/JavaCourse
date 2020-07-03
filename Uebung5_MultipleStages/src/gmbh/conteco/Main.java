package gmbh.conteco;

import javafx.application.Application;
import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Button button = new Button ("OK");
        Scene scene = new Scene(button, 200, 200);
        primaryStage.setTitle("Working with two Stages - Stage 1/2");
        primaryStage.setScene(scene);
        primaryStage.show();

        Stage stage = new Stage();
        stage.setTitle("Second stage");
        stage.setScene(new Scene(new Button("my OK button"), 500, 500));
        stage.show();
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}
