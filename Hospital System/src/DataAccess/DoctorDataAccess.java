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
                statement.setString(6, specialist.getSpecialization().toString());
                statement.setInt(7, specialist.getExperience());
            }
            else
            {
                // Set as null for regular doctors
                statement.setNull(6, java.sql.Types.VARCHAR);
                statement.setNull(7, java.sql.Types.INTEGER);
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

    public static void deleteDoctor(Doctor doctor)
    {
        String sql = "DELETE FROM doctors WHERE doctorID = ?";

        try(PreparedStatement statement = Database.getConnection().prepareStatement(sql))
        {
            statement.setString(1, doctor.getDoctorID());
            statement.execute();
        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }
    }

    public static List<Doctor> getAllDoctors()
    {
        List<Doctor> doctors = new ArrayList<>();

        String sql = "SELECT * FROM doctors";

        try(PreparedStatement statement = Database.getConnection().prepareStatement(sql))
        {
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
                            Specializations.valueOf(specialistString), // Converts string to Enum
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
}
