package BBDD;

import org.hibernate.Session;
import Mapeo.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Clase que contiene métodos para actualizar diferentes entidades en la base de datos.
 */

public class Actualizaciones {

    private static final Logger logger = LoggerFactory.getLogger(Actualizaciones.class);

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
        logger.info("Tutor actualizado: " + tutorActualizado);
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
        logger.info("Profesor actualizado: " + profesorActualizado);
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
        logger.info("Se ha actualizado la reserva: " + extraescolarActualizado.getPista() + " para el día: " + extraescolarActualizado.getFechaReserva() + " a la hora: " + extraescolarActualizado.getHora());

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
        logger.info("Curso actualizado: " + cursoActualizado.getNombre());
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
        logger.info("Asignatura actualizada: " + asignaturaActualizada.getNombre());
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
        logger.info("Estudiante actualizado: " + estudianteActualizado);
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
        logger.info("Se ha actualizado la matricula del estudiante: "+ matriculaActualizada.getEstudiante() + " en el curso: " + matriculaActualizada.getCurso().getNombre());
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
        logger.info("Se ha actualizado la nota del estudiante " + historialActualizado.getEstudiante() + " en la asignatura de " + historialActualizado.getAsignatura().getNombre() + "donde tenía una nota de " + historialActualizado.getNotaFinal());
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
        logger.info("Se ha actualizado la asistencia del estudiante: " + asistenciaActualizada.getEstudiante() + " del día " + asistenciaActualizada.getFecha());

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
        logger.info("Se ha actualizado el evento: " + eventoActualizado.getNombre());
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
        logger.info("Inscripción actualizada del estudiante " + estudianteEventoActualizado.getEstudiante() + " para el evento " + estudianteEventoActualizado.getEvento().getNombre());
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
        logger.info("Horario actualizado: " + horarioActualizado);
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
        logger.info("Beca actualizada: " + becaActualizada.getEstudiante() + " con un importe de: " + becaActualizada.getMonto());
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
        logger.info("Convalidación actualizada: " + convalidacionActualizada.getEstudiante() + " para la asignatura: " + convalidacionActualizada.getAsignaturaOriginal().getNombre());

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
        logger.info("Administrador actualizado: " + administradorActualizado);
    }

}