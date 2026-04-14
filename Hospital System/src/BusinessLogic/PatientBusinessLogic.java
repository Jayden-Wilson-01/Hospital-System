package BusinessLogic;

import DataAccess.PatientDataAccess;
import Models.Patient;
import Utilities.Input;

import java.util.List;

public class PatientBusinessLogic
{

    //Works
    public static void LoadAllPatients()
    {
        int limit = Input.GetInt("Enter a load limit: ");

        List<Patient> patients = PatientDataAccess.loadAllPatients(limit);

        int index = 0;
        while (index < patients.size())
        {
            System.out.println();
            System.out.println("Patient ID: " + patients.get(index).getPatientID());
            System.out.println("Patient firstname: " + patients.get(index).getFirstName());
            System.out.println("Patient surname: " + patients.get(index).getSurname());
            System.out.println("Patient address: " + patients.get(index).getAddress());
            System.out.println("Patient phone: " + patients.get(index).getPhone());
            System.out.println("Patient email: " + patients.get(index).getEmail());
            index++;
        }
    }

    //Works
    public static void AddPatient()
    {
        //Gather details
        String patientID = Input.GetString("Enter new Patient ID: ");
        String firstName = Input.GetString("Enter new first name: ");
        String lastName = Input.GetString("Enter new last name: ");
        String address = Input.GetString("Enter new address: ");
        String phone = Input.GetString("Enter new phone number: ");
        String email = Input.GetString("Enter new email: ");

        //Create model
        Patient patient = new Patient(patientID, firstName, lastName, address, phone, email);

        //Add to database
        PatientDataAccess.createPatient(patient);
    }

    //Works
    public static void UpdatePatient()
    {
        //Get patient ID
        boolean patientExists = false;
        String patientID = "";

        while(true)
        {
            patientID = Input.GetString("Enter existing patient ID: "); //Check if it even exists
            patientExists = PatientDataAccess.ExistsInDatabase(patientID);

            if(patientExists)
            {
                break;
            }
            else
            {
                System.out.println("Error: Patient id doesn't exist!");
            }
        }

        String firstName = Input.GetString("Enter new first name: ");
        String lastName = Input.GetString("Enter new last name: ");
        String address = Input.GetString("Enter new address: ");
        String phone = Input.GetString("Enter new phone number: ");
        String email = Input.GetString("Enter new email: ");

        //Create new model
        Patient patient = new Patient(patientID, firstName, lastName, address, phone, email);

        //Update
        PatientDataAccess.updatePatient(patient);
    }

    //Works
    public static void DeletePatient()
    {
        boolean patientExists = false;
        String patientID = "";

        while(!patientExists)
        {
            patientID = Input.GetString("Enter existing Patient ID or 0 to exit: "); //Check if it even exists

            if(patientID.equals("0"))
            {
                return;
            }

            patientExists = PatientDataAccess.ExistsInDatabase(patientID);

            if(patientExists)
            {
                break;
            }
            else
            {
                System.out.println("Error: Patient id doesn't exist!");
            }
        }

        //Delete
        PatientDataAccess.deletePatient(patientID);
    }
}
