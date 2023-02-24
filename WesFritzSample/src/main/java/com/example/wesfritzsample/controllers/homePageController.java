package com.example.wesfritzsample.controllers;

import com.example.wesfritzsample.Main;
import com.example.wesfritzsample.JDBC.JDBC;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

/** Class that controls the homePage page.
 * Contains buttons that lead the user to the two different views of parcel data.
 */
public class homePageController implements Initializable {

    Stage stage;

    Parent scene;

    @FXML
    private Button byFirstNameButton;

    @FXML
    private Button byStreetNameButton;

    @FXML
    private Button exitAppButton;


    /** This method closes the application upon clicking.
     * Also gives an alert to confirm that the user wishes to do so.
     * @param event Happens when the user clicks the 'Exit' button.
     */
    @FXML
    void onClickExitApp(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "You are about to close the application. Are you sure?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            JDBC.closeConnection();
            System.exit(0);
        }
    }

    /** This method takes the user to the byFirstName page upon clicking.
     * @param event Happens when user clicks the 'Owner's First Name' button.
     * @throws IOException
     */
    @FXML
    void onClickGoToFirstName(ActionEvent event) throws IOException{
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        Object scene = FXMLLoader.load(Main.class.getResource("byFirstName.fxml"));
        stage.setScene(new Scene((Parent) scene));
        stage.show();
    }

    /** This method takes the user to the byStreetName page upon clicking.
     * @param event Happens when user clicks the 'Street Name and Number' button.
     * @throws IOException
     */
    @FXML
    void onClickGoToStreetName(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        Object scene = FXMLLoader.load(Main.class.getResource("byStreetName.fxml"));
        stage.setScene(new Scene((Parent) scene));
        stage.show();


    }

    /** This method would be used to initialize any data that needed populating on this page.
     * Unused currently, but ready for future implementation if I need a table.
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
