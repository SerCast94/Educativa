package BBDD;

import org.hibernate.Session;
import org.hibernate.Transaction;
import Mapeo.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Clase que contiene métodos para eliminar diferentes entidades en la base de datos.
 */

public class Eliminaciones {

    private static final Logger logger = LoggerFactory.getLogger(Eliminaciones.class);

    /**
     * Elimina un tutor de la base de datos.
     *
     * @param session La sesión de Hibernate.
     * @param tutor   El objeto Tutor a eliminar.
     */
    public static void deleteTutores(Session session, Tutores tutor) {
        Transaction transaction = session.beginTransaction();
        Tutores tutorEnSesion = session.get(Tutores.class, tutor.getIdTutor());
        if (tutorEnSesion != null) {
            session.delete(tutorEnSesion);
        }
        transaction.commit();
        logger.info("Tutor eliminado: " + tutor);
    }

    /**
     * Elimina un profesor de la base de datos.
     *
     * @param session La sesión de Hibernate.
     * @param profesor El objeto Profesor a eliminar.
     */
    public static void deleteProfesores(Session session, Profesores profesor) {
        Transaction transaction = session.beginTransaction();
        Profesores profesorEnSesion = session.get(Profesores.class, profesor.getIdProfesor());
        if (profesorEnSesion != null) {
            session.delete(profesorEnSesion);
        }
        transaction.commit();
        logger.info("Profesor eliminado: " + profesor);
    }

    /**
     * Elimina un extraescolar de la base de datos.
     *
     * @param session      La sesión de Hibernate.
     * @param extraescolar El objeto Extraescolar a eliminar.
     */
    public static void deleteExtraescolares(Session session, Extraescolares extraescolar) {
        Transaction transaction = session.beginTransaction();
        Extraescolares extraescolarEnSesion = session.get(Extraescolares.class, extraescolar.getId());
        if (extraescolarEnSesion != null) {
            session.delete(extraescolarEnSesion);
        }
        transaction.commit();
        logger.info("Se ha eliminado la reserva: " + extraescolar.getPista() + " para el día: " + extraescolar.getFechaReserva() + " a la hora: " + extraescolar.getHora());
    }

    /**
     * Elimina un curso de la base de datos.
     *
     * @param session La sesión de Hibernate.
     * @param curso   El objeto Curso a eliminar.
     */
    public static void deleteCursos(Session session, Cursos curso) {
        Transaction transaction = session.beginTransaction();
        Cursos cursoEnSesion = session.get(Cursos.class, curso.getIdCurso());
        if (cursoEnSesion != null) {
            session.delete(cursoEnSesion);
        }
        transaction.commit();
        logger.info("Curso eliminado: " + curso.getNombre());
    }

    /**
     * Elimina una asignatura de la base de datos.
     *
     * @param session    La sesión de Hibernate.
     * @param asignatura El objeto Asignatura a eliminar.
     */
    public static void deleteAsignaturas(Session session, Asignaturas asignatura) {
        Transaction transaction = session.beginTransaction();
        Asignaturas asignaturaEnSesion = session.get(Asignaturas.class, asignatura.getIdAsignatura());
        if (asignaturaEnSesion != null) {
            session.delete(asignaturaEnSesion);
        }
        transaction.commit();
        logger.info("Asignatura eliminada: " + asignatura.getNombre());
    }

    /**
     * Elimina un estudiante de la base de datos.
     *
     * @param session   La sesión de Hibernate.
     * @param estudiante El objeto Estudiante a eliminar.
     */
    public static void deleteEstudiantes(Session session, Estudiantes estudiante) {
        Transaction transaction = session.beginTransaction();
        Estudiantes estudianteEnSesion = session.get(Estudiantes.class, estudiante.getIdEstudiante());
        if (estudianteEnSesion != null) {
            session.delete(estudianteEnSesion);
        }
        transaction.commit();
        logger.info("Estudiante eliminado: " + estudiante);
    }

    /**
     * Elimina una matrícula de la base de datos.
     *
     * @param session  La sesión de Hibernate.
     * @param matricula El objeto Matricula a eliminar.
     */
    public static void deleteMatriculas(Session session, Matriculas matricula) {
        Transaction transaction = session.beginTransaction();
        Matriculas matriculaEnSesion = session.get(Matriculas.class, matricula.getIdMatricula());
        if (matriculaEnSesion != null) {
            session.delete(matriculaEnSesion);
        }
        transaction.commit();
        logger.info("Se ha borrado la matricula del estudiante: "+ matricula.getEstudiante() + " en el curso: " + matricula.getCurso().getNombre());
    }

    /**
     * Elimina un historial académico de la base de datos.
     *
     * @param session   La sesión de Hibernate.
     * @param historial El objeto HistorialAcademico a eliminar.
     */
    public static void deleteHistorialAcademico(Session session, HistorialAcademico historial) {
        Transaction transaction = session.beginTransaction();
        HistorialAcademico historialEnSesion = session.get(HistorialAcademico.class, historial.getIdHistorial());
        if (historialEnSesion != null) {
            session.delete(historialEnSesion);
        }
        transaction.commit();
        logger.info("Se ha borrado la nota del estudiante " + historial.getEstudiante() + " en la asignatura de " + historial.getAsignatura().getNombre() + "donde tenía una nota de " + historial.getNotaFinal());
    }

    /**
     * Elimina una asistencia de la base de datos.
     *
     * @param session   La sesión de Hibernate.
     * @param asistencia El objeto Asistencia a eliminar.
     */
    public static void deleteAsistencia(Session session, Asistencia asistencia) {
        Transaction transaction = session.beginTransaction();
        Asistencia asistenciaEnSesion = session.get(Asistencia.class, asistencia.getIdAsistencia());
        if (asistenciaEnSesion != null) {
            session.delete(asistenciaEnSesion);
        }
        transaction.commit();
        logger.info("Se ha eliminado la asistencia del estudiante: " + asistencia.getEstudiante() + " del día " + asistencia.getFecha());

    }

    /**
     * Elimina un evento de la base de datos.
     *
     * @param session La sesión de Hibernate.
     * @param evento  El objeto Evento a eliminar.
     */
    public static void deleteEventos(Session session, Eventos evento) {
        Transaction transaction = session.beginTransaction();
        Eventos eventoEnSesion = session.get(Eventos.class, evento.getIdEvento());
        if (eventoEnSesion != null) {
            session.delete(eventoEnSesion);
        }
        transaction.commit();
        logger.info("Evento eliminado: " + evento.getNombre());
    }

    /**
     * Elimina un estudiante-evento de la base de datos.
     *
     * @param session         La sesión de Hibernate.
     * @param estudianteEvento El objeto EstudiantesEventos a eliminar.
     */
    public static void deleteEstudiantesEventos(Session session, EstudiantesEventos estudianteEvento) {
        Transaction transaction = session.beginTransaction();

        // Crear el Id compuesto utilizando los IDs de Estudiante y Evento
        EstudiantesEventosId id = new EstudiantesEventosId(
                estudianteEvento.getEstudiante().getIdEstudiante(),
                estudianteEvento.getEvento().getIdEvento()
        );

        // Buscar la entidad utilizando el Id compuesto
        EstudiantesEventos eventoEnSesion = session.get(EstudiantesEventos.class, id);

        if (eventoEnSesion != null) {
            session.delete(eventoEnSesion);
        }

        transaction.commit();
        logger.info("Inscripción eliminada del estudiante " + estudianteEvento.getEstudiante() + " para el evento " + estudianteEvento.getEvento().getNombre());
    }

    /**
     * Elimina un horario de la base de datos.
     *
     * @param session La sesión de Hibernate.
     * @param horario El objeto Horario a eliminar.
     */
    public static void deleteHorarios(Session session, Horarios horario) {
        Transaction transaction = session.beginTransaction();
        Horarios horarioEnSesion = session.get(Horarios.class, horario.getIdHorario());
        if (horarioEnSesion != null) {
            session.delete(horarioEnSesion);
        }
        transaction.commit();
        logger.info("Horario eliminado: " + horario);
    }

    /**
     * Elimina una beca de la base de datos.
     *
     * @param session La sesión de Hibernate.
     * @param beca    El objeto Beca a eliminar.
     */
    public static void deleteBecas(Session session, Becas beca) {
        Transaction transaction = session.beginTransaction();
        Becas becaEnSesion = session.get(Becas.class, beca.getIdBeca());
        if (becaEnSesion != null) {
            session.delete(becaEnSesion);
        }
        transaction.commit();
        logger.info("Beca eliminada: " + beca.getEstudiante() + " con un importe de: " + beca.getMonto());
    }

    /**
     * Elimina una convalidación de la base de datos.
     *
     * @param session      La sesión de Hibernate.
     * @param convalidacion El objeto Convalidacion a eliminar.
     */
    public static void deleteConvalidaciones(Session session, Convalidaciones convalidacion) {
        Transaction transaction = session.beginTransaction();
        Convalidaciones convalidacionEnSesion = session.get(Convalidaciones.class, convalidacion.getIdConvalidacion());
        if (convalidacionEnSesion != null) {
            session.delete(convalidacionEnSesion);
        }
        transaction.commit();
        logger.info("Convalidación eliminada: " + convalidacion.getEstudiante() + " para la asignatura: " + convalidacion.getAsignaturaOriginal().getNombre());
    }

    /**
     * Elimina un administrador de la base de datos.
     *
     * @param session       La sesión de Hibernate.
     * @param administrador El objeto Administrador a eliminar.
     */
    public static void deleteAdministradores(Session session, Administradores administrador) {
        Transaction transaction = session.beginTransaction();
        Administradores administradorEnSesion = session.get(Administradores.class, administrador.getIdAdministrador());
        if (administradorEnSesion != null) {
            session.delete(administradorEnSesion);
        }
        transaction.commit();
        logger.info("Administrador eliminado: " + administrador);
    }
}
