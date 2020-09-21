package com.teste;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.db.ConexaoHSQLDB;

public class NewTesteConnection {

	public static void main(String[] args) {
		ConexaoHSQLDB conn = new ConexaoHSQLDB();
		Connection connection = conn.conectar();
		System.out.println(connection);
		
		//Inserir dados
				final String SQL_INSERT_FUNCIONARIO = "INSERT INTO FUNCIONARIO(NOME, NASCIMENTO, ALTURA) VALUES ( ?, ?, ?)";
				PreparedStatement pst;
				try {
					
					pst = connection.prepareStatement(SQL_INSERT_FUNCIONARIO);
					pst.setString(1, "aaaa");
					pst.setDate(2, Date.valueOf("1999-06-23"));
					pst.setFloat(3, (float) 1.75);
					int qtde = pst.executeUpdate();
					System.out.println("Qtde inserido: "+ qtde);
				} catch (SQLException e) {
					e.printStackTrace();
				}
				
				
				final String SQL_SELECT_FUNCIONARIO = "SELECT * FROM FUNCIONARIO";
//				PreparedStatement pst;
				try {
					pst = connection.prepareStatement(SQL_SELECT_FUNCIONARIO);
					ResultSet rs = pst.executeQuery();
					
					while(rs.next()) {
						int id = rs.getInt("ID");
						String nome = rs.getString("NOME");
						Date nascimento = rs.getDate("NASCIMENTO");
						Float altura = rs.getFloat("ALTURA");

						System.out.println(id +" "+ nascimento +" "+ altura );
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
				
				try {
					connection.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
	}
}
