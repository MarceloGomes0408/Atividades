
package DAL;

/**
 *
 * @author Marcelo Gomes
 */

import Models.Author;
import Models.Book;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;


public class DAOAuthor {
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

    //incluir
    public void incluir(Author author) throws SQLException {        

        String query = "INSERT INTO livraria.author(name) "
                + "VALUES (?)";
        PreparedStatement prep = conection.prepareStatement(query,
                Statement.RETURN_GENERATED_KEYS);
        
        
        prep.setString(1, author.getAuthor().getName());      
      
        prep.execute();
        
        ResultSet idOrdemCompra=prep.getGeneratedKeys();
        idOrdemCompra.next();
        System.out.println("Valor gerado: "+idOrdemCompra.getInt(1));
        
        conection.commit();
        conection.close();
    }

    //alterar - a model deve estar com o id preenchido
    public void alterar(Author author) throws SQLException {
        
        try {
            String query = "UPDATE livraria.author "
                    + "SET name=? WHERE id=?";
            //PreparedStatement prep = cdb.getconection.prepareStatement(query);
            PreparedStatement prep = conection.prepareStatement(query);
            
            prep.setString(1, author.getAuthor().getName());
            prep.setInt(2, author.getAuid());
                       
            prep.execute();

            conection.commit();
            conection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //excluir
    public void excluir(Author author) {
        
        try {
            String query = "DELETE FROM livraria.author "
                    + "WHERE id=?";
            PreparedStatement prep = conection.prepareStatement(query);

            prep.setInt(1, author.getAuid());
            prep.execute();

            conection.commit();
            conection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //consultar
    public int consultarPorName(Author author) {
        
        int idTmp = -1;
        String query = "SELECT * from livraria.author "
                + "WHERE name = ?";
        try {
            PreparedStatement prep = conection.prepareStatement(query);
            prep.setString(1, author.getName());

            ResultSet list = prep.executeQuery();

            while (list.next()) {
                 
                idTmp = list.getInt("id");
                String tmpName = list.getString("name");
                author.setAuid(idTmp);
                author.setName(tmpName);                
                author.setAuthor(author);
                
                
                break;
            }            
            conection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        author.setAuid(idTmp);
        return idTmp;
    }

    public List listar(String params) {
        
        List<Author> listaLivros = new ArrayList<>();
        String query = "SELECT * from livraria.author " + params;

        try {
            PreparedStatement prep = conection.prepareStatement(query);
            ResultSet lista = prep.executeQuery();

            while (lista.next()) {
                
                Author author = new Author();
                author.setAuid(lista.getInt("auid"));
                author.setName(lista.getString("name"));
               
                /*book.setAuthor(lista.getString("autor"));
                book.setEmail(lista.getString("email"));
                listaLivros.add(book);*/
            }            
            conection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaLivros;
    }       

}
