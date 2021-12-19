package views;

import inholland.me.me_ad_project.Start;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainScreen {
    public static Stage getMainScreen() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Start.class.getResource("main-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        Stage stage = new Stage();
        stage.setScene(scene);
        return stage;
    }
}
