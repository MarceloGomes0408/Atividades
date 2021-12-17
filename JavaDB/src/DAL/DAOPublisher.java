

package DAL;

import Models.Author;
import Models.Book;
import Models.Publisher;
//import static com.sun.org.glassfish.external.amx.AMXUtil.prop;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 *
 * @author Marcelo Gomes
 */
public class DAOPublisher {   
    
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

    //incluir -o objeto book nÃ£o tem id.

    public void incluir(Publisher publisher) throws SQLException {             

        String query = "INSERT INTO livraria.publisher (puId, name, endereco) "
                + "VALUES (?,?,?)";
        PreparedStatement prep = conection.prepareStatement(
                query,
                Statement.RETURN_GENERATED_KEYS);
        prep.setInt(1, publisher.getPuID());
        prep.setString(2, publisher.getName());
        prep.setString(3, publisher.getEndereco());
        prep.execute();
        
        //pega a ultima id gerada
        ResultSet idUltimaCompra=prep.getGeneratedKeys();
        idUltimaCompra.next();
        System.out.println("Valor gerado:"+idUltimaCompra.getInt(1));        
        
        //query="SELECT LAST_INSERT_ID()";
        conection.commit();
        conection.close();
    }

    //alterar - a model deve estar com o id preenchido
    public void alterar(Publisher publisher) throws SQLException {       
        
        try {
            String query = "UPDATE livaria.publisher "
                    + "SET name=?, endereco=? WHERE id=?";
            PreparedStatement prep = conection.prepareStatement(query);

            //prep.setInt(4, publisher.getPuID());            
            prep.setString(2, publisher.getName());            
            prep.setString(3, publisher.getEndereco());
            prep.execute();

            conection.commit();
            conection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //excluir --o objeto book precisa do id....
    public void excluir(Publisher publisher) {
        //conectar();
        try {
            String query = "DELETE FROM livraria.publisher "
                    + "WHERE id=?";
            PreparedStatement prep = conection.prepareStatement(query);

            prep.setInt(1, publisher.getPuID());
            prep.execute();

            conection.commit();
            conection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //consultar
    public int consultarPublicao(Publisher publisher) {
        //conectar();
        
        int idTmp = -1;
        //String tmpPublica="";        
        
        String query = "SELECT * from livraria.publisher "
                + "WHERE name = ?";
        try {
            PreparedStatement prep = conection.prepareStatement(query);
            prep.setString(1, publisher.getName());

            ResultSet list = prep.executeQuery();

            
            while (list.next()) {
                idTmp = list.getInt("id");
                String tmpName=list.getString("name");
                publisher.setPuID(idTmp);
                
                break;
            }
            conection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //publisher.setPuID(idTmp);
        //publisher.setTitle(tmpTitle);
        //... 
        //preencher o objeto inteiro.
        
        return idTmp;
    }

    public List listar(String params) {
        //conectar();
        List<Publisher> listaLivros = new ArrayList<>();
        String query = "SELECT * from livraria.publisher " + params;

        try {
            PreparedStatement prep = conection.prepareStatement(query);
            ResultSet lista = prep.executeQuery();

            while (lista.next()) {
                Publisher publisher = new Publisher();
                publisher.setPuID(lista.getInt("id"));
                publisher.setName(lista.getString("name"));
                publisher.setEndereco(lista.getString("endereco"));               
                
                //listaLivros.add(publisher);
            }
            conection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaLivros;
    }    
}

