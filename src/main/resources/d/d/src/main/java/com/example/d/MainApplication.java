package com.example.d;

import helper.JDBC;
import helper.LoginConfirmation;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;
/** This application  launches the appointment management software*/
public class MainApplication extends Application {

    /** This starts the FXML stage and loads the Main Menu as the first scene.
     * @param stage Login stage launcher
     *  */
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("Login.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 400, 400);
        stage.setTitle("Appointment Manager");
        stage.setScene(scene);
        stage.show();
    }

    /** This launches the app and connects to the SQL database
     * @param args arguments for launching */



    public static void main(String[] args) {




        JDBC.openConnection();

        launch(args);
        JDBC.closeConnection();
    }
}