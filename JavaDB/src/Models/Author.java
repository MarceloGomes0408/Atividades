
package Models;

/**
 *
 * @author Marcelo Gomes
 */

public class Author {
    private String name;
    private int auid;

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