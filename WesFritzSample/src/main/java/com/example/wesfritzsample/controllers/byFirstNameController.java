package com.example.wesfritzsample.controllers;

import com.example.wesfritzsample.Main;
import com.example.wesfritzsample.JDBC.JDBC;
import com.example.wesfritzsample.SQLqueries.parcelQueries;
import com.example.wesfritzsample.classes.Parcel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;


/** Class that controls the byFirstName page.
 * Contains a table that displays data from a SQL query in order of first names.
 * Contains a search method to search owner names in full or partially.
 */
public class byFirstNameController implements Initializable {

    @FXML
    private Button backToHomePageButton;

    @FXML
    private TableView<Parcel> byFirstNameTableView;

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
    public TextField txtSearchOwnedParcels;


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

    /** This method allows the user to search for an owner using the search bar.
     * This search can be either a full search, or partial.
     * @param actionEvent The event happens when 'enter' is hit after text is entered.
     * @throws IOException
     * @throws SQLException
     */
    public void onTextSearchOwner(ActionEvent actionEvent) throws IOException, SQLException {
        parcelQueries.createConnection();
        // Pressing enter is what finishes the search after typing in the searchbar!

        String owner =  txtSearchOwnedParcels.getText();
        ObservableList<Parcel> resultsList = searchByOwnerName(owner);

        if (resultsList.size() == 0) {
            try {
                String partialOwner = String.valueOf(owner);
                Parcel parcel = (Parcel) searchByOwnerName(partialOwner);
                if (parcel != null) {
                    resultsList.add(parcel);
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.initModality(Modality.APPLICATION_MODAL);
                    alert.setTitle("No Match");
                    alert.setHeaderText("Unable to locate '" + owner + "' in the records list. Showing all records.");
                    alert.showAndWait();
                }
            } catch (NumberFormatException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.initModality(Modality.APPLICATION_MODAL);
                alert.setTitle("No Match");
                alert.setHeaderText("Unable to locate '" + owner + "' in the records list, Showing all records.");
                alert.showAndWait();
            }
        }
        byFirstNameTableView.setItems(resultsList);
    }


    /** This method works in conjunction with onTextSearchOwner.
     * Allows a user to search through records using an owner's name.
     * @param partialOwner Uses the partial or full name of an owner.
     * @return ownedParcels - a list of the records corresponding to the text input.
     * @throws SQLException
     */
    private ObservableList<Parcel> searchByOwnerName(String partialOwner) throws SQLException{
        parcelQueries.createConnection();
        ObservableList<Parcel> ownedParcels = FXCollections.observableArrayList();
        ObservableList<Parcel> allParcels = parcelQueries.selectToFillFirstNameTable(parcelQueries.connectToDB());
        for (Parcel parcel : allParcels) {
            if (parcel.getOwner().contains(partialOwner)) {
                ownedParcels.add(parcel);
            }
        }
        return ownedParcels;
    }


    /** The initialize method that populates the byFirstName page's fields.
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        // set up connection to database
        try {
            Connection connection = parcelQueries.createConnection();
            ObservableList<Parcel> firstNameParcels = null;


            firstNameParcels = parcelQueries.selectToFillFirstNameTable(connection);
            //fill columns with values
            bySNPin.setCellValueFactory(new PropertyValueFactory<Parcel, Long>("pin"));
            bySNAddress.setCellValueFactory(new PropertyValueFactory<Parcel, String>("address"));
            bySNOwner.setCellValueFactory(new PropertyValueFactory<Parcel, String>("owner"));
            bySNMarketValue.setCellValueFactory(new PropertyValueFactory<Parcel, Double>("marketValue"));
            bySNSaleDate.setCellValueFactory(new PropertyValueFactory<Parcel, String>("saleDate"));
            bySNSalePrice.setCellValueFactory(new PropertyValueFactory<Parcel, Double>("salePrice"));
            bySNLink.setCellValueFactory(new PropertyValueFactory<Parcel, String>("link"));

            byFirstNameTableView.setItems(firstNameParcels);

        } catch (SQLException e) {
            JDBC.sqlAlert();
            e.printStackTrace();
            throw new RuntimeException(e);
        }


    }
}
