package com.example.tasktracker;

import Database.DatabaseManager;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;


public class CreateController implements Initializable {
    @FXML
    private TextField eventName;
    @FXML
    private ComboBox<Integer> startTime;
    @FXML
    private Spinner<String> startTimeAMPM;
    @FXML
    private ComboBox<Integer> endTime;
    @FXML
    private Spinner<String> endTimeAMPM;
    @FXML
    private ComboBox<String> colorPicker;
    @FXML
    private ComboBox<String> dayPicker;
    @FXML
    private Label errorLabel;

    private final ArrayList<Integer> HOURS = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12 ));
    private final ArrayList<String> COLORS = new ArrayList<>(Arrays.asList("Red", "Orange", "Yellow", "Green", "Blue", "Cyan"));
    private final ArrayList<String> DAYS = new ArrayList<>(Arrays.asList("Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"));
    private final String[] AMPM ={"PM", "AM"};

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // initializes ChoiceBoxes and Spinners
        try {
            startTime.getItems().addAll(HOURS);
            endTime.getItems().addAll(HOURS);
            SpinnerValueFactory<String> startValueFactory =
                    new SpinnerValueFactory.ListSpinnerValueFactory<>(FXCollections.observableArrayList(AMPM));
            SpinnerValueFactory<String> endValueFactory =
                    new SpinnerValueFactory.ListSpinnerValueFactory<>(FXCollections.observableArrayList(AMPM));
            startValueFactory.setValue("AM");
            startTimeAMPM.setValueFactory(startValueFactory);
            endValueFactory.setValue("AM");
            endTimeAMPM.setValueFactory(endValueFactory);
            colorPicker.getItems().addAll(COLORS);
            dayPicker.getItems().addAll(DAYS);
        }
        catch(Exception e){
            System.out.println(e);
        }
    }


    public void addButton(ActionEvent event){
        try{
            String day = dayPicker.getValue();
            String name = eventName.getText();
            String color = colorPicker.getValue();
            int firstHour = startTime.getValue();
            int lastHour = endTime.getValue();
            String startAMPM = startTimeAMPM.getValue();
            String endAMPM = endTimeAMPM.getValue();
            if(validateInputs(day, name, color, firstHour, lastHour, startAMPM, endAMPM)){
                Node node = (Node) event.getSource();
                Stage stage = (Stage) node.getScene().getWindow();
                stage.close();
            }
        } catch(NullPointerException e){
            errorLabel.setText("Please fill out all of the values.");
        }
    }


    public boolean validateInputs(String day, String name, String color, int firstHour, int lastHour, String startAMPM, String endAMPM){
        // adjust time by 12 hours if PM is selected
        //if the starting time is during 'PM', but not 12pm (this should allow for events lasting from 12 pm to #pm)
        if (startAMPM.equals("PM") && firstHour != 12){
            firstHour += 12; //add 12 to the starting time (think military/24 time)
        }

        //if the start time is 12 AM
        if(startAMPM.equals("AM") && firstHour == 12) {
            firstHour = 0; //make it 0 o'clock. This should allow for an event to last from 12am to #am
        }

        //if it is # PM (excluding 12)
        if (endAMPM.equals("PM") && lastHour != 12){
            //add 12 to the starting time (think military/24 time)
            lastHour += 12;
        }
        //if the endtime is 12 am
        if (endAMPM.equals("AM") && lastHour == 12) {
            lastHour = 0; //make lastHour 0 o'clock
        }
        if (firstHour > lastHour) {
            errorLabel.setText("An event can't end before it starts!");
            return false;
        }
        if (name.isEmpty()){
            errorLabel.setText("Please fill out all of the values.");
            return false;
        }
        if (day.isEmpty()){
            errorLabel.setText("Please fill out all of the values.");
            return false;
        }
        if (color.isEmpty()){
            errorLabel.setText("Please fill out all of the values.");
            return false;
        }
        System.out.println("Event set.");
        setData(day, color, name, firstHour, lastHour);
        return true;
    }

    public void setData(String day, String color, String name, int firstHour, int lastHour){
        TestDataSingleton singleton = TestDataSingleton.getInstance();;
        DatabaseManager databaseManager = DatabaseManager.getInstance();

        singleton.setDay(day);
        singleton.setColor(color);
        singleton.setEventName(name);
        // increment each by one to adjust for calendar grid
        singleton.setStartHour(firstHour+1);
        singleton.setLastHour(lastHour+1);

        // Save to the database
        databaseManager.InsertEvent(day, name, color, firstHour, lastHour, false);
    }

}