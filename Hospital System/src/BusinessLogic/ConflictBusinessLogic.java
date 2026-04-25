package BusinessLogic;

import DataAccess.ConflictDataAccess;
import DataAccess.DoctorDataAccess;
import DataAccess.PrescriptionDataAccess;
import Models.Conflict;
import Models.Doctor;
import PresentationLayer.GetDetails;
import PresentationLayer.GetIdentifiers;

import java.util.HashSet;

public class ConflictBusinessLogic
{
    /**
     * Evaluates if a candidate drug causes a conflict with a patients current prescriptions.
     * @param newDrugID The identifier of teh drug being considered
     * @param patientID The identifier of the patients whose records are being checked.
     * @return The string ID of the first conflicting drug found
     * @return null if no conflicts are detected or data is available
     */
    public static String findActiveConflict(String newDrugID, String patientID)
    {
        //Gets patients current drugs patient uses via their prescriptions
        HashSet<String> patientMeds = PrescriptionDataAccess.getDrugIdByPatientId(patientID);
        if(patientMeds == null) {return null;}

        //Get the list of identifiers that conflict with the NEW drug
        HashSet<Conflict> potentialConflicts = ConflictDataAccess.getConflictsForID(newDrugID);
        if(potentialConflicts == null) {return null;}

        //Check occurs oen at a time as teh first conflict should be shown
        //Loop through the potential conflicts and check if the patient's HashSet contains them
        for (Conflict conflict : potentialConflicts)
        {
            if (patientMeds.contains(conflict.getDrugID()))
            {
                //Found a conflict! Return the identifier so the UI can show which drug is the problem
                return conflict.getDrugName();
            }
        }

        return null;
    }

    /**
     * Adds a new conflict to database
     */
    public static void addConflict()
    {
        //Get drug id
        String drugID = GetIdentifiers.getDrugID(true);

        //Get conflicting drug id
        String conflictingDrugID = GetIdentifiers.getConflictingDrugID(true);

        //Add to database
        ConflictDataAccess.addConflict(drugID, conflictingDrugID);
    }

    /**
     * Deletes an existing conflict from database
     */
    public static void deleteConflict()
    {
        //Get drug id
        String drugID = GetIdentifiers.getDrugID(true);

        //Get conflicting drug id
        String conflictingDrugID = GetIdentifiers.getConflictingDrugID(true);

        //Delete from database
        ConflictDataAccess.deleteConflict(drugID, conflictingDrugID);
    }

    public static void viewConflictByDrugID()
    {
        //Get drug id
        String drugID = GetIdentifiers.getDrugID(true);

        //Get conflicting drugs
        HashSet<Conflict> conflicts = ConflictDataAccess.getConflictsForID(drugID);

        System.out.println("Conflicting drugs");

        //Get name of each conflicting drug
        for (Conflict conflict : conflicts)
        {
            System.out.println(conflict.displayDetails());
        }
    }
}
