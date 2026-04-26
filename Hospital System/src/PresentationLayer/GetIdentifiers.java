package PresentationLayer;

import DataAccess.*;
import Models.*;
import Utilities.Input;

public class GetIdentifiers
{
    /**
     * Get id of patient
     * @param patientExists determine whether to get id of new or existing patient
     * @return the patient id string
     */
    public static String getPatientID(boolean patientExists)
    {
        //Get id of an existing patient
        if(patientExists)
        {
            do
            {
                //Get patient id
                String patientId = Input.GetString("Enter Patient ID or 0 to cancel: ");
                if(patientId.equals("0")){return null;}

                //Check patient exists
                Patient patient = PatientDataAccess.GetPatientById(patientId);

                if(patient != null)
                {
                    return patientId;
                }

                System.out.print("Patient not found. Try again.\n");
            }
            while (true);
        }

        //Get id of a new patient
        else
        {
            do
            {
                //Get patient id
                String patientId = Input.GetString("Enter Patient ID or 0 to cancel: ");
                if(patientId.equals("0")){return null;}

                Patient patient = PatientDataAccess.GetPatientById(patientId);

                if(patient == null)
                {
                    return patientId;
                }

                System.out.println("Patient found, ID must be unique. Try again.\n");
            }
            while (true);
        }
    }

    /**
     * Get id of doctor
     * @param doctorExists determine whether to get id of new or existing doctor
     * @return the doctor id string
     */
    public static String getDoctorID(boolean doctorExists)
    {
        //Get id of existing doctor
        if(doctorExists)
        {
            do
            {
                //Get doctor id
                String doctorId = Input.GetString("Enter Doctor ID or 0 to cancel: ");
                if(doctorId.equals("0")) {return null;}

                //Check doctor exists
                Doctor doctor = DoctorDataAccess.getDoctorById(doctorId);

                if(doctor != null)
                {
                    return doctorId;
                }

                System.out.println("Doctor not found. Try again.\n");
            }
            while (true);
        }

        //Get id of new doctor
        else
        {
            do
            {
                //Get doctor id
                String doctorId = Input.GetString("Enter Doctor ID or 0 to cancel: ");
                if(doctorId.equals("0")) {return null;}

                //Check doctor exists
                Doctor doctor = DoctorDataAccess.getDoctorById(doctorId);

                if(doctor == null)
                {
                    return doctorId;
                }

                System.out.println("Doctor found, ID must be unique. Try again.\n");
            }
            while (true);
        }
    }

    /**
     * Get id of drug
     * @param drugExists determine whether to get id of new or existing drug
     * @return the drug id string
     */
    public static String getDrugID(boolean drugExists)
    {
        //Get id of existing drug
        if(drugExists)
        {
            do
            {
                //Get drug id
                String drugId = Input.GetString("Enter Drug ID or 0 to cancel: ");
                if(drugId.equals("0")) {return null;}

                Drug drug = DrugDataAccess.getDrugById(drugId);

                if(drug != null)
                {
                    return drugId;
                }

                System.out.println("Drug not found. Try again.\n");
            }
            while (true);
        }

        //Get id of new drug
        else
        {
            do
            {
                //Get drug id
                String drugId = Input.GetString("Enter Drug ID or 0 to cancel: ");
                if(drugId.equals("0")) {return null;}

                Drug drug = DrugDataAccess.getDrugById(drugId);

                if(drug == null)
                {
                    return drugId;
                }

                System.out.println("Drug found, ID must be unique. Try again.\n");
            }
            while (true);
        }
    }

    /**
     * Get id of conflicting drug
     * @param drugExists determine whether to get id of new or existing drug
     * @return the drug id string
     */
    public static String getConflictingDrugID(boolean drugExists)
    {
        //Get id of existing drug
        if(drugExists)
        {
            do
            {
                //Get drug id
                String drugId = Input.GetString("Enter ID of conflicting Drug or 0 to finish: ");
                if(drugId.equals("0")) {return null;}

                //Get
                Drug drug = DrugDataAccess.getDrugById(drugId);

                if(drug != null)
                {
                    return drugId;
                }

                System.out.println("Drug not found. Try again.\n");
            }
            while (true);
        }

        //Get id of new drug
        else
        {
            do
            {
                //Get drug id
                String drugId = Input.GetString("Enter ID of conflicting Drug or 0 to finish: ");
                if(drugId.equals("0")) {return null;}

                Drug drug = DrugDataAccess.getDrugById(drugId);

                if(drug == null)
                {
                    return drugId;
                }

                System.out.println("Drug found, ID must be unique. Try again.\n");
            }
            while (true);
        }
    }

    /**
     * Get id of insurance
     * @param insuranceExists determine whether to get id of new or existing insurance company
     * @return the insurance id string
     */
    public static String getInsuranceID(boolean insuranceExists)
    {
        //Get id of an existing insurance
        if(insuranceExists)
        {
            do
            {
                //Get insurance id
                String insuranceId = Input.GetString("Enter Insurance ID or 0 to cancel: ");
                if(insuranceId.equals("0")){return null;}

                Insurance insurance = InsuranceDataAccess.getInsuranceById(insuranceId);

                if(insurance != null)
                {
                    return insuranceId;
                }

                System.out.println("Insurance not found. Try again.\n");
            }
            while (true);
        }

        //Get id of new insurance
        else
        {
            do
            {
                //Get insurance id
                String insuranceId = Input.GetString("Enter Insurance ID or 0 to cancel: ");
                if(insuranceId.equals("0")){return null;}

                Insurance insurance = InsuranceDataAccess.getInsuranceById(insuranceId);

                if(insurance == null)
                {
                    return insuranceId;
                }

                System.out.println("Insurance found, ID must be unique. Try again.\n");
            }
            while (true);
        }
    }

    /**
     * Get id of prescription
     * @param prescriptionExists determine whether to get id of new or existing prescription
     * @return the prescription id string
     */
    public static String getPrescriptionID(boolean prescriptionExists)
    {
        //Get id of an existing prescription
        if(prescriptionExists)
        {
            do
            {
                //Get prescription id
                String prescriptionId = Input.GetString("Enter Prescription ID or 0 to cancel: ");
                if(prescriptionId.equals("0")) {return null;}

                Prescription prescription = PrescriptionDataAccess.getPrescriptionById(prescriptionId);

                if(prescription != null)
                {
                    return prescriptionId;
                }

                System.out.println("Prescription not found. Try again.\n");
            }
            while (true);
        }

        else
        {
            //Get id of new prescription
            do
            {
                //Get prescription id
                String prescriptionId = Input.GetString("Enter Prescription ID or 0 to cancel: ");
                if(prescriptionId.equals("0")) {return null;}

                Prescription prescription = PrescriptionDataAccess.getPrescriptionById(prescriptionId);

                if(prescription == null)
                {
                    return prescriptionId;
                }

                System.out.println("Prescription found, ID must be unique. Try again.\n");
            }
            while (true);
        }
    }

    /**
     * Get id of visit
     * @param visitExists determine whether to get id of new or existing visit
     * @return the visit id string
     */
    public static String getVisitID(boolean visitExists)
    {
        //Get id of an existing visit
        if(visitExists)
        {
            do
            {
                //Get visit id
                String visitId = Input.GetString("Enter Visit ID or 0 to cancel: ");
                if(visitId.equals("0")) {return null;}

                Visit visit = VisitDataAccess.getVisitById(visitId);

                if(visit != null)
                {
                    return visitId;
                }

                System.out.println("Visit not found. Try again.\n");
            }
            while (true);
        }

        //Get id of new visit
        else
        {
            do
            {
                //Get visit id
                String visitId = Input.GetString("Enter Visit ID or 0 to cancel: ");
                if(visitId.equals("0")) {return null;}

                Visit visit = VisitDataAccess.getVisitById(visitId);

                if(visit == null)
                {
                    return visitId;
                }

                System.out.println("Visit found, ID must be unique. Try again.\n");
            }
            while (true);
        }
    }
}
