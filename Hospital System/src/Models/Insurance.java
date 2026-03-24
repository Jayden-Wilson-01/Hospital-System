package Models;

import Utilities.Input;

public class Insurance
{
    //Identifiers
    private final String insuranceID;

    //Properties
    private final String company;
    private final String address;
    private final String phone;

    //Constructor
    public Insurance(String insuranceID, String company, String address, String phone)
    {
        this.insuranceID = Input.RequireNonNull(insuranceID);
        this.company = Input.RequireNonNull(company);
        this.address = Input.RequireNonNull(address);
        this.phone = Input.RequireNonNull(phone);
    }

    //Getters
    public String getInsuranceID() {return insuranceID;}
    public String getCompany() {return company;}
    public String getAddress() {return address;}
    public String getPhone() {return phone;}
}
