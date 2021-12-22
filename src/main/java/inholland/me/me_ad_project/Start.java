package inholland.me.me_ad_project;

import javafx.application.Application;
import javafx.stage.Stage;
import views.GraphScreen;
import views.MainScreen;

import java.io.IOException;

public class Start extends Application {
    @Override
    public void start(Stage stage) throws IOException {

        stage = MainScreen.getMainScreen();
        stage.setTitle("Hello!");
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}