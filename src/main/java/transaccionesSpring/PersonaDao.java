package transaccionesSpring;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.sql.DataSource;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

public class PersonaDao {

	public void insertarPersona(int identificador) throws Exception {
		Connection conexion = null;
		Statement stmt = null;

		try {
			conexion = DataBase.getConexion();
			conexion.setAutoCommit(true);

			stmt = conexion.createStatement();

			stmt.executeUpdate("INSERT INTO PERSONA (ID, NOMBRE, APELLIDOS) VALUES (" + identificador
					+ ", 'Manuel Jesús', 'Martín Prieto')");
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			stmt.close();
			conexion.close();
		}
	}

	public void modificarPersona(int identificador, String apellidos) throws Exception {
		Connection conexion = null;
		Statement stmt = null;

		try {
			conexion = DataBase.getConexion();
			conexion.setAutoCommit(false);

			stmt = conexion.createStatement();

			stmt.executeUpdate("UPDATE PERSONA SET APELLIDOS='" + apellidos + "' WHERE ID=" + identificador);

			conexion.commit();
		} catch (Exception e) {
			conexion.rollback();
			e.printStackTrace();
			throw e;
		} finally {
			stmt.close();
			conexion.close();
		}
	}

	public void consultarPersona(int identificador) throws Exception {
		Connection conexion = null;
		Statement stmt = null;
		ResultSet result = null;

		try {
			conexion = DataBase.getConexion();
			stmt = conexion.createStatement();

			result = stmt.executeQuery("SELECT * FROM PERSONA WHERE ID=" + identificador);

			while (result.next()) {
				String id = result.getString("id");
				String nombre = result.getString("nombre");
				String apellidos = result.getString("apellidos");
				System.out.println(id + " " + nombre + " " + apellidos);
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			result.close();
			stmt.close();
			conexion.close();
		}
	}

	@Transactional
	public void insertarPersona2(int identificador, DataSource dataSource) {
		new JdbcTemplate(dataSource).execute("INSERT INTO PERSONA (ID, NOMBRE, APELLIDOS) VALUES (" + identificador
				+ ", 'Manuel Jesús', 'Martín Prieto')");
	}

	@Transactional(propagation = Propagation.NEVER)
	public void modificarPersona2(int identificador, DataSource dataSource) {
		new JdbcTemplate(dataSource).execute("UPDATE PERSONA SET APELLIDOS='Modificar2' WHERE ID=" + identificador);
	}

	public void crearPersona(int identificador, DataSource dataSource) {
		this.insertarPersona2(identificador, dataSource);
		this.modificarPersona2(identificador, dataSource);
	}

	public static void main(String[] args) {
		ApplicationContext contexto = new ClassPathXmlApplicationContext("spring-configuracion/config-spring.xml");
	}
}
