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
 * @author Marcelo Gomes
 */
public class DAOProduto {
    

private Connection conection = null;
    
    public void conectar() {
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
    public void produto(Order order)throws SQLException{
        DAOProduto daoProduto=new DAOProduto();        
        //DAOProduto daoProduto=new DAOProduto();
        /*for(Book b:order.getBook()){  
            System.out.println(b.getBoID());
            System.out.println(b.getTitle());
            System.out.println(b.getAuthor());
            System.out.println(b.getMail());
            System.out.println(b.getNumPags());
            System.out.println(b.getQuantidade());*/
        
            String query = "INSERT INTO livraria.orderitens (quantidade) "
                + "VALUES (?)";
            PreparedStatement prepLivro = conection.prepareStatement(query,
                Statement.RETURN_GENERATED_KEYS);
           //Produto p=new Produto();
           //p=daoProduto.consultaPorID(b);             
           //faz a consulta no banco pela quantidade do produto
           //prepLivro.setInt(1, b.getBoID());
           //prepLivro.setInt(1, orderitens.getOiID());
           ResultSet produtoDoBanco = null;
           prepLivro.execute();
           produtoDoBanco.next();
           int qtdeDeLivroNoBanco=produtoDoBanco.getInt("quantidade");
           
            conection.commit();
            conection.close();
        }
    
        public void AddProduto(OrderItens orderitens) throws Exception{
           String query = "INSERT INTO livraria.orderitens (oiID,Fkid,quantidade) "
                + "VALUES (?,?,?)";
        PreparedStatement prep1 = conection.prepareStatement(query,
                Statement.RETURN_GENERATED_KEYS);
           //adiciona o produto vinculando no orderitens
           prep1.setInt(1, orderitens.getOiId());
           //prep1.setInt(2, b.getFkidProduto());
           prep1.setInt(2, orderitens.getFkidProduto());
           //prep1.setInt(3, b.getQuantidade());
           prep1.setInt(3, orderitens.getQuantidade());
           prep1.addBatch();
           prep1.executeBatch();
           
           conection.commit();
           conection.close();
        }

        public void atualiza(Book book)throws SQLException{
           //atualiza a tabela de produtos, decrementando o estoque.
           String query = "INSERT INTO livraria.orderitens (oiID,Fkid,quantidade) "
                + "VALUES (?,?,?)";
        PreparedStatement prep2 = conection.prepareStatement(query,
                Statement.RETURN_GENERATED_KEYS);
           prep2.setInt(1, book.getBoid());
           prep2.setInt(2, book.getFkpuID());
           //prep2.setInt(3, qtdDeLivroNoBanco-b.getQuantidade()));
           prep2.setInt(3, book.getQuantidade());
           prep2.addBatch();      
           prep2.executeBatch();
        
        conection.commit();
        conection.close();
    }
}