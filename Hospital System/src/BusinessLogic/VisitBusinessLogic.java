package BusinessLogic;

import DataAccess.DoctorDataAccess;
import DataAccess.DrugDataAccess;
import DataAccess.PatientDataAccess;
import DataAccess.VisitDataAccess;
import Models.Doctor;
import Models.Drug;
import Models.Visit;
import PresentationLayer.GetDetails;
import PresentationLayer.GetIdentifiers;
import PresentationLayer.RecordAccessGuard;
import Utilities.Input;

import java.time.LocalDate;
import java.util.List;

public class VisitBusinessLogic
{
    /**
     * Add new visit by gathering new details and adding to database
     */
    public static void AddVisit()
    {
        //Gather details
        Visit visit = GetDetails.getVisitDetails(false);

        //Check if visit exists
        if(visit == null){System.out.println("Operation cancelled"); return;}

        //Add to Database
        VisitDataAccess.createVisit(visit);
    }

    /**
     * Update existing visit by gathering new details and update database
     */
    public static void UpdateVisit()
    {
        //Gather details
        Visit visit = GetDetails.getVisitDetails(true);

        //Check if visit exists
        if(visit == null){System.out.println("Operation cancelled"); return;}

        //Update Database
        VisitDataAccess.updateVisit(visit);
    }

    /**
     * Delete existing visit by gathering existing id and deleting from database
     */
    public static void DeleteVisit()
    {
        //Get existing visit id
        String visitId = GetIdentifiers.getVisitID(true);

        //Check if visit exists
        if(visitId == null){System.out.println("Operation cancelled"); return;}

        //Delete from database
        VisitDataAccess.deleteVisit(visitId);
    }

    /**
     * Display details of each visit based on limit
     */
    public static void LoadAllVisits()
    {
        //Get limit
        int limit =  Input.GetInt("Enter limit: ");

        //Get each visit and add to list
        List<Visit> visits = VisitDataAccess.loadAllVisits(limit);

        //Check if there are records
        if(visits == null || visits.isEmpty())
        {
            return;
        }

        //Show details of each visit
        int index = 0;
        while(index < visits.size())
        {
            Visit visit = visits.get(index);
            visit.DisplayDetails();
            index++;
        }
    }

    /**
     * Display details of each visit based on the provided id
     */
    public static void LoadVisitById()
    {
        //Get id of existing visit
        String visitId = GetIdentifiers.getDrugID(true);
        if(visitId == null){System.out.println("Operation cancelled"); return;}

        //Create new visit model and populate with the details of the found id
        Visit visit = VisitDataAccess.getVisitById(visitId);
        if(visit == null){System.out.println("Operation cancelled"); return;}

        //Show details of visit
        visit.DisplayDetails();
    }

    /**
     * Display details of visit based on parameters
     */
    public static void LoadVisitByParameters()
    {
        //Add each found visit to the list
        List<Visit> visits = RecordAccessGuard.getVisitByParameters();
        if(visits == null){System.out.println("Operation cancelled"); return;}

        //Show details of each found visit
        for (int i = 0; i < visits.size(); i++)
        {
            visits.get(i).DisplayDetails();
        }
    }
}
