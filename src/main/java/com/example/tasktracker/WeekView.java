package com.example.tasktracker;

import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

public class WeekView {
    private final int SINGLE_DAY = 1;
    public void addEventToCalendar(GridPane week, Button button, int dayCol, int startTime, int duration){
       //.add(button, colIndex, rowIndex, colSpan, rowSpan)
        week.add(button, dayCol, startTime, SINGLE_DAY, duration);
    }

}
