package inholland.me.me_ad_project;

import dataLayer.ReadStrain;
import javafx.application.Application;
import javafx.stage.Stage;
import models.Strain;
import views.HomeScreen;
import views.TestScreen;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public class Start extends Application {
    @Override
    public void start(Stage stage) throws IOException {

        stage = HomeScreen.getHomeScreen();
        stage.setTitle("Hello!");
        stage.show();
    }

    public static void main(String[] args) throws IOException {
        List<Strain> strains = ReadStrain.strainsFromTimeframe(LocalDateTime.of(2020, 2, 12, 0, 0),
                                                              LocalDateTime.of(2020, 2, 12, 3, 0));
        launch();
    }
}