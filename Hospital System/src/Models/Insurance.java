package Models;

import Utilities.Input;

public class Insurance
{
    //Identifiers
    private String insuranceID;

    //Properties
    private String company;
    private String address;
    private String phone;

    //Constructor
    public Insurance(String insuranceID, String company, String address, String phone)
    {
        this.insuranceID = Input.RequireNonNull(insuranceID);
        this.company = Input.RequireNonNull(company);
        this.address = Input.RequireNonNull(address);
        this.phone = Input.RequireNonNull(phone);
    }

    public Insurance()
    {
        //Empty
    }

    //Getters
    public String getInsuranceID() {return insuranceID;}
    public String getCompany() {return company;}
    public String getAddress() {return address;}
    public String getPhone() {return phone;}

    //Setters
    public void setInsuranceID(String insuranceID){this.insuranceID = insuranceID;}
    public void setCompany(String company){this.company = company;}
    public void setAddress(String address){this.address = address;}
    public void setPhone(String phone){this.phone = phone;}

    public void DisplayDetails()
    {
        System.out.println();
        System.out.println("Insurance ID: " + insuranceID);
        System.out.println("Company name: " + company);
        System.out.println("Address: " + address);
        System.out.println("Phone: " + phone);
    }
}
