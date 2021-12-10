
package livraria;

/**
 *
 * @author Marcelo Gomes
 */
public abstract class BaseName {
    private String name;
    private Account account;

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }  
}


