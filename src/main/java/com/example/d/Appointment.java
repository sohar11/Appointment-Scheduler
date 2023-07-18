package com.example.d;

import java.time.LocalDateTime;

/**Appointment class holds all appointment information from the SQL database*/

public  class Appointment {

    private int appointmentId;
    private String title;
    private String description;
    private String location;
    private String type;
    private LocalDateTime start;
    private LocalDateTime end;

    private int customerId;
    private int userId;
    private int contactId;


    /**getter for appoinment ID
     * @return returns appoinment ID */
    public int getAppointmentId() {
        return appointmentId;
    }


    /**getter for appointment title
     * @return returns appointment title*/
    public String getTitle() {
        return title;
    }

    /**getter for appointment description
     * @return  returns appointment description*/

    public String getDescription() {
        return description;
    }

    /**getter for appointment location
     * @return returns appoinment location*/
    public String getLocation() {
        return location;
    }

    /**getter for appointment type
     * @return returns appointment type*/
    public String getType() {
        return type;
    }

    /**getter for start time
     * @return returns appointment start time*/
    public LocalDateTime getStart() {
        return start;
    }

    /**getter for end time
     * @return returns appointment end time*/

    public LocalDateTime getEnd() {
        return end;
    }


    /**getter for customer ID
     * @return returns customer ID*/

    public int getCustomerId() {
        return customerId;
    }

    /**getter for User ID
     * @return  returns userID*/
    public int getUserId() {
        return userId;
    }

    /**getter for contact ID
     * @return  returns contact ID */
    public int getContactId() {
        return contactId;
    }

    /**setter for appointment Id
     * @param appointmentId Integer, sets appointment ID to this.appointmentId */
    public void setAppointmentId(int appointmentId) {
        this.appointmentId = appointmentId;
    }

    /**setter for appointment start time
     * @param start LocalDateTime start time for appointment, sets start time to this.start time*/
    public void setStart(LocalDateTime start) {
        this.start = start;
    }


    /**setter for appointment end time
     * @param end LocalDateTime end time for appointment, sets end time to this.end time*/
    public void setEnd(LocalDateTime end) {
        this.end = end;
    }


    /**Appointment Constructor, creates member of Appointment class
     * @param contactId contact ID associated with appointment, integer
     * @param customerId customer ID associated with appointment, integer
     * @param type appointment type, String
     * @param end appointment end time, LocalDateTime
     * @param location appointment location, String
     * @param appointmentId appointment ID, Integer
     * @param description appointment description, String
     * @param start appointment start time, LocalDateTime
     * @param title appointment title, String
     * @param userId user ID associated with appointment, integer */
    public Appointment(int appointmentId, String title, String description, String location, String type, LocalDateTime start, LocalDateTime end, int customerId, int userId, int contactId) {
        this.appointmentId = appointmentId;
        this.title = title;
        this.description = description;
        this.location = location;
        this.type = type;
        this.start = start;
        this.end = end;
        this.customerId = customerId;
        this.userId = userId;
        this.contactId = contactId;
    }
}
