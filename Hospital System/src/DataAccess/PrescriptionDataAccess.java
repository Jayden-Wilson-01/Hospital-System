package DataAccess;

import Models.Drug;
import Models.Prescription;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class PrescriptionDataAccess
{
    /**
     * Create a new prescription directly in the database
     * @param prescription the new prescription model with the new details
     */
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

    /**
     * Update an existing prescription directly from the database
     * @param prescription the prescription model with the updated details
     */
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

    /**
     * Delete an existing prescription directly from the database
     * @param prescriptionId the existing prescriptions id
     */
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

    /**
     * Gets the details of all prescriptions within a provided limit
     * @param limit The limit of how many prescription details to fetch from the database
     * @return a list of the prescription models with populated details
     */
    public static List<Prescription> loadAllPrescriptions(int limit)
    {
        List<Prescription> prescriptions = new ArrayList<>();

        //Gets required details from multiple tables e.g. doctors name, patients name
        String sql = "SELECT pre.*, CONCAT(doc.firstname, ' ', doc.surname) AS doctorName, CONCAT(pat.firstname, ' ', pat.surname) AS patientName, drug.drugname FROM prescriptions pre JOIN doctors doc ON pre.doctorID = doc.doctorID JOIN patients pat ON pre.patientID = pat.patientID JOIN drugs drug ON pre.drugID = drug.drugID LIMIT ?";

        try(PreparedStatement statement = Database.getConnection().prepareStatement(sql))
        {
            //Set the statement parameters using the according details
            statement.setInt(1, limit);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next())
            {
                //Create new prescription
                Prescription prescription = new Prescription(
                        resultSet.getString("prescriptionID"),
                        resultSet.getString("drugID"),
                        resultSet.getString("doctorID"),
                        resultSet.getString("patientID"),
                        resultSet.getDate("datePrescribed").toLocalDate(),
                        resultSet.getInt("dosage"),
                        resultSet.getInt("duration"),
                        resultSet.getString("comment"),
                        resultSet.getString(resultSet.getString("doctorName")),
                        resultSet.getString(resultSet.getString("patientName")),
                        resultSet.getString(resultSet.getString("drugName"))
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

    /**
     * Get details of an existing prescription based on provided id
     * @param prescriptionId The id of the existing prescription
     * @return A singular prescription model
     */
    public static Prescription getPrescriptionById(String prescriptionId)
    {
        String sql = "SELECT pre.*, CONCAT(doc.firstname, ' ', doc.surname) AS doctorName, CONCAT(pat.firstname, ' ', pat.surname) AS patientName, drug.drugname FROM prescriptions pre JOIN doctors doc ON pre.doctorID = doc.doctorID JOIN patients pat ON pre.patientID = pat.patientID JOIN drugs drug ON pre.drugID = drug.drugID WHERE pre.prescriptionID = ?";

        try(PreparedStatement statement = Database.getConnection().prepareStatement(sql))
        {
            //Set the statement parameters using the according details
            statement.setString(1, prescriptionId);

            try(ResultSet resultSet = statement.executeQuery())
            {
                if (resultSet.next())
                {
                    //Create new prescription
                    return new Prescription(
                            resultSet.getString("prescriptionID"),
                            resultSet.getString("drugID"),
                            resultSet.getString("doctorID"),
                            resultSet.getString("patientID"),
                            resultSet.getDate("datePrescribed").toLocalDate(),
                            resultSet.getInt("dosage"),
                            resultSet.getInt("duration"),
                            resultSet.getString("comment"),
                            resultSet.getString(resultSet.getString("doctorName")),
                            resultSet.getString(resultSet.getString("patientName")),
                            resultSet.getString(resultSet.getString("drugName"))
                    );
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

    /**
     * Gets a list of drugs a patient is prescribed
     * @param prescriptionId the id of the prescription
     * @return A list of drug id's the patient is prescribed
     */
    public static HashSet<String> getDrugIdByPatientId(String prescriptionId)
    {
        String sql = "SELECT drugID FROM prescriptions WHERE patientID = ?";

        try(PreparedStatement statement = Database.getConnection().prepareStatement(sql))
        {
            //used a hashset in the event that there are duplicated drug id's;
            HashSet<String> drugIds = new  HashSet<>();

            statement.setString(1, prescriptionId);

            try(ResultSet resultSet = statement.executeQuery())
            {
                while (resultSet.next())
                {
                    drugIds.add(resultSet.getString("drugID"));
                }

                return drugIds;
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

    /**
     * Get details of each prescription found with the same parameters
     * @param patientFirstname First parameter: The firstname of the patient
     * @param patientSurname Second parameter: The surname of the patient
     * @param doctorFirstname Third parameter: The firstname of the doctor
     * @param doctorSurname Forth parameter: The surname of the doctor
     * @return A list of each prescription that matches the same parameter
     */
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
