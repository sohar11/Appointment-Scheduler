package helper;

import com.example.d.model.Contact;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**ContactMGMT class handles all of the database interactions relating to contact information in the database*/
public abstract class ContactMGMT {


    /**pullAllContacts
     * interacts with sql database and
     * @return  returns all Contacts in the database*/
    public static ObservableList<Contact> pullAllContacts() {
        try {
                String sql = "select * from contacts;";
            PreparedStatement statement = JDBC.connection.prepareStatement(sql);
            ResultSet result = statement.executeQuery();
            ObservableList<Contact> allContacts = FXCollections.observableArrayList();

            while(result.next())
            {
                String name = result.getString("Contact_Name");
                String email = result.getString("Email");
                int id = result.getInt("Contact_ID");

                Contact stored = new Contact(id, name, email);
                allContacts.add(stored);
            }
            return allContacts;
        }
        catch (SQLException e)
        { System.out.println(e.getMessage());}
        return null;



    }


    /**convertContactNameToID converts the contacts name, a string, to an Integer contactID
     * @param contactName the contact name
     * @return returns the contact ID associated with the contactName*/
    public static int convertContactNameToID(String contactName) {
        try {
            String sql = "select Contact_ID from contacts where Contact_Name = ?";
            PreparedStatement connect = JDBC.connection.prepareStatement(sql);
            connect.setString(1, contactName);

            ResultSet result = connect.executeQuery();
            result.next();
            return result.getInt("Contact_ID");


        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
            return 0;
        }

    }


    /**convertContactIDToName converts from contactid to contact Name
     * @param contactId contact ID
     * @return  returns contact Name associated with contact ID*/

    public static String convertContactIDToName(int contactId)
    { try
          {
            String sql = "select Contact_Name from contacts where Contact_ID = ?";
            PreparedStatement connect = JDBC.connection.prepareStatement(sql);
            connect.setInt(1, contactId);

            ResultSet result = connect.executeQuery();
            result.next();
            return result.getString("Contact_Name");


    }
    catch (SQLException e) {
        System.out.println(e.getMessage());
        return null;
    }}

}
