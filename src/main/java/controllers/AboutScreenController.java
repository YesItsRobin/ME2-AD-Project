package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.stage.Stage;
import views.AboutScreen;
import views.HomeScreen;

import java.io.IOException;

public class AboutScreenController extends BaseController{
    @FXML
    public void Home(ActionEvent actionEvent) throws IOException {
        Stage stage = HomeScreen.getHomeScreen();
        stage.show();
        closeStage(actionEvent);

    }
}
