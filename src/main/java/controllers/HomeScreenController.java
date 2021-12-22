package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;

public class HomeScreenController {
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private ImageView bar_graph_image;

    @FXML
    private ImageView line_graph_image;

    @FXML
    private ImageView bridge_image;

    @FXML
    private ImageView info_image;

    @FXML
    private Button statistics_button;

    @FXML
    private Button timelapse_button;

    @FXML
    private Button bridge_sensors_button;

    @FXML
    private Button info_button;

    @FXML
    void bridgePressed(ActionEvent event) {

    }

    @FXML
    void infoPressed(ActionEvent event) {

    }

    /* Function that is called when the statistics button is pressed.
    when clicked it will send to the page where you can pick a strain and show the bar graph*/
    @FXML
    void statisticsPressed(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("BarChart.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void timelapsePressed(ActionEvent event) throws IOException{
//        Parent root = FXMLLoader.load(getClass().getResource("Homescreen.fxml"));
//        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
//        scene = new Scene(root);
//        stage.setScene(scene);
//        stage.show();
    }
}