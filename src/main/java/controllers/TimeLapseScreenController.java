package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import dataLayer.ReadCompactStrain;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Slider;
import javafx.stage.Stage;
import models.CompactStrain;
import models.Influences;
import models.MulRegression;
import models.SimRegression;

public class TimeLapseScreenController extends BaseController implements Initializable {
    @FXML
    public CheckBox group1;
    @FXML
    public CheckBox group2;
    @FXML
    public CheckBox group3;
    @FXML
    public CheckBox group4;
    @FXML
    public CheckBox group5;
    @FXML
    public CheckBox group6;
    @FXML
    public CheckBox group7;
    @FXML
    public CheckBox group8;
    @FXML
    public CheckBox temperature;
    @FXML
    public CheckBox windspeed;
    @FXML
    public CheckBox atmosphericPressure;
    @FXML
    public CheckBox humidity;
    @FXML
    public CheckBox sunshine;
    @FXML
    public Button SelectAll;
    @FXML
    public Button ClearAll;
    @FXML
    public NumberAxis xAxis;
    @FXML
    public NumberAxis yAxis;
    @FXML
    public CheckBox allGroups;
    @FXML
    public Slider climateSlider;

    @FXML
    private LineChart<Number, Number> timelapseChart;
    private int maxAge = 5000;
    private int climate;

    private Stage stage;
    private Scene scene;
    private Parent root;


    /**
     * This checkbox will check all checkboxes when the button 'select all is clicked'
     *
     * @param actionEvent
     */
    public void selectAll(ActionEvent actionEvent) {
        group1.setSelected(true);
        group2.setSelected(true);
        group3.setSelected(true);
        group4.setSelected(true);
        group5.setSelected(true);
        group6.setSelected(true);
        group7.setSelected(true);
        group8.setSelected(true);
        allGroups.setSelected(true);
    }

    /**
     * This method is used when the button 'clear all' is clicked, all checkboxes will be empty
     *
     * @param actionEvent
     */
    public void clearAll(ActionEvent actionEvent) {
        group1.setSelected(false);
        group2.setSelected(false);
        group3.setSelected(false);
        group4.setSelected(false);
        group5.setSelected(false);
        group6.setSelected(false);
        group7.setSelected(false);
        group8.setSelected(false);
        allGroups.setSelected(false);
    }

    /**
     * This method returns an arraylist of all groups that are selected and thus need to be drawn
     *
     * @return ArrayList
     */
    public ArrayList<String> getGroups() {
        ArrayList<String> groups = new ArrayList<String>();
        if (group1.isSelected()) {
            groups.add("1");
        }
        if (group2.isSelected()) {
            groups.add("2");
        }
        if (group3.isSelected()) {
            groups.add("3");
        }
        if (group4.isSelected()) {
            groups.add("4");
        }
        if (group5.isSelected()) {
            groups.add("5");
        }
        if (group6.isSelected()) {
            groups.add("6");
        }
        if (group7.isSelected()) {
            groups.add("7");
        }
        if (group8.isSelected()) {
            groups.add("8");
        }
        if (allGroups.isSelected()) {
            groups.add("allGroups");
        }
        return groups;
    }

    @Override
    /**
     * Invoked automatically in the background and can perform any code
     * found within, after the root element has already been added
     */
    public void initialize(URL url, ResourceBundle resources) {
        climate = (int) climateSlider.getValue();
        climateSlider.valueProperty().addListener(new ChangeListener<Number>() {

            public void changed(ObservableValue<? extends Number> observable, Number oldNumber, Number newNumber) {
                climate = (int) climateSlider.getValue();
            }
        });
    }


    /**
     * This method is used when the button 'draw' is clicked, it will draw the selected strain group(s),
     * and takes the chosen weather factor in consideration
     *
     * @throws IOException
     */
    public void draw() throws IOException {
        try {
            timelapseChart.getData().clear();
        } catch (Exception e) {
            e.printStackTrace();
        }

        for (String group : getGroups()) {

            // Create new line to go on the chart
            XYChart.Series<Number, Number> series = new XYChart.Series<>();
            series.setName(group);
            ArrayList<CompactStrain> strains = new ArrayList<>();

            if (!Objects.equals(group, "allGroups")) {
                strains.addAll(ReadCompactStrain.getCompactedStrainsGroupWMeteo(Integer.parseInt(group)));
            } else {
                for (int i = 1; i <= 8; i++) {
                    strains.addAll(ReadCompactStrain.getCompactedStrainsGroup(i));
                }
            }
            SimRegression ageReg = new SimRegression(strains, Influences.age);
            System.out.println(strains.size());
            if (windspeed.isSelected() || temperature.isSelected() || atmosphericPressure.isSelected()
                    || sunshine.isSelected() || sunshine.isSelected()) {
                System.out.println(climate);

                if (!Objects.equals(group, "allGroups")) {
                    strains.addAll(ReadCompactStrain.getCompactedStrainsGroupWMeteo(Integer.parseInt(group)));
                } else {
                    for (int i = 1; i <= 8; i++) {
                        strains.addAll(ReadCompactStrain.getCompactedStrainsGroupWMeteo(i));
                    }

                }

                MulRegression reg = new MulRegression(strains, windspeed.isSelected(), temperature.isSelected(),
                        atmosphericPressure.isSelected(), sunshine.isSelected(), humidity.isSelected());

                SimRegression windReg = new SimRegression(Influences.windSpeed, maxAge, climate);
                SimRegression tempReg = new SimRegression(Influences.temp, maxAge, climate);
                SimRegression atmosPresReg = new SimRegression(Influences.atmosPres, maxAge, climate);
                SimRegression sunReg = new SimRegression(Influences.sun, maxAge, climate);
                SimRegression humidityReg = new SimRegression(Influences.sun, maxAge, climate);

                for (int i = 1; i <= maxAge; i++) {
                    ArrayList<Double> x = new ArrayList<>();

                    x.add(ageReg.getY(i));

                    if (windspeed.isSelected()) {
                        x.add(windReg.getY(i));
                    }
                    if (temperature.isSelected()) {
                        x.add(tempReg.getY(i));

                    }
                    if (atmosphericPressure.isSelected()) {
                        x.add(atmosPresReg.getY(i));
                    }
                    if (sunshine.isSelected()) {
                        x.add(sunReg.getY(i));
                    }
                    if (humidity.isSelected()) {
                        x.add(humidityReg.getY(i));
                    }
                    series.getData().add(new XYChart.Data<>(i, reg.getY(x)));
                }
            } else {
                for (int i = 0; i < maxAge; i++) {
                    series.getData().add(new XYChart.Data<>(i, ageReg.getY(i)));
                }
            }


            timelapseChart.getData().add(series); // Adds the data of the strain group(s) to the chart
            xAxis.setAutoRanging(false);
            xAxis.setLowerBound(0);
            xAxis.setUpperBound(600);

            yAxis.setAutoRanging(true);
            yAxis.setLowerBound(-1000);
            yAxis.setUpperBound(-1000);
        }

    }

}

