package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.bean.Comentario;

public class ComentarioDAOImpl implements ComentarioDAO {
	
	private static final String INSERT     = "INSERT INTO comentario (nome, texto, fk_noticia_id) " +
											 "VALUES (?,?,?)";
	private static final String DELETE_ALL = "DELETE FROM comentario WHERE fk_noticia_id = ?";
	private static final String SELECT_ALL = "SELECT * FROM comentario WHERE fk_noticia_id = ?";
	
	private Connection conn;
	
	public ComentarioDAOImpl(Connection conn) {
		this.conn = conn;
	}

	@Override
	public void save(int noticiaId, Comentario comentario) throws SQLException {
		PreparedStatement stmt = conn.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);

	    // Insere os dados do comentario
	    stmt.setString(1, comentario.getNome());
	    stmt.setString(2, comentario.getTexto());
	    stmt.setInt(3, noticiaId);
	
	    // Faz a inserção no banco
	    stmt.execute();
	
	    // Salva o id gerado
	    int id = Comentario.ID_DESCONHECIDO;
	    ResultSet keys = stmt.getGeneratedKeys();
	    if (keys.next()) id =  keys.getInt(1);
	    comentario.setId(id);
	
	    // Encerra tudo
	    keys.close();
	    stmt.close();
	}

	@Override
	public void deleteAll(int noticiaId) throws SQLException {
		PreparedStatement stmt = conn.prepareStatement(DELETE_ALL);

        // Define o id da noticia a qual os comentários pertencem
        stmt.setInt(1, noticiaId);

        // Deleta do banco
        stmt.execute();
        stmt.close();
	}

	@Override
	public List<Comentario> get(int noticiaId) throws SQLException {
		PreparedStatement stmt = conn.prepareStatement(SELECT_ALL);
	
	    // Define o id da noticia
	    stmt.setInt(1, noticiaId);
	
	    // Pega todas os comentários pertencentes a noticia
	    ResultSet rs = stmt.executeQuery();
	
	    // Itera por todos os comentários retornados
	    List<Comentario> comentarios = new ArrayList<>();
	    while (rs.next()) {
	        // Pega as informações do comentário
	        int id = rs.getInt("id");
	        String nome = rs.getString("nome");
	        String texto = rs.getString("texto");
	
	        // Cria um bean com os dados
	        Comentario comentario = new Comentario();
	        comentario.setId(id);
	        comentario.setNome(nome);
	        comentario.setTexto(texto);
	        
	        // Adiciona o comentário à lista
	        comentarios.add(comentario);
	    }
	
	    rs.close();
	    stmt.close();
	
	    // Retorna a lista
	    return comentarios;
	}
}
