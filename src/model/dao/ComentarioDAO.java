package model.dao;

import java.sql.SQLException;
import java.util.List;

import model.bean.Comentario;

public interface ComentarioDAO {
	/**
     * Salva um coment�rio no banco.
     * @param noticiaId Id da noticia ao qual o coment�rio pertence.
     * @param comentario Coment�rio a ser salvo.
     * @throws SQLException Exce��o lan�ada caso haja algum erro na inser��o.
     */
    void save(int noticiaId, Comentario comentario) throws SQLException;

    /**
     * Deleta todos os coment�rios de uma noticia.
     * @param noticiaId Id da noticia que ter� seus coment�rios exclu�dos.
     * @throws SQLException Exce��o lan�ada caso haja algum erro na exclus�o.
     */
    void deleteAll(int noticiaId) throws SQLException;

    /**
     * Retorna todos os coment�rios de uma noticia.
     * @param noticiaId Id da noticia, que ter� seus coment�rios retornados.
     * @throws SQLException Exce��o lan�ada caso haja algum erro na obten��o.
     */
    List<Comentario> get(int noticiaId) throws SQLException;
}
