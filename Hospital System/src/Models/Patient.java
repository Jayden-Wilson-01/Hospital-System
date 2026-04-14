package Models;

import Utilities.Input;

public class Patient
{
    //Identifiers
    private String patientID;

    //Properties
    private final String firstName;
    private final String surname;

    // Removed postcode to there is consistency between other objects like a doctor
    //private final String postcode;

    private final String address;
    private final String phone;
    private final String email;

    //Constructor
    public Patient(String patientID, String firstName, String surname, String address, String phone, String email)
    {
        this.patientID = Input.RequireNonNull(patientID);
        this.firstName = Input.RequireNonNull(firstName);
        this.surname = Input.RequireNonNull(surname);
        //this.postcode = Input.RequireNonNull(postcode);
        this.address = Input.RequireNonNull(address);
        this.phone = Input.RequireNonNull(phone);
        this.email = Input.RequireNonNull(email);
    }

    public Patient(String firstName, String surname, String address, String phone, String email)
    {
        this.firstName = Input.RequireNonNull(firstName);
        this.surname = Input.RequireNonNull(surname);
        //this.postcode = Input.RequireNonNull(postcode);
        this.address = Input.RequireNonNull(address);
        this.phone = Input.RequireNonNull(phone);
        this.email = Input.RequireNonNull(email);
    }

    //Getters
    public String getPatientID() {return patientID;}
    public String getFirstName() {return firstName;}
    public String getSurname() {return surname;}
    //public String getPostcode() {return postcode;}
    public String getAddress() {return address;}
    public String getPhone() {return phone;}
    public String getEmail() {return email;}
}
