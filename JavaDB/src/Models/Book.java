package Models;

/**
 *
 * @author Marcelo Gomes
 */
public class Book {
    
    private int boId;
    private String titulo;
    private String autor;
    private String email;
    private int quantidade;
    private String numPags;
    private int FkpuID; 
    
    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public int getFkpuID() {
        return FkpuID;
    }

    public void setFkpuID(int FkpuID) {
        this.FkpuID = FkpuID;
    }

    public int getBoid() {
        return boId;
    }

    public void setBoid(int boid) {
        this.boId = boid;
    }

    
    

    public String getNumPags() {
        return numPags;
    }

    public void setNumPags(String numPags) {
        this.numPags = numPags;
    }
    
    

    

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    
}


