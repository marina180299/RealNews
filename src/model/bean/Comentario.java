package model.bean;

import java.io.Serializable;

public class Comentario implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	public static final int ID_DESCONHECIDO = -1;
	
	private int id;
	private String nome;
	private String texto;
	
	public Comentario() {}
	
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
	
	public String getTexto() {
		return texto;
	}
	
	public void setTexto(String texto) {
		this.texto = texto;
	}
	
}
