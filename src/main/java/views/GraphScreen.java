package views;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

import java.util.List;


public class GraphScreen {

    private static final int MAX_X = 50; // x-axis max value
    private static final int MAX_Y = 60; // y-axis max value

    public static Stage getGraphScreen() {
        Stage primaryStage = new Stage();
        List<LineChart.Data<Number, Number>> data;
        data = List.of(
                new LineChart.Data<>(10, 15),
                new LineChart.Data<>(15, 20),
                new LineChart.Data<>(20, 30),
                new LineChart.Data<>(25, 2));

        var xAxis = new NumberAxis("x", 0.0D, 50.0D, 10.0D);
        var yAxis = new NumberAxis("y", 0.0D, 60.0D, 10.0D);

        var series = new XYChart.Series<>(FXCollections.observableList(data)); // series in data
        var lineChart = new LineChart(xAxis, yAxis, FXCollections.singletonObservableList(series)); // creates chart
        primaryStage.setTitle("My Chart");
        Scene scene = new Scene(lineChart, 600.0D, 600.0D); // Create JavaFX windows
        primaryStage.setScene(scene);
        return primaryStage;
    }
}