

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
public class AppBook {
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
                       "CREATE TABLE IF NOT EXISTS livraria.book (" +
                          "boid INT PRIMARY KEY AUTO_INCREMENT," +                          
                          "name VARCHAR(25)," +
                          "numPags INT(9)," +
                          "quantidade INT(9))"+
                        "ENGINE=InnoDB;");

               // Prepare INSERT Statement to Add Books
               try (PreparedStatement prep = conn.prepareStatement(
                           "INSERT INTO livraria.book (name, numPags, quantidade)"
                                   + " VALUES (?,?,?)",                            
                           Statement.RETURN_GENERATED_KEYS)) {

                   // Add book
                   prep.setString(1, "Capitães de Areia");
                   prep.setInt(2, 100);
                   prep.setInt(3, 10);
                   prep.execute();
                   
                   prep.setString(1, "Dom Casmurro");   
                   prep.setInt(2, 120);
                   prep.setInt(3, 20);
                   prep.execute();
                   
                   prep.setString(1,  "O Reacionário");  
                   prep.setInt(2, 150);
                   prep.setInt(3, 30);
                   prep.execute();
                                
                   // Execute Prepared Statements in Batch
                   System.out.println("Biblioteca: ");
                   int[] updateCounts = prep.executeBatch();
                   for (int count : updateCounts) {

                       // Print Counts
                       System.out.println(count);
                   }
             }

             // Prepare UPDATE Statement
             try (PreparedStatement prep = conn.prepareStatement(
                         "UPDATE livraria.book " +
                         "SET name = ? WHERE id = ?")) {

                 // Change Email Address
                 prep.setString(1, "O Reacionário");

                 // Change ID
                 prep.setInt(1,1);
                 prep.executeBatch();
             }

             // Prepare DELETE Statement
             try (PreparedStatement prep = conn.prepareStatement(
                         "DELETE FROM livraria.book " +
                         "WHERE id = ?")) {

                 // ID of Row to Remove
                 prep.setInt(1, 1);
                 prep.executeBatch();
             }

             // Commit Changes
             conn.commit();

             // Execute a SELECT Statement
             ResultSet book_list = stmtl.executeQuery(
                     "SELECT name, numPags, quantidade " +
                     "FROM livraria.book");

             // Iterate over the Result-set

             System.out.println("Livro do ator:");
             while (book_list.next()) {

                 // Print Row
                 System.out.println(
                         String.format(
                             "- %s %d <%d>",                             
                             book_list.getString("name"),
                             book_list.getInt("numPags"),
                             book_list.getInt("quantidade")));                              
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