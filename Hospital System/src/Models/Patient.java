package Models;

import Utilities.Input;

public class Patient
{
    //Identifiers
    private String patientID;
    private String insuranceID;
    private String insuranceCompany;
    private String doctorID;
    private String doctorFirstname;
    private String doctorSurname;

    //Properties
    private String firstName;
    private String surname;

    private String postcode;

    private String address;
    private String phone;
    private String email;

    //Constructor
    public Patient(String patientID, String insuranceID, String doctorID, String firstName, String surname, String postcode, String address, String phone, String email)
    {
        this.patientID = Input.RequireNonNull(patientID);
        this.insuranceID = insuranceID;
        this.doctorID = doctorID;
        this.firstName = Input.RequireNonNull(firstName);
        this.surname = Input.RequireNonNull(surname);
        this.postcode = postcode;
        this.address = Input.RequireNonNull(address);
        this.phone = Input.RequireNonNull(phone);
        this.email = Input.RequireNonNull(email);
    }

    public Patient(String patientID, String insuranceID, String doctorID, String firstName, String surname, String postcode, String address, String phone,  String email, String insuranceCompany, String doctorFirstname, String doctorSurname)
    {
        this.patientID = Input.RequireNonNull(patientID);
        this.insuranceID = insuranceID;
        this.doctorID = doctorID;
        this.firstName = Input.RequireNonNull(firstName);
        this.surname = Input.RequireNonNull(surname);
        this.postcode = postcode;
        this.address = Input.RequireNonNull(address);
        this.phone = Input.RequireNonNull(phone);
        this.email = Input.RequireNonNull(email);
        this.insuranceCompany = insuranceCompany;
        this.doctorFirstname = doctorFirstname;
        this.doctorSurname = doctorSurname;
    }

    public Patient (String firstName, String surname)
    {
        this.firstName = firstName;
        this.surname = surname;
    }

    //Getters
    public String getInsuranceID() {return insuranceID;}
    public String getInsuranceCompany() {return insuranceCompany;}
    public String getDoctorID() {return doctorID;}
    public String getDoctorFirstname() {return doctorFirstname;}
    public String getDoctorSurname() {return doctorSurname;}
    public String getPatientID() {return patientID;}
    public String getFirstName() {return firstName;}
    public String getSurname() {return surname;}
    public String getPostcode() {return postcode;}
    public String getAddress() {return address;}
    public String getPhone() {return phone;}
    public String getEmail() {return email;}

//    public void setPatientID(String patientID) {this.patientID = patientID;}
//    public void setInsuranceID(String insuranceID) {this.insuranceID = insuranceID;}
//    public void setInsuranceCompany(String insuranceCompany) {this.insuranceCompany = insuranceCompany;}
//    public void setFirstName(String firstName) {this.firstName = firstName;}
//    public void setSurname(String surname) {this.surname = surname;}
//    public void setAddress(String address) {this.address = address;}
//    public void setPostcode(String postcode) {this.postcode = postcode;}
//    public void setPhone(String phone) {this.phone = phone;}
//    public void setEmail(String email) {this.email = email;}

    public void DisplayDetails()
    {
        System.out.println("\nPatient ID: " + patientID);
        System.out.println("Insurance Info: \n\tID: " + insuranceID + "\n\tName: " + insuranceCompany);
        System.out.println("Primary Doctor Info: \n\tID: " + doctorID + "\n\tName: " + Input.combineStrings(doctorFirstname, doctorSurname));
        System.out.println("Patient firstname: " + firstName);
        System.out.println("Patient surname: " + surname);
        System.out.println("Patient address: " + postcode + " " + address);
        System.out.println("Patient phone: " + phone);
        System.out.println("Patient email: " + email);
    }
}
