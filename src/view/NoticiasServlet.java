package view;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import config.Config;
import mock.NoticiasMock;
import model.Noticia;

@WebServlet(name = "/NoticiasServlet", urlPatterns = { "/" })
public class NoticiasServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	protected void doGet(
		HttpServletRequest request,
		HttpServletResponse response
	) throws ServletException, IOException {
		
		List<Noticia> noticias = NoticiasMock.noticias;
		
		String html =
				"<!DOCTYPE html>" + 
				"<html>" + 
				"	<head>" + 
				"		<meta charset=\"ISO-8859-1\">" + 
				"		<title>" + Config.PAGE_TITLE + "</title>" + 
				"		<link href=\"style.css\" rel=\"stylesheet\" type=\"text/css\">" + 
				"	</head>" + 
				"	<body>" + 
				"		<h1>RealNews</h1>" + 
				"		<hr>" + 
				"		<ol>";
		
		for (Noticia noticia : noticias) {
			html += "<li><a href=\"noticia?id=" + noticia.getId() + "\">" + noticia.getTitulo() + "</a></li>"; 
		}
		
		html += 
				"		</ol>" + 
				"		<hr>" + 
				"	</body>" + 
				"</html>";
		
		// Escreve o html
		PrintWriter writer = response.getWriter();
		writer.print(html);
	}

	protected void doPost(
		HttpServletRequest request,
		HttpServletResponse response
	) throws ServletException, IOException {
		doGet(request, response);
	}

}
