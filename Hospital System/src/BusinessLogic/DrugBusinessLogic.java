package BusinessLogic;

import DataAccess.ConflictDataAccess;
import DataAccess.DrugDataAccess;
import Models.Drug;
import PresentationLayer.GetDetails;
import PresentationLayer.GetIdentifiers;
import PresentationLayer.RecordAccessGuard;
import Utilities.Input;

import java.util.HashSet;
import java.util.List;

public class DrugBusinessLogic
{
    /**
     * Adds a new drug by gathering details then transferring model to database to be added
     */
    public static void AddDrug()
    {
        //Gather details for the drug
        Drug drug = GetDetails.getDrugDetails(false);

        if(drug == null){System.out.println("Operation cancelled"); return;}

        //Gather details of  existing drugs that conflict with the drug being added
        HashSet<String> conflicts = GetDetails.getConflictDetails();

        //Add drug to database
        DrugDataAccess.createDrug(drug);

        //Check if there are ant conflicts to add
        if(conflicts == null){System.out.println("Operation cancelled"); return;}

        //Add conflict
        for (String conflictId : conflicts)
        {
            ConflictDataAccess.addConflict(drug.getDrugID(), conflictId);
        }
    }

    /**
     * Update existing drug and gather the new details to update teh database
     */
    public static void UpdateDrug()
    {
        //Create a new drug model with the updated details
        Drug drug = GetDetails.getDrugDetails(true);

        //Check if drug exists
        if(drug == null){System.out.println("Operation cancelled"); return;}

        //Update database
        DrugDataAccess.updateDrug(drug);
    }

    /**
     * Delete existing drug based on provided id
     */
    public static void DeleteDrug()
    {
        //Get existing drug id
        String drugId = GetIdentifiers.getDrugID(true);

        //Check drug id exists
        if(drugId == null){System.out.println("Operation cancelled"); return;}

        //Delete from database
        DrugDataAccess.deleteDrug(drugId);
    }

    /**
     * Get all drug details based on limit
     */
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

    /**
     * Get details of drug based on id
     */
    public static void LoadDrugById()
    {
        //Get existing drug id
        String drugId = GetIdentifiers.getDrugID(true);
        if(drugId == null){System.out.println("Operation cancelled"); return;}

        //Create drug model based on existing drug and id
        Drug drug = DrugDataAccess.getDrugById(drugId);
        if(drug == null){System.out.println("Operation cancelled"); return;}

        //Display details of existing drug
        drug.DisplayDetails();
    }

    /**
     * Display drug details based on parameters
     */
    public static void LoadDrugByParameters()
    {
        //get list of found drugs based on parameters
        List<Drug> drugs = RecordAccessGuard.getDrugByParameters();
        if(drugs == null){System.out.println("Operation cancelled"); return;}

        //Display details of each found drug
        for (int i = 0; i < drugs.size(); i++)
        {
            drugs.get(i).DisplayDetails();
        }
    }
}
