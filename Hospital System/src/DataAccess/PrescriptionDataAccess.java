package DataAccess;

import Models.Drug;
import Models.Prescription;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PrescriptionDataAccess
{
    public static void createPrescription(Prescription prescription)
    {
        //remove prescriptionID as prescriptionID is automatically assigned by the database via AUTO_INCREMENT?
        String sql = "INSERT INTO prescriptions (prescriptionID, drugID, doctorID, patientID, datePrescribed, dosage, duration, comment) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try(PreparedStatement statement = Database.getConnection().prepareStatement(sql))
        {
            statement.setString(1, prescription.getPrescriptionID());
            statement.setString(2, prescription.getDrugID());
            statement.setString(3, prescription.getDoctorID());
            statement.setString(4, prescription.getPatientID());

            //Needed to use value of as getDatePrescribed() returns java.time.LocalDate type rather than java.sql.Date
            statement.setDate(5, java.sql.Date.valueOf(prescription.getDatePrescribed()));

            statement.setInt(6, prescription.getDosage());
            statement.setInt(7, prescription.getDuration());
            statement.setString(8, prescription.getComment());
            statement.execute();
        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }
    }

    public static void updatePrescription(Prescription prescription)
    {
        String sql = "UPDATE prescriptions SET drugID = ?, doctorID = ?, patientID = ?, datePrescribed = ?, dosage = ?, duration = ?, comment = ? WHERE prescriptionID = ?";

        try(PreparedStatement statement = Database.getConnection().prepareStatement(sql))
        {
            statement.setString(1, prescription.getDrugID());
            statement.setString(2, prescription.getDoctorID());
            statement.setString(3, prescription.getPatientID());

            //Needed to use value of as getDatePrescribed() returns java.time.LocalDate type rather than java.sql.Date
            statement.setDate(4, java.sql.Date.valueOf(prescription.getDatePrescribed()));

            statement.setInt(5, prescription.getDosage());
            statement.setInt(6, prescription.getDuration());
            statement.setString(7, prescription.getComment());
            statement.setString(8, prescription.getPrescriptionID());
            statement.execute();
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }

    public static void deletePrescription(Prescription prescription)
    {
        String sql = "DELETE FROM prescriptions WHERE prescriptionID = ?";

        try(PreparedStatement statement = Database.getConnection().prepareStatement(sql))
        {
            statement.setString(1, prescription.getPrescriptionID());
            statement.execute();
        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }
    }

    public static List<Prescription> loadAllPrescriptions()
    {
        List<Prescription> prescriptions = new ArrayList<>();
        String sql = "SELECT * FROM prescriptions";

        try(PreparedStatement statement = Database.getConnection().prepareStatement(sql); ResultSet resultSet = statement.executeQuery())
        {
            while (resultSet.next())
            {
                Prescription prescription = new Prescription(
                        resultSet.getString("prescriptionID"),
                        resultSet.getString("drugID"),
                        resultSet.getString("doctorID"),
                        resultSet.getString("patientID"),
                        resultSet.getDate("datePrescribed").toLocalDate(),
                        resultSet.getInt("dosage"),
                        resultSet.getInt("duration"),
                        resultSet.getString("comment")
                );

                prescriptions.add(prescription);
            }
        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
            return null;
        }

        return prescriptions;
    }
}
