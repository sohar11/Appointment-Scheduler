package helper;

import com.example.d.Country;
import com.example.d.model.FirstLevelDivisions;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


/**DivisionMGMT manages interactions with the database relating to division information*/

public abstract class DivisionMGMT {


    /**pullAllDivisionsFromCountry pulls all of the States/Provinces within the inputted country
     * @param country country to pull all of division information from
     * @return returns all divisions within the particular country*/
    public static ObservableList<FirstLevelDivisions> pullAllDivisionsFromCountry(Country country){
        try {
            String sql = "select * from first_level_divisions where country_ID = ?;";
            PreparedStatement message = JDBC.connection.prepareStatement(sql);
            message.setInt(1, country.getCountryId());
            ResultSet result = message.executeQuery();
            ObservableList<FirstLevelDivisions> allDivisions =FXCollections.observableArrayList();


            while (result.next()){
                int id = result.getInt("Division_ID");
                int countryID = result.getInt("Country_ID");
                String name = result.getString("Division");

                FirstLevelDivisions div = new FirstLevelDivisions(id, countryID, name);
                allDivisions.add(div);

            }
            return allDivisions;

        }
        catch (SQLException e){
            System.out.println(e.getMessage());
            return null;
        }
    }


    /**pullDivisionFromID pulls all of the division information by searching via division ID
     * @param division  the division to be searched for, division ID, integer
     * @return returns all division information associated with division id */
    public static FirstLevelDivisions pullDivisionFromID(int division){
        try {
            FirstLevelDivisions div = null;
            String sql = "select * from first_level_divisions where division_ID = ?;";
            PreparedStatement message = JDBC.connection.prepareStatement(sql);
            message.setInt(1, division);
            ResultSet result = message.executeQuery();



            while (result.next()){
                int id = result.getInt("Division_ID");
                int countryID = result.getInt("Country_ID");
                String name = result.getString("Division");

                 div = new FirstLevelDivisions(id, countryID, name);


            }
            return div;

        }
        catch (SQLException e){
            System.out.println(e.getMessage());
            return null;
        }
    }


    /**pullDivisionFromName pulls all divisions from a division name
     * @param division the division to be searched for
     * @return  returns all division information*/
    public static FirstLevelDivisions pullDivisionFromName(String division){
        try {
            FirstLevelDivisions div = null;
            String sql = "select * from first_level_divisions where division= ?";
            PreparedStatement message = JDBC.connection.prepareStatement(sql);
            message.setString(1, division);
            ResultSet result = message.executeQuery();



            while (result.next()){
                int id = result.getInt("Division_ID");
                int countryID = result.getInt("Country_ID");
                String name = result.getString("Division");

                div = new FirstLevelDivisions(id, countryID, name);


            }
            return div;

        }
        catch (SQLException e){
            System.out.println(e.getMessage());
            return null;
        }
    }


}
