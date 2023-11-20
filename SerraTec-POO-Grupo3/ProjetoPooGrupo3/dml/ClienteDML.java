package dml;

import dao.ClienteDAO;
import conexao.Conexao;
import classes.Cliente;

public class ClienteDML {
	
	public static void gravarCliente(Conexao con, String schema, Cliente c) {
		ClienteDAO cdao = new ClienteDAO(con, schema);
		
		cdao.incluirCliente(c);
	}
	
	public static void alterarCliente(Conexao con, String schema, Cliente c) {
		ClienteDAO cdao = new ClienteDAO(con, schema);
		
		cdao.alterarCliente(c);
	}
	
	public static void excluirCliente(Conexao con, String schema, Cliente c) {
		ClienteDAO cdao = new ClienteDAO(con, schema);
		
		cdao.excluirCliente(c);
	}
}
