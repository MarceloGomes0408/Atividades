
package Models;

/**
 *
 * @author Marcelo Gomes
 */

public class Author {
    private String name;
    private int auid;
    private Author author;

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public int getAuid() {
        return auid;
    }

    public void setAuid(int auid) {
        this.auid = auid;
    }
    
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        if(name != null){
            this.name = name;
        }else{
           this.name = "Autor desconhecido"; 
        }
    }    
}