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
    private void activate(ActionEvent event) {

    }

    public void Factor(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.initOwner(dialogStage);
        alert.setTitle("Not available");
        alert.setHeaderText("Only strain group 1 available");
        alert.setContentText(" ");

        alert.showAndWait();
    }

    @FXML
    private Button homeButton;

    @FXML
    private MenuButton factorMenu;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Create new line to go on the chart
        int maxAge=600;
        xAxis.setAutoRanging(false);
        xAxis.setLowerBound(0);
        xAxis.setUpperBound(maxAge);

        yAxis.setAutoRanging(false);
        yAxis.setLowerBound(-300);
        yAxis.setUpperBound(100);
        timelapseChart.setTitle("Chart");

        XYChart.Series<Number, Number> series = new XYChart.Series<>();
        series.setName("Strain - Group 1");

        try {
            for (int i = 1; i < 11; i++) {
                ArrayList<CompactStrain> strains = ReadCompactStrain.getStrains(1, i);
                SimRegression reg = new SimRegression(strains, Influences.age);
                for (int j = 0; j < maxAge; j++) {
                    series.getData().add(new XYChart.Data<>(j, reg.getY(j)));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        timelapseChart.getData().add(series);

    }


}

