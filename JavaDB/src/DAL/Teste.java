package DAL;

import Models.Book;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Marcelo Gomes
 */
public class Teste {
    public static void main(String[] args) throws SQLException{
        Book b1=new Book();
        b1.setTitulo("A volta ao mundo");
        b1.setAutor("julio Verne");
        b1.setEmail("editora@verne.br");
        try{
        DAOBook db=new DAOBook();
        db.incluir(b1);
        
        List<Book> books=db.listar("");
        
        
               
        for(Book b:books){
            System.out.println(b.getBoid());
            System.out.println(b.getTitulo());
        }
        
        Book b2=new Book();
        b2.setTitulo("A volta ao mundo");
        int id=db.consultarPorTitulo(b2);
        db.excluir(b2);

            
        
        for(Book b:books){
            System.out.println(b.getBoid());
            System.out.println(b.getTitulo());
        }
        
        
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    
}


