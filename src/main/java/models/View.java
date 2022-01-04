package models;

import inholland.me.me_ad_project.Start;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public abstract class View {
    public static Stage getView(String file) throws IOException {
        //fxml loader that gets the fxml file through a string
        FXMLLoader fxmlLoader = new FXMLLoader(Start.class.getResource(file));
        Scene scene = new Scene(fxmlLoader.load(), 620, 440); //v = xas, v1 = yas
        Stage stage = new Stage();  //the scene contains the fxml info, the stage contains the scene
        stage.setMinHeight(800);
        stage.setMinWidth(1000);
        stage.setScene(scene);      //put the scene in the stage
        return stage;
    }
}
