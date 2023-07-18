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
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.w3c.dom.Text;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ModifyCustomerController  implements Initializable {
    Stage stage;
    Parent scene;

   Customer highlightedCustomer;


    @FXML
    ComboBox<String> divisionBox;
    @FXML
    ComboBox<String> countryBox;
   @FXML
   private TextField customerIDField;

   @FXML
   private TextField nameField;

   @FXML
   private TextField addressField;

   @FXML
   private TextField phoneField;

   @FXML
   private TextField postalField;

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


       CustomerMGMT.deleteCustomer(highlightedCustomer);
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
        highlightedCustomer = DirectoryController.getHighlightedCustomer();
        customerIDField.setText(String.valueOf(highlightedCustomer.getCustomerId()));
        addressField.setText(highlightedCustomer.getAddress());
        nameField.setText(highlightedCustomer.getCustomerName());
        phoneField.setText(highlightedCustomer.getPhone());
        postalField.setText(highlightedCustomer.getPostalCode());
        countryBox.setValue(CountryMGMT.pullCountryFromDivision(highlightedCustomer.getDivisionId()).getCountryName());
        divisionBox.setValue(DivisionMGMT.pullDivisionFromID(highlightedCustomer.getDivisionId()).getDivisionName());


        ObservableList<Country> allCountry = CountryMGMT.pullAllCountries();
        ObservableList<String> countryName = FXCollections.observableArrayList();

        for(Country c : allCountry) {
            countryName.add(c.getCountryName());

        }
        countryBox.setItems(countryName);
    }
}