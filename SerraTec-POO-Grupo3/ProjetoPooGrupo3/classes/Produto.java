package classes;

import contantes.Util;

public class Produto {
	private int idproduto;
	private String nome_prod;
	private String descricao;
	
	public static Produto cadastrar() {
		
		Produto p = new Produto();
		p.setNome_prod(Util.validarString("Insira o nome Produto: "));
		p.setDescricao(Util.validarString("Insira a Descrição do Produto: "));
		
		return p;
	}
	
	public static Produto alterar(Produto prod) {
		
		prod.setNome_prod(Util.validarString("Insira o nome Produto: "));
		prod.setDescricao(Util.validarString("Insira a Descrição do Produto: "));
		
		return prod;
	}
	
	public static Produto select() {
		Produto c = new Produto();
		c.setIdproduto(Util.validarInteiro("Informe o id do Produto: "));
		return c;
	}

	public int getIdproduto() {
		return idproduto;
	}

	public void setIdproduto(int idproduto) {
		this.idproduto = idproduto;
	}

	public String getNome_prod() {
		return nome_prod;
	}

	public void setNome_prod(String nome_prod) {
		this.nome_prod = nome_prod;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
}
