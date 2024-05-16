package com.sample;

public class Sintoma {
	
	private final String idPaciente;
	private String sintoma;	
	
	
	public Sintoma(String idPaciente, String sintoma) {
		this.idPaciente = idPaciente;
		this.sintoma = sintoma;
	}
	
	public String getIdPaciente() {return this.idPaciente;}
	public String getSintoma() {return this.sintoma;}
	public void setSintoma() {this.sintoma = sintoma;}

}
