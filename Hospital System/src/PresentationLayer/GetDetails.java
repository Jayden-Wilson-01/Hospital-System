package PresentationLayer;

import Models.*;
import Utilities.Input;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;

public class GetDetails
{
    public static Patient getPatientDetails(boolean patientExists)
    {
        String patientID = GetIdentifiers.getPatientID(patientExists);
        if(patientID == null){return null;}

        String insuranceID = null;
        boolean includeInsurance = Input.YesNo("Include insurance? ");
        if(includeInsurance)
        {
            insuranceID = GetIdentifiers.getInsuranceID(true); //Must exist
            if(insuranceID == null){return null;}
        }

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
        return new Patient(patientID, insuranceID, firstname, surname, null, address, phone, email);
    }

    public static Doctor getDoctorDetails(boolean doctorExists)
    {
        boolean isSpecialist = Input.YesNo("Is the doctor a specialist? ");

        String doctorID = GetIdentifiers.getDoctorID(doctorExists);
        if(doctorID == null){return null;}

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

            int experience = Input.GetInt("Enter experience (years) or 0 to exit: ");
            if(experience == 0){return null;}

            //Create model
            Specialist specialist = new Specialist();
            specialist.setDoctorID(doctorID);
            specialist.setFirstName(firstname);
            specialist.setSurname(surname);
            specialist.setAddress(address);
            specialist.setEmail(email);
            specialist.setHospital(hospital);
            specialist.setSpecialization(specialization);
            specialist.setExperience(experience);

            return specialist;
        }

        else
        {
            //Create model
            Doctor doctor = new Doctor();
            doctor.setDoctorID(doctorID);
            doctor.setFirstName(firstname);
            doctor.setSurname(surname);
            doctor.setAddress(address);
            doctor.setEmail(email);
            doctor.setHospital(hospital);

            return doctor;
        }
    }

    public static Insurance getInsuranceDetails(boolean insuranceExists)
    {
        String insuranceId = GetIdentifiers.getInsuranceID(insuranceExists);
        if(insuranceId == null){return null;}

        String company = Input.GetString("Enter insurance company name or 0 to cancel: ");
        if(company.equals("0")){return null;}

        String address = Input.GetString("Enter insurance address or 0 to cancel: ");
        if(address.equals("0")){return null;}

        String phone  = Input.GetString("Enter insurance phone or 0 to cancel: ");
        if(phone.equals("0")){return null;}

        //Create model
        Insurance insurance = new Insurance();
        insurance.setInsuranceID(insuranceId);
        insurance.setCompany(company);
        insurance.setAddress(address);
        insurance.setPhone(phone);

        return insurance;
    }

    public static Drug getDrugDetails(boolean drugExists)
    {
        String drugId = GetIdentifiers.getDrugID(drugExists);
        if(drugId == null){return null;}

        String drugName = Input.GetString("Enter drug name or 0 to exit: ");
        if(drugName.equals("0")){return null;}

        String sideEffects =  Input.GetString("Enter side effects or 0 to cancel: ");
        if(sideEffects.equals("0")){return null;}

        String benefits =  Input.GetString("Enter benefits or 0 to cancel: ");
        if(benefits.equals("0")){return null;}

        //Create and return model
        return new Drug(drugId, drugName, sideEffects, benefits);
    }

    public static HashSet<String> getConflictDetails()
    {
        HashSet<String> conflictIds = new HashSet<>();

        do
        {
            String drugID = GetIdentifiers.getConflictingDrugID(true);
            if(drugID == null){return conflictIds;}

            conflictIds.add(drugID);
        }
        while (true);
    }

    public static Prescription getPrescriptionDetails(boolean prescriptionExists)
    {
        String prescriptionId = GetIdentifiers.getPrescriptionID(prescriptionExists);
        if(prescriptionId == null){return null;}

        String drugId = GetIdentifiers.getDrugID(true);
        if(drugId == null){return null;}

        String doctorId = GetIdentifiers.getDoctorID(true);
        if(doctorId == null){return null;}

        String patientId = GetIdentifiers.getPatientID(true);
        if(patientId == null){return null;}

        LocalDate datePrescribed = Input.GetDate("Enter prescription date (yyyy-mm-dd) or 0 to exit: ");
        if(datePrescribed == null){return null;}

        int dosage = Input.GetInt("Enter dosage or 0 to exit: ");
        if(dosage == 0){return null;}

        int duration =  Input.GetInt("Enter duration or 0 to exit: ");
        if(duration == 0){return null;}

        String comment = Input.GetString("Enter comment or 0 to exit: ");
        if(comment.equals("0")){return null;}

        //Create model
        Prescription prescription = new Prescription();
        prescription.setPrescriptionID(prescriptionId);
        prescription.setPatientID(patientId);
        prescription.setDoctorID(doctorId);
        prescription.setDrugID(drugId);
        prescription.setDatePrescribed(datePrescribed);
        prescription.setDosage(dosage);
        prescription.setDuration(duration);
        prescription.setComment(comment);

        return prescription;
    }

    public static Visit getVisitDetails(boolean visitExists)
    {
        String visitId = GetIdentifiers.getVisitID(visitExists);
        if(visitId == null){return null;}

        String doctorId = GetIdentifiers.getDoctorID(true);
        if(doctorId == null){return null;}

        String patientId = GetIdentifiers.getPatientID(true);
        if(patientId == null){return null;}

        LocalDate dateOfVisit = Input.GetDate("Enter date of visit (yyyy-mm-dd) or 0 to exit: ");
        if(dateOfVisit == null){return null;}

        String symptoms = Input.GetString("Enter symptoms or 0 to exit: ");
        if(symptoms.equals("0")){return null;}

        String diagnosis = Input.GetString("Enter diagnosis or 0 to exit: ");
        if(diagnosis.equals("0")){return null;}

        //Create and return model
        return new Visit(visitId,  doctorId, patientId, dateOfVisit, symptoms, diagnosis);
    }
}
