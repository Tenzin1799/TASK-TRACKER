package com.example.tasktracker;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
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
    final private int OFFSET = 1;   // offset duration of events by 1 when start and end are subtracted to account events that are only 1 hour.

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

    public void openCreateView() throws IOException {
        Parent createRoot = FXMLLoader.load(getClass().getResource("Create.fxml"));
        Stage createStage = new Stage();
        Scene createScene = new Scene(createRoot);
        createStage.setScene(createScene);
        createStage.setResizable(true);
        createStage.setFullScreen(false);
        createStage.showAndWait();
        try {
            addEvent();
        } catch(Exception e){
            System.out.println(e);
        }
    }

    public void addEvent(){
        TestDataSingleton singleton = TestDataSingleton.getInstance();
        Button button = new Button();
        button.setText(singleton.getEventName());
        button.setId(singleton.getEventName());
        String color = singleton.getColor();
        int day = getDayNum(singleton.getDay());
        int startTime = singleton.getStartHour();
        int endTime = singleton.getLastHour();
        int duration = (endTime - startTime) + OFFSET;
        setButtonColor(button, color);
        setButtonFunctionality(button);
        System.out.println(button.getText());
        button.setMaxSize(week.getHeight(), week.getWidth());
        view.addEventToCalendar(week, button, day, startTime, duration);
    }

    // Changes color to specified hex-values to avoid using the ugly default colors.
    public void setButtonColor(Button button, String color){
        switch(color){
            case "Red":
                color = "#ff3333";
                break;
            case "Orange":
                color = "#ffaa3b";
                break;
            case "Yellow":
                color = "#ffea00";
                break;
            case "Green":
                color = "#16fa66";
                break;
            case "Blue":
                color = "#11abed";
                break;
        }
        button.setStyle("-fx-background-color: " + color + ";");
    }

    // Gives each event button that is created functionality so that they can open up an edit
    // screen with its particular data attached.
    public void setButtonFunctionality(Button button){
        button.setOnAction((ActionEvent e) -> {
            Parent createRoot = null;
            try {
                createRoot = FXMLLoader.load(getClass().getResource("Edit.fxml"));
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            Stage createStage = new Stage();
            Scene createScene = new Scene(createRoot);
            createStage.setScene(createScene);
            createStage.setResizable(true);
            createStage.setFullScreen(false);
            createStage.showAndWait();
        });
    }

    public int getDayNum(String day){
        if(day == "Sunday"){
            return 1;
        }
        else if (day == "Monday"){
            return 2;
        }
        else if (day == "Tuesday"){
            return 3;
        }
        else if (day == "Wednesday"){
            return 4;
        }
        else if (day == "Thursday"){
            return 5;
        }
        else if (day == "Friday"){
            return 6;
        }
        else
            return 7;
    }

}

