package gmbh.conteco;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Series;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Main extends Application {
        @Override
        public void start(Stage primaryStage) {
            CategoryAxis xAxis = new CategoryAxis();
            NumberAxis yAxis = new NumberAxis();
            ScatterChart scatterChart = new ScatterChart(xAxis, yAxis);
            scatterChart.setData(getChartData());
            scatterChart.setTitle("Umsatzsteigerungen");
            primaryStage.setTitle("ScatterChart Beispiel");

            StackPane root = new StackPane();
            root.getChildren().add(scatterChart);
            primaryStage.setScene(new Scene(root, 400, 250));
            primaryStage.show();
        }

        private ObservableList<XYChart.Series<String, Double>> getChartData() {
            double team1Value = 1.06;
            double team2Value = 1.56;
            ObservableList<XYChart.Series<String, Double>> seriesList = FXCollections.observableArrayList();
            Series<String, Double> team1Series = new Series<String, Double>();
            Series<String, Double> team2Series = new Series<String, Double>();
            team1Series.setName("VW Team1");
            team2Series.setName("VW Team2");

            for (int i = 2011; i < 2021; i++) {
                team2Series.getData().add(new XYChart.Data(Integer.toString(i), team2Value));
                team2Value = team2Value + Math.random() - .5;
                team1Series.getData().add(new XYChart.Data(Integer.toString(i), team1Value));
                team1Value = team1Value + Math.random() - .5;
            }
            seriesList.addAll(team2Series, team1Series);
            return seriesList;
        }

        public static void main(String[] args) {
            launch(args);
        }
    }