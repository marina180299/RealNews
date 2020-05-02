package view;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import config.Config;
import conn.ConnectionFactory;
import model.bean.Comentario;
import model.bean.Noticia;
import model.dao.NoticiasDAO;
import model.dao.NoticiasDAOImpl;

@WebServlet(name = "/NoticiaServlet", urlPatterns = { "/noticia" })
public class NoticiaServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	protected void doGet(
		HttpServletRequest request,
		HttpServletResponse response
	) throws ServletException, IOException {
		
		// Pega o id da noticia, passado como parâmetro
		int id = Integer.parseInt(request.getParameter("id"));
		
		// Faz a conexão com o banco
		Connection conn = ConnectionFactory.getConnection();
		
		Noticia noticia = new Noticia();
		
		try {
			// Requisita a noticia
			NoticiasDAO dao = new NoticiasDAOImpl(conn);
			noticia = dao.get(id);
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			ConnectionFactory.closeConnection(conn);
		}
		
		// Gera o código html para essa página
		String titulo = Config.PAGE_TITLE + " - " + noticia.getTitulo();
		String html = generateHtml(titulo, noticia);
		
		// "Escreve" o html na tela
		PrintWriter writer = response.getWriter();
		writer.print(html);
	}
	
	private String generateHtml(String titulo, Noticia noticia) {
		String html =
				"<!DOCTYPE html>\n" + 
				"<html>\n" + 
				"	<head>\n" + 
				"		<meta charset=\"ISO-8859-1\">\n" + 
				"		<title>" + titulo + "</title>\n" + 
				"		<link href=\"style.css\" rel=\"stylesheet\" type=\"text/css\">\n" + 
				"	</head>\n" + 
				"	<body>\n" + 
				"		<div>\n" + 
				"			<h1>RealNews</h1>\n" + 
				"			<hr>\n" + 
				"		</div>\n" + 
				"		<div class=\"content\">\n" + 
				"			<h2>" + noticia.getTitulo() + "</h2>\n" + 
				"			<br>\n" + 
				"			<p>" + noticia.getTexto() + "</p>\n" + 
				"			<br>\n" + 
				"			<h2>Comentários</h2>\n" + 
				"			<div class=\"comment-container\">\n";
		
		// Exibe os comentários
		for (Comentario comentario : noticia.getComentarios()) {
			html += "				<h3>" + comentario.getNome() + "</h3>\n";
			html += "				<p class=\"comment-text\">" + comentario.getTexto() + "</p>\n"; 
			html += "				<hr class=\"comment-separator\">\n"; 
		} 
 
		html +=
				"				<h3 class=\"add-comment-lbl\">Adicionar comentário:</h3>\n" + 
				"				<form method=\"post\" action=\"comentario\">\n" + 
				"					<div class=\"row\">\n" + 
				"						<div class=\"col-16\">\n" + 
				"							<label class=\"comment-field-lbl\" for=\"nome\">Nome:</label>\n" + 
				"						</div>\n" + 
				"						<div class=\"col-84\">\n" + 
				"							<input name=\"nome\" type=\"text\" placeholder=\"Digite seu nome..\" />\n" + 
				"						</div>\n" + 
				"					</div>\n" + 
				"					<div class=\"row\">\n" + 
				"						<div class=\"col-16\">\n" + 
				"							<label class=\"comment-field-lbl\" for=\"texto\">Comentário:</label>\n" + 
				"						</div>\n" + 
				"						<div class=\"col-84\">\n" + 
				"							<textarea name=\"texto\" placeholder=\"Diga algo sobre a notícia..\" style=\"height: 100px\"></textarea>\n" + 
				"						</div>\n" + 
				"					</div>\n" +
				"					<div class=\"row\">\n" + 
				"						<input type=\"hidden\" name=\"noticia_id\" value=\"" + noticia.getId() + "\" />\n" + 
				"					</div>\n" + 
				"					<div class=\"row\">\n" + 
				"						<input class=\"btn comment-field-btn\" type=\"submit\" value=\"Enviar\" />\n" + 
				"					</div>\n" + 
				"				</form>\n" + 
				"			</div>\n" + 
				"		</div>\n" + 
				"		<div class=\"footer\">\n" + 
				"			<p>Copyright © 2020 - RealNews</p>\n" + 
				"		</div>\n" +
				"	</body>\n" + 
				"</html>";
		
		return html;
	}

}
