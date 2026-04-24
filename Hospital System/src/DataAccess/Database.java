package DataAccess;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Database
{
    /**
     * The database Connection string
     * @return the connection to the database via a URL
     */
    public static Connection getConnection()
    {
        String url = "jdbc:mariadb://localhost:3306/healthcare";
        String user = "root";
        String password = "Chernobyl112900";

        try
        {
            return DriverManager.getConnection(url, user,password);
        }
        catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
    }
}
