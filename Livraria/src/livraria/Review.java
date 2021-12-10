
package livraria;

/**
 *
 * @author Marcelo Gomes
 */

public class Review {
    private String text;
    private Reviewer reviewer;
    

        public String getText() {
        return text;
    }

    
    public void setText(String text) {
        this.text = text;
    }

    
    public Reviewer getReviewer() {
        return reviewer;
    }

    
    public void setReviewer(Reviewer reviewer) {
        this.reviewer = reviewer;
    }

}
