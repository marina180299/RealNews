package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.bean.Comentario;
import model.bean.Noticia;

public class NoticiasDAOImpl implements NoticiasDAO {
	
	private static final String INSERT     = "INSERT INTO noticia (titulo, descricao, texto) VALUES (?,?,?)";
	private static final String UPDATE     = "UPDATE noticia SET titulo = ?, descricao = ?, texto = ? " +
											 "WHERE id = ?";
	private static final String DELETE     = "DELETE FROM noticia WHERE id = ?";
	private static final String SELECT	   = "SELECT * FROM noticia WHERE id = ?";
	private static final String SELECT_ALL = "SELECT * FROM noticia";
	
	private Connection conn;
	
	public NoticiasDAOImpl(Connection conn) {
		this.conn = conn;
	}

	@Override
	public void save(Noticia noticia) throws SQLException {
		// O segundo parâmetro faz com que seja retornado o id gerado
        PreparedStatement stmt = conn.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);

        // Insere os dados da noticia
        stmt.setString(1, noticia.getTitulo());
        stmt.setString(2, noticia.getDescricao());
        stmt.setString(3, noticia.getTexto());

        // Faz a inserção no banco
        stmt.execute();

        // Salva o id gerado
        int id = Noticia.ID_DESCONHECIDO;
        ResultSet keys = stmt.getGeneratedKeys();
        if (keys.next()) id =  keys.getInt(1);
        noticia.setId(id);

        // Encerra tudo
        keys.close();
        stmt.close();
	}

	@Override
	public void update(Noticia noticia) throws SQLException {
        PreparedStatement stmt = conn.prepareStatement(UPDATE);

        // Atualiza o titulo, descricao e texto
        stmt.setString(1, noticia.getTitulo());
        stmt.setString(2, noticia.getDescricao());
        stmt.setString(3, noticia.getTexto());


        // Define o id da noticia que deverá ser atualizada
        stmt.setInt(4, noticia.getId());

        // Atualiza os dados no banco
        stmt.executeUpdate();
        stmt.close();
	}

	@Override
	public void delete(int noticiaId) throws SQLException {
        // É necessário excluir todos os comentários da noticia antes
        ComentarioDAO dao = new ComentarioDAOImpl(conn);
        dao.deleteAll(noticiaId);

        // Assim, é possível excluir a noticia do banco
        PreparedStatement stmt = conn.prepareStatement(DELETE);

        // Define o id da noticia que deverá ser deletada
        stmt.setInt(1, noticiaId);

        // Deleta do banco
        stmt.execute();
        stmt.close();
		
	}

	@Override
	public Noticia get(int noticiaId) throws SQLException {
		PreparedStatement stmt = conn.prepareStatement(SELECT);
		
		// Define o id da noticia a ser retornada
		stmt.setInt(1, noticiaId);
		
		// Tenta pegar o projeto no banco
		ResultSet rs = stmt.executeQuery();
		
		// Se houver algum resultado
		if (rs.next()) {
			// Pega as informações da noticia
			int id = rs.getInt("id");
			String titulo = rs.getString("titulo");
			String descricao = rs.getString("descricao");
			String texto = rs.getString("texto");
			
			// Já podemos encerrar os objetos usados para a consulta
			rs.close();
			stmt.close();
			
			// Cria um bean com os dados
			Noticia noticia = new Noticia();
			noticia.setId(id);
			noticia.setTitulo(titulo);
			noticia.setDescricao(descricao);
			noticia.setTexto(texto);

			// Tenta pegar os comentários da noticia
			ComentarioDAO dao = new ComentarioDAOImpl(conn);
			List<Comentario> comentarios = dao.get(id); 
			noticia.setComentarios(comentarios);
			
			// E então retorna a noticia
			return noticia;
		}
		
		rs.close();
		stmt.close();
		
		// Se o fluxo de execução chegar a esse ponto, nenhuma noticia foi encontrada
        throw new SQLException("Noticia não encontrada!");
	}

	@Override
	public List<Noticia> getAll() throws SQLException {
		PreparedStatement stmt = conn.prepareStatement(SELECT_ALL);
		
		// Pega todos as noticias no banco
		ResultSet rs = stmt.executeQuery();
		
		// Itera por tudo que foi retornado
		List<Noticia> noticias = new ArrayList<>();
		while (rs.next()) {
			// Pega as informações da noticia
			int id = rs.getInt("id");
			String titulo = rs.getString("titulo");
			String descricao = rs.getString("descricao");
			String texto = rs.getString("texto");
			
			// Cria um bean com os dados
			Noticia noticia = new Noticia();
			noticia.setId(id);
			noticia.setTitulo(titulo);
			noticia.setDescricao(descricao);
			noticia.setTexto(texto);
			noticia.setComentarios(new ArrayList<>());
			
			// E então adiciona a lista
			noticias.add(noticia);
		}
		
		return noticias;
	}
}
