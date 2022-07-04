package br.edu.ifcvideira.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {	
	private final static String driver = "org.postgresql.Driver";
	private final static String usuario = "postgres";
	private final static String senha = "1234";
	private final static String host = "localhost";
	private final static String porta = "5432";
	private final static String banco = "DBPI";
	private final static String url = "jdbc:postgresql://" + host + ":" + porta + "/" + banco;
	private static Connection conexao = null;
	    
	public static Connection conectar(){
		 try {
			 Class.forName(driver);
			 conexao = DriverManager.getConnection(url, usuario, senha);
			 System.out.println("Conexão efetuada com sucesso");
	       
		 } catch (Exception ex) {
			 ex.printStackTrace();
		 }
		return conexao; 
	}

	public static void fechar() {
		try {
			conexao.close();
			System.out.println("Conexão encerrada");
		} 
	        
		catch (SQLException e) {
			e.printStackTrace();
		}
	}	
}