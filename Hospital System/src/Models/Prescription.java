package Models;

import Utilities.Input;

import java.time.LocalDate;

public class Prescription
{
    //Identifiers
    private String prescriptionID;

    // Could pass the object itself but for a DB heavy application, a passed object is loaded in memory and won't
    // act as the source of truth. Using ID referencing means fetch data is always the latest and most correct.
    private String drugID;
    private String doctorID;
    private String patientID;

    //Properties

    //Used local date as local date allows for direct parsing
    private LocalDate datePrescribed;
    private int dosage;
    private int duration;
    private String comment;

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
        this.comment = comment;
    }

    public Prescription()
    {
        //Empty
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

    //setters
    public void setPrescriptionID(String prescriptionID) {this.prescriptionID = prescriptionID;}
    public void setDrugID(String drugID) {this.drugID = drugID;}
    public void setDoctorID(String doctorID) {this.doctorID = doctorID;}
    public void setPatientID(String patientID) {this.patientID = patientID;}

    public void setDatePrescribed(LocalDate datePrescribed) {this.datePrescribed = datePrescribed;}
    public void setDosage(int dosage) {this.dosage = dosage;}
    public void setDuration(int duration) {this.duration = duration;}
    public void setComment(String comment) {this.comment = comment;}

    public void DisplayDetails()
    {
        System.out.println();
        System.out.println("Prescription ID: " + prescriptionID);
        System.out.println("Doctor ID: " + doctorID);
        System.out.println("Patient ID: " + patientID);
        System.out.println("Drug ID: " + drugID);
        System.out.println("Prescription date: " + datePrescribed);
        System.out.println("Dosage: " + dosage);
        System.out.println("Duration: " + duration);
        System.out.println("Comment: " + comment);
    }
}
