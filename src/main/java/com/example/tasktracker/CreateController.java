package com.example.tasktracker;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


public class CreateController {
    @FXML
    AnchorPane create;
    @FXML
    TextField eventName;


    public void addButton(ActionEvent event) {
        Button button = new Button();
        button.setText(eventName.getText());
        button.setId(eventName.getText());
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.close();
        TestDataSingleton holder = TestDataSingleton.getInstance();
        holder.setButton(button);
    }

}
