package Database;

import com.example.tasktracker.Event;
import com.example.tasktracker.SocialEvent;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class DatabaseManager
{
    public Connection connectToDb() {
        final String API_KEY = "jdbc:postgresql://ep-raspy-dream-a5akhty5.us-east-2.aws.neon.tech/TraskTrackerDB?user=TraskTrackerDB_owner&password=lCiLWQfpz74r&sslmode=require";
        Connection connection = null;
        try
        {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(API_KEY);
            if(connection != null) {
                System.out.println("Connected to Database");            }
            else {
                System.out.println("Failed to connect");
            }

        }
        catch (Exception e) {
            System.out.println(e);
        }
        return  connection;
    }

    public void RefreshEventsTable() {
        Statement statement;
        try
        {
            String query = "drop table events; create table events (eventday int,eventName varchar(255),startTime int,endtime int);";
            statement = connectToDb().createStatement();
            statement.executeUpdate(query);
            System.out.println("Events refreshed");
        }
        catch (Exception e) {

        }
    }

    public void InsertEvent(int eventID, String eventName, int startTime, int endTime) {
        Statement statement;
        try
        {
            String query = String.format("insert into tasks(eventday, eventname, starttime, endtime) values(%d,'%s',%d,%d);"
                    , eventID, eventName, startTime, endTime);
            statement = connectToDb().createStatement();
            statement.executeUpdate(query);
            System.out.println("Row inserted");
        }
        catch (Exception e) {
            System.out.println(e);
        }
    }

    public ArrayList<SocialEvent> GetEventList() {
        ArrayList<SocialEvent> eventList = new ArrayList<SocialEvent>();
        Statement statement;
        ResultSet results;
        try {
            String query = "select * from events";
            statement = connectToDb().createStatement();
            results = statement.executeQuery(query);

            while (results.next()) {

                SocialEvent event = new SocialEvent(results.getString("eventname"),
                        Integer.parseInt(results.getString("starttime")),
                        Integer.parseInt(results.getString("endtime")),
                        results.getString("starttime"),
                        results.getString("endtime")
                        );

                eventList.add(event);

                System.out.println("Added: " + results.getString("eventday")+" "+results.getString("eventname")
                        +" "+results.getString("starttime")+" "+results.getString("endtime"));
            }

            return eventList;
        }
        catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    public void PrintEvents() {
        Statement statement;
        ResultSet results;
        try {
            String query = "select * from events";
            statement = connectToDb().createStatement();
            results = statement.executeQuery(query);
            while (results.next()) {
                System.out.println(results.getString("eventday")+" "+results.getString("eventname")
                        +" "+results.getString("starttime")+" "+results.getString("endtime"));
            }
        }
        catch (Exception e) {
            System.out.println(e);
        }
    }
}
