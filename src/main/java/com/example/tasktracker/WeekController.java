package com.example.tasktracker;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

import java.net.URL;
import java.util.ResourceBundle;

public class WeekController implements Initializable {
    @FXML
    GridPane week;
    ObservableList<Day> daysList;
    
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Day sunday = new Day("Sunday");
        Day monday = new Day("Monday");
        Day tuesday = new Day("Tuesday");
        Day wednesday = new Day("Wednesday");
        Day thursday = new Day("Thursday");
        Day friday = new Day("Friday");
        Day saturday = new Day("Saturday");


    }


    public void addButton(){
        Button button = new Button();
        button.setText("Test");
        button.setId("Test");
        button.setMaxSize(week.getHeight(), week.getWidth());
        week.add(button,1, 2);
//         attempting to use MVC architecture but getting a "this.week is null" error
//        view.addEventToWeek(btn);

        Button testStretchedButton = new Button();
        testStretchedButton.setText("Big Button");
        testStretchedButton.setId("Test");
        testStretchedButton.setMaxSize(week.getHeight(), week.getWidth());
        week.add(testStretchedButton,2, 4, 1, 3);

        Button layeredButton = new Button();
        layeredButton.setText("Layer Button");
        layeredButton.setId("Layer");
        layeredButton.setMaxSize(week.getHeight(), week.getWidth());
        week.add(layeredButton,2, 4, 1, 1);

    }


}

