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
	
	
	public static boolean UpdateButtonClicked = false;//set to false, applies to each time the pop up window gets opened.
	public static boolean DeleteButtonClicked = false;
	public static boolean CompleteButtonClicked = false;


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
			case "Grey":	
				color = "#A4A4A4"; 	
        }
        button.setStyle("-fx-background-color: " + color + ";");
    }

    // Gives each event button that is created functionality so that they can open up an edit
    // screen with its particular data attached.
    public void setButtonFunctionality(Button button){
        button.setOnAction((ActionEvent e) -> {	//when the button is clicked, the following will occur:
			Button userButton = (Button) e.getSource(); 
            Parent editRoot = null;
            try {
                editRoot = FXMLLoader.load(getClass().getResource("Edit.fxml"));
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            Stage editStage = new Stage();
            Scene editScene = new Scene(editRoot);
            editStage.setScene(editScene);
            editStage.setResizable(true);
            editStage.setFullScreen(false);
			System.out.println(userButton);

            editStage.showAndWait();
			if (UpdateButtonClicked == true) {

				week.getChildren().remove(userButton);
				try {
					addEvent();	
				} catch(Exception a){	
					System.out.println(a);
				}
				UpdateButtonClicked = false;
			}
			if (DeleteButtonClicked == true) { 

				//delete the button associated with the variable userButton
				week.getChildren().remove(userButton); 
				DeleteButtonClicked = false;
			}
			
			if (CompleteButtonClicked == true) { 

				//call the setButtonColor and set it to grey or something.
				setButtonColor(userButton, "Grey");
				CompleteButtonClicked = false;
			}  
			

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

