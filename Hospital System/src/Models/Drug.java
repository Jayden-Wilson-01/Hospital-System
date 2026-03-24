package Models;

import Utilities.Input;

import java.time.LocalDate;

public class Drug
{
    //Identifiers
    private final String drugID;

    //Properties
    private final String drugName;
    private final String sideEffects;
    private final String benefits;

    //Constructor
    public Drug(String drugID, String drugName, String sideEffects, String benefits)
    {
        this.drugID = Input.RequireNonNull(drugID);
        this.drugName = Input.RequireNonNull(drugName);
        this.sideEffects = Input.RequireNonNull(sideEffects);
        this.benefits = Input.RequireNonNull(benefits);
    }

    //Getters
    public String getDrugID() {return drugID;}
    public String getDrugName() {return drugName;}
    public String getSideEffects() {return sideEffects;}
    public String getBenefits() {return benefits;}
}
