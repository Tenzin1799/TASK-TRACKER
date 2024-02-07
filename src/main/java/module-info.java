module com.example.tasktracker {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.tasktracker to javafx.fxml;
    exports com.example.tasktracker;
}