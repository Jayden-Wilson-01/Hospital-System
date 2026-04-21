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
    public static void AddDrug()
    {
        //Gather details for the drug
        Drug drug = GetDetails.getDrugDetails(false);

        if(drug == null){System.out.println("Operation cancelled"); return;}

        //Gather details for conflicting id's
        HashSet<String> conflicts = GetDetails.getConflictDetails();

        //Add drug to database
        DrugDataAccess.createDrug(drug);

        //Add conflict
        if(conflicts == null){System.out.println("Operation cancelled"); return;}

        for (String conflictId : conflicts)
        {
            ConflictDataAccess.addConflict(drug.getDrugID(), conflictId);
        }
    }

    public static void UpdateDrug()
    {
        //Gather details
        Drug drug = GetDetails.getDrugDetails(true);

        if(drug == null){System.out.println("Operation cancelled"); return;}

        //Update database
        DrugDataAccess.updateDrug(drug);
    }

    public static void DeleteDrug()
    {
        //Ask for existing id
        String drugId = GetIdentifiers.getDrugID(true);

        if(drugId == null){System.out.println("Operation cancelled"); return;}

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

    public static void LoadDrugById()
    {
        String drugId = GetIdentifiers.getDrugID(true);
        if(drugId == null){System.out.println("Operation cancelled"); return;}

        Drug drug = DrugDataAccess.getDrugById(drugId);
        if(drug == null){System.out.println("Operation cancelled"); return;}

        drug.DisplayDetails();
    }

    public static void LoadDrugByParameters()
    {
        List<Drug> drugs = RecordAccessGuard.getDrugByParameters();
        if(drugs == null){System.out.println("Operation cancelled"); return;}

        for (int i = 0; i < drugs.size(); i++)
        {
            drugs.get(i).DisplayDetails();
        }
    }
}
