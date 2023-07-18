package com.example.d.model;


/**Contact class holds the contact information from the contact Table of the database*/

public  class Contact {


    /**contactId Contact ID Integer*/
    private int contactId;



    /**getter for Contact ID
     * @return returns contactId*/
    public int getContactId() {
        return contactId;
    }


    /**setter for contactID
     * @param contactId sets contact ID as this.contactId*/

    public void setContactId(int contactId) {
        this.contactId = contactId;
    }


    /**getter for contact Name
     * @return returns contact Name*/

    public String getContactName() {
        return contactName;
    }

    /**setter for Contact name
     * @param contactName  sets contact Name to this.contactName*/

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }


    /**contactName String holds contact Name*/
    private String contactName;


    /**email String holds contact's email*/

    private String email;


    /**constructor for contact class creates class member
     * @param contactName contact Name, String
     * @param contactId  contact ID, Integer
     * @param email contact email, String */

    public Contact(int contactId, String contactName, String email) {
        this.contactId = contactId;
        this.contactName = contactName;
        this.email = email;
    }
}
