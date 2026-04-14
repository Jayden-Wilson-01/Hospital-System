package BusinessLogic;

import DataAccess.InsuranceDataAccess;
import Models.Insurance;
import Utilities.Input;

import java.util.List;

public class InsuranceBusinessLogic
{
    private static Insurance GetDetails(boolean includeID)
    {
        String insuranceId = "";

        if(includeID)
        {
            insuranceId = GetID(false);
        }

        String company = Input.GetString("Enter company name: ");
        String address = Input.GetString("Enter address: ");
        String phone = Input.GetString("Enter phone: ");

        //Create new model
        Insurance insurance = new Insurance();

        //Assign values
        if(includeID) {insurance.setInsuranceID(insuranceId);}
        insurance.setCompany(company);
        insurance.setAddress(address);
        insurance.setPhone(phone);

        return insurance;
    }

    private static String GetID(boolean exist)
    {
        if(!exist)
        {
            while(true)
            {
                String doctorID = Input.GetString("Enter insurance ID: ");

                exist = InsuranceDataAccess.ExistsInDatabase(doctorID);

                if(!exist)
                {
                    return doctorID;
                }
            }
        }
        else
        {
            while(true)
            {
                String doctorID = Input.GetString("Enter insurance ID: ");

                exist = InsuranceDataAccess.ExistsInDatabase(doctorID);

                if(exist)
                {
                    return doctorID;
                }
            }
        }
    }

    public static void AddInsurance()
    {
        //Gather details
        Insurance insurance = GetDetails(true);

        //Add to database
        InsuranceDataAccess.createInsurance(insurance);
    }

    public static void UpdateInsurance()
    {
        //As we are updating an existing insurance we need an existing id
        String insuranceID = GetID(true);

        //We have already asked for the id so we don't need to include it again, but we still need to set it
        Insurance insurance = GetDetails(false);
        insurance.setInsuranceID(insuranceID);

        //Update from database
        InsuranceDataAccess.updateInsurance(insurance);
    }

    public static void DeleteInsurance()
    {
        //Get id of existing insurance record
        String insuranceID = GetID(true);

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
}
