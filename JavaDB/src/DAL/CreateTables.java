package DAL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 *
 * @author Marcelo Gomes
 */
public class CreateTables {

    public static void main(String[] args) throws SQLException {
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
                       
            // author
            try (Statement stmt = conn.createStatement()) {
                stmt.execute(
                        "CREATE TABLE IF NOT EXISTS livraria.author ("
                        + "auid INT PRIMARY KEY AUTO_INCREMENT,"
                        + "name VARCHAR(25))"
                        + "ENGINE=InnoDB;");
            } catch (Exception e) {
                e.printStackTrace();
            }

            // book
            try (Statement stmt = conn.createStatement()) {
                stmt.execute(
                        "CREATE TABLE IF NOT EXISTS livraria.book ("
                        + "boid INT PRIMARY KEY AUTO_INCREMENT,"
                        + "titulo VARCHAR(25),"
                        + "name VARCHAR(25),"                        
                        + "numPags INT(9),"
                        + "quantidade INT(9),"
                        + "fkpuid INT NOT NULL,"
                        + "fkauid INT NOT NULL,"
                        + "CONSTRAINT FK_puid FOREIGN KEY (fkpuid)"
                        + " REFERENCES livraria.publisher(puid),"
                        + "CONSTRAINT FK_auid FOREIGN KEY (fkauid)"
                        + " REFERENCES livraria.author(auid))"
                        + "ENGINE=InnoDB;");
            } catch (Exception e) {
                e.printStackTrace();
            }
            
            // order
            try (Statement stmt = conn.createStatement()) {
                stmt.execute(
                        "CREATE TABLE IF NOT EXISTS livraria.order ("
                        + "orid INT PRIMARY KEY AUTO_INCREMENT,"
                        + "date VARCHAR(8),"
                        + "numberofbooks INT(6) NOT NULL,"
                        + "fkusid INT NOT NULL,"
                        + "CONSTRAINT FK_pusid FOREIGN KEY (fkusid)"
                        + " REFERENCES livraria.user(usid))"
                        + "ENGINE=InnoDB;");
            } catch (Exception e) {
                e.printStackTrace();
            }

            // order itens
            try (Statement stmt = conn.createStatement()) {
                stmt.execute(
                        "CREATE TABLE IF NOT EXISTS livraria.orderitens ("
                        + "oiID INT PRIMARY KEY AUTO_INCREMENT,"
                        + "fkorid INT," //id da venda
                        + "fkboid INT," //id do livro
                        + "CONSTRAINT FK_orid FOREIGN KEY (fkorid)"
                        + " REFERENCES livraria.order(orid),"
                        + "CONSTRAINT FK_boid FOREIGN KEY (fkboid)"
                        + " REFERENCES livraria.books(boid)"
                        + ")"
                        + "ENGINE=InnoDB;");
            } catch (Exception e) {
                e.printStackTrace();
            } 
            
            // publisher
            try (Statement stmt = conn.createStatement()) {
                stmt.execute(
                        "CREATE TABLE IF NOT EXISTS livraria.publisher ("
                        + "puid INT PRIMARY KEY AUTO_INCREMENT,"
                        + "name VARCHAR(25),"                                               
                        + "endereco VARCHAR (100))"
                        + "ENGINE=InnoDB;");
            } catch (Exception e) {
                e.printStackTrace();
            }
            
            // user
            try (Statement stmt = conn.createStatement()) {
                stmt.execute(
                        "CREATE TABLE IF NOT EXISTS livraria.user ("
                        + "usid INT PRIMARY KEY AUTO_INCREMENT,"
                        + "name VARCHAR(25),"
                        + "type VARCHAR(25))"
                        + "ENGINE=InnoDB;");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
