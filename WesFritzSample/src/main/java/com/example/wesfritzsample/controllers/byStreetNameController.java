package com.example.wesfritzsample.controllers;

import com.example.wesfritzsample.Main;
import com.example.wesfritzsample.JDBC.JDBC;
import com.example.wesfritzsample.SQLqueries.parcelQueries;
import com.example.wesfritzsample.classes.Parcel;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.scene.Scene;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;


/** Class that controls the byStreetName page.
 * Contains a table that displays data from a SQL query in order of street names, then street numbers.
 */
public class byStreetNameController implements Initializable {

    Stage stage;
    Parent scene;
    @FXML
    private Button backToHomePageButton;

    @FXML
    private TableColumn<Parcel, String> bySNAddress;

    @FXML
    private TableColumn<Parcel, String> bySNLink;

    @FXML
    private TableColumn<Parcel, Double> bySNMarketValue;

    @FXML
    private TableColumn<Parcel, String> bySNOwner;

    @FXML
    private TableColumn<Parcel, Long> bySNPin;

    @FXML
    private TableColumn<Parcel, String> bySNSaleDate;

    @FXML
    private TableColumn<Parcel, Double> bySNSalePrice;

    @FXML
    private TableView<Parcel> byStreetNameTableView;

    /** This method takes the user back to the home page.
     * @param event Happens when user clicks the back to home page button.
     * @throws IOException
     */
    @FXML
    void onClickBackToHomePage(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        Object scene = FXMLLoader.load(Main.class.getResource("homePage.fxml"));
        stage.setScene(new Scene((Parent) scene));
        stage.show();
    }

    /** Class that controls the byStreetName page.
     * Contains a table that displays data from a SQL query in order of street names, then numbers.
     */
    @Override
public void initialize(URL url, ResourceBundle resourceBundle) {

        // set up connection to database
        try {
        Connection connection = parcelQueries.createConnection();
        ObservableList <Parcel> streetNameParcels = null;


            streetNameParcels = parcelQueries.selectToFillStreetNameTable(connection);
            //fill columns with values
            bySNPin.setCellValueFactory(new PropertyValueFactory<Parcel, Long>("pin"));
            bySNAddress.setCellValueFactory(new PropertyValueFactory<Parcel, String>("address"));
            bySNOwner.setCellValueFactory(new PropertyValueFactory<Parcel, String>("owner"));
            bySNMarketValue.setCellValueFactory(new PropertyValueFactory<Parcel, Double>("marketValue"));
            bySNSaleDate.setCellValueFactory(new PropertyValueFactory<Parcel, String>("saleDate"));
            bySNSalePrice.setCellValueFactory(new PropertyValueFactory<Parcel, Double>("salePrice"));
            bySNLink.setCellValueFactory(new PropertyValueFactory<Parcel, String>("link"));

            byStreetNameTableView.setItems(streetNameParcels);

        } catch (SQLException e) {
            JDBC.sqlAlert();
            e.printStackTrace();
            throw new RuntimeException(e);
        }


    }
}
