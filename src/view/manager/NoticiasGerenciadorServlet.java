package view.manager;

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
import model.bean.Noticia;
import model.dao.NoticiasDAO;
import model.dao.NoticiasDAOImpl;

@WebServlet(name = "/NoticiasGerenciadorServlet", urlPatterns = { "/manager/noticias" })
public class NoticiasGerenciadorServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	protected void doGet(
		HttpServletRequest request,
		HttpServletResponse response
	) throws ServletException, IOException {
		
		Connection conn = ConnectionFactory.getConnection();
		List<Noticia> noticias = new ArrayList<>();
		
		try {
			NoticiasDAO dao = new NoticiasDAOImpl(conn);
			noticias = dao.getAll();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionFactory.closeConnection(conn);
		}
		
		// Gera o html
		String html = generateHtml(Config.MANAGER_PAGE_TITLE, noticias);
		
		// "Escreve" o html na tela
		PrintWriter writer = response.getWriter();
		writer.print(html);
	}

	private String generateHtml(String titulo, List<Noticia> noticias) {
		String html =
				"<!DOCTYPE html>\n" + 
				"<html>\n" + 
				"	<head>\n" + 
				"		<meta charset=\"ISO-8859-1\">\n" + 
				"		<title>" + titulo + "</title>\n" + 
				"		<link href=\"../style.css\" rel=\"stylesheet\" type=\"text/css\">\n" + 
				"	</head>\n" + 
				"	<body>\n" + 
				"		<h1>RealNews</h1>\n" + 
				"		<hr>\n" + 
				"		<ol>\n" +
				"		<a class=\"btn\" href=\"noticias/nova\">Criar notícia</a>\n";
		
		for (Noticia noticia : noticias) {
			html += "			<li class=\"list-item\">\n" +
					"				<div class=\"row content\">\n" +
					"					<div class=\"col-80\">\n" +
					"						<a href=\"../noticia?id=" + noticia.getId() + "\">" + noticia.getTitulo() + "</a>\n" +
					"						<br>\n" +
					"						<p>" + noticia.getDescricao() + "</p>\n" +
					"					</div>\n" +
					"					<div class=\"col-20\">\n" +
					"						<a class=\"btn\" href=\"noticias/alterar?id=" + noticia.getId() + "\">Editar</a>\n" +
					"						<a class=\"btn\" href=\"noticias/excluir?id=" + noticia.getId() + "\">Excluir</a>\n" +
					"					</div>\n" +
					"					<hr class=\"comment-separator\">\n" +
					"				</div>\n" +
					"			</li>\n"; 
		}
		
		html += "		</ol>\n" + 
				"	</body>\n" + 
				"</html>";
		
		return html;
	}
}
