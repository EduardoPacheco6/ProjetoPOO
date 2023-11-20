package conexao;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class ArquivoTxt {
	private String nmArquivo;
	File Arquivo;
	private DadosConexao data = new DadosConexao();	
	
	/**
	 * 
	 * @author Ricardo de Jesus Oliveira
	 * @return Retorna os dados de conexão
	 */
	public DadosConexao getData() {
		return data;
	}
	
	/**
	 *  Construtor da classe
	 *  
	 *  @autor Ricardo de Jesus Oliveira
	 *  
	 */
	public ArquivoTxt(String nmArquivo) {
		this.nmArquivo = nmArquivo;
		File Arq = new File(nmArquivo);
		Arquivo = Arq;
	}
	
	/**
	 * Cria um novo arquivo, se não existir.
	 * 
	 * 
	 * @author Ricardo de Jesus Oliveira
	 * @return Retorna verdadeiro se o arquivo for criado ou se já existir.
	 */
	public boolean criarArquivo() {
		try {
			Arquivo.createNewFile();		
			return true;
		} catch (IOException e) {			
			e.printStackTrace();
			return false;
		}			
	}
	
	public void escreverArquivo(String linha) {
		try {
			FileWriter arqEscrita = new FileWriter(nmArquivo, true);
			BufferedWriter arq = new BufferedWriter(arqEscrita);
			arqEscrita.write(linha);
			arq.newLine();
			arq.close();
			arqEscrita.close();
		} catch (Exception e) {
			System.err.println(e);
		}
	}
	
	public String lerArquivo() {		
		String data = "";
		try {
			if (Arquivo.exists()) {  
				Scanner Linhas = new Scanner(Arquivo);
		      
				while (Linhas.hasNextLine()) {
					data += Linhas.nextLine() + "\n";
				}
		
				Linhas.close();
			} else
				System.out.println("Arquivo não existe.");
		
		} catch (FileNotFoundException e) {
		      System.out.println("Ocorreu um erro na leitura.");
		      e.printStackTrace();
		}
		
		return data;
	}
	
	public boolean alimentaDadosConexao() {		
		try {
			if (Arquivo.exists()) {  
				Scanner Linhas = new Scanner(Arquivo);
				Linhas.reset();
				String linha;
				while (Linhas.hasNextLine()) {
					linha = Linhas.nextLine();
					
					if (linha.substring(0, 6).equals("local=")) {
						data.setLocal(linha.substring(6, linha.length()));
					} else if (linha.substring(0, 8).equals("usuario=")) {
						data.setUser(linha.substring(8, linha.length()));
					} else if (linha.substring(0, 6).equals("senha=")) {
						data.setSenha(linha.substring(6, linha.length()));
					} else if (linha.substring(0, 6).equals("porta=")) {
						data.setPorta(linha.substring(6, linha.length()));
					} else if (linha.substring(0, 3).equals("bd=")) {
						data.setBd(linha.substring(3, linha.length()));
					} else if (linha.substring(0, 6).equals("banco=")) {
						data.setBanco(linha.substring(6, linha.length()));
					}
				}
				Linhas.close();
			}		
		} catch (FileNotFoundException e) {
		      System.out.println("Ocorreu um erro na leitura.");
		      e.printStackTrace();
		}
		boolean result;
		
		if (!(data == null) &&
			!(data.getBanco()==null) &&
			!(data.getBd()==null) &&
			!(data.getLocal()==null) &&
			!(data.getPorta()==null) &&
			!(data.getSenha()==null) &&
			!(data.getUser()==null)) { 
			//result = !data.getBanco().isBlank();
			result = true;
			result = result && !data.getBanco().isEmpty();
			//result = result && !data.getBd().isBlank();
			result = result && !data.getBd().isEmpty();
			//result = result && !data.getLocal().isBlank();
			result = result && !data.getLocal().isEmpty();
			//result = result && !data.getPorta().isBlank();
			result = result && !data.getPorta().isEmpty();
			//result = result && !data.getSenha().isBlank();
			result = result && !data.getSenha().isEmpty();
			//result = result && !data.getUser().isBlank();
			result = result && !data.getUser().isEmpty();
		} else
			result = false;
		
		return result;
	}
	
	/**
	 * Exclui um arquivo existente
	 * 
	 * @author Ricardo de Jesus Oliveira
	 * @return Retorna verdadeiro se a exclusão for realizada.
	 */
	public boolean apagarArquivo() {
		return Arquivo.delete();
	}
	
	
}
