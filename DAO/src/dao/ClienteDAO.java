

package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import MODEL.Cliente;
import java.util.Date;
//import java.sql.Date;

/**
 *
 * @author Marcelo Gomes
 */

public class ClienteDAO {
	private static String tbName = "tb_cliente";
	private static String tbUsuario = "tb_usuario";
	private static String SELECT = String.format("SELECT * FROM %1$s, %2$s where %1$s.id_usuario = %2$s.id",ClienteDAO.tbName,ClienteDAO.tbUsuario);
	private static String SELECTBYPK = String.format("SELECT * FROM %1$s, %2$s WHERE %1$s.id=? AND %1$s.id_usuario = %2$s.id", ClienteDAO.tbName, ClienteDAO.tbUsuario);
	private static String INSERT = String.format("INSERT INTO %1$s (rg, cpf, estado_civil, logradouro, cep, endereco, numero, complemento, bairro, cidade,uf,sexo,data_nascimento) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)", ClienteDAO.tbName);
	private static String DELETE = String.format("DELETE FROM %1$s where id = ?", ClienteDAO.tbName);
	private static String UPDATE = String.format("UPDATE %1$s SET rg = ?, cpf=?, estado_civil =?, logradouro=?, cep = ?, endereco =?, numero=?, complemento =? , bairro=?,cidade = ?, uf=?,sexo=?,data_nascimento =? where id = ?", ClienteDAO.tbName);

	public boolean inserir(ClienteDAO cliente) throws Exception {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			ps = ConexaoDAO.getConnection().prepareStatement(INSERT);
			ps.setString(1, cliente.getRg());
			ps.setString(2, cliente.getCpf());
			ps.setString(3, cliente.getEstadoCivil());
			ps.setString(4, cliente.getLogradouro());
			ps.setString(5, cliente.getCep());
			ps.setString(6, cliente.getEndereco());
			ps.setString(7, cliente.getNumero());
			ps.setString(8, cliente.getComplemento());
			ps.setString(9, cliente.getBairro());
			ps.setString(10, cliente.getCidade());
			ps.setString(11, cliente.getUf());
			ps.setString(12, cliente.getSexo());
			//ps.setDate(13, new java.sql.Date(cliente.getDataNascimento().getTime()));
			return ps.executeUpdate() > 0;
		} catch (Exception e) {
			e.getStackTrace();
			return false;
		} finally {
			ConexaoDAO.fechaConexoes(conn, ps, null);
		}
	}

	public ArrayList<ClienteDAO> carregaLista() throws Exception {
		Connection conn = ConexaoDAO.getConnection();
		PreparedStatement ps = conn.prepareStatement(SELECT);
		ResultSet rs = ps.executeQuery();
		ArrayList<ClienteDAO> clientes = new ArrayList<>();
		while (rs.next()) {
			ClienteDAO cliente = new ClienteDAO();
			cliente.setId(rs.getInt("id"));
			cliente.setId_usuario(rs.getInt("id_usuario"));

                        cliente.setRg(rs.getString("rg"));
			cliente.setNome(rs.getString("nome"));
			cliente.setCpf(rs.getString("cpf"));
			cliente.setEstadoCivil(rs.getString("estado_civil"));
			cliente.setLogradouro(rs.getString("logradouro"));
			cliente.setCep(rs.getString("cep"));
			cliente.setEndereco(rs.getString("endereco"));

                        cliente.setNumero(rs.getString("numero"));
			cliente.setComplemento(rs.getString("complemento"));
			cliente.setBairro(rs.getString("bairro"));
			cliente.setCidade(rs.getString("cidade"));
			cliente.setUf(rs.getString("uf"));
			cliente.setSexo(rs.getString("sexo"));
			//cliente.setDataNascimento(new java.util.Date(rs.getDate("data_nascimento").getTime()));
			clientes.add(cliente);
			cliente = null;
		}
		ConexaoDAO.fechaConexoes(conn, ps, rs);
		return clientes;
	}

	public void deletar(int id) {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = ConexaoDAO.getConnection();
			ps = conn.prepareStatement(DELETE);
			ps.setInt(1, id);
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConexaoDAO.fechaConexoes(conn, ps, null);
		}
	}

	public ClienteDAO selectByPk(int id) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ClienteDAO cliente = null;
		try {
			conn = ConexaoDAO.getConnection();
			ps = conn.prepareStatement(SELECTBYPK);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			if (rs.next()) {
				cliente = new ClienteDAO();
				cliente.setId(rs.getInt("id"));
				cliente.setId_usuario(rs.getInt("id_usuario"));
				cliente.setRg(rs.getString("rg"));
				cliente.setNome(rs.getString("nome"));
				cliente.setCpf(rs.getString("cpf"));
				cliente.setEstadoCivil(rs.getString("estado_civil"));
				cliente.setLogradouro(rs.getString("logradouro"));
				cliente.setCep(rs.getString("cep"));
				cliente.setEndereco(rs.getString("endereco"));
				cliente.setNumero(rs.getString("numero"));
				cliente.setComplemento(rs.getString("complemento"));
				cliente.setBairro(rs.getString("bairro"));
				cliente.setCidade(rs.getString("cidade"));
				cliente.setUf(rs.getString("uf"));
				cliente.setSexo(rs.getString("sexo"));
				//cliente.setDataNascimento(new java.util.Date(rs.getDate("data_nascimento").getTime()));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConexaoDAO.fechaConexoes(conn, ps, rs);
		}
		return cliente;
	}

	public boolean atualizar(ClienteDAO cliente) {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			ps = ConexaoDAO.getConnection().prepareStatement(UPDATE);
			ps.setString(1, cliente.getRg());
			ps.setString(2, cliente.getCpf());
			ps.setString(3, cliente.getEstadoCivil());
			ps.setString(4, cliente.getLogradouro());
			ps.setString(5, cliente.getCep());
			ps.setString(6, cliente.getEndereco());
			ps.setString(7, cliente.getNumero());
			ps.setString(8, cliente.getComplemento());
			ps.setString(9, cliente.getBairro());
			ps.setString(10, cliente.getCidade());
			ps.setString(11, cliente.getUf());
			ps.setString(12, cliente.getSexo());
			//ps.setDate(13,(cliente.getDataNascimento().getTime()));
			ps.setLong(14, cliente.getId());
			return ps.executeUpdate() > 0;
		} catch (Exception e) {
			e.getStackTrace();
			return false;
		} finally {
			ConexaoDAO.fechaConexoes(conn, ps, null);
		}
	}

    private String getRg() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private String getCpf() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private String getEstadoCivil() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private String getLogradouro() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private String getCep() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private String getEndereco() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private String getNumero() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private String getComplemento() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private String getBairro() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private String getCidade() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private String getUf() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private String getSexo() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private Object getDataNascimento() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void setId(int aInt) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void setId_usuario(int aInt) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void setRg(String string) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void setNome(String string) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void setCpf(String string) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void setEstadoCivil(String string) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void setLogradouro(String string) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void setCep(String string) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void setEndereco(String string) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void setNumero(String string) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void setComplemento(String string) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void setBairro(String string) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void setCidade(String string) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void setUf(String string) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void setSexo(String string) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /*private void setDataNascimento(Date date) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }*/

    private long getId() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}