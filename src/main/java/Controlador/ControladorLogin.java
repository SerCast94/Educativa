package Controlador;

import Mapeo.Estudiantes;
import Mapeo.Profesores;
import Mapeo.Administradores;
import java.util.List;

public class ControladorLogin {
    /**
     * Comprobar el login
     * @param usuario es el usuario de la cuenta
     * @param password la contraseña ya cifrada de la cuenta
     * @return
     * 0 si las credenciales son incorrectas,
     * 1 si las credenciales son correctas y la cuenta es de un alumno, o
     * 2 si las credenciales son correctas y la cuenta es de un profesor
     * 3 si las credenciales son correctas y la cuenta es de un admin
     */
    public int comprobarLogin(String usuario, String password) {
        // Obtener lista de alumnos
        List<Estudiantes> listaAlumnos = Controlador.getListaEstudiantes();
        // Obtener lista de profesores
        List<Profesores> listaProfesores = Controlador.getListaProfesores();
        // Obtener lista de administradores
        List<Administradores> listaAdministradores = Controlador.getListaAdministradores();

        // Comprobar alumnos
        for (Estudiantes alumno : listaAlumnos) {
            if (alumno.getUsuario().equals(usuario) && alumno.getContrasena().equals(password)) {
                return 1;
            }
        }

        // Comprobar profesores
        for (Profesores profesor : listaProfesores) {
            if (profesor.getUsuario().equals(usuario) && profesor.getContrasena().equals(password)) {
                return 2;
            }
        }

        // Comprobar administradores
        for (Administradores admin : listaAdministradores) {
            if (admin.getUsuario().equals(usuario) && admin.getContrasena().equals(password)) {
                return 3;
            }
        }

        // Credenciales incorrectas
        return 0;
    }
}