package com.example.d;

import com.example.d.model.AppointmentMGMT;
import helper.ContactMGMT;
import helper.InstantMessenger;
import helper.timeMGMT;
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

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ResourceBundle;
/**The Modify Appointment controller handles the functionality of the Modify Appointment page for the modification of existing appointments
 * The controller links with ModifyAppointment.fxml*/
public class ModifyAppointmentController implements Initializable {
    Stage stage;
    Parent scene;

    /** appointment highlighted in the appointment table view on the directory page
     * This is appointment that is being modified on the modify appointment page*/

     Appointment highlightedAppointment;

    /**Date picker for starting date of appointment*/
    @FXML
    DatePicker startDateSelection;

    /**Date picker for end date of appointment*/
    @FXML
    DatePicker endDateSelection;



    /**Text field for appointment ID*/
    @FXML
    TextField apptIDField;

    /**Text field for appointment title*/
    @FXML
    TextField titleField;

    /**Text field for appointment description*/


    @FXML
    TextField descriptionField;

    /**Text field for appointment type*/
    @FXML
    TextField typeField;
    /**Text field for appointment location*/

    @FXML
    TextField locationField;


    /**Combo box for selecting contact*/

    @FXML
    ComboBox<String> contactBox;

    /**Combo box for appointment start time*/

    @FXML
    ComboBox<String> startBox;
    /**Combo box for appointment end time*/

    @FXML
    ComboBox<String> endBox;

    /**Text field for user ID*/

    @FXML
    TextField userIDField;
    /**Text field for customer ID */

    @FXML
    TextField customerIDField;

    /**This saves a modified appointment after verifying times are valid(appointment is during business hours and does not overlap with other appointments) and that all fields are not empty.
     * This deletes original appointment prior to saving the modified appointment in its place
     * After successfully saving the new appointment, the user is return to the directory page.
     * @param event is triggered by clicking the save button
     * @throws IOException if the directory page fails to load
     * */

    @FXML
    void saveAndReturn(ActionEvent event) throws IOException {
        if ( userIDField.getText().isEmpty() || apptIDField.getText().isEmpty() || titleField.getText().isEmpty() || customerIDField.getText().isEmpty() || descriptionField.getText().isEmpty() || typeField.getText().isEmpty() || locationField.getText().isEmpty() || contactBox.getValue() == null || startBox.getValue() == null || endBox.getValue() == null )
        {
            InstantMessenger mess = new InstantMessenger( "Error", "Some fields are empty", "All fields are required", Alert.AlertType.ERROR);
            mess.show();
            return;
        }
        int id = Integer.parseInt(apptIDField.getText());
        String title = titleField.getText();
        String description = descriptionField.getText();
        String location = locationField.getText();
        String type = typeField.getText();
        LocalDateTime start = timeMGMT.convertSystemToUTC(startDateSelection.getValue().atTime(LocalTime.parse(startBox.getValue())));
        LocalDateTime end = timeMGMT.convertSystemToUTC(endDateSelection.getValue().atTime(LocalTime.parse(endBox.getValue())));
        int custID = Integer.parseInt(customerIDField.getText());
        int userID = Integer.parseInt(userIDField.getText());
        int contactID = ContactMGMT.convertContactNameToID(contactBox.getValue());

        if (timeValidation(start,end, id)) {
            AppointmentMGMT.deleteAppointment(highlightedAppointment);
            Appointment appt = new Appointment(id, title, description, location, type, start, end, custID, userID, contactID);

            AppointmentMGMT.addAppointment(appt);


            stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            scene = FXMLLoader.load(getClass().getResource("Directory.fxml"));
            stage.setScene(new Scene(scene));
            stage.show();
        }
    }

    /**timeValidation takes the start and end times and the appointmentID. These are used to compare the times of all appointments in the database
     * to ensure that there is no overlap between appointments and that all appointments take place during business hours
     * @param beginning LocalDateTime the start time of the appointment
     * @param end  LocalDateTime the end time of the appointment
     * @param apptID appointmentID of appointment, used to insure appointment is not being compared with itself
     * @return  true if appointment times are valid, else false.*/

    private boolean timeValidation(LocalDateTime beginning, LocalDateTime end, int apptID){
        LocalDateTime estBeginning = timeMGMT.convertUTCToEST(beginning);
        LocalDateTime estEnd = timeMGMT.convertUTCToEST(end);
        LocalTime beginningTime = estBeginning.toLocalTime();
        LocalTime endTime = estEnd.toLocalTime();

        LocalDateTime beginning2 = beginning.minusHours(4);
        LocalDateTime end2 = end.minusHours(4);



        LocalTime startDay = LocalTime.of(8,0,0);
        LocalTime endDay = LocalTime.of(22,0,0);



        for (Appointment appointment : AppointmentMGMT.pullAllAppointments() ) {
            LocalDateTime appStart = appointment.getStart();
            LocalDateTime appEnd = appointment.getEnd();



            boolean isSelectedAppt = apptID == appointment.getAppointmentId();
            boolean beginningOverlap = (beginning2.isAfter(appStart) || beginning2.isEqual(appStart)) && (beginning2.isBefore(appEnd) || beginning2.isEqual(appEnd));
            boolean endOverlap = (end2.isAfter(appStart) || end2.isEqual(appStart)) && (end2.isBefore(appEnd) || end2.isEqual(appEnd));
            boolean containedWithin = (appStart.isAfter(beginning2) && appEnd.isBefore(end2));
            boolean hoursOfOperation = (beginningTime.isAfter(startDay.minusMinutes(1)) && (endTime.isBefore(endDay.plusMinutes(1))));

            if(!isSelectedAppt && beginningOverlap) {
                InstantMessenger message = new InstantMessenger("Error!", "This Appointment overlaps with another appointment on the Schedule", "Please select another time", Alert.AlertType.ERROR );
                message.show();
                return false;
            }
            if(!isSelectedAppt && endOverlap) {
                InstantMessenger message = new InstantMessenger("Error!", "This Appointment overlaps with another appointment on the Schedule", "Please select another time", Alert.AlertType.ERROR );
                message.show();
                return false;
            }

            if(!isSelectedAppt && containedWithin) {
                InstantMessenger message = new InstantMessenger("Error!", "This Appointment overlaps with another appointment on the Schedule", "Please select another time", Alert.AlertType.ERROR );
                message.show();
                return false;
            }

            if(!hoursOfOperation) {
                InstantMessenger message = new InstantMessenger("Error!", "This Appointment falls outside of business hours!", "Please select a time between 8:00 AM and 10:00 PM EST", Alert.AlertType.ERROR );
                message.show();
                return false;

            }
        }
        return true;
    }

    /**This cancels the Modification of an appointment and returns the user to the directory
     * @param event event triggered by clicking cancel button
     * @throws  IOException if the page fails to load*/
    @FXML
    void cancelAndReturnToDirectory(ActionEvent event) throws IOException {
        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("Directory.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();

    }
    /**Initialize sets up the modify appointment page by filling all the text fields and comboBoxes with the values of the highlighted appointment from the appointment table on the directory page
     *
     * fills the combobox for start and end times with options every 15minutes
     * fills contact combobox with all contacts in the database*/
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        highlightedAppointment = DirectoryController.getHighlightedAppointment();
        customerIDField.setText(String.valueOf(highlightedAppointment.getCustomerId()));
        userIDField.setText(String.valueOf(highlightedAppointment.getUserId()));
        apptIDField.setText(String.valueOf(highlightedAppointment.getAppointmentId()));
        descriptionField.setText(highlightedAppointment.getDescription());
        locationField.setText(highlightedAppointment.getLocation());
        titleField.setText(highlightedAppointment.getTitle());
        typeField.setText(highlightedAppointment.getType());
        contactBox.setValue(ContactMGMT.convertContactIDToName(highlightedAppointment.getContactId()));
        startBox.setValue(String.valueOf(highlightedAppointment.getStart().toLocalTime()));
        endBox.setValue(String.valueOf(highlightedAppointment.getEnd().toLocalTime()));
        startDateSelection.setValue(highlightedAppointment.getStart().toLocalDate());
        endDateSelection.setValue(highlightedAppointment.getEnd().toLocalDate());

        LocalTime first = LocalTime.parse("08:00");
        LocalTime last = LocalTime.parse("22:00");
        ObservableList<String> apptOptions = FXCollections.observableArrayList();
        do {
            apptOptions.add(String.valueOf(first));
            first = first.plusMinutes(15);

        }
        while (first.isBefore(last));

        startBox.setItems(apptOptions);
        endBox.setItems(apptOptions);

    }
}