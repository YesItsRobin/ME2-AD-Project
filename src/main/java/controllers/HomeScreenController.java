package controllers;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import views.AboutScreen;
import views.BridgeScreen;
import views.StatisticsScreen;
import views.TimeLapseScreen;

import java.io.IOException;

//Controllers assign events to buttons and references for images. It can also assign values for things like labels
public class HomeScreenController extends BaseController {


    //These 4 buttons change the view from HomeScreen To another

    public void statScreen(ActionEvent actionEvent) throws IOException {
        Stage stage = StatisticsScreen.getStatisticsScreen();   //Get the new screen that needs to be shown
        stage.show();               //Open that new view
        closeStage(actionEvent);    //Close the old view (HomeScreen)
    }

    public void TimeLapse(ActionEvent actionEvent) throws IOException {
        Stage stage = TimeLapseScreen.getTimeLapseScreen();
        stage.show();
        closeStage(actionEvent);
    }

    public void Bridge(ActionEvent actionEvent) throws IOException{
        Stage stage = BridgeScreen.getBridgeScreen();
        stage.show();
        closeStage(actionEvent);
    }

    public void About(ActionEvent actionEvent) throws IOException {
        Stage stage = AboutScreen.getAboutScreen();
        stage.show();
        closeStage(actionEvent);
    }

}
