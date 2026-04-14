package DataAccess;

import Models.Drug;
import Models.Patient;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PatientDataAccess
{
    public static void createPatient(Patient patient)
    {
        //remove patientID as patientID is automatically assigned by the database via AUTO_INCREMENT?
        String sql = "INSERT INTO patients (patientID, firstname, surname, address, phone, email) VALUES (?, ?, ?, ?, ?, ?)";

        try(PreparedStatement statement = Database.getConnection().prepareStatement(sql))
        {
            statement.setString(1, patient.getPatientID());
            statement.setString(2, patient.getFirstName());
            statement.setString(3, patient.getSurname());
            statement.setString(4, patient.getAddress());
            statement.setString(5, patient.getPhone());
            statement.setString(6, patient.getEmail());
            statement.execute();
        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }
    }

    public static void updatePatient(Patient patient)
    {
        String sql = "UPDATE patients SET firstname = ?, surname = ?, address = ?, phone = ?, email = ? WHERE patientID = ?";

        try(PreparedStatement statement = Database.getConnection().prepareStatement(sql))
        {
            statement.setString(1, patient.getFirstName());
            statement.setString(2, patient.getSurname());
            statement.setString(3, patient.getAddress());
            statement.setString(4, patient.getPhone());
            statement.setString(5, patient.getEmail());
            statement.setString(6, patient.getPatientID());
            statement.execute();
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }

    public static void deletePatient(String patientID)
    {
        String sql = "DELETE FROM patients WHERE patientID = ?";

        try(PreparedStatement statement = Database.getConnection().prepareStatement(sql))
        {
            statement.setString(1, patientID);
            statement.execute();
        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }
    }

    public static List<Patient> loadAllPatients(int limit)
    {
        List<Patient> patients = new ArrayList<>();
        String sql = "SELECT * FROM patients LIMIT ?";

        try(PreparedStatement statement = Database.getConnection().prepareStatement(sql))
        {
            statement.setInt(1, limit);

            try(ResultSet resultSet = statement.executeQuery())
            {
                while (resultSet.next())
                {
                    Patient patient = new Patient(
                            resultSet.getString("patientID"),
                            resultSet.getString("firstname"),
                            resultSet.getString("surname"),
                            resultSet.getString("address"),
                            resultSet.getString("phone"),
                            resultSet.getString("email")
                    );

                    patients.add(patient);
                }
            }
            catch (SQLException e)
            {
                System.out.println(e.getMessage());
                return null;
            }
        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
            return null;
        }

        return patients;
    }

    public static boolean ExistsInDatabase(String patientID)
    {
        String sql = "SELECT 1 FROM patients WHERE patientID = ?";

        try(PreparedStatement statement = Database.getConnection().prepareStatement(sql))
        {
            statement.setString(1, patientID);

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
