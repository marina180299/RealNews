package model;

import java.io.Serializable;

public class Comentario implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private static final long UNDEFINED_ID = -1L;
	
	private Long id;
	private String nome;
	private String texto;
	
	public Comentario() {
		this("", "");
	}
	
	public Comentario(String nome, String texto) {
		this(UNDEFINED_ID, nome, texto);
	}
	
	public Comentario(Long id, String nome, String texto) {
		this.id = id;
		this.nome = nome;
		this.texto = texto;
	}
	
	public Long getId() {
		return id;
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
