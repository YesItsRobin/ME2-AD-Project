package views;

import javafx.stage.Stage;
import models.View;

import java.io.IOException;

//Create a new view for every screen(fxml file)

public class HomeScreen extends View {
    public static Stage getHomeScreen() throws IOException {
        return getView("HomeScreen.fxml");  //check the View class
    }
}
