package Models;

import Utilities.Input;

public class Specialist extends Doctor
{
    //Properties
    private String specialization;
    private int experience;

    //Constructor
    public Specialist(String doctorID, String firstName, String surname, String address, String email, String hospital, String specialization, int experience)
    {
        super(doctorID, firstName, surname, address, email, hospital);

        this.specialization = Input.RequireNonNull(specialization);
        this.experience = Input.RequireNonNull(experience);
    }

    public Specialist()
    {
        //Empty Constructor
    }

    //Getters
    public String getSpecialization() {return specialization;}
    public int getExperience() {return experience;}

    //Setters
    public void setExperience(int experience) {this.experience = experience;}
    public void setSpecialization(String specialization){this.specialization = specialization;}

    @Override
    public void DisplayDetails()
    {
        System.out.println();
        System.out.println("Doctor ID: " + getDoctorID());
        System.out.println("Doctor firstname: " + getFirstName());
        System.out.println("Doctor surname: " + getSurname());
        System.out.println("Doctor address: " + getAddress());
        System.out.println("Doctor email: " + getEmail());
        System.out.println("Situated hospital: " + getHospital());
        System.out.println("Specialization: " + getSpecialization());
        System.out.println("Experience: " + getExperience());
    }
}
