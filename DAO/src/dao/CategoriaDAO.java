
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import MODEL.CategoriaLivro;


/**
 *
 * @author Marcelo Gomes
 */

public class CategoriaDAO {
	private static String tbName = "tb_categoria_livro";
	private static String SELECT = String.format("SELECT * FROM %1$s",CategoriaDAO.tbName);
	private static String SELECTBYPK = String.format("SELECT * FROM %1$s WHERE id=?", CategoriaDAO.tbName);
	private static String INSERT = String.format("INSERT INTO %1$s (descricao) VALUES(?)", CategoriaDAO.tbName);
	private static String DELETE = String.format("DELETE FROM %1$s where id = ?", CategoriaDAO.tbName);
	private static String UPDATE = String.format("UPDATE %1$s SET descricao = ? where id = ?", CategoriaDAO.tbName);

	public boolean inserir(CategoriaLivro categoria) throws Exception {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			ps = ConexaoDAO.getConnection().prepareStatement(INSERT);
			ps.setString(1, categoria.getDescricao());
			return ps.executeUpdate() > 0;
		} catch (Exception e) {
			e.getStackTrace();
			return false;
		} finally {
			ConexaoDAO.fechaConexoes(conn, ps, null);
		}
	}

	public ArrayList<CategoriaLivro> carregaLista() throws Exception {
		Connection conn = ConexaoDAO.getConnection();
		PreparedStatement ps = conn.prepareStatement(SELECT);
		ResultSet rs = ps.executeQuery();
		ArrayList<CategoriaLivro> categorias = new ArrayList<>();
		while (rs.next()) {
			CategoriaLivro categoria = new CategoriaLivro();
			categoria.setId(rs.getLong("id"));
			categoria.setDescricao(rs.getString("descricao"));
			categorias.add(categoria);
			categoria = null;
		}
		ConexaoDAO.fechaConexoes(conn, ps, rs);
		return categorias;
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

	public CategoriaLivro selectByPk(int id) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		CategoriaLivro categoria = null;
		try {
			conn = ConexaoDAO.getConnection();
			ps = conn.prepareStatement(SELECTBYPK);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			if (rs.next()) {
				categoria = new CategoriaLivro();
				categoria.setId(rs.getLong("id"));
				categoria.setDescricao(rs.getString("descricao"));

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConexaoDAO.fechaConexoes(conn, ps, rs);
		}
		return categoria;
	}

	public boolean atualizar(CategoriaLivro categoria) {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			ps = ConexaoDAO.getConnection().prepareStatement(UPDATE);
			ps.setString(1, categoria.getDescricao());
			ps.setLong(2, categoria.getId());
			return ps.executeUpdate() > 0;
		} catch (Exception e) {
			e.getStackTrace();
			return false;
		} finally {
			ConexaoDAO.fechaConexoes(conn, ps, null);
		}
	}
}
