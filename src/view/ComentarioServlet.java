package view;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import conn.ConnectionFactory;
import model.bean.Comentario;
import model.dao.ComentarioDAO;
import model.dao.ComentarioDAOImpl;

@WebServlet(name = "/ComentarioServlet", urlPatterns = { "/comentario" })
public class ComentarioServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	protected void doPost(
		HttpServletRequest request,
		HttpServletResponse response
	) throws ServletException, IOException {

		int noticiaId = Integer.parseInt(request.getParameter("noticia_id"));
		String nome = request.getParameter("nome");
		String texto = request.getParameter("texto");
		
		// Se os campos n�o estiverem vazios ou apenas com espa�os em branco
		if (!nome.isBlank() && !texto.isBlank()) {
			
			// Cria um bean com os dados
			Comentario comentario = new Comentario();
			comentario.setNome(nome);
			comentario.setTexto(texto);
			
			// Conecta com o banco
			Connection conn = ConnectionFactory.getConnection();
			try {
				// Insere um novo coment�rio
				ComentarioDAO dao = new ComentarioDAOImpl(conn);
				dao.save(noticiaId, comentario);
			} catch (SQLException ex) {
				ex.printStackTrace();
			} finally {
				ConnectionFactory.closeConnection(conn);
			}
		}
		
		// Redireciona para a noticia aberta anteriormente
		response.sendRedirect(request.getContextPath() + "/noticia?id=" + noticiaId);
	}

}
