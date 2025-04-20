package BBDD;

import Controlador.Controlador;
import org.hibernate.Session;
import org.hibernate.query.Query;
import java.util.List;
import Mapeo.*;

public class Consultas {

    private static Session session = Controlador.getSession();

    public Consultas(Session session) {
        this.session = Controlador.getSession();
    }

    // Consultas para obtener todos los registros de cada tabla

    public static List<Tutores> selectTutores() {
        Query<Tutores> query = session.createQuery("FROM Tutores", Tutores.class);
        return query.getResultList();
    }

    public static List<Profesores> selectProfesores() {
        Query<Profesores> query = session.createQuery("FROM Profesores", Profesores.class);
        return query.getResultList();
    }

    public static List<Extraescolares> selectExtraescolares() {
        Query<Extraescolares> query = session.createQuery("FROM Extraescolares", Extraescolares.class);
        return query.getResultList();
    }

    public static List<Cursos> selectCursos() {
        Query<Cursos> query = session.createQuery("FROM Cursos", Cursos.class);
        return query.getResultList();
    }

    public static List<Asignaturas> selectAsignaturas() {
        Query<Asignaturas> query = session.createQuery("FROM Asignaturas", Asignaturas.class);
        return query.getResultList();
    }

    public static List<Estudiantes> selectEstudiantes() {
        Query<Estudiantes> query = session.createQuery("FROM Estudiantes", Estudiantes.class);
        return query.getResultList();
    }

    public static List<Matriculas> selectMatriculas() {
        Query<Matriculas> query = session.createQuery("FROM Matriculas", Matriculas.class);
        return query.getResultList();
    }

    public static List<HistorialAcademico> selectHistorialAcademico() {
        Query<HistorialAcademico> query = session.createQuery("FROM HistorialAcademico", HistorialAcademico.class);
        return query.getResultList();
    }

    public static List<Asistencia> selectAsistencia() {
        Query<Asistencia> query = session.createQuery("FROM Asistencia", Asistencia.class);
        return query.getResultList();
    }

    public static List<Eventos> selectEventos() {
        Query<Eventos> query = session.createQuery("FROM Eventos", Eventos.class);
        return query.getResultList();
    }

    public static List<EstudiantesEventos> selectEstudiantesEventos() {
        Query<EstudiantesEventos> query = session.createQuery("FROM EstudiantesEventos", EstudiantesEventos.class);
        return query.getResultList();
    }

    public static List<Horarios> selectHorarios() {
        Query<Horarios> query = session.createQuery("FROM Horarios", Horarios.class);
        return query.getResultList();
    }

    public static List<Becas> selectBecas() {
        Query<Becas> query = session.createQuery("FROM Becas", Becas.class);
        return query.getResultList();
    }

    public static List<Convalidaciones> selectConvalidaciones() {
        Query<Convalidaciones> query = session.createQuery("FROM Convalidaciones", Convalidaciones.class);
        return query.getResultList();
    }

    public static List<Administradores> selectAdministradores() {
        Query<Administradores> query = session.createQuery("FROM Administradores", Administradores.class);
        return query.getResultList();
    }
}

