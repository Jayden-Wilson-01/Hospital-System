package Models;

import Utilities.Input;
import java.time.LocalDate;

public class Visit
{
    //Identifiers
    private final String visitID;
    private final String patientID;
    private final String doctorID;

    //Properties
    private final LocalDate dateOfVisit;
    private final String symptoms;
    private final String diagnosis;

    //Constructor
    public Visit(String visitID, String patientID, String doctorID, LocalDate dateOfVisit, String symptoms, String diagnosis)
    {
        this.visitID = Input.RequireNonNull(visitID);
        this.patientID = Input.RequireNonNull(patientID);
        this.doctorID = Input.RequireNonNull(doctorID);
        this.dateOfVisit = Input.RequireNonNull(dateOfVisit);
        this.symptoms = Input.RequireNonNull(symptoms);
        this.diagnosis = Input.RequireNonNull(diagnosis);
    }

    //Getters
    public String getVisitID() {return visitID;}
    public String getPatientID() {return patientID;}
    public String getDoctorID() {return doctorID;}
    public LocalDate getDateOfVisit() {return dateOfVisit;}
    public String getSymptoms() {return symptoms;}
    public String getDiagnosis() {return diagnosis;}
}
