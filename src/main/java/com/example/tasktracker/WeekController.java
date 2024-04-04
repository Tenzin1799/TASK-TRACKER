package com.example.tasktracker;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class WeekController implements Initializable {
    @FXML
    private GridPane week;


    private ObservableList<Day> daysList;
    private WeekView view = new WeekView();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
//        Day sunday = new Day("Sunday");
//        Day monday = new Day("Monday");
//        Day tuesday = new Day("Tuesday");
//        Day wednesday = new Day("Wednesday");
//        Day thursday = new Day("Thursday");
//        Day friday = new Day("Friday");
//        Day saturday = new Day("Saturday");
    }


//    public void addButton(){
//        Button button = new Button();
//        button.setText("Test");
//        button.setId("Test");
//        button.setMaxSize(week.getHeight(), week.getWidth());
//        view.testAddButton(week, button);
//    }


    public void openCreateView() throws IOException {
        Parent createRoot = FXMLLoader.load(getClass().getResource("Create.fxml"));
        Stage createStage = new Stage();
        Scene createScene = new Scene(createRoot);
        createStage.setScene(createScene);
        createStage.setResizable(false);
        createStage.showAndWait();

        // this part should probably go to a different function
        TestDataSingleton singleton = TestDataSingleton.getInstance();
        Button button = singleton.getButton();
        System.out.println(button.getText());
        button.setMaxSize(week.getHeight(), week.getWidth());
        view.testAddButton(week, button);
    }

    public void addButton(){

    }


}

