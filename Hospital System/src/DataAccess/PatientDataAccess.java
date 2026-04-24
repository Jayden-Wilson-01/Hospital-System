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
        String sql = "INSERT INTO patients (patientID, insuranceid, doctorID, firstname, surname, address, phone, email) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try(PreparedStatement statement = Database.getConnection().prepareStatement(sql))
        {
            statement.setString(1, patient.getPatientID());
            statement.setString(2, patient.getInsuranceID());
            statement.setString(3, patient.getDoctorID());
            statement.setString(4, patient.getFirstName());
            statement.setString(5, patient.getSurname());
            statement.setString(6, patient.getAddress());
            statement.setString(7, patient.getPhone());
            statement.setString(8, patient.getEmail());
            statement.execute();
        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }
    }

    public static void updatePatient(Patient patient)
    {
        String sql = "UPDATE patients SET insuranceid = ?, doctorID = ?, firstname = ?, surname = ?, address = ?, phone = ?, email = ? WHERE patientID = ?";

        try(PreparedStatement statement = Database.getConnection().prepareStatement(sql))
        {
            statement.setString(1, patient.getInsuranceID());
            statement.setString(2, patient.getDoctorID());
            statement.setString(3, patient.getFirstName());
            statement.setString(4, patient.getSurname());
            statement.setString(5, patient.getAddress());
            statement.setString(6, patient.getPhone());
            statement.setString(7, patient.getEmail());
            statement.setString(8, patient.getPatientID());
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
        String sql = "SELECT pat.*, ins.company, doc.firstname, doc.surname FROM patients pat LEFT JOIN insurance ins ON pat.insuranceid = ins.insuranceID LEFT JOIN doctors doc ON pat.doctorID = doc.doctorID LIMIT ?";

        try(PreparedStatement statement = Database.getConnection().prepareStatement(sql))
        {
            statement.setInt(1, limit);

            try(ResultSet resultSet = statement.executeQuery())
            {
                while (resultSet.next())
                {
                    Patient patient = new Patient(
                            resultSet.getString("patientID"),
                            resultSet.getString("insuranceid"),
                            resultSet.getString("doctorID"),
                            resultSet.getString("firstname"),
                            resultSet.getString("surname"),
                            resultSet.getString("postcode"),
                            resultSet.getString("address"),
                            resultSet.getString("phone"),
                            resultSet.getString("email"),
                            resultSet.getString("company"),
                            resultSet.getString("doc.firstname"),
                            resultSet.getString("doc.surname")
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

    public static Patient GetPatientById(String patientId)
    {
        String sql = "SELECT pat.*, ins.company, doc.firstname, doc.surname FROM patients pat LEFT JOIN insurance ins ON pat.insuranceid = ins.insuranceID LEFT JOIN doctors doc ON pat.doctorID = doc.doctorID WHERE pat.patientID = ?";

        try(PreparedStatement statement = Database.getConnection().prepareStatement(sql))
        {
            statement.setString(1, patientId);

            try(ResultSet resultSet = statement.executeQuery())
            {
                if (resultSet.next())
                {

                    return new Patient(
                            resultSet.getString("patientID"),
                            resultSet.getString("insuranceid"),
                            resultSet.getString("doctorID"),
                            resultSet.getString("firstname"),
                            resultSet.getString("surname"),
                            resultSet.getString("postcode"),
                            resultSet.getString("address"),
                            resultSet.getString("phone"),
                            resultSet.getString("email"),
                            resultSet.getString("company"),
                            resultSet.getString("doc.firstname"),
                            resultSet.getString("doc.surname")
                    );
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

        return null;
    }

    public static List<Patient> GetPatientByParameters(String firstname, String surname)
    {
        String sql = "SELECT pat.*, ins.company, doc.firstname, doc.surname FROM patients pat LEFT JOIN insurance ins ON pat.insuranceid = ins.insuranceID LEFT JOIN doctors doc ON pat.doctorID = doc.doctorID WHERE pat.firstname = ? AND pat.surname = ?";

        try(PreparedStatement statement = Database.getConnection().prepareStatement(sql))
        {
            List<Patient> patients = new ArrayList<>();

            statement.setString(1, firstname);
            statement.setString(2, surname);

            try(ResultSet resultSet = statement.executeQuery())
            {
                while (resultSet.next())
                {
                    Patient patient = new Patient(
                            resultSet.getString("patientID"),
                            resultSet.getString("insuranceid"),
                            resultSet.getString("doctorID"),
                            resultSet.getString("firstname"),
                            resultSet.getString("surname"),
                            resultSet.getString("postcode"),
                            resultSet.getString("address"),
                            resultSet.getString("phone"),
                            resultSet.getString("email"),
                            resultSet.getString("company"),
                            resultSet.getString("doc.firstname"),
                            resultSet.getString("doc.surname")
                    );

                    patients.add(patient);
                }

                return patients;
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
    }
}
