package gmbh.conteco;

import javafx.animation.KeyFrame;
import javafx.animation.RotateTransition;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.scene.Group;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import javafx.scene.shape.*;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.util.Random;

import static gmbh.conteco.LightningEvent.PLASMA_STRIKE;

public class Main extends Application {
    private static final int FIELD_SIZE = 10;
    private static final Random random = new Random(42);

    @Override
    public void start(Stage primaryStage) throws Exception{
        TilePane field = generateField();

        Scene scene = new Scene(field);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();

        field.addEventHandler(PLASMA_STRIKE, event -> System.out.println("Field handled strike: " +  event.getI() + ", " + event.getJ()));

        periodicallyStrikeRandomNumbers(field);

        /*
        Button button = new Button("Click me!");
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

            }
        });

        button.addEventHandler(ActionEvent.ACTION, event -> System.out.println("Click"));

        button.addEventHandler(MouseEvent.MOUSE_ENTERED, event -> System.out.println("Mouse Entered"));

        button.addEventHandler(PLASMA_STRIKE, event -> System.out.println("Plasma"));

        Scene scene = new Scene(button);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
        */
    }

    public void periodicallyStrikeRandomNumbers(TilePane field) {
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(0), event -> strikeRandomNode(field)),
                new KeyFrame(Duration.seconds(2))
            );
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    private void strikeRandomNode(TilePane field) {
        LightningRectangle currentNode = (LightningRectangle)field.getChildren().get(random.nextInt(FIELD_SIZE * FIELD_SIZE));
        LightningEvent lightningEvent = new LightningEvent(this, currentNode, PLASMA_STRIKE);
        currentNode.fireEvent(lightningEvent);
    }

    public static TilePane generateField() {
        TilePane field = new TilePane();
        field.setPrefColumns(FIELD_SIZE);
        field.setMinWidth(TilePane.USE_PREF_SIZE);
        field.setMaxWidth(TilePane.USE_PREF_SIZE);

        for (int i = 0; i < FIELD_SIZE; i++) {
            for (int j = 0; j < FIELD_SIZE; j++) {
                field.getChildren().add(new LightningRectangle(i, j, new StrikeEventHandler()));
            }
        }

        return field;
    }

    public static void eventIntroduction(Stage primaryStage) {
        Circle circle = new Circle();
        circle.setRadius(25.0f);
        circle.setCenterX(300.0f);
        circle.setCenterY(150.0f);
        circle.setFill(Color.BROWN);
        circle.setStrokeWidth(20);

        Box box = new Box();
        box.setHeight(250.0);
        box.setWidth(250.0);
        box.setDepth(100);

        box.setTranslateX(150);
        box.setTranslateY(150);
        box.setTranslateZ(150);

        PhongMaterial material = new PhongMaterial();
        material.setDiffuseColor(Color.DARKSLATEBLUE);

        RotateTransition rotation = new RotateTransition();
        rotation.setAxis(Rotate.Y_AXIS);
        rotation.setNode(box);
        rotation.setByAngle(320);
        rotation.setCycleCount(30);
        rotation.setAutoReverse(true);

        PerspectiveCamera camera = new PerspectiveCamera();
        camera.setTranslateX(0);
        camera.setTranslateY(0);
        camera.setTranslateZ(0);

        box.setMaterial(material);

        EventHandler<MouseEvent> handler = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                circle.setFill(Color.LIMEGREEN);
            }
        };

        box.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                rotation.play();
                //rotation.stop();
            }
        });

        circle.addEventFilter(MouseEvent.MOUSE_CLICKED, handler);
        circle.removeEventFilter(MouseEvent.MOUSE_CLICKED, handler);

        Text text = new Text("Click on the circle to change its color");
        text.setFill(Color.GREEN);
        text.setX(150);
        text.setY(200);
        text.setFont(Font.font(null, FontWeight.BOLD, 15));

        Group group = new Group(box, circle, text);


        Scene scene = new Scene(group, 600, 400);
        scene.setCamera(camera);
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
