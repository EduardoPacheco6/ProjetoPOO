package dml;

import classes.ProdEmpr;
import conexao.Conexao;
import dao.ProdemprDAO;

public class ProdEmprDML {
	public static void gravarProdEmpr(Conexao con, String schema, ProdEmpr pdi) {
		ProdemprDAO cdao = new ProdemprDAO(con, schema);
		
		cdao.incluirProdEmpr(pdi);
	}
	
	public static void alterarProdEmpr(Conexao con, String schema, ProdEmpr pdi) {
		ProdemprDAO cdao = new ProdemprDAO(con, schema);
		
		cdao.alterarProdEmpr(pdi);
	}
	
	public static void excluirProdEmpr(Conexao con, String schema, ProdEmpr pdi) {
		ProdemprDAO cdao = new ProdemprDAO(con, schema);
		
		cdao.excluirProdEmpr(pdi);
	}
}
