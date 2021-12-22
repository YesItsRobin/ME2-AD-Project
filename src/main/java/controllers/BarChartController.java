package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.stage.Stage;


public class BarChartController implements Initializable{
    private Stage stage;
    private Scene scene;
    @FXML
    private BarChart<String, Double> OnderkantLigger;

    @FXML
    private CategoryAxis x;

    @FXML
    private NumberAxis y;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        XYChart.Series<String, Double> set1 = new XYChart.Series();
        set1.getData().add(new XYChart.Data("Strain #10201", -461,74));
        set1.getData().add(new XYChart.Data("Strain #10202", -778.13));
        set1.getData().add(new XYChart.Data("Strain #10203", -373,17));
        set1.getData().add(new XYChart.Data("Strain #10204", -1094,65));
        set1.getData().add(new XYChart.Data("Strain #10205", -822.85));
        set1.getData().add(new XYChart.Data("Strain #10206", -670.28));

        OnderkantLigger.getData().addAll(set1);
    }

    @FXML
    private MenuButton strainGroupMenu;


    @FXML
    private Button homeButton;

    @FXML
    void setStrain(ActionEvent event) {
        //TODO
    }

    @FXML
    void showHomepage(ActionEvent event)  throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("Homescreen.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}

