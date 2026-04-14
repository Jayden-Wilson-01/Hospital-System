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
    public static void createDoctor(Doctor doctor)
    {
        //remove doctorID as doctorID is automatically assigned by the database via AUTO_INCREMENT?
        String sql = "INSERT INTO doctors (doctorID, firstname, surname, address, email, hospital, specialization, experience) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try(PreparedStatement statement = Database.getConnection().prepareStatement(sql))
        {
            statement.setString(1, doctor.getDoctorID());
            statement.setString(2, doctor.getFirstName());
            statement.setString(3, doctor.getSurname());
            statement.setString(4, doctor.getAddress());
            statement.setString(5, doctor.getEmail());
            statement.setString(6, doctor.getHospital());

            // Map Specialist specific fields if applicable
            if (doctor instanceof Specialist specialist)
            {
                statement.setString(7, specialist.getSpecialization().toString());
                statement.setInt(8, specialist.getExperience());
            }
            else
            {
                // Set as null for regular doctors
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

    public static void updateDoctor(Doctor doctor)
    {
        String sql = "UPDATE doctors SET firstname= ?, surname= ?, address= ?, email= ?, hospital= ?, specialization= ?, experience= ? WHERE doctorID=?";

        try(PreparedStatement statement = Database.getConnection().prepareStatement(sql))
        {
            statement.setString(1, doctor.getFirstName());
            statement.setString(2, doctor.getSurname());
            statement.setString(3, doctor.getAddress());
            statement.setString(4, doctor.getEmail());
            statement.setString(5, doctor.getHospital());

            if (doctor instanceof Specialist specialist)
            {
                statement.setString(6, specialist.getSpecialization().toString());
                statement.setInt(7, specialist.getExperience());
            }
            else
            {
                statement.setNull(6, java.sql.Types.VARCHAR);
                statement.setNull(7, java.sql.Types.INTEGER);
            }

            statement.setString(8, doctor.getDoctorID()); // The ID for the WHERE clause
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deleteDoctor(String doctorID)
    {
        String sql = "DELETE FROM doctors WHERE doctorID = ?";

        try(PreparedStatement statement = Database.getConnection().prepareStatement(sql))
        {
            statement.setString(1, doctorID);
            statement.execute();
        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }
    }

    public static List<Doctor> getAllDoctors(int limit)
    {
        List<Doctor> doctors = new ArrayList<>();

        String sql = "SELECT * FROM doctors LIMIT ?";

        try(PreparedStatement statement = Database.getConnection().prepareStatement(sql))
        {
            statement.setInt(1, limit);
            ResultSet rs = statement.executeQuery();

            while (rs.next())
            {
                // Logic to determine if this row is a Specialist or a Doctor
                String specialistString = rs.getString("specialization");

                if (specialistString != null && !specialistString.isEmpty())
                {
                    Specialist specialist = new Specialist(
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
        }
        catch (SQLException e)
        {
            System.err.println(e.getMessage());
        }

        return doctors;
    }

    public static boolean ExistsInDatabase(String doctorID)
    {
        String sql = "SELECT * FROM doctors WHERE doctorID = ?";

        try(PreparedStatement statement = Database.getConnection().prepareStatement(sql))
        {
            statement.setString(1, doctorID);

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

    public static String GetDoctorTypeByID(String doctorID)
    {
        String sql = "SELECT specialization FROM doctors WHERE doctorID = ?";

        try(PreparedStatement statement = Database.getConnection().prepareStatement(sql))
        {
            statement.setString(1, doctorID);

            try (ResultSet resultSet = statement.executeQuery())
            {
                // if rs.next() is true, a row was found
                if(resultSet.next())
                {
                    if(resultSet.getString("specialization") == null)
                    {
                        return "Standard";
                    }
                    else
                    {
                        return "Specialist";
                    }
                }
            }
            catch (SQLException e)
            {
                System.out.println(e.getMessage());
                return "Standard";
            }
        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
            return "Standard";
        }

        return "Standard";
    }
}
