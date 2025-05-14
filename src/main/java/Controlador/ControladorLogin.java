package Controlador;

import Mapeo.Estudiantes;
import Mapeo.Profesores;
import Mapeo.Administradores;
import Mapeo.Tutores;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Controlador para el login y la gestión de usuarios en la aplicación.
 * Este controlador se encarga de verificar las credenciales de los usuarios
 * y de almacenar la información del usuario logueado.
 */
public class ControladorLogin {

    private static final Logger logger = LoggerFactory.getLogger(ControladorLogin.class);
    public static Estudiantes estudianteLogeado;
    public static Profesores profesorLogeado;
    public static Administradores adminLogeado;
    public static Tutores tutorLogeado;

    /**
     * Comprobar el login
     * @param usuario es el usuario de la cuenta
     * @param password la contraseña ya cifrada de la cuenta
     * @return
     * 0 si las credenciales son incorrectas,
     * 1 si las credenciales son correctas y la cuenta es de un estudiante
     * 2 si las credenciales son correctas y la cuenta es de un profesor
     * 3 si las credenciales son correctas y la cuenta es de un admin
     * 4 si las credenciales son correctas y la cuenta es de un tutor
     */
    public int comprobarLogin(String usuario, String password) {

        // Obtener lista de alumnos
        List<Estudiantes> listaEstudiantes = Controlador.getListaEstudiantes();
        // Comprobar alumnos
        for (Estudiantes estudiante : listaEstudiantes) {
            if (estudiante.getUsuario().equals(usuario) && estudiante.getContrasena().equals(password)) {
                estudianteLogeado = estudiante;
                logger.info("Estudiante Logeado: " + estudianteLogeado);
                return 1;
            }
        }

        // Obtener lista de profesores
        List<Profesores> listaProfesores = Controlador.getListaProfesores();
        // Comprobar profesores
        for (Profesores profesor : listaProfesores) {
            if (profesor.getUsuario().equals(usuario) && profesor.getContrasena().equals(password)) {
                profesorLogeado = profesor;
                logger.info("Profesor Logeado: " + profesorLogeado);
                return 2;
            }
        }

        // Obtener lista de administradores
        List<Administradores> listaAdministradores = Controlador.getListaAdministradores();
        // Comprobar administradores
        for (Administradores admin : listaAdministradores) {
            if (admin.getUsuario().equals(usuario) && admin.getContrasena().equals(password)) {
                adminLogeado = admin;
                logger.info("Administrador Logeado: " + adminLogeado);
                return 3;
            }
        }

        // Obtener lista de tutores
        List<Tutores> listaTutores = Controlador.getListaTutores();
        // Comprobar tutores
        for (Tutores tutor : listaTutores) {
            if (tutor.getUsuario().equals(usuario) && tutor.getContrasena().equals(password)) {
                tutorLogeado = tutor;
                logger.info("Tutor Logeado: " + tutorLogeado);
                return 4;
            }
        }

        // Credenciales incorrectas
        logger.warn("Credenciales incorrectas para el usuario: " + usuario);
        return 0;
    }


    /**
     * Obtener el estudiante logueado
     * @return el estudiante logueado
     */
    public Estudiantes getEstudianteLogeado() {
        return estudianteLogeado;
    }

    /**
     * Establecer el estudiante logueado
     * @param estudiante el estudiante logueado
     */
    public static void setEstudianteLogeado(Estudiantes estudiante) {
        estudianteLogeado = estudiante;
    }

    /**
     * Obtener el profesor logueado
     * @return el profesor logueado
     */
    public Profesores getProfesorLogeado() {
        return profesorLogeado;
    }

    /**
     * Establecer el profesor logueado
     * @param profesorLogeado el profesor logueado
     */
    public void setProfesorLogeado(Profesores profesorLogeado) {
        this.profesorLogeado = profesorLogeado;
    }

    /**
     * Obtener el administrador logueado
     * @return el administrador logueado
     */
    public Administradores getAdminLogeado() {
        return adminLogeado;
    }

    /**
     * Establecer el administrador logueado
     * @param adminLogeado el administrador logueado
     */
    public void setAdminLogeado(Administradores adminLogeado) {
        this.adminLogeado = adminLogeado;
    }

    /**
     * Obtener el tutor logueado
     * @return el tutor logueado
     */
    public Tutores getTutorLogeado() {
        return tutorLogeado;
    }

    /**
     * Establecer el tutor logueado
     * @param tutorLogeado el tutor logueado
     */
    public void setTutorLogeado(Tutores tutorLogeado) {
        this.tutorLogeado = tutorLogeado;
    }
}