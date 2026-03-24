package Models;

import Utilities.Input;

public class Specialist extends Doctor
{
    //Properties
    private final Specializations specialization;
    private final int experience;

    //Constructor
    public Specialist(String doctorID, String firstName, String surname, String address, String email, String hospital, Specializations specialization, int experience)
    {
        super(doctorID, firstName, surname, address, email, hospital);

        this.specialization = Input.RequireNonNull(specialization);
        this.experience = Input.RequireNonNull(experience);
    }

    //Getters
    public Specializations getSpecialization() {return specialization;}
    public int getExperience() {return experience;}
}
