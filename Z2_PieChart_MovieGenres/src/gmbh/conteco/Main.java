package gmbh.conteco;

import javafx.application.Application;
import javafx.geometry.Side;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.chart.PieChart;

public class Main extends Application {
    @Override
    public void start(Stage s) {
        PieChart pieChart = new PieChart();

        // add five slices ('s') to our chart
        PieChart.Data s1 = new PieChart.Data("Comedy", 20);
        PieChart.Data s2 = new PieChart.Data("Thriller", 40);
        PieChart.Data s3 = new PieChart.Data("Horror", 20);
        PieChart.Data s4 = new PieChart.Data("SciFi", 15);
        PieChart.Data s5 = new PieChart.Data("Action", 5);

        pieChart.getData().addAll(s1,s2,s3,s4,s5);

        // location to display the legend
        pieChart.setLegendSide(Side.RIGHT);

        // set the title of the chart
        pieChart.setTitle("MOVIE GENRES");

        // direction of the created pie chart
        pieChart.setClockwise(true);

        // length of the label lines in the pie chart
        pieChart.setLabelLineLength(45);

        // set the visibility of labels in the chart
        pieChart.setLabelsVisible(true);

        // start angle that has to be used in chart
        pieChart.setStartAngle(180);

        // Display our chart
        Scene sc = new Scene(new Group(pieChart), 600, 400);
        s.setTitle("Pie chart");
        s.setScene(sc);
        s.show();
    }
    public static void main(String args[]){
        launch(args);
    }
}