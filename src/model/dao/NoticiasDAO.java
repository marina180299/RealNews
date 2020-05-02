package model.dao;

import java.sql.SQLException;
import java.util.List;

import model.bean.Noticia;

public interface NoticiasDAO {
	/**
     * Salva uma noticia no banco.
     * @param noticia Noticia a ser salva.
     * @throws SQLException Exce��o lan�ada caso haja algum erro na inser��o.
     */
    void save(Noticia noticia) throws SQLException;

    /**
     * Altera uma noticia.
     * @param noticia Noticia a ser alterada, j� com os dados alterados.
     * @throws SQLException Exce��o lan�ada caso haja algum erro na altera��o.
     */
    void update(Noticia noticia) throws SQLException;

    /**
     * Deleta uma noticia do banco.
     * @param noticiaId Id da noticia a ser deletada.
     * @throws SQLException Exce��o lan�ada caso haja algum erro na exclus�o.
     */
    void delete(int noticiaId) throws SQLException;

    /**
     * Retorna todos os dados da noticia com id especificado.
     * @param noticiaId Id da noticia que dever� ser retornada.
     * @throws SQLException Exce��o lan�ada caso haja algum erro na obten��o.
     */
    Noticia get(int noticiaId) throws SQLException;

    /**
     * Retorna todas as noticias inseridas no banco.
     * @throws SQLException Exce��o lan�ada caso haja algum erro na obten��o.
     */
    List<Noticia> getAll() throws SQLException;
}
