package livraria;

/**
 *
 * @author Marcelo Gomes
 */
public class Author {
    private String name;

   
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

