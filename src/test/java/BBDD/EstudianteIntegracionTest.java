package BBDD;

import Mapeo.Estudiantes;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.jupiter.api.*;
import java.sql.Date;
import static org.junit.jupiter.api.Assertions.*;

public class EstudianteIntegracionTest {

    private static SessionFactory sessionFactory;
    private Session sesion;

    @BeforeAll
    static void setUp() {
        sessionFactory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Estudiantes.class).buildSessionFactory();
    }

    @BeforeEach
    void openSession() {
        sesion = sessionFactory.openSession();
        sesion.beginTransaction();
    }

    @AfterEach
    void closeSession() {
        sesion.getTransaction().commit();
        sesion.close();
    }

    @AfterAll
    static void tearDown() {
        sessionFactory.close();
    }


    @Test
    void testGuardarYBorrarEstudiante() {
        String usuarioUnico = "usuario_" + System.currentTimeMillis();
        Estudiantes estudiante = new Estudiantes(
                "Juan", "Pérez", "12345678Z", Date.valueOf("2000-01-01"), "Calle Falsa 123",
                "600111222", "juan@test.com", Date.valueOf("2023-09-01"), null, usuarioUnico,
                "123456", Estudiantes.EstadoEstudiante.activo
        );

        sesion.save(estudiante);
        sesion.flush();
        sesion.clear();

        Estudiantes resultado = sesion.get(Estudiantes.class, estudiante.getIdEstudiante());
        assertNotNull(resultado);
        assertEquals("Juan", resultado.getNombre());

        sesion.delete(resultado);
        sesion.flush();
        sesion.clear();

        Estudiantes estudianteEliminado = sesion.get(Estudiantes.class, estudiante.getIdEstudiante());
        assertNull(estudianteEliminado);
    }
}
