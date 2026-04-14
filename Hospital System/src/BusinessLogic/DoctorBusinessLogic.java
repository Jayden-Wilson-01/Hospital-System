package BusinessLogic;

import DataAccess.DoctorDataAccess;
import DataAccess.PatientDataAccess;
import Models.Doctor;
import Models.Patient;
import Models.Specialist;
import Models.Specializations;
import Utilities.Input;

import javax.print.Doc;
import java.util.List;
import java.util.Objects;

public class DoctorBusinessLogic
{
    private static Specialist GetSpecialistDetails(boolean includeID)
    {
        String doctorId = "";

        if(includeID)
        {
            doctorId = GetID(false);
        }

        String firstname = Input.GetString("Enter firstname: ");
        String surname = Input.GetString("Enter surname: ");
        String address  = Input.GetString("Enter address: ");
        String email = Input.GetString("Enter email: ");
        String hospital = Input.GetString("Enter hospital: ");

        Specializations.DisplayOptions();

        int choice = Input.GetInt("Enter specialization (1-" + Specializations.OptionAmount() + "): ");
        Models.Specializations.SpecializationType specialization = Specializations.SpecializationType.values()[choice - 1];

        int experience = Input.GetInt("Enter experience (years): ");

        //Create specialist model
        Specialist specialist = new Specialist();

        if(includeID)
        {
            specialist.setDoctorID(doctorId);
        }

        specialist.setFirstName(firstname);
        specialist.setSurname(surname);
        specialist.setAddress(address);
        specialist.setEmail(email);
        specialist.setHospital(hospital);
        specialist.setSpecialization(specialization.toString());
        specialist.setExperience(experience);

        return specialist;
    }

    private static Doctor GetStandardDetails(boolean includeID)
    {
        String doctorId = "";
        if(includeID)
        {
            doctorId = GetID(false);
        }

        String firstname = Input.GetString("Enter firstname: ");
        String surname = Input.GetString("Enter surname: ");
        String address  = Input.GetString("Enter address: ");
        String email = Input.GetString("Enter email: ");
        String hospital = Input.GetString("Enter hospital: ");

        //Create standard model
        Doctor standard = new Doctor();

        if(includeID)
        {
            standard.setDoctorID(doctorId);
        }

        standard.setFirstName(firstname);
        standard.setSurname(surname);
        standard.setAddress(address);
        standard.setEmail(email);
        standard.setHospital(hospital);

        return standard;
    }

    private static String GetID(boolean exist)
    {
        if(!exist)
        {
            while(true)
            {
                String doctorID = Input.GetString("Enter doctor ID: ");

                exist = DoctorDataAccess.ExistsInDatabase(doctorID);

                if(!exist)
                {
                    return doctorID;
                }
            }
        }
        else
        {
            while(true)
            {
                String doctorID = Input.GetString("Enter existing doctor ID: ");

                exist = DoctorDataAccess.ExistsInDatabase(doctorID);

                if(exist)
                {
                    return doctorID;
                }
            }
        }
    }

    private static String GetDoctorType()
    {
        int doctorType = 0;

        while(true)
        {
            System.out.println("Doctor types");
            System.out.println("1. Specialist");
            System.out.println("2. Standard");

            doctorType = Input.GetInt("Enter doctor type: ");

            if(doctorType == 1)
            {
                return "Specialist";
            }
            if (doctorType == 2)
            {
                return "Standard";
            }
        }
    }

    private static String GetDoctorTypeByID(String doctorID)
    {
        return DoctorDataAccess.GetDoctorTypeByID(doctorID);
    }

    //Public functions
    public static void AddDoctor()
    {
        //Get doctor type before getting details
        String doctorType = GetDoctorType();

        //Check the type of doctor
        if(Objects.equals(doctorType, "Specialist"))
        {
             Doctor specialist = GetSpecialistDetails(true);

            //Add to database
            DoctorDataAccess.createDoctor(specialist);
        }
        else
        {
            //Create doctor model
            Doctor standard = GetStandardDetails(true);

            //Add to database
            DoctorDataAccess.createDoctor(standard);
        }
    }

    public static void UpdateDoctor()
    {
        //Get ID
        String doctorId = GetID(true);

        //Determine doctor type
        String doctorType = GetDoctorTypeByID(doctorId);

        if(doctorType.equals("Specialist"))
        {
            Specialist specialist = GetSpecialistDetails(false);
            specialist.setDoctorID(doctorId);

            //Update in database
            DoctorDataAccess.updateDoctor(specialist);
        }
        else
        {
            Doctor standard = GetStandardDetails(false);
            standard.setDoctorID(doctorId);

            //Update in database
            DoctorDataAccess.updateDoctor(standard);
        }
    }

    public static void DeleteDoctor()
    {
        //Get id
        String doctorId = GetID(true);

        //Delete from database
        DoctorDataAccess.deleteDoctor(doctorId);
    }

    public static void LoadAllDoctors()
    {
        int limit = Input.GetInt("Enter a load limit: ");

        List<Doctor> doctors = DoctorDataAccess.getAllDoctors(limit);

        int index = 0;
        while (index < doctors.size())
        {
            if(doctors.get(index) instanceof Specialist)
            {
               doctors.get(index).DisplayDetails();
            }
            else
            {
                doctors.get(index).DisplayDetails();
            }

            index++;
        }
    }
}
