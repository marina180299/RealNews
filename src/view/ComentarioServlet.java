package view;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mock.NoticiasMock;
import model.Comentario;

@WebServlet(name = "/ComentarioServlet", urlPatterns = { "/comentario" })
public class ComentarioServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int noticiaId = Integer.parseInt(request.getParameter("noticia_id"));
		String nome = request.getParameter("nome");
		String texto = request.getParameter("texto");
		
		// Cria um novo comentário
		if (!nome.isBlank() && !texto.isBlank()) {
			var comentarios = NoticiasMock.noticias.get(noticiaId).getComentarios();
			comentarios.add(new Comentario(50L, nome, texto));
			NoticiasMock.noticias.get(0).setComentarios(comentarios);
			
			System.out.println("Novo comentário: " + noticiaId + ", " + nome + ", " + texto);
		}
		
		// Redireciona para a noticia aberta anteriormente
		request.getRequestDispatcher("/noticia?id=" + noticiaId).forward(request, response);
	}

}
