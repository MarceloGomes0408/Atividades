package Models;

/**
 *
 * @author Marcelo Gomes
 */
public class Book {
    private int boID;
    private String title;
    private Author author;
    private String name;    
    private int fkpuID;
    private int fkauID;
    private int numPags;
    private int quantidade;   

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }      
     
    
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }
    
    public int getBoID() {
        return boID;
    }

    public void setBoID(int boID) {
        this.boID = boID;
    }

    public int getFkpuID() {
        return fkpuID;
    }

    public void setFkpuID(int fkpuID) {
        this.fkpuID = fkpuID;
    }

    public int getFkauID() {
        return fkauID;
    }

    public void setFkauID(int fkauID) {
        this.fkauID = fkauID;
    }

    public int getNumPags() {
        return numPags;
    }

    public void setNumPags(int numPags) {
        this.numPags = numPags;
    }

    
    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }         

   
}
