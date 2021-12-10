
package Models;

/**
 *
 * @author Marcelo Gomes
 */

public class Order {
    private int orId;
    private Book book;
    //private ShippingInfo shippingInfo;
    //private BillingInfo billingInfo;
    private User user;

    public int getOrId() {
        return orId;
    }

    public void setOrId(int orId) {
        this.orId = orId;
    }
    
   
    public Book getBook() {
        return book;
    }
   
    public void setBook(Book book) {
        this.book = book;
    }

    /*public ShippingInfo getShippingInfo() {
        return shippingInfo;
    }
    
    //public void setShippingInfo(ShippingInfo shippingInfo) {
        this.shippingInfo = shippingInfo;
    }

    //public BillingInfo getBillingInfo() {
        return billingInfo;
    }

    //public void setBillingInfo(BillingInfo billingInfo) {
        this.billingInfo = billingInfo;
    }*/

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public boolean isFullfilled()
    {
        if(
            book != null &&
           // shippingInfo != null &&
           // billingInfo != null &&
            user != null
        )
        {
            return true;
        }
        return false;
    }

}
    




