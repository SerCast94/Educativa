package BBDD;

import org.hibernate.Session;
import Mapeo.*;

/**
 * Clase que contiene métodos para actualizar diferentes entidades en la base de datos.
 */

public class Actualizaciones {

    /**
     * Actualiza un tutor en la base de datos.
     *
     * @param sesion           La sesión de Hibernate.
     * @param tutorActualizado El objeto Tutor actualizado.
     */
    public static void actualizarTutor(Session sesion, Tutores tutorActualizado) {
        sesion.beginTransaction();
        sesion.merge(tutorActualizado);
        sesion.getTransaction().commit();
    }

    /**
     * Actualiza un profesor en la base de datos.
     *
     * @param sesion             La sesión de Hibernate.
     * @param profesorActualizado El objeto Profesor actualizado.
     */

    public static void actualizarProfesor(Session sesion, Profesores profesorActualizado) {
        sesion.beginTransaction();
        sesion.merge(profesorActualizado);
        sesion.getTransaction().commit();
    }

    /**
     * Actualiza un extraescolar en la base de datos.
     *
     * @param sesion               La sesión de Hibernate.
     * @param extraescolarActualizado El objeto Extraescolar actualizado.
     */

    public static void actualizarExtraescolar(Session sesion, Extraescolares extraescolarActualizado) {
        sesion.beginTransaction();
        sesion.merge(extraescolarActualizado);
        sesion.getTransaction().commit();
    }

    /**
     * Actualiza un curso en la base de datos.
     *
     * @param sesion             La sesión de Hibernate.
     * @param cursoActualizado El objeto Curso actualizado.
     */

    public static void actualizarCurso(Session sesion, Cursos cursoActualizado) {
        sesion.beginTransaction();
        sesion.merge(cursoActualizado);
        sesion.getTransaction().commit();
    }

    /**
     * Actualiza una asignatura en la base de datos.
     *
     * @param sesion               La sesión de Hibernate.
     * @param asignaturaActualizada El objeto Asignatura actualizado.
     */

    public static void actualizarAsignatura(Session sesion, Asignaturas asignaturaActualizada) {
        sesion.beginTransaction();
        sesion.merge(asignaturaActualizada);
        sesion.getTransaction().commit();
    }

    /**
     * Actualiza un estudiante en la base de datos.
     *
     * @param sesion               La sesión de Hibernate.
     * @param estudianteActualizado El objeto Estudiante actualizado.
     */

    public static void actualizarEstudiante(Session sesion, Estudiantes estudianteActualizado) {
        sesion.beginTransaction();
        sesion.merge(estudianteActualizado);
        sesion.getTransaction().commit();
    }

    /**
     * Actualiza una matrícula en la base de datos.
     *
     * @param sesion               La sesión de Hibernate.
     * @param matriculaActualizada El objeto Matrícula actualizado.
     */

    public static void actualizarMatricula(Session sesion, Matriculas matriculaActualizada) {
        sesion.beginTransaction();
        sesion.merge(matriculaActualizada);
        sesion.getTransaction().commit();
    }

    /**
     * Actualiza un historial académico en la base de datos.
     *
     * @param sesion               La sesión de Hibernate.
     * @param historialActualizado El objeto Historial Académico actualizado.
     */

    public static void actualizarHistorialAcademico(Session sesion, HistorialAcademico historialActualizado) {
        sesion.beginTransaction();
        sesion.merge(historialActualizado);
        sesion.getTransaction().commit();
    }

    /**
     * Actualiza una asistencia en la base de datos.
     *
     * @param sesion               La sesión de Hibernate.
     * @param asistenciaActualizada El objeto Asistencia actualizado.
     */

    public static void actualizarAsistencia(Session sesion, Asistencia asistenciaActualizada) {
        sesion.beginTransaction();
        sesion.merge(asistenciaActualizada);
        sesion.getTransaction().commit();
    }

    /**
     * Actualiza un evento en la base de datos.
     *
     * @param sesion               La sesión de Hibernate.
     * @param eventoActualizado El objeto Evento actualizado.
     */

    public static void actualizarEvento(Session sesion, Eventos eventoActualizado) {
        sesion.beginTransaction();
        sesion.merge(eventoActualizado);
        sesion.getTransaction().commit();
    }

    /**
     * Actualiza un estudiante-evento en la base de datos.
     *
     * @param sesion               La sesión de Hibernate.
     * @param estudianteEventoActualizado El objeto Estudiante-Evento actualizado.
     */

    public static void actualizarEstudianteEvento(Session sesion, EstudiantesEventos estudianteEventoActualizado) {
        sesion.beginTransaction();
        sesion.merge(estudianteEventoActualizado);
        sesion.getTransaction().commit();
    }

    /**
     * Actualiza un horario en la base de datos.
     *
     * @param sesion               La sesión de Hibernate.
     * @param horarioActualizado El objeto Horario actualizado.
     */

    public static void actualizarHorario(Session sesion, Horarios horarioActualizado) {
        sesion.beginTransaction();
        sesion.merge(horarioActualizado);
        sesion.getTransaction().commit();
    }

    /**
     * Actualiza una beca en la base de datos.
     *
     * @param sesion               La sesión de Hibernate.
     * @param becaActualizada El objeto Beca actualizado.
     */

    public static void actualizarBeca(Session sesion, Becas becaActualizada) {
        sesion.beginTransaction();
        sesion.merge(becaActualizada);
        sesion.getTransaction().commit();
    }

    /**
     * Actualiza una convalidación en la base de datos.
     *
     * @param sesion               La sesión de Hibernate.
     * @param convalidacionActualizada El objeto Convalidación actualizado.
     */

    public static void actualizarConvalidacion(Session sesion, Convalidaciones convalidacionActualizada) {
        sesion.beginTransaction();
        sesion.merge(convalidacionActualizada);
        sesion.getTransaction().commit();
    }

    /**
     * Actualiza un administrador en la base de datos.
     *
     * @param sesion               La sesión de Hibernate.
     * @param administradorActualizado El objeto Administrador actualizado.
     */

    public static void actualizarAdministrador(Session sesion, Administradores administradorActualizado) {
        sesion.beginTransaction();
        sesion.merge(administradorActualizado);
        sesion.getTransaction().commit();
    }

}