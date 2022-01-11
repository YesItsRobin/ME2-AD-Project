package controllers;

import java.io.IOException;
import java.lang.reflect.Array;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

public class TimeLapseScreenController extends BaseController implements Initializable {
    private Stage stage;
    private Scene scene;
    private Parent root;


    @FXML
    private AnchorPane TimelapseChart;

    @FXML
    private LineChart<?, ?> timelapseChart;

    @FXML
    private MenuButton strainGroupMenu;

    @FXML
    private Button homeButton;

    @FXML
    private MenuButton factorMenu;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        // Create new line on chart
        XYChart.Series series = new XYChart.Series();
        series.setName("Strain");

        Object[][] strain = new Object[4][2];
        strain[0][0] = "Jan 1";
        strain[0][1] = 244;
        strain[1][0] = "Jan 2";
        strain[1][1] = 214;
        strain[2][0] = "Jan 3";
        strain[2][1] = 240;
        strain[3][0] = "Jan 4";
        strain[3][1] = 230;
        // Add data points to make line
        for(int i=0; i<strain.length; i++) {
            series.getData().add(new XYChart.Data(strain[i][0], strain[i][1]));
        }
        // Add series to LineChart
        timelapseChart.getData().add(series);
    }
}
