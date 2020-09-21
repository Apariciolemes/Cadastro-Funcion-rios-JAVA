package com.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoHSQLDB {
	// login de acesso
	private String usuario = "SA";
	//senha de acesso
	private String senha = "";
	// pasta do projeto que a criação da base de dados
	private String PathBase = "C:\\Users\\netol\\eclipse-workspace\\Prova-Aparicio\\DadosDB\\funcionarios";
	// pegar está url duplo click na pasta LIB hsqldb, URL do connect selecionando Standalone
	private String URL = "jdbc:hsqldb:file:" + PathBase +";";
	
	public Connection conectar() {
		try {
			return DriverManager.getConnection(URL, usuario, senha);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new Error("SQLException");
			
		}
	}
}
