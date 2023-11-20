package principal;

import lista.*;

import java.util.Scanner;

import classes.*;
import contantes.Util;
import dao.*;
import dml.*;
import conexao.*;

public class Principal {
	
	public static Conexao con;
	public static DadosConexao dadosCon = new DadosConexao();
	
	public static final String BANCO = "grupo3_poo";
	public static final String SCHEMA = "sistema";
	public static final String PATH = "dadosConexao\\";
	public static final String SFILE = "DadosConexao.ini";
	
	
	public static void main(String[] args) {
		
		if (configInicial()) {
			if (CreateDAO.createBD(BANCO, SCHEMA, dadosCon)) {
				con = new Conexao(dadosCon); 
				con.conect();
				
				int continuar;
				
				do{
					continuar = opcoes(menu());
				}while(continuar == 1);
				
				System.out.println(Util.MENUFINAL);
				
			} else {
				System.out.println("Ocorreu um problema na criação do banco de dados");
			}
		}else {
			System.err.println("Não foi possível executar o sistema.");
		}

	}
	
	public static boolean configInicial() {
		@SuppressWarnings("resource")
		Scanner input = new Scanner(System.in);
		ArquivoTxt conexaoIni = new ArquivoTxt(PATH+SFILE);
		boolean abrirSistema = false;
		String local, porta, usuario, senha, banco;
		
		
		if (conexaoIni.criarArquivo()) {
			if (conexaoIni.alimentaDadosConexao()) {
				dadosCon = conexaoIni.getData();
				abrirSistema = true;
			} else {
				conexaoIni.apagarArquivo();
				System.out.println("Arquivo de configuração de conexão:\n");
				System.out.println("Local: ");
				local = input.nextLine();
				System.out.println("Porta: ");
				porta = input.nextLine();
				System.out.println("Usuário: ");
				usuario = input.nextLine();
				System.out.println("Senha: ");
				senha = input.nextLine();
				System.out.println("Database: ");
				banco = input.nextLine();
				
				if (conexaoIni.criarArquivo()) {
					conexaoIni.escreverArquivo("bd=PostgreSql");
					conexaoIni.escreverArquivo("local="+local);
					conexaoIni.escreverArquivo("porta="+porta);
					conexaoIni.escreverArquivo("usuario="+usuario);
					conexaoIni.escreverArquivo("senha="+senha);
					conexaoIni.escreverArquivo("banco="+banco);
					
					if (conexaoIni.alimentaDadosConexao()) {
						dadosCon = conexaoIni.getData();
						abrirSistema = true;
					} else System.out.println("Não foi possível efetuar a configuração.\nVerifique");	
				}
			}
		} else
			System.out.println("Houve um problema na criação do arquivo de configuração.");
		
		return abrirSistema;
	}
	
	public static int  menu() {	
		System.out.println(Util.LINHAD);
		System.out.println(Util.CABECALHO);
		System.out.println(Util.MENU);
		System.out.println(Util.LINHAD);
		System.out.println("""
				
				1) Empresa(CRUD)
				2) Produto(CRUD)
				3) Produto-Empresa(CRUD)
				4) Cliente(CRUD)
				5) Pedido(CRUD)
				6) Itens do Pedido(CRUD)
				7) Querys(Relatórios)
				8) Sair
				""");
		System.out.println(Util.LINHAD);
		return Util.validarInteiro("Informe uma opção:");
		
		}

	
	public static int subMenu(int opcao) {
		System.out.println(Util.LINHAD);
		System.out.println(Util.CABECALHO);
		System.out.println(Util.MENU);
		System.out.println(Util.LINHAD);
		System.out.println("""
					
					1) Cadastrar
					2) Alterar
					3) Excluir
					4) Listar
					""");
		System.out.println(Util.LINHAD);
		return Util.validarInteiro("Informe uma opção:");
	}
	
	public static int subMenuQuery() {
		System.out.println(Util.LINHAD);
		System.out.println(Util.CABECALHO);
		System.out.println(Util.MENU);
		System.out.println(Util.LINHAD);
		System.out.println("""
				
				1) Deve permitir selecionar um cliente por código e mostrar todos os pedidos que possui.
				2) Deve permitir selecionar um produto por código e mostrar todos os pedidos que possui.
				3) Deve permitir selecionar um pedido por código e mostrar o seu cliente e todos os produtos que possui.
				""");
		System.out.println(Util.LINHAD);
		return Util.validarInteiro("Informe uma opção:");
	}
	
	public static void opcaoCrudCliente(int opcao) {
		switch (opcao) {
		case 1:
			cadastrarCliente();
			break;
		case 2:
			alterarCliente();
			break;
		case 3:
			excluirCliente();
			break;
		case 4:
			listarCliente();
			break;
		default:
			System.out.println("Opção inválidada");
			break;
		}
	}

	public static void opcaoCrudEmpresa(int opcao) {
		switch (opcao) {
		case 1:
			cadastrarEmpresa();
			break;
		case 2:
			alterarEmpresa();
			break;
		case 3:
			excluirEmpresa();
			break;
		case 4:
			listarEmpresa();
			break;
		default:
			System.out.println("Opção inválidada");
			break;
		}
	}
	
	public static void opcaoCrudProduto(int opcao) {
		switch (opcao) {
		case 1:
			cadastrarProduto();
			break;
		case 2:
			alterarProduto();
			break;
		case 3:
			excluirProduto();
			break;
		case 4:
			listarProduto();
			break;
		default:
			System.out.println("Opção inválidada");
			break;
		}
	}
	
	public static void opcaoCrudPedido(int opcao) {
		boolean parar = false;
		String adcionar;
		switch (opcao) {
		case 1:
			cadastrarPedido();
			do {
				adcionar = Util.validarChar("Deseja adicionar mais 1 produto ao pedido?(S ou N)");
				if (adcionar.equals("S")) {
					System.out.println("Selecione o código referente a data do pedido acima para incluir os produtos: ");
					cadastrarPedidoItem();
				}
				else {
					parar = true;
				}
			}while(!parar);
			break;
		case 2:
			alterarPedido();
			break;
		case 3:
			excluirPedido();
			break;
		case 4:
			listarPedido();
			break;
		default:
			System.out.println("Opção inválidada");
			break;
		}
	}
	
	public static void opcaoCrudPedidoItem(int opcao) {
		switch (opcao) {
		case 1:
			cadastrarPedidoItem();
			break;
		case 2:
			alterarPedidoItem();
			break;
		case 3:
			excluirPedidoItem();
			break;
		case 4:
			listarPedidoItem();
			break;
		default:
			System.out.println("Opção inválidada");
			break;
		}
	}
	
	public static void opcaoCrudProdEmpr(int opcao) {
		switch (opcao) {
		case 1:
			cadastrarProdEmpr();
			break;
		case 2:
			alterarProdEmpr();
			break;
		case 3:
			excluirProdEmpr();
			break;
		case 4:
			listarProdEmpr();
			break;
		default:
			System.out.println("Opção inválidada");
			break;
		}
	}
	
	public static void opcaoQuery(int opcao) {
		switch (opcao) {
		case 1:
			queryTodosPedCliente();
			break;
		case 2:
			queryTodosPedProd();
			break;
		case 3:
			queryPedClienteProdutos();
			break;
		default:
			System.out.println("Opção inválidada");
			break;
		}
	}
	
	public static int opcoes(int opcao) {
		
			switch (opcao) {
			case 1:
				opcaoCrudEmpresa(subMenu(opcao));
				break;
			case 2:
				opcaoCrudProduto(subMenu(opcao));
				break;
			case 3:
				opcaoCrudProdEmpr(subMenu(opcao));
				break;
			case 4:
				opcaoCrudCliente(subMenu(opcao));
				break;
			case 5:
				opcaoCrudPedido(subMenu(opcao));
				break;
			case 6:
				opcaoCrudPedidoItem(subMenu(opcao));
				break;
			case 7:
				opcaoQuery(subMenuQuery());
				break;
			case 8:
				System.out.println(Util.MENUFINAL);
				break;
			default:
				System.out.println("Opção inválidada");
				break;
			}
		return Util.validarInteiro("\nDeseja voltar ao menu inicial? \n1) Voltar \n2) Sair");
	}
	
	public static void cadastrarCliente() {
		ClienteDML.gravarCliente(con, SCHEMA, Cliente.cadastrar());
	}
	
	public static void cadastrarEmpresa() {
		EmpresaDML.gravarEmpresa(con, SCHEMA, Empresa.cadastrar());
	}
	
	public static void cadastrarPedido() {
		PedidoDML.gravarPedido(con, SCHEMA, Pedido.cadastrar(con,SCHEMA));
	}
	
	public static void cadastrarPedidoItem() {
		PedidoItemDML.gravarPedidoItem(con, SCHEMA, PedidoItem.cadastrar(con,SCHEMA));

	}
	
	public static void cadastrarProduto() {
		ProdutoDML.gravarProduto(con, SCHEMA, Produto.cadastrar());
	}
	
	public static void cadastrarProdEmpr() {
		ProdEmprDML.gravarProdEmpr(con, SCHEMA, ProdEmpr.cadastrar(con,SCHEMA));
	}
	
	public static void alterarCliente() {
		ListaCliente listaCliente = new ListaCliente(con,SCHEMA);
		listaCliente.imprimirClientes();
		ClienteDML.alterarCliente(con, SCHEMA, Cliente.alterar(listaCliente.localizarCliente(Util.validarInteiro("Informe o Id do Cliente que deseja alterar:"))));
	}
	
	public static void alterarEmpresa() {
		ListaEmpresa listaEmpresa = new ListaEmpresa(con,SCHEMA);
		listaEmpresa.imprimirEmpresas();
		EmpresaDML.alterarEmpresa(con, SCHEMA, Empresa.alterar(listaEmpresa.localizarEmpresa(Util.validarInteiro("Informe o Id da Empresa que deseja alterar:"))));
	}
	
	public static void alterarPedido() {
		ListaPedido listaPedido = new ListaPedido(con,SCHEMA);
		listaPedido.imprimirPedidos();
		PedidoDML.alterarPedido(con, SCHEMA, Pedido.alterar(con,SCHEMA,listaPedido.localizarPedido(Util.validarInteiro("Informe o Id da Pedido que deseja alterar:"))));
	}
	
	public static void alterarPedidoItem() {
		ListaPedidoItem listaPedidoItem = new ListaPedidoItem(con,SCHEMA);
		listaPedidoItem.imprimirPedidoItens();
		PedidoItemDML.alterarPedidoItem(con, SCHEMA, PedidoItem.alterar(con,SCHEMA,listaPedidoItem.localizarPedidoItem(Util.validarInteiro("Informe o Id do Pedido Item que deseja alterar:"))));
	}
	
	public static void alterarProduto() {
		ListaProduto listaProduto = new ListaProduto(con,SCHEMA);
		listaProduto.imprimirProdutos();
		ProdutoDML.alterarProduto(con, SCHEMA, Produto.alterar(listaProduto.localizarProduto(Util.validarInteiro("Informe o Id da Produto que deseja alterar:"))));
	}
	
	public static void alterarProdEmpr() {
		ListaProdEmpr listaProdEmpr = new ListaProdEmpr(con,SCHEMA);
		listaProdEmpr.imprimirProdEmpr();
		ProdEmprDML.alterarProdEmpr(con, SCHEMA, ProdEmpr.alterar(con,SCHEMA,listaProdEmpr.localizarProdEmpr(Util.validarInteiro("Informe o Id do Produto-Empresa que deseja alterar:"))));
	}
	
	public static void excluirCliente() {
		ListaCliente listaCliente = new ListaCliente(con,SCHEMA);
		listaCliente.imprimirClientes();
		ClienteDML.excluirCliente(con, SCHEMA, listaCliente.localizarCliente(Util.validarInteiro("Informe o Id do Cliente que deseja excluir:")));
		
	}
	
	public static void excluirEmpresa() {
		ListaEmpresa listaEmpresa = new ListaEmpresa(con,SCHEMA);
		listaEmpresa.imprimirEmpresas();
		EmpresaDML.excluirEmpresa(con, SCHEMA, listaEmpresa.localizarEmpresa(Util.validarInteiro("Informe o Id da Empresa que deseja excluir:")));
	}
	
	public static void excluirPedido() {
		ListaPedido listaPedido = new ListaPedido(con,SCHEMA);
		listaPedido.imprimirPedidos();
		PedidoDML.excluirPedido(con, SCHEMA, listaPedido.localizarPedido(Util.validarInteiro("Informe o Id do Pedido que deseja excluir:")));
	}
	
	public static void excluirPedidoItem() {
		ListaPedidoItem listaPedidoItem = new ListaPedidoItem(con,SCHEMA);
		listaPedidoItem.imprimirPedidoItens();
		PedidoItemDML.excluirPedidoItem(con, SCHEMA, listaPedidoItem.localizarPedidoItem(Util.validarInteiro("Informe o Id do Pedido Item que deseja excluir:")));
	}
	
	public static void excluirProduto() {
		ListaProduto listaProduto = new ListaProduto(con,SCHEMA);
		listaProduto.imprimirProdutos();
		ProdutoDML.excluirProduto(con, SCHEMA, listaProduto.localizarProduto(Util.validarInteiro("Informe o Id do Produto que deseja excluir:")));
	}
	
	public static void excluirProdEmpr() {
		ListaProdEmpr listaProdEmpr = new ListaProdEmpr(con,SCHEMA);
		listaProdEmpr.imprimirProdEmpr();
		ProdEmprDML.excluirProdEmpr(con, SCHEMA, listaProdEmpr.localizarProdEmpr(Util.validarInteiro("Informe o Id do Produto-Empresa que deseja excluir:")));
	}
	
	public static void listarCliente() {
		ListaCliente listaCliente = new ListaCliente(con,SCHEMA);
		listaCliente.imprimirClientes();
	}
	
	public static void listarEmpresa() {
		ListaEmpresa listaEmpresa = new ListaEmpresa(con,SCHEMA);
		listaEmpresa.imprimirEmpresas();		
	}
	
	public static void listarPedido() {
		ListaPedido listaPedido = new ListaPedido(con,SCHEMA);
		listaPedido.imprimirPedidos();
	}
	
	public static void listarPedidoItem() {
		ListaPedidoItem listaPedidoItem = new ListaPedidoItem(con,SCHEMA);
		listaPedidoItem.imprimirPedidoItens();		
	}
	
	public static void listarProduto() {
		ListaProduto listaProduto = new ListaProduto(con,SCHEMA);
		listaProduto.imprimirProdutos();
	}
	public static void listarProdEmpr() {
		ListaProdEmpr listaProdEmpr = new ListaProdEmpr(con,SCHEMA);
		listaProdEmpr.imprimirProdEmpr();
	}
	
	public static void queryTodosPedCliente() {
		QueryDML.querySelectCliente(con, SCHEMA, Cliente.select());
	}

	public static void queryTodosPedProd() {
		QueryDML.querySelectProduto(con, SCHEMA, Produto.select());
	}
	
	public static void queryPedClienteProdutos() {
		QueryDML.querySelectPedido(con, SCHEMA, Pedido.select());
	}
	
}