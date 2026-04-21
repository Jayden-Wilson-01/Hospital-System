package DataAccess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ConflictDataAccess
{
    public static void addConflict(String drugID, String conflictID)
    {
        String sql = "INSERT INTO drugconflicts (drugID, conflictID) VALUES (?, ?)";

        try(PreparedStatement statement = Database.getConnection().prepareStatement(sql))
        {
            // Set up a transaction so both inserts happen, or neither
            Database.getConnection().setAutoCommit(false);

            // First direction: Drug A -> Drug B
            statement.setString(1, drugID);
            statement.setString(2, conflictID);
            statement.addBatch();

            // Second direction: Drug B -> Drug A
            statement.setString(1, conflictID);
            statement.setString(2, drugID);
            statement.addBatch();

            statement.executeBatch();
            Database.getConnection().commit();

            System.out.println("Conflict successfully added.");
        }
        catch(SQLException e)
        {
            System.out.println(e.getMessage());
        }
    }

    public static void deleteConflict(String drugID,  String conflictID)
    {
        //Needs both IDs to identify the specific link to break
        String sql = "DELETE FROM drugconflicts WHERE (drugID = ? AND conflictID = ?) OR (conflictID = ? AND drugID = ?)";

        try(PreparedStatement statement = Database.getConnection().prepareStatement(sql))
        {
            statement.setString(1, drugID);
            statement.setString(2, conflictID);

            statement.setString(3, drugID);
            statement.setString(4, conflictID);

            statement.executeUpdate();
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }
}
