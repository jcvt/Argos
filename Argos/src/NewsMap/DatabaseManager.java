package NewsMap;
import java.sql.*;

public class DatabaseManager {
    // JDBC driver name and database URL
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
    static final String DB_URL = "jdbc:mysql://localhost/RSS";

    //  Database credentials
    static final String USER = "dbuser";
    static final String PASS = "dbpassword";
    
    //RRS feeds
    static BBCRSS bbc;
    public static void main(String[] args) {
    Connection conn = null;
    Statement stmt = null;
    try{
        System.out.println("Connecting to a selected database...");
        conn = DriverManager.getConnection(DB_URL, USER, PASS);
        System.out.println("Connected database successfully...");
        bbc = new BBCRSS("http://feeds.bbci.co.uk/news/world/rss.xml");
        for (RssArticle art : bbc.getList()){
            stmt = conn.createStatement();
            String sql = "INSERT INTO article " +
                         "VALUES (null,'" +  art.getTitle() + "','" 
                         + art.getDescription() + "','"
                         + art.getFullText() + "','"
                         + art.getDate() + "','"
                         + art.getPublisher() + "')";
            stmt.executeUpdate(sql);
        }
        //STEP 4: Execute a query
        System.out.println("Inserting records into the table...");
        stmt = conn.createStatement();
    
    }
    catch(SQLException se){
        //Handle errors for JDBC
        se.printStackTrace();
     }
    finally{
        //finally block used to close resources
        try{
           if(stmt!=null)
              conn.close();
        }
        catch(SQLException se){
        }// do nothing
        try{
           if(conn!=null)
              conn.close();
        }
        catch(SQLException se){
           se.printStackTrace();
        }//end finally try
     }//end try
     System.out.println("Goodbye!");
  }//end main
}//end JDBCExample