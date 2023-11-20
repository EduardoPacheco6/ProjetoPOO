package dml;

import classes.Cliente;
import classes.Produto;
import classes.Pedido;
import conexao.Conexao;
import dao.QueryDAO;

public class QueryDML {
	public static void querySelectCliente(Conexao con, String schema, Cliente c) {
		QueryDAO cdao = new QueryDAO(con, schema);
		cdao.selectCliente(c);
	}
	
	public static void querySelectProduto(Conexao con, String schema, Produto p) {
		QueryDAO cdao = new QueryDAO(con, schema);
		
		cdao.selectProduto(p);
	}
	
	public static void querySelectPedido(Conexao con, String schema, Pedido pe) {
		QueryDAO cdao = new QueryDAO(con, schema);
		
		cdao.selectPedido(pe);
	}
}
