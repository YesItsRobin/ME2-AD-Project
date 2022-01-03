package views;

import javafx.stage.Stage;
import models.View;

import java.io.IOException;

public class TimeLapseScreen extends View {
    public static Stage getTimeLapseScreen() throws IOException {
        return getView("TimeLapseScreen.fxml");  //check the View class
    }
}
