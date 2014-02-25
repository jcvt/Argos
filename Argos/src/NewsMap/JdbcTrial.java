package NewsMap;
import java.sql.*;

public class JdbcTrial {

       // JDBC driver name and database URL
       static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
       static final String DB_URL = "jdbc:mysql://localhost/RSS";

       //  Database credentials
       static final String USER = "dbuser";
       static final String PASS = "dbpassword";
       
       public static void main(String[] args) {
       Connection conn = null;
       Statement stmt = null;
       try{
          //STEP 2: Register JDBC driver
          //TODO might not need this
          //Class.forName("com.mysql.jdbc.Driver");

          //STEP 3: Open a connection
          System.out.println("Connecting to a selected database...");
          conn = DriverManager.getConnection(DB_URL, USER, PASS);
          System.out.println("Connected database successfully...");
          
          //STEP 4: Execute a query
          System.out.println("Inserting records into the table...");
          stmt = conn.createStatement();
          
          String sql = "INSERT INTO article " +
                       "VALUES (null,'Venezuela to expel US officials', "
                       + "'Venezuelan President Nicolas Maduro expels three US consular officials, accusing them of meeting students involved in anti-government protests.',"
                       + " 'Venezuela to expel US officials Venezuelan President Nicolas Maduro expels three US consular officials accusing them of meeting students involved in anti-government protests.',"
                       + "'2014-02-17',"
                       + "'BBC')";
          stmt.executeUpdate(sql);
          System.out.println("Inserted records into the table...");

       }catch(SQLException se){
          //Handle errors for JDBC
          se.printStackTrace();
       }catch(Exception e){
          //Handle errors for Class.forName
          e.printStackTrace();
       }finally{
          //finally block used to close resources
          try{
             if(stmt!=null)
                conn.close();
          }catch(SQLException se){
          }// do nothing
          try{
             if(conn!=null)
                conn.close();
          }catch(SQLException se){
             se.printStackTrace();
          }//end finally try
       }//end try
       System.out.println("Goodbye!");
    }//end main
}//end JDBCExample
