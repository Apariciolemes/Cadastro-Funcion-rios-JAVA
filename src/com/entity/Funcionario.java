package com.entity;

import java.sql.Date;
import java.text.SimpleDateFormat;

public class Funcionario {

	private int id;
	private String nome;
	private Date nascimento;
	private Float altura;
	
	public Funcionario() {
	}
	public Funcionario(String nome, Date nascimento, Float altura ) {
		super();
		this.nome = nome;
		this.nascimento = nascimento;
		this.altura = altura;
	}
	
	public Funcionario(int id, String nome, Date nascimento, Float altura) {
		super();
		this.id = id;
		this.nome = nome;
		this.nascimento = nascimento;
		this.altura = altura;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Date getNascimento() {
		return nascimento;
	}
	public void setNascimento(Date nascimento) {
		this.nascimento = nascimento;
	}
	public Float getAltura() {
		return altura;
	}
	public void setAltura(Float altura) {
		this.altura = altura;
	}
	@Override
	public String toString() {
		SimpleDateFormat dt = new SimpleDateFormat("dd/MM/yyyy");
		return "Funcionario - ID: " + id + "  \n- Nome: " + nome + "  \n- Nascimento: " + dt.format(nascimento) + "  \n- Altura:" + altura + "";
	}
	
	
	
}