package com.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.db.ConexaoHSQLDB;
import com.entity.Funcionario;

public class FuncionarioDAO extends ConexaoHSQLDB {
	final String SQL_INSERT_Funcionario = "INSERT INTO Funcionario(NOME, NASCIMENTO, ALTURA) VALUES ( ?, ?, ?)";
	final String SQL_SELECT_Funcionario = "SELECT * FROM Funcionario";
	final String SQL_SELECT_Funcionario_ID = "SELECT * FROM Funcionario WHERE ID = ?";
	final String SQL_ALTERA_Funcionario = "UPDATE Funcionario SET NOME=?, NASCIMENTO=?, ALTURA=? WHERE ID = ?";
	final String SQL_DELETA_Funcionario = "DELETE FROM Funcionario WHERE ID = ?";
	public int inserir(Funcionario Funcionario) {
		int quantidade = 0;

		try (Connection connection = this.conectar();
				PreparedStatement pst = connection.prepareStatement(SQL_INSERT_Funcionario);) {
			pst.setString(1, Funcionario.getNome());
			pst.setDate(2, Funcionario.getNascimento());
			pst.setFloat(3, Funcionario.getAltura());
			quantidade = pst.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return quantidade;
	}
	
	
	
	public List<Funcionario> listAll(){
		List<Funcionario> listaFuncionarios = new ArrayList<Funcionario>();
		
		try (Connection connection = this.conectar();
				PreparedStatement pst = connection.prepareStatement(SQL_SELECT_Funcionario);){

			ResultSet rs = pst.executeQuery();
			
			while(rs.next()) {
				Funcionario Funcionario = new Funcionario();
				
				Funcionario.setId(rs.getInt("ID"));
				Funcionario.setNome(rs.getString("NOME"));
				Funcionario.setNascimento(rs.getDate("NASCIMENTO"));
				Funcionario.setAltura(rs.getFloat("ALTURA"));
				
				listaFuncionarios.add(Funcionario);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return listaFuncionarios;
	}



	public Funcionario findByID(int id) {
		Funcionario Funcionario = null;
		try (Connection connection = this.conectar();
				PreparedStatement pst = connection.prepareStatement(SQL_SELECT_Funcionario_ID);){

			pst.setInt(1, id);
			ResultSet rs = pst.executeQuery();
			
			while(rs.next()) {
				Funcionario = new Funcionario();
				
				Funcionario.setId(rs.getInt("ID"));
				Funcionario.setNome(rs.getString("NOME"));
				Funcionario.setNascimento(rs.getDate("NASCIMENTO"));
				Funcionario.setAltura(rs.getFloat("ALTURA"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return Funcionario;
	}



	public int alterar(Funcionario Funcionario) {
		int quantidade = 0;

		try (Connection connection = this.conectar();
			PreparedStatement pst = connection.prepareStatement(SQL_ALTERA_Funcionario);) {
			pst.setString(1, Funcionario.getNome());
			pst.setDate(2, Funcionario.getNascimento());
			pst.setFloat(3, Funcionario.getAltura());
			pst.setInt(4, Funcionario.getId());
			quantidade = pst.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} 

		return quantidade;
	}
	
	public int deletar(int id) {
        int quantidade = 0;
        try (Connection connection = this.conectar();
            PreparedStatement pst = connection.prepareStatement(SQL_DELETA_Funcionario);) {
            pst.setInt(1, id);
            quantidade = pst.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
        return quantidade;
    }
	

}