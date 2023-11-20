package classes;

import conexao.Conexao;
import contantes.Util;
import lista.ListaEmpresa;
import lista.ListaProduto;

public class ProdEmpr {
	private int idprodempr;
	private double vl_un;
	private int idproduto;
	private int idempresa;
	
	public static ProdEmpr cadastrar(Conexao con, String schema) {
		ProdEmpr pdi = new ProdEmpr();
		
		ListaProduto listaProdutos = new ListaProduto(con,schema);
		ListaEmpresa listaEmpresa = new ListaEmpresa(con,schema);
		
		listaEmpresa.imprimirEmpresas();
		pdi.setIdempresa(Util.validarInteiro("Insira o Id Empresa: "));
		System.out.println();
		
		listaProdutos.imprimirProdutos();
		pdi.setIdproduto(Util.validarInteiro("Insira o Id Produto: "));
		System.out.println();
		
		pdi.setVl_un(Util.validarDouble("Insira o Valor unitário do Produto: "));
		
		return pdi;
	}
	
	public static ProdEmpr alterar(Conexao con, String schema, ProdEmpr pi) {
		
		ListaProduto listaProdutos = new ListaProduto(con,schema);
		ListaEmpresa listaEmpresa = new ListaEmpresa(con,schema);
		
		listaEmpresa.imprimirEmpresas();
		pi.setIdempresa(Util.validarInteiro("Insira o Id Empresa: "));
		System.out.println();
		
		listaProdutos.imprimirProdutos();
		pi.setIdproduto(Util.validarInteiro("Insira o Id Produto: "));
		System.out.println();
		
		pi.setVl_un(Util.validarDouble("Insira o Valor unitário do Produto: "));
		
		return pi;
	}
	
	public int getIdprodempr() {
		return idprodempr;
	}
	public void setIdprodempr(int idprodempr) {
		this.idprodempr = idprodempr;
	}
	public double getVl_un() {
		return vl_un;
	}
	public void setVl_un(double vl_un) {
		this.vl_un = vl_un;
	}
	public int getIdproduto() {
		return idproduto;
	}
	public void setIdproduto(int idproduto) {
		this.idproduto = idproduto;
	}
	public int getIdempresa() {
		return idempresa;
	}
	public void setIdempresa(int idempresa) {
		this.idempresa = idempresa;
	}
	
}