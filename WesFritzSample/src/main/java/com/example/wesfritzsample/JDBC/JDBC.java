package com.example.wesfritzsample.JDBC;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Locale;
import java.util.ResourceBundle;
import java.sql.*;

/** This connects the java application to the database.
 * I used a local database for my project, via IntelliJ and MySQL.
 */
public abstract class JDBC {
    private static final String protocol = "jdbc";
    private static final String vendor = ":mysql:";

    //localhost below is where our local database is hosted.
    private static final String location = "//localhost/";
    private static final String databaseName = "parcel_data";

    // trying a new connection string....
    private static final String jdbcUrl = protocol + vendor + location + databaseName + "?useLegacyDatetimeCode=false&serverTimezone=UTC";

    private static final String driver = "com.mysql.cj.jdbc.Driver"; // Driver reference

    // holds login information for the MySQL database.
    private static final String userName = "outsideUser"; // Username
    private static String password = "outsideUser!14"; // Password

    public static Connection connection;  // Connection Interface

    /** This method opens the connection to the database.
     * Gives a line of output to signify a successful connection.
     * @return connection1 -- provides connection to the database when fired off. */
    public static Connection createConnection(){
        try {
            Class.forName(driver);
            Connection connection1 = DriverManager.getConnection(jdbcUrl, userName, password);
            System.out.println("Connected to Database.");
            return connection1;
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException("Cannot connect to database", e);
        }

    }

    /** This method closes the connection to the database.
     * Gives a line of output to signify successful disconnection. */
    public static Connection closeConnection(){
        try {
            Class.forName(driver);
            Connection connection = DriverManager.getConnection(jdbcUrl, userName, password);
            connection.close();
            System.out.println("Connection closed.");
            return connection;
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException("Cannot close connection", e);
        }

    }

    /** This method is used to signify an error within SQL.
     * Helpful when trying to make SQL queries that need adjustment.*/
    public static void sqlAlert() {
        Alert alert = new Alert(Alert.AlertType.ERROR, "There is a general SQL Error, refer to terminal output", ButtonType.OK);
        alert.showAndWait();
    }


}

