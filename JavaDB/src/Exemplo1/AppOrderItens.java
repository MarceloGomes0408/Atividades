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
 * @author Marcelo Gomeso
 */
public class AppOrderItens {
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
                       "CREATE TABLE IF NOT EXISTS livraria.orderitens (" +
                          "oiID INT PRIMARY KEY AUTO_INCREMENT)" +                                                                      
                        "ENGINE=InnoDB;");

               // Prepare INSERT Statement to Add Books
               try (PreparedStatement prep = conn.prepareStatement(
                           "INSERT INTO livraria.orderitens (oiID)"
                                   + " VALUES (?)",                            
                           Statement.RETURN_GENERATED_KEYS)) {

                   // Add orderItens
                   prep.setInt(1, 1);                  
                   prep.executeBatch();                 
                                    
                                      
                   // Execute Prepared Statements in Batch
                   System.out.println("Item Pedido");
                   int[] updateCounts = prep.executeBatch();
                   for (int count : updateCounts) {

                       // Print Counts
                       System.out.println(count);
                   }
             }

             // Prepare UPDATE Statement
             try (PreparedStatement prep = conn.prepareStatement(
                         "UPDATE livraria.orderitens " +
                         " WHERE oiID = ?")) {

                 // Change orderitens
                 prep.setInt(1, 2);
                 prep.executeBatch();                
                 
             }

             // Prepare DELETE Statement
             try (PreparedStatement prep = conn.prepareStatement(
                         "DELETE FROM livraria.orderitens " +
                         "WHERE oiID = ?")) {

                 // ID of Row to Remove
                 prep.setInt(1, 1);
                 prep.executeBatch();
             }

             // Commit Changes
             conn.commit();

             // Execute a SELECT Statement
             ResultSet orderitens_list = stmt.executeQuery(
                     "SELECT oiID =? " +
                     "FROM livraria.orderitens");

             // Iterate over the Result-set

             System.out.println("Item:");
             while (orderitens_list.next()) {

                 // Print Row
                 System.out.println(
                         String.format(
                             "-%d ",                             
                             orderitens_list.getInt("orID")));                                                           
             }
        }

        // Catch SQL Exceptions for Queries
        catch (SQLException ex) {
             ex.printStackTrace();
             conn.rollback();
        }
      }

      // Catch SQL Exceptions from Connection
      catch (SQLException e) {
         e.printStackTrace();
      }    
   }
}

