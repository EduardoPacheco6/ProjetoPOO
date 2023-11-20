package lista;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import classes.Pedido;
import conexao.Conexao;
import contantes.Util;
import dao.PedidoDAO;

public class ListaPedido {
	private Conexao con;
	private String schema;
	
	ArrayList<Pedido> pedidos = new ArrayList<>();
	
	
	public ListaPedido (Conexao con, String schema) {
		this.con = con;
		this.schema = schema;
		
		carregarListaPedidos();
	}
	
	public void imprimirPedidos() {
		ArrayList<String[]> tabela = new ArrayList<>();
		String aux;
		tabela.add(new String[] {"Id Pedido","Data do pedido","Id Cliente"});
		for (Pedido pedido : pedidos) {
			aux = Util.validaDataTransString(pedido.getData_ped());
			tabela.add(new String[] {String.valueOf(pedido.getIdpedido()),aux,String.valueOf(pedido.getIdcliente())});
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
	
	public void adicionarPedidoLista(Pedido p) {
		this.pedidos.add(p);
	}
	
	public Pedido localizarPedido(int idPedido) {
		Pedido localizado = null;
		
		for (Pedido p : pedidos) {
			if (p.getIdpedido()== idPedido) {
				localizado = p;
				break;
			}
		}
		return localizado;
	}
	
	private void carregarListaPedidos() {
		PedidoDAO pdao = new PedidoDAO(con, schema);
		
		ResultSet tabela = pdao.carregarPedidos();
		this.pedidos.clear();
		
		try {
			tabela.beforeFirst();
			
			while (tabela.next()) {							
				this.pedidos.add(dadosPedido(tabela));				
			}
			tabela.close();
		
		} catch (Exception e) {
			System.err.println(e);
			e.printStackTrace();
		}
	}
	
	private Pedido dadosPedido(ResultSet tabela) {
		Pedido p = new Pedido();
		
		try {
			p.setIdpedido(tabela.getInt("idpedido"));
			p.setIdcliente(tabela.getInt("idcliente"));
			p.setData_ped(tabela.getDate(("data_ped")).toLocalDate());
			return p;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
}