package connections;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

// -------------------------------------------------------------------------
/**
 * DatabaseClient provides connectivity to databases as well as a convientant
 * set of methods to alter a database.
 *
 * @author Rich Episcopo
 * @version Sep 10, 2013
 */
public class DatabaseClient
{
    // Fields
    private String       port;
    private String       server;
    private String       schema;
    private String       dbName;
    private String       table;
    private String       username;
    private String       password;
    private Connection   con;

    private final String INSERT_STATEMENT = "INSERT INTO "
                                              + "`@schema`.`@table` "
                                              + "(`article_id`, `Title`, "
                                              + "`Snippet`, `Address`, "
                                              + "`Date`, `Publisher`) "
                                              + "VALUES (@id, @title, "
                                              + "@snippet, @address, @date,"
                                              + " @publisher);";

    private final String DELETE_STATEMENT = "DELETE FROM `@schema`.`@table`"
                                              + " WHERE 'article_id' = '@id';";


    // ----------------------------------------------------------
    /**
     * Create a new DatabaseClient object.
     *
     * @throws SQLException
     *             If cannot connect to database.
     */
    public DatabaseClient()
        throws SQLException
    {
        con =
            DriverManager.getConnection("jdbc:mysql://" + server + ":" + port
                + "/" + dbName, username, password);

    }


// // ----------------------------------------------------------
// /**
// * Inserts an article object into the array.
// *
// * @param article
// * The Article object to be inserted into the array.
// * @return True if article was inserted, false otherwise.
// */
// public boolean insertArticleIntoDatabase(Article article)
// {
//
// }

}
