package controllers;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class TimeLapseScreenController {
    private Stage stage;
    private Scene scene;
    private Parent root;


    @FXML
    private AnchorPane TimelapseChart;

    @FXML
    private MenuButton strainGroupMenu;

    @FXML
    private Button homeButton;

    @FXML
    private MenuButton factorMenu;


    @FXML
    void showHomepage(ActionEvent event)  throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("Homescreen.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}
