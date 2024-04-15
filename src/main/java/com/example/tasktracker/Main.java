package com.example.tasktracker;

import Database.DatabaseManager;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Main extends Application {



    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("WeekView.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setFullScreen(false);
        stage.setResizable(true);
        stage.show();
        DatabaseManager databaseManager = DatabaseManager.getInstance();
        databaseManager.connectToDb();
    }


    public static void main(String[] args) {
        launch(args);
    }


}
