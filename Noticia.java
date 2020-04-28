package model;

import java.io.Serializable;
import java.util.List;

public class Noticia implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private int id;
	private String titulo;
	private String descricao;
	private String texto;
	
	private List<Comentario> comentarios; // Should go here?
	
	public Noticia() {
		this("", "", "");
	}
	
	public Noticia(String titulo, String descricao, String texto) {
		this.titulo = titulo;
		this.descricao = descricao;
		this.texto = texto;
	}
	
	public String getTitulo() {
		return titulo;
	}
	
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public String getTexto() {
		return texto;
	}
	
	public void setTexto(String texto) {
		this.texto = texto;
	}

	public List<Comentario> getComentarios() {
		return comentarios;
	}

	public void setComentarios(List<Comentario> comentarios) {
		this.comentarios = comentarios;
	}

}
