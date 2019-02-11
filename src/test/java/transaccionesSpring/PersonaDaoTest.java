package transaccionesSpring;

import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:spring-configuracion/config-spring.xml" })
@TransactionConfiguration(transactionManager = "dualTransactionManager")
public class PersonaDaoTest {

	@Autowired
	private ApplicationContext contexto;

	@Test
	public void deberiaInsertarUnaPersona() {
		try {
			PersonaDao dao = new PersonaDao();
			dao.insertarPersona(0);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void deberiaModificarUnaPersona() {
		try {
			PersonaDao dao = new PersonaDao();
			dao.modificarPersona(0, "Actualizado1 Actualizado1");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void deberiaConsultarUnaPersona() {
		try {
			PersonaDao dao = new PersonaDao();
			dao.consultarPersona(0);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void deberiaInsertarUnaPersona2() {
		try {
			PersonaDao dao = new PersonaDao();
			dao.insertarPersona2(0, (DataSource) contexto.getBean("dualDataSource"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void deberiaCrearUnaPersona() {
		try {
			PersonaDao dao = new PersonaDao();
			dao.crearPersona(10, (DataSource) contexto.getBean("dualDataSource"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
