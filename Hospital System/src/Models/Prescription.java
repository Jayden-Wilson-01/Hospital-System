package Models;

import Utilities.Input;

import java.time.LocalDate;

public class Prescription
{
    //Identifiers
    private final String prescriptionID;

    // Could pass the object itself but for a DB heavy application, a passed object is loaded in memory and won't
    // act as the source of truth. Using ID referencing means fetch data is always the latest and most correct.
    private final String drugID;

    private final String doctorID;
    private final String patientID;

    //Properties

    //Used local date as local date allows for direct parsing
    private final LocalDate datePrescribed;
    private final int dosage;
    private final int duration;
    private final String comment;

    //Constructor
    public Prescription(String prescriptionID, String drugID, String doctorID, String patientID, LocalDate datePrescribed, int dosage, int duration, String comment)
    {
        this.prescriptionID = Input.RequireNonNull(prescriptionID);
        this.drugID = Input.RequireNonNull(drugID);
        this.doctorID = Input.RequireNonNull(doctorID);
        this.patientID = Input.RequireNonNull(patientID);

        this.datePrescribed = Input.RequireNonNull(datePrescribed);
        this.dosage = Input.RequireNonNull(dosage);
        this.duration = Input.RequireNonNull(duration);
        this.comment = Input.RequireNonNull(comment);
    }

    //Getters
    public String getPrescriptionID() {return prescriptionID;}
    public String getDrugID() {return drugID;}
    public String getDoctorID() {return doctorID;}
    public String getPatientID() {return patientID;}

    public LocalDate getDatePrescribed() {return datePrescribed;}
    public int getDosage() {return dosage;}
    public int getDuration() {return duration;}
    public String getComment() {return comment;}
}
