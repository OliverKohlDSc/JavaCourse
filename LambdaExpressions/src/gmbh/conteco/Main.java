package gmbh.conteco;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

public class Main extends Application {
    // Private Field / Property
    private static final Map<String, BinaryOperator> operators = new LinkedHashMap<>();
    private static TextField textFieldX;
    private static TextField textFieldY;
    private static TextField textFieldResult;

    // Block
    {
        operators.put("Plus", (x, y) -> x + y);
        operators.put("Minus", (x, y) -> x - y);
        operators.put("Times", (x, y) -> x * y);
        operators.put("Div", (x, y) -> x / y);
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        FlowPane pane = new FlowPane();
        Scene scene = new Scene(pane);
        textFieldX = new TextField();
        textFieldY = new TextField();
        textFieldResult = new TextField();

        pane.getChildren().addAll(textFieldX, textFieldY);
        addButtons(pane);
        pane.getChildren().add(textFieldResult);

        primaryStage.setScene(scene);
        primaryStage.setTitle("Maps mit Lambdas");
        primaryStage.show();
    }

    private static void addButtons(FlowPane pane) {
        for (Map.Entry<String, BinaryOperator> entry : operators.entrySet()) {
            Button button = new Button(entry.getKey());
            button.setOnAction(actionEvent -> onCalc(entry.getValue()));
            pane.getChildren().add(button);
        }
    }

    private static void onCalc(BinaryOperator op) {
        int x = Integer.parseInt(textFieldX.getText());
        int y = Integer.parseInt(textFieldY.getText());

        int result = op.apply(x, y);
        textFieldResult.setText(String.valueOf(result));
    }

    public static void main(String[] args) {
        launch(args);
    }
}