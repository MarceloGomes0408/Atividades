
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import MODEL.Cliente;
import MODEL.Pagamento;
import MODEL.Item;
import MODEL.Livro;
import MODEL.Pedido;
import java.util.Date;




/**
 *
 * @author Marcelo Gomes
 */

public class PedidoDAO {

	private static String tbName ="tb_pedido";
	private static String tbItens ="tb_itens_pedido";
	private static String SELECT = String.format("SELECT * FROM %1$s", PedidoDAO.tbName);
	private static String SELECTBYPK =  String.format("SELECT * FROM %1$s WHERE id=?", PedidoDAO.tbName);
	private static String INSERT =  String.format("INSERT INTO %1$s (id_cliente, numero, data_venda,forma_pagamento) VALUES(?,?,?,?)", PedidoDAO.tbName); 
	private static String DELETE =  String.format("DELETE FROM %1$s where id = ?", PedidoDAO.tbName);
	private static String UPDATE =  String.format("UPDATE %1$s SET id_cliente =?, numero?, data_venda = ?, forma_pagamento = ? where id = ?", PedidoDAO.tbName);
	private static String SELECTITENS = String.format("SELECT * FROM %1$s where id_pedido = ?",PedidoDAO.tbItens);
	private static String INSERTITEM = String.format("INSERT INTO %1$s (id_pedido, id_livro, perc_desconto,quantidade) VALUES(?,?,?,?)",PedidoDAO.tbItens); 
	private static String DELETEITENS = String.format("DELETE FROM %1$s where id_pedido = ?",PedidoDAO.tbItens);
	
	private ClienteDAO clienteDAO = new ClienteDAO();
	private LivroDAO livroDAO = new LivroDAO();
	
	public boolean inserir(PedidoDAO pedido){

		Connection conn = null;
		PreparedStatement ps = null;
		PreparedStatement ps2 = null;
		int result1 = 0;
		int result2 = 0;
		try {
			// insere o pedido
			ps = ConexaoDAO.getConnection().prepareStatement(INSERT);
			Cliente cliente = pedido.getCliente();
			if (cliente != null) {
				ps.setLong(1, cliente.getId());

                                ps.setLong(2, pedido.getNumero());
				//ps.setDate(3,new java.sql.Date(pedido.getDataVenda().getTime()));
				//ps.setLong(4, pedido.getPagamento().ordinal());
				result1= ps.executeUpdate();
				ps2 = ConexaoDAO.getConnection().prepareStatement(INSERTITEM);
				// insere os itens do pedido
				for (Item item : pedido.getItem()) {
					Livro livro = item.getLivro();
					if (livro != null) {
						ps2.setLong(1, pedido.getId());
						ps2.setLong(2, livro.getId());
						ps2.setDouble(3, item.getPercDesconto());
						ps2.setInt(4, item.getQuantidade());
						result2 += ps2.executeUpdate();
					}
				}
			}
			return result1 + result2 > 0;
		} catch (Exception e) {
			e.getStackTrace();
			return false;
		} finally {
			ConexaoDAO.fechaConexoes(conn, ps, null);
			ConexaoDAO.fechaConexoes(conn, ps2, null);
		}
	}
	
	public boolean atualizar(PedidoDAO pedido) {

		Connection conn = null;
		PreparedStatement ps = null;
		PreparedStatement ps2 = null;
		PreparedStatement ps3 = null;
		int result1 = 0;
		int result2 = 0;
		int result3 = 0;
		try {
			// atualiza o pedido
			ps = ConexaoDAO.getConnection().prepareStatement(UPDATE);
			Cliente cliente = pedido.getCliente();
			if (cliente != null) {
				ps.setLong(1, cliente.getId());
				ps.setLong(2, pedido.getNumero());
				//ps.setDate(3,new java.sql.Date(pedido.getDataVenda().getTime()));
				//ps.setLong(4, pedido.getPagamento().ordinal());
				ps.setLong(5, pedido.getId());
				result1= ps.executeUpdate();
			
				// deleta os itens do pedido para reinseri-los
				ps2 = ConexaoDAO.getConnection().prepareStatement(DELETEITENS);
				ps2.setLong(1, pedido.getId());
				result2 = ps2.executeUpdate();
				
				// insere os itens do pedido novamente
				ps3 = ConexaoDAO.getConnection().prepareStatement(INSERTITEM);
				for (Item item : pedido.getItem()) {
					Livro livro = item.getLivro();
					if (livro != null) {
						ps3.setLong(1, pedido.getId());
						ps3.setLong(2, livro.getId());
						ps3.setDouble(3, item.getPercDesconto());
						ps3.setInt(4, item.getQuantidade());
						result3 += ps3.executeUpdate();
					}
				}
			}
			return result1 + result2+ result3 > 0;
		} catch (Exception e) {
			e.getStackTrace();
			return false;
		} finally {
			ConexaoDAO.fechaConexoes(conn, ps, null);
			ConexaoDAO.fechaConexoes(conn, ps2, null);
			ConexaoDAO.fechaConexoes(conn, ps3, null);
		}
	}
	
	public void deletar(int id) {

		Connection conn = null;
		PreparedStatement ps1 = null;
		PreparedStatement ps2 = null;
		try {
			conn = ConexaoDAO.getConnection();
			
			// deletar os itens do pedido
			ps1 = ConexaoDAO.getConnection().prepareStatement(DELETEITENS);
			ps1.setLong(1,id);
			ps1.executeUpdate();
			
			// deletar o pedido
			ps2 = conn.prepareStatement(DELETE);
			ps2.setLong(1, id);
			ps2.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConexaoDAO.fechaConexoes(conn, ps1, null);
			ConexaoDAO.fechaConexoes(conn, ps2, null);
		}
	}	
	
	public ArrayList<PedidoDAO> carregaLista() throws Exception {

		Connection conn = ConexaoDAO.getConnection();
		PreparedStatement ps1 = conn.prepareStatement(SELECT);
		PreparedStatement ps2 = conn.prepareStatement(SELECTITENS);
		ResultSet rsItens = null;
		ResultSet rsPedido = ps1.executeQuery();	
		ArrayList<PedidoDAO> pedidos = new ArrayList<>();
		while (rsPedido.next()) {
			//obtém o cliente do pedido

                        ClienteDAO cliente = clienteDAO.selectByPk(rsPedido.getInt("id_cliente"));
			if (cliente != null) {
				// carrega dados do pedido
				PedidoDAO pedido = new PedidoDAO();
				pedido.setId(rsPedido.getLong("id"));

                                pedido.setCliente(cliente);
				pedido.setNumero(rsPedido.getLong("numero"));
				pedido.setDataVenda(new java.util.Date(rsPedido.getDate("data_venda").getTime()));
				pedido.setFormaPagamento(Pagamento.values()[rsPedido.getInt("forma_pagamento")]);
				
				// carrega itens do pedido
				ArrayList<Item> itens = new ArrayList<>();
				ps2.setLong(1, rsPedido.getLong("id"));
				rsItens = ps2.executeQuery();	
				while (rsItens.next()) {
					LivroDAO livro = livroDAO.selectByPk(rsItens.getInt("id_livro"));
					if (livro != null) {
						Item item = new Item();
						item.setId(rsItens.getLong("id"));
						item.setLivro(livro);
						item.setPercDesconto(rsItens.getDouble("percDesconto"));
						item.setQuantidade(rsItens.getInt("quantidade"));
						itens.add(item);
					}	
				}
				pedido.setItens(itens);
				pedidos.add(pedido);
				pedido = null;
			}
		}
		ConexaoDAO.fechaConexoes(conn, ps1, rsPedido);
		ConexaoDAO.fechaConexoes(conn, ps2, rsItens);
		return pedidos;
	}
	
	public PedidoDAO selectByPk(int id) {

		Connection conn = null;
		PreparedStatement ps1 = null;
		PreparedStatement ps2 = null;
		ResultSet rsPedido = null;
		ResultSet rsItens = null;
		PedidoDAO pedido = null;
		try {
			conn = ConexaoDAO.getConnection();
			ps1 = conn.prepareStatement(SELECTBYPK);
			ps2 = conn.prepareStatement(SELECTITENS);
			ps1.setInt(1, id);
			rsPedido = ps1.executeQuery();
			if (rsPedido.next()) {
				//obtém o cliente do pedido
				ClienteDAO cliente = clienteDAO.selectByPk(rsPedido.getInt("id_cliente"));
				if (cliente != null) {
					// carrega dados do pedido
					pedido = new PedidoDAO();
					pedido.setId(rsPedido.getLong("id"));
					pedido.setCliente(cliente);
					pedido.setNumero(rsPedido.getLong("numero"));
					pedido.setDataVenda(new java.util.Date(rsPedido.getDate("data_venda").getTime()));
					pedido.setPagamento(Pagamento.values()[rsPedido.getInt("pagamento")]);
					
					// carrega itens do pedido
					ArrayList<Item> itens = new ArrayList<>();
					ps2.setLong(1, rsPedido.getLong("id"));
					rsItens = ps2.executeQuery();	
					while (rsItens.next()) {
						LivroDAO livro = livroDAO.selectByPk(rsItens.getInt("id_livro"));
						if (livro != null) {
							Item item = new Item();

                                                        item.setId(rsItens.getLong("id"));
							item.setLivro(livro);
							item.setPercDesconto(rsItens.getDouble("percDesconto"));
							item.setQuantidade(rsItens.getInt("quantidade"));
							itens.add(item);
						}	
					}
					pedido.setItens(itens);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConexaoDAO.fechaConexoes(conn, ps1, rsPedido);
			ConexaoDAO.fechaConexoes(conn, ps2, rsItens);
		}
		return pedido;
	}

    private Cliente getCliente() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private long getNumero() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private Object getDataVenda() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private Object getPagamento() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private Iterable<Item> getItem() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private long getId() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void setId(long aLong) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void setCliente(ClienteDAO cliente) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void setNumero(long aLong) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void setDataVenda(Date date) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void setFormaPagamento(Pagamento value) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void setItens(ArrayList<Item> itens) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void setPagamento(Pagamento value) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}