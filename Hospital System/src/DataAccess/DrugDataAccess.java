package DataAccess;

import Models.Drug;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DrugDataAccess
{
    /**
     * Create a new drug directly in the database
     * @param drug the new drug model with the new details
     */
    public static void createDrug(Drug drug)
    {
        String sql = "INSERT INTO drugs (drugID, drugName, sideEffects, benefits) VALUES (?, ?, ?, ?)";

        try(PreparedStatement statement = Database.getConnection().prepareStatement(sql))
        {
            //Set the statement parameters using the according details
            statement.setString(1, drug.getDrugID());
            statement.setString(2, drug.getDrugName());
            statement.setString(3, drug.getSideEffects());
            statement.setString(4, drug.getBenefits());
            statement.execute();
        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Update an existing drug directly from the database
     * @param drug the drug model with the updated details
     */
    public static void updateDrug(Drug drug)
    {
        String sql = "UPDATE drugs SET drugName = ?, sideEffects = ?, benefits = ? WHERE drugID = ?";

        try(PreparedStatement statement = Database.getConnection().prepareStatement(sql))
        {
            //Set the statement parameters using the according details
            statement.setString(1, drug.getDrugName());
            statement.setString(2, drug.getSideEffects());
            statement.setString(3, drug.getBenefits());
            statement.setString(4, drug.getDrugID());
            statement.execute();
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Delete an existing drug directly from the database
     * @param drugID the existing drugs id
     */
    public static void deleteDrug(String drugID)
    {
        String sql = "DELETE FROM drugs WHERE drugID = ?";

        try(PreparedStatement statement = Database.getConnection().prepareStatement(sql))
        {
            //Set the statement parameters using the according details
            statement.setString(1, drugID);
            statement.execute();
        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Gets the details of all drugs within a provided limit
     * @param limit The limit of how many drug details to fetch from the database
     * @return a list of the drug models with populated details
     */
    public static List<Drug> loadAllDrugs(int limit)
    {
        List<Drug> drugs = new ArrayList<>();
        String sql = "SELECT * FROM drugs LIMIT ?";

        try(PreparedStatement statement = Database.getConnection().prepareStatement(sql))
        {
            //Set the statement parameters using the according details
            statement.setInt(1, limit);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next())
            {
                //Create a new drug model
                Drug drug = new Drug(
                        resultSet.getString("drugID"),
                        resultSet.getString("drugName"),
                        resultSet.getString("sideEffects"),
                        resultSet.getString("benefits")
                );

                drugs.add(drug);
            }
        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
            return null;
        }

        return drugs;
    }

    /**
     * Get details of an existing drug based on provided id
     * @param drugId The id of the existing drug
     * @return A singular drug model
     */
    public static Drug getDrugById(String drugId)
    {
        String sql = "SELECT * FROM drugs WHERE drugId = ?";

        try(PreparedStatement statement = Database.getConnection().prepareStatement(sql))
        {
            //Set the statement parameters using the according details
            statement.setString(1, drugId);
            ResultSet resultSet = statement.executeQuery();

            if(resultSet.next())
            {
                //Create a new drug model
                return new Drug(
                        resultSet.getString("drugID"),
                        resultSet.getString("drugName"),
                        resultSet.getString("sideEffects"),
                        resultSet.getString("benefits")
                );
            }

            return null;
        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
            return null;
        }
    }

    /**
     * Get details of each drug found with the same parameters
     * @param drugName First parameter: The name of the drug
     * @return A list of each drug that matches the same parameter
     */
    public static List<Drug> getDrugByParameters(String drugName)
    {
        List<Drug> drugs = new ArrayList<>();

        String sql = "SELECT * FROM drugs WHERE drugName = ?";

        try(PreparedStatement statement = Database.getConnection().prepareStatement(sql))
        {
            //Set the statement parameters using the according details
            statement.setString(1, drugName);
            ResultSet resultSet = statement.executeQuery();

            while(resultSet.next())
            {
                //Create new drug model
                Drug drug = new Drug(
                        resultSet.getString("drugID"),
                        resultSet.getString("drugName"),
                        resultSet.getString("sideEffects"),
                        resultSet.getString("benefits")
                );

                drugs.add(drug);
            }

            return drugs;
        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
            return null;
        }
    }
}
