package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import classes.PedidoItem;
import conexao.Conexao;

public class PedidoItemDAO {
	
	private Conexao conexao;
	private String schema;
	
	PreparedStatement pInclusao;
	PreparedStatement pAlteracao;
	PreparedStatement pExclusao;
	
	public PedidoItemDAO(Conexao conexao, String schema) {
		this.conexao = conexao;
		this.schema = schema;
		prepararSqlInclusao();
		prepararSqlAlteracao();
		prepararSqlExclusao();
	}

	private void prepararSqlInclusao() {
		String sql = "INSERT INTO "+ this.schema + ".pedidoitem";	
		sql += " (qtd,idpedido,idprodempr)";
		sql += " VALUES ";
		sql += " (?,?,?)";
		
		try {
			this.pInclusao = conexao.getC().prepareStatement(sql);
		} catch (Exception e) {
			System.err.println(e);
			e.printStackTrace();
		}
		
	}
	
	public int incluirPedidoItem(PedidoItem pedidoitem) {
		try {		
							
			pInclusao.setDouble(1, pedidoitem.getQtd());
			pInclusao.setDouble(2, pedidoitem.getIdpedido());
			pInclusao.setDouble(3, pedidoitem.getIdprodempr());			
			
			return pInclusao.executeUpdate();
		} catch (Exception e) {
			if (e.getLocalizedMessage().contains("is null")) {
				System.err.println("\nProduto não incluído no pedido.\nVerifique se foi chamado o conect:\n" + e);				
			} else {				
				System.err.println(e);
				e.printStackTrace();
			}
			return 0;
		}
	}
	
	private void prepararSqlAlteracao() {
		String sql = "UPDATE "+ this.schema + ".pedidoitem";	
		sql += " set qtd = ?";
		sql += " where idpedidoitem = ?";
		
		try {
			this.pAlteracao =  conexao.getC().prepareStatement(sql);
		} catch (Exception e) {
			System.err.println(e);
			e.printStackTrace();
		}
	}
	
	public int alterarPedidoItem(PedidoItem pedidoitem) {
		try {
			pAlteracao.setDouble(1, pedidoitem.getQtd());
			pAlteracao.setInt(2, pedidoitem.getIdpedidoitem());
			
			return pAlteracao.executeUpdate();
			
		} catch (Exception e) {
			if (e.getLocalizedMessage().contains("is null")) {
				System.err.println("\nProduto não alterado no pedido.\nVerifique se foi chamado o conect:\n" + e);				
			} else {				
				System.err.println(e);
				e.printStackTrace();
			}
			return 0;
		}
	}
	
	private void prepararSqlExclusao() {
		String sql = "DELETE FROM "+ this.schema + ".pedidoitem";
		sql += " WHERE idpedidoitem = ?";
		
		try {
			this.pExclusao = conexao.getC().prepareStatement(sql);
		} catch (Exception e) {
			System.err.println(e);
			e.printStackTrace();
		}
	}
	
	public int excluirPedidoItem(PedidoItem pedidoitem) {
		try {
			pExclusao.setInt(1, pedidoitem.getIdpedidoitem());
			
			return pExclusao.executeUpdate();
		} catch  (Exception e) {
			if (e.getLocalizedMessage().contains("is null")) {
				System.err.println("\nProduto não excluído do pedido.\nVerifique se foi chamado o conect:\n" + e);				
			} else {				
				System.err.println(e);
				e.printStackTrace();
			}
			return 0;
		}
	}
	
	public ResultSet carregarPedidoItem() {
		ResultSet tabela;				
		String sql = "select * from " + this.schema + ".pedidoitem order by idpedidoitem";
		
		tabela = conexao.query(sql);
			
		return tabela;
	}
}