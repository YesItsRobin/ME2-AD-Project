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

    private Stage stage;
    private Scene scene;
    private Parent root;
    private Stage dialogStage;


    public void group1(ActionEvent actionEvent) {
        showGraph(1);
        if(!group1.isSelected()){
            clearGraph();
        }
    }

    public void group2(ActionEvent actionEvent) {
        showGraph(2);
        if (!group2.isSelected()) {
            clearGraph();
        }
    }

    public void group3(ActionEvent actionEvent) {
        showGraph(3);
        if (!group3.isSelected()) {
            clearGraph();
        }
    }

    public void group4(ActionEvent actionEvent) {
        showGraph(4);
        if (!group4.isSelected()) {
            clearGraph();
        }
    }

    public void group5(ActionEvent actionEvent) {
        showGraph(5);
        if (!group5.isSelected()) {
            clearGraph();
        }
    }
    public void group6(ActionEvent actionEvent) {
        showGraph(6);
        if (!group6.isSelected()) {
            clearGraph();
        }
    }

    public void group7(ActionEvent actionEvent) {
        showGraph(7);
        if (!group7.isSelected()) {
            clearGraph();
        }
    }

    public void group8(ActionEvent actionEvent) {
        showGraph(8);
        if (!group8.isSelected()) {
            clearGraph();
        }
    }

    public void selectAll(ActionEvent actionEvent){
        if (!group1.isSelected()) {
            showGraph(1);
            group1.setSelected(true);
            }
        if (!group2.isSelected()) {
            showGraph(2);
            group2.setSelected(true);
            }
        if (!group3.isSelected()) {
            showGraph(3);
            group3.setSelected(true);
        }
        if (!group4.isSelected()) {
            showGraph(4);
            group4.setSelected(true);
        }
        if (!group5.isSelected()) {
            showGraph(5);
            group5.setSelected(true);
        }
        if (!group6.isSelected()) {
            showGraph(6);
            group6.setSelected(true);
        }
        if (!group7.isSelected()) {
            showGraph(7);
            group7.setSelected(true);
        }
        if (!group8.isSelected()) {
            showGraph(8);
            group8.setSelected(true);
        }

    }


    public void clearAll(ActionEvent actionEvent){
        clearGraph();
    }

    public void showGraph(int group) {
        // Create new line to go on the chart
        int maxAge = 600;
        xAxis.setAutoRanging(false);
        xAxis.setLowerBound(0);
        xAxis.setUpperBound(maxAge);

        yAxis.setAutoRanging(false);
        yAxis.setLowerBound(-750);
        yAxis.setUpperBound(1000);
        timelapseChart.setTitle("Strain group");

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

    public void clearGraph() {
            timelapseChart.getData().clear();
            group1.setSelected(false);
            group2.setSelected(false);
            group3.setSelected(false);
            group4.setSelected(false);
            group5.setSelected(false);
            group6.setSelected(false);
            group7.setSelected(false);
            group8.setSelected(false);
        }
}



