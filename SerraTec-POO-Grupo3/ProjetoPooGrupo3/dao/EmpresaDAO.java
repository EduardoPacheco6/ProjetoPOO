package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import classes.Empresa;
import conexao.Conexao;

public class EmpresaDAO {
	private Conexao conexao;
	private String schema;
	
	PreparedStatement pInclusao;
	PreparedStatement pAlteracao;
	PreparedStatement pExclusao;
	
	public EmpresaDAO(Conexao conexao, String schema) {
		this.conexao = conexao;
		this.schema = schema;
		prepararSqlInclusao();
		prepararSqlAlteracao();
		prepararSqlExclusao();
	}

	private void prepararSqlInclusao() {
		String sql = "INSERT INTO "+ this.schema + ".empresa";	
		sql += " (nome_fantasia, razao_social)";
		sql += " VALUES ";
		sql += " (?, ?)";
		
		try {
			this.pInclusao = conexao.getC().prepareStatement(sql);
		} catch (Exception e) {
			System.err.println(e);
			e.printStackTrace();
		}
		
	}
	
	public int incluirEmpresa(Empresa empresa) {
		try {		
							
			pInclusao.setString(1, empresa.getNome_fantasia());
			pInclusao.setString(2, empresa.getRazao_social());
			
			return pInclusao.executeUpdate();
		} catch (Exception e) {
			if (e.getLocalizedMessage().contains("is null")) {
				System.err.println("\nEmpresa não incluída.\nVerifique se foi chamado o conect:\n" + e);				
			} else {				
				System.err.println(e);
				e.printStackTrace();
			}
			return 0;
		}
	}
	
	private void prepararSqlAlteracao() {
		String sql = "UPDATE "+ this.schema + ".empresa";	
		sql += " set nome_fantasia = ?,";
		sql += " razao_social = ?";
		sql += " WHERE idempresa = ?";
		
		try {
			this.pAlteracao =  conexao.getC().prepareStatement(sql);
		} catch (Exception e) {
			System.err.println(e);
			e.printStackTrace();
		}
	}
	
	public int alterarEmpresa(Empresa empresa) {
		try {
			pAlteracao.setString(1, empresa.getNome_fantasia());
			pAlteracao.setString(2, empresa.getRazao_social());
			pAlteracao.setInt(3, empresa.getIdempresa());
			
			return pAlteracao.executeUpdate();
			
		} catch (Exception e) {
			if (e.getLocalizedMessage().contains("is null")) {
				System.err.println("\nEmpresa não alterada.\nVerifique se foi chamado o conect:\n" + e);				
			} else {				
				System.err.println(e);
				e.printStackTrace();
			}
			return 0;
		}
	}
	
	private void prepararSqlExclusao() {
		String sql = "DELETE FROM "+ this.schema + ".empresa";
		sql += " WHERE idempresa = ?";
		
		try {
			this.pExclusao = conexao.getC().prepareStatement(sql);
		} catch (Exception e) {
			System.err.println(e);
			e.printStackTrace();
		}
	}
	
	public int excluirEmpresa(Empresa empresa) {
		try {
			pExclusao.setInt(1, empresa.getIdempresa());
			
			return pExclusao.executeUpdate();
		} catch  (Exception e) {
			if (e.getLocalizedMessage().contains("is null")) {
				System.err.println("\nEmpresa não excluída.\nVerifique se foi chamado o conect:\n" + e);				
			} else {				
				System.err.println(e);
				e.printStackTrace();
			}
			return 0;
		}
	}
	
	public ResultSet carregarEmpresas() {
		ResultSet tabela;				
		String sql = "select * from " + this.schema + ".empresa order by idempresa";
		
		tabela = conexao.query(sql);
			
		return tabela;
	}
}
