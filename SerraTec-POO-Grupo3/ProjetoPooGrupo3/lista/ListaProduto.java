package lista;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import classes.Produto;
import conexao.Conexao;
import contantes.Util;
import dao.ProdutoDAO;

public class ListaProduto {
	private Conexao con;
	private String schema;
	
	ArrayList<Produto> produtos = new ArrayList<>();
	
	public ListaProduto (Conexao con, String schema) {
		this.con = con;
		this.schema = schema;
		
		carregarListaProdutos();
	}
	
	public void imprimirProdutos() {
		ArrayList<String[]> tabela = new ArrayList<>();
		
		tabela.add(new String[] {"Id Produto","Nome Produto","Descrição"});
		for (Produto produto : produtos) {
			tabela.add(new String[] {String.valueOf(produto.getIdproduto()),produto.getNome_prod(),produto.getDescricao()});
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
	
	public void adicionarProdutoLista(Produto p) {
		this.produtos.add(p);
	}
	
	public Produto localizarProduto(int idProduto) {
		Produto localizado = null;
		
		for (Produto p : produtos) {
			if (p.getIdproduto() == idProduto) {
				localizado = p;
				break;
			}
		}
		return localizado;
	}
	
	private void carregarListaProdutos() {
		ProdutoDAO cdao = new ProdutoDAO(con, schema);
		
		ResultSet tabela = cdao.carregarProdutos();
		this.produtos.clear();
		
		try {
			tabela.beforeFirst();
			
			while (tabela.next()) {							
				this.produtos.add(dadosProduto(tabela));				
			}
			tabela.close();
		
		} catch (Exception e) {
			System.err.println(e);
			e.printStackTrace();
		}
	}
	
	private Produto dadosProduto(ResultSet tabela) {
		Produto p = new Produto();
		
		try {
			p.setNome_prod(tabela.getString("nome_prod"));
			p.setDescricao(tabela.getString("descricao"));
			p.setIdproduto(tabela.getInt("idproduto"));
			return p;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
}
