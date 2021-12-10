package DAL;

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

/**
 *
 * @author felip
 */
public class DAOBook {

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

    //incluir
    public void incluir(Book book) throws SQLException {
        conectar();

        String query = "INSERT INTO livraria.livros (titulo, autor, email, endereço,numero de paginas, quantidade) "
                + "VALUES (?,?,?,?,?,?)";
        PreparedStatement prep = conection.prepareStatement(
                query,
                Statement.RETURN_GENERATED_KEYS);
        prep.setString(1, book.getTitulo());
        prep.setString(2, book.getAutor());
        prep.setString(3, book.getEmail());
        prep.setString(4, book.getNumPags());
        prep.setInt(5, book.getQuantidade());
        
        prep.execute();
        conection.commit();

        conection.close();
    }

    //alterar - a model deve estar com o id preenchido
    public void alterar(Book book) throws SQLException {
        conectar();
        try {
            String query = "UPDATE livraria.livros "
                    + "SET titulo=?, autor=?, email=? WHERE id=?";
            PreparedStatement prep = conection.prepareStatement(query);

            prep.setInt(4, book.getBoid());
            prep.setString(1, book.getTitulo());
            prep.setString(2, book.getAutor());
            prep.setString(3, book.getEmail());
            prep.setString(4, book.getNumPags());
            prep.setInt(5, book.getQuantidade());
            prep.execute();

            conection.commit();
            conection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //excluir
    public void excluir(Book book) {
        conectar();
        try {
            String query = "DELETE FROM livraria.livros "
                    + "WHERE id=?";
            PreparedStatement prep = conection.prepareStatement(query);

            prep.setInt(1, book.getBoid());
            prep.execute();

            conection.commit();
            conection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //consultar
    public int consultarPorTitulo(Book book) {
        conectar();
        int idTmp = -1;
        String query = "SELECT * from livraria.livros "
                + "WHERE titulo = ?";
        try {
            PreparedStatement prep = conection.prepareStatement(query);
            prep.setString(1, book.getTitulo());

            ResultSet list = prep.executeQuery();

            while (list.next()) {
                idTmp = list.getInt("id");
                book.setBoid(list.getInt("id"));
                book.setTitulo(list.getString("titulo"));
                book.setTitulo(list.getString("autor"));
                book.setTitulo(list.getString("publisher"));
                break;
            }
            conection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        book.setBoid(idTmp);
        return idTmp;
    }

    public List listar(String params) {
        conectar();
        List<Book> listaLivros = new ArrayList<>();
        String query = "SELECT * from livraria.livros " + params;

        try {
            PreparedStatement prep = conection.prepareStatement(query);
            ResultSet lista = prep.executeQuery();

            while (lista.next()) {
                Book book = new Book();
                book.setBoid(lista.getInt("id"));
                book.setTitulo(lista.getString("titulo"));
                book.setAutor(lista.getString("autor"));
                book.setEmail(lista.getString("NumPags"));
                book.setEmail(lista.getString("Quantidade"));
                
                //listaLivros.add(book);
            }
            conection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaLivros;
    }

}


