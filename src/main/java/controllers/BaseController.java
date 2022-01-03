package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.stage.Stage;
import views.HomeScreen;

import java.io.IOException;

public abstract class BaseController {

    //Created a BaseController class because there are buttons that every view uses

    @FXML
    protected void closeStage(ActionEvent actionEvent){         //This method closes a view(window)
        Node source = (Node) actionEvent.getSource();           //Get the current view
        Stage oldStage = (Stage) source.getScene().getWindow(); //Get the window of that view
        oldStage.close();
    }

    public void Home(ActionEvent actionEvent) throws IOException {  //This method closes the old window and opens the HomeScreen
        Stage stage = HomeScreen.getHomeScreen();               //Get the HomeScreen view
        stage.show();                                           //Open the view
        closeStage(actionEvent);                                //Close the old view
    }
}
