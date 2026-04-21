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
    public static void AddInsurance()
    {
        //Gather details
        Insurance insurance = GetDetails.getInsuranceDetails(false);

        if(insurance == null){System.out.println("Operation Cancelled"); return;}

        //Add to database
        InsuranceDataAccess.createInsurance(insurance);
    }

    public static void UpdateInsurance()
    {
        //Gather details
        Insurance insurance = GetDetails.getInsuranceDetails(true);

        if(insurance == null){System.out.println("Operation Cancelled"); return;}

        //Update database
        InsuranceDataAccess.updateInsurance(insurance);
    }

    public static void DeleteInsurance()
    {
        //Get id of existing insurance record
        String insuranceID = GetIdentifiers.getInsuranceID(true);
        if(insuranceID == null){System.out.println("Operation Cancelled"); return;}

        //Delete from database
        InsuranceDataAccess.deleteInsurance(insuranceID);
    }

    public static void LoadAllInsurance()
    {
        //Get limit for how many records you wish to show
        int limit = Input.GetInt("Enter load limit: ");

        //List of all the insurance models
        List<Insurance> insurance = InsuranceDataAccess.loadAllInsurance(limit);

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

    public static void LoadInsuranceById()
    {
        String insuranceId = GetIdentifiers.getInsuranceID(true);
        if(insuranceId == null){System.out.println("Operation cancelled"); return;}

        Insurance insurance = InsuranceDataAccess.getInsuranceById(insuranceId);
        if(insurance == null){System.out.println("Operation cancelled"); return;}

        insurance.DisplayDetails();
    }

    public static void LoadInsuranceByParameters()
    {
        List<Insurance> insurances = RecordAccessGuard.getInsuranceByParameters();
        if(insurances == null){System.out.println("Operation cancelled"); return;}

        for (int i = 0; i < insurances.size(); i++)
        {
            insurances.get(i).DisplayDetails();
        }
    }
}
