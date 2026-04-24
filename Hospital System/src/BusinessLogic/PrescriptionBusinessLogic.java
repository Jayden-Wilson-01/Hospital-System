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
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class PrescriptionBusinessLogic
{
    /**
     * Add new prescription by gathering details and adding to database
     */
    public static void AddPrescription()
    {
        //Gather details
        Prescription prescription = GetDetails.getPrescriptionDetails(false);

        //Check prescription exists
        if(prescription == null){System.out.println("Operation cancelled"); return;}

        //Add to database
        PrescriptionDataAccess.createPrescription(prescription);
    }

    /**
     * Update existing prescription by gathering new details and updating database
     */
    public static void UpdatePrescription()
    {
        //Gather details
        Prescription prescription = GetDetails.getPrescriptionDetails(false);

        //Check prescription exists
        if(prescription == null){System.out.println("Operation cancelled"); return;}

        //Update database
        PrescriptionDataAccess.updatePrescription(prescription);
    }

    /**
     * Delete existing prescription by gathering id and deleting from database
     */
    public static void DeletePrescription()
    {
        //Get existing prescription id
        String prescriptionID = GetIdentifiers.getPrescriptionID(true);

        //Check prescription id exists
        if(prescriptionID == null){System.out.println("Operation cancelled"); return;}

        //Delete from database
        PrescriptionDataAccess.deletePrescription(prescriptionID);
    }

    /**
     * Display details of each prescription based on the provided limit
     */
    public static void LoadAllPrescriptions()
    {
        //Get limit
        int limit =  Input.GetInt("Enter limit: ");

        //Get prescriptions and add to list
        List<Prescription> prescriptions = PrescriptionDataAccess.loadAllPrescriptions();

        //Check if there are records
        if(prescriptions == null || prescriptions.isEmpty())
        {
            return;
        }

        //Display details of each prescription
        int index = 0;
        while(index < prescriptions.size())
        {
            Prescription prescription = prescriptions.get(index);
            prescription.DisplayDetails();
            index++;
        }
    }

    /**
     * Display details of prescription based on provided id
     */
    public static void LoadPrescriptionById()
    {
        //Get existing prescriptions id
        String prescriptionId = GetIdentifiers.getPrescriptionID(true);
        if(prescriptionId == null){System.out.println("Operation cancelled"); return;}

        //Create  new prescription model and populate with details of found prescription
        Prescription prescription = PrescriptionDataAccess.getPrescriptionById(prescriptionId);
        if(prescription == null){System.out.println("Operation cancelled"); return;}

        //Show details of prescription
        prescription.DisplayDetails();
    }

    /**
     * Display details of each prescription found based on parameters
     */
    public static void LoadPrescriptionByParameters()
    {
        //Create list of prescriptions that were found
        List<Prescription> prescriptions = RecordAccessGuard.getPrescriptionByParameters();
        if(prescriptions == null || prescriptions.isEmpty()) {System.out.println("Operation cancelled"); return;}

        //Show details of each found prescription
        for (int i = 0; i < prescriptions.size(); i++)
        {
            prescriptions.get(i).DisplayDetails();
        }
    }
}