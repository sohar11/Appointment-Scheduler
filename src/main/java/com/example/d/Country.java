package com.example.d;
/**Country class hold Country information from SQL database*/
public  class Country {
    /**Country constructor
     * @param countryId the country ID
     * @param countryName name of country*/
    public Country(int countryId, String countryName) {
        this.countryId = countryId;
        this.countryName = countryName;
    }


    /**setter for CountryID
     * @param countryId sets countryID as this.countryId*/
    public void setCountryId(int countryId) {
        this.countryId = countryId;
    }

    /**getter for country ID
     * @return returns countryId*/
    public int getCountryId() {
        return countryId;
    }
    /**getter for country Name
     * @return returns country Name*/

    public String getCountryName() {
        return countryName;
    }

    /**countryId int holds country ID  */

    private int countryId;


   /**countryName String holds country Name */
    private String countryName;

}
