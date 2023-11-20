package dml;

import classes.Empresa;
import conexao.Conexao;
import dao.EmpresaDAO;

public class EmpresaDML {
	public static void gravarEmpresa(Conexao con, String schema, Empresa e) {
		EmpresaDAO cdao = new EmpresaDAO(con, schema);
		
		cdao.incluirEmpresa(e);
	}
	
	public static void alterarEmpresa(Conexao con, String schema, Empresa e) {
		EmpresaDAO cdao = new EmpresaDAO(con, schema);
		
		cdao.alterarEmpresa(e);
	}
	
	public static void excluirEmpresa(Conexao con, String schema, Empresa e) {
		EmpresaDAO cdao = new EmpresaDAO(con, schema);
		
		cdao.excluirEmpresa(e);
	}
}
