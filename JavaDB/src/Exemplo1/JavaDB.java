package Exemplo1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;


/**
 *
 * @author Marcelo Gomes
 */
public class JavaDB {

    
    public static void main(String[] argv) {
        Properties connConfig = new Properties();
        connConfig.setProperty("user", "root");
        connConfig.setProperty("password", "123");
        try (Connection conn = DriverManager.getConnection("jdbc:mariadb://127.0.0.1:3306", connConfig)) {
            try (Statement stmt = conn.createStatement()) {
                try (ResultSet _list = stmt.executeQuery("SELECT id, name FROM sample.user")) {
                    while (_list.next()) {
                        System.out.println(String.format("%d %s",
                            _list.getInt("id"),
                            _list.getString("name")));
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}


