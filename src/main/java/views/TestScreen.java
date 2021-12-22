package views;

import inholland.me.me_ad_project.Start;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class TestScreen {
    public static Stage getTestScreen() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Start.class.getResource("../../resources/inholland/me/me_ad_project/main-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        Stage stage = new Stage();
        stage.setScene(scene);
        return stage;
    }
}
