package controllers;

import dataLayer.ReadStrain;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Slider;
import javafx.scene.text.Text;
import models.Strain;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

public  class StatisticsScreenController extends BaseController implements Initializable {
    public String influence = null;
    public int bridgeAge;

    public LineChart<Float, Float> statGraph;
    public CategoryAxis XLabel;
    public Text ageLabel;
    public Slider ageSlider;
    public ChoiceBox influenceBox;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        influenceBox.getItems().addAll("Rainfall", "Temperature", "Wind");
    }

    public void ChangeInfluence(ActionEvent mouseEvent) {
        setInfluence((String) influenceBox.getValue());
        XLabel.setLabel(getInfluence());

        XYChart.Series set = new XYChart.Series();
        LocalDateTime from = LocalDateTime.of(LocalDate.of(2019+getBridgeAge(),1,1), LocalTime.of(0,0,0));
        LocalDateTime to = LocalDateTime.of(LocalDate.of(2020+getBridgeAge(),1,1), LocalTime.of(0,0,0));

        try {
            if (Objects.equals(getInfluence(), "Rainfall")){
                for (Strain strain: ReadStrain.strainsFromTimeframe(from,to)){
                    set.getData().add(new XYChart.Data(21, strain.getWaarde()));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


        statGraph.getData().addAll(set);
    }

    public void changeAgeLabel(){
        setBridgeAge((int)(ageSlider.getValue()));
        ageLabel.setText(String.valueOf(getBridgeAge()));
    }


    public String getInfluence() {
        return influence;
    }

    public void setInfluence(String influence) {
        this.influence = influence;
    }

    public int getBridgeAge() {
        return bridgeAge;
    }

    public void setBridgeAge(int bridgeAge) {
        this.bridgeAge = bridgeAge;
    }


}
