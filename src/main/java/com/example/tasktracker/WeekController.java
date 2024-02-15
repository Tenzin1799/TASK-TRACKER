package com.example.tasktracker;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

import java.net.URL;
import java.util.ResourceBundle;

public class WeekController implements Initializable {
    @FXML
    GridPane week;
    public void addButton(){

        Button button = new Button();
        button.setText("Test");
        button.setId("Test");
        button.setMaxSize(week.getHeight(), week.getWidth());
        week.add(button,1, 2);

//         attempting to use MVC architecture but getting a "this.week is null" error
//        view.addEventToWeek(btn);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}

