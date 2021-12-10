package livraria;

/**
 *
 * @author Marcelo Gomes
 */
public class Book {
    private String title;
    private Author author;
    private Publisher publisher;

    
    public String getTitle() {
        return title;
    }

    
    public void setTitle(String title) {
        this.title = title;
    }


    
    public Author getAuthor() {
        return author;
    }

    
    public void setAuthor(Author author) {
        this.author = author;
    }

    
    public Publisher getPublisher() {
        return publisher;
    }

    
    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }

}
