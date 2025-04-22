package BBDD;

import org.hibernate.Session;
import Mapeo.*;

public class Inserciones {

    public static void insertarEstudiante(Session nuevaSesion, Estudiantes estudiante) {
        nuevaSesion.beginTransaction();
        nuevaSesion.save(estudiante);
        nuevaSesion.getTransaction().commit();
    }

    public static void insertarProfesor(Session nuevaSesion, Profesores profesor) {
        nuevaSesion.beginTransaction();
        nuevaSesion.save(profesor);
        nuevaSesion.getTransaction().commit();
    }

    public static void insertarCurso(Session nuevaSesion, Cursos curso) {
        nuevaSesion.beginTransaction();
        nuevaSesion.save(curso);
        nuevaSesion.getTransaction().commit();
    }

    public static void insertarMatricula(Session nuevaSesion, Matriculas matricula) {
        nuevaSesion.beginTransaction();
        nuevaSesion.save(matricula);
        nuevaSesion.getTransaction().commit();
    }

    public static void insertarAsignatura(Session nuevaSesion, Asignaturas asignatura) {
        nuevaSesion.beginTransaction();
        nuevaSesion.save(asignatura);
        nuevaSesion.getTransaction().commit();
    }

    public static void insertarAsistencia(Session nuevaSesion, Asistencia asistencia) {
        nuevaSesion.beginTransaction();
        nuevaSesion.save(asistencia);
        nuevaSesion.getTransaction().commit();
    }

    public static void insertarBeca(Session nuevaSesion, Becas beca) {
        nuevaSesion.beginTransaction();
        nuevaSesion.save(beca);
        nuevaSesion.getTransaction().commit();
    }

    public static void insertarConvalidacion(Session nuevaSesion, Convalidaciones convalidacion) {
        nuevaSesion.beginTransaction();
        nuevaSesion.save(convalidacion);
        nuevaSesion.getTransaction().commit();
    }

    public static void insertarCursosAsignatura(Session nuevaSesion, CursosAsignaturas cursosAsignaturas) {
        nuevaSesion.beginTransaction();
        nuevaSesion.save(cursosAsignaturas);
        nuevaSesion.getTransaction().commit();
    }

    public static void insertarEstudiantesEventos(Session nuevaSesion, EstudiantesEventos estudiantesEventos) {
        nuevaSesion.beginTransaction();
        nuevaSesion.save(estudiantesEventos);
        nuevaSesion.getTransaction().commit();
    }

    public static void insertarEventos(Session nuevaSesion, Eventos eventos) {
        nuevaSesion.beginTransaction();
        nuevaSesion.save(eventos);
        nuevaSesion.getTransaction().commit();
    }

    public static void insertarExtraescolares(Session nuevaSesion, Extraescolares extraescolares) {
        nuevaSesion.beginTransaction();
        nuevaSesion.save(extraescolares);
        nuevaSesion.getTransaction().commit();
    }

    public static void insertarHistorialAcademico(Session nuevaSesion, HistorialAcademico historialAcademico) {
        nuevaSesion.beginTransaction();
        nuevaSesion.save(historialAcademico);
        nuevaSesion.getTransaction().commit();
    }

    public static void insertarHorarios(Session nuevaSesion, Horarios horarios) {
        nuevaSesion.beginTransaction();
        nuevaSesion.save(horarios);
        nuevaSesion.getTransaction().commit();
    }

    public static void insertarMatriculas(Session nuevaSesion, Matriculas matriculas) {
        nuevaSesion.beginTransaction();
        nuevaSesion.save(matriculas);
        nuevaSesion.getTransaction().commit();
    }

    public static void insertarTutores(Session nuevaSesion, Tutores tutores) {
        nuevaSesion.beginTransaction();
        nuevaSesion.save(tutores);
        nuevaSesion.getTransaction().commit();
    }

}