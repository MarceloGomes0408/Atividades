

import DAL.ConectionDB;
import DAL.DAOBook;
import DAL.DAOCliente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import Models.Order;
import Models.Book;
import Models.Cliente;
import Models.OrderItens;
import Models.Pedido;

public class DAOPedido {

	private static String tbName ="tb_pedido";
	private static String tbItens ="tb_itens_pedido";
	private static String SELECT = String.format("SELECT * FROM %1$s", DAOPedido.tbName);
	private static String SELECTBYPK =  String.format("SELECT * FROM %1$s WHERE id=?", DAOPedido.tbName);
	private static String INSERT =  String.format("INSERT INTO %1$s (id_cliente, numero, data_venda,forma_pagamento) VALUES(?,?,?,?)", DAOPedido.tbName); 
	private static String DELETE =  String.format("DELETE FROM %1$s where id = ?", DAOPedido.tbName);
	private static String UPDATE =  String.format("UPDATE %1$s SET id_cliente =?, numero?, data_venda = ?, forma_pagamento = ? where id = ?", DAOPedido.tbName);
	private static String SELECTITENS = String.format("SELECT * FROM %1$s where id_pedido = ?",DAOPedido.tbItens);
	private static String INSERTITEM = String.format("INSERT INTO %1$s (id_pedido, id_livro, perc_desconto,quantidade) VALUES(?,?,?,?)",DAOPedido.tbItens); 
	private static String DELETEITENS = String.format("DELETE FROM %1$s where id_pedido = ?",DAOPedido.tbItens);
	
	private DAOCliente clienteDAO = new DAOCliente();
	private DAOBook livroDAO = new DAOBook();
	
	public boolean inserir(Pedido pedido){

		Connection conn = null;
		PreparedStatement ps = null;
		PreparedStatement ps2 = null;
		int result1 = 0;
		int result2 = 0;
            return false;
                
            } 
}
        
