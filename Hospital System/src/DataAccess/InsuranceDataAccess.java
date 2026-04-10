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
    public static void createInsurance(Insurance insurance)
    {
        //remove insuranceID as insuranceID is automatically assigned by the database via AUTO_INCREMENT?
        String sql = "INSERT INTO insurance (insuranceID, company, address, phone) VALUES (?, ?, ?, ?)";

        try(PreparedStatement statement = Database.getConnection().prepareStatement(sql))
        {
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

    public static void updateInsurance(Insurance insurance)
    {
        String sql = "UPDATE insurance SET insuranceID = ?, company = ?, address = ?, phone = ?";

        try(PreparedStatement statement = Database.getConnection().prepareStatement(sql))
        {
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

    public static void deleteInsurance(Insurance insurance)
    {
        String sql = "DELETE FROM insurance WHERE insuranceID = ?";

        try(PreparedStatement statement = Database.getConnection().prepareStatement(sql))
        {
            statement.setString(1, insurance.getInsuranceID());
            statement.execute();
        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }
    }

    public static List<Insurance> loadAllInsurance()
    {
        List<Insurance> insurances = new ArrayList<>();
        String sql = "SELECT * FROM insurance";

        try(PreparedStatement statement = Database.getConnection().prepareStatement(sql); ResultSet resultSet = statement.executeQuery())
        {
            while (resultSet.next())
            {
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
}
