
package MODEL;

import dao.LivroDAO;

/**
 *
 * @author Marcelo Gomes
 */

public class Item {

	private long id;
	private Livro livro;
	private int quantidade;
	private Double percDesconto;
	
	public Livro getLivro() {
		return livro;
	}
	public void setLivro(Livro livro) {
		this.livro = livro;
	}
	public int getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
	public Double getPercDesconto() {
		return percDesconto;
	}
	public void setPercDesconto(Double percDesconto) {
		this.percDesconto = percDesconto;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}

    public void setLivro(LivroDAO livro) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
	
	
}