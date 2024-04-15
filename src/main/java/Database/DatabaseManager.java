package Database;

import com.example.tasktracker.Event;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;


public final class DatabaseManager
{
    boolean isConnected = false;

    public final static DatabaseManager INSTANCE = new DatabaseManager();

    private DatabaseManager() {}

    public static DatabaseManager getInstance() {return INSTANCE;}

    // Connects to the Database
    public Connection connectToDb() {
        final String API_KEY = "jdbc:postgresql://ep-raspy-dream-a5akhty5.us-east-2.aws.neon.tech/TraskTrackerDB?user=TraskTrackerDB_owner&password=lCiLWQfpz74r&sslmode=require";
        Connection connection = null;
        try
        {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(API_KEY);
            if(connection != null) {
                System.out.println("Connected to Database");
                isConnected = true;
            }
            else {
                System.out.println("Failed to connect");
            }

        }
        catch (Exception e) {
            System.out.println(e);
        }
        return  connection;
    }

    // Deletes the table and creates it again
    public void RefreshEventsTable() {
        Statement statement;
        try
        {
            String query = "drop table events; create table events (eventday varchar(255), eventName varchar(255),eventColor varchar(255),startTime int,endtime int);";
            statement = connectToDb().createStatement();
            statement.executeUpdate(query);
            System.out.println("Events refreshed");
        }
        catch (Exception e) {

        }
    }

    // Inserts an event to the table
    public void InsertEvent(String eventDay, String eventName, String eventColor, int startTime, int endTime, boolean isCompleted) {
        Statement statement;
        try
        {
            String query = String.format("insert into events(eventday, eventname, eventcolor, starttime, endtime, iscompleted) values('%s','%s','%s',%d,%d, %b);"
                    , eventDay, eventName, eventColor, startTime, endTime, isCompleted);
            statement = connectToDb().createStatement();
            statement.executeUpdate(query);
            System.out.println("Row inserted");
        }
        catch (Exception e) {
            System.out.println(e);
        }
    }

    // Deletes an event from the table
    public void DeleteEvent(String eventDay, String eventName, String eventColor, int startTime, int endTime) {
        Statement statement;
        try {
            String query = "delete from events where eventday = '"+eventDay
                    + "' and eventName = '"+eventName+"' and eventcolor = '"
                    + eventColor+ "' and starttime ="+startTime
                    + " and endtime ="+endTime ;
            statement = connectToDb().createStatement();
            statement.executeUpdate(query);
            System.out.println("Removed row");
        }
        catch (Exception e) {
            System.out.println(e);
        }
    }

    // Updates an event by finding it through its older values and updates them
    public void UpdateEvent(String eventDay, String eventName, String eventColor, int startTime, int endTime,
        String newEventDay, String newEventName, String newEventColor, int newStartTime, int newEndTime, boolean isCompleted) {
        Statement statement;
        try {
            String query = "update events "
                    + "set eventday ='"+newEventDay+"', eventName = '"+newEventName+"', eventcolor = '"
                    + newEventColor+ "', starttime ="+newStartTime+", endtime ="+ newEndTime+", iscompleted ="+isCompleted
                    + " where eventday = '"+eventDay + "' and eventName = '"+eventName
                    + "' and eventcolor = '" + eventColor+ "' and starttime ="+startTime
                    + " and endtime ="+endTime;
            statement = connectToDb().createStatement();
            statement.executeUpdate(query);
            System.out.println("Removed row");
        }
        catch (Exception e) {
            System.out.println(e);
        }
    }

    // Returns a list of events
    public ArrayList<Event> GetEventList() {
        ArrayList<Event> eventList = new ArrayList<Event>();
        Statement statement;
        ResultSet results;
        try {
            String query = "select * from events";
            statement = connectToDb().createStatement();
            results = statement.executeQuery(query);

            while (results.next()) {

                Event event = new Event(results.getString("eventday"),
                        results.getString("eventname"),
                        results.getString("eventcolor"),
                        Integer.parseInt(results.getString("starttime")),
                        Integer.parseInt(results.getString("endtime")),
                        results.getBoolean("iscompleted")
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

    public boolean isConnected() {
        return isConnected;
    }
}
