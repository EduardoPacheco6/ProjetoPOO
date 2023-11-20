package conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Conexao {
	private String local;
	private String senha;
	private String user;
	private Connection c;
	private Statement statment;
	private String str_conexao;
	private String driverjdbc;
	
	public Conexao(DadosConexao dados) {
		if (dados.getBd().equals("PostgreSql")){
			setStr_conexao("jdbc:postgresql://"+ dados.getLocal() +":" + dados.getPorta() +"/"+ dados.getBanco());
  			setLocal(dados.getLocal());
  			setSenha(dados.getSenha());
  			setUser(dados.getUser());
  			setDriverjdbc("org.postgresql.Driver");
		}
	}
	
	public void configUser(String user, String senha) {
		setUser(user);
		setSenha(senha);
	}
	
	public void configLocal(String banco) {
		setLocal(banco);
	}
	
	public void conect(){
		try {
			Class.forName(getDriverjdbc());
			setC(DriverManager.getConnection(getStr_conexao(), getUser(), getSenha()));
			//setStatment(getC().createStatement());
			setStatment(getC().createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE, 
					ResultSet.CONCUR_UPDATABLE));
		}catch (Exception e) {
			System.err.println(e);
			e.printStackTrace();
		}
	}
	
	public void disconect(){
		try {
			getC().close();
		}catch (SQLException ex) {
			System.err.println(ex);
			ex.printStackTrace();
		}
	}
	public ResultSet query(String query){
		try {
			return getStatment().executeQuery(query);
		}catch (NullPointerException | SQLException ex) {
			if (!ex.getLocalizedMessage().contentEquals("Nenhum resultado foi retornado pela consulta.")) {
				//System.out.println(ex.getMessage());
				//System.out.println(ex.getErrorCode());
				ex.printStackTrace();
			} else if (ex.getLocalizedMessage().contentEquals("is null")) {
				System.err.println("Verifique se foi chamado o conect");
			}
			return null;
		}
	}

	public String getLocal() {
		return local;
	}

	public void setLocal(String local) {
		this.local = local;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Connection getC() {
		return c;
	}

	public void setC( Connection c) {
		this.c = c;
	}

	public Statement getStatment() {
		return statment;
	}

	public void setStatment( Statement statment) {
		this.statment = statment;
	}

	public String getStr_conexao() {
		return str_conexao;
	}

	public void setStr_conexao(String str_conexao) {
		this.str_conexao = str_conexao;
	}

	public String getDriverjdbc() {
		return driverjdbc;
	}

	public void setDriverjdbc(String driverjdbc) {
		this.driverjdbc = driverjdbc;
	}
}
