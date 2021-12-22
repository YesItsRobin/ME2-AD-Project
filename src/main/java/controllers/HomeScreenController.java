package controllers;


import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.stage.Stage;
import views.AboutScreen;

import java.io.IOException;

//Controllers assign events to buttons and references for images. It can also assign values for things like labels
public class HomeScreenController extends BaseController {

    public void statScreen(ActionEvent actionEvent) {
    }

    public void OtherStatScreen(ActionEvent actionEvent) {
    }

    public void Bridge(ActionEvent actionEvent) {
    }

    public void About(ActionEvent actionEvent) throws IOException {
        Stage stage = AboutScreen.getAboutScreen();
        stage.show();
        closeStage(actionEvent);
    }

}
