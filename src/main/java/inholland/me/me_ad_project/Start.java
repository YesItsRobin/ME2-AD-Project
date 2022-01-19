package inholland.me.me_ad_project;

import javafx.application.Application;
import javafx.stage.Stage;
import views.HomeScreen;

import java.io.IOException;


//This is the main class that starts the application. Don't add to this class unless absolutely necessary!!
public class Start extends Application {
    @Override       //Stage stage creates an empty stage, this is an fx thing where you have to open a window.
    public void start(Stage stage) throws IOException {
        //The stage is filled
        stage = HomeScreen.getHomeScreen(); //calls the method getHomeScreen of the view Homescreen
        stage.setTitle("SWECO STATISTICS");      //text in window
        stage.show();                       //Opens up the stage
    }
}