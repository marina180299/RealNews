package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mock.NoticiasMock;
import model.Noticia;

@WebServlet(name = "/NoticiaServlet", urlPatterns = { "/noticia" })
public class NoticiaServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	protected void doGet(
		HttpServletRequest request,
		HttpServletResponse response
	) throws ServletException, IOException {

		Noticia noticia = NoticiasMock.noticias.get(0);
		
		// Exibe a lista de noticias
		request.setAttribute("noticia", noticia);
		request.getRequestDispatcher("/noticia.jsp").forward(request, response);
	}

	protected void doPost(
		HttpServletRequest request,
		HttpServletResponse response
	) throws ServletException, IOException {
		// Cria uma nova noticia
		String nome = request.getParameter("nome");
		String comentario = request.getParameter("comentario");
		if (nome != null)
			System.out.println("Nova noticia: " + nome + ", " + comentario);
		
		// Exibe a lista de noticias
		doGet(request, response);
	}

}
