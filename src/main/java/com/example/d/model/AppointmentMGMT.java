package com.example.d.model;

import com.example.d.Appointment;
import com.example.d.Customer;
import helper.JDBC;
import helper.timeMGMT;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


import java.sql.*;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.time.temporal.TemporalAccessor;

public class AppointmentMGMT {
    public static void addAppointment(Appointment appt) {
        try {
            String sql = "insert into appointments (Appointment_ID, Title, Description, Location, Type, Start, End, Customer_ID, User_ID, Contact_ID)" + "values(?,?,?,?,?,?,?,?,?,?);";
            PreparedStatement add = JDBC.connection.prepareStatement(sql);
            add.setString(1, Integer.toString(appt.getAppointmentId()));
            add.setString(2, appt.getTitle());
            add.setString(3, appt.getDescription());
            add.setString(4, appt.getLocation());
            add.setString(5, appt.getType());
            add.setString(6, String.valueOf(appt.getStart()));
            add.setString(7, String.valueOf(appt.getEnd()));
            add.setString(8, Integer.toString(appt.getCustomerId()));
            add.setString(9, Integer.toString(appt.getUserId()));
            add.setString(10, Integer.toString(appt.getContactId()));
            add.executeUpdate();

        }
        catch (SQLException e)
        { System.out.println(e.getMessage());}
    }

    public static void deleteAppointment(Appointment appt) {
        try {
            String sql = "delete from appointments where Appointment_ID = ?";

            PreparedStatement del = JDBC.connection.prepareStatement(sql);
            del.setString(1,Integer.toString(appt.getAppointmentId()));
            del.executeUpdate();

        }
        catch (SQLException e)
        { System.out.println(e.getMessage());}
    }

    public static ObservableList<Appointment> pullAllAppointments() {
        try {
            String sql = "select * from appointments;";
            PreparedStatement statement = JDBC.connection.prepareStatement(sql);
            ResultSet result = statement.executeQuery();
            ObservableList<Appointment> allAppointments = FXCollections.observableArrayList();

            while(result.next())
            {
                int appointmentID = result.getInt("Appointment_ID");
                String title = result.getString("Title");
                String description = result.getString("Description");
                String location = result.getString("Location");
                String type = result.getString("Type");


                Date d = new Date(result.getDate("Start").getTime());
                Time t = new Time(result.getTime("Start").getTime());
                String newTime = (d.toString() + "T" + t.toString());
                Date e = new Date(result.getDate("End").getTime());
                Time f = new Time(result.getTime("End").getTime());
                String newEnd = (e.toString() + "T" + f.toString());


                LocalDateTime newp = LocalDateTime.parse(newTime);
                LocalDateTime newt = LocalDateTime.parse(newEnd);


                LocalDateTime start = timeMGMT.convertUTCToSystem(newp);
                LocalDateTime end = timeMGMT.convertUTCToSystem(newt);


                int customerId = result.getInt("Customer_ID");
                int userId = result.getInt("User_ID");
                int contactId = result.getInt("Contact_ID");

                Appointment stored = new Appointment(appointmentID, title, description, location, type, start, end, customerId, userId, contactId);
                allAppointments.add(stored);
            }
            return allAppointments;
        }
        catch (SQLException e)
        { System.out.println(e.getMessage());}
        return null;
    }

    public static ObservableList<Appointment> pullAllApptsbyCustomer(Customer cust) {
        try {
            String sql = "select * from appointments where Customer_ID = ?";

            PreparedStatement statement = JDBC.connection.prepareStatement(sql);
            statement.setString(1, Integer.toString(cust.getCustomerId()));
            ResultSet result = statement.executeQuery();
            ObservableList<Appointment> resultAppt = FXCollections.observableArrayList();

            while(result.next()) {
                int id = result.getInt("Appointment_ID");
                String title = result.getString("Title");
                String location = result.getString("Location");
                String description = result.getString("Description");
                String type = result.getString("Type");
                int custId = result.getInt("Customer_ID");
                int userId = result.getInt("User_ID");
                int contactId = result.getInt("Contact_ID");

                Date d = new Date(result.getDate("Start").getTime());
                Time t = new Time(result.getTime("Start").getTime());
                String newTime = (d.toString() + "T" + t.toString());
                Date e = new Date(result.getDate("End").getTime());
                Time f = new Time(result.getTime("End").getTime());
                String newEnd = (e.toString() + "T" + f.toString());


                LocalDateTime newp = LocalDateTime.parse(newTime);
                LocalDateTime newt = LocalDateTime.parse(newEnd);


                LocalDateTime start = timeMGMT.convertUTCToSystem(newp);
                LocalDateTime end = timeMGMT.convertUTCToSystem(newt);


                Appointment appt = new Appointment(id, title, description, location, type, start, end, custId, userId, contactId);
                resultAppt.add(appt);


            }
            return resultAppt;
        }
        catch (SQLException e)
        { System.out.println(e.getMessage());}
        return null;
    }

    public static ObservableList<Appointment> apptThisWeek() {
        try {
            String sql = "select * from appointments where start between curdate() and date_add(curdate(), interval 1 week);" ;
            PreparedStatement mess = JDBC.connection.prepareStatement(sql);
            ResultSet result = mess.executeQuery();
            ObservableList<Appointment> resultAppt = FXCollections.observableArrayList();

            while(result.next()) {
                int id = result.getInt("Appointment_ID");
                String title = result.getString("Title");
                String location = result.getString("Location");
                String description = result.getString("Description");
                String type = result.getString("Type");
                int custId = result.getInt("Customer_ID");
                int userId = result.getInt("User_ID");
                int contactId = result.getInt("Contact_ID");

                Date d = new Date(result.getDate("Start").getTime());
                Time t = new Time(result.getTime("Start").getTime());
                String newTime = (d.toString() + "T" + t.toString());
                Date e = new Date(result.getDate("End").getTime());
                Time f = new Time(result.getTime("End").getTime());
                String newEnd = (e.toString() + "T" + f.toString());


                LocalDateTime newp = LocalDateTime.parse(newTime);
                LocalDateTime newt = LocalDateTime.parse(newEnd);


                LocalDateTime start = timeMGMT.convertUTCToSystem(newp);
                LocalDateTime end = timeMGMT.convertUTCToSystem(newt);

                Appointment appt = new Appointment(id, title, description, location, type, start, end, custId, userId, contactId);
                resultAppt.add(appt);


            }
            return resultAppt;
        }


        catch (SQLException e)
        { System.out.println(e.getMessage());}
        return null;
    }

    public static ObservableList<Appointment> apptThisMonth() {
        try {
            String sql = "select * from appointments where start between curdate() and date_add(curdate(), interval 1 month);" ;
            PreparedStatement mess = JDBC.connection.prepareStatement(sql);
            ResultSet result = mess.executeQuery();
            ObservableList<Appointment> resultAppt = FXCollections.observableArrayList();

            while(result.next()) {
                int id = result.getInt("Appointment_ID");
                String title = result.getString("Title");
                String location = result.getString("Location");
                String description = result.getString("Description");
                String type = result.getString("Type");
                int custId = result.getInt("Customer_ID");
                int userId = result.getInt("User_ID");
                int contactId = result.getInt("Contact_ID");
                Date d = new Date(result.getDate("Start").getTime());
                Time t = new Time(result.getTime("Start").getTime());
                String newTime = (d.toString() + "T" + t.toString());
                Date e = new Date(result.getDate("End").getTime());
                Time f = new Time(result.getTime("End").getTime());
                String newEnd = (e.toString() + "T" + f.toString());


                LocalDateTime newp = LocalDateTime.parse(newTime);
                LocalDateTime newt = LocalDateTime.parse(newEnd);


                LocalDateTime start = timeMGMT.convertUTCToSystem(newp);
                LocalDateTime end = timeMGMT.convertUTCToSystem(newt);

                Appointment appt = new Appointment(id, title, description, location, type, start, end, custId, userId, contactId);
                resultAppt.add(appt);


            }
            return resultAppt;
        }


        catch (SQLException e)
        { System.out.println(e.getMessage());}
        return null;
    }

    public static int generateApptID() {
        try {
            String sql = "select appointment_ID from appointments order by appointment_ID desc limit 1;";
            PreparedStatement message = JDBC.connection.prepareStatement(sql);
            ResultSet result = message.executeQuery();
            result.next();
            int newiD = result.getInt("appointment_id") + 1;
            return  newiD;

        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
            return 0;
        }
    }


    public static ObservableList<Appointment> pullAllApptsbyContactID(int contactIdz) {
        try {
            String sql = "select * from appointments where Contact_ID = ?";

            PreparedStatement statement = JDBC.connection.prepareStatement(sql);
            statement.setInt(1, contactIdz);
            ResultSet result = statement.executeQuery();
            ObservableList<Appointment> resultAppt = FXCollections.observableArrayList();

            while(result.next()) {
                int id = result.getInt("Appointment_ID");
                String title = result.getString("Title");
                String location = result.getString("Location");
                String description = result.getString("Description");
                String type = result.getString("Type");
                int custId = result.getInt("Customer_ID");
                int userId = result.getInt("User_ID");
                int contactId = result.getInt("Contact_ID");
                Date d = new Date(result.getDate("Start").getTime());
                Time t = new Time(result.getTime("Start").getTime());
                String newTime = (d.toString() + "T" + t.toString());
                Date e = new Date(result.getDate("End").getTime());
                Time f = new Time(result.getTime("End").getTime());
                String newEnd = (e.toString() + "T" + f.toString());


                LocalDateTime newp = LocalDateTime.parse(newTime);
                LocalDateTime newt = LocalDateTime.parse(newEnd);


                LocalDateTime start = timeMGMT.convertUTCToSystem(newp);
                LocalDateTime end = timeMGMT.convertUTCToSystem(newt);


                Appointment appt = new Appointment(id, title, description, location, type, start, end, custId, userId, contactId);
                resultAppt.add(appt);


            }
            return resultAppt;
        }
        catch (SQLException e)
        { System.out.println(e.getMessage());}
        return null;
    }

    public static ObservableList<Appointment> pullAllApptsbyMonthAndType(String mnth, String typ) {
        DateTimeFormatter parser = DateTimeFormatter.ofPattern("MMM");
        TemporalAccessor accessor = parser.parse(mnth);
        int monthAsInteger = accessor.get(ChronoField.MONTH_OF_YEAR);
        try {
            String sql = "select * from appointments where month(start)= ? and type = ?;";

            PreparedStatement statement = JDBC.connection.prepareStatement(sql);
            statement.setInt(1, monthAsInteger);
            statement.setString(2, typ);
            ResultSet result = statement.executeQuery();
            ObservableList<Appointment> resultAppt = FXCollections.observableArrayList();

            while(result.next()) {
                int id = result.getInt("Appointment_ID");
                String title = result.getString("Title");
                String location = result.getString("Location");
                String description = result.getString("Description");
                String type = result.getString("Type");
                int custId = result.getInt("Customer_ID");
                int userId = result.getInt("User_ID");
                int contactId = result.getInt("Contact_ID");
                Date d = new Date(result.getDate("Start").getTime());
                Time t = new Time(result.getTime("Start").getTime());
                String newTime = (d.toString() + "T" + t.toString());
                Date e = new Date(result.getDate("End").getTime());
                Time f = new Time(result.getTime("End").getTime());
                String newEnd = (e.toString() + "T" + f.toString());


                LocalDateTime newp = LocalDateTime.parse(newTime);
                LocalDateTime newt = LocalDateTime.parse(newEnd);


                LocalDateTime start = timeMGMT.convertUTCToSystem(newp);
                LocalDateTime end = timeMGMT.convertUTCToSystem(newt);

                Appointment appt = new Appointment(id, title, description, location, type, start, end, custId, userId, contactId);
                resultAppt.add(appt);


            }
            return resultAppt;
        }
        catch (SQLException e)
        { System.out.println(e.getMessage());}
        return null;
    }

    public static ObservableList<String> pullAllAppointmentTypes(){
        try{
            String sql = "select distinct type from appointments;";

            PreparedStatement statement = JDBC.connection.prepareStatement(sql);
            ResultSet result = statement.executeQuery();
            ObservableList<String> resultTypes = FXCollections.observableArrayList();

            while (result.next()){
                String type = result.getString("Type");
                resultTypes.add(type);
            }
            return resultTypes;

        }
        catch (SQLException e)
        { System.out.println(e.getMessage());}
        return null;
    }
}
