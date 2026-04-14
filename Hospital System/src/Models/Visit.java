package Models;

import Utilities.Input;
import java.time.LocalDate;

public class Visit
{
    //Identifiers
    private String visitID;
    private String patientID;
    private String doctorID;

    //Properties
    private LocalDate dateOfVisit;
    private String symptoms;
    private String diagnosis;

    //Constructor
    public Visit(String visitID, String patientID, String doctorID, LocalDate dateOfVisit, String symptoms, String diagnosis)
    {
        this.visitID = Input.RequireNonNull(visitID);
        this.patientID = Input.RequireNonNull(patientID);
        this.doctorID = Input.RequireNonNull(doctorID);
        this.dateOfVisit = Input.RequireNonNull(dateOfVisit);
        this.symptoms = symptoms;
        this.diagnosis = diagnosis;
    }

    public Visit()
    {
        //empty
    }

    //Getters
    public String getVisitID() {return visitID;}
    public String getPatientID() {return patientID;}
    public String getDoctorID() {return doctorID;}
    public LocalDate getDateOfVisit() {return dateOfVisit;}
    public String getSymptoms() {return symptoms;}
    public String getDiagnosis() {return diagnosis;}

    //Setters
    public void setVisitID(String visitID) {this.visitID = visitID;}
    public void setPatientID(String patientID) {this.patientID = patientID;}
    public void setDoctorID(String doctorID) {this.doctorID = doctorID;}
    public void setDateOfVisit(LocalDate dateOfVisit) {this.dateOfVisit = dateOfVisit;}
    public void setSymptoms(String symptoms) {this.symptoms = symptoms;}
    public void setDiagnosis(String diagnosis) {this.diagnosis = diagnosis;}

    public void DisplayDetails()
    {
        System.out.println();
        System.out.println("VisitID: " + visitID);
        System.out.println("PatientID: " + patientID);
        System.out.println("DoctorID: " + doctorID);
        System.out.println("DateOfVisit: " + dateOfVisit);
        System.out.println("Symptoms: " + symptoms);
        System.out.println("Diagnosis: " + diagnosis);
    }
}
