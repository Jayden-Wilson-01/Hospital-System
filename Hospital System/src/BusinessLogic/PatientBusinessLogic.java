package BusinessLogic;

import DataAccess.InsuranceDataAccess;
import DataAccess.PatientDataAccess;
import DataAccess.PrescriptionDataAccess;
import Models.Patient;
import Models.Prescription;
import PresentationLayer.GetDetails;
import PresentationLayer.GetIdentifiers;
import PresentationLayer.RecordAccessGuard;
import Utilities.Input;

import java.time.LocalDate;
import java.util.List;

public class PatientBusinessLogic
{
    public static void AddPatient()
    {
        //Gather details
        Patient patient = GetDetails.getPatientDetails(false);

        if(patient == null){System.out.println("Operation cancelled"); return;}

        //Add to database
        PatientDataAccess.createPatient(patient);
    }

    public static void UpdatePatient()
    {
        //Gather new details
        Patient patient = GetDetails.getPatientDetails(true);

        if(patient == null){System.out.println("Operation cancelled"); return;}

        //Update database
        PatientDataAccess.updatePatient(patient);
    }

    public static void DeletePatient()
    {
        //Ask for existing id
        String patientId = GetIdentifiers.getPatientID(true);

        //Delete from database
        PatientDataAccess.deletePatient(patientId);
    }

    public static void LoadAllPatients()
    {
        //Ask for limit
        int limit =  Input.GetInt("Enter limit: ");

        //Get records and add to list
        List<Patient> patients = PatientDataAccess.loadAllPatients(limit);

        //Check if there are records
        if(patients == null || patients.isEmpty())
        {
            return;
        }

        int index = 0;
        while(index < patients.size())
        {
            Patient patient = patients.get(index);
            patient.DisplayDetails();
            index++;
        }
    }

    public static void LoadPatientById()
    {
        String patientId = GetIdentifiers.getPatientID(true);
        if(patientId == null){System.out.println("Operation cancelled"); return;}

        Patient patient = PatientDataAccess.GetPatientById(patientId);
        if(patient == null){System.out.println("Operation cancelled"); return;}

        patient.DisplayDetails();
    }

    public static void LoadPatientByParameters()
    {
        List<Patient> patients = RecordAccessGuard.getPatientByParameters();
        if(patients == null){System.out.println("Operation cancelled"); return;}

        for (int i = 0; i < patients.size(); i++) {

            patients.get(i).DisplayDetails();
        }
    }
}
