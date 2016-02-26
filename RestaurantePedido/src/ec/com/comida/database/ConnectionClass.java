package ec.com.comida.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionClass {
	// private static String driver;
	// private static String userName;
	// private static String password;
	// private static String connectionUrl;
	// private static String database;

	public static Connection getConnection() {

		String url = "jdbc:postgresql://localhost:5432/restaurante";
		Properties props = new Properties();
		props.setProperty("user", "resto");
		props.setProperty("password", "resto2015");
		// props.setProperty("ssl", "true");
		// props.setProperty("currentSchema", "RESTOSCH");
		Connection conn = null;
		try {
			Class.forName("org.postgresql.Driver");
			conn = DriverManager.getConnection(url, props);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// driver = "com.mysql.jdbc.Driver";
		// userName = "resto";
		// password = "resto2015";
		// connectionUrl = "jdbc:mysql://localhost:5432/";
		// database = "restaurante";
		// Connection connection = null;
		// try {
		// Class.forName(driver);
		// connection = DriverManager.getConnection(connectionUrl + database,
		// userName, password);
		// return connection;
		// } catch (ClassNotFoundException e) {
		// e.printStackTrace();
		// } catch (SQLException e) {
		// e.printStackTrace();
		// } catch (Exception e) {
		// e.printStackTrace();
		// }
		return conn;
	}
}
