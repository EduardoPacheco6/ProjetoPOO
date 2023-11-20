package classes;

import conexao.Conexao;
import lista.ListaProdEmpr;
import contantes.Util;
import lista.ListaPedido;

	public class PedidoItem {
	private int idpedidoitem;
	private double qtd;
	private int idpedido;
	private int idprodempr;

	public static PedidoItem cadastrar(Conexao con, String schema) {

		PedidoItem pdi = new PedidoItem();
		
		ListaPedido listaPedidos = new ListaPedido(con,schema);
		ListaProdEmpr listaProdEmpr = new ListaProdEmpr(con,schema);
		
		listaPedidos.imprimirPedidos();
		pdi.setIdpedido(Util.validarInteiro("Insira o Id Pedido: "));
		System.out.println();
		
		listaProdEmpr.imprimirProdEmpr();
		pdi.setIdprodempr(Util.validarInteiro("Insira o Id Produto-Empresa: "));
		System.out.println();
		
		pdi.setQtd(Util.validarDouble("Insira a quantidade: "));

		return pdi;
	}
	
	public static PedidoItem alterar(Conexao con, String schema, PedidoItem pi) {
				
		ListaPedido listaPedidos = new ListaPedido(con,schema);
		ListaProdEmpr listaProdEmpr = new ListaProdEmpr(con,schema);
		
		listaPedidos.imprimirPedidos();
		pi.setIdpedido(Util.validarInteiro("Insira o Id Pedido: "));
		System.out.println();
		
		listaProdEmpr.imprimirProdEmpr();
		pi.setIdprodempr(Util.validarInteiro("Insira o Id Produto-Empresa: "));
		System.out.println();
		
		pi.setQtd(Util.validarDouble("Insira a quantidade: "));
		System.out.println();
		
		return pi;
	}
	
	public int getIdpedidoitem() {
		return idpedidoitem;
	}

	public void setIdpedidoitem(int idpedidoitem) {
		this.idpedidoitem = idpedidoitem;
	}

	public double getQtd() {
		return qtd;
	}

	public void setQtd(double qtd) {
		this.qtd = qtd;
	}

	public int getIdpedido() {
		return idpedido;
	}

	public void setIdpedido(int idpedido) {
		this.idpedido = idpedido;
	}

	public int getIdprodempr() {
		return idprodempr;
	}

	public void setIdprodempr(int idprodempr) {
		this.idprodempr = idprodempr;
	}

}