package gmbh.conteco;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        Group root = new Group();

        Canvas canvas = new Canvas(800, 800);
        root.getChildren().add(canvas);

        EventHandler<MouseEvent> mouseevent = new CanvasMouseHandler(canvas);
        canvas.addEventHandler(MouseEvent.MOUSE_PRESSED, mouseevent);

        Scene scene = new Scene(root, 800, 800);
        stage.setScene(scene);
        stage.show();
    }
}