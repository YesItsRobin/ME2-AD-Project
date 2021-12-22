package inholland.me.me_ad_project;

import javafx.application.Application;
import javafx.stage.Stage;
import views.HomeScreen;
import views.TestScreen;

import java.io.IOException;

public class Start extends Application {
    @Override
    public void start(Stage stage) throws IOException {

        stage = HomeScreen.getHomeScreen();
        stage.setTitle("Hello!");
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}