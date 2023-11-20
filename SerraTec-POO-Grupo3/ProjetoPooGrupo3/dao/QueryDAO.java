package dao;


import java.sql.ResultSet;
import classes.Cliente;
import classes.Pedido;
import classes.Produto;
import conexao.Conexao;
import contantes.Util;

public class QueryDAO {
	private Conexao conexao;
	private String schema;
	
	public QueryDAO(Conexao conexao, String schema) { 
		this.conexao = conexao;
		this.schema = schema;
	}	
	
	public void selectCliente(Cliente cliente) {
		ResultSet tabela;
		String sql = "SELECT cli.nome, pe.idpedido, pe.data_ped, em.nome_fantasia, pr.nome_prod, peit.qtd, prem.vl_un, ";
		sql += "SUM(peit.qtd * prem.vl_un) AS subtotal,";
		sql += "(SELECT SUM(prem.vl_un * peit.qtd)";
		sql	+= " FROM sistema.pedidoitem peit";
		sql += " JOIN sistema.prodempr prem ON prem.idprodempr = peit.idprodempr";
		sql += " JOIN sistema.produto pr ON pr.idproduto = prem.idproduto";
		sql += " WHERE peit.idpedido = pe.idpedido";
		sql += " ) AS total_bruto_pedido";
		sql += " FROM "+ this.schema + ".cliente cli";
		sql += " LEFT JOIN "+ this.schema + ".pedido pe ON pe.idcliente = cli.idcliente";
		sql += " LEFT JOIN "+ this.schema + ".pedidoitem peit ON peit.idpedido = pe.idpedido";
		sql += " LEFT JOIN "+ this.schema + ".prodempr prem ON prem.idprodempr = peit.idprodempr";
		sql += " LEFT JOIN "+ this.schema + ".produto pr ON prem.idproduto = pr.idproduto";
		sql += " LEFT JOIN "+ this.schema + ".empresa em ON em.idempresa = prem.idempresa";
		sql += " WHERE cli.idcliente = "+cliente.getIdcliente();
		sql += " GROUP BY cli.nome, pe.idpedido, pe.data_ped, em.nome_fantasia, pr.nome_prod, peit.qtd, prem.vl_un";
		
		try {
			tabela=conexao.query(sql);
			tabela.beforeFirst();
			tabela.next();
			System.out.println(" Cliente: " + tabela.getString("nome") + "\n");
			System.out.format(" %-30s | %-30s | %-30s | %-30s | %-30s | %-30s | %-30s | %-30s | \n","Código do Pedido","Data do Pedido","Empresa","Nome do Produto","Quant.","Vl Unit.", "Vl Total Prod.", "Total Pedido");
			for(int i = 0; i < 8; i++) {
				System.out.print(Util.LINHAD);
			}
			tabela.beforeFirst();
			System.out.println();
			while(tabela.next()) {
				System.out.format(" %-30s | %-30s | %-30s | %-30s | %-30s | %-30s | %-30s | %-30s | ",tabela.getString("idpedido"),Util.validaDataTransString(tabela.getDate("data_ped").toLocalDate()),tabela.getString("nome_fantasia"),tabela.getString("nome_prod"),tabela.getString("qtd"),tabela.getString("vl_un"),tabela.getString("subtotal"),tabela.getString("total_bruto_pedido"));
				System.out.println();
			}
			
		} catch (Exception e) {
			if (e.getLocalizedMessage().contains("is null")) {
				System.err.println("\nCliente não encontrado.\nVerifique se foi chamado o conect:\n" + e);				
			} else {				
				System.err.println(e);
				e.printStackTrace();
			}
		}
	}
	
	
	public void selectProduto(Produto produto) {
		ResultSet tabela;
		String sql = "SELECT pr.idproduto, pr.nome_prod, p.idpedido, em.nome_fantasia FROM "+ this.schema + ".produto pr";
		sql += " LEFT JOIN "+ this.schema + ".prodempr pe ON pe.idproduto = pr.idproduto";
		sql += " LEFT JOIN "+ this.schema + ".empresa em ON em.idempresa = pe.idempresa";
		sql += " LEFT JOIN "+ this.schema + ".pedidoitem pi ON pi.idprodempr = pe.idprodempr";
		sql += " LEFT JOIN "+ this.schema + ".pedido p ON p.idpedido = pi.idpedido";
		sql += " WHERE pr.idproduto = " + produto.getIdproduto();
		
		try {
			tabela=conexao.query(sql);
			tabela.beforeFirst();
			tabela.next();
			System.out.format(" Código do Produto: %s | Produto: %s |\n",tabela.getString("idproduto"),tabela.getString("nome_prod"));
			System.out.format(" %-30s | %-30s | \n","Código do Pedido","Empresa");
			for(int i = 0; i < 2; i++) {
				System.out.print(Util.LINHAD);
			}
			tabela.beforeFirst();
			System.out.println();
			while(tabela.next()) {
				System.out.format(" %-30s | %-30s | ",tabela.getString("idpedido"),tabela.getString("nome_fantasia"));
				System.out.println();
			}
			
		} catch (Exception e) {
			if (e.getLocalizedMessage().contains("is null")) {
				System.err.println("\nProduto não encontrado.\nVerifique se foi chamado o conect:\n" + e);				
			} else {				
				System.err.println(e);
				e.printStackTrace();
			}
		}
	}
	
	
	
	public void selectPedido(Pedido pedido) {
		ResultSet tabela;
		String sql = "SELECT cli.nome, pe.idpedido, pe.data_ped, em.nome_fantasia, pr.nome_prod, peit.qtd, prem.vl_un, pr.idproduto, ";
		sql += " SUM(peit.qtd * prem.vl_un) AS subtotal,";
		sql += " (SELECT SUM(prem.vl_un * peit.qtd)";
		sql	+= " FROM sistema.pedidoitem peit";
		sql += " JOIN sistema.prodempr prem ON prem.idprodempr = peit.idprodempr";
		sql += " JOIN sistema.produto pr ON pr.idproduto = prem.idproduto";
		sql += " WHERE peit.idpedido = pe.idpedido";
		sql += " ) AS total_bruto_pedido";
		sql += " FROM "+ this.schema + ".pedido pe";
		sql += " LEFT JOIN "+ this.schema + ".cliente cli ON cli.idcliente = pe.idcliente";
		sql += " LEFT JOIN "+ this.schema + ".pedidoitem peit ON peit.idpedido = pe.idpedido";
		sql += " LEFT JOIN "+ this.schema + ".prodempr prem ON prem.idprodempr = peit.idprodempr";
		sql += " LEFT JOIN "+ this.schema + ".produto pr ON pr.idproduto = prem.idproduto";
		sql += " LEFT JOIN "+ this.schema + ".empresa em ON em.idempresa = prem.idempresa";
		sql += " WHERE pe.idpedido = " + pedido.getIdpedido();
		sql += " GROUP BY cli.nome, pe.idpedido, pe.data_ped, em.nome_fantasia, pr.nome_prod, peit.qtd, prem.vl_un, pr.idproduto";
		
		try {
			tabela=conexao.query(sql);
			tabela.beforeFirst();
			tabela.next();
			System.out.format("Cliente : %s | Código do Pedido: %s | Data do Pedido %s | Valor Total do Pedido %s | \n", tabela.getString("nome"), tabela.getString("idpedido"), Util.validaDataTransString(tabela.getDate("data_ped").toLocalDate()), tabela.getString("total_bruto_pedido"));
			System.out.format("%-30s | %-30s | %-30s | %-30s | %-30s | %-30s | \n","Código","Nome do Produto","Quant.","Vl Unit.","Vl Total","Empresa");
			for(int i = 0; i < 5; i++) {
				System.out.print(Util.LINHAD);
			}
			tabela.beforeFirst();
			System.out.println();
			while(tabela.next()) {
				System.out.format("%-30s | %-30s | %-30s | %-30s | %-30s | %-30s | ",tabela.getString("idproduto"),tabela.getString("nome_prod"),tabela.getString("qtd"),tabela.getString("vl_un"),tabela.getString("subtotal"),tabela.getString("nome_fantasia"));
				System.out.println();
			}
		} catch (Exception e) {
			if (e.getLocalizedMessage().contains("is null")) {
				System.err.println("\nPedido não encontrado.\nVerifique se foi chamado o conect:\n" + e);				
			} else {				
				System.err.println(e);
				e.printStackTrace();
			}
		}
	}
}