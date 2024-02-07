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

    private final ArrayList<Integer> hours = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12 ));
    private final String[] AMPM ={"PM", "AM"};
    private static TestModel tm = new TestModel();  // static allows data to be modified by different Scenes and Stages

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // initializes ChoiceBoxes and Spinners
        try {
            startTime.getItems().addAll(hours);
            endTime.getItems().addAll(hours);
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
            if(validateTimes(name, firstHour, lastHour, startAMPM, endAMPM)){
                tm.stuff.add(new SocialEvent(name, firstHour, lastHour, startAMPM, endAMPM));
                tm.stuff.sort(new EventComparator());
                eventName.setText("");
                startTime.setValue(null);
                endTime.setValue(null);
            }
            System.out.println(tm.stuff);
        }
        catch(NullPointerException e){
            errorLabel.setText("Please fill out all of the values.");
        }
        catch(Exception e){
            System.out.println(e);
        }
    }

    public boolean validateTimes(String name, int firstHour, int lastHour, String startAMPM, String endAMPM){
        // adjust time by 12 hours if PM is selected
        if (startAMPM.equals("PM")){
            firstHour += 12;
        }
        if (endAMPM.equals("PM")){
            lastHour += 12;
        }
        if (firstHour > lastHour) {
            errorLabel.setText("An event can't end before it starts!");
            return false;
        }
        if (name.equals("")){
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
            sundayList.getItems().addAll(tm.stuff);
            System.out.println(tm.stuff.toString());
        }
        catch (Exception e){
            System.out.println(e);
        }
    }


}