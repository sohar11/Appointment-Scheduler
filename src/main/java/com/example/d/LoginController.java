package com.example.d;


import helper.InstantMessenger;
import helper.LoginConfirmation;
import com.example.d.model.AppointmentMGMT;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Locale;
import java.util.ResourceBundle;
/**LoginController controls the logic of the login page
 * it links with Login.fxml
 * a username and password that is within the SQL database is required for login*/
public class LoginController implements Initializable {
    Stage stage;
    Parent scene;

    /**login initiates the login process using the information contained within the two text fields
     * A filewriter logs all login attempts to login_activity.txt
     * When issues are encountered, error messages are displayed
     * The log in page automatically detects the Users location based on timezone settings
     * The page is automatically translated to French when necessary
     * @param event is the clicking of the log in button
     * @throws IOException when the process fails to load
     * after successful login, the user is redirected to the Directory page
     * calls checkAppts to check for upcoming appointments following login*/
    @FXML
    void login(ActionEvent event) throws IOException {

        if (LoginConfirmation.confirmed(userNameText.getText(), passwordText.getText())) {
            try {
                FileWriter logs = new FileWriter("login_activity.txt", true);
                PrintWriter outputFile = new PrintWriter(logs);
                outputFile.println("Log in attempted by: " + userNameText.getText() + " at " + LocalDateTime.now() + " SUCCESSFUL!");
                outputFile.close();
            }

            catch (IOException e) {
                System.out.println("Error occurred while writing file!");
                e.printStackTrace();
            }

        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("Directory.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
        checkAppts();

    }
        else {
            String error;
            String info;
            String call;
            if(Locale.getDefault().toString().equals("en_US")) {
                error = "ERROR!";
                call = "Please Try again";
                info = "Username or Password is incorrect!";
            }
            else {
                error = "Erreur";
                call = "Veuillez réessayer";
                info = "Le nom d’utilisateur ou le mot de passe est incorrect";

            }
            InstantMessenger message = new InstantMessenger(error, call, info, Alert.AlertType.ERROR);
            message.show();

            try {
                FileWriter logs = new FileWriter("login_activity.txt", true);
                PrintWriter outputFile = new PrintWriter(logs);
                outputFile.println(" FAILED Log in attempted by: " + userNameText.getText() + " at " + LocalDateTime.now());
                outputFile.close();
            }

            catch (IOException e) {
                System.out.println("Error occurred while writing file!");
                e.printStackTrace();
            }

        }

    }
    /**Label to output the users Timezone information*/
    @FXML
    private Label tzOutputLabel ;


    /**Label for the Timezone*/

    @FXML
    private Label  timezoneLabel;


    /**label for the login header*/


    @FXML
    private Label loginLabel ;

    /**label for the user name field */

    @FXML
    private Label  userNameLabel;
    /**label for the password field*/

    @FXML
    private Label passwordLabel ;

    /**the login button triggers login*/


    @FXML
    private Button  loginButtonLabel;

    /**exit button triggers closeApplication*/

    @FXML
    private Button  exitButtonLabel;

    /**Text field to input password*/

    @FXML
    private TextField passwordText;

    /** Text field to input username*/


    @FXML
    private TextField userNameText;

    /**An observable list of all appointments that is used to notify the user of upcoming appointments after a succesful login*/


    public static ObservableList<Appointment> appts;


    /** closes the application */

    @FXML
    void closeApplication(ActionEvent event)  {
        System.exit(0);
    }

    /**checkAppts is called at the end of the login process
     * it checks to see whether there are any upcoming appointments within 15minutes of the login and displays a message*/

    private void checkAppts()
    {
        String t;
        String h;
        String c;
        for(Appointment appointment : appts)
        {
            LocalDateTime begin = appointment.getStart();
            LocalDateTime current = LocalDateTime.now();
            boolean next15 = begin.isAfter(current) && (current.plusMinutes(15).isEqual(begin) || current.plusMinutes(15).isAfter(begin)) ;

            if (next15)
            {
                t = "Notification";
                h = "User " + appointment.getUserId() + " has an upcoming appointment soon.";
                c = "Appointment " + appointment.getAppointmentId() + " has a start time of " + appointment.getStart();
                InstantMessenger message = new InstantMessenger(t,h,c, Alert.AlertType.INFORMATION);
                message.show();
                return;
            }

        }
        InstantMessenger m = new InstantMessenger("Notification", "No Upcoming appointments!", "Within the next 15 minutes.", Alert.AlertType.INFORMATION);
        m.show();

    }

    /** initialize sets up the login page
     * its uses the english and french language bundles to translate the login page depending upon the appropriate locale*/


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            Locale locale = Locale.getDefault();
            Locale.setDefault(locale);
            System.out.println((locale));
            ZoneId timezone = ZoneId.systemDefault();
            tzOutputLabel.setText(String.valueOf(timezone));
            rb = ResourceBundle.getBundle("lang", Locale.getDefault());
            loginLabel.setText(rb.getString("login"));
            loginButtonLabel.setText(rb.getString("login"));
            userNameLabel.setText(rb.getString("username"));
            passwordLabel.setText(rb.getString("password"));
            exitButtonLabel.setText(rb.getString("exit"));
            timezoneLabel.setText(rb.getString("timezone"));
            appts = AppointmentMGMT.pullAllAppointments();


        }
        catch(Exception e) {System.out.println(e);}

    }
}