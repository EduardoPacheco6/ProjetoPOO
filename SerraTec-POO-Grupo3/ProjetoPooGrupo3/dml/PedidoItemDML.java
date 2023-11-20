package dml;

import classes.PedidoItem;
import conexao.Conexao;
import dao.PedidoItemDAO;

public class PedidoItemDML {
	public static void gravarPedidoItem(Conexao con, String schema, PedidoItem pdi) {
		PedidoItemDAO cdao = new PedidoItemDAO(con, schema);
		
		cdao.incluirPedidoItem(pdi);
	}
	
	public static void alterarPedidoItem(Conexao con, String schema, PedidoItem pdi) {
		PedidoItemDAO cdao = new PedidoItemDAO(con, schema);
		
		cdao.alterarPedidoItem(pdi);
	}
	
	public static void excluirPedidoItem(Conexao con, String schema, PedidoItem pdi) {
		PedidoItemDAO cdao = new PedidoItemDAO(con, schema);
		
		cdao.excluirPedidoItem(pdi);
	}
}