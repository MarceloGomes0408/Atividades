
package Models;

/**
 *
 * @author Marcelo Gomes
 */
public class Publisher {
    private String name;
    private int puId;
    private String endereço;

    public int getPuid() {
        return puId;
    }

    public void setPuid(int puid) {
        this.puId = puid;
    }

    

    public String getEndereço() {
        return endereço;
    }

    public void setEndereço(String endereço) {
        this.endereço = endereço;
    }
    
    
    
    public String getName() {
        return name;
    }

   
    public void setName(String name) {
        this.name = name;
    }

    
}
