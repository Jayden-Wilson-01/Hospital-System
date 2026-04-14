package Models;

import Utilities.Input;

public class Doctor
{
    //Identifiers
    private String doctorID;

    //Properties
    private String firstName;
    private String surname;
    private String address;
    private String email;
    private String hospital;

    //Constructor
    public Doctor(String doctorID, String firstName, String surname, String address, String email, String hospital)
    {
        this.doctorID = Input.RequireNonNull(doctorID);
        this.firstName = Input.RequireNonNull(firstName);
        this.surname = Input.RequireNonNull(surname);
        this.address = Input.RequireNonNull(address);
        this.email = Input.RequireNonNull(email);
        this.hospital = hospital; //Allow for nulls
    }

    public Doctor()
    {
        //Empty constructor
    }

    //Getters
    public String getDoctorID() {return doctorID;}
    public String getFirstName() {return firstName;}
    public String getSurname() {return surname;}
    public String getAddress() {return address;}
    public String getEmail() {return email;}
    public String getHospital() {return hospital;}

    //Setters
    public void setDoctorID(String doctorID){this.doctorID = doctorID;}
    public void setFirstName(String firstName) {this.firstName = firstName;}
    public void setSurname(String surname) {this.surname = surname;}
    public void setAddress(String address) {this.address = address;}
    public void setEmail(String email) {this.email = email;}
    public void setHospital(String hospital) {this.hospital = hospital;}

    public void DisplayDetails()
    {
        System.out.println();
        System.out.println("Doctor ID: " + getDoctorID());
        System.out.println("Doctor firstname: " + getFirstName());
        System.out.println("Doctor surname: " + getSurname());
        System.out.println("Doctor address: " + getAddress());
        System.out.println("Doctor email: " + getEmail());
        System.out.println("Situated hospital: " + getHospital());
    }
}
