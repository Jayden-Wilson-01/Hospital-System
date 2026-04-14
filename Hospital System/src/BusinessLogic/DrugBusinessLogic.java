package BusinessLogic;

import DataAccess.DrugDataAccess;
import Models.Drug;
import Utilities.Input;

import java.util.List;

public class DrugBusinessLogic
{
    private static Drug GetDetails(boolean includeID)
    {
        String drugId = "";

        //Get Details
        if(includeID) {drugId = GetID(false);}
        String drugName = Input.GetString("Enter drug name: ");
        String drugSideEffects = Input.GetString("Enter drug side effects: ");
        String drugBenefits =  Input.GetString("Enter drug benefits: ");

        //Create new model
        Drug drug = new Drug();

        //Populate model
        if(includeID){drug.setDrugID(drugId);}
        drug.setDrugName(drugName);
        drug.setSideEffects(drugSideEffects);
        drug.setBenefits(drugBenefits);

        //Return drug model
        return drug;
    }

    private static String GetID(boolean exist)
    {
        if(!exist)
        {
            while(true)
            {
                String doctorID = Input.GetString("Enter drug ID: ");

                exist = DrugDataAccess.ExistsInDatabase(doctorID);

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
                String doctorID = Input.GetString("Enter drug ID: ");

                exist = DrugDataAccess.ExistsInDatabase(doctorID);

                if(exist)
                {
                    return doctorID;
                }
            }
        }
    }

    public static void AddDrug()
    {
        //Gather details
        Drug drug = GetDetails(true);

        //Add to database
        DrugDataAccess.createDrug(drug);
    }

    public static void UpdateDrug()
    {
        //Ask for existing id
        String drugId  = GetID(true);

        //Gather new details
        Drug drug = GetDetails(false);
        drug.setDrugID(drugId);

        //Update database
        DrugDataAccess.updateDrug(drug);
    }

    public static void DeleteDrug()
    {
        //Ask for existing id
        String drugId =  GetID(true);

        //Delete from database
        DrugDataAccess.deleteDrug(drugId);
    }

    public static void LoadAllDrugs()
    {
        //Ask for limit
        int limit = Input.GetInt("Enter limit: ");

        //Load all drugs into list
        List<Drug> drugs = DrugDataAccess.loadAllDrugs(limit);

        //Display details of each drug
        if(drugs != null)
        {
            int index = 0;
            while(index < drugs.size())
            {
                drugs.get(index).DisplayDetails();
                index++;
            }
        }
    }
}
