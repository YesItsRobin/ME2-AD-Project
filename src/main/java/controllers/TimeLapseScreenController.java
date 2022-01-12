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
    XYChart.Series<Number, Number> series = new XYChart.Series<>();
    public NumberAxis xAxis;
    public NumberAxis yAxis;
    public CheckBox rainCheck;
    public CheckBox tempCheck;
    public CheckBox windCheck;
    public CheckBox noneCheck;

    private Stage stage;
    private Scene scene;
    private Parent root;
    private Stage dialogStage;
    public int group = 1;
    private boolean rain = false;
    private boolean wind = false;
    private boolean temp = false;
    private int maxAge;


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
        series.setName(getName());

        try {
            ArrayList<CompactStrain> strains = ReadCompactStrain.getStrains(1, 1);
            SimRegression reg = new SimRegression(strains, Influences.age);
            for (int i = 0; i < maxAge; i++) {
                series.getData().add(new XYChart.Data<>(i, reg.getY(i)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        timelapseChart.getData().add(series);

    }

    public void updateSeries(){
        int maxAge=600;
        xAxis.setAutoRanging(false);
        xAxis.setLowerBound(0);
        xAxis.setUpperBound(maxAge);

        yAxis.setAutoRanging(false);
        yAxis.setLowerBound(-300);
        yAxis.setUpperBound(100);
        timelapseChart.setTitle("Chart");

        XYChart.Series<Number, Number> series = new XYChart.Series<>();
        series.setName(getName());

        try {
            ArrayList<CompactStrain> strains = ReadCompactStrain.getStrains(1, 1);
            SimRegression reg = new SimRegression(strains, Influences.age);
            for (int i = 0; i < maxAge; i++) {
                series.getData().add(new XYChart.Data<>(i, reg.getY(i)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        timelapseChart.getData().add(series);
    }

    private String getName(){
        StringBuilder str = new StringBuilder();
        if (isWind()){
            str.append("wind ");
        }
        if (isRain()){
            str.append("rain ");
        }
        if (isTemp()){
            str.append("temperature ");
        }
        if (getGroup()==999) {
            str.append("All groups.");
        }
        else{
            str.append(getGroup());
        }
        return str.toString();
    }

    public int getGroup() {
        return group;
    }

    public void setGroup(int group) {
        this.group = group;
    }

    public void group1(ActionEvent actionEvent) {
        strainGroupMenu.setText("Straingroup 1");
        setGroup(1);
    }

    public void group2(ActionEvent actionEvent) {
        strainGroupMenu.setText("Straingroup 2");
        setGroup(2);
    }

    public void group3(ActionEvent actionEvent) {
        strainGroupMenu.setText("Straingroup 3");
        setGroup(3);
    }
    public void group4(ActionEvent actionEvent) {
        strainGroupMenu.setText("Straingroup 4");
        setGroup(4);
    }

    public void group5(ActionEvent actionEvent) {
        strainGroupMenu.setText("Straingroup 5");
        setGroup(5);
    }

    public void group6(ActionEvent actionEvent) {
        strainGroupMenu.setText("Straingroup 6");
        setGroup(6);
    }

    public void group7(ActionEvent actionEvent) {
        strainGroupMenu.setText("Straingroup 7");
        setGroup(7);
    }

    public void group8(ActionEvent actionEvent) {
        strainGroupMenu.setText("Straingroup 8");
        setGroup(8);
    }

    public void allGroups(ActionEvent actionEvent) {
        strainGroupMenu.setText("All groups");
        setGroup(999);
    }

    public void switchRain(ActionEvent actionEvent) {
        setRain(!isRain());
        noneCheck.setSelected(false);
    }

    public void switchWind(ActionEvent actionEvent) {
        setWind(!isWind());
        noneCheck.setSelected(false);
    }

    public void switchTemp(ActionEvent actionEvent) {
        setWind(!isWind());
        noneCheck.setSelected(false);
    }

    public void switchNone(ActionEvent actionEvent) {
        setWind(false);
        setRain(false);
        setTemp(false);

        windCheck.setSelected(false);
        rainCheck.setSelected(false);
        tempCheck.setSelected(false);
    }

    public boolean isRain() {
        return rain;
    }

    public void setRain(boolean rain) {
        this.rain = rain;
    }

    public boolean isWind() {
        return wind;
    }

    public void setWind(boolean wind) {
        this.wind = wind;
    }

    public boolean isTemp() {
        return temp;
    }

    public void setTemp(boolean temp) {
        this.temp = temp;
    }

    public void Redraw(ActionEvent actionEvent) {
        timelapseChart.getData().remove(0);
        updateSeries();
    }

}


