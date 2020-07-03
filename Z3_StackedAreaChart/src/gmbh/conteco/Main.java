package gmbh.conteco;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.StackedAreaChart;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {

        Group root = new Group();
        final NumberAxis xAxis = new NumberAxis(1, 12, 1);

        final NumberAxis yAxis = new NumberAxis();
        final StackedAreaChart<Number,Number> stackedAreaChart = new StackedAreaChart<Number,Number>(xAxis,yAxis);
        final XYChart.Series<Number,Number> genesisSeries = new XYChart.Series<Number,Number>();

        xAxis.setLabel("Month");
        yAxis.setLabel("# of Bikes");

        stackedAreaChart.setTitle("StackedAreaChart");
        genesisSeries.setName("Genesis");

        genesisSeries.getData().add(new XYChart.Data(1, 100));
        genesisSeries.getData().add(new XYChart.Data(2, 200));
        genesisSeries.getData().add(new XYChart.Data(10, 150));
        genesisSeries.getData().add(new XYChart.Data(3, 130));
        genesisSeries.getData().add(new XYChart.Data(11, 230));

        XYChart.Series<Number,Number> cubeSeries = new XYChart.Series();
        cubeSeries.setName("Cube");

        cubeSeries.getData().add(new XYChart.Data(1, 50));
        cubeSeries.getData().add(new XYChart.Data(2, 200));
        cubeSeries.getData().add(new XYChart.Data(10, 260));
        cubeSeries.getData().add(new XYChart.Data(11, 280));

        stackedAreaChart.getData().addAll(genesisSeries, cubeSeries);
        stackedAreaChart.setTitle("Sales Figures");

        root.getChildren().addAll(stackedAreaChart);

        primaryStage.setScene(new Scene(root, 500, 400));
        primaryStage.setTitle("Bike Store");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}