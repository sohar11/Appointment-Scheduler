package helper;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.util.Optional;


/**The Instant Messenger class hold the logic for displaying various alerts throughout the application*/

public class InstantMessenger {



    /**Alert note sets up Alert*/

    private Alert note;


    /**Instant messenger constructor
     * @param h header of the Instant Message, String
     * @param t title of the Instant Message, String
     * @param c content of the Instant Message, String
     * @param type Alert type for displaying the message*/

    public InstantMessenger(String t,String h, String c, Alert.AlertType type) {
        note = new Alert(type);
        note.setTitle(t);
        note.setHeaderText(h);
        note.setContentText(c);
    }


    /**show displays the message on screen for the user*/

    public void show() {
        note.showAndWait();
    }


    /**authorize is for displaying messages to the user that require confirmation
     * @return  returns true if user confirms, otherwise returns false*/
    public boolean authorize() {
        Optional<ButtonType> confirm = note.showAndWait();
        if(confirm.isPresent() && confirm.get() == ButtonType.OK) {
            return true;
        }
        else { return false;  }
    }
}
