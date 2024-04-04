package com.example.tasktracker;

import javafx.scene.control.Button;

public final class TestDataSingleton {
    private Button button;
    private String day;
    private String eventName;
    private String color;
    private int startHour;
    private int lastHour;
    private final static TestDataSingleton INSTANCE = new TestDataSingleton();

    private TestDataSingleton() {}

    public static TestDataSingleton getInstance(){
        return INSTANCE;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public int getStartHour() {
        return startHour;
    }

    public void setStartHour(int startHour) {
        this.startHour = startHour;
    }

    public int getLastHour() {
        return lastHour;
    }

    public void setLastHour(int lastHour) {
        this.lastHour = lastHour;
    }

    public void setButton(Button button, String color) {
        // CHANGE THIS TO ALLOW USER TO PICK A COLOR (ie: dropdown list of colors
        button.setStyle("-fx-background-color: " + color + ";");
        this.button = button;
    }

    public Button getButton() {
        return this.button;
    }

    public void setDay(String day){
        this.day = day;
    }

    public String getDay(){
        return this.day;
    }


    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}

