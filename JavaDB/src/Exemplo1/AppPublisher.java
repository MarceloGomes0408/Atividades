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
public class AppPublisher {
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
                       "CREATE TABLE IF NOT EXISTS livraria.publisher (" +
                          "id INT PRIMARY KEY AUTO_INCREMENT," +                          
                          "name VARCHAR(25)," + 
                          "endereco VARCHAR (25))"+
                        "ENGINE=InnoDB;");

               // Prepare INSERT Statement to Add Books
               try (PreparedStatement prep = conn.prepareStatement(
                           "INSERT INTO livraria.publisher (name, endereco)"
                                   + " VALUES (?, ?)",                            
                           Statement.RETURN_GENERATED_KEYS)) {

                   // Add autor
                   prep.setString(1, "Editora Atlas");
                   prep.setString(2, "Avenida Sem Rumo");
                   prep.execute();
                   
                   prep.setString(1, "Editora Amazon");
                   prep.setString(2, "Rua dos Estudantes Aflitos");
                   prep.execute();
                   
                   prep.setString(1, "Editora Moderna");
                   prep.setString(2, "Rua dos Sem Noção");
                   prep.execute();
                                
                   // Execute Prepared Statements in Batch
                   System.out.println("Editora: ");
                   int[] updateCounts = prep.executeBatch();
                   for (int count : updateCounts) {

                       // Print Counts
                       System.out.println(count);
                   }
             }

             // Prepare UPDATE Statement
             try (PreparedStatement prep = conn.prepareStatement(
                         "UPDATE livraria.publisher " +
                         "SET name = ? WHERE id = ?")) {

                 // Change name
                 prep.setString(1, "Editora Mundial");

                 // Change ID
                 prep.setInt(1,1);
                 prep.executeBatch();
             }

             // Prepare DELETE Statement
             try (PreparedStatement prep = conn.prepareStatement(
                         "DELETE FROM livraria.publisher " +
                         "WHERE id = ?")) {

                 // ID of Row to Remove
                 prep.setInt(1, 1);
                 prep.executeBatch();
             }

             // Commit Changes
             conn.commit();

             // Execute a SELECT Statement
             ResultSet publisher_list = stmtl.executeQuery(
                     "SELECT name, endereco " +
                     "FROM livraria.publisher");

             // Iterate over the Result-set

             //System.out.println("Editor:");
             while (publisher_list.next()) {

                 // Print Row
                 System.out.println(
                         String.format(
                             " %s <%s>",                             
                             publisher_list.getString("name"),
                             publisher_list.getString("endereco")));                              
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
