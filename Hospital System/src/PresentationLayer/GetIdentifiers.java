package PresentationLayer;

import DataAccess.*;
import Models.*;
import Utilities.Input;

public class GetIdentifiers
{
    public static String getPatientID(boolean patientExists)
    {
        if(patientExists)
        {
            do
            {
                String patientId = Input.GetString("Enter Patient ID or 0 to cancel: ");
                if(patientId.equals("0")){return null;}

                Patient patient = PatientDataAccess.GetPatientById(patientId);

                if(patient != null)
                {
                    return patientId;
                }

                System.out.print("Patient not found. Try again.");
            }
            while (true);
        }

        else
        {
            do
            {
                String patientId = Input.GetString("Enter Patient ID or 0 to cancel: ");
                if(patientId.equals("0")){return null;}

                Patient patient = PatientDataAccess.GetPatientById(patientId);

                if(patient == null)
                {
                    return patientId;
                }

                System.out.println("Patient found, ID must be unique. Try again.");
            }
            while (true);
        }
    }

    public static String getDoctorID(boolean doctorExists)
    {
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

                System.out.println("Doctor not found. Try again.");
            }
            while (true);
        }

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

                System.out.println("Doctor found, ID must be unique. Try again.");
            }
            while (true);
        }
    }

    public static String getDrugID(boolean drugExists)
    {
        if(drugExists)
        {
            do
            {
                String drugId = Input.GetString("Enter Drug ID or 0 to cancel: ");
                if(drugId.equals("0")) {return null;}

                Drug drug = DrugDataAccess.getDrugById(drugId);

                if(drug != null)
                {
                    return drugId;
                }

                System.out.println("Drug not found. Try again.");
            }
            while (true);
        }

        else
        {
            do
            {
                String drugId = Input.GetString("Enter Drug ID or 0 to cancel: ");
                if(drugId.equals("0")) {return null;}

                Drug drug = DrugDataAccess.getDrugById(drugId);

                if(drug == null)
                {
                    return drugId;
                }

                System.out.println("Drug found, ID must be unique. Try again.");
            }
            while (true);
        }
    }

    public static String getConflictingDrugID(boolean drugExists)
    {
        if(drugExists)
        {
            do
            {
                String drugId = Input.GetString("Enter ID of conflicting Drug or 0 to finish: ");
                if(drugId.equals("0")) {return null;}

                Drug drug = DrugDataAccess.getDrugById(drugId);

                if(drug != null)
                {
                    return drugId;
                }

                System.out.println("Drug not found. Try again.");
            }
            while (true);
        }

        else
        {
            do
            {
                String drugId = Input.GetString("Enter ID of conflicting Drug or 0 to finish: ");
                if(drugId.equals("0")) {return null;}

                Drug drug = DrugDataAccess.getDrugById(drugId);

                if(drug == null)
                {
                    return drugId;
                }

                System.out.println("Drug found, ID must be unique. Try again.");
            }
            while (true);
        }
    }

    public static String getInsuranceID(boolean insuranceExists)
    {
        if(insuranceExists)
        {
            do
            {
                String insuranceId = Input.GetString("Enter Insurance ID or 0 to cancel: ");
                if(insuranceId.equals("0")){return null;}

                Insurance insurance = InsuranceDataAccess.getInsuranceById(insuranceId);

                if(insurance != null)
                {
                    return insuranceId;
                }

                System.out.println("Insurance not found. Try again.");
            }
            while (true);
        }

        else
        {
            do
            {
                String insuranceId = Input.GetString("Enter Insurance ID or 0 to cancel: ");
                if(insuranceId.equals("0")){return null;}

                Insurance insurance = InsuranceDataAccess.getInsuranceById(insuranceId);

                if(insurance == null)
                {
                    return insuranceId;
                }

                System.out.println("Insurance found, ID must be unique. Try again.");
            }
            while (true);
        }
    }

    public static String getPrescriptionID(boolean prescriptionExists)
    {
        if(prescriptionExists)
        {
            do
            {
                String prescriptionId = Input.GetString("Enter Prescription ID or 0 to cancel: ");
                if(prescriptionId.equals("0")) {return null;}

                Prescription prescription = PrescriptionDataAccess.getPrescriptionById(prescriptionId);

                if(prescription != null)
                {
                    return prescriptionId;
                }

                System.out.println("Prescription not found. Try again.");
            }
            while (true);
        }

        else
        {
            do
            {
                String prescriptionId = Input.GetString("Enter Prescription ID or 0 to cancel: ");
                if(prescriptionId.equals("0")) {return null;}

                Prescription prescription = PrescriptionDataAccess.getPrescriptionById(prescriptionId);

                if(prescription == null)
                {
                    return prescriptionId;
                }

                System.out.println("Prescription found, ID must be unique. Try again.");
            }
            while (true);
        }
    }

    public static String getVisitID(boolean visitExists)
    {
        if(visitExists)
        {
            do
            {
                String visitId = Input.GetString("Enter Visit ID or 0 to cancel: ");
                if(visitId.equals("0")) {return null;}

                Visit visit = VisitDataAccess.getVisitById(visitId);

                if(visit != null)
                {
                    return visitId;
                }

                System.out.println("Visit not found. Try again.");
            }
            while (true);
        }

        else
        {
            do
            {
                String visitId = Input.GetString("Enter Visit ID or 0 to cancel: ");
                if(visitId.equals("0")) {return null;}

                Visit visit = VisitDataAccess.getVisitById(visitId);

                if(visit == null)
                {
                    return visitId;
                }

                System.out.println("Visit found, ID must be unique. Try again.");
            }
            while (true);
        }
    }
}
