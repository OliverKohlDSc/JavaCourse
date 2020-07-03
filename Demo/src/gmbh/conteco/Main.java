package gmbh.conteco;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.Map;

public class Main extends Application {
    TextField nameTextField = new TextField();
    Label resultingMeaningLabel = new Label();
    Map<String, Integer> cacheMap = new HashMap<>();
    private Button calculateButton = new Button("Ausrechnen");

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        HBox hBox = new HBox();
        VBox vBox = new VBox();
        Label nameLabel = new Label();

        Font boldItalicFont = Font.font("Verdana", FontWeight.BOLD, FontPosture.ITALIC, 12);

        calculateButton.setOnAction(event -> {
            // Are we dealing with a cached key-value pair - name/number?
            if (!cacheMap.containsKey(nameTextField.getText())) {
                // Calculate the numerological value
                int value = numerologicalValue(nameTextField.getText());

                // Fetch description and display result
                resultingMeaningLabel.setText("Die Zahl " + value + " bedeutet: " + describe(value));

                // Add the name and its value
                cacheMap.put(nameTextField.getText(), value);
            } else {
                // Read the name's number from the cache
                int value = cacheMap.get(nameTextField.getText());

                // Fetch description and display result
                resultingMeaningLabel.setText("Die Zahl " + value + " bedeutet: " + describe(value));
            }
        });

        resultingMeaningLabel.setWrapText(true);
        resultingMeaningLabel.setText("");
        resultingMeaningLabel.setFont(boldItalicFont);
        resultingMeaningLabel.setPadding(new Insets(10, 10, 10, 10));
        resultingMeaningLabel.setTextAlignment(TextAlignment.JUSTIFY);

        nameLabel.setFont(boldItalicFont);
        nameLabel.setText("Vollständiger Name");

        hBox.getChildren().addAll(nameLabel, nameTextField, calculateButton);
        hBox.setSpacing(12);
        hBox.setPadding(new Insets(10, 10, 10, 10));

        vBox.getChildren().addAll(hBox, resultingMeaningLabel);

        primaryStage.setScene(new Scene(vBox, 420, 300));
        primaryStage.setTitle("Mein Name in Zahlen");
        primaryStage.show();
    }

    /**
     * Step 1: Convert letter A-Z to numeric value; all other characters receive
     * a value of zero.
     */
    private int letterValue(char letter) {
        int value = 0;
        if (letter >= 'A' && letter <= 'Z') {
            value = (letter - 'A' /*65*/) % 9 + 1;
        }
        return value;
    }

    /**
     * Step 2: Process the letters A-Z. We convert letters a-z to upper case at
     * this point. We return an int[] holding all of the values
     */
    private int[] letterValues(char[] letters) {
        int[] values = new int[letters.length];
        for (int i = 0; i < letters.length; i++) {
            char c = letters[i];
            c = Character.toUpperCase(c);
            values[i] = letterValue(c);
        }
        return values;
    }

    /**
     * Step 3: Sum the digits of an integer
     */
    private int sumOfDigits(int in) {
        int sum = 0;
        while (in > 0) {
            sum += in % 10;
            in = in / 10;
        }
        return sum;
    }

    /**
     * Step 4: Calculate numerological value of a string
     */
    private int numerologicalValue(String name) {
        char[] chars = name.toCharArray();
        int[] values = letterValues(chars);

        // Sum the values
        int sum = 0;
        for (int value : values)
            sum += value;

        // Reduce to one of the final values
        while (sum > 23 || (sum > 11 && sum < 22) || sum == 10) {
            sum = sumOfDigits(sum);
        }

        return sum;
    }

    /**
     * Step 5: Convert values into silly descriptions
     */
    private String describe(int sum) {
        String desc;
        switch (sum) {
            case 1:
                desc = "";
                break;
            case 2:
                desc = "";
                break;
            case 3:
                desc = "";
                break;
            case 4:
                desc = "";
                break;
            case 5:
                desc = "";
                break;
            case 6:
                desc = "";
                break;
            case 7:
                desc = "";
                break;
            case 8:
                desc = "";
                break;
            case 9:
                desc = "";
                break;
            default:
                desc = "Ich weis nichts über dich!";
        }
        return desc;
    }
}