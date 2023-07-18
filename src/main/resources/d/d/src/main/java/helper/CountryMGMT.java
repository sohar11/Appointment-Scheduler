package helper;

import com.example.d.Country;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**CountryMGMT manages all of the database interactions related to the Country information in the database*/
public class CountryMGMT {


    /**pullAllCountries
     * pulls all of the country information contained in the database
     * @return  returns all of the Countries in the database*/
    public static ObservableList<Country> pullAllCountries() {
        try {
            String sql = "select * from countries";
            PreparedStatement statement = JDBC.connection.prepareStatement(sql);
            ResultSet result = statement.executeQuery();
            ObservableList<Country> allCountries = FXCollections.observableArrayList();

            while (result.next()) {
                int id = result.getInt("Country_ID");
                String name = result.getString("Country");

                Country cont = new Country(id, name);
                allCountries.add(cont);
            }
            return allCountries;
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
            return null;
        }
    }


    /**pullCountryFromName pulls countries from the database
     * @param name is the name of country that should be pulled from the database, String
     * @return returns country associated with name*/
    public static Country pullCountryFromName(String name){
        try {
            Country cont = null;
            String sql = "select * from countries where country = ?";
            PreparedStatement message = JDBC.connection.prepareStatement(sql);
            message.setString(1,name);
            ResultSet result = message.executeQuery();

            while(result.next()){
                int id = result.getInt("Country_ID");
                String nameC = result.getString("Country");
                cont = new Country(id, nameC);
            }
            return cont;

        }
        catch (SQLException e){
            System.out.println(e.getMessage());
            return null;
        }
    }


    /**pullCountryFromDivision selects Country from database based on division ID
     * @param division Integer, division ID
     * @return returns country associated with division ID */
    public static Country pullCountryFromDivision(int division){
        try {
            Country cont = null;
            String sql = "select * from countries where country_ID = ?";
            PreparedStatement message = JDBC.connection.prepareStatement(sql);
            message.setInt(1, DivisionMGMT.pullDivisionFromID(division).getCountryId());
            ResultSet result = message.executeQuery();

            while(result.next()){
                int id = result.getInt("Country_ID");
                String nameC = result.getString("Country");
                cont = new Country(id, nameC);
            }
            return cont;

        }
        catch (SQLException e){
            System.out.println(e.getMessage());
            return null;
        }
    }
}
