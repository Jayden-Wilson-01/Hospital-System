package BusinessLogic;

import DataAccess.InsuranceDataAccess;
import DataAccess.PatientDataAccess;
import Models.Doctor;
import Models.Insurance;
import Models.Patient;
import PresentationLayer.GetDetails;
import PresentationLayer.GetIdentifiers;
import PresentationLayer.RecordAccessGuard;
import Utilities.Input;

import java.util.List;

public class InsuranceBusinessLogic
{
    /**
     * Add insurance by gathering details and adding to database
     */
    public static void AddInsurance()
    {
        //Gather details
        Insurance insurance = GetDetails.getInsuranceDetails(false);

        //Check if insurance model exists
        if(insurance == null){System.out.println("Operation Cancelled"); return;}

        //Add to database
        InsuranceDataAccess.createInsurance(insurance);
    }

    /**
     * Update insurance by gathering details and updating database
     */
    public static void UpdateInsurance()
    {
        //Gather details
        Insurance insurance = GetDetails.getInsuranceDetails(true);

        //Check if insurance exists
        if(insurance == null){System.out.println("Operation Cancelled"); return;}

        //Update database
        InsuranceDataAccess.updateInsurance(insurance);
    }

    /**
     * Delete insurance based on id and delete from database
     */
    public static void DeleteInsurance()
    {
        //Get id of existing insurance record
        String insuranceID = GetIdentifiers.getInsuranceID(true);
        if(insuranceID == null){System.out.println("Operation Cancelled"); return;}

        //Delete from database
        InsuranceDataAccess.deleteInsurance(insuranceID);
    }

    /**
     * Load all insurance company details based on a provided limit
     */
    public static void LoadAllInsurance()
    {
        //Get limit for how many records you wish to show
        int limit = Input.GetInt("Enter load limit: ");

        //List of all the insurance models
        List<Insurance> insurance = InsuranceDataAccess.loadAllInsurance(limit);

        //Display details of each insurance company
        int index = 0;
        if (insurance != null)
        {
            while(index < insurance.size())
            {
                insurance.get(index).DisplayDetails();
                index++;
            }
        }
    }

    /**
     * Show details of insurance companies based on id
     */
    public static void LoadInsuranceById()
    {
        //Get existing insurance id
        String insuranceId = GetIdentifiers.getInsuranceID(true);
        if(insuranceId == null){System.out.println("Operation cancelled"); return;}

        //Create insurance model of provided insurance
        Insurance insurance = InsuranceDataAccess.getInsuranceById(insuranceId);
        if(insurance == null){System.out.println("Operation cancelled"); return;}

        //Display details of insurance
        insurance.DisplayDetails();
    }

    /**
     * Show details of insurance companies based on parameters
     */
    public static void LoadInsuranceByParameters()
    {
        //List of insurance companies found based on parameters
        List<Insurance> insurances = RecordAccessGuard.getInsuranceByParameters();
        if(insurances == null){System.out.println("Operation cancelled"); return;}

        //Show details of each insurance company
        for (int i = 0; i < insurances.size(); i++)
        {
            insurances.get(i).DisplayDetails();
        }
    }
}
