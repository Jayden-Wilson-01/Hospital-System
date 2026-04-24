package DataAccess;

import Models.Drug;
import Models.Insurance;
import Models.Prescription;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class InsuranceDataAccess
{
    /**
     * Add a new insurance company directly to the database
     * @param insurance The new, populated insurance model
     */
    public static void createInsurance(Insurance insurance)
    {
        //Thought: Remove insuranceID as insuranceID is automatically assigned by the database via AUTO_INCREMENT?
        String sql = "INSERT INTO insurance (insuranceID, company, address, phone) VALUES (?, ?, ?, ?)";

        try(PreparedStatement statement = Database.getConnection().prepareStatement(sql))
        {
            //Set the statement parameters using the according details
            statement.setString(1, insurance.getInsuranceID());
            statement.setString(2, insurance.getCompany());
            statement.setString(3, insurance.getAddress());
            statement.setString(4, insurance.getPhone());
            statement.execute();
        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Update an existing insurance company directly in from database
     * @param insurance The updated, populated insurance model
     */
    public static void updateInsurance(Insurance insurance)
    {
        String sql = "UPDATE insurance SET company = ?, address = ?, phone = ? WHERE insuranceID = ?";

        try(PreparedStatement statement = Database.getConnection().prepareStatement(sql))
        {
            //Set the statement parameters using the according details
            statement.setString(1, insurance.getCompany());
            statement.setString(2, insurance.getAddress());
            statement.setString(3, insurance.getPhone());
            statement.setString(4, insurance.getInsuranceID());
            statement.execute();
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Delete an existing insurance company directly in from database
     * @param insuranceID The id of the existing insurance company
     */
    public static void deleteInsurance(String insuranceID)
    {
        String sql = "DELETE FROM insurance WHERE insuranceID = ?";

        try(PreparedStatement statement = Database.getConnection().prepareStatement(sql))
        {
            //Set the statement parameters using the according details
            statement.setString(1, insuranceID);
            statement.execute();
        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Gets the details of all insurance companies within a provided limit
     * @param limit the limit of how many insurance company details to fetch from the database
     * @return a list of the insurance company models with populated details
     */
    public static List<Insurance> loadAllInsurance(int limit)
    {
        List<Insurance> insurances = new ArrayList<>();
        String sql = "SELECT * FROM insurance LIMIT ?";

        try(PreparedStatement statement = Database.getConnection().prepareStatement(sql))
        {
            //Set the statement parameters using the according details
            statement.setInt(1, limit);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next())
            {
                //Create new insurance company model
                Insurance insurance = new Insurance(
                        resultSet.getString("insuranceID"),
                        resultSet.getString("company"),
                        resultSet.getString("address"),
                        resultSet.getString("phone")
                );

                insurances.add(insurance);
            }
        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
            return null;
        }

        return insurances;
    }

    /**
     * Get details of an existing insurance company based on provided id
     * @param insuranceID The id of the existing insurance company
     * @return A singular insurance company model
     */
    public static Insurance getInsuranceById(String insuranceID)
    {
        String sql = "SELECT * FROM insurance WHERE insuranceID = ?";

        try(PreparedStatement statement = Database.getConnection().prepareStatement(sql))
        {
            //Set the statement parameters using the according details
            statement.setString(1, insuranceID);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next())
            {
                //Create new insurance company model
                return new Insurance(
                        resultSet.getString("insuranceID"),
                        resultSet.getString("company"),
                        resultSet.getString("address"),
                        resultSet.getString("phone")
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
     * Get details of each insurance company found with the same parameters
     * @param insuranceCompanyName First parameter: The name of teh insurance company
     * @return A list of each insurance company that matches the same parameter
     */
    public static List<Insurance> getInsuranceByParameters(String insuranceCompanyName)
    {
        List<Insurance> insurances = new ArrayList<>();

        String sql = "SELECT * FROM insurance WHERE company = ?";

        try(PreparedStatement statement = Database.getConnection().prepareStatement(sql))
        {
            //Set the statement parameters using the according details
            statement.setString(1, insuranceCompanyName);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next())
            {
                //Create new insurance company model
                Insurance insurance = new Insurance(
                        resultSet.getString("insuranceID"),
                        resultSet.getString("company"),
                        resultSet.getString("address"),
                        resultSet.getString("phone")
                );

                insurances.add(insurance);
            }

            return insurances;
        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
            return null;
        }
    }
}
