package com.sample;

public class Diagnostico {

	
	private String idPaciente;
	private String nomePaciente;
	private String designacao;
	public Diagnostico(String idPaciente, String nomePaciente, String designacao) {
		super();
		this.idPaciente = idPaciente;
		this.nomePaciente = nomePaciente;
		this.designacao = designacao;
	}
	public String getIdPaciente() {
		return idPaciente;
	}
	public void setIdPaciente(String idPaciente) {
		this.idPaciente = idPaciente;
	}
	public String getNomePaciente() {
		return nomePaciente;
	}
	public void setNomePaciente(String nomePaciente) {
		this.nomePaciente = nomePaciente;
	}
	public String getDesignacao() {
		return designacao;
	}
	public void setDesignacao(String designacao) {
		this.designacao = designacao;
	}
	
	
}
