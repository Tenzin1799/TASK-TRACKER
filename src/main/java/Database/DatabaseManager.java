package Database;

import java.sql.Connection;
import java.sql.DriverManager;


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
}
