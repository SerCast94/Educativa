package BBDD;

import Controlador.Controlador;
import org.hibernate.Session;
import org.hibernate.query.Query;
import java.time.LocalDate;
import java.util.List;
import Mapeo.*;

/**
 * Clase que contiene métodos para realizar consultas a la base de datos.
 */

public class Consultas {

    /**
     * Atributo de la clase que representa la sesión de Hibernate.
     */

    private static Session session = Controlador.getSession();

    /**
     * Constructor de la clase Consultas.
     *
     * @param session La sesión de Hibernate.
     */
    public Consultas(Session session) {
        this.session = Controlador.getSession();
    }


    // CONSULTAS PARA OBTENER TODOS LOS REGISTROS DE CADA TABLA

    /**
     * Método para obtener todos los tutores de la base de datos.
     *
     * @return Lista de objetos Tutores.
     */
    public static List<Tutores> selectTutores() {
        Query<Tutores> query = session.createQuery("FROM Tutores", Tutores.class);
        return query.getResultList();
    }

    /**
     * Método para obtener todos los profesores de la base de datos.
     *
     * @return Lista de objetos Profesores.
     */
    public static List<Profesores> selectProfesores() {
        Query<Profesores> query = session.createQuery("FROM Profesores", Profesores.class);
        return query.getResultList();
    }

    /**
     * Método para obtener todos los extraescolares de la base de datos.
     *
     * @return Lista de objetos Extraescolares.
     */
    public static List<Extraescolares> selectExtraescolares() {
        Query<Extraescolares> query = session.createQuery("FROM Extraescolares", Extraescolares.class);
        return query.getResultList();
    }

    /**
     * Método para obtener todos los cursos de la base de datos.
     *
     * @return Lista de objetos Cursos.
     */
    public static List<Cursos> selectCursos() {
        Query<Cursos> query = session.createQuery("FROM Cursos", Cursos.class);
        return query.getResultList();
    }

    /**
     * Método para obtener todos los asignaturas de la base de datos.
     *
     * @return Lista de objetos Asignaturas.
     */
    public static List<Asignaturas> selectAsignaturas() {
        Query<Asignaturas> query = session.createQuery("FROM Asignaturas", Asignaturas.class);
        return query.getResultList();
    }

    /**
     * Método para obtener la relacion de cursos y asignaturas de la base de datos.
     *
     * @return Lista de objetos Grupos.
     */
    public static List<CursosAsignaturas> selectCursosAsignaturas() {
        Query<CursosAsignaturas> query = session.createQuery("FROM CursosAsignaturas", CursosAsignaturas.class);
        return query.getResultList();
    }

    /**
     * Método para obtener todos los estudiantes de la base de datos.
     *
     * @return Lista de objetos Estudiantes.
     */
    public static List<Estudiantes> selectEstudiantes() {
        Query<Estudiantes> query = session.createQuery("FROM Estudiantes", Estudiantes.class);
        return query.getResultList();
    }

    /**
     * Método para obtener todos los matriculas de la base de datos.
     *
     * @return Lista de objetos Matriculas.
     */
    public static List<Matriculas> selectMatriculas() {
        Query<Matriculas> query = session.createQuery("FROM Matriculas", Matriculas.class);
        return query.getResultList();
    }

    /**
     * Método para obtener todos los historial academico de la base de datos.
     *
     * @return Lista de objetos HistorialAcademico.
     */
    public static List<HistorialAcademico> selectHistorialAcademico() {
        Query<HistorialAcademico> query = session.createQuery("FROM HistorialAcademico", HistorialAcademico.class);
        return query.getResultList();
    }

    /**
     * Método para obtener todos los asistencia de la base de datos.
     *
     * @return Lista de objetos Asistencia.
     */
    public static List<Asistencia> selectAsistencia() {
        Query<Asistencia> query = session.createQuery("FROM Asistencia", Asistencia.class);
        return query.getResultList();
    }

    /**
     * Método para obtener todos los eventos de la base de datos.
     *
     * @return Lista de objetos Eventos.
     */
    public static List<Eventos> selectEventos() {
        Query<Eventos> query = session.createQuery("FROM Eventos", Eventos.class);
        return query.getResultList();
    }

    /**
     * Método para obtener todos los estudiantes eventos de la base de datos.
     *
     * @return Lista de objetos EstudiantesEventos.
     */
    public static List<EstudiantesEventos> selectEstudiantesEventos() {
        Query<EstudiantesEventos> query = session.createQuery("FROM EstudiantesEventos", EstudiantesEventos.class);
        return query.getResultList();
    }

    /**
     * Método para obtener todos los horarios de la base de datos.
     *
     * @return Lista de objetos Horarios.
     */
    public static List<Horarios> selectHorarios() {
        Query<Horarios> query = session.createQuery("FROM Horarios", Horarios.class);
        return query.getResultList();
    }

    /**
     * Método para obtener todos los becas de la base de datos.
     *
     * @return Lista de objetos Becas.
     */
    public static List<Becas> selectBecas() {
        Query<Becas> query = session.createQuery("FROM Becas", Becas.class);
        return query.getResultList();
    }

    /**
     * Método para obtener todos los convalidaciones de la base de datos.
     *
     * @return Lista de objetos Convalidaciones.
     */
    public static List<Convalidaciones> selectConvalidaciones() {
        Query<Convalidaciones> query = session.createQuery("FROM Convalidaciones", Convalidaciones.class);
        return query.getResultList();
    }

    /**
     * Método para obtener todos los administradores de la base de datos.
     *
     * @return Lista de objetos Administradores.
     */
    public static List<Administradores> selectAdministradores() {
        Query<Administradores> query = session.createQuery("FROM Administradores", Administradores.class);
        return query.getResultList();
    }

    /**
     * Método para obtener todos los extraescolares por fecha de la base de datos.
     *
     * @param fecha La fecha para filtrar los extraescolares.
     * @return Lista de objetos Extraescolares filtrados por fecha.
     */
    public List<Extraescolares> obtenerExtraescolaresPorFecha(LocalDate fecha) {
        String fechaStr = fecha.toString();

        Query<Extraescolares> query = session.createQuery("SELECT e FROM Extraescolares e WHERE e.fechaReserva = :fecha", Extraescolares.class);
        query.setParameter("fecha", fechaStr);

        return query.getResultList();
    }
}

