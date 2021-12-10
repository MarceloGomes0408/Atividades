package livraria;

/**
 *
 * @author Marcelo Gomes
 */

public class Account {
    private String emailAddress;
    private long id;
    private String password;

    private long idTemp;
    private String passwordTemp;
           
    public boolean validateLogin(long ID, String password){
       idTemp=ID;
       passwordTemp=password;
       if(this.password.equals(password)&& this.id==ID)return true;
       return false;
       //return (validadeLogin() && verifyPassword());
    }    
        
    //metodos acessores
    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }   
}
