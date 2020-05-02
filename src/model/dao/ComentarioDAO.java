package model.dao;

import java.sql.SQLException;
import java.util.List;

import model.bean.Comentario;

public interface ComentarioDAO {
	/**
     * Salva um comentário no banco.
     * @param noticiaId Id da noticia ao qual o comentário pertence.
     * @param comentario Comentário a ser salvo.
     * @throws SQLException Exceção lançada caso haja algum erro na inserção.
     */
    void save(int noticiaId, Comentario comentario) throws SQLException;

    /**
     * Deleta todos os comentários de uma noticia.
     * @param noticiaId Id da noticia que terá seus comentários excluídos.
     * @throws SQLException Exceção lançada caso haja algum erro na exclusão.
     */
    void deleteAll(int noticiaId) throws SQLException;

    /**
     * Retorna todos os comentários de uma noticia.
     * @param noticiaId Id da noticia, que terá seus comentários retornados.
     * @throws SQLException Exceção lançada caso haja algum erro na obtenção.
     */
    List<Comentario> get(int noticiaId) throws SQLException;
}
