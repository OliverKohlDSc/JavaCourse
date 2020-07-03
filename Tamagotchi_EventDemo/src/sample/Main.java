package sample;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        TamagotchiButton button = new TamagotchiButton();

        button.addEventHandler(HungerEvent.HUNGRY, event -> {
            System.out.println("I'm hungry.");
        });

        button.addEventHandler(HungerEvent.PEE, event -> {
            System.out.println("😊");
        });

        button.setHungry(true);

        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(button, 300, 275));
        primaryStage.show();

        List<Float> meineZahlen = new ArrayList<>();
        meineZahlen.add(3f);
        meineZahlen.add(5f);
        //Wildcards.add(meineZahlen);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
