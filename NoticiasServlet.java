package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mock.NoticiasMock;

@WebServlet(name = "/NoticiasServlet", urlPatterns = { "/noticias" })
public class NoticiasServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	protected void doGet(
		HttpServletRequest request,
		HttpServletResponse response
	) throws ServletException, IOException {
		
		// Exibe a lista de noticias
		request.setAttribute("noticias", NoticiasMock.noticias);
		request.getRequestDispatcher("/noticias.jsp").forward(request, response);
	}

	protected void doPost(
		HttpServletRequest request,
		HttpServletResponse response
	) throws ServletException, IOException {
		doGet(request, response);
	}

}
