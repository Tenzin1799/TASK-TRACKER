package com.example.tasktracker;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;


public class CreateController implements Initializable {
    @FXML
    private AnchorPane create;
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


    public void addButton(ActionEvent event) {
        Button button = new Button();
        button.setText(eventName.getText());
        button.setId(eventName.getText());
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.close();
        TestDataSingleton holder = TestDataSingleton.getInstance();
        String color = colorPicker.getValue();
        holder.setButton(button, color);
    }

}
