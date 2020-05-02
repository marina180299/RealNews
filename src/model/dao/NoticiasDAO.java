package model.dao;

import java.sql.SQLException;
import java.util.List;

import model.bean.Noticia;

public interface NoticiasDAO {
	/**
     * Salva uma noticia no banco.
     * @param noticia Noticia a ser salva.
     * @throws SQLException Exceção lançada caso haja algum erro na inserção.
     */
    void save(Noticia noticia) throws SQLException;

    /**
     * Altera uma noticia.
     * @param noticia Noticia a ser alterada, já com os dados alterados.
     * @throws SQLException Exceção lançada caso haja algum erro na alteração.
     */
    void update(Noticia noticia) throws SQLException;

    /**
     * Deleta uma noticia do banco.
     * @param noticiaId Id da noticia a ser deletada.
     * @throws SQLException Exceção lançada caso haja algum erro na exclusão.
     */
    void delete(int noticiaId) throws SQLException;

    /**
     * Retorna todos os dados da noticia com id especificado.
     * @param noticiaId Id da noticia que deverá ser retornada.
     * @throws SQLException Exceção lançada caso haja algum erro na obtenção.
     */
    Noticia get(int noticiaId) throws SQLException;

    /**
     * Retorna todas as noticias inseridas no banco.
     * @throws SQLException Exceção lançada caso haja algum erro na obtenção.
     */
    List<Noticia> getAll() throws SQLException;
}
