package gmbh.conteco;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {
        ClockPane clock = new ClockPane();
        String timeString =  clock.getHour() + ":" +
                clock.getMinute() + ":" +
                clock.getSecond();

        Label lblCurrentTime = new Label(timeString);

        BorderPane pane = new BorderPane();
        pane.setCenter(clock);
        pane.setBottom(lblCurrentTime);
        BorderPane.setAlignment(lblCurrentTime, Pos.TOP_CENTER);

        Scene scene2 = new Scene(pane, 250, 250);
        primaryStage.setTitle("Clock");
        primaryStage.setScene(scene2);
        primaryStage.show();
    }
}

