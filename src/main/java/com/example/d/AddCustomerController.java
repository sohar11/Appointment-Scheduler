package com.example.d;

import com.example.d.model.FirstLevelDivisions;
import helper.CountryMGMT;
import helper.CustomerMGMT;
import helper.DivisionMGMT;
import helper.InstantMessenger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AddCustomerController  implements Initializable {
    Stage stage;
    Parent scene;

    @FXML
    ComboBox<String> divisionBox;
    @FXML
    ComboBox<String> countryBox;

    @FXML
    TextField customerIDField;

    @FXML
    TextField nameField;

    @FXML
    TextField addressField;

    @FXML
    TextField phoneField;

    @FXML
    TextField postalField;

    @FXML
    void saveAndReturn(ActionEvent event) throws IOException {
        if (nameField.getText().isEmpty() || addressField.getText().isEmpty() || customerIDField.getText().isEmpty() || postalField.getText().isEmpty() || phoneField.getText().isEmpty() || countryBox.getValue() == null || divisionBox.getValue() == null)
        {
            InstantMessenger mess = new InstantMessenger( "Error", "Some fields are empty", "All fields are required", Alert.AlertType.ERROR);
            mess.show();
            return;
        }
        int id = Integer.parseInt(customerIDField.getText());
        String name = nameField.getText();
        String address = addressField.getText();
        String postal = postalField.getText();
        String phone = phoneField.getText();
        int divisionId = DivisionMGMT.pullDivisionFromName(divisionBox.getValue()).getDivisionId();

        Customer cust = new Customer(id, name, address, postal, phone,divisionId);
        CustomerMGMT.addCustomer(cust);


        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("Directory.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();

    }

    @FXML
    void cancelAndReturnToDirectory(ActionEvent event) throws IOException {
        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("Directory.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();

    }
    @FXML
    void fillDivisions(ActionEvent event) throws IOException {
        String countr = countryBox.getValue();
        ObservableList<String> diviName = FXCollections.observableArrayList();
        ObservableList<FirstLevelDivisions> divisions = DivisionMGMT.pullAllDivisionsFromCountry(CountryMGMT.pullCountryFromName(countr));

        for (FirstLevelDivisions division : divisions) {
            diviName.add(division.getDivisionName());
        }
       // divisions.forEach(division -> diviName.add(division.getDivisionName()) );
        divisionBox.setItems(diviName);




    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        customerIDField.setText(String.valueOf(CustomerMGMT.generateCustomerID()));



        ObservableList<Country> allCountry = CountryMGMT.pullAllCountries();
        ObservableList<String> countryName = FXCollections.observableArrayList();

        for(Country c : allCountry) {
            countryName.add(c.getCountryName());

        }
        countryBox.setItems(countryName);


    }
}