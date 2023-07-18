package helper;


import com.example.d.Customer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


/**CustomerMGMT manages the database interactions relating to the customer information in the database*/
public class CustomerMGMT {


    /**pullAllCustomers pulls all of the Customer information in the database
     * @return returns all customers in the database*/
    public static ObservableList<Customer> pullAllCustomers() {
        try {
            String sql = "select * from customers;";
            PreparedStatement statement = JDBC.connection.prepareStatement(sql);
            ResultSet result = statement.executeQuery();
            ObservableList<Customer> allCustomers = FXCollections.observableArrayList();

            while(result.next())
            {
                String name = result.getString("Customer_Name");
                String address = result.getString("Address");
                String postal = result.getString("Postal_Code");
                String phone = result.getString("Phone");
                int id = result.getInt("Customer_ID");
                int divisionId = result.getInt("Division_ID");

                Customer stored = new Customer(id,name,address,postal,phone,divisionId);
                allCustomers.add(stored);
            }
            return allCustomers;
        }
        catch (SQLException e)
        { System.out.println(e.getMessage());}
        return null;



    }



    /**addCustomer takes a customer and adds them to the database
     * @param cust customer to be added to the database*/
    public static void addCustomer(Customer cust) {
        try {
            String sql = "insert into Customers (Customer_ID, Customer_Name, Address, Postal_Code, Phone, Division_ID)" + "values(?,?,?,?,?,?);";
            PreparedStatement add = JDBC.connection.prepareStatement(sql);
            add.setString(1, Integer.toString(cust.getCustomerId()));
            add.setString(2, cust.getCustomerName());
            add.setString(3, cust.getAddress());
            add.setString(4, cust.getPostalCode() );
            add.setString(5, cust.getPhone());
            add.setInt(6, cust.getDivisionId());
            add.executeUpdate();

        }
        catch (SQLException e)
        { System.out.println(e.getMessage());}
    }


    /**deleteCustomer deletes a customer from the database
     * @param cust the customer to be deleted from the database*/
    public static void deleteCustomer(Customer cust) {
        try {
            String sql = "delete from customers where Customer_ID = ?";

            PreparedStatement del = JDBC.connection.prepareStatement(sql);
            del.setString(1,Integer.toString(cust.getCustomerId()));
            del.executeUpdate();

        }
        catch (SQLException e)
        { System.out.println(e.getMessage());}
    }



    /**generateCustomerID handles the autogeneration of Customer IDs
     * @return returns new customer ID */
    public static int generateCustomerID() {
        try {
            String sql = "select customer_ID from customers order by customer_ID desc limit 1;";
            PreparedStatement message = JDBC.connection.prepareStatement(sql);
            ResultSet result = message.executeQuery();
            result.next();
            int newiD = result.getInt("customer_id") + 1;
            return  newiD;

        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
            return 0;
        }
    }


    /**pullAllCustomersByCountry pulls all customers in the database within a particular country
     * @param countryID  the country to search for all of the customers within
     * @return returns all customers in the selected country*/
    public static ObservableList<Customer> pullAllCustomersByCountry(int countryID)
    {
        try {
            String sql = "select customers.* from customers inner join first_level_divisions on first_level_divisions.division_id = customers.division_id where first_level_divisions.country_id = ?;";
            PreparedStatement message = JDBC.connection.prepareStatement(sql);
            message.setString(1, Integer.toString(countryID));

            ResultSet result = message.executeQuery();
            ObservableList<Customer> resultCustomers = FXCollections.observableArrayList();

            while(result.next())
            {
                String name = result.getString("Customer_Name");
                String address = result.getString("Address");
                String postal = result.getString("Postal_Code");
                String phone = result.getString("Phone");
                int id = result.getInt("Customer_ID");
                int divisionId = result.getInt("Division_ID");

                Customer stored = new Customer(id,name,address,postal,phone,divisionId);
                resultCustomers.add(stored);
            }
            return resultCustomers;
        }
        catch (SQLException e)
        { System.out.println(e.getMessage());}
        return null;

        }

    }
