package BusinessLogic;

import DataAccess.*;
import Models.Prescription;
import Models.Visit;
import Utilities.Input;

import java.time.LocalDate;
import java.util.List;

public class PrescriptionBusinessLogic
{
    private static Prescription GetDetails(boolean includeId)
    {
        //Identifiers
        String prescriptionId = "";
        String patientId = "";
        String doctorId = "";
        String drugId = "";

        //Ask for the id's
        if(includeId){prescriptionId = GetPrescriptionID(false);}
        patientId = GetPatientID(); //Must exist
        doctorId = GetDoctorID(); //Must exist
        drugId = GetDrugID(); //Must exist

        //Exit the operation early if user asks to cancel
        if(patientId.equals("0") || doctorId.equals("0") || drugId.equals("0"))
        {
            return null; //cancel the operation
        }

        //Ask for details
        LocalDate datePrescribed = Input.GetDate("Enter date prescribed: ");
        int dosage =  Input.GetInt("Enter dosage: ");
        int duration =  Input.GetInt("Enter duration: ");
        String comment = Input.GetString("Enter comment: ");

        //Create model
        Prescription prescription = new Prescription();

        if(includeId){prescription.setPrescriptionID(prescriptionId);}
        prescription.setPatientID(patientId);
        prescription.setDoctorID(doctorId);
        prescription.setDrugID(drugId);
        prescription.setDatePrescribed(datePrescribed);
        prescription.setDosage(dosage);
        prescription.setDuration(duration);
        prescription.setComment(comment);

        //return model
        return prescription;
    }

    private static String GetPrescriptionID(boolean mustExist)
    {
        while(true)
        {
            String visitId = Input.GetString("Enter prescription ID: ");
            boolean exists = PrescriptionDataAccess.ExistsInDatabase(visitId);

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

    private static String GetDrugID()
    {
        while(true)
        {
            String drugId = Input.GetString("Enter drug ID ot 0 to exit: ");
            boolean exists = DrugDataAccess.ExistsInDatabase(drugId);

            if(drugId.equals("0"))
            {
                return drugId;
            }

            if (exists)
            {
                return drugId;
            }
            else
            {
                System.out.println("Error: ID does not exist. Please enter a valid ID to update.");
            }
        }
    }

    public static void AddPrescription()
    {
        //Gather details
        Prescription prescription = GetDetails(true);

       if(prescription != null)
       {
           //Add to database
           System.out.println(prescription.getPrescriptionID());
           PrescriptionDataAccess.createPrescription(prescription);
       }
    }

    public static void UpdatePrescription()
    {
        //Ask for existing id
        String prescriptionID = GetPrescriptionID(true);

        //Gather new details
        Prescription prescription = GetDetails(false);
        prescription.setPrescriptionID(prescriptionID);

        //Update database
        PrescriptionDataAccess.updatePrescription(prescription);
    }

    public static void DeletePrescription()
    {
        //Ask for existing id
        String prescriptionID = GetPrescriptionID(true);

        //Delete from database
        PrescriptionDataAccess.deletePrescription(prescriptionID);
    }

    public static void LoadAllPrescriptions()
    {
        //Ask for limit
        int limit =  Input.GetInt("Enter limit: ");

        //Get records and add to list
        List<Prescription> prescriptions = PrescriptionDataAccess.loadAllPrescriptions();

        //Check if there are records
        if(prescriptions == null || prescriptions.isEmpty())
        {
            return;
        }

        int index = 0;
        while(index < prescriptions.size())
        {
            Prescription prescription = prescriptions.get(index);
            prescription.DisplayDetails();
            index++;
        }
    }
}
