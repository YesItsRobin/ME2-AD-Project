package views;

import javafx.stage.Stage;
import models.View;

import java.io.IOException;

public class StatisticsScreen extends View {
    public static Stage getStatisticsScreen() throws IOException {
        return getView("StatisticsScreen.fxml");  //check the View class
    }
}
