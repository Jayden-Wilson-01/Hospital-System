package PresentationLayer;

import DataAccess.*;
import Models.*;
import Utilities.Input;

import java.util.List;

//Gets parameters from user
public class RecordAccessGuard
{
    /**
     * Finds patients that match the parameters
     * First parameter: patients firstname
     * Second parameter: patients surname
     * @return A list of patients that match the specified parameters
     */
    public static List<Patient> getPatientByParameters()
    {
        do
        {
            //Get firstname
            String firstname = Input.GetString("Enter patients firstname or 0 to cancel: ");
            if(firstname.equals("0")){return null;}

            //Get surname
            String surname =  Input.GetString("Enter patients surname or 0 to cancel: ");
            if(surname.equals("0")){return null;}

            //List of patients that match the parameters
            List<Patient> patients = PatientDataAccess.GetPatientByParameters(firstname, surname);
            if(patients != null){return patients;}

            System.out.println("Patient(s) not found. Try again.");
        }
        while(true);

    }

    /**
     * Finds doctors that match the parameters
     * First parameter: doctors firstname
     * Second parameter: doctors surname
     * @return A list of doctors that match the specified parameters
     */
    public static List<Doctor> getDoctorByParameters()
    {
        do
        {
            //Get doctors firstname
            String firstname = Input.GetString("Enter doctors firstname or 0 to cancel: ");
            if(firstname.equals("0")){return null;}

            //Get doctors surname
            String surname =  Input.GetString("Enter doctors surname or 0 to cancel: ");
            if(surname.equals("0")){return null;}

            //Get list of doctors that match parameters
            List<Doctor> doctors = DoctorDataAccess.getDoctorByParameters(firstname, surname);
            if(doctors != null){return doctors;}

            System.out.println("Doctor(s) not found. Try again.");
        }
        while(true);

    }

    /**
     * Finds doctors that match the speciality
     * @return A list of doctors that match the specified speciality
     */
    public static List<Doctor> getDoctorBySpeciality()
    {
        do
        {
            //Displays all available specialist options
            Specializations.DisplayOptions();

            //Get specialist id
            int specialization = Input.GetInt("Enter doctors specialization (1-" + Specializations.OptionAmount()+") or 0 to cancel: ");
            if(specialization == 0){return null;}

            //Gets a list of each doctor that matches the same specialization by taking the selected specialist id and turn to string
            List<Doctor> doctors = DoctorDataAccess.getDoctorBySpeciality(Specializations.GetSpecializationType(specialization));
            if(doctors != null){return doctors;}

            System.out.println("Doctor(s) not found. Try again.");
        }
        while(true);

    }

    /**
     * Finds drugs that match the parameters
     * First parameter: drug name
     * @return A list of drugs that match the specified parameters
     */
    public static List<Drug> getDrugByParameters()
    {
        do
        {
            //Get drug name
            String drugName = Input.GetString("Enter drugs name or 0 to cancel: ");
            if(drugName.equals("0")){return null;}

            //Get list of drugs that matches parameters
            List<Drug> drugs = DrugDataAccess.getDrugByParameters(drugName);
            if(drugs != null){return drugs;}

            System.out.println("Drug(s) not found. Try again.");
        }
        while(true);
    }

    /**
     * Finds insurance companies that match the parameters
     * First parameter: insurance company name
     * @return A list of insurance companies that match the specified parameters
     */
    public static List<Insurance> getInsuranceByParameters()
    {
        do
        {
            //Get insurance company name
            String insuranceCompanyName = Input.GetString("Enter insurance company name or 0 to cancel: ");
            if(insuranceCompanyName.equals("0")){return null;}

            //Get list of insurance companies that matches parameters
            List<Insurance> insurances = InsuranceDataAccess.getInsuranceByParameters(insuranceCompanyName);
            if(insurances != null){return insurances;}

            System.out.println("Insurance(s) not found. Try again.");
        }
        while(true);
    }

    /**
     * Finds prescriptions that match the parameters
     * First parameter: patients firstname
     * Second parameter: patients surname
     * Third parameter: doctors firstname
     * Forth parameter: doctors surname
     * @return A list of prescriptions that match the specified parameters
     */
    public static List<Prescription> getPrescriptionByParameters()
    {
        do
        {
            //Get patients firstname
            String patientFirstname = Input.GetString("Enter patients first name or 0 to cancel: ");
            if(patientFirstname.equals("0")){return null;}

            //Get patients surname
            String patientSurname = Input.GetString("Enter patients surname or 0 to cancel: ");
            if(patientSurname.equals("0")){return null;}

            //Get doctors firstname
            String doctorFirstname = Input.GetString("Enter doctors first name or 0 to cancel: ");
            if(doctorFirstname.equals("0")){return null;}

            //Get doctors surname
            String doctorSurname = Input.GetString("Enter doctors surname or 0 to cancel: ");
            if(doctorSurname.equals("0")){return null;}

            //Get list of prescriptions that match parameters
            List<Prescription> prescriptions = PrescriptionDataAccess.getPrescriptionByParameters(patientFirstname, patientSurname, doctorFirstname, doctorSurname);
            if(prescriptions != null){return prescriptions;}

            System.out.println("Prescription(s) not found. Try again.");
        }
        while(true);
    }

    /**
     * Finds visits that match the parameters
     * First parameter: patients firstname
     * Second parameter: patients surname
     * Third parameter: doctors firstname
     * Forth parameter: doctors surname
     * @return A list of visits that match the specified parameters
     */
    public static List<Visit> getVisitByParameters()
    {
        do
        {
            //Get patients firstname
            String patientFirstname = Input.GetString("Enter patients first name or 0 to cancel: ");
            if(patientFirstname.equals("0")){return null;}

            //Get patients surname
            String patientSurname = Input.GetString("Enter patients surname or 0 to cancel: ");
            if(patientSurname.equals("0")){return null;}

            //Get doctors firstname
            String doctorFirstname = Input.GetString("Enter doctors first name or 0 to cancel: ");
            if(doctorFirstname.equals("0")){return null;}

            //Get doctors surname
            String doctorSurname = Input.GetString("Enter doctors surname or 0 to cancel: ");
            if(doctorSurname.equals("0")){return null;}

            //Get list of visits that match parameters
            List<Visit> visits = VisitDataAccess.getVisitByParameters(patientFirstname, patientSurname, doctorFirstname, doctorSurname);
            if(visits != null){return visits;}

            System.out.println("Visit(s) not found. Try again.");
        }
        while(true);
    }
}
