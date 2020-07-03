package gmbh.conteco;

import javafx.application.Application;
import javafx.collections.*;
import javafx.scene.Scene;
import javafx.scene.chart.Axis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {
        Pane p = new Pane();

        // Create  series
        ObservableList<XYChart.Series> seriesObservableList = FXCollections.observableArrayList();

        // Create dataset for  males and add it to the series
        ObservableList<XYChart.Data> males = FXCollections.observableArrayList(
                new XYChart.Data(0, 0),
                new XYChart.Data(2, 25),
                new XYChart.Data(4, 37),
                new XYChart.Data(6, 46),
                new XYChart.Data(8, 115)
        );
        seriesObservableList.add(new XYChart.Series("Male", males));

        // Create dataset for  females and add it to the series
        ObservableList<XYChart.Data> females = FXCollections.observableArrayList(
                new XYChart.Data(0, 0),
                new XYChart.Data(2, 43),
                new XYChart.Data(4, 51),
                new XYChart.Data(6, 64),
                new XYChart.Data(8, 92)
        );
        seriesObservableList.add(new XYChart.Series(" Female ", females));

        // Create the axes
        Axis x = new NumberAxis("Books read", 0, 8, 1);
        Axis y = new NumberAxis("Time taken in hours", 0, 150, 10);

        // Add both the axes and the data to the line chart
        LineChart c = new LineChart(x, y, seriesObservableList);

        // Show everything
        p.getChildren().add(c);
        Scene sc = new Scene(p);
        primaryStage.setScene(sc);
        primaryStage.setTitle("Females vs males - # of books read");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}