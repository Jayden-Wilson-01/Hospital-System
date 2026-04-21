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
    public static void AddVisit()
    {
        //Gather details
        Visit visit = GetDetails.getVisitDetails(false);

        if(visit == null){System.out.println("Operation cancelled"); return;}

        //Add to Database
        VisitDataAccess.createVisit(visit);
    }

    public static void UpdateVisit()
    {
        //Gather details
        Visit visit = GetDetails.getVisitDetails(true);

        if(visit == null){System.out.println("Operation cancelled"); return;}

        //Update Database
        VisitDataAccess.updateVisit(visit);
    }

    public static void DeleteVisit()
    {
        //Ask for existing id
        String visitId = GetIdentifiers.getVisitID(true);

        if(visitId == null){System.out.println("Operation cancelled"); return;}

        //Delete from database
        VisitDataAccess.deleteVisit(visitId);
    }

    public static void LoadAllVisits()
    {
        //Ask for limit
        int limit =  Input.GetInt("Enter limit: ");

        //Get records and add to list
        List<Visit> visits = VisitDataAccess.loadAllVisits();

        //Check if there are records
        if(visits == null || visits.isEmpty())
        {
            return;
        }

        int index = 0;
        while(index < visits.size())
        {
            Visit visit = visits.get(index);
            visit.DisplayDetails();
            index++;
        }
    }

    public static void LoadVisitById()
    {
        String visitId = GetIdentifiers.getDrugID(true);
        if(visitId == null){System.out.println("Operation cancelled"); return;}

        Visit visit = VisitDataAccess.getVisitById(visitId);
        if(visit == null){System.out.println("Operation cancelled"); return;}

        visit.DisplayDetails();
    }

    public static void LoadVisitByParameters()
    {
        List<Visit> visits = RecordAccessGuard.getVisitByParameters();
        if(visits == null){System.out.println("Operation cancelled"); return;}

        for (int i = 0; i < visits.size(); i++)
        {
            visits.get(i).DisplayDetails();
        }
    }
}
