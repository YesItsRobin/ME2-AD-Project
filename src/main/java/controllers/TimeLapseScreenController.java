package controllers;

import java.io.IOException;
import java.util.ArrayList;
import dataLayer.ReadCompactStrain;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import models.CompactStrain;
import models.Influences;
import models.SimRegression;

public class TimeLapseScreenController extends BaseController {
    @FXML public CheckBox group1;
    @FXML public CheckBox group2;
    @FXML public CheckBox group3;
    @FXML public CheckBox group4;
    @FXML public CheckBox group5;
    @FXML public CheckBox group6;
    @FXML public CheckBox group7;
    @FXML public CheckBox group8;
    @FXML public CheckBox rainfall;
    @FXML public CheckBox temperature;
    @FXML public CheckBox windspeed;
    @FXML public Button SelectAll;
    @FXML public Button ClearAll;
    @FXML public NumberAxis xAxis;
    @FXML public NumberAxis yAxis;
    @FXML private AnchorPane TimelapseChart;
    @FXML private LineChart<Number, Number> timelapseChart;
    @FXML private MenuButton strainGroupMenu;
    @FXML private Button homeButton;
    private int maxAge=5000;

    private Stage stage;
    private Scene scene;
    private Parent root;
    private Stage dialogStage;


    public void selectAll(ActionEvent actionEvent){
        group1.setSelected(true);
        group2.setSelected(true);
        group3.setSelected(true);
        group4.setSelected(true);
        group5.setSelected(true);
        group6.setSelected(true);
        group7.setSelected(true);
        group8.setSelected(true);
    }


    public void clearAll(ActionEvent actionEvent){
        group1.setSelected(false);
        group2.setSelected(false);
        group3.setSelected(false);
        group4.setSelected(false);
        group5.setSelected(false);
        group6.setSelected(false);
        group7.setSelected(false);
        group8.setSelected(false);
    }

    public void initialize(ActionEvent actionEvent){
        xAxis.setAutoRanging(false);
        xAxis.setLowerBound(0);
        xAxis.setUpperBound(maxAge);

        yAxis.setAutoRanging(false);
        yAxis.setLowerBound(-750);
        yAxis.setUpperBound(1000);
        timelapseChart.setTitle("Strain group");
    }

    public void showGraph(int group) {
        // Create new line to go on the chart
        XYChart.Series<Number, Number> series = new XYChart.Series<>();
        series.setName(toString(group));

        try {
            ArrayList<CompactStrain> strain = ReadCompactStrain.getCompactedStrainsGroup(group);
            SimRegression reg = new SimRegression(strain, Influences.age);
            for (int j = 0; j < maxAge; j++) {
                series.getData().add(new XYChart.Data<>(j, reg.getY(j)));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        timelapseChart.getData().add(series);
        }


    private String toString(int group) {
        return Integer.toString(group);
    }

    public void rain(ActionEvent actionEvent) {

    }

    public void wind(ActionEvent actionEvent) {
    }

    public void temp(ActionEvent actionEvent) {
    }
}



