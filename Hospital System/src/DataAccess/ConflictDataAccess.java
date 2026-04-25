package DataAccess;

import Models.Conflict;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class ConflictDataAccess
{
    /**
     * Used to add a drug conflict directly to the database
     * @param drugID the id of the current drug
     * @param conflictID The id of the drug that causes a conflict with the current drug
     */
    public static void addConflict(String drugID, String conflictID)
    {
        String sql = "INSERT INTO drugconflicts (drugID, conflictID) VALUES (?, ?)";

        try(PreparedStatement statement = Database.getConnection().prepareStatement(sql))
        {
            //Set up a transaction so both inserts happen, or neither
            Database.getConnection().setAutoCommit(false);

            //First direction: Drug A -> Drug B
            statement.setString(1, drugID);
            statement.setString(2, conflictID);
            statement.addBatch();

            //Second direction: Drug B -> Drug A
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

    /**
     * Used to delete an existing conflict between two drugs
     * @param drugID The id of the current drug
     * @param conflictID The id of the drug that causes a conflict with the current drug
     */
    public static void deleteConflict(String drugID,  String conflictID)
    {
        //Needs both IDs to identify the specific link to break
        String sql = "DELETE FROM drugconflicts WHERE (drugID = ? AND conflictID = ?) OR (conflictID = ? AND drugID = ?)";

        try(PreparedStatement statement = Database.getConnection().prepareStatement(sql))
        {
            //Delete both directions of conflict A -> B and B- > A
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

    /**
     * Get a list of all drug id's that conflict with a defined drug
     * @param drugID The id of the drug you want to find conflicts for
     * @return A HashSet of all id's of conflicting drugs. HashSet used to prevent duplicate id's
     */
    public static HashSet<Conflict> getConflictsForID(String drugID)
    {
        HashSet<Conflict> conflicts = new HashSet<>();

        String sql = "SELECT dc.conflictID, d.drugName FROM drugconflicts dc JOIN drugs d ON dc.conflictID = d.drugID WHERE dc.drugID = ?";

        try (PreparedStatement statement = Database.getConnection().prepareStatement(sql))
        {
            statement.setString(1, drugID);

            try (ResultSet resultSet = statement.executeQuery())
            {
                while (resultSet.next())
                {
                    //Create a new model object for every row found
                    Conflict conflict = new Conflict(
                            resultSet.getString("conflictID"),
                            resultSet.getString("drugName")
                    );

                    conflicts.add(conflict);
                }
            }
        }
        catch (SQLException e)
        {
            System.err.println("Database error: " + e.getMessage());
        }

        return conflicts;
    }
}
