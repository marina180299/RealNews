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
import model.bean.Comentario;
import model.bean.Noticia;
import model.dao.ComentarioDAO;
import model.dao.ComentarioDAOImpl;
import model.dao.NoticiasDAO;
import model.dao.NoticiasDAOImpl;

@WebServlet(name = "/AlterarNoticiaGerenciadorServlet", urlPatterns = { "/manager/noticias/alterar" })
public class AlterarNoticiaGerenciadorServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	protected void doGet(
		HttpServletRequest request,
		HttpServletResponse response
	) throws ServletException, IOException {
		
		int id = Integer.parseInt(request.getParameter("id"));
		
		Connection conn = ConnectionFactory.getConnection();
		Noticia noticia = new Noticia();
		
		try {
			NoticiasDAO dao = new NoticiasDAOImpl(conn);
			noticia = dao.get(id);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionFactory.closeConnection(conn);
		}
		
		// Gera o html
		String titulo = Config.MANAGER_PAGE_TITLE + " - " + noticia.getTitulo();
		String html = generateHtml(titulo, noticia);
		
		// "Escreve" o html na tela
		PrintWriter writer = response.getWriter();
		writer.print(html);
	}
	
	@Override
	protected void doPost(
		HttpServletRequest request,
		HttpServletResponse response
	) throws ServletException, IOException {
		
		int id = Integer.parseInt(request.getParameter("id"));
		String titulo = request.getParameter("titulo");
		String descricao = request.getParameter("descricao");
		String texto = request.getParameter("texto");

		// Se os campos não estiverem vazios ou apenas com espaços em branco
		if (!titulo.isBlank() && !descricao.isBlank() && !texto.isBlank()) {
			
			// Cria um bean com os dados
			Noticia noticia = new Noticia();
			noticia.setId(id);
			noticia.setTitulo(titulo);
			noticia.setDescricao(descricao);
			noticia.setTexto(texto);
			
			// Conecta com o banco
			Connection conn = ConnectionFactory.getConnection();
			try {
				// Atualiza a notícia no banco
				NoticiasDAO dao = new NoticiasDAOImpl(conn);
				dao.update(noticia);
			} catch (SQLException ex) {
				ex.printStackTrace();
			} finally {
				ConnectionFactory.closeConnection(conn);
			}
		}
		
		// Redireciona para a página inicial do gerenciador
		response.sendRedirect(request.getContextPath() + "/manager/noticias");
	}

	private String generateHtml(String titulo, Noticia noticia) {
		String html =
				"<!DOCTYPE html>\n" + 
				"<html>\n" + 
				"	<head>\n" + 
				"		<meta charset=\"ISO-8859-1\">\n" + 
				"		<title>" + titulo + "</title>\n" + 
				"		<link href=\"../../style.css\" rel=\"stylesheet\" type=\"text/css\">\n" + 
				"	</head>\n" + 
				"	<body>\n" + 
				"		<div>\n" + 
				"			<h1>RealNews</h1>\n" + 
				"			<hr>\n" + 
				"		</div>\n" + 
				"		<div class=\"content\">\n" +
				"			<h2>Nova notícia:</h2>\n" + 
				"			<form method=\"post\" action=\"alterar\">\n" + 
				"				<div class=\"row\">\n" + 
				"					<div class=\"col-11\">\n" + 
				"						<label class=\"comment-field-lbl\" for=\"titulo\">Título:</label>\n" + 
				"					</div>\n" + 
				"					<div class=\"col-89\">\n" + 
				"						<input name=\"titulo\" type=\"text\" placeholder=\"Digite o título da notícia..\" value=\"" + noticia.getTitulo() + "\" />\n" + 
				"					</div>\n" + 
				"				</div>\n" + 
				"				<div class=\"row\">\n" + 
				"					<div class=\"col-11\">\n" + 
				"						<label class=\"comment-field-lbl\" for=\"descricao\">Descrição:</label>\n" + 
				"					</div>\n" + 
				"					<div class=\"col-89\">\n" + 
				"						<textarea name=\"descricao\" placeholder=\"Descreva a notícia em poucas palavras..\" style=\"height: 60px\">" + noticia.getDescricao() + "</textarea>\n" + 
				"					</div>\n" + 
				"				</div>\n" +
				"				<div class=\"row\">\n" + 
				"					<div class=\"col-11\">\n" + 
				"						<label class=\"comment-field-lbl\" for=\"texto\">Texto:</label>\n" + 
				"					</div>\n" + 
				"					<div class=\"col-89\">\n" + 
				"						<textarea name=\"texto\" placeholder=\"Aqui vai o texto da notícia!\" style=\"height: 600px\">" + noticia.getTexto() + "</textarea>\n" + 
				"					</div>\n" + 
				"				</div>\n" +
				"				<div class=\"row\">\n" + 
				"					<input type=\"hidden\" name=\"id\" value=\"" + noticia.getId() + "\" />\n" + 
				"				</div>\n" + 
				"				<div class=\"row\">\n" + 
				"					<input class=\"btn comment-field-btn\" type=\"submit\" value=\"Salvar\" />\n" + 
				"				</div>\n" + 
				"			</form>\n" + 
				"		</div>\n" + 
				"		<div class=\"footer\">\n" + 
				"			<p>Copyright © 2020 - RealNews</p>\n" + 
				"		</div>\n" +
				"	</body>\n" + 
				"</html>";
		
		return html;
	}
}
