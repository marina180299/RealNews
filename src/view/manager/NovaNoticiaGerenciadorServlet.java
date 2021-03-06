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

@WebServlet(name = "/NovaNoticiaGerenciadorServlet", urlPatterns = { "/manager/noticias/nova" })
public class NovaNoticiaGerenciadorServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	protected void doGet(
		HttpServletRequest request,
		HttpServletResponse response
	) throws ServletException, IOException {
		
		// Gera o html
		String titulo = Config.MANAGER_PAGE_TITLE + " - ";
		String html = generateHtml(titulo);
		
		// "Escreve" o html na tela
		PrintWriter writer = response.getWriter();
		writer.print(html);
	}
	
	@Override
	protected void doPost(
		HttpServletRequest request,
		HttpServletResponse response
	) throws ServletException, IOException {
		
		String titulo = request.getParameter("titulo");
		String descricao = request.getParameter("descricao");
		String texto = request.getParameter("texto");

		// Se os campos n�o estiverem vazios ou apenas com espa�os em branco
		if (!titulo.isBlank() && !descricao.isBlank() && !texto.isBlank()) {
			
			// Cria um bean com os dados
			Noticia noticia = new Noticia();
			noticia.setTitulo(titulo);
			noticia.setDescricao(descricao);
			noticia.setTexto(texto);
			
			// Conecta com o banco
			Connection conn = ConnectionFactory.getConnection();
			try {
				// Insere uma nova not�cia
				NoticiasDAO dao = new NoticiasDAOImpl(conn);
				dao.save(noticia);
			} catch (SQLException ex) {
				ex.printStackTrace();
			} finally {
				ConnectionFactory.closeConnection(conn);
			}
		}
		
		// Redireciona para a p�gina inicial do gerenciador
		response.sendRedirect(request.getContextPath() + "/manager/noticias");
	}

	private String generateHtml(String titulo) {
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
				"			<h2>Nova not�cia:</h2>\n" + 
				"			<form method=\"post\" action=\"nova\">\n" + 
				"				<div class=\"row\">\n" + 
				"					<div class=\"col-11\">\n" + 
				"						<label class=\"comment-field-lbl\" for=\"titulo\">T�tulo:</label>\n" + 
				"					</div>\n" + 
				"					<div class=\"col-89\">\n" + 
				"						<input name=\"titulo\" type=\"text\" placeholder=\"Digite o t�tulo da not�cia..\" />\n" + 
				"					</div>\n" + 
				"				</div>\n" + 
				"				<div class=\"row\">\n" + 
				"					<div class=\"col-11\">\n" + 
				"						<label class=\"comment-field-lbl\" for=\"descricao\">Descri��o:</label>\n" + 
				"					</div>\n" + 
				"					<div class=\"col-89\">\n" + 
				"						<textarea name=\"descricao\" placeholder=\"Descreva a not�cia em poucas palavras..\" style=\"height: 60px\"></textarea>\n" + 
				"					</div>\n" + 
				"				</div>\n" +
				"				<div class=\"row\">\n" + 
				"					<div class=\"col-11\">\n" + 
				"						<label class=\"comment-field-lbl\" for=\"texto\">Texto:</label>\n" + 
				"					</div>\n" + 
				"					<div class=\"col-89\">\n" + 
				"						<textarea name=\"texto\" placeholder=\"Aqui vai o texto da not�cia!\" style=\"height: 600px\"></textarea>\n" + 
				"					</div>\n" + 
				"				</div>\n" +
				"				<div class=\"row\">\n" + 
				"					<input class=\"btn comment-field-btn\" type=\"submit\" value=\"Salvar\" />\n" + 
				"				</div>\n" + 
				"			</form>\n" + 
				"		</div>\n" + 
				"		<div class=\"footer\">\n" + 
				"			<p>Copyright � 2020 - RealNews</p>\n" + 
				"		</div>\n" +
				"	</body>\n" + 
				"</html>";
		
		return html;
	}
}
