package gmbh.conteco;

import javafx.application.Application;
import javafx.geometry.Side;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {		
		VBox vbox = new VBox(createBarChart());
		Scene scene = new Scene(vbox, 400, 200);
		primaryStage.setScene(scene);
		primaryStage.setTitle("BarChart demo");
		primaryStage.show();
		
		StackPane piePane = new StackPane(createPieChart());
		Scene pieScene = new Scene (piePane, 400, 400);
		Stage pieStage = new Stage();
		pieStage.setScene(pieScene);
		pieStage.setTitle("PieChart demo");
		pieStage.show();
		
		Scene combyScene = new Scene (createCombinationOfCharts(), 400, 300);
		Stage combyStage = new Stage();
		combyStage.setScene(combyScene);
		combyStage.setTitle("Combining two charts");
		combyStage.show();
	}
	
	private Parent createCombinationOfCharts() {
		StackPane root = new StackPane();
		
		CategoryAxis xAxis = new CategoryAxis();
		xAxis.setLabel("Country");
		
		NumberAxis yAxis = new NumberAxis();
		yAxis.setLabel("Population");
		
		// Bar chart
		BarChart<String, Number> barChart = new BarChart<>(xAxis, yAxis);
		barChart.setLegendVisible(false);
		barChart.setAnimated(false);

		// Data series #1
		XYChart.Series<String, Number> dataSeries1 = new XYChart.Series<>();
		dataSeries1.getData().add(new XYChart.Data<>("Austria", 25601.34));
		dataSeries1.getData().add(new XYChart.Data<>("Brazil", 20148.82));
		dataSeries1.getData().add(new XYChart.Data<>("France", 10000.00));
		dataSeries1.getData().add(new XYChart.Data<>("Germany", 35407.15));
		dataSeries1.getData().add(new XYChart.Data<>("USA", 12000));
		barChart.getData().add(dataSeries1);
		
		// Line Chart -> Create the overlay - our second chart
		LineChart<String, Number> lineChart = new LineChart<>(xAxis, yAxis);
		lineChart.setLegendVisible(false);
		lineChart.setAnimated(false);
		lineChart.getXAxis().setVisible(false);
		lineChart.getYAxis().setVisible(false);
		lineChart.setAlternativeColumnFillVisible(false);
		lineChart.setAlternativeRowFillVisible(false);
		lineChart.setHorizontalGridLinesVisible(false);
		lineChart.setVerticalGridLinesVisible(false);
		// This should be solved by using resources instead
		//lineChart.getStylesheets().add("CombineCharts.css"); 
		lineChart.getStylesheets().addAll(getClass().getResource("CombineCharts.css").toExternalForm());
		
		// Data series #2
		int delta = 1000;
		XYChart.Series<String, Number> dataSeries2 = new XYChart.Series<>();
		dataSeries2.getData().add(new XYChart.Data<>("Austria", 25601.34 + delta));
		dataSeries2.getData().add(new XYChart.Data<>("Brazil", 20148.82 + delta));
		dataSeries2.getData().add(new XYChart.Data<>("France", 10000.00 + delta));
		dataSeries2.getData().add(new XYChart.Data<>("Germany", 35407.15 + delta));
		dataSeries2.getData().add(new XYChart.Data<>("USA", 12000 + delta));
		lineChart.getData().add(dataSeries2);
		
		root.getChildren().addAll(barChart, lineChart);
		
		return root;
	}

	private Node createPieChart() {
		PieChart pieChart = new PieChart();
		
		PieChart.Data slice1 = new PieChart.Data("Germany", 3.948);
		PieChart.Data slice2 = new PieChart.Data("France", 2.778);
		PieChart.Data slice3 = new PieChart.Data("Italy", 2.084);
		PieChart.Data slice4 = new PieChart.Data("Austria", 0.455);
		
		pieChart.getData().add(slice1);
		pieChart.getData().add(slice2);
		pieChart.getData().add(slice3);
		pieChart.getData().add(slice4);
		
		pieChart.setTitle("2018: GDP by Country");
		pieChart.setLegendSide(Side.LEFT);
		
		return pieChart;
	}
	
	private Node createBarChart() {
		CategoryAxis xAxis = new CategoryAxis();
		xAxis.setLabel("Programming Language");
		
		NumberAxis yAxis = new NumberAxis();
		yAxis.setLabel("Percent");
		
		BarChart<String, Number> barChart = new BarChart<>(xAxis, yAxis);
		
		XYChart.Series<String, Number> dataSeries1 = new XYChart.Series<>();
		XYChart.Series<String, Number> dataSeries2 = new XYChart.Series<>();
		XYChart.Series<String, Number> dataSeries3 = new XYChart.Series<>();
		
		dataSeries1.setName("2020");
		dataSeries1.getData().add(new XYChart.Data<String, Number>("Java", 17.78));
		dataSeries1.getData().add(new XYChart.Data<String, Number>("C", 16.33));
		dataSeries1.getData().add(new XYChart.Data<String, Number>("Pyhton", 10.11));
		
		dataSeries2.setName("2015");
		dataSeries2.getData().add(new XYChart.Data<String, Number>("Java", 15.58));
		dataSeries2.getData().add(new XYChart.Data<String, Number>("C", 16.64));
		dataSeries2.getData().add(new XYChart.Data<String, Number>("Pyhton", 2.61));
		
		dataSeries3.setName("2010");
		dataSeries3.getData().add(new XYChart.Data<String, Number>("Java", 17.51));
		dataSeries3.getData().add(new XYChart.Data<String, Number>("C", 17.28));
		dataSeries3.getData().add(new XYChart.Data<String, Number>("Pyhton", 4.23));
		
		barChart.getData().add(dataSeries1);
		barChart.getData().add(dataSeries2);
		barChart.getData().add(dataSeries3);
		
		barChart.setTitle("History of programming language");
		
		return barChart;
	}
}