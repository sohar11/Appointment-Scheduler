package helper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**LoginConfirmation handles the login interaction with the SQL database
 * takes username and password and confirms that they match username and password contain in SQL database*/
public abstract class LoginConfirmation {


    /**confirmed confirms the username and password match an entry for username and password in the database
     * @param password password that is inputted into password field, String
     * @param username username inputted into username field, String
     * @return returns true if username and password match database, otherwise it returns false*/
    public static boolean confirmed(String username, String password) {
        try {
            String sql = "select * from users where User_Name = ? and password = ?";
            PreparedStatement connect = JDBC.connection.prepareStatement(sql);
            connect.setString(1, username);
            connect.setString(2, password);
            ResultSet result = connect.executeQuery();
            result.next();


            return result.getString("User_Name").equals(username) && result.getString("password").equals(password);
        } catch (SQLException e) {
            return false;
        }







        }
    }

