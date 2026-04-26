package PresentationLayer;

import BusinessLogic.ConflictBusinessLogic;
import Models.*;
import Utilities.Input;

import java.time.LocalDate;
import java.util.HashSet;

public class GetDetails
{
    /**
     * Get user inputs for patient details
     * @param patientExists determines if the patient is new or existing
     * @return a patient model with the filled details
     */
    public static Patient getPatientDetails(boolean patientExists)
    {
        //Get patient id
        String patientID = GetIdentifiers.getPatientID(patientExists);
        if(patientID == null){return null;}

        //Get insurance id (optional)
        String insuranceID = null;
        boolean includeInsurance = Input.YesNo("Include insurance? ");
        if(includeInsurance)
        {
            insuranceID = GetIdentifiers.getInsuranceID(true); //Must exist
            if(insuranceID == null){return null;}
        }

        //Get doctor id
        String doctorID = null;
        boolean includeDoctor = Input.YesNo("Include primary doctor? ");
        if(includeDoctor)
        {
            doctorID = GetIdentifiers.getDoctorID(true);
            if(doctorID == null){return null;}
        }

        //Get additional details
        String firstname = Input.GetString("Enter patients firstname or 0 to cancel: ");
        if(firstname.equals("0")){return null;}

        String surname = Input.GetString("Enter patients surname or 0 to cancel: ");
        if(surname.equals("0")){return null;}

        String address = Input.GetString("Enter patients address or 0 to cancel: ");
        if(address.equals("0")){return null;}

        String phone  = Input.GetString("Enter patients phone or 0 to cancel: ");
        if(phone.equals("0")){return null;}

        String email = Input.GetString("Enter patients email or 0 to cancel: ");
        if(email.equals("0")){return null;}

        //Create and return model
        return new Patient(patientID, insuranceID, doctorID, firstname, surname, null, address, phone, email);
    }

    /**
     * Get user inputs for doctor details
     * @param doctorExists determines if the doctor is new or existing
     * @return a doctor model with the filled details
     */
    public static Doctor getDoctorDetails(boolean doctorExists)
    {
        //Determine what form of doctor to get details of: Standard or Specialist
        boolean isSpecialist = Input.YesNo("Is the doctor a specialist? ");

        //Get the doctor id
        String doctorID = GetIdentifiers.getDoctorID(doctorExists);
        if(doctorID == null){return null;}

        //Get additional details
        String firstname = Input.GetString("Enter doctors firstname or 0 to cancel: ");
        if(firstname.equals("0")){return null;}

        String surname = Input.GetString("Enter doctors surname or 0 to cancel: ");
        if(surname.equals("0")){return null;}

        String address = Input.GetString("Enter doctors address or 0 to cancel: ");
        if(address.equals("0")){return null;}

        String email = Input.GetString("Enter doctors email or 0 to cancel: ");
        if(email.equals("0")){return null;}

        String hospital = Input.GetString("Enter doctors hospital or 0 to cancel: ");
        if(hospital.equals("0")){return null;}

        if(isSpecialist)
        {
            //Select specialization
            Specializations.DisplayOptions();
            int choice = Input.GetInt("Enter specialization (1-" + Specializations.OptionAmount() + " or 0 to exit): ");
            if(choice == 0){return null;}
            String specialization = Specializations.GetSpecializationType(choice);

            int experience = Input.GetInt("Enter experience (years) or 0 to cancel: ");
            if(experience == 0){return null;}

            //Create and return model
            return new Specialist(doctorID, firstname, surname, address, email, hospital, specialization, experience);
        }

        else
        {
            //Create and return model
            return new Doctor(doctorID, firstname, surname, address, email, hospital);
        }
    }

    /**
     * Get user inputs for insurance details
     * @param insuranceExists determines if the insurance is new or existing
     * @return an insurance model with the filled details
     */
    public static Insurance getInsuranceDetails(boolean insuranceExists)
    {
        //Get insurance id
        String insuranceId = GetIdentifiers.getInsuranceID(insuranceExists);
        if(insuranceId == null){return null;}

        //Get additional details
        String company = Input.GetString("Enter insurance company name or 0 to cancel: ");
        if(company.equals("0")){return null;}

        String address = Input.GetString("Enter insurance address or 0 to cancel: ");
        if(address.equals("0")){return null;}

        String phone  = Input.GetString("Enter insurance phone or 0 to cancel: ");
        if(phone.equals("0")){return null;}

        //Create model
        return new Insurance(insuranceId, company, address, phone);
    }

    /**
     * Get user inputs for drug details
     * @param drugExists determines if the drug is new or existing
     * @return a drug model with the filled details
     */
    public static Drug getDrugDetails(boolean drugExists)
    {
        //get drug id
        String drugId = GetIdentifiers.getDrugID(drugExists);
        if(drugId == null){return null;}

        //Get additional details
        String drugName = Input.GetString("Enter drug name or 0 to cancel: ");
        if(drugName.equals("0")){return null;}

        String sideEffects =  Input.GetString("Enter side effects or 0 to cancel: ");
        if(sideEffects.equals("0")){return null;}

        String benefits =  Input.GetString("Enter benefits or 0 to cancel: ");
        if(benefits.equals("0")){return null;}

        //Create and return model
        return new Drug(drugId, drugName, sideEffects, benefits);
    }

    /**
     * Get user inputs for conflicting drug details
     * @return a set of details for conflicting drugs
     */
    public static HashSet<String> getConflictDetails()
    {
        HashSet<String> conflictIds = new HashSet<>();

        //Loop getting conflicting drug id until stopped (stopped when is null which is set in getConflictingDrugID)
        do
        {
            String drugID = GetIdentifiers.getConflictingDrugID(true);
            if(drugID == null){return conflictIds;}

            conflictIds.add(drugID);
        }
        while (true);
    }

    /**
     * Get user inputs for prescription details
     * @param prescriptionExists determines if the prescription is new or existing
     * @return a prescription model with the filled details
     */
    public static Prescription getPrescriptionDetails(boolean prescriptionExists)
    {
        //Get prescription id
        String prescriptionId = GetIdentifiers.getPrescriptionID(prescriptionExists);
        if(prescriptionId == null){return null;}

        //Get patient id
        String patientId = GetIdentifiers.getPatientID(true);
        if(patientId == null){return null;}

        String drugId = null;

        //Keep asking for drug id until there is no conflict
        do
        {
            drugId = GetIdentifiers.getDrugID(true);
            if(drugId == null){return null;}

            //Check if drug conflicts, if it does return;
            Conflict conflict = ConflictBusinessLogic.findActiveConflict(drugId, patientId);

            if (conflict == null)
            {
                break;
            }

            System.out.println("Conflict found: " + conflict.displayDetails());
        }
        while (true);


        //Get doctor id
        String doctorId = GetIdentifiers.getDoctorID(true);
        if(doctorId == null){return null;}

        //Get additional details
        LocalDate datePrescribed = Input.GetDate("Enter prescription date (yyyy-mm-dd) or 0 to cancel: ");
        if(datePrescribed == null){return null;}

        int dosage = Input.GetInt("Enter dosage or 0 to cancel: ");
        if(dosage == 0){return null;}

        int duration =  Input.GetInt("Enter duration or 0 to cancel: ");
        if(duration == 0){return null;}

        String comment = Input.GetString("Enter comment or 0 to cancel: ");
        if(comment.equals("0")){return null;}

        //Create and return model
        return new Prescription(prescriptionId, drugId, doctorId, patientId, datePrescribed, dosage, duration, comment);
    }

    /**
     * Get user inputs for visit details
     * @param visitExists determines if the visit is new or existing
     * @return a visit model with the filled details
     */
    public static Visit getVisitDetails(boolean visitExists)
    {
        //Get visit id
        String visitId = GetIdentifiers.getVisitID(visitExists);
        if(visitId == null){return null;}

        //Get doctor id
        String doctorId = GetIdentifiers.getDoctorID(true);
        if(doctorId == null){return null;}

        //get patient id
        String patientId = GetIdentifiers.getPatientID(true);
        if(patientId == null){return null;}

        //Get additional details
        LocalDate dateOfVisit = Input.GetDate("Enter date of visit (yyyy-mm-dd) or 0 to cancel: ");
        if(dateOfVisit == null){return null;}

        String symptoms = Input.GetString("Enter symptoms or 0 to cancel: ");
        if(symptoms.equals("0")){return null;}

        String diagnosis = Input.GetString("Enter diagnosis or 0 to cancel: ");
        if(diagnosis.equals("0")){return null;}

        //Create and return model
        return new Visit(visitId, patientId, doctorId, dateOfVisit, symptoms, diagnosis);
    }
}
