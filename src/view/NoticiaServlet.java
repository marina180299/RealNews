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
import model.Comentario;
import model.Noticia;

@WebServlet(name = "/NoticiaServlet", urlPatterns = { "/noticia" })
public class NoticiaServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	protected void doGet(
		HttpServletRequest request,
		HttpServletResponse response
	) throws ServletException, IOException {
		
		int id = Integer.parseInt(request.getParameter("id"));
		Noticia noticia = NoticiasMock.noticias.get(id);
		
		String html =
				"<!DOCTYPE html>" + 
				"<html>" + 
				"	<head>" + 
				"		<meta charset=\"ISO-8859-1\">" + 
				"		<title>" + Config.PAGE_TITLE + "</title>" + 
				"		<link href=\"style.css\" rel=\"stylesheet\" type=\"text/css\">" + 
				"	</head>" + 
				"	<body>" + 
				"		<div>" + 
				"			<h1>RealNews</h1>" + 
				"			<hr>" + 
				"		</div>" + 
				"		<div class=\"content\">" + 
				"			<h2>" + noticia.getTitulo() + "</h2>" + 
				"			<br>" + 
				"			<p>" + noticia.getTexto() + "</p>" + 
				"			<br>" + 
				"			<h2>Comentários</h2>" + 
				"			<div class=\"comment-container\">";
		
		// Exibe os comentários
		for (Comentario comentario : noticia.getComentarios()) {
			html += "<h3>" + comentario.getNome() + "</h3>";
			html += "<p class=\"comment-text\">" + comentario.getTexto() + "</p>"; 
			html += "<hr class=\"comment-separator\">"; 
		} 
 
		html +=
				"				<h3 class=\"add-comment-lbl\">Adicionar comentário:</h3>" + 
				"				<form method=\"post\" action=\"comentario\">" + 
				"					<div class=\"row\">" + 
				"						<div class=\"col-16\">" + 
				"							<label class=\"comment-field-lbl\" for=\"nome\">Nome:</label>" + 
				"						</div>" + 
				"						<div class=\"col-84\">" + 
				"							<input name=\"nome\" type=\"text\" placeholder=\"Digite seu nome..\" />" + 
				"						</div>" + 
				"					</div>" + 
				"					<div class=\"row\">" + 
				"						<div class=\"col-16\">" + 
				"							<label class=\"comment-field-lbl\" for=\"texto\">Comentário:</label>" + 
				"						</div>" + 
				"						<div class=\"col-84\">" + 
				"							<textarea name=\"texto\" placeholder=\"Diga algo sobre a notícia..\" style=\"height: 100px\"></textarea>" + 
				"						</div>" + 
				"					</div>" +
				"					<div class=\"row\">" + 
				"						<input type=\"hidden\" name=\"noticia_id\" value=\"" + id + "\" />" + 
				"					</div>" + 
				"					<div class=\"row\">" + 
				"						<input class=\"comment-field-btn\" type=\"submit\" value=\"Enviar\" />" + 
				"					</div>" + 
				"				</form>" + 
				"			</div>" + 
				"		</div>" + 
				"		<div class=\"footer\">" + 
				"			<p>Copyright © 2020 - RealNews</p>" + 
				"		</div>" +
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
		
		// Exibe a lista de noticias
		doGet(request, response);
	}

}
