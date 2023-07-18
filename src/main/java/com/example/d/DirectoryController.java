package com.example.d;


import com.example.d.model.AppointmentMGMT;
import helper.CustomerMGMT;
import helper.InstantMessenger;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

/**The Directory controller handles the logic for the directory page
 * The controller links with Directory.fxml
 * The directory page is the main page after succesfully logging in and contains Appointment and Customer Tables
 * it has buttons to add new appointments, modify existing appointments, to add new customers, modify existing customers, and viewing the various reports
 * all of these buttons navigate to the other pages within the application */

public class DirectoryController implements Initializable {
    Stage stage;
    Parent scene;

    /**Toggle group for filtering appointments by week, month, and all appointments*/
    @FXML
    public ToggleGroup filterToggleGroup;

    /**Table view for the appointments table*/

    @FXML
    public TableView<Appointment> apptTableView;

    /**appt ID column on appt table*/

    @FXML
    public TableColumn<Appointment, Integer> apptIDColumn;

    /**appt title column on appt table*/
    @FXML
    public TableColumn<Appointment, String> apptTitleColumn;

    /**appt description column on appt table */
    @FXML
    public TableColumn<Appointment, String> apptDescriptionColumn;

    /**appt Location column on appt table*/
    @FXML
    public TableColumn<Appointment, String> apptLocationColumn;

    /**appt type column on appt table*/
    @FXML
    public TableColumn<Appointment, String> apptTypeColumn;

    /**appt Start column on appt table */
    @FXML
    public TableColumn<Appointment, LocalDateTime> apptStartColumn;

    /**appt End column on appt table*/
    @FXML
    public TableColumn<Appointment, LocalDateTime> apptEndColumn;

    /**Customer ID column on appt table*/
    @FXML
    public TableColumn<Appointment, Integer> apptCustomerIDColumn;

    /**User ID column on appt table*/
    @FXML
    public TableColumn<Appointment, Integer> apptUserIDColumn;

    /**Contact ID on appt table*/
    @FXML
    public TableColumn<Appointment, Integer> apptContactIDColumn;

    /**Table view that contains the customer Table*/
    @FXML

    public TableView<Customer> customerTableView;

    /**customer ID column on customer table*/
    @FXML
    public TableColumn<Customer, Integer> customerIDColumn;


    /**customer name column on customer table*/
    @FXML
    public TableColumn<Customer, String> customerNameColumn;

    /**Address column on customer table*/
    @FXML
    public TableColumn<Customer, String> customerAddressColumn;

    /**Postal code column on customer table*/
    @FXML
    public TableColumn<Customer, String> customerPostalCodeColumn;

    /**customer phone number column on customer table*/
    @FXML
    public TableColumn<Customer, String> customerPhoneColumn;

    /**Division ID column on customer table
     * State or Province depending on the country*/
    @FXML
    public TableColumn<Customer, Integer> customerDivisionIDColumn;

    /**Getter for appointment selected in the appointments table
     * @return returns the selected appointment for the table*/
    public static Appointment getHighlightedAppointment() {
        return highlightedAppointment;
    }

    /**highlightedAppointment is the appointment selected in the appointment table view
     * Can be used to select an appointment for modification or deletion*/
    private static Appointment highlightedAppointment;

    /**Getter for selected customer from the Customer Table
     * @return returns the selected customer from the table */
    public static Customer getHighlightedCustomer() {
        return highlightedCustomer;
    }

    /**highlightedCustomer is the selected Customer from the Customer Table
     * Can be used for selection in order to modify or delete a customer.*/
    private static Customer highlightedCustomer;

    /**allAppts is the default filter placed on the Appointments Table
     * It displays all Appointments that exist in the database
     * @param event  is the selection of the 'All Appointments' button
     * @throws  IOException if the data fails to load*/
    @FXML
    void allAppts(ActionEvent event) throws IOException {
        apptTableView.setItems(AppointmentMGMT.pullAllAppointments());
        apptIDColumn.setCellValueFactory(new PropertyValueFactory<>("appointmentId"));
        apptTitleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        apptDescriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        apptTypeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
        apptLocationColumn.setCellValueFactory(new PropertyValueFactory<>("location"));
        apptStartColumn.setCellValueFactory(new PropertyValueFactory<>("start"));
        apptEndColumn.setCellValueFactory(new PropertyValueFactory<>("end"));
        apptUserIDColumn.setCellValueFactory(new PropertyValueFactory<>("userId"));
        apptCustomerIDColumn.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        apptContactIDColumn.setCellValueFactory(new PropertyValueFactory<>("contactId"));

    }

    /**apptsByMonth is a filter placed on the Appointments Table
     * It displays all Appointments that exist in the database that occur in this month
     * @param event  is the selection of the 'this Month' button
     * @throws  IOException if the data fails to load*/
    @FXML
    void apptsByMonth(ActionEvent event) throws IOException {
        apptTableView.setItems(AppointmentMGMT.apptThisMonth());
        apptIDColumn.setCellValueFactory(new PropertyValueFactory<>("appointmentId"));
        apptTitleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        apptDescriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        apptTypeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
        apptLocationColumn.setCellValueFactory(new PropertyValueFactory<>("location"));
        apptStartColumn.setCellValueFactory(new PropertyValueFactory<>("start"));
        apptEndColumn.setCellValueFactory(new PropertyValueFactory<>("end"));
        apptUserIDColumn.setCellValueFactory(new PropertyValueFactory<>("userId"));
        apptCustomerIDColumn.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        apptContactIDColumn.setCellValueFactory(new PropertyValueFactory<>("contactId"));

    }

    /**apptsByWEeek is a filter placed on the Appointments Table
     * It displays all Appointments that exist in the database that occur in this week
     * @param event  is the selection of the 'This Week' button
     * @throws  IOException if the data fails to load*/
    @FXML
    void apptsByWeek(ActionEvent event) throws IOException {
        apptTableView.setItems(AppointmentMGMT.apptThisWeek());
        apptIDColumn.setCellValueFactory(new PropertyValueFactory<>("appointmentId"));
        apptTitleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        apptDescriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        apptTypeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
        apptLocationColumn.setCellValueFactory(new PropertyValueFactory<>("location"));
        apptStartColumn.setCellValueFactory(new PropertyValueFactory<>("start"));
        apptEndColumn.setCellValueFactory(new PropertyValueFactory<>("end"));
        apptUserIDColumn.setCellValueFactory(new PropertyValueFactory<>("userId"));
        apptCustomerIDColumn.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        apptContactIDColumn.setCellValueFactory(new PropertyValueFactory<>("contactId"));


    }

 /**openAddAppointment loads the Add Appointment page
  * @param event  is the clicking of the 'Add' button
  * @throws IOException if the page fails to load*/
    @FXML
    void openAddAppointment(ActionEvent event) throws IOException {
        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("AddAppointment.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();

    }

    /**openAddCustomer opens the add customer page
     * @param event is the clicking of the 'Add' button below the customer Table
     * @throws IOException if the page fails to load*/
    @FXML
    void openAddCustomer(ActionEvent event) throws IOException {
        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("AddCustomer.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();

    }
    /**openReports opens the Reports Page
     * @param event is the clicking of the 'Reports' button
     * @throws IOException if the page fails to load*/
    @FXML
    void openReports(ActionEvent event) throws IOException {
        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("Reports.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();

    }
    /**logout closes the directory page and returns the user to the login page
     * @param event is the clicking of the 'logout' button
     * @throws IOException if the page fails to load*/
    @FXML
    void logout(ActionEvent event) throws IOException {
        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("Login.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();

    }
    /**openModifyCustomer opens the Modify customer page
     * a highlightedCustomer must be selected in the customers Table in order to proceed, otherwise user receives error message
     * @param event is clicking the 'modify' button below the customer table
     * @throws IOException if the page fails to load*/

    @FXML
    void openModifyCustomer(ActionEvent event) throws IOException {
        highlightedCustomer = customerTableView.getSelectionModel().getSelectedItem();

        if (highlightedCustomer == null) {
            InstantMessenger message = new InstantMessenger("Error", "No Customer selected", "Please select a Customer", Alert.AlertType.ERROR);
            message.show();
        } else {

            stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            scene = FXMLLoader.load(getClass().getResource("ModifyCustomer.fxml"));
            stage.setScene(new Scene(scene));
            stage.show();

        }
    }

    /**openModifyAppointment opens the Modify appointment page
     * a highlightedAppointment must be selected in the Appointment Table in order to proceed, otherwise user receives error message
     * @param event is clicking the 'modify' button below the Appointments table
     * @throws IOException if the page fails to load*/
    @FXML
    void openModifyAppointment(ActionEvent event) throws IOException {
        highlightedAppointment = apptTableView.getSelectionModel().getSelectedItem();

        if (highlightedAppointment == null) {
            InstantMessenger message = new InstantMessenger("Error", "No Appointment selected!", "Please select an Appointment!", Alert.AlertType.ERROR);
            message.show();
        } else {
            stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            scene = FXMLLoader.load(getClass().getResource("ModifyAppointment.fxml"));
            stage.setScene(new Scene(scene));
            stage.show();

        }
    }
    /**deleteCustomer deletes the highlightedCustomer that is selected in the customer Table
     * if no customer is selected, an error message is displayed
     * @param event is the clicking of the delete button below the customer table
     * @throws IOException if the process fails to complete
     * updates Table view after deletion
     * deletes associated Appointments first
     * displays confirmation message*/
    @FXML
    void deleteCustomer(ActionEvent event) throws IOException {
        highlightedCustomer = customerTableView.getSelectionModel().getSelectedItem();
        if (highlightedCustomer == null) {
            InstantMessenger mess = new InstantMessenger("Error!", "No customer selected!", "Please select a customer for deletion", Alert.AlertType.ERROR);
            mess.show();
            return;
        }

        InstantMessenger message = new InstantMessenger("Please Confirm", "Are you sure you want to delete this customer?", "All connected Appointments will be deleted first", Alert.AlertType.CONFIRMATION);


        if (message.authorize()) {
            ObservableList<Appointment> custAppt = AppointmentMGMT.pullAllApptsbyCustomer(highlightedCustomer);


        for (Appointment appt : custAppt) {
            AppointmentMGMT.deleteAppointment(appt);

        }}
        CustomerMGMT.deleteCustomer(highlightedCustomer);
        customerTableView.setItems(CustomerMGMT.pullAllCustomers());
        customerIDColumn.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        customerNameColumn.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        customerAddressColumn.setCellValueFactory(new PropertyValueFactory<>("address"));
        customerPhoneColumn.setCellValueFactory(new PropertyValueFactory<>("phone"));
        customerDivisionIDColumn.setCellValueFactory(new PropertyValueFactory<>("divisionId"));
        customerPostalCodeColumn.setCellValueFactory(new PropertyValueFactory<>("postalCode"));


    }
    /**deleteAppt deletes a highlightedAppointment that is selected in the Appointment table view
     * An appointment must be selected, or an error message will be displayed
     * @param event is the clicking of the delete button below the Appointment table
     * @throws IOException if the process fails
     * displays message after successful deletion of Appointment*/
    @FXML
    void deleteAppt(ActionEvent event) throws IOException {
        highlightedAppointment = apptTableView.getSelectionModel().getSelectedItem();
        if(highlightedAppointment == null) {
            InstantMessenger notification = new InstantMessenger("Error", "No appointment selected", "Please select an appointment prior to deletion", Alert.AlertType.ERROR);
            notification.show();
            return;
        }

        AppointmentMGMT.deleteAppointment(highlightedAppointment);
        String t = "Appointment Deleted";
        String h = "Appointment " + highlightedAppointment.getAppointmentId() + " deleted";
        String c = "Deleted Appointment Type was " + highlightedAppointment.getType();
        InstantMessenger complete = new InstantMessenger(t, h, c, Alert.AlertType.INFORMATION);
        complete.show();
        apptTableView.setItems(AppointmentMGMT.pullAllAppointments());
        apptIDColumn.setCellValueFactory(new PropertyValueFactory<>("appointmentId"));
        apptTitleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        apptDescriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        apptTypeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
        apptLocationColumn.setCellValueFactory(new PropertyValueFactory<>("location"));
        apptStartColumn.setCellValueFactory(new PropertyValueFactory<>("start"));
        apptEndColumn.setCellValueFactory(new PropertyValueFactory<>("end"));
        apptUserIDColumn.setCellValueFactory(new PropertyValueFactory<>("userId"));
        apptCustomerIDColumn.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        apptContactIDColumn.setCellValueFactory(new PropertyValueFactory<>("contactId"));
    }

    public static ObservableList<Appointment> appts;
    private void updateApptTime()  {


        for (Appointment appt: appts)
        {
            LocalDateTime UTCStartTime = appt.getStart();
            LocalDateTime UTCEndTime = appt.getEnd();
            appt.setStart(UTCStartTime);
            appt.setEnd(UTCEndTime);
        }

        apptTableView.setItems(appts);
        apptIDColumn.setCellValueFactory(new PropertyValueFactory<>("appointmentId"));
        apptTitleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        apptDescriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        apptTypeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
        apptLocationColumn.setCellValueFactory(new PropertyValueFactory<>("location"));
        apptStartColumn.setCellValueFactory(new PropertyValueFactory<>("start"));
        apptEndColumn.setCellValueFactory(new PropertyValueFactory<>("end"));
        apptUserIDColumn.setCellValueFactory(new PropertyValueFactory<>("userId"));
        apptCustomerIDColumn.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        apptContactIDColumn.setCellValueFactory(new PropertyValueFactory<>("contactId"));
    }


   /**initialize sets up the directory page
    * it populates both tables with all of the appointments and customers in the database*/
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        appts = AppointmentMGMT.pullAllAppointments();
        updateApptTime();



        customerTableView.setItems(CustomerMGMT.pullAllCustomers());
        customerIDColumn.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        customerNameColumn.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        customerAddressColumn.setCellValueFactory(new PropertyValueFactory<>("address"));
        customerPhoneColumn.setCellValueFactory(new PropertyValueFactory<>("phone"));
        customerDivisionIDColumn.setCellValueFactory(new PropertyValueFactory<>("divisionId"));
        customerPostalCodeColumn.setCellValueFactory(new PropertyValueFactory<>("postalCode"));

    }
}