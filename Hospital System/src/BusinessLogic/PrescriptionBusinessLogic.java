package BusinessLogic;

import DataAccess.*;
import Models.Drug;
import Models.Prescription;
import Models.Visit;
import PresentationLayer.GetDetails;
import PresentationLayer.GetIdentifiers;
import PresentationLayer.RecordAccessGuard;
import Utilities.Input;

import java.time.LocalDate;
import java.util.List;

public class PrescriptionBusinessLogic
{
    public static void AddPrescription()
    {
        //Gather details
        Prescription prescription = GetDetails.getPrescriptionDetails(false);

        if(prescription == null){System.out.println("Operation cancelled"); return;}

        //Add to database
        PrescriptionDataAccess.createPrescription(prescription);
    }

    public static void UpdatePrescription()
    {
        //Gather details
        Prescription prescription = GetDetails.getPrescriptionDetails(false);

        if(prescription == null){System.out.println("Operation cancelled"); return;}

        //Update database
        PrescriptionDataAccess.updatePrescription(prescription);
    }

    public static void DeletePrescription()
    {
        //Ask for existing id
        String prescriptionID = GetIdentifiers.getPrescriptionID(true);

        if(prescriptionID == null){System.out.println("Operation cancelled"); return;}

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

    public static void LoadPrescriptionById()
    {
        String prescriptionId = GetIdentifiers.getPrescriptionID(true);
        if(prescriptionId == null){System.out.println("Operation cancelled"); return;}

        Prescription prescription = PrescriptionDataAccess.getPrescriptionById(prescriptionId);
        if(prescription == null){System.out.println("Operation cancelled"); return;}

        prescription.DisplayDetails();
    }

    public static void LoadPrescriptionByParameters()
    {
        List<Prescription> prescriptions = RecordAccessGuard.getPrescriptionByParameters();
        if(prescriptions == null || prescriptions.isEmpty()) {System.out.println("Operation cancelled"); return;}

        for (int i = 0; i < prescriptions.size(); i++)
        {
            prescriptions.get(i).DisplayDetails();
        }
    }
}
