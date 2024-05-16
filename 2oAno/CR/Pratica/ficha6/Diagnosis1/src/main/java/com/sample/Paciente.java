package com.sample;

public class Paciente {
	
	private String id;
	private String nome;
	private int idade;
	private String diagnostico;
	
	
	public Paciente(String id, String nome, int idade, String diagnostico) {
		this.id = id;
		this.nome = nome;
		this.idade = idade;
		this.diagnostico = diagnostico;
	}
	
	public String getId() {return this.id;}
	//public void setID(String id) {this.id = id;}
	public String getNome() {return this.nome;}
	//public void setNome(String nome) {this.nome = nome;}
	public int getIdade() {return this.idade;}
	//public void setIDade(int idade) {this.idade = idade;}
	public String getDiagnostico() {return this.diagnostico;}
	public void setDiagnostico(String diagnostico) {this.diagnostico = diagnostico;}
	
	
	

}