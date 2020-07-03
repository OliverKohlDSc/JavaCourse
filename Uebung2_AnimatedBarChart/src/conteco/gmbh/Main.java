package conteco.gmbh;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Main extends Application {

    final static String ITEM_A = "A";
    final static String ITEM_B = "B";
    final static String ITEM_C = "F";
    @Override
    public void start(Stage stage) {
        final NumberAxis xAxis = new NumberAxis();
        final CategoryAxis yAxis = new CategoryAxis();
        final BarChart<Number, String> bc = new BarChart<>(xAxis, yAxis);
        bc.setTitle("Summary");
        xAxis.setLabel("Value");
        xAxis.setTickLabelRotation(90);
        yAxis.setLabel("Item");

        XYChart.Series series1 = new XYChart.Series();
        series1.setName("2003");
        series1.getData().add(new XYChart.Data(2, ITEM_A));
        series1.getData().add(new XYChart.Data(20, ITEM_B));
        series1.getData().add(new XYChart.Data(10, ITEM_C));

        XYChart.Series series2 = new XYChart.Series();
        series2.setName("2004");
        series2.getData().add(new XYChart.Data(50, ITEM_A));
        series2.getData().add(new XYChart.Data(41, ITEM_B));
        series2.getData().add(new XYChart.Data(45, ITEM_C));

        XYChart.Series series3 = new XYChart.Series();
        series3.setName("2005");
        series3.getData().add(new XYChart.Data(45, ITEM_A));
        series3.getData().add(new XYChart.Data(44, ITEM_B));
        series3.getData().add(new XYChart.Data(18, ITEM_C));

        // Let's create our timeline
        Timeline tl = new Timeline();
        tl.getKeyFrames().add(new KeyFrame(Duration.millis(500),
                new EventHandler<ActionEvent>() {
                    @Override public void handle(ActionEvent actionEvent) {
                        for (XYChart.Series<Number, String> series : bc.getData()) {
                            for (XYChart.Data<Number, String> data : series.getData()) {
                                data.setXValue(Math.random() * 100);
                            }
                        }
                    }
                }));
        tl.setCycleCount(Animation.INDEFINITE);
        tl.play();

        Scene scene = new Scene(bc, 800, 600);
        bc.getData().addAll(series1, series2, series3);
        stage.setScene(scene);
        stage.setTitle("Working with a timeline & a bar chart");
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}