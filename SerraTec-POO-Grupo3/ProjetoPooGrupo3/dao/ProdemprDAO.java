package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import classes.ProdEmpr;
import conexao.Conexao;

public class ProdemprDAO {
	
	private Conexao conexao;
	private String schema;
	
	PreparedStatement pInclusao;
	PreparedStatement pAlteracao;
	PreparedStatement pExclusao;
	
	public ProdemprDAO(Conexao conexao, String schema) {
		this.conexao = conexao;
		this.schema = schema;
		prepararSqlInclusao();
		prepararSqlAlteracao();
		prepararSqlExclusao();
	}

	private void prepararSqlInclusao() {
		String sql = "INSERT INTO "+ this.schema + ".prodempr";	
		sql += " (vl_un,idproduto,idempresa)";
		sql += " VALUES ";
		sql += " (?,?,?)";
		
		try {
			this.pInclusao = conexao.getC().prepareStatement(sql);
		} catch (Exception e) {
			System.err.println(e);
			e.printStackTrace();
		}
		
	}
	
	public int incluirProdEmpr(ProdEmpr prodemrp) {
		try {		
							
			pInclusao.setDouble(1, prodemrp.getVl_un());
			pInclusao.setDouble(2, prodemrp.getIdproduto());
			pInclusao.setDouble(3, prodemrp.getIdempresa());
			
			
			return pInclusao.executeUpdate();
		} catch (Exception e) {
			if (e.getLocalizedMessage().contains("is null")) {
				System.err.println("\n Produto-Empresa não incluído.\nVerifique se foi chamado o conect:\n" + e);				
			} else {				
				System.err.println(e);
				e.printStackTrace();
			}
			return 0;
		}
	}
	
	private void prepararSqlAlteracao() {
		String sql = "UPDATE "+ this.schema + ".prodempr";	
		sql += " set vl_un = ?,";
		sql += " idproduto = ?,";
		sql += " idempresa = ?";
		sql += " where idprodempr = ?";
		
		try {
			this.pAlteracao =  conexao.getC().prepareStatement(sql);
		} catch (Exception e) {
			System.err.println(e);
			e.printStackTrace();
		}
	}
	
	public int alterarProdEmpr(ProdEmpr prodemrp) {
		try {
			pAlteracao.setDouble(1, prodemrp.getVl_un());
			pAlteracao.setDouble(2, prodemrp.getIdproduto());
			pAlteracao.setDouble(3, prodemrp.getIdempresa());
			pAlteracao.setDouble(4, prodemrp.getIdprodempr());
			
			return pAlteracao.executeUpdate();
			
		} catch (Exception e) {
			if (e.getLocalizedMessage().contains("is null")) {
				System.err.println("\nProduto-Empresa não alterado.\nVerifique se foi chamado o conect:\n" + e);				
			} else {				
				System.err.println(e);
				e.printStackTrace();
			}
			return 0;
		}
	}
	
	private void prepararSqlExclusao() {
		String sql = "DELETE FROM "+ this.schema + ".prodempr";
		sql += " WHERE idprodempr = ?";
		
		try {
			this.pExclusao = conexao.getC().prepareStatement(sql);
		} catch (Exception e) {
			System.err.println(e);
			e.printStackTrace();
		}
	}
	
	public int excluirProdEmpr(ProdEmpr prodemrp) {
		try {
			pExclusao.setInt(1, prodemrp.getIdprodempr());
			
			return pExclusao.executeUpdate();
		} catch  (Exception e) {
			if (e.getLocalizedMessage().contains("is null")) {
				System.err.println("\n Produto-Empresa não excluído.\nVerifique se foi chamado o conect:\n" + e);				
			} else {				
				System.err.println(e);
				e.printStackTrace();
			}
			return 0;
		}
	}
	
	public ResultSet carregarProdEmpr() {
		ResultSet tabela;				
		String sql = "select * from " + this.schema + ".prodempr order by idprodempr";
		
		tabela = conexao.query(sql);
			
		return tabela;
	}
	
}
