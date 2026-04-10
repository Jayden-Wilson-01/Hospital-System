package DataAccess;

import Models.Drug;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DrugDataAccess
{
    public static void createDrug(Drug drug)
    {
        //remove drugID as drugID is automatically assigned by the database via AUTO_INCREMENT?
        String sql = "INSERT INTO drugs (drugID, drugName, sideEffects, benefits) VALUES (?, ?, ?, ?)";

        try(PreparedStatement statement = Database.getConnection().prepareStatement(sql))
        {
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

    public static void updateDrug(Drug drug)
    {
        String sql = "UPDATE drugs SET drugName = ?, sideEffects = ?, benefits = ? WHERE drugID = ?";

        try(PreparedStatement statement = Database.getConnection().prepareStatement(sql))
        {
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

    public static void deleteDrug(Drug drug)
    {
        String sql = "DELETE FROM drugs WHERE drugID = ?";

        try(PreparedStatement statement = Database.getConnection().prepareStatement(sql))
        {
            statement.setString(1, drug.getDrugName());
            statement.execute();
        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }
    }

    public static List<Drug> loadAllDrugs()
    {
        List<Drug> drugs = new ArrayList<>();
        String sql = "SELECT * FROM drugs";

        try(PreparedStatement statement = Database.getConnection().prepareStatement(sql); ResultSet resultSet = statement.executeQuery())
        {
            while (resultSet.next())
            {
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
}
