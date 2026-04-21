package PresentationLayer;

import DataAccess.*;
import Models.*;
import Utilities.Input;

import java.util.List;

//Prevents a user for accessing all details of a record until the specified fields are provided
public class RecordAccessGuard
{
    public static List<Patient> getPatientByParameters()
    {
        do
        {
            String firstname = Input.GetString("Enter patients firstname or 0 to cancel: ");
            if(firstname.equals("0")){return null;}

            String surname =  Input.GetString("Enter patients surname or 0 to cancel: ");
            if(surname.equals("0")){return null;}

            List<Patient> patients = PatientDataAccess.GetPatientByParameters(firstname, surname);
            if(patients != null){return patients;}

            System.out.println("Patient(s) not found. Try again.");
        }
        while(true);

    }

    public static List<Doctor> getDoctorByParameters()
    {
        do
        {
            String firstname = Input.GetString("Enter doctors firstname or 0 to cancel: ");
            if(firstname.equals("0")){return null;}

            String surname =  Input.GetString("Enter doctors surname or 0 to cancel: ");
            if(surname.equals("0")){return null;}

            List<Doctor> doctors = DoctorDataAccess.getDoctorByParameters(firstname, surname);
            if(doctors != null){return doctors;}

            System.out.println("Doctor(s) not found. Try again.");
        }
        while(true);

    }

    public static List<Doctor> getDoctorBySpeciality()
    {
        do
        {
            Specializations.DisplayOptions();
            int specialization = Input.GetInt("Enter doctors specialization (1-" + Specializations.OptionAmount()+") or 0 to cancel: ");
            if(specialization == 0){return null;}

            List<Doctor> doctors = DoctorDataAccess.getDoctorBySpeciality(Specializations.GetSpecializationType(specialization));
            if(doctors != null){return doctors;}

            System.out.println("Doctor(s) not found. Try again.");
        }
        while(true);

    }

    public static List<Drug> getDrugByParameters()
    {
        do
        {
            String drugName = Input.GetString("Enter drugs name or 0 to cancel: ");
            if(drugName.equals("0")){return null;}

            List<Drug> drugs = DrugDataAccess.getDrugByParameters(drugName);
            if(drugs != null){return drugs;}

            System.out.println("Drug(s) not found. Try again.");
        }
        while(true);
    }

    public static List<Insurance> getInsuranceByParameters()
    {
        do
        {
            String insuranceCompanyName = Input.GetString("Enter insurance company name or 0 to cancel: ");
            if(insuranceCompanyName.equals("0")){return null;}

            List<Insurance> insurances = InsuranceDataAccess.getInsuranceByParameters(insuranceCompanyName);
            if(insurances != null){return insurances;}

            System.out.println("Insurance(s) not found. Try again.");
        }
        while(true);
    }

    public static List<Prescription> getPrescriptionByParameters()
    {
        do
        {
            String patientFirstname = Input.GetString("Enter patients first name or 0 to cancel: ");
            if(patientFirstname.equals("0")){return null;}

            String patientSurname = Input.GetString("Enter patients surname or 0 to cancel: ");
            if(patientSurname.equals("0")){return null;}

            String doctorFirstname = Input.GetString("Enter doctors first name or 0 to cancel: ");
            if(doctorFirstname.equals("0")){return null;}

            String doctorSurname = Input.GetString("Enter doctors surname or 0 to cancel: ");
            if(doctorSurname.equals("0")){return null;}

            List<Prescription> prescriptions = PrescriptionDataAccess.getPrescriptionByParameters(patientFirstname, patientSurname, doctorFirstname, doctorSurname);
            if(prescriptions != null){return prescriptions;}

            System.out.println("Prescription(s) not found. Try again.");
        }
        while(true);
    }

    public static List<Visit> getVisitByParameters()
    {
        do
        {
            String patientFirstname = Input.GetString("Enter patients first name or 0 to cancel: ");
            if(patientFirstname.equals("0")){return null;}

            String patientSurname = Input.GetString("Enter patients surname or 0 to cancel: ");
            if(patientSurname.equals("0")){return null;}

            String doctorFirstname = Input.GetString("Enter doctors first name or 0 to cancel: ");
            if(doctorFirstname.equals("0")){return null;}

            String doctorSurname = Input.GetString("Enter doctors surname or 0 to cancel: ");
            if(doctorSurname.equals("0")){return null;}

            List<Visit> visits = VisitDataAccess.getVisitByParameters(patientFirstname, patientSurname, doctorFirstname, doctorSurname);
            if(visits != null){return visits;}

            System.out.println("Visit(s) not found. Try again.");
        }
        while(true);
    }
}
