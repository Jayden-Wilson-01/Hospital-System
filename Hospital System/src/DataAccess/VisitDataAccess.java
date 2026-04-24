package DataAccess;

import Models.Prescription;
import Models.Visit;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class VisitDataAccess
{
    /**
     * Create a new visit directly in the database
     * @param visit the new visit model with the new details
     */
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

    /**
     * Update an existing visit directly from the database
     * @param visit the visit model with the updated details
     */
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

    /**
     * Delete an existing visit directly from the database
     * @param visitId the existing visits id
     */
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

    /**
     * Gets the details of all visit within a provided limit
     * @param limit The limit of how many visit details to fetch from the database
     * @return a list of the visit models with populated details
     */
    public static List<Visit> loadAllVisits(int limit)
    {
        List<Visit> visits = new ArrayList<>();
        String sql = "SELECT vis.*, CONCAT(doc.firstname, ' ', doc.surname) AS doctorName, CONCAT(pat.firstname, ' ', pat.surname) AS patientName FROM visits vis JOIN doctors doc ON vis.doctorID = doc.doctorID JOIN patients pat ON vis.patientID = pat.patientID LIMIT ?";

        try(PreparedStatement statement = Database.getConnection().prepareStatement(sql))
        {
            //Set the statement parameters using the according details
            statement.setInt(1, limit);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next())
            {
                //Create new visit
                Visit visit = new Visit(
                        resultSet.getString("visitID"),
                        resultSet.getString("patientID"),
                        resultSet.getString("doctorID"),
                        resultSet.getDate("dateOfVisit").toLocalDate(),
                        resultSet.getString("symptoms"),
                        resultSet.getString("diagnosis"),
                        resultSet.getString("patientName"),
                        resultSet.getString("doctorName")
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

    /**
     * Get details of an existing visit based on provided id
     * @param visitId The id of the existing visit
     * @return A singular visit model
     */
    public static Visit getVisitById(String visitId)
    {
        String sql = "SELECT vis.*, CONCAT(doc.firstname, ' ', doc.surname) AS doctorName, CONCAT(pat.firstname, ' ', pat.surname) AS patientName FROM visits vis JOIN doctors doc ON vis.doctorID = doc.doctorID JOIN patients pat ON vis.patientID = pat.patientID WHERE visitId = ?";

        try(PreparedStatement statement = Database.getConnection().prepareStatement(sql))
        {
            //Set the statement parameters using the according details
            statement.setString(1, visitId);

            try(ResultSet resultSet = statement.executeQuery())
            {
                if (resultSet.next())
                {
                    //Create new visit
                    return new Visit(
                            resultSet.getString("visitID"),
                            resultSet.getString("patientID"),
                            resultSet.getString("doctorID"),
                            resultSet.getDate("dateOfVisit").toLocalDate(),
                            resultSet.getString("symptoms"),
                            resultSet.getString("diagnosis"),
                            resultSet.getString("patientName"),
                            resultSet.getString("doctorName")
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
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
            return null;
        }
    }

    /**
     * Get details of each drug found with the same parameters
     * @param patientFirstname First parameter: The firstname of the patient
     * @param patientSurname Second parameter: The surname of the patient
     * @param doctorFirstname Third parameter: The firstname of the doctor
     * @param doctorSurname Forth parameter: The surname of the doctor
     * @return A list of each drug that matches the same parameter
     */
    public static List<Visit> getVisitByParameters(String patientFirstname, String patientSurname, String doctorFirstname, String  doctorSurname)
    {
        List<Visit> visits = new ArrayList<>();

        String sql = "SELECT vis.*, CONCAT(doc.firstname, ' ', doc.surname) AS doctorName, CONCAT(pat.firstname, ' ', pat.surname) AS patientName FROM visits vis LEFT JOIN doctors doc ON vis.doctorID = doc.doctorID LEFT JOIN patients pat ON vis.patientID = pat.patientID WHERE pat.firstname = ? AND pat.surname = ? AND doc.surname = ? AND doc.surname = ?";

        try(PreparedStatement statement = Database.getConnection().prepareStatement(sql))
        {
            //Set the statement parameters using the according details
            statement.setString(1, patientFirstname);
            statement.setString(2, patientSurname);
            statement.setString(3, doctorFirstname);
            statement.setString(4, doctorSurname);

            try(ResultSet resultSet = statement.executeQuery())
            {
                while (resultSet.next())
                {
                    //Create new visit
                    Visit visit = new Visit(
                            resultSet.getString("visitID"),
                            resultSet.getString("patientID"),
                            resultSet.getString("doctorID"),
                            resultSet.getDate("dateOfVisit").toLocalDate(),
                            resultSet.getString("symptoms"),
                            resultSet.getString("diagnosis"),
                            resultSet.getString("patientName"),
                            resultSet.getString("doctorName")
                    );

                    visits.add(visit);
                }

                return visits;
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
