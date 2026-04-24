package BusinessLogic;

import DataAccess.DoctorDataAccess;
import DataAccess.PatientDataAccess;
import Models.Doctor;
import Models.Patient;
import Models.Specialist;
import Models.Specializations;
import PresentationLayer.GetDetails;
import PresentationLayer.GetIdentifiers;
import PresentationLayer.RecordAccessGuard;
import Utilities.Input;

import java.util.List;

public class DoctorBusinessLogic
{
    /**
     * Adds a new doctor by taking input and transferring model to database
     */
    public static void AddDoctor()
    {
        //Create a new model with teh provided details
        Doctor doctor = GetDetails.getDoctorDetails(false);

        //Make sure model exists
        if(doctor == null){System.out.println("Operation cancelled"); return;}

        //Add to database
        DoctorDataAccess.createDoctor(doctor);
    }

    /**
     * Updates and existing doctor by taking the details and transferring the model to the database
     */
    public static void UpdateDoctor()
    {
        //Create a new model with the updated details
        Doctor doctor = GetDetails.getDoctorDetails(true);

        //Check that teh model exists
        if(doctor == null){System.out.println("Operation cancelled"); return;}

        //Update database
        DoctorDataAccess.updateDoctor(doctor);
    }

    /**
     * Deletes a doctor by getting the existing doctors identifier and deleting from the database
     */
    public static void DeleteDoctor()
    {
        //Get existing id
        String doctorId = GetIdentifiers.getDoctorID(true);

        //Delete from database
        DoctorDataAccess.deleteDoctor(doctorId);
    }

    /**
     * Shows all doctors details based on a defined limit.
     */
    public static void LoadAllDoctors()
    {
        //Defines the limit for how many doctors to load
        int limit = Input.GetInt("Enter a load limit: ");

        //The list of the doctors loaded from the database
        List<Doctor> doctors = DoctorDataAccess.getAllDoctors(limit);

        int index = 0;
        while (index < doctors.size())
        {
            //Automatically knows id doctor is a normal doctor or specialist due to polymorphism
            doctors.get(index).DisplayDetails();
            index++;
        }
    }

    /**
     * Shows details doctor based on a provided ID
     */
    public static void LoadDoctorById()
    {
        //Get existing doctor id
        String doctorId = GetIdentifiers.getDoctorID(true);
        if(doctorId == null){System.out.println("Operation cancelled"); return;}

        //Create model to represent the doctor
        Doctor doctor = DoctorDataAccess.getDoctorById(doctorId);
        if(doctor == null){System.out.println("Operation cancelled"); return;}

        //Show the details of the chosen doctor
        doctor.DisplayDetails();
    }

    /**
     * Shows details of a doctor based on the provided parameters
     */
    public static void LoadDoctorByParameters()
    {
        //Gets a list of doctors that match the provided parameters
        List<Doctor> doctors = RecordAccessGuard.getDoctorByParameters();
        if(doctors == null){System.out.println("Operation cancelled"); return;}

        //Display details of each found doctor
        for (int i = 0; i < doctors.size(); i++)
        {
            doctors.get(i).DisplayDetails();
        }
    }

    /**
     * Shows details of doctors of a specific speciality
     */
    public static void LoadDoctorBySpeciality()
    {
        //List fo doctors with the specific speciality
        List<Doctor> doctors = RecordAccessGuard.getDoctorBySpeciality();
        if(doctors == null){System.out.println("Operation cancelled"); return;}

        //Shows details of each found doctor
        for (int i = 0; i < doctors.size(); i++)
        {
            doctors.get(i).DisplayDetails();
        }
    }
}
