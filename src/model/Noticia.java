package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Noticia implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private static final long UNDEFINED_ID = -1L;
	
	private Long id;
	private String titulo;
	private String descricao;
	private String texto;
	
	private List<Comentario> comentarios; // Should go here?
	
	public Noticia() {
		this("", "", "");
	}
	
	public Noticia(String titulo, String descricao, String texto) {
		this(UNDEFINED_ID, titulo, descricao, texto);
	}
	
	public Noticia(Long id, String titulo, String descricao, String texto) {
		this.id = id;
		this.titulo = titulo;
		this.descricao = descricao;
		this.texto = texto;
		this.comentarios = new ArrayList<>();
	}
	
	public Long getId() {
		return id;
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