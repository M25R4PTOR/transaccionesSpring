package transaccionesSpring;

import java.sql.Connection;
import java.sql.DriverManager;

public class DataBase {

	private static String URL = "jdbc:mysql://localhost:3306/transacciones";
	private static String USER = "root";
	private static String PASS = "";

	public static Connection getConexion() {
		try {
			try {
				// Se registra el Driver de MySQL
				Class.forName("com.mysql.cj.jdbc.Driver");
			} catch (ClassNotFoundException ex) {
				System.out.println("Error al registrar el driver de MySQL: " + ex);
			}
			Connection connection = null;

			connection = DriverManager.getConnection(URL, USER, PASS);

			boolean valid = connection.isValid(5000);
			System.out.println(valid ? "TEST OK" : "TEST FAIL");

			return connection;

		} catch (java.sql.SQLException sqle) {
			System.out.println("Error: " + sqle);
		}

		return null;
	}
}
