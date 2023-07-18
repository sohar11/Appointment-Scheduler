package com.example.d;
/**The customer class holds all the customer information for customers in the database*/

public  class Customer {

    /**customer ID integer*/
    private int customerId;

    /**String holding customers name*/

    private String customerName;


    /**String holding customer address*/
    private String address;

    /**String holding customers postal code*/
    private String postalCode;

    /**String holding customer phone number*/
    private String phone;

    /**getter for customer ID
     * @return  returns customer ID */

    public int getCustomerId() {
        return customerId;
    }

    /**getter for customer Name
     * @return returns customer name*/

    public String getCustomerName() {
        return customerName;
    }

    /**getter for customer Address
     * @return returns customer address*/

    public String getAddress() {
        return address;
    }

    /**getter for customer postal code
     * @return returns customer postal code*/

    public String getPostalCode() {
        return postalCode;
    }

    /**getter for customer phone number
     * @return  returns customer phone number*/

    public String getPhone() {
        return phone;
    }

    /**getter for customer division ID
     * @return returns division ID*/

    public int getDivisionId() {
        return divisionId;
    }

    /**setter for customerid
     * @param customerId sets customerID as this.customerId */
    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    /**setter for customerName
     * @param customerName sets customerName as this.customerName */

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    /**Integer holding customers division ID */

    private int divisionId;



     /**Customer constructor builds a member of customer class
      * @param divisionId  customers division id, integer
      * @param customerId  customer ID, integer
      * @param address  customer address, string
      * @param customerName customer name, string
      * @param phone  customer phone number ,string
      * @param postalCode customer postal code, string
      * */
    public Customer(int customerId, String customerName, String address, String postalCode, String phone, int divisionId) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.address = address;
        this.postalCode = postalCode;
        this.phone = phone;
        this.divisionId = divisionId;
    }
}
