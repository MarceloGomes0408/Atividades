package Exemplo1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 *
 * @author Marcelo Gomes
 */
public class AppOrder {
    public static void main(String[] argv) {

       // Connection Configuration
       Properties connConfig = new Properties();
       connConfig.setProperty("user", "root");
       connConfig.setProperty("password", "17012001");
       //connConfig.setProperty("useSsl", "true");
       //connConfig.setProperty("serverSslCert", "/path/to/ca-bundle.pem");

       // Create Connection to MariaDB Enterprise
       try (Connection conn = DriverManager.getConnection("jdbc:mariadb://127.0.0.1:3306", connConfig)) {

           // Disable Auto-Commit
           conn.setAutoCommit(false);

           // Use Statement to Create library.books table
           try (Statement stmt = conn.createStatement()) {
                stmt.execute(
                       "CREATE TABLE IF NOT EXISTS livraria.order (" +
                               "orid INT PRIMARY KEY AUTO_INCREMENT, " +
                               "date VARCHAR(8)," +
                               "numberofbooks INT (6)NOT NULL)" +
                               "ENGINE=InnoDB;");

               // Prepare INSERT Statement to Add Books
               try (PreparedStatement prep = conn.prepareStatement(
                           "INSERT INTO livraria.order (date, numberofbooks)"
                                   + " VALUES (?, ?)",                            
                           Statement.RETURN_GENERATED_KEYS)) {

                   // Add order
                   prep.setString(1, "10/12/2021");
                   prep.setInt(2, 10);
                   prep.executeBatch();                 
                   
                   prep.setString(1, "15/12/2021");
                   prep.setInt(2, 25);
                   prep.executeBatch();
                   
                   prep.setString(1, "25/12/2021");
                   prep.setInt(2, 70);
                   prep.executeBatch();                  
                  
                                
                   // Execute Prepared Statements in Batch
                   System.out.println("Pedido: ");
                   int[] updateCounts = prep.executeBatch();
                   for (int count : updateCounts) {

                       // Print Counts
                       System.out.println(count);
                   }
             }

             // Prepare UPDATE Statement
             try (PreparedStatement prep = conn.prepareStatement(
                         "UPDATE livraria.order " +
                         " WHERE orid = ?")) {

                 // Change name
                 prep.setInt(1, 1);

                 // Change ID
                 prep.setInt(1,1);
                 prep.executeBatch();
             }

             // Prepare DELETE Statement
             try (PreparedStatement prep = conn.prepareStatement(
                         "DELETE FROM livraria.order " +
                         "WHERE orid = ?")) {

                 // ID of Row to Remove
                 prep.setInt(1, 1);
                 prep.executeBatch();
             }

             // Commit Changes
             conn.commit();

             // Execute a SELECT Statement
             ResultSet order_list = stmt.executeQuery(
                     "SELECT date, numberofbooks " +
                     "FROM livraria.order");

             // Iterate over the Result-set

             System.out.println("Order:");
             while (order_list.next()) {

                 // Print Row
                 System.out.println(
                         String.format(
                             "- %s <%d>",                             
                             order_list.getString("date"),
                             order_list.getInt("numberofbooks")));                              
                    }          
        }

        // Catch SQL Exceptions for Queries
        catch (SQLException exc) {
             exc.printStackTrace();
             conn.rollback();
        }
      }

      // Catch SQL Exceptions from Connection
      catch (SQLException e) {
         e.printStackTrace();
      }    
   }
}
