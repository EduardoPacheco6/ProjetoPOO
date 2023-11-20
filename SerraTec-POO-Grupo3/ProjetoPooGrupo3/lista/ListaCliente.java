package lista;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import classes.Cliente;
import conexao.Conexao;
import contantes.Util;
import dao.ClienteDAO;

public class ListaCliente {
	private Conexao con;
	private String schema;
	
	ArrayList<Cliente> clientes = new ArrayList<>();
	
	public ListaCliente (Conexao con, String schema) {
		this.con = con;
		this.schema = schema;
		
		carregarListaClientes();
	}
	
	public void adicionarClienteLista(Cliente c) {
		this.clientes.add(c);
	}
	
	public Cliente localizarCliente(int idCliente) {
		Cliente localizado = null;
		
		for (Cliente c : clientes) {
			if (c.getIdcliente() == idCliente) {
				localizado = c;
				break;
			}
		}
		return localizado;
	}
	
	private void carregarListaClientes() {
		ClienteDAO cdao = new ClienteDAO(con, schema);
		
		ResultSet tabela = cdao.carregarClientes();
		this.clientes.clear();
		
		try {
			tabela.beforeFirst();
			
			while (tabela.next()) {							
				this.clientes.add(dadosCliente(tabela));				
			}
			tabela.close();
		
		} catch (Exception e) {
			System.err.println(e);
			e.printStackTrace();
		}
	}
	
	public void imprimirClientes() {
		ArrayList<String[]> tabela = new ArrayList<>();
		String aux;
		tabela.add(new String[] {"Id Cliente","Nome", "CPF", "Telefone","Email", "Data de Nascimento","Endereco"});
		for (Cliente cliente : clientes) {
			aux=Util.validaDataTransString(cliente.getData_nasc());
			tabela.add(new String[] {String.valueOf(cliente.getIdcliente()),cliente.getNome(),cliente.getCpf(), cliente.getTelefone(), cliente.getEmail(), aux, cliente.getEndereco()});
		}
		for (int i = 0; i < tabela.size(); i++) {
		    String[] linha = tabela.get(i);
		    if(i == 1) {
		    	for (int k=0;k<linha.length;k++) {
		    		System.out.print(Util.LINHAD);
		    	}
		    	System.out.print(Util.LINHAD);
		    	System.out.println();
	        }
		    for (int j = 0; j < linha.length; j++) {
		        if(j == linha.length-1) {
		        	System.out.format("%-80s | ", linha[j]);
		        }
		        else {
		        	System.out.format("%-30s | ", linha[j]);
		        }
		    	
		    }
		    System.out.println();
		}
		
	}
	
	private Cliente dadosCliente(ResultSet tabela) {
		Cliente c = new Cliente();
		
		try {
			c.setNome(tabela.getString("nome"));
			c.setCpf(tabela.getString("cpf"));
			c.setTelefone(tabela.getString("telefone"));
			c.setEndereco(tabela.getString("endereco"));
			c.setEmail(tabela.getString("email"));
			c.setData_nasc(tabela.getDate("data_nasc").toLocalDate());
			c.setIdcliente(tabela.getInt("idcliente"));
			return c;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
}
