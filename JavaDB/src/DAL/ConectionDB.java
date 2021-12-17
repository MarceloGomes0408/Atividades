
package DAL;

/**
 *
 * @author Marcelo Gomes
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 *
 * @author Marcelo Gomes
 */
public class ConectionDB {
    
    private Connection conection;
    
    public static void main(String[] args) throws SQLException {

    //public void conectar() {
        Properties prop = new Properties();
        prop.setProperty("user", "root");
        prop.setProperty("password", "17012001");
        try {
            Connection conection = DriverManager.getConnection("jdbc:mariadb://127.0.0.1:3306", prop);
            conection.setAutoCommit(false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public Connection getConnection(){
        return conection;
    }
    
    public void close(){
        try{
            conection.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}