
package MODEL;

import java.util.Date;
import java.util.List;

/**
 *
 * @author Marcelo Gomes
 */

public class Pedido {

	private long id, numero;
	private Cliente cliente;
	private Date dataVenda;
	private Pagamento formaPagamento;	
	private List<Pedido> itens;
	
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
	public Pagamento getFormaPagamento() {
		return formaPagamento;
	}
	public void setFormaPagamento(Pagamento formaPagamento) {
		this.formaPagamento = formaPagamento;
	}
	public List<Pedido> getItens() {
		return itens;
	}
	public void setItens(List<Pedido> itens) {
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