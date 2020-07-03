package sample;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.util.concurrent.Flow;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{

        primaryStage.setTitle("Hello World");

        MainWindowScene mainWindows = new MainWindowScene(new Group());
        primaryStage.setScene(mainWindows);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
