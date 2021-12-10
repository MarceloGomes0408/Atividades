
package DAL;



import Models.Book;
import Models.Order;
import Models.OrderItens;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 *
 * @author Marcelo GOmes
 */
public class DAOOrder {

    private Connection conection = null;   
    
    private void conectar(){
        Properties prop = new Properties();
        prop.setProperty("user", "root");
        prop.setProperty("password", "123");
        try {
            conection = DriverManager.getConnection(
                    "jdbc:mariadb://127.0.0.1:3306", prop);
            conection.setAutoCommit(false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    //DAOOrder(Order order) throws SQLException{ 
    public void OrdemCompra(Order order)throws SQLException{
        conectar();
        //faz a insercao da ordem de compra 
         String query0 = "INSERT INTO livaria.order (fkiduser, nome) "
                + "VALUES (?,?)";
         PreparedStatement prep0 = conection.prepareStatement(
                            query0,Statement.RETURN_GENERATED_KEYS);    
         prep0.setInt(1, order.getOrId());
         prep0.setString(2, "venda grande");
         prep0.execute();
         
         //pega o id da ordem de compra
         ResultSet idOrdemCompra=prep0.getGeneratedKeys();
         idOrdemCompra.next();
         //int idOC = ultimaID.getInt("id"); 
         
         conection.commit();
            conection.close();
    }
        
        public void lista(OrderItens orderitens) throws SQLException{  
          conectar();
        //varre a lista de livros e prepara a insercao em order_itens
        String query1 = "INSERT INTO livaria.order_itens (fkidorder, fkidproduto, qtde) "
                + "VALUES (?,?,?)";
        PreparedStatement prep1 = conection.prepareStatement(
                             query1,Statement.RETURN_GENERATED_KEYS);
        prep1.setInt(1, orderitens.getFkorID());
        prep1.setInt(2, orderitens.getFkidProduto());
        prep1.setInt(3, orderitens.getQuantidade());
        prep1.execute();
        
        conection.commit();
        conection.close();
        }
        
        public void atualiza(Book book) throws SQLException, SQLException{
            conectar();
        //atualiza a lista de produtos        
        String query2 = "UPDATE livraria.livros " +
                         "SET qtde = ? WHERE id = ?";
               
        PreparedStatement prep2 = conection.prepareStatement(query2);      
        
        prep2.setInt(1, book.getQuantidade());
        prep2.execute();
        
        conection.commit();
            conection.close();
    }
        
        public void consulta(Book book) throws SQLException{
         conectar();
         try {
        //consulta sql do produto
        String queryLivro= "Select * from livraria.livros " +
                         "where id = ?";
               
        PreparedStatement prepLivro = conection.prepareStatement(queryLivro);
        prepLivro.setInt(1, book.getBoid());
        prepLivro.execute();

        conection.commit();
        conection.close();
        
        } catch (SQLException e) {
            e.printStackTrace();        
        }
    }
}        