package Models;

/**
 *
 * @author Marcelo Gomes
 */
public class Publisher {
    private int puID;
    private String name;
    private String endereco;   
    private Publisher publisher;
    
    public int getPuID() {
        return puID;
    }

    public void setPuID(int puID) {
        this.puID = puID;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }    

    public Publisher getPublisher() {
        return publisher;
    }

    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }   
    
}
