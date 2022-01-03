package views;

import javafx.stage.Stage;
import models.View;

import java.io.IOException;

public class BridgeScreen extends View {
    public static Stage getBridgeScreen() throws IOException {
        return getView("BridgeScreen.fxml");  //check the View class
    }
}
