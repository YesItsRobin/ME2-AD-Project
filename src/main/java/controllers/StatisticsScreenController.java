package controllers;

import dataLayer.ReadStrain;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Slider;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import models.Strain;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.ResourceBundle;

public  class StatisticsScreenController extends BaseController implements Initializable {
    public Boolean rainfall= false;
    public Boolean temp= false;
    public Boolean wind= false;
    public int bridgeAge;


    public LineChart<Float, Float> statGraph;

    public CategoryAxis XLabel;
    public Text ageLabel;
    public Slider ageSlider;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        XYChart.Series set = new XYChart.Series();
        LocalDateTime from = LocalDateTime.of(LocalDate.of(2019+getBridgeAge(),1,1), LocalTime.of(0,0,0));
        LocalDateTime to = LocalDateTime.of(LocalDate.of(2020+getBridgeAge(),1,1), LocalTime.of(0,0,0));

        try {
            addStrains((ReadStrain.strainsFromTimeframe(from,to)),set);
        } catch (IOException e) {
            e.printStackTrace();
        }




        set1.getData().add(new XYChart.Data("Strain #10201", -461,74));
        set1.getData().add(new XYChart.Data("Strain #10202", -778.13));
        set1.getData().add(new XYChart.Data("Strain #10203", -373,17));
        set1.getData().add(new XYChart.Data("Strain #10204", -1094,65));
        set1.getData().add(new XYChart.Data("Strain #10205", -822.85));
        set1.getData().add(new XYChart.Data("Strain #10206", -670.28));

        statGraph.getData().addAll(set1);
    }

    public void addStrains(List<Strain> strainList, XYChart.Series set){
        for (Strain strain: list){
            set.getData().add(new XYChart.Data(, -461,74));
        }
    }

    public void changeXLabel(){
        StringBuilder text = new StringBuilder();
        if (getRainfall()){
            text.append("Rainfall ");
        }
        if (getWind()){
            text.append("Wind ");
        }
        if (getTemp()){
            text.append("Temperature");
        }
        XLabel.setLabel(text.toString());
    }

    public void changeAgeLabel(){
        setBridgeAge((int)(ageSlider.getValue()));
        ageLabel.setText(String.valueOf(getBridgeAge()));
    }

    public void CheckRainfall(ActionEvent actionEvent) {
        setRainfall(!getRainfall());
        changeXLabel();
    }

    public void CheckTemp(ActionEvent actionEvent) {
        setTemp(!getTemp());
        changeXLabel();
    }

    public void CheckWind(ActionEvent actionEvent) {
        setWind(!getWind());
        changeXLabel();
    }

    public Boolean getRainfall() {
        return rainfall;
    }

    public void setRainfall(Boolean rainfall) {
        this.rainfall = rainfall;
    }

    public Boolean getTemp() {
        return temp;
    }

    public void setTemp(Boolean temp) {
        this.temp = temp;
    }

    public Boolean getWind() {
        return wind;
    }

    public void setWind(Boolean wind) {
        this.wind = wind;
    }

    public int getBridgeAge() {
        return bridgeAge;
    }

    public void setBridgeAge(int bridgeAge) {
        this.bridgeAge = bridgeAge;
    }
}
