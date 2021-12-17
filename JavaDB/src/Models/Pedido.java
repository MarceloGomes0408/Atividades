
package Models;

/**
 *
 * @author Marcelo Gomes
 */


import java.util.Date;
import java.util.List;

public class Pedido {

	private long id, numero;
	private Cliente cliente;
	private Date dataVenda;
	private List<OrderItens> itens;
	
	public long getNumero() {
		return numero;
	}
	public void setNumero(long numero) {
		this.numero = numero;
	}
	public Date getDataVenda() {
		return dataVenda;
	}
	public void setDataVenda(Date dataVenda) {
		this.dataVenda = dataVenda;
	}
	
	public List<OrderItens> getItens() {
		return itens;
	}
	public void setItens(List<OrderItens> itens) {
		this.itens = itens;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

}