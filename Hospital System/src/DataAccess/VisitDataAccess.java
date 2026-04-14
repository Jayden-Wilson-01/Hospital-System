package DataAccess;

import Models.Prescription;
import Models.Visit;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VisitDataAccess
{
    public static void createVisit(Visit visit)
    {
        //remove visitID as visitID is automatically assigned by the database via AUTO_INCREMENT?
        String sql = "INSERT INTO visits (visitID, patientID, doctorID, dateOfVisit, symptoms, diagnosis) VALUES (?, ?, ?, ?, ?, ?)";

        try(PreparedStatement statement = Database.getConnection().prepareStatement(sql))
        {
            statement.setString(1, visit.getVisitID());
            statement.setString(2, visit.getPatientID());
            statement.setString(3, visit.getDoctorID());

            //Needed to use value of as getDateOfVisit() returns java.time.LocalDate type rather than java.sql.Date
            statement.setDate(4, java.sql.Date.valueOf(visit.getDateOfVisit()));

            statement.setString(5, visit.getSymptoms());
            statement.setString(6, visit.getDiagnosis());
            statement.execute();
        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }
    }

    public static void updateVisit(Visit visit)
    {
        String sql = "UPDATE visits SET patientID = ?, doctorID = ?, dateOfVisit = ?, symptoms = ?, diagnosis = ? WHERE visitId = ?";

        try(PreparedStatement statement = Database.getConnection().prepareStatement(sql))
        {
            statement.setString(1, visit.getPatientID());
            statement.setString(2, visit.getDoctorID());

            //Needed to use value of as getDateOfVisit() returns java.time.LocalDate type rather than java.sql.Date
            statement.setDate(3, java.sql.Date.valueOf(visit.getDateOfVisit()));

            statement.setString(4, visit.getSymptoms());
            statement.setString(5, visit.getDiagnosis());
            statement.setString(6, visit.getVisitID());
            statement.execute();
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }

    public static void deleteVisit(String visitId)
    {
        String sql = "DELETE FROM visits WHERE visitID = ?";

        try(PreparedStatement statement = Database.getConnection().prepareStatement(sql))
        {
            statement.setString(1, visitId);
            statement.execute();
        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }
    }

    public static List<Visit> loadAllVisits()
    {
        List<Visit> visits = new ArrayList<>();
        String sql = "SELECT * FROM visits";

        try(PreparedStatement statement = Database.getConnection().prepareStatement(sql); ResultSet resultSet = statement.executeQuery())
        {
            while (resultSet.next())
            {
                Visit visit = new Visit(
                        resultSet.getString("visitID"),
                        resultSet.getString("patientID"),
                        resultSet.getString("doctorID"),
                        resultSet.getDate("dateOfVisit").toLocalDate(),
                        resultSet.getString("symptoms"),
                        resultSet.getString("diagnosis")
                );

                visits.add(visit);
            }
        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
            return null;
        }

        return visits;
    }

    public static boolean ExistsInDatabase(String visitId)
    {
        String sql = "SELECT * FROM visits WHERE visitId = ?";

        try(PreparedStatement statement = Database.getConnection().prepareStatement(sql))
        {
            statement.setString(1, visitId);

            try (ResultSet resultSet = statement.executeQuery())
            {
                // if rs.next() is true, a row was found
                return resultSet.next();
            }
            catch (SQLException e)
            {
                System.out.println(e.getMessage());
                return false;
            }
        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
            return false;
        }
    }
}
