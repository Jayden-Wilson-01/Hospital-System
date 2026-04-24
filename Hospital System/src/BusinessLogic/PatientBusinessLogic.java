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
    /**
     * Add new patient by gathering new details and adding to database
     */
    public static void AddPatient()
    {
        //Gather details
        Patient patient = GetDetails.getPatientDetails(false);

        if(patient == null){System.out.println("Operation cancelled"); return;}

        //Add to database
        PatientDataAccess.createPatient(patient);
    }

    /**
     * Update existing patient by gathering new details and updating database
     */
    public static void UpdatePatient()
    {
        //Gather new details
        Patient patient = GetDetails.getPatientDetails(true);

        if(patient == null){System.out.println("Operation cancelled"); return;}

        //Update database
        PatientDataAccess.updatePatient(patient);
    }

    /**
     * Delete existing patient based on provided id
     */
    public static void DeletePatient()
    {
        //Get existing patient id
        String patientId = GetIdentifiers.getPatientID(true);

        //Delete from database
        PatientDataAccess.deletePatient(patientId);
    }

    /**
     * Display details of each patient based on provided limit
     */
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

        //Show details of each patient
        int index = 0;
        while(index < patients.size())
        {
            Patient patient = patients.get(index);
            patient.DisplayDetails();
            index++;
        }
    }

    /**
     * Display details of patient based on id
     */
    public static void LoadPatientById()
    {
        //Get existing patient id
        String patientId = GetIdentifiers.getPatientID(true);
        if(patientId == null){System.out.println("Operation cancelled"); return;}

        //Create patient model and populate with details of found patient
        Patient patient = PatientDataAccess.GetPatientById(patientId);
        if(patient == null){System.out.println("Operation cancelled"); return;}

        //Show details of found patient
        patient.DisplayDetails();
    }

    /**
     * Display details of patients based on parameters
     */
    public static void LoadPatientByParameters()
    {
        //create a list of patients found based on provided parameters
        List<Patient> patients = RecordAccessGuard.getPatientByParameters();
        if(patients == null){System.out.println("Operation cancelled"); return;}

        //Show details of each patient
        for (int i = 0; i < patients.size(); i++) {

            patients.get(i).DisplayDetails();
        }
    }
}
