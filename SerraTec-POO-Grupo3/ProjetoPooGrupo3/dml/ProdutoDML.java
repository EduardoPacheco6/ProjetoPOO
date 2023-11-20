package dml;

import classes.Produto;
import conexao.Conexao;
import dao.ProdutoDAO;

public class ProdutoDML {
	
	public static void gravarProduto(Conexao con, String schema, Produto p) {
		ProdutoDAO cdao = new ProdutoDAO(con, schema);
		
		cdao.incluirProduto(p);
	}
	
	public static void alterarProduto(Conexao con, String schema, Produto p) {
		ProdutoDAO cdao = new ProdutoDAO(con, schema);
		
		cdao.alterarProduto(p);
	}
	
	public static void excluirProduto(Conexao con, String schema, Produto p) {
		ProdutoDAO cdao = new ProdutoDAO(con, schema);
		
		cdao.excluirProduto(p);
	}
}
