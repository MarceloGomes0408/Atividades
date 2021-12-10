
package Models;

/**
 *
 * @author Marcelo Gomes
 */
public class OrderItens{
    
    private int oiId;
    private int fkorID;
    private int fkboID;
    private int FkidProduto;
    private int quantidade;

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

    public int getFkidProduto() {
        return FkidProduto;
    }

    public void setFkidProduto(int FkidProduto) {
        this.FkidProduto = FkidProduto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public int getOiId() {
        return oiId;
    }

    public void setOiId(int oiId) {
        this.oiId = oiId;
    }
    
 
}
