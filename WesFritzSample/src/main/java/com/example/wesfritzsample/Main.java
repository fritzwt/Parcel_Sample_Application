package com.example.wesfritzsample;

// ******************* This application was made by Wesley Fritz, February 2023 **************************

import com.example.wesfritzsample.JDBC.JDBC;
import com.example.wesfritzsample.SQLqueries.parcelQueries;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.sql.Connection;
import java.sql.SQLException;
import java.io.IOException;

/** This is the main class for my sample/application.
 * Contains the primary launchpad for my application.
 */
public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("homePage.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        stage.setTitle("Wes Fritz Raftelis Sample Application");
        stage.setScene(scene);
        stage.show();
    }

    /** The main method for the main class.
     * Opens the connection to the database, launches the application, then closes it when done.
     * Through the included methods within, gives text output showing database connection status.
     * @param args launches with contained arguments.
     * @throws SQLException
     */
    public static void main(String[] args) throws SQLException {

        // database connection opened
        JDBC.createConnection();

        Connection connection = parcelQueries.connectToDB();
        parcelQueries.selectToFillParcelTable(connection);

        // launches application to Login screen
        launch(args);

        // database connection closed
        JDBC.closeConnection();
    }
}