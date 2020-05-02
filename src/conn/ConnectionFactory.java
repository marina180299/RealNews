package conn;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

	private static final String HOST = "localhost";
	private static final String PORT = "3306";
	
	private static final String USER = "realnews";
	private static final String PASS = "realnews123";
	
	private static final String DB = "portal_realnews";
	
	private static final String URL = "jdbc:mysql://" + HOST + ":" + PORT + "/" + DB +
            						  "?useTimezone=true&serverTimezone=UTC";
	
	public static Connection getConnection() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			return DriverManager.getConnection(URL, USER, PASS);
		} catch (Exception ex) {
			throw new RuntimeException("Erro na conexão com o banco! " + ex);
		}
	}
	
	public static void closeConnection(Connection conn) {
		if (conn == null) return;
		try {
			conn.close();
		} catch (SQLException ex) {
			throw new RuntimeException(ex);
		}
	}
	
}
