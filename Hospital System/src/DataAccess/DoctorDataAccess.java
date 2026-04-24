package DataAccess;

import Models.Doctor;
import Models.Specialist;
import Models.Specializations;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DoctorDataAccess
{
    /**
     * Creates a new doctor directly to teh database
     * @param doctor The doctor to add. The model should include the details and the parameters to be used in the query
     */
    public static void createDoctor(Doctor doctor)
    {
        String sql = "INSERT INTO doctors (doctorID, firstname, surname, address, email, hospital, specialization, experience) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try(PreparedStatement statement = Database.getConnection().prepareStatement(sql))
        {
            //Set the statement parameters using the according details from the model
            statement.setString(1, doctor.getDoctorID());
            statement.setString(2, doctor.getFirstName());
            statement.setString(3, doctor.getSurname());
            statement.setString(4, doctor.getAddress());
            statement.setString(5, doctor.getEmail());
            statement.setString(6, doctor.getHospital());

            //Set Specialist specific parameters if applicable
            if (doctor instanceof Specialist specialist)
            {
                statement.setString(7, specialist.getSpecialization());
                statement.setInt(8, specialist.getExperience());
            }
            else
            {
                //Set as null for regular doctors
                statement.setNull(7, java.sql.Types.VARCHAR);
                statement.setNull(8, java.sql.Types.INTEGER);
            }

            statement.execute();
        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Updates an existing doctor directly from the database
     * @param doctor The doctor to update. The model should include the new details and the parameters to be used in the query
     */
    public static void updateDoctor(Doctor doctor)
    {
        String sql = "UPDATE doctors SET firstname= ?, surname= ?, address= ?, email= ?, hospital= ?, specialization= ?, experience= ? WHERE doctorID=?";

        try(PreparedStatement statement = Database.getConnection().prepareStatement(sql))
        {
            //Set the statement parameters using the according details from the model
            statement.setString(1, doctor.getFirstName());
            statement.setString(2, doctor.getSurname());
            statement.setString(3, doctor.getAddress());
            statement.setString(4, doctor.getEmail());
            statement.setString(5, doctor.getHospital());

            //Set Specialist specific parameters if applicable
            if (doctor instanceof Specialist specialist)
            {
                statement.setString(6, specialist.getSpecialization());
                statement.setInt(7, specialist.getExperience());
            }
            else
            {
                //Set as null for regular doctors
                statement.setNull(6, java.sql.Types.VARCHAR);
                statement.setNull(7, java.sql.Types.INTEGER);
            }

            statement.setString(8, doctor.getDoctorID());
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Delete existing doctor directly from the database
     * @param doctorID The id of teh doctor to delete
     */
    public static void deleteDoctor(String doctorID)
    {
        String sql = "DELETE FROM doctors WHERE doctorID = ?";

        try(PreparedStatement statement = Database.getConnection().prepareStatement(sql))
        {
            //Set the statement parameters using the provided id
            statement.setString(1, doctorID);
            statement.execute();
        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Gets the details of each doctor directly from the database
     * @param limit the limit for how many doctors to get details of
     * @return a list of every doctor
     */
    public static List<Doctor> getAllDoctors(int limit)
    {
        List<Doctor> doctors = new ArrayList<>();

        String sql = "SELECT * FROM doctors LIMIT ?";

        try(PreparedStatement statement = Database.getConnection().prepareStatement(sql))
        {
            //Set the statement parameters using the provided limit
            statement.setInt(1, limit);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next())
            {
                // Logic to determine if this row is a Specialist or a Doctor
                String specialistString = resultSet.getString("specialization");

                if (specialistString != null && !specialistString.isEmpty())
                {
                    //Create a specialist using specialist subclass model
                    Specialist specialist = new Specialist(
                            resultSet.getString("doctorID"),
                            resultSet.getString("firstname"),
                            resultSet.getString("surname"),
                            resultSet.getString("address"),
                            resultSet.getString("email"),
                            resultSet.getString("hospital"),
                            resultSet.getString("specialization"),
                            resultSet.getInt("experience")
                    );

                    doctors.add(specialist);
                }
                else
                {
                    //Create a standard doctor using the doctor model
                    Doctor doctor = new Doctor(
                            resultSet.getString("doctorID"),
                            resultSet.getString("firstname"),
                            resultSet.getString("surname"),
                            resultSet.getString("address"),
                            resultSet.getString("email"),
                            resultSet.getString("hospital")
                    );

                    doctors.add(doctor);
                }
            }
        }
        catch (SQLException e)
        {
            System.err.println(e.getMessage());
        }

        return doctors;
    }

    /**
     * Get the details of a chosen doctor
     * @param doctorID the id of the doctor you want teh details of
     * @return a singular populated doctor model (Includes variations like subclasses e.g. specialist)
     */
    public static Doctor getDoctorById(String doctorID)
    {

        String sql = "SELECT * FROM doctors WHERE doctorID = ?";

        try(PreparedStatement statement = Database.getConnection().prepareStatement(sql))
        {
            //Set the statement parameters using the provided id
            statement.setString(1, doctorID);
            ResultSet rs = statement.executeQuery();

            if(rs.next())
            {
                //Logic to determine if this row is a Specialist or a Doctor
                String specialistString = rs.getString("specialization");

                if (specialistString != null && !specialistString.isEmpty())
                {
                    //Create a specialist using specialist model (subclass of doctor)
                    return new Specialist(
                            rs.getString("doctorID"),
                            rs.getString("firstname"),
                            rs.getString("surname"),
                            rs.getString("address"),
                            rs.getString("email"),
                            rs.getString("hospital"),
                            rs.getString("specialization"),
                            rs.getInt("experience")
                    );
                }
                else
                {
                    //Create s standard doctor using doctor model
                    return new Doctor(
                            rs.getString("doctorID"),
                            rs.getString("firstname"),
                            rs.getString("surname"),
                            rs.getString("address"),
                            rs.getString("email"),
                            rs.getString("hospital")
                    );
                }
            }
        }
        catch (SQLException e)
        {
            System.err.println(e.getMessage());
            return null;
        }

        return null;
    }

    /**
     * Gets a list of doctors directly from the database based on the provided parameters
     * @param firstname First parameter: doctors firstname
     * @param surname Second parameter: doctors surname
     * @return A list of all doctors that match provided parameters
     */
    public static List<Doctor> getDoctorByParameters(String firstname, String surname)
    {
        List<Doctor> doctors = new ArrayList<>();

        String sql = "SELECT * FROM doctors WHERE firstname =  ? AND surname = ?";

        try(PreparedStatement statement = Database.getConnection().prepareStatement(sql))
        {
            //Set the statement parameters using the according details
            statement.setString(1, firstname);
            statement.setString(2, surname);
            ResultSet rs = statement.executeQuery();

            while(rs.next())
            {
                //Logic to determine if this row is a Specialist or a Doctor
                String specialistString = rs.getString("specialization");

                if (specialistString != null && !specialistString.isEmpty())
                {
                    //Create a specialist using specialist model (subclass of doctor)
                    Specialist specialist = new  Specialist(
                            rs.getString("doctorID"),
                            rs.getString("firstname"),
                            rs.getString("surname"),
                            rs.getString("address"),
                            rs.getString("email"),
                            rs.getString("hospital"),
                            rs.getString("specialization"),
                            rs.getInt("experience")
                    );

                    doctors.add(specialist);
                }
                else
                {
                    //Create a standard doctor using doctor model
                    Doctor doctor = new Doctor(
                            rs.getString("doctorID"),
                            rs.getString("firstname"),
                            rs.getString("surname"),
                            rs.getString("address"),
                            rs.getString("email"),
                            rs.getString("hospital")
                    );

                    doctors.add(doctor);
                }
            }

            return doctors;
        }
        catch (SQLException e)
        {
            System.err.println(e.getMessage());
            return null;
        }
    }

    /**
     * Gets a list of doctors directly from the database based on their specialization
     * @param specialization The specialization you want to search for that doctors have
     * @return A list of doctors that have the same specialization
     */
    public static List<Doctor> getDoctorBySpeciality(String specialization)
    {
        List<Doctor> doctors = new ArrayList<>();

        String sql = "SELECT * FROM doctors WHERE specialization =  ?";

        try(PreparedStatement statement = Database.getConnection().prepareStatement(sql))
        {
            //Set the statement parameters using the according details
            statement.setString(1, specialization);
            ResultSet rs = statement.executeQuery();

            while(rs.next())
            {
                /*Create a specialist using the specialist model.
                As you are searching based on specialization you don't have the ability
                to have standard doctors
                 */
                Specialist specialist = new  Specialist(
                        rs.getString("doctorID"),
                        rs.getString("firstname"),
                        rs.getString("surname"),
                        rs.getString("address"),
                        rs.getString("email"),
                        rs.getString("hospital"),
                        rs.getString("specialization"),
                        rs.getInt("experience")
                );

                doctors.add(specialist);
            }

            return doctors;
        }
        catch (SQLException e)
        {
            System.err.println(e.getMessage());
            return null;
        }
    }
}
