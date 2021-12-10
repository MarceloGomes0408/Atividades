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
public class App {

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

           // Use Statement to Create livraria.livros table
           try (Statement stmt = conn.createStatement()) {
               stmt.execute(
                       "CREATE TABLE IF NOT EXISTS livraria.livros (" +
                          "id INT PRIMARY KEY AUTO_INCREMENT," +
                          "titulo VARCHAR(25)," +
                          "autor VARCHAR(25)," +
                          "email VARCHAR(100))" +
                       "ENGINE=InnoDB;");

               // Prepare INSERT Statement to Add Books
               try (PreparedStatement prep = conn.prepareStatement(
                           "INSERT INTO livraria.livros (titulo, autor, email) VALUES (?, ?, ?)",
                           Statement.RETURN_GENERATED_KEYS)) {

                   // Add book
                   prep.setString(1, "Cem anos de solidao");
                   prep.setString(2, "Gabriel Garcia Marques");
                   prep.setString(3, "editoraplaneta@gmail.com"); //injecao de sql
                   prep.addBatch();

                   // Add book
                   prep.setString(1, "Os trabalhadores do mar");
                   prep.setString(2, "Victor Hugo");
                   prep.setString(3, "teste@teste.com");
                   prep.addBatch();

                   // Add book
                   prep.setString(1, "Biblia");
                   prep.setString(2, "Varios");
                   prep.setString(3, "teste1@teste1.com");
                   prep.addBatch();

                   // Execute Prepared Statements in Batch
                   System.out.println("Batch Counts");
                   int[] updateCounts = prep.executeBatch();
                   for (int count : updateCounts) {

                       // Print Counts
                       System.out.println(count);
                   }
             }

             // Prepare UPDATE Statement
             try (PreparedStatement prep = conn.prepareStatement(
                         "UPDATE livraria.livros " +
                         "SET email = ? WHERE id = ?")) {

                 // Change Email Address
                 prep.setString(1, "editoraplanetaXX@gmail.com");

                 // Change ID
                 prep.setInt(2, 1);
                 prep.execute();
             }

             // Prepare DELETE Statement
             try (PreparedStatement prep = conn.prepareStatement(
                         "DELETE FROM livraria.livros " +
                         "WHERE id = ?")) {

                 // ID of Row to Remove
                 prep.setInt(1, 3);
                 prep.execute();
             }

             // Commit Changes
             conn.commit();

             // Execute a SELECT Statement
             ResultSet book_list = stmt.executeQuery(
                     "SELECT titulo, autor, email " +
                     "FROM livraria.livros");

             // Iterate over the Result-set

             System.out.println("livros:");
             while (book_list.next()) {

                 // Print Row
                 System.out.println(
                         String.format(
                             "- %s %s <%s>",
                             book_list.getString("titulo"),
                             book_list.getString("autor"),
                             book_list.getString("email")));
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


