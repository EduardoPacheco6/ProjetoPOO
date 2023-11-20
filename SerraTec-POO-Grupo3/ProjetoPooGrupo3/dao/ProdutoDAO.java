package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import classes.Produto;
import conexao.Conexao;

public class ProdutoDAO {
	private Conexao conexao;
	private String schema;
	
	PreparedStatement pInclusao;
	PreparedStatement pAlteracao;
	PreparedStatement pExclusao;
	
	public ProdutoDAO(Conexao conexao, String schema) {
		this.conexao = conexao;
		this.schema = schema;
		prepararSqlInclusao();
		prepararSqlAlteracao();
		prepararSqlExclusao();
	}

	private void prepararSqlInclusao() {
		String sql = "INSERT INTO "+ this.schema + ".produto";	
		sql += " (nome_prod, descricao)";
		sql += " VALUES ";
		sql += " (?, ?)";
		
		try {
			this.pInclusao = conexao.getC().prepareStatement(sql);
		} catch (Exception e) {
			System.err.println(e);
			e.printStackTrace();
		}
		
	}
	
	public int incluirProduto(Produto produto) {
		try {		
							
			pInclusao.setString(1, produto.getNome_prod());
			pInclusao.setString(2, produto.getDescricao());
			
			return pInclusao.executeUpdate();
		} catch (Exception e) {
			if (e.getLocalizedMessage().contains("is null")) {
				System.err.println("\nProduto não incluído.\nVerifique se foi chamado o conect:\n" + e);				
			} else {				
				System.err.println(e);
				e.printStackTrace();
			}
			return 0;
		}
	}
	
	private void prepararSqlAlteracao() {
		String sql = "UPDATE "+ this.schema + ".produto";	
		sql += " set nome_prod = ?,";
		sql += " descricao = ?";
		sql += " where idproduto = ?";
		
		try {
			this.pAlteracao =  conexao.getC().prepareStatement(sql);
		} catch (Exception e) {
			System.err.println(e);
			e.printStackTrace();
		}
	}
	
	public int alterarProduto(Produto produto) {
		try {
			pAlteracao.setString(1, produto.getNome_prod());
			pAlteracao.setString(2, produto.getDescricao());
			pAlteracao.setInt(3, produto.getIdproduto());
			
			return pAlteracao.executeUpdate();
			
		} catch (Exception e) {
			if (e.getLocalizedMessage().contains("is null")) {
				System.err.println("\nProduto não alterado.\nVerifique se foi chamado o conect:\n" + e);				
			} else {				
				System.err.println(e);
				e.printStackTrace();
			}
			return 0;
		}
	}
	
	private void prepararSqlExclusao() {
		String sql = "DELETE FROM "+ this.schema + ".produto";
		sql += " WHERE idproduto = ?";
		
		try {
			this.pExclusao = conexao.getC().prepareStatement(sql);
		} catch (Exception e) {
			System.err.println(e);
			e.printStackTrace();
		}
	}
	
	public int excluirProduto(Produto produto) {
		try {
			pExclusao.setInt(1, produto.getIdproduto());
			
			return pExclusao.executeUpdate();
		} catch  (Exception e) {
			if (e.getLocalizedMessage().contains("is null")) {
				System.err.println("\nProduto não excluído.\nVerifique se foi chamado o conect:\n" + e);				
			} else {				
				System.err.println(e);
				e.printStackTrace();
			}
			return 0;
		}
	}
	
	public ResultSet carregarProdutos() {
		ResultSet tabela;				
		String sql = "select * from " + this.schema + ".produto order by idproduto";
		
		tabela = conexao.query(sql);
			
		return tabela;
	}
}
