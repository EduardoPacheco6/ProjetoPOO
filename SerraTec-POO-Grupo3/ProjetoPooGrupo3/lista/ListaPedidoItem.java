package lista;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import classes.PedidoItem;
import conexao.Conexao;
import contantes.Util;
import dao.PedidoItemDAO;

public class ListaPedidoItem {
	private Conexao con;
	private String schema;
	
	ArrayList<PedidoItem> pedidoItens = new ArrayList<>();
	
	public ListaPedidoItem (Conexao con, String schema) {
		this.con = con;
		this.schema = schema;
		
		carregarListaPedidoItem();
	}
	
	public void imprimirPedidoItens() {
		ArrayList<String[]> tabela = new ArrayList<>();
		
		tabela.add(new String[] {"Id Pedido Item","Quantidade","Id Pedido", "Id Produto-Empresa"});
		for (PedidoItem pedidoItem : pedidoItens) {
			tabela.add(new String[] {String.valueOf(pedidoItem.getIdpedidoitem()), String.valueOf(pedidoItem.getQtd()), String.valueOf(pedidoItem.getIdpedido()), String.valueOf(pedidoItem.getIdprodempr())});
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
		
	public void adicionarPedidoItemLista(PedidoItem pdi) {
		this.pedidoItens.add(pdi);
	}
	
	public PedidoItem localizarPedidoItem(int Idpedidoitem) {
		PedidoItem localizado = null;
		
		for (PedidoItem pdi : pedidoItens) {
			if (pdi.getIdpedidoitem() == Idpedidoitem) {
				localizado = pdi;
				break;
			}
		}
		return localizado;
	}
	
	private void carregarListaPedidoItem() {
		PedidoItemDAO cdao = new PedidoItemDAO(con, schema);
		
		ResultSet tabela = cdao.carregarPedidoItem();
		this.pedidoItens.clear();
		
		try {
			tabela.beforeFirst();
			
			while (tabela.next()) {							
				this.pedidoItens.add(dadosPedidoItem(tabela));				
			}
			tabela.close();
		
		} catch (Exception e) {
			System.err.println(e);
			e.printStackTrace();
		}
	}
	
	private PedidoItem dadosPedidoItem(ResultSet tabela) {
		PedidoItem pdi = new PedidoItem();
		
		try {
			pdi.setQtd(tabela.getDouble("qtd"));
			pdi.setIdpedidoitem(tabela.getInt("idpedidoitem"));
			pdi.setIdprodempr(tabela.getInt("idprodempr"));
			pdi.setIdpedido(tabela.getInt("idpedido"));
			
			return pdi;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
}
