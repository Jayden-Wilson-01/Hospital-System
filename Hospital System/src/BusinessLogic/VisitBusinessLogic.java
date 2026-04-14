package BusinessLogic;

import DataAccess.DoctorDataAccess;
import DataAccess.DrugDataAccess;
import DataAccess.PatientDataAccess;
import DataAccess.VisitDataAccess;
import Models.Visit;
import Utilities.Input;

import java.time.LocalDate;
import java.util.List;

public class VisitBusinessLogic
{
    private static Visit GetDetails(boolean includeId)
    {
        //Identifiers
        String visitId = "";
        String patientId = "";
        String doctorId = "";

        //Ask for the id's
        if(includeId){visitId = GetVisitID(false);}
        patientId = GetPatientID(); //Must exist
        doctorId = GetDoctorID(); //Must exist

        //Exit the operation early if user asks to cancel
        if(patientId.equals("0") || doctorId.equals("0"))
        {
            return null; //cancel the operation
        }

        //Ask for details
        LocalDate dateOfVisit = Input.GetDate("Enter Date of Visit: ");
        String symptoms = Input.GetString("Enter Symptoms: ");
        String diagnosis  = Input.GetString("Enter Diagnosis: ");

        //Create model
        Visit visit = new Visit();

        //Populate the model
        if(includeId){visit.setVisitID(visitId);}
        visit.setPatientID(patientId);
        visit.setDoctorID(doctorId);
        visit.setDateOfVisit(dateOfVisit);
        visit.setSymptoms(symptoms);
        visit.setDiagnosis(diagnosis);

        return visit;
    }

    private static String GetVisitID(boolean mustExist)
    {
        while(true)
        {
            String visitId = Input.GetString("Enter visit ID: ");
            boolean exists = VisitDataAccess.ExistsInDatabase(visitId);

            if (mustExist)
            {
                if (exists)
                {
                    return visitId; // Found it, we can update!
                }
                else
                {
                    System.out.println("Error: ID does not exist. Please enter a valid ID to update.");
                }
            }
            else
            {
                if (!exists)
                {
                    return visitId;
                }
                else
                {
                    System.out.println("Error: ID already exists. Please choose a unique ID.");
                }
            }
        }
    }

    private static String GetPatientID()
    {
        while(true)
        {
            String patientId = Input.GetString("Enter patient ID or 0 to exit: ");
            boolean exists = PatientDataAccess.ExistsInDatabase(patientId);

            if(patientId.equals("0"))
            {
                return patientId;
            }

            if (exists)
            {
                return patientId;
            }
            else
            {
                System.out.println("Error: ID does not exist. Please enter a valid ID to update.");
            }
        }
    }

    private static String GetDoctorID()
    {
        while(true)
        {
            String doctorId = Input.GetString("Enter doctor ID ot 0 to exit: ");
            boolean exists = DoctorDataAccess.ExistsInDatabase(doctorId);

            if(doctorId.equals("0"))
            {
                return doctorId;
            }

            if (exists)
            {
                return doctorId;
            }
            else
            {
                System.out.println("Error: ID does not exist. Please enter a valid ID to update.");
            }
        }
    }

    public static void AddVisit()
    {
        //Gather details
        Visit visit = GetDetails(true);

        if(visit != null)
        {
            //Add to database
            VisitDataAccess.createVisit(visit);
        }
    }

    public static void UpdateVisit()
    {
        //Ask for existing id
        String visitId = GetVisitID(true);

        //Gather new details
        Visit visit = GetDetails(false);
        visit.setVisitID(visitId);

        //Update database
        VisitDataAccess.updateVisit(visit);
    }

    public static void DeleteVisit()
    {
        //Ask for existing id
        String visitId = GetVisitID(true);

        //Delete from database
        VisitDataAccess.deleteVisit(visitId);
    }

    public static void LoadAllVisits()
    {
        //Ask for limit
        int limit =  Input.GetInt("Enter limit: ");

        //Get records and add to list
        List<Visit> visits = VisitDataAccess.loadAllVisits();

        //Check if there are records
        if(visits == null || visits.isEmpty())
        {
            return;
        }

        int index = 0;
        while(index < visits.size())
        {
            Visit visit = visits.get(index);
            visit.DisplayDetails();
            index++;
        }
    }
}
