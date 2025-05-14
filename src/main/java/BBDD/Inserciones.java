package BBDD;

import org.hibernate.Session;
import Mapeo.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Esta clase contiene métodos para insertar diferentes entidades en la base de datos.
 */

public class Inserciones {

    private static final Logger logger = LoggerFactory.getLogger(Inserciones.class);

    /**
     * Inserta un estudiante en la base de datos.
     *
     * @param nuevaSesion La sesión de Hibernate.
     * @param estudiante   El objeto Estudiante a insertar.
     */
    public static void insertarEstudiante(Session nuevaSesion, Estudiantes estudiante) {
        nuevaSesion.beginTransaction();
        nuevaSesion.save(estudiante);
        nuevaSesion.getTransaction().commit();
        logger.info("Estudiante insertado: " + estudiante);
    }

    /**
     * Inserta un profesor en la base de datos.
     *
     * @param nuevaSesion La sesión de Hibernate.
     * @param profesor    El objeto profesor a insertar.
     */
    public static void insertarProfesor(Session nuevaSesion, Profesores profesor) {
        nuevaSesion.beginTransaction();
        nuevaSesion.save(profesor);
        nuevaSesion.getTransaction().commit();
        logger.info("Profesor insertado: " + profesor);
    }

    /**
     * Inserta un curso en la base de datos.
     *
     * @param nuevaSesion La sesión de Hibernate.
     * @param curso       El objeto Curso a insertar.
     */
    public static void insertarCurso(Session nuevaSesion, Cursos curso) {
        nuevaSesion.beginTransaction();
        nuevaSesion.save(curso);
        nuevaSesion.getTransaction().commit();
        logger.info("Curso insertado: " + curso.getNombre());
    }

    /**
     * Inserta una matrícula en la base de datos.
     *
     * @param nuevaSesion La sesión de Hibernate.
     * @param matricula   El objeto Matricula a insertar.
     */
    public static void insertarMatricula(Session nuevaSesion, Matriculas matricula) {
        nuevaSesion.beginTransaction();
        nuevaSesion.save(matricula);
        nuevaSesion.getTransaction().commit();
        logger.info("El estudiante: "+ matricula.getEstudiante() + " ha sido matriculado en el curso: " + matricula.getCurso().getNombre());
    }

    /**
     * Inserta una asignatura en la base de datos.
     *
     * @param nuevaSesion La sesión de Hibernate.
     * @param asignatura  El objeto Asignatura a insertar.
     */
    public static void insertarAsignatura(Session nuevaSesion, Asignaturas asignatura) {
        nuevaSesion.beginTransaction();
        nuevaSesion.save(asignatura);
        nuevaSesion.getTransaction().commit();
        logger.info("Asignatura insertada: " + asignatura.getNombre());
    }

    /**
     * Inserta una asistencia en la base de datos.
     *
     * @param nuevaSesion La sesión de Hibernate.
     * @param asistencia  El objeto Asistencia a insertar.
     */
    public static void insertarAsistencia(Session nuevaSesion, Asistencia asistencia) {
        nuevaSesion.beginTransaction();
        nuevaSesion.save(asistencia);
        nuevaSesion.getTransaction().commit();
        logger.info("El estudiante: " + asistencia.getEstudiante() + " no ha asistido a la clase el día " + asistencia.getFecha());
    }

    /**
     * Inserta una beca en la base de datos.
     *
     * @param nuevaSesion La sesión de Hibernate.
     * @param beca       El objeto Beca a insertar.
     */
    public static void insertarBeca(Session nuevaSesion, Becas beca) {
        nuevaSesion.beginTransaction();
        nuevaSesion.save(beca);
        nuevaSesion.getTransaction().commit();
        logger.info("Se ha aprobado una beca para el estudiante: " + beca.getEstudiante() + " con un importe de: " + beca.getMonto());
    }

    /**
     * Inserta una convalidación en la base de datos.
     *
     * @param nuevaSesion La sesión de Hibernate.
     * @param convalidacion El objeto Convalidacion a insertar.
     */
    public static void insertarConvalidacion(Session nuevaSesion, Convalidaciones convalidacion) {
        nuevaSesion.beginTransaction();
        nuevaSesion.save(convalidacion);
        nuevaSesion.getTransaction().commit();
        logger.info("El estudiante: " + convalidacion.getEstudiante() + " ha solicitado una convalidación de la asignatura: " + convalidacion.getAsignaturaOriginal().getNombre());
    }

    /**
     * Inserta un curso-asignatura en la base de datos.
     *
     * @param nuevaSesion La sesión de Hibernate.
     * @param cursosAsignaturas El objeto CursosAsignaturas a insertar.
     */
    public static void insertarCursosAsignatura(Session nuevaSesion, CursosAsignaturas cursosAsignaturas) {
        nuevaSesion.beginTransaction();
        nuevaSesion.save(cursosAsignaturas);
        nuevaSesion.getTransaction().commit();
        logger.info("Curso Asignatura insertado: " + cursosAsignaturas);
    }

    /**
     * Inserta un estudianteseventos en la base de datos.
     *
     * @param nuevaSesion La sesión de Hibernate.
     * @param estudiantesEventos El objeto EstudiantesEventos a insertar.
     */
    public static void insertarEstudiantesEventos(Session nuevaSesion, EstudiantesEventos estudiantesEventos) {
        nuevaSesion.beginTransaction();
        nuevaSesion.save(estudiantesEventos);
        nuevaSesion.getTransaction().commit();
        logger.info("Estudiantes Eventos insertado: " + estudiantesEventos);
    }

    /**
     * Inserta un evento en la base de datos.
     *
     * @param nuevaSesion La sesión de Hibernate.
     * @param eventos       El objeto Eventos a insertar.
     */
    public static void insertarEventos(Session nuevaSesion, Eventos eventos) {
        nuevaSesion.beginTransaction();
        nuevaSesion.save(eventos);
        nuevaSesion.getTransaction().commit();
        logger.info("Se ha creado el evento: " + eventos.getNombre());
    }

    /**
     * Inserta una extraescolar en la base de datos.
     *
     * @param nuevaSesion La sesión de Hibernate.
     * @param extraescolares El objeto Extraescolares a insertar.
     */
    public static void insertarExtraescolares(Session nuevaSesion, Extraescolares extraescolares) {
        nuevaSesion.beginTransaction();
        nuevaSesion.save(extraescolares);
        nuevaSesion.getTransaction().commit();
        logger.info("Se ha reservado: " + extraescolares.getPista() + " para el día: " + extraescolares.getFechaReserva() + " a la hora: " + extraescolares.getHora());
    }

    /**
     * Inserta un historial académico en la base de datos.
     *
     * @param nuevaSesion La sesión de Hibernate.
     * @param historialAcademico El objeto HistorialAcademico a insertar.
     */
    public static void insertarHistorialAcademico(Session nuevaSesion, HistorialAcademico historialAcademico) {
        nuevaSesion.beginTransaction();
        nuevaSesion.save(historialAcademico);
        nuevaSesion.getTransaction().commit();
        logger.info("El estudiante " + historialAcademico.getEstudiante() + " en la asignatura de " + historialAcademico.getAsignatura().getNombre() + "tiene una nota de " + historialAcademico.getNotaFinal());
    }

    /**
     * Inserta un horario en la base de datos.
     *
     * @param nuevaSesion La sesión de Hibernate.
     * @param horarios       El objeto Horarios a insertar.
     */
    public static void insertarHorarios(Session nuevaSesion, Horarios horarios) {
        nuevaSesion.beginTransaction();
        nuevaSesion.save(horarios);
        nuevaSesion.getTransaction().commit();
        logger.info("Horario insertado: " + horarios);
    }

    /**
     * Inserta un tutor en la base de datos.
     *
     * @param nuevaSesion La sesión de Hibernate.
     * @param tutores       El objeto Tutor a insertar.
     */
    public static void insertarTutores(Session nuevaSesion, Tutores tutores) {
        nuevaSesion.beginTransaction();
        nuevaSesion.save(tutores);
        nuevaSesion.getTransaction().commit();
        logger.info("Tutor insertado: " + tutores);
    }
}