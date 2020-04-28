package mock;
import java.util.ArrayList;
import java.util.List;

import model.Comentario;
import model.Noticia;

public class NoticiasMock {
	
	private static final String DESCRICAO =
			"Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod...";
	private static final String TEXTO =
			"Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod " +
			"tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim " +
			"veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex " +
			"ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate " +
			"velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat " +
			"cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.";
	
	public static List<Comentario> comentarios = new ArrayList<>() {{
		add(new Comentario("Giovanna", "Teste de comentário"));
		add(new Comentario("Marina", "Você é louca, Bolsominion!"));
		add(new Comentario("Giovanna", "Vc esquerdista FDP"));
		add(new Comentario("Marina", "Bozo"));
	}};
	
	public static List<Noticia> noticias = new ArrayList<>() {{
		add(new Noticia("Noticia #1", DESCRICAO, TEXTO));
		add(new Noticia("Noticia #2", DESCRICAO, TEXTO));
		add(new Noticia("Noticia #3", DESCRICAO, TEXTO));
		add(new Noticia("Noticia #4", DESCRICAO, TEXTO));
		add(new Noticia("Noticia #5", DESCRICAO, TEXTO));
		add(new Noticia("Noticia #6", DESCRICAO, TEXTO));
		add(new Noticia("Noticia #7", DESCRICAO, TEXTO));
		
		get(0).setComentarios(comentarios);
	}};
	
}
