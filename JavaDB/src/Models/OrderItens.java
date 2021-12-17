package Models;

/**
 *
 * @author Marcelo Gomes
 */
public class OrderItens {
    private int oiID;
    private int fkorID;
    private int fkboID;
    private int FkidProduto;
    private int quantidade;

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    
    
    public int getFkidProduto() {
        return FkidProduto;
    }

    public void setFkidProduto(int FkidProduto) {
        this.FkidProduto = FkidProduto;
    }    
    
    public int getOiID() {
        return oiID;
    }

    public void setOiID(int oiID) {
        this.oiID = oiID;
    }

    public int getFkorID() {
        return fkorID;
    }

    public void setFkorID(int fkorID) {
        this.fkorID = fkorID;
    }

    public int getFkboID() {
        return fkboID;
    }

    public void setFkboID(int fkboID) {
        this.fkboID = fkboID;
    }

    public Iterable<Book> getItem() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
