
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import MODEL.Usuario;


/**
 *
 * @author Marcelo Gomes
 */
public class UsuarioDAO {
	private static String tbName = "tb_usuario";
	private static String SELECT = String.format("SELECT * FROM %1$s",UsuarioDAO.tbName);
	private static String SELECTBYPK = String.format("SELECT * FROM %1$s WHERE id=?", UsuarioDAO.tbName);
	private static String INSERT = String.format("INSERT INTO %1$s (nome, username, senha) VALUES(?,?,?)", UsuarioDAO.tbName);
	private static String DELETE = String.format("DELETE FROM %1$s where id = ?", UsuarioDAO.tbName);
	private static String UPDATE = String.format("UPDATE %1$s SET nome = ?, username=?, senha =? where id = ?", UsuarioDAO.tbName);

	public boolean inserir(UsuarioDAO usuario) throws Exception {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			ps = ConexaoDAO.getConnection().prepareStatement(INSERT);
			ps.setString(1, usuario.getNome());

                        ps.setString(2, usuario.getUsername());
			ps.setString(3, usuario.getSenha());
			return ps.executeUpdate() > 0;
		} catch (Exception e) {
			e.getStackTrace();
			return false;
		} finally {
			ConexaoDAO.fechaConexoes(conn, ps, null);
		}
	}

	public ArrayList<UsuarioDAO> carregaLista() throws Exception {
		Connection conn = ConexaoDAO.getConnection();
		PreparedStatement ps = conn.prepareStatement(SELECT);
		ResultSet rs = ps.executeQuery();
		ArrayList<UsuarioDAO> usuarios = new ArrayList<>();
		while (rs.next()) {
			UsuarioDAO usuario = new UsuarioDAO();
			usuario.setId_usuario(rs.getLong("id"));
			usuario.setNome(rs.getString("nome"));
			usuario.setUsername(rs.getString("username"));
			usuarios.add(usuario);
			usuario = null;
		}
		ConexaoDAO.fechaConexoes(conn, ps, rs);
		return usuarios;
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

	public UsuarioDAO selectByPk(int id) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		UsuarioDAO usuario = null;
		try {
			conn = ConexaoDAO.getConnection();
			ps = conn.prepareStatement(SELECTBYPK);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			if (rs.next()) {
				usuario = new UsuarioDAO();
				usuario.setId_usuario(rs.getLong("id"));
				usuario.setNome(rs.getString("nome"));
				usuario.setUsername(rs.getString("username"));

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConexaoDAO.fechaConexoes(conn, ps, rs);
		}
		return usuario;
	}

	public boolean atualizar(UsuarioDAO usuario) {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			ps = ConexaoDAO.getConnection().prepareStatement(UPDATE);
			ps.setString(1, usuario.getNome());
			ps.setString(2, usuario.getUsername());
			ps.setString(3, usuario.getSenha());
			return ps.executeUpdate() > 0;
		} catch (Exception e) {
			e.getStackTrace();
			return false;
		} finally {
			ConexaoDAO.fechaConexoes(conn, ps, null);
		}
	}

    private String getNome() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private String getUsername() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private String getSenha() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void setId_usuario(long aLong) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void setNome(String string) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void setUsername(String string) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}