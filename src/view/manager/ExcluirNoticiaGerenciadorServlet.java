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

@WebServlet(name = "/ExcluirNoticiaGerenciadorServlet", urlPatterns = { "/manager/noticias/excluir" })
public class ExcluirNoticiaGerenciadorServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(
		HttpServletRequest request,
		HttpServletResponse response
	) throws ServletException, IOException {
		
		int id = Integer.parseInt(request.getParameter("id"));

		// Conecta com o banco
		Connection conn = ConnectionFactory.getConnection();
		try {
			// Deleta a notícia do banco
			NoticiasDAO dao = new NoticiasDAOImpl(conn);
			dao.delete(id);
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			ConnectionFactory.closeConnection(conn);
		}
		
		// Redireciona para a página inicial do gerenciador
		response.sendRedirect(request.getContextPath() + "/manager/noticias");
	}
}
