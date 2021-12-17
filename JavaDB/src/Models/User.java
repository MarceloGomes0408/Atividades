
package Models;

/**
 *
 * @author Marcelo Gomes
 */

public class User {
    private int usId;
    private String name;
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    

    public int getUsId() {
        return usId;
    }

    public void setUsId(int usId) {
        this.usId = usId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
 

}
