module inholland.me.me_ad_project {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.jetbrains.annotations;
    requires opencsv;


    opens inholland.me.me_ad_project to javafx.fxml;
    exports inholland.me.me_ad_project;
    exports controllers;
    opens controllers to javafx.fxml;
}