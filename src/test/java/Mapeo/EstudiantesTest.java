package Mapeo;

import Mapeo.Estudiantes.EstadoEstudiante;
import org.junit.jupiter.api.Test;
import java.sql.Date;
import static org.junit.jupiter.api.Assertions.*;

public class EstudiantesTest {

    @Test
    public void testValidarDNI() {
        assertTrue(Estudiantes.validarDNI("12345678Z"));
        assertFalse(Estudiantes.validarDNI("12345678A"));
        assertFalse(Estudiantes.validarDNI("1234"));
        assertFalse(Estudiantes.validarDNI(null));
    }

    @Test
    public void testValidarEmail() {
        assertTrue(Estudiantes.validarEmail("correo@test.com"));
        assertFalse(Estudiantes.validarEmail("correo@"));
        assertFalse(Estudiantes.validarEmail("correo.com"));
        assertFalse(Estudiantes.validarEmail(null));
    }

    @Test
    public void testValidarTelefono() {
        assertTrue(Estudiantes.validarTelefono("+34600111222"));
        assertTrue(Estudiantes.validarTelefono("600111222"));
        assertFalse(Estudiantes.validarTelefono("123abc"));
        assertFalse(Estudiantes.validarTelefono(null));
    }

    @Test
    public void testValidarContrasena() {
        assertTrue(Estudiantes.validarContrasena("abcdef"));
        assertFalse(Estudiantes.validarContrasena("abc"));
        assertFalse(Estudiantes.validarContrasena(null));
    }

    @Test
    public void testConstructorCompletoYToString() {
        Date nacimiento = Date.valueOf("2000-01-01");
        Date matricula = Date.valueOf("2023-09-01");

        Estudiantes estudiante = new Estudiantes(
                "Juan", "Pérez", "12345678Z", nacimiento, "Calle Falsa 123",
                "600111222", "juan@test.com", matricula, null, "juanp",
                "123456", EstadoEstudiante.activo
        );

        assertEquals("Juan", estudiante.getNombre());
        assertEquals("Pérez", estudiante.getApellido());
        assertEquals("Juan Pérez", estudiante.toString());
    }

}