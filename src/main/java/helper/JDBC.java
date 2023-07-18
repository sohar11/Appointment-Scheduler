package helper;

import java.sql.Connection;
import java.sql.DriverManager;

/**JDBC this class handles all of the components necessary for connecting with the SQL database*/

public abstract class JDBC {

    private static final String protocol = "jdbc";
    private static final String vendor = ":mysql:";
    private static final String location = "//localhost/";
    private static final String databaseName = "client_schedule";
    private static final String jdbcUrl = protocol + vendor + location + databaseName + "?connectionTimeZone = SERVER"; // LOCAL
    private static final String driver = "com.mysql.cj.jdbc.Driver"; // Driver reference
    /**sql user name*/
    private static final String userName = "sqlUser"; // Username
    /**sql db password*/
    private static String password = "Passw0rd!"; // Password
    /**connection connection interface*/
    public static Connection connection;  // Connection Interface


    /**openConnection establishes the connection between the application and the SQL server
     * called in MainApplication*/
    public static void openConnection()
    {
        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(jdbcUrl, userName, password);
            System.out.println("Connection successful!");
        }
        catch(Exception e)
        {
            System.out.println("Error:" + e.getMessage());
        }
    }

    /**closeConnection ends the connection between the SQL server and the application
     * called in MainApplication after exiting application*/
    public static void closeConnection() {
        try {
            connection.close();
            System.out.println("Connection closed!");
        }
        catch(Exception e)
        {
            System.out.println("Error:" + e.getMessage());
        }
    }

}
