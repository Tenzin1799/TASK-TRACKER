package com.example.tasktracker;

import javafx.scene.control.Button;

public final class TestDataSingleton {
    private Button button;
    private final static TestDataSingleton INSTANCE = new TestDataSingleton();

    private TestDataSingleton() {}

    public static TestDataSingleton getInstance(){
        return INSTANCE;
    }

    public void setButton(Button button, String color) {
        // CHANGE THIS TO ALLOW USER TO PICK A COLOR (ie: dropdown list of colors
        button.setStyle("-fx-background-color: " + color + ";");
        this.button = button;
    }

    public Button getButton() {
        return this.button;
    }


}

