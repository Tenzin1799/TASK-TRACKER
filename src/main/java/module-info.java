module com.example.tasktracker {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    //requires org.postgresql.jdbc;


    opens com.example.tasktracker to javafx.fxml;
    exports com.example.tasktracker;
}