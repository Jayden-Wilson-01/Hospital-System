package DataAccess;

import Models.Drug;
import Models.Prescription;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
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

    public static void deletePrescription(String prescriptionId)
    {
        String sql = "DELETE FROM prescriptions WHERE prescriptionID = ?";

        try(PreparedStatement statement = Database.getConnection().prepareStatement(sql))
        {
            statement.setString(1, prescriptionId);
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
        String sql = "SELECT pre.*, " +
                "CONCAT(doc.firstname, ' ', doc.surname) AS doctorName, " +
                "CONCAT(pat.firstname, ' ', pat.surname) AS patientName, " +
                "drug.drugname " +
                "FROM prescriptions pre " +
                "JOIN doctors doc ON pre.doctorID = doc.doctorID " +
                "JOIN patients pat ON pre.patientID = pat.patientID " +
                "JOIN drugs drug ON pre.drugID = drug.drugID";

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

                prescription.setDoctorName(resultSet.getString("doctorName"));
                prescription.setPatientName(resultSet.getString("patientName"));
                prescription.setDrugName(resultSet.getString("drugName"));

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

    public static Prescription getPrescriptionById(String prescriptionId)
    {
        String sql = "SELECT pre.*, " +
                "CONCAT(doc.firstname, ' ', doc.surname) AS doctorName, " +
                "CONCAT(pat.firstname, ' ', pat.surname) AS patientName, " +
                "drug.drugname " +
                "FROM prescriptions pre " +
                "JOIN doctors doc ON pre.doctorID = doc.doctorID " +
                "JOIN patients pat ON pre.patientID = pat.patientID " +
                "JOIN drugs drug ON pre.drugID = drug.drugID " +
                "WHERE pre.prescriptionID = ?";

        try(PreparedStatement statement = Database.getConnection().prepareStatement(sql))
        {
            statement.setString(1, prescriptionId);

            try(ResultSet resultSet = statement.executeQuery())
            {
                if (resultSet.next())
                {
                    Prescription prescription = new Prescription();

                    prescription.setPrescriptionID(resultSet.getString("prescriptionID"));
                    prescription.setDrugID(resultSet.getString("drugID"));
                    prescription.setDoctorID(resultSet.getString("doctorID"));
                    prescription.setPatientID(resultSet.getString("patientID"));
                    prescription.setDatePrescribed(resultSet.getDate("datePrescribed").toLocalDate());
                    prescription.setDosage(resultSet.getInt("dosage"));
                    prescription.setDuration(resultSet.getInt("duration"));
                    prescription.setComment(resultSet.getString("comment"));

                    //From the join
                    prescription.setDoctorName(resultSet.getString("doctorName"));
                    prescription.setPatientName(resultSet.getString("patientName"));
                    prescription.setDrugName(resultSet.getString("drugName"));

                    return prescription;
                }
            }
            catch (Exception e)
            {
                System.out.println(e.getMessage());
                return null;
            }

            return null;
        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public static List<Prescription> getPrescriptionByParameters(String patientFirstname, String patientSurname, String doctorFirstname, String doctorSurname)
    {
        List<Prescription> prescriptions = new ArrayList<>();

        String sql = "SELECT pre.*, CONCAT(doc.firstname, ' ', doc.surname) AS doctorName, CONCAT(pat.firstname, ' ', pat.surname) AS patientName, drug.drugname FROM prescriptions pre LEFT JOIN doctors doc ON pre.doctorID = doc.doctorID LEFT JOIN patients pat ON pre.patientID = pat.patientID LEFT JOIN drugs drug ON pre.drugID = drug.drugID WHERE pat.firstname = ? AND pat.surname = ? AND doc.firstname = ? AND doc.surname = ?";

        try(PreparedStatement statement = Database.getConnection().prepareStatement(sql))
        {
            statement.setString(1, patientFirstname);
            statement.setString(2, patientSurname);
            statement.setString(3, doctorFirstname);
            statement.setString(4, doctorSurname);

            try(ResultSet resultSet = statement.executeQuery())
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
                            resultSet.getString("comment"),
                            resultSet.getString("doctorName"),
                            resultSet.getString("patientName"),
                            resultSet.getString("drugName")
                    );

                    prescriptions.add(prescription);
                }

                return prescriptions;
            }
            catch (Exception e)
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
