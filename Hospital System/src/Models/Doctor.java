package Models;

import Utilities.Input;

public class Doctor
{
    //Identifiers
    private final String doctorID;

    //Properties
    private final String firstName;
    private final String surname;
    private final String address;
    private final String email;
    private final String hospital;

    //Constructor
    public Doctor(String doctorID, String firstName, String surname, String address, String email, String hospital)
    {
        this.doctorID = Input.RequireNonNull(doctorID);
        this.firstName = Input.RequireNonNull(firstName);
        this.surname = Input.RequireNonNull(surname);
        this.address = Input.RequireNonNull(address);
        this.email = Input.RequireNonNull(email);
        this.hospital = Input.RequireNonNull(hospital);
    }

    //Getters
    public String getDoctorID() {return doctorID;}
    public String getFirstName() {return firstName;}
    public String getSurname() {return surname;}
    public String getAddress() {return address;}
    public String getEmail() {return email;}
    public String getHospital() {return hospital;}
}
