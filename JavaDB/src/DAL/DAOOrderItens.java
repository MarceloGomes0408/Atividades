package DAL;

import Models.Author;
import Models.OrderItens;
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
public class DAOOrderItens {
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
    public void incluirItem(OrderItens orderitens) throws SQLException {        

        String query = "INSERT INTO livraria.orderitens(oiID, fkorID, fkboID) "
                + "VALUES (?, ?, ?)";
        PreparedStatement prep = conection.prepareStatement(query,
                Statement.RETURN_GENERATED_KEYS);
        
        prep.setInt(1, orderitens.getOiID());      
        prep.setInt(2, orderitens.getFkorID()); 
        prep.setInt(3, orderitens.getFkboID()); 
      
        prep.execute();
        
        /*ResultSet idItemCompra=prep.getGeneratedKeys();
        idItemCompra.next();
        System.out.println("Valor gerado: "+idItemCompra.getInt(1));*/
        
        conection.commit();
        conection.close();
    }

    //alterar - a model deve estar com o id preenchido
    public void alterarItem(OrderItens orderitens) throws SQLException {
        
        try {
            String query = "UPDATE livraria.orderItens "
                    + "SET oiID=? WHERE id=?";
            //PreparedStatement prep = cdb.getconection.prepareStatement(query);
            PreparedStatement prep = conection.prepareStatement(query);            
            
            prep.setInt(1, orderitens.getOiID()); 
                       
            prep.execute();

            conection.commit();
            conection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //excluir
    public void excluirItem(OrderItens orderitens) {
        
        try {
            String query = "DELETE FROM livraria.orderitens "
                    + "WHERE id=?";
            PreparedStatement prep = conection.prepareStatement(query);

            prep.setInt(1,orderitens.getOiID());
            prep.execute();

            conection.commit();
            conection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //consultar
    public int consultarPorItem(OrderItens orderitens) {
        
        int idTmp = -1;
        String query = "SELECT * from livraria.orderitens "
                + "WHERE name = ?";
        try {
            PreparedStatement prep = conection.prepareStatement(query);
            prep.setInt(1, orderitens.getOiID());

            ResultSet list = prep.executeQuery();

            while (list.next()) {
                 
                idTmp = list.getInt("id");               
                orderitens.setOiID(idTmp);
                
                /*Author author = new Author();
                author.setName(list.getString("autor"));
                book.setAutor(author);
                Publisher publisher = new Publisher();
                publisher.setName(list.getString("publisher"));
                book.setPublisher(publisher);*/
                break;
            }            
            conection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        orderitens.setOiID(idTmp);
        return idTmp;
    }

    public List listarItem(String params) {
        
        List<Author> listaLivros = new ArrayList<>();
        String query = "SELECT * from livraria.orderitens " + params;

        try {
            PreparedStatement prep = conection.prepareStatement(query);
            ResultSet lista = prep.executeQuery();

            while (lista.next()) {
                
                OrderItens orderitens = new OrderItens();
                orderitens.setOiID(lista.getInt("oiID"));
                
               
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
