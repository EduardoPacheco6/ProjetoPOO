package contantes;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Util {
	public static Scanner in = new Scanner(System.in);
	public static final String CABECALHO = 	"--------------Amazonia------------";
	public static final String MENU = 		"----------------Menu--------------";
	public static final String LINHA = 		"----------------------------------";
	public static final String LINHAD = 	"==================================";
	public static final String LINHAB = 	"__________________________________";
	public static final String MENUCADASTRO = "------------Cadastro------------";
	public static final String MENUALTERACAO = "------------Alteração-----------";
	public static final String MENUEXCLUIR = "------------Excluir-------------";
	public static final String MENUIMPRIMIR = "------------Imprimir------------";
	public static final String MENUCLIENTE = "------------Cliente-------------";
	public static final String MENUPRODUTO = "------------Produto-------------";
	public static final String MENUPEDIDO = "------------Pedido--------------";
	public static final String MENUPEDIDOITEM = "------------Pedido Item---------";
	public static final String MENUEMPRESA = "------------Empresa------------";
	public static final String MENUFINAL = "------Programa Finalizado------";

	public enum CRUD {
		CADASTRAR,
		ALTERAR,
		EXCLUIR,
		IMPRIMIR
	}
	
	public static void br() {
		System.out.println("");
	}
	
	public static void escrever(String mensagem) {
		System.out.println(mensagem);
	}
	
	public static LocalDate validarData(String mensagem) {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDate dataConvertida = null;
		String sData; 
		boolean dataValidada = false;
		
		do {
			System.out.println(mensagem);
			sData = in.nextLine();		
			
			try {
				dataConvertida = LocalDate.parse(sData, dtf);   
				dataValidada = true;
				return dataConvertida;
			} catch (Exception e) {
				System.out.println("Data inválida");	
			}
		} while (!dataValidada);
		return null;
	}
	
	public static String validaDataTransString(LocalDate local) {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		String sData; 
		boolean dataValidada = false;
		
		do {			
			try {
				sData = local.format(dtf);
				dataValidada = true;
				return sData;
			} catch (Exception e) {
				System.out.println("Data inválida");	
				return null;
			}
		} while (!dataValidada);
	}
	
 	public static int validarInteiro(String mensagem) {
		int numero = 0;
		boolean validado = false;		
		
		do {
			try {
				System.out.println(mensagem);
				String s = in.nextLine();
				numero = Integer.parseInt(s);
				validado = true;
			} catch (Exception e) {
				System.out.println("Informe um número válido - " + e.getMessage());
			}
		} while (!validado);
		
		//in.close();
		
		return numero;
	}
	
	public static double validarDouble(String mensagem) {
		String s;
		double numero = 0.0;
		boolean validado = false;
		@SuppressWarnings("resource")
		Scanner in = new Scanner(System.in);
		
		do {
			try {
				System.out.println(mensagem);
				s = in.nextLine();			
				numero = Double.parseDouble(s);
				validado = true;
			} catch (Exception e) {
				System.out.println("Informe um número válido - " + e.getMessage());
			}
		} while (!validado);
		
		//in.close();
		
		return numero;
	}

	public static String validarChar(String mensagem) {
    	boolean validado = false;
    	String caractere ;
    	Pattern p = Pattern.compile("^[sSnN]*$");
    	
    	do {
    		System.out.println(mensagem);
    		caractere = in.nextLine();

    		try {
    			if (caractere.trim().isEmpty()) {
    				throw new RuntimeException("O campo não pode conter somente caracteres vazios!\n");
    			} else if (!p.matcher(caractere).find()) {
    				throw new RuntimeException("O campo só deve conter S ou N!\n");
    			} else if (caractere.length()!=1) {
    				throw new RuntimeException("O campo deve apenas conter 1 caractere!\n");
    			}
    			else {
    				validado = true;
    			}
    		} catch (RuntimeException e) {
    			System.out.println(e.getMessage());
    		}
    	} while (!validado);
    	
    	return caractere.toUpperCase();
    }
	
    public static String validarString(String mensagem) {
    	boolean validado = false;
    	String string = null;
    	Pattern p = Pattern.compile("^[ a-zA-Zà-úÀ-Ú0-9.,ç]*$");
    	
    	do {
    		System.out.println(mensagem);
    		string = in.nextLine();

    		try {
    			if (string.trim().isEmpty()) {
    				throw new RuntimeException("O campo não pode conter somente caracteres vazios!\n");
    			} else if (!p.matcher(string).find()) {
    				throw new RuntimeException("O campo não pode conter caracteres especiais!\n");
    			} else {
    				validado = true;
    			}
    		} catch (RuntimeException e) {
    			System.out.println(e.getMessage());
    		}
    	} while (!validado);
    	
    	return string;
    }
    
    public static String validarCPF(String mensagem) {
    	Pattern p = Pattern.compile("[0-9]{11}");
    	String cpf = "00000000000";
    	boolean valido = p.matcher(cpf).matches();
    	
    	do {
    		System.out.println(mensagem);
    		cpf = in.nextLine();
    		
    		// Valida o CPF
    		valido = p.matcher(cpf).matches();

    		if (!valido) {
    			System.out.println("CPF inválido.");
    		}
    	} while (!valido);
    	
    	return cpf;
    }
    
    public static String validarTelefone(String mensagem) {
    	Pattern p = Pattern.compile("^([+0-9]{3}|00)?[0-9]{9,11}$");
    	String telefone = "+5511999999999";
    	boolean valido = p.matcher(telefone).matches();
    	
    	do {
    		System.out.println(mensagem);
    		telefone = in.nextLine();

    		// Valida o Telefone
    		valido = p.matcher(telefone).matches();

    		if (!valido) {
    			System.out.println("Telefone inválido.");
    		}
    	} while (!valido);
    	
    	return telefone;
    }
    
    public static String validarEmail(String mensagem) {
    	Pattern p = Pattern.compile("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}");
    	String email = "meuemail@gmail.com";
    	boolean valido = p.matcher(email).matches();
    	
    	do {
    		System.out.println(mensagem);
    		email = in.nextLine();

    		// Valida o email
    		valido = p.matcher(email).matches();

    		if (!valido) {
    			System.out.println("Email inválido.");
    		}
    	} while (!valido);
    	
    	return email;
    }
    
    public static String validarUf(String mensagem) {
        Pattern p = Pattern.compile("[a-zA-Z]{2}");
        String uf = "AA";
        boolean valido = p.matcher(uf).matches();
        
        do {
            System.out.println(mensagem);
            uf = in.nextLine();

            // Valida o UF
            valido = p.matcher(uf).matches();

            if (!valido) {
                System.out.println("UF inválida.");
            }
        } while (!valido);
        
        return uf;
    }
 }
