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

    public static Doctor getDoctorById(String doctorID)
    {

        String sql = "SELECT * FROM doctors WHERE doctorID = ?";

        try(PreparedStatement statement = Database.getConnection().prepareStatement(sql))
        {
            statement.setString(1, doctorID);
            ResultSet rs = statement.executeQuery();

            if(rs.next())
            {
                // Logic to determine if this row is a Specialist or a Doctor
                String specialistString = rs.getString("specialization");

                if (specialistString != null && !specialistString.isEmpty())
                {
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

    public static List<Doctor> getDoctorByParameters(String firstname, String surname)
    {
        List<Doctor> doctors = new ArrayList<>();

        String sql = "SELECT * FROM doctors WHERE firstname =  ? AND surname = ?";

        try(PreparedStatement statement = Database.getConnection().prepareStatement(sql))
        {
            statement.setString(1, firstname);
            statement.setString(2, surname);
            ResultSet rs = statement.executeQuery();

            while(rs.next())
            {
                // Logic to determine if this row is a Specialist or a Doctor
                String specialistString = rs.getString("specialization");

                if (specialistString != null && !specialistString.isEmpty())
                {
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

    public static List<Doctor> getDoctorBySpeciality(String specialization)
    {
        List<Doctor> doctors = new ArrayList<>();

        String sql = "SELECT * FROM doctors WHERE specialization =  ?";

        try(PreparedStatement statement = Database.getConnection().prepareStatement(sql))
        {
            statement.setString(1, specialization);
            ResultSet rs = statement.executeQuery();

            while(rs.next())
            {
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
