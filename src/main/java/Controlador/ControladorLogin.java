package Controlador;

import Mapeo.Estudiantes;
import Mapeo.Profesores;
import Mapeo.Administradores;
import Mapeo.Tutores;

import java.util.List;

public class ControladorLogin {

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
                return 1;
            }
        }

        // Obtener lista de profesores
        List<Profesores> listaProfesores = Controlador.getListaProfesores();
        // Comprobar profesores
        for (Profesores profesor : listaProfesores) {
            if (profesor.getUsuario().equals(usuario) && profesor.getContrasena().equals(password)) {
                profesorLogeado = profesor;
                return 2;
            }
        }

        // Obtener lista de administradores
        List<Administradores> listaAdministradores = Controlador.getListaAdministradores();
        // Comprobar administradores
        for (Administradores admin : listaAdministradores) {
            if (admin.getUsuario().equals(usuario) && admin.getContrasena().equals(password)) {
                adminLogeado = admin;
                return 3;
            }
        }

        // Obtener lista de tutores
        List<Tutores> listaTutores = Controlador.getListaTutores();
        // Comprobar tutores
        for (Tutores tutor : listaTutores) {
            if (tutor.getUsuario().equals(usuario) && tutor.getContrasena().equals(password)) {
                tutorLogeado = tutor;
                return 4;
            }
        }

        // Credenciales incorrectas
        return 0;
    }


    public Estudiantes getEstudianteLogeado() {
        return estudianteLogeado;
    }

    public static void setEstudianteLogeado(Estudiantes estudiante) {
        estudianteLogeado = estudiante;
    }

    public Profesores getProfesorLogeado() {
        return profesorLogeado;
    }

    public void setProfesorLogeado(Profesores profesorLogeado) {
        this.profesorLogeado = profesorLogeado;
    }

    public Administradores getAdminLogeado() {
        return adminLogeado;
    }

    public void setAdminLogeado(Administradores adminLogeado) {
        this.adminLogeado = adminLogeado;
    }

    public Tutores getTutorLogeado() {
        return tutorLogeado;
    }

    public void setTutorLogeado(Tutores tutorLogeado) {
        this.tutorLogeado = tutorLogeado;
    }
}