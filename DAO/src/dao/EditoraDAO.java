
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import MODEL.Editora;



/**
 *
 * @author Marcelo Gomes
 */

public class EditoraDAO{
	private static String tbName = "tb_editora";
	private static String SELECT = String.format("SELECT * FROM %1$s",EditoraDAO.tbName);
	private static String SELECTBYPK = String.format("SELECT * FROM %1$s WHERE id = ?",EditoraDAO.tbName);
	private static String INSERT = String.format("INSERT INTO %1$s VALUES (nome,cnpj,telefones,logradouro,cep,endereco,numero,complemento,bairro,cidade,uf) VALUES (?,?,?,?,?,?,?,?,?,?,?)",EditoraDAO.tbName);
	private static String DELETE = String.format("DELETE FROM %1$s WHERE id=?",EditoraDAO.tbName);
	private static String UPDATE = String.format("UPDATE %1$s SET nome=?,cnpj=?,telefones=?,logradouro=?,cep=?,endereco=?,numero=?,complemento=?,bairro=?,cidade=?,uf=? WHERE id=?",EditoraDAO.tbName);

	public boolean inserir(EditoraDAO editora) {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			ps = ConexaoDAO.getConnection().prepareStatement(INSERT);
			ps.setString(1, editora.getNome());
			ps.setString(2, editora.getCnpj());
			ps.setString(3, editora.getTelefones());
			ps.setString(4, editora.getLogradouro());

                        
                        ps.setString(5, editora.getCep());
			ps.setString(6, editora.getEndereco());
			ps.setString(7, editora.getNumero());
			ps.setString(8, editora.getComplemento());
			ps.setString(9, editora.getBairro());
			ps.setString(10, editora.getCidade());
			ps.setString(11, editora.getUf());
			return ps.executeUpdate() > 0;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			ConexaoDAO.fechaConexoes(conn, ps, null);
		}
	}

	public ArrayList<EditoraDAO> carregaLista() {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			ps = ConexaoDAO.getConnection().prepareStatement(SELECT);
			ResultSet rs = ps.executeQuery();
			ArrayList<EditoraDAO> editoras = null;
			while (rs.next()) {
				if (editoras == null)
					editoras = new ArrayList<>();
				EditoraDAO editora = new EditoraDAO();
				editora.setId(rs.getInt("id"));
				editora.setNome(rs.getString("nome"));
				editora.setCnpj(rs.getString("cnpj"));
				editora.setTelefones(rs.getString("telefones"));
				editora.setLogradouro(rs.getString("logradouro"));
				editora.setCep(rs.getString("cep"));
				editora.setEndereco(rs.getString("endereco"));
				editora.setNumero(rs.getString("numero"));
				editora.setComplemento(rs.getString("complemento"));
				editora.setBairro(rs.getString("bairro"));
				editora.setCidade(rs.getString("cidade"));
				editora.setUf(rs.getString("uf"));
				editoras.add(editora);

                        }

			return editoras;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			ConexaoDAO.fechaConexoes(conn, ps, null);
		}
	}

	public void deletar(int id) {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			ps = ConexaoDAO.getConnection().prepareStatement(DELETE);
			ps.setInt(1, id);

			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConexaoDAO.fechaConexoes(conn, ps, null);
		}
	}

	public EditoraDAO selectByPk(int id) {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			ps = ConexaoDAO.getConnection().prepareStatement(SELECTBYPK);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			EditoraDAO museu = null;
			while (rs.next()) {
				museu = new EditoraDAO();
				EditoraDAO editora = new EditoraDAO();
				editora.setId(rs.getInt("id"));
				editora.setNome(rs.getString("nome"));
				editora.setCnpj(rs.getString("cnpj"));
				editora.setTelefones(rs.getString("telefones"));
				editora.setLogradouro(rs.getString("logradouro"));
				editora.setCep(rs.getString("cep"));
				editora.setEndereco(rs.getString("endereco"));
				editora.setNumero(rs.getString("numero"));
				editora.setComplemento(rs.getString("complemento"));
				editora.setBairro(rs.getString("bairro"));
				editora.setCidade(rs.getString("cidade"));
				editora.setUf(rs.getString("uf"));
			}

			return museu;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			ConexaoDAO.fechaConexoes(conn, ps, null);
		}
	}

	public boolean atualizar(EditoraDAO editora) {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			ps = ConexaoDAO.getConnection().prepareStatement(UPDATE);
			ps.setString(1, editora.getNome());
			ps.setString(2, editora.getCnpj());
			ps.setString(3, editora.getTelefones());
			ps.setString(4, editora.getLogradouro());
			ps.setString(5, editora.getCep());
			ps.setString(6, editora.getEndereco());
			ps.setString(7, editora.getNumero());
			ps.setString(8, editora.getComplemento());
			ps.setString(9, editora.getBairro());
			ps.setString(10, editora.getCidade());
			ps.setString(11, editora.getUf());
			ps.setLong(12, editora.getId());
			return ps.executeUpdate() > 0;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			ConexaoDAO.fechaConexoes(conn, ps, null);
		}
	}

    private String getNome() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private String getCnpj() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private String getTelefones() {
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

    private void setId(int aInt) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void setNome(String string) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void setCnpj(String string) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void setTelefones(String string) {
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

    private long getId() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}