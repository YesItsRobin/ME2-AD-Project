package controllers;

import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Array;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import dataLayer.ReadCompactStrain;
import dataLayer.ReadStrain;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import models.CompactStrain;
import models.Influences;
import models.SimRegression;
import models.Strain;
import org.apache.commons.math3.stat.regression.SimpleRegression;

public class TimeLapseScreenController extends BaseController implements Initializable{
    public MenuItem group1;
    public MenuItem group2;
    public MenuItem group3;
    public MenuItem group4;
    public MenuItem group5;
    public MenuItem group6;
    public MenuItem group7;
    public MenuItem group8;
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
    private LineChart timelapseChart;

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
                NumberAxis xAxis = new NumberAxis();
                NumberAxis yAxis = new NumberAxis();

                XYChart.Series<String, Double> series = new XYChart.Series<>();
                series.setName("Strain - Group 1");

            try {
                ArrayList<CompactStrain> strains = ReadCompactStrain.getStrains(1, 1);
                SimRegression reg = new SimRegression(strains, Influences.age);
                for (int i = 0; i < strains.toArray().length; i++) {
                    series.getData().add(new XYChart.Data<>(String.valueOf(i), reg.getY(i)));
                }
            } catch (IOException ignored) {
            }


            // Add series to LineChart
            timelapseChart = new LineChart(xAxis,yAxis);
            timelapseChart.getData().add(series);


    }


}

