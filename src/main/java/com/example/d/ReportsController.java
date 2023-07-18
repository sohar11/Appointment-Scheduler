package com.example.d;

import com.example.d.model.AppointmentMGMT;
import com.example.d.model.Contact;
import helper.*;
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
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.ResourceBundle;
/**The ReportsController controls the logic for the reports page
 * its links with Reports.fxml*/

public class ReportsController implements Initializable {
  Stage stage;
  Parent scene;

  /**contactBox comboBox contains the contacts in the database
   * used for select contacts to view their appointments*/

  @FXML
  public ComboBox<String> contactBox;

    /**monthBox ComboBox contains all the months in the year
     * used to select months to search by month and appointment type*/

  @FXML
  public ComboBox<String> monthBox;
    /**typeBox ComboBox contains all types of appointments
     * used to select type to search by month and appointment type*/
  @FXML
  public ComboBox<String> typeBox;

    /**countryBox ComboBox contains all countries in the database
     * used to select country to search customers by country*/

  @FXML
  public ComboBox<String> countryBox;

    /**Observable list that contains all appointments*/

  public static ObservableList<Appointment> appts;
    /**appointment Table view for searching by month and type*/
    @FXML
    public TableView<Appointment> apptTableView;

    /**apptId column */

    @FXML
    public TableColumn<Appointment, Integer> apptIDColumn;

    /**appt title column*/
    @FXML
    public TableColumn<Appointment, String> apptTitleColumn;

    /**appt description column*/
    @FXML
    public TableColumn<Appointment, String> apptDescriptionColumn;

    /** appt Type Column*/

    @FXML
    public TableColumn<Appointment, String> apptTypeColumn;

    /**appt Start Column*/
    @FXML
    public TableColumn<Appointment, LocalDateTime> apptStartColumn;

    /**appt End Column*/
    @FXML
    public TableColumn<Appointment, LocalDateTime> apptEndColumn;
    /** appt Customer ID Column*/
    @FXML
    public TableColumn<Appointment, Integer> apptCustomerIDColumn;

    /**appt Table view for searching by contact*/

    @FXML
    public TableView<Appointment> apptTableView2;
    /**appt ID column for 2nd table*/
    @FXML
    public TableColumn<Appointment, Integer> apptIDColumn2;

    /**appt Title column for 2nd table*/
    @FXML
    public TableColumn<Appointment, String> apptTitleColumn2;

    /**appt Description column for 2nd table*/
    @FXML
    public TableColumn<Appointment, String> apptDescriptionColumn2;

    /**appt Type column for 2nd table*/

    @FXML
    public TableColumn<Appointment, String> apptTypeColumn2;

    /**appt Start Column for 2nd table*/
    @FXML
    public TableColumn<Appointment, LocalDateTime> apptStartColumn2;

    /**appt End Column for 2nd table*/
    @FXML
    public TableColumn<Appointment, LocalDateTime> apptEndColumn2;

    /**appt Customer ID for 2nd table*/

    @FXML
    public TableColumn<Appointment, Integer> apptCustomerIDColumn2;

    /**Customer Table view for searching by country*/

    @FXML
    public TableView<Customer> customerTableView;

    /** customer Name column for customer table*/
    @FXML
    public TableColumn<Customer, String> customerNameColumn;
    /**customer address column for customer table*/
    @FXML
    public TableColumn<Customer, String> customerAddressColumn;

    /** customer Postal code column for customer table*/
    @FXML
    public TableColumn<Customer, String> customerPostalCodeColumn;

    /** customer division ID column for customer table*/
    @FXML
    public TableColumn<Customer, Integer> customerDivisionIDColumn;

    /** customer phone number column for customer table*/
    @FXML
    public TableColumn<Customer, String> customerPhoneColumn;

    /**customer ID column for customer table*/

    @FXML
    public TableColumn<Customer, Integer> customerIDColumn;





    /**logout exits the reports page and returns to the login screen
     * @param event clicking the logout button
     * @throws  IOException if page fails to load*/
    @FXML
    void logout(ActionEvent event) throws IOException {
        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("Login.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();

    }

    /**cancelAndReturnToDirectory leaves the report page and returns to the directory page
     * @param event is clicking the 'back' button
     * @throws IOException if the page fails to load*/
    @FXML
    void cancelAndReturnToDirectory(ActionEvent event) throws IOException {
        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("Directory.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();

    }

    /**contactReport fills the 2nd table with all of the appointments related to a selected contact
     * @param event is the clicking of the 'GO' button by the 2nd table
     * @throws IOException if the process fails
     * it takes the contact value from the contactBox, displays an error if none is selected, and fills the table with the appointment information*/
    @FXML
    void contactReport(ActionEvent event) throws IOException {
        String name = contactBox.getValue();

        if (name == null){
            InstantMessenger message = new InstantMessenger("Error!", "No Contact Selected", "Please select a Contact", Alert.AlertType.ERROR);
            message.show();
            return;
        }
        int contactId = ContactMGMT.convertContactNameToID(name);
        appts = AppointmentMGMT.pullAllApptsbyContactID(contactId);


        apptTableView.setItems(appts);
        apptIDColumn.setCellValueFactory(new PropertyValueFactory<>("appointmentId"));
        apptTitleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        apptDescriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        apptTypeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
        apptStartColumn.setCellValueFactory(new PropertyValueFactory<>("start"));
        apptEndColumn.setCellValueFactory(new PropertyValueFactory<>("end"));
        apptCustomerIDColumn.setCellValueFactory(new PropertyValueFactory<>("customerId"));




    }

    /**monthAndTypeReport fills the first table with appointments of the selected Type and from the selected month
     * uses the monthBox and typeBox to get selected values
     * if either box is empty, error messages are displayed
     * @param event  is the clicking of the 'Go' button beside the first table
     * @throws IOException if the process fails to load*/
    @FXML
    void monthAndTypeReport(ActionEvent event) throws IOException {
        String month = monthBox.getValue();
        String type = typeBox.getValue();
        if (month == null || type == null)
        {
            InstantMessenger message = new InstantMessenger("Error!", "Month and Type required", "Please select a type and a month!", Alert.AlertType.ERROR);
            message.show();
            return;

        }
        appts = AppointmentMGMT.pullAllApptsbyMonthAndType(month,type);
        apptTableView2.setItems(appts);
        apptIDColumn2.setCellValueFactory(new PropertyValueFactory<>("appointmentId"));
        apptTitleColumn2.setCellValueFactory(new PropertyValueFactory<>("title"));
        apptDescriptionColumn2.setCellValueFactory(new PropertyValueFactory<>("description"));
        apptTypeColumn2.setCellValueFactory(new PropertyValueFactory<>("type"));
        apptStartColumn2.setCellValueFactory(new PropertyValueFactory<>("start"));
        apptEndColumn2.setCellValueFactory(new PropertyValueFactory<>("end"));
        apptCustomerIDColumn2.setCellValueFactory(new PropertyValueFactory<>("customerId"));
    }

    /**countryReport fills the third table with customers from the selected country
     * uses the countryBox to select countries
     * displays error messages if no country is selected
     * @param event clicking the 'Go' button beside the table
     * @throws IOException if the process fails to load*/
    @FXML
    void countryReport(ActionEvent event) throws IOException {
        String name = countryBox.getValue();

        if (name == null){
            InstantMessenger message = new InstantMessenger("Error!", "No Country Selected", "Please select a Country", Alert.AlertType.ERROR);
            message.show();
            return;
        }
        int countryId = CountryMGMT.pullCountryFromName(name).getCountryId();
        ObservableList<Customer> customers = CustomerMGMT.pullAllCustomersByCountry(countryId);


        customerTableView.setItems(customers);
        customerNameColumn.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        customerAddressColumn.setCellValueFactory(new PropertyValueFactory<>("address"));
        customerPostalCodeColumn.setCellValueFactory(new PropertyValueFactory<>("postalCode"));
        customerDivisionIDColumn.setCellValueFactory(new PropertyValueFactory<>("divisionId"));
        customerPhoneColumn.setCellValueFactory(new PropertyValueFactory<>("phone"));
        customerIDColumn.setCellValueFactory(new PropertyValueFactory<>("customerId"));




    }

    /**initialize sets up the reports page by populating the comboBoxes that are used for selecting the search criteria in the 3 different reports
     * initialize uses 3 Lambda expressions in order to fill the comboBoxes with the necessary values
     * each of the Lambda expressions are used for parsimony and efficiency as they reduce the number of Observable lists that need to be created within the class
     * and enable the user to be able to easily adapt the reports to other possible inquiries of interest*/
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<Contact> contacts = ContactMGMT.pullAllContacts();
        ObservableList<String> contactName = FXCollections.observableArrayList();

        contacts.forEach(contact -> contactName.add(contact.getContactName()) );
        contactBox.setItems(contactName);

        ObservableList<String> months = FXCollections.observableArrayList("Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec");
        ObservableList<String> monthsName = FXCollections.observableArrayList();

        months.forEach(month -> monthsName.add(month));
        monthBox.setItems(monthsName);

        ObservableList<String> types = AppointmentMGMT.pullAllAppointmentTypes();
        typeBox.setItems(types);

        ObservableList<Country> countries = CountryMGMT.pullAllCountries();
        ObservableList<String> countriesName = FXCollections.observableArrayList();

        countries.forEach(country -> countriesName.add(country.getCountryName()));
        countryBox.setItems(countriesName);



    }
}
