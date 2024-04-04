package com.example.tasktracker;

import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

public class WeekView {

    public void testAddButton(GridPane week, Button button){
        // ALTER THIS TO ACCOUNT FOR TIME
       //.add(button, colIndex, rowIndex, colSpan, rowSpan)
        week.add(button,1, 1);
    }
}
