package Models;

import Utilities.Input;

import java.time.LocalDate;

public class Drug
{
    //Identifiers
    private String drugID;

    //Properties
    private String drugName;
    private String sideEffects;
    private String benefits;

    //Constructor
    public Drug(String drugID, String drugName, String sideEffects, String benefits)
    {
        this.drugID = Input.RequireNonNull(drugID);
        this.drugName = Input.RequireNonNull(drugName);
        this.sideEffects = Input.RequireNonNull(sideEffects);
        this.benefits = Input.RequireNonNull(benefits);
    }

    public Drug()
    {
        //Empty
    }

    //Getters
    public String getDrugID() {return drugID;}
    public String getDrugName() {return drugName;}
    public String getSideEffects() {return sideEffects;}
    public String getBenefits() {return benefits;}

    //Setters
    public void setDrugID(String drugID){this.drugID = drugID;}
    public void setDrugName(String drugName){this.drugName = drugName;}
    public void setSideEffects(String sideEffects){this.sideEffects = sideEffects;}
    public void setBenefits(String benefits){this.benefits = benefits;}

    public void DisplayDetails()
    {
        System.out.println();
        System.out.println("Drug id: " + getDrugID());
        System.out.println("Drug name: " + getDrugName());
        System.out.println("Side effects: " + getSideEffects());
        System.out.println("Benefits: " + getBenefits());
    }
}
