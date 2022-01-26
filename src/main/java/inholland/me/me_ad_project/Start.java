package inholland.me.me_ad_project;

import dataLayer.ReadCompactMeteo;
import dataLayer.ReadCompactStrain;
import javafx.application.Application;
import javafx.stage.Stage;
import models.CompactStrain;
import models.Influences;
import models.MathHelper;
import views.HomeScreen;

import java.io.IOException;
import java.util.ArrayList;


//This is the main class that starts the application. Don't add to this class unless absolutely necessary!!
public class Start extends Application {
    @Override       //Stage stage creates an empty stage, this is an fx thing where you have to open a window.
    public void start(Stage stage) throws IOException {
        //The stage is filled
        stage = HomeScreen.getHomeScreen(); //calls the method getHomeScreen of the view Homescreen
        stage.setTitle("SWECO STATISTICS");      //text in window
        stage.show();                       //Opens up the stage

        ArrayList<CompactStrain> strains = new ArrayList<>();
        for(int i=1;i<=8;i++){
            strains.addAll(ReadCompactStrain.getCompactedStrainsGroup(i));
        }
        System.out.println("Correlation strain/age: "       + MathHelper.SpearmansCorrelation(strains, Influences.age));
        System.out.println("Correlation strain/windSpeed: " + MathHelper.SpearmansCorrelation(strains, Influences.windSpeed));
        System.out.println("Correlation strain/temp: "      + MathHelper.SpearmansCorrelation(strains, Influences.temp));
        System.out.println("Correlation strain/sun: "       + MathHelper.SpearmansCorrelation(strains, Influences.sun));
        System.out.println("Correlation strain/atmosPres: " + MathHelper.SpearmansCorrelation(strains, Influences.atmosPres));
        System.out.println("Correlation strain/humidity: "  + MathHelper.SpearmansCorrelation(strains, Influences.humidity));

    }
}