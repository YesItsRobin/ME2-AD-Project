package controllers;


import javafx.event.ActionEvent;
import javafx.stage.Stage;
import views.AboutScreen;
import views.TimeLapseScreen;

import java.io.IOException;

//Controllers assign events to buttons and references for images. It can also assign values for things like labels
public class HomeScreenController extends BaseController {


    //These 2 buttons change the view from HomeScreen To another

    public void TimeLapse(ActionEvent actionEvent) throws IOException {

        Stage stage = TimeLapseScreen.getTimeLapseScreen();
        stage.show();
        closeStage(actionEvent);
    }


    public void About(ActionEvent actionEvent) throws IOException {
        Stage stage = AboutScreen.getAboutScreen();
        stage.show();
        closeStage(actionEvent);
    }

}
