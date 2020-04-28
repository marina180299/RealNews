package model;

import java.io.Serializable;

public class Comentario implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private int id;
	private String nome;
	private String texto;
	
	public Comentario() {
		this("", "");
	}
	
	public Comentario(String nome, String texto) {
		this.nome = nome;
		this.texto = texto;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getTexto() {
		return texto;
	}
	
	public void setTexto(String texto) {
		this.texto = texto;
	}
	
}
