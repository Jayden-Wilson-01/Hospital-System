package Models;

public class Conflict
{
    //Identifiers
    private String drugID;
    private String drugName;

    public Conflict(String drugID, String drugName)
    {
        this.drugID = drugID;
        this.drugName = drugName;
    }

    // Getters
    public String getDrugID() { return drugID; }
    public String getDrugName() { return drugName; }

    //Show details
    public String displayDetails()
    {
        return drugName + " (" + drugID + ")";
    }
}
