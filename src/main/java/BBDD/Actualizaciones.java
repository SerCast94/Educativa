package BBDD;

import org.hibernate.Session;
import Mapeo.*;

public class Actualizaciones {

    public static void actualizarTutor(Session sesion, Tutores tutorActualizado) {
        sesion.beginTransaction();
        sesion.merge(tutorActualizado);
        sesion.getTransaction().commit();
    }

    public static void actualizarProfesor(Session sesion, Profesores profesorActualizado) {
        sesion.beginTransaction();
        sesion.merge(profesorActualizado);
        sesion.getTransaction().commit();
    }

    public static void actualizarExtraescolar(Session sesion, Extraescolares extraescolarActualizado) {
        sesion.beginTransaction();
        sesion.merge(extraescolarActualizado);
        sesion.getTransaction().commit();
    }

    public static void actualizarCurso(Session sesion, Cursos cursoActualizado) {
        sesion.beginTransaction();
        sesion.merge(cursoActualizado);
        sesion.getTransaction().commit();
    }

    public static void actualizarAsignatura(Session sesion, Asignaturas asignaturaActualizada) {
        sesion.beginTransaction();
        sesion.merge(asignaturaActualizada);
        sesion.getTransaction().commit();
    }

    public static void actualizarEstudiante(Session sesion, Estudiantes estudianteActualizado) {
        sesion.beginTransaction();
        sesion.merge(estudianteActualizado);
        sesion.getTransaction().commit();
    }

    public static void actualizarMatricula(Session sesion, Matriculas matriculaActualizada) {
        sesion.beginTransaction();
        sesion.merge(matriculaActualizada);
        sesion.getTransaction().commit();
    }

    public static void actualizarHistorialAcademico(Session sesion, HistorialAcademico historialActualizado) {
        sesion.beginTransaction();
        sesion.merge(historialActualizado);
        sesion.getTransaction().commit();
    }

    public static void actualizarAsistencia(Session sesion, Asistencia asistenciaActualizada) {
        sesion.beginTransaction();
        sesion.merge(asistenciaActualizada);
        sesion.getTransaction().commit();
    }

    public static void actualizarEvento(Session sesion, Eventos eventoActualizado) {
        sesion.beginTransaction();
        sesion.merge(eventoActualizado);
        sesion.getTransaction().commit();
    }

    public static void actualizarEstudianteEvento(Session sesion, EstudiantesEventos estudianteEventoActualizado) {
        sesion.beginTransaction();
        sesion.merge(estudianteEventoActualizado);
        sesion.getTransaction().commit();
    }

    public static void actualizarHorario(Session sesion, Horarios horarioActualizado) {
        sesion.beginTransaction();
        sesion.merge(horarioActualizado);
        sesion.getTransaction().commit();
    }

    public static void actualizarBeca(Session sesion, Becas becaActualizada) {
        sesion.beginTransaction();
        sesion.merge(becaActualizada);
        sesion.getTransaction().commit();
    }

    public static void actualizarConvalidacion(Session sesion, Convalidaciones convalidacionActualizada) {
        sesion.beginTransaction();
        sesion.merge(convalidacionActualizada);
        sesion.getTransaction().commit();
    }

    public static void actualizarAdministrador(Session sesion, Administradores administradorActualizado) {
        sesion.beginTransaction();
        sesion.merge(administradorActualizado);
        sesion.getTransaction().commit();
    }
}
