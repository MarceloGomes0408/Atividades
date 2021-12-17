
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
public class AppUser {
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
           try (Statement stmtl = conn.createStatement()) {
               stmtl.execute(
                       "CREATE TABLE IF NOT EXISTS livraria.user (" +
                          "id INT PRIMARY KEY AUTO_INCREMENT," +                          
                          "name VARCHAR(25)," +
                          "type VARCHAR(25))" +
                        "ENGINE=InnoDB;");

               // Prepare INSERT Statement to Add Books
               try (PreparedStatement prep = conn.prepareStatement(
                           "INSERT INTO livraria.user (name, type)"
                                   + " VALUES (?, ?)",                            
                           Statement.RETURN_GENERATED_KEYS)) {

                   // Add autor
                   prep.setString(1, "Dois");
                   prep.setString(2, "Suspense");
                   prep.execute();
                   
                   prep.setString(1, "Imperdoável");  
                   prep.setString(2, "Drama");
                   prep.execute();
                   
                   prep.setString(1,  "Outra Vida"); 
                   prep.setString(2, "Série");
                   prep.execute();
                                
                   // Execute Prepared Statements in Batch
                   System.out.println("User: ");
                   int[] updateCounts = prep.executeBatch();
                   for (int count : updateCounts) {

                       // Print Counts
                       System.out.println(count);
                   }
             }

             // Prepare UPDATE Statement
             try (PreparedStatement prep = conn.prepareStatement(
                         "UPDATE livraria.user " +
                         "SET type = ? WHERE id = ?")) {

                 // Change Email Address
                 prep.setString(1, "Série");

                 // Change ID
                 prep.setInt(1,1);
                 prep.executeBatch();
             }

             // Prepare DELETE Statement
             try (PreparedStatement prep = conn.prepareStatement(
                         "DELETE FROM livraria.user " +
                         "WHERE id = ?")) {

                 // ID of Row to Remove
                 prep.setInt(1, 1);
                 prep.executeBatch();
             }

             // Commit Changes
             conn.commit();

             // Execute a SELECT Statement
             ResultSet user_list = stmtl.executeQuery(
                     "SELECT name, type " +
                     "FROM livraria.user");

             // Iterate over the Result-set

             //System.out.println("User:");
             while (user_list.next()) {

                 // Print Row
                 System.out.println(
                         String.format(
                             "- %s <%s>",                             
                             user_list.getString("name"),
                             user_list.getString("type")));                              
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
