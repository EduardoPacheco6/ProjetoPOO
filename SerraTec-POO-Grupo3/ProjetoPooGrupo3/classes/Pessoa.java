package classes;

import java.util.Scanner;

abstract class Pessoa {
	private String nome;
	
	public void dadosPessoa() {			
		
		System.out.println("");
		System.out.println("Dados-------------------------------");
		System.out.printf("Nome: %s%n", this.nome);
		System.out.println("------------------------------------");
	}
	
	public void alterar() {
		@SuppressWarnings("resource")
		Scanner input = new Scanner(System.in);
		
		System.out.println("Alteracao de dados");
		System.out.println("------------------------------------");
		System.out.println("");
		System.out.println("Nome: ");
		String s = input.nextLine();
		
		if (!s.isEmpty() && !s.isBlank() && s != null)
			this.nome = s;
	}	
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
}
