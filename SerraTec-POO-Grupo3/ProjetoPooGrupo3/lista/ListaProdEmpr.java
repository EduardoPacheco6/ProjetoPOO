package lista;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import classes.ProdEmpr;
import conexao.Conexao;
import contantes.Util;
import dao.ProdemprDAO;

public class ListaProdEmpr {
	private Conexao con;
	private String schema;
	
	ArrayList<ProdEmpr> prodEmprs = new ArrayList<>();
	
	public ListaProdEmpr (Conexao con, String schema) {
		this.con = con;
		this.schema = schema;
		
		carregarListaProdEmpr();
	}
	
	public void imprimirProdEmpr() {
		ArrayList<String[]> tabela = new ArrayList<>();
		
		tabela.add(new String[] {"Id Produto-Empresa","Valor unitário", "Id Produto", "Id Empresa"});
		for (ProdEmpr prodEmpr : prodEmprs) {
			tabela.add(new String[] {String.valueOf(prodEmpr.getIdprodempr()), String.valueOf(prodEmpr.getVl_un()), String.valueOf(prodEmpr.getIdproduto()), String.valueOf(prodEmpr.getIdempresa())});
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
		
	public void adicionarProdEmprLista(ProdEmpr pdi) {
		this.prodEmprs.add(pdi);
	}
	
	public ProdEmpr localizarProdEmpr(int idprodempr) {
		ProdEmpr localizado = null;
		
		for (ProdEmpr pdi : prodEmprs) {
			if (pdi.getIdprodempr() == idprodempr) {
				localizado = pdi;
				break;
			}
		}
		return localizado;
	}
	
	private void carregarListaProdEmpr() {
		ProdemprDAO cdao = new ProdemprDAO(con, schema);
		
		ResultSet tabela = cdao.carregarProdEmpr();
		this.prodEmprs.clear();
		
		try {
			tabela.beforeFirst();
			
			while (tabela.next()) {							
				this.prodEmprs.add(dadosProdEmprs(tabela));				
			}
			tabela.close();
		
		} catch (Exception e) {
			System.err.println(e);
			e.printStackTrace();
		}
	}
	
	private ProdEmpr dadosProdEmprs(ResultSet tabela) {
		ProdEmpr pdi = new ProdEmpr();
		
		try {
			pdi.setIdprodempr(tabela.getInt("idprodempr"));
			pdi.setVl_un(tabela.getDouble("vl_un"));
			pdi.setIdproduto(tabela.getInt("idproduto"));
			pdi.setIdempresa(tabela.getInt("idempresa"));
			return pdi;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
}
