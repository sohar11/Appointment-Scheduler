package com.example.d.model;

/**FirstLevelDivisions class holds State/province information and country ID*/

public  class FirstLevelDivisions {

    /**divisionId stores state/province information as Integer*/
    private int divisionId;

    /**divisionName String holds state/province name*/
    private String divisionName;

    /**FirstLevelDivisions constructor builds a class member
     * @param divisionId the division ID integer
     * @param countryId the country ID integer
     * @param divisionName the division name String */

    public FirstLevelDivisions(int divisionId, int countryId, String divisionName) {
        this.divisionId = divisionId;
        this.divisionName = divisionName;
        this.countryId = countryId;
    }

    /**getter for division ID
     * @return returns divisionID*/
    public int getDivisionId() {
        return divisionId;
    }


    /**getter for division name
     * @return returns divisionName */
    public String getDivisionName() {
        return divisionName;
    }


    /**setter for division id
     * @param divisionId sets divisionID as this.divisionId*/
    public void setDivisionId(int divisionId) {
        this.divisionId = divisionId;
    }


    /**getter for country ID
     * @return returns country ID */
    public int getCountryId() {
        return countryId;
    }


    /**countryID integer holds country information*/
    private int countryId;
}
