package lista;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import classes.Empresa;
import conexao.Conexao;
import contantes.Util;
import dao.EmpresaDAO;

public class ListaEmpresa {
	private Conexao con;
	private String schema;
	
	ArrayList<Empresa> empresas = new ArrayList<>();
	
	public ListaEmpresa (Conexao con, String schema) {
		this.con = con;
		this.schema = schema;
		
		carregarListaEmpresa();
	}
	
	public void imprimirEmpresas() {
		ArrayList<String[]> tabela = new ArrayList<>();
		
		tabela.add(new String[] {"Id Empresa","Nome Fantasia","Razão Social"});
		for (Empresa empresa : empresas) {
			tabela.add(new String[] {String.valueOf(empresa.getIdempresa()),empresa.getNome_fantasia(),empresa.getRazao_social()});
		}
		for (int i = 0; i < tabela.size(); i++) {
		    String[] linha = tabela.get(i);
		    if(i == 1) {
		    	for (int k=0;k<linha.length;k++) {
		    		System.out.print(Util.LINHAD);
		    	}
		    	System.out.println();
	        }
		    for (int j = 0; j < linha.length; j++) {
		        System.out.format("%-30s | ", linha[j]);
		    }
		    System.out.println();
		}
	}
	
	public void adicionarEmpresaLista(Empresa emp) {
		this.empresas.add(emp);
	}
	
	public Empresa localizarEmpresa(int idEmpresa) {
		Empresa localizado = null;
		
		for (Empresa emp : empresas) {
			if (emp.getIdempresa() == idEmpresa) {
				localizado = emp;
				break;
			}
		}		
	
		return localizado;
	}
	
	private void carregarListaEmpresa() {
		EmpresaDAO edao = new EmpresaDAO(con, schema);
		
		ResultSet tabela = edao.carregarEmpresas();
		this.empresas.clear();
		
		try {
			tabela.beforeFirst();
			
			while (tabela.next()) {							
				this.empresas.add(dadosEmpresa(tabela));				
			}
			tabela.close();
		
		} catch (Exception e) {
			System.err.println(e);
			e.printStackTrace();
		}
	}
	
	private Empresa dadosEmpresa(ResultSet tabela) {
		Empresa emp = new Empresa();
		
		try {
			emp.setNome_fantasia(tabela.getString("nome_fantasia"));
			emp.setRazao_social(tabela.getString("razao_social"));
			emp.setIdempresa(tabela.getInt("idempresa"));
			return emp;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
}
