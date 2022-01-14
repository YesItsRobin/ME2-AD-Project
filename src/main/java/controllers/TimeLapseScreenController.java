package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import dataLayer.ReadCompactStrain;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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

public class TimeLapseScreenController extends BaseController implements Initializable{
    public MenuItem group1;
    public MenuItem group2;
    public MenuItem group3;
    public MenuItem group4;
    public MenuItem group5;
    public MenuItem group6;
    public MenuItem group7;
    public MenuItem group8;
    public NumberAxis xAxis;
    public NumberAxis yAxis;
    private Stage stage;
    private Scene scene;
    private Parent root;
    private Stage dialogStage;

    public void group1(ActionEvent actionEvent) {
        showGraph(1);
    }

    public void group2(ActionEvent actionEvent) {
        showGraph(2);
    }

    public void group3(ActionEvent actionEvent) {
        showGraph(3);
    }
    public void group4(ActionEvent actionEvent) {
        showGraph(4);
    }

    public void group5(ActionEvent actionEvent) {
        showGraph(5);
    }

    public void group6(ActionEvent actionEvent) {
        showGraph(6);
    }

    public void group7(ActionEvent actionEvent) {
        showGraph(7);
    }

    public void group8(ActionEvent actionEvent) {
        showGraph(8);
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }
    @FXML
    private AnchorPane TimelapseChart;

    @FXML
    private LineChart<Number,Number> timelapseChart;

    @FXML
    private MenuButton strainGroupMenu;

    @FXML
    private Button homeButton;

    @FXML
    private MenuButton factorMenu;

    public void showGraph(int group){
        // Create new line to go on the chart
        int maxAge = 600;
        xAxis.setAutoRanging(false);
        xAxis.setLowerBound(0);
        xAxis.setUpperBound(maxAge);

        yAxis.setAutoRanging(true);
        timelapseChart.setTitle("Chart");

        XYChart.Series<Number, Number> series = new XYChart.Series<>();
        series.setName("Strain - Group " + group);

        try {

            ArrayList<CompactStrain> strains = ReadCompactStrain.getCompactedStrainsGroup(group);
            SimRegression reg = new SimRegression(strains, Influences.age);
            for (int j = 0; j < maxAge; j++) {
                series.getData().add(new XYChart.Data<>(j, reg.getY(j)));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        timelapseChart.getData().add(series);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }
}


