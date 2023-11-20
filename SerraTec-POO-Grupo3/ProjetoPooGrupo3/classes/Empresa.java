package classes;

import contantes.Util;

public class Empresa {
	private int idempresa;
	private String nome_fantasia;
	private String razao_social;
	

	public static Empresa cadastrar() {
		Empresa e = new Empresa();
		
		e.setNome_fantasia(Util.validarString("Insira o Nome Fantasia: "));
		e.setRazao_social(Util.validarString("Insira a Razão Social: "));
		
		return e;		
	}
	
	public static Empresa alterar(Empresa empre) {
		
		empre.setNome_fantasia(Util.validarString("Insira o Nome Fantasia: "));
		empre.setRazao_social(Util.validarString("Insira a Razão Social: "));
		
		return empre;		
	}
	
	public int getIdempresa() {
		return idempresa;
	}
	public void setIdempresa(int idEmpresa) {
		this.idempresa = idEmpresa;
	}
	public String getNome_fantasia() {
		return nome_fantasia;
	}
	public void setNome_fantasia(String nome_fantasia) {
		this.nome_fantasia = nome_fantasia;
	}
	public String getRazao_social() {
		return razao_social;
	}
	public void setRazao_social(String razao_social) {
		this.razao_social = razao_social;
	}
			
}
