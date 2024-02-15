package com.example.tasktracker;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;


import java.io.IOException;
import java.net.URL;
import java.util.*;


public class SceneController implements Initializable {

    @FXML
    private ListView<Event> sundayList;
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
    private Label errorLabel;

    private final ArrayList<Integer> HOURS = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12 ));
    private final String[] AMPM ={"PM", "AM"};
    private static Day sunday = new Day("Sunday");  // static allows data to be modified by different Scenes and Stages

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
        }
        catch(Exception e){
            System.out.println(e);
        }
    }

    public void switchToCreateScene() throws IOException{
        Parent createRoot = FXMLLoader.load(getClass().getResource("Create.fxml"));
        Stage createStage = new Stage();
        Scene createScene = new Scene(createRoot);
        createStage.setScene(createScene);
        createStage.setResizable(false);
        createStage.show();
    }


    public void addItem(){
        try {
            String name = eventName.getText();
            int firstHour = startTime.getValue();
            int lastHour = endTime.getValue();
            String startAMPM = startTimeAMPM.getValue();
            String endAMPM = endTimeAMPM.getValue();
            if(validateInputs(name, firstHour, lastHour, startAMPM, endAMPM)){
                sunday.getEvents().add(new SocialEvent(name, firstHour, lastHour, startAMPM, endAMPM));
                sunday.getEvents().sort(new EventComparator());
                eventName.setText("");
                startTime.setValue(null);
                endTime.setValue(null);
            }
            System.out.println(sunday.getEvents());
        }
        catch(NullPointerException e){
            errorLabel.setText("Please fill out all of the values.");
        }
        catch(Exception e){
            System.out.println(e);
        }
    }

    public boolean validateInputs(String name, int firstHour, int lastHour, String startAMPM, String endAMPM){
        // adjust time by 12 hours if PM is selected
        if (startAMPM.equals("PM") && firstHour != 12){//if the starting time is during 'PM', but not 12pm (this should allow for events lasting from 12 pm to #pm)
            firstHour += 12; //add 12 to the starting time (think military/24 time)
        }
		if(startAMPM.equals("AM") && firstHour == 12) { //if the start time is 12 AM
			firstHour = 0; //make it 0 o'clock. This should allow for an event to last from 12am to #am
		}
		
        if (endAMPM.equals("PM") && lastHour != 12){ //if it is # PM (excluding 12),
            lastHour += 12; //add 12 to the starting time (think military/24 time)
        }
		if (endAMPM.equals("AM") && lastHour == 12) {//if the endtime is 12 am
			lastHour = 0; //make lastHour 0 o'clock
		}
        if (firstHour != 12 && lastHour == 12){
            errorLabel.setText("Events can't be scheduled past 11 pm.");
            return false;
        }
        if (firstHour > lastHour) {
            errorLabel.setText("An event can't end before it starts!");
            return false;
        }
        if (name.isEmpty()){
            errorLabel.setText("Please fill out all of the values.");
            return false;
        }
        errorLabel.setText("Event has been set. Head back and refresh!");
        return true;
    }

    // These are just testing, not how it should be in final build.
    // I can't seem to figure out how to simply add the item to the main stage without passing the controller back to it.
    // Maybe once the database is implemented it will fix that problem, and we'll be able to add tasks from the Create.fxml stage.
    public void refresh(){
        try {
            sundayList.getItems().clear();
            sundayList.getItems().addAll(sunday.getEvents());
            System.out.println(sunday.getEvents().toString());
        }
        catch (Exception e){
            System.out.println(e);
        }
    }


}
