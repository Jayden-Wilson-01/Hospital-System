package BusinessLogic;

import DataAccess.ConflictDataAccess;
import DataAccess.PrescriptionDataAccess;

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
        HashSet<String> potentialConflicts = ConflictDataAccess.getConflictsForID(newDrugID);
        if(potentialConflicts == null) {return null;}

        //Check occurs oen at a time as teh first conflict should be shown
        //Loop through the potential conflicts and check if the patient's HashSet contains them
        for (String conflictID : potentialConflicts)
        {
            if (patientMeds.contains(conflictID))
            {
                //Found a conflict! Return the identifier so the UI can show which drug is the problem
                return conflictID;
            }
        }

        return null;
    }
}
