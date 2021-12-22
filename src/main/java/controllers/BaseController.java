package controllers;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.stage.Stage;

public abstract class BaseController {
    protected void closeStage(ActionEvent actionEvent){
        Node source = (Node) actionEvent.getSource();
        Stage oldStage = (Stage) source.getScene().getWindow();
        oldStage.close();
    }
}
