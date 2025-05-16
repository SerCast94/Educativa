package Controlador;

import BackUtil.Encriptador;
import Mapeo.Estudiantes;
import Mapeo.Profesores;
import Mapeo.Tutores;
import Mapeo.Administradores;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

class ControladorLoginTest {

    ControladorLogin controladorLogin;

    @BeforeEach
    void setUp() {
        controladorLogin = new ControladorLogin();

        Estudiantes estudiantePrueba = new Estudiantes();
        estudiantePrueba.setUsuario("EstudiantePrueba");
        estudiantePrueba.setContrasena(Encriptador.encryptMD5("pass"));

        Profesores profesorPrueba = new Profesores();
        profesorPrueba.setUsuario("ProfesorPrueba");
        profesorPrueba.setContrasena(Encriptador.encryptMD5("profe123"));

        Tutores tutorPrueba = new Tutores();
        tutorPrueba.setUsuario("TutorPrueba");
        tutorPrueba.setContrasena(Encriptador.encryptMD5("tutor123"));

        Administradores adminPrueba = new Administradores();
        adminPrueba.setUsuario("AdminPrueba");
        adminPrueba.setContrasena(Encriptador.encryptMD5("admin123"));

        Controlador.listaEstudiantes = new ArrayList<>();
        Controlador.listaEstudiantes.add(estudiantePrueba);

        Controlador.listaProfesores = new ArrayList<>();
        Controlador.listaProfesores.add(profesorPrueba);

        Controlador.listaTutores = new ArrayList<>();
        Controlador.listaTutores.add(tutorPrueba);

        Controlador.listaAdministradores = new ArrayList<>();
        Controlador.listaAdministradores.add(adminPrueba);
    }

    @Test
    void comprobarLoginConCredencialesCorrectasEstudiante() {
        String usuario = "EstudiantePrueba";
        String contrasena = "pass";
        String contrasenaCifrada = Encriptador.encryptMD5(contrasena);

        int resultado = controladorLogin.comprobarLogin(usuario, contrasenaCifrada);
        assertEquals(1, resultado);
    }

    @Test
    void comprobarLoginConCredencialesCorrectasProfesor() {
        String usuario = "ProfesorPrueba";
        String contrasena = "profe123";
        String contrasenaCifrada = Encriptador.encryptMD5(contrasena);

        int resultado = controladorLogin.comprobarLogin(usuario, contrasenaCifrada);
        assertEquals(2, resultado);
    }

    @Test
    void comprobarLoginConCredencialesCorrectasTutor() {
        String usuario = "TutorPrueba";
        String contrasena = "tutor123";
        String contrasenaCifrada = Encriptador.encryptMD5(contrasena);

        int resultado = controladorLogin.comprobarLogin(usuario, contrasenaCifrada);
        assertEquals(4, resultado);
    }

    @Test
    void comprobarLoginConCredencialesCorrectasAdmin() {
        String usuario = "AdminPrueba";
        String contrasena = "admin123";
        String contrasenaCifrada = Encriptador.encryptMD5(contrasena);

        int resultado = controladorLogin.comprobarLogin(usuario, contrasenaCifrada);
        assertEquals(3, resultado);
    }

    @Test
    void comprobarLoginConCredencialesIncorrectas() {
        String usuario = "UsuarioFalso";
        String contrasena = "noexiste";
        String contrasenaCifrada = Encriptador.encryptMD5(contrasena);

        int resultado = controladorLogin.comprobarLogin(usuario, contrasenaCifrada);
        assertEquals(0, resultado);
    }
}
