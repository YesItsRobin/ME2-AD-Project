package views;

import javafx.stage.Stage;

import java.io.IOException;

public class AboutScreen extends View{
    public static Stage getAboutScreen() throws IOException {
        return getView("AboutScreen.fxml");  //check the View class
    }
}
