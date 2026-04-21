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

import javax.print.Doc;
import java.util.List;
import java.util.Objects;

public class DoctorBusinessLogic
{
    //Public functions
    public static void AddDoctor()
    {
        Doctor doctor = GetDetails.getDoctorDetails(false);

        if(doctor == null){System.out.println("Operation cancelled"); return;}

        //Add to database
        DoctorDataAccess.createDoctor(doctor);
    }

    public static void UpdateDoctor()
    {
        Doctor doctor = GetDetails.getDoctorDetails(true);

        if(doctor == null){System.out.println("Operation cancelled"); return;}

        //Update database
        DoctorDataAccess.updateDoctor(doctor);
    }

    public static void DeleteDoctor()
    {
        //Get id
        String doctorId = GetIdentifiers.getDoctorID(true);

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

    public static void LoadDoctorById()
    {
        String doctorId = GetIdentifiers.getDoctorID(true);
        if(doctorId == null){System.out.println("Operation cancelled"); return;}

        Doctor doctor = DoctorDataAccess.getDoctorById(doctorId);
        if(doctor == null){System.out.println("Operation cancelled"); return;}

        doctor.DisplayDetails();
    }

    public static void LoadDoctorByParameters()
    {
        List<Doctor> doctors = RecordAccessGuard.getDoctorByParameters();
        if(doctors == null){System.out.println("Operation cancelled"); return;}

        for (int i = 0; i < doctors.size(); i++)
        {
            doctors.get(i).DisplayDetails();
        }
    }

    public static void LoadDoctorBySpeciality()
    {
        List<Doctor> doctors = RecordAccessGuard.getDoctorBySpeciality();
        if(doctors == null){System.out.println("Operation cancelled"); return;}

        for (int i = 0; i < doctors.size(); i++)
        {
            doctors.get(i).DisplayDetails();
        }
    }
}
