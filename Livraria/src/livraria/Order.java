package livraria;

/**
 *
 * @author Marcelo Gomes
 */

    public class Order {
    private Book book;
    private ShippingInfo shippingInfo;
    private BillingInfo billingInfo;
    private User user;
   
    public Book getBook() {
        return book;
    }
   
    public void setBook(Book book) {
        this.book = book;
    }

    public ShippingInfo getShippingInfo() {
        return shippingInfo;
    }
    
    public void setShippingInfo(ShippingInfo shippingInfo) {
        this.shippingInfo = shippingInfo;
    }

    public BillingInfo getBillingInfo() {
        return billingInfo;
    }

    public void setBillingInfo(BillingInfo billingInfo) {
        this.billingInfo = billingInfo;
    }

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
            shippingInfo != null &&
            billingInfo != null &&
            user != null
        )
        {
            return true;
        }
        return false;
    }

}
    



