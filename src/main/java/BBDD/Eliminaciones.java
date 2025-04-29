package BBDD;

import Controlador.Controlador;
import org.hibernate.Session;
import org.hibernate.Transaction;
import Mapeo.*;

public class Eliminaciones {

    public static void deleteTutores(Session session, Tutores tutor) {
        Transaction transaction = session.beginTransaction();
        Tutores tutorEnSesion = session.get(Tutores.class, tutor.getIdTutor());
        if (tutorEnSesion != null) {
            session.delete(tutorEnSesion);
        }
        transaction.commit();
    }

    public static void deleteProfesores(Session session, Profesores profesor) {
        Transaction transaction = session.beginTransaction();
        Profesores profesorEnSesion = session.get(Profesores.class, profesor.getIdProfesor());
        if (profesorEnSesion != null) {
            session.delete(profesorEnSesion);
        }
        transaction.commit();
    }

    public static void deleteExtraescolares(Session session, Extraescolares extraescolar) {
        Transaction transaction = session.beginTransaction();
        Extraescolares extraescolarEnSesion = session.get(Extraescolares.class, extraescolar.getIdExtraescolar());
        if (extraescolarEnSesion != null) {
            session.delete(extraescolarEnSesion);
        }
        transaction.commit();
    }

    public static void deleteCursos(Session session, Cursos curso) {
        Transaction transaction = session.beginTransaction();
        Cursos cursoEnSesion = session.get(Cursos.class, curso.getIdCurso());
        if (cursoEnSesion != null) {
            session.delete(cursoEnSesion);
        }
        transaction.commit();
    }

    public static void deleteAsignaturas(Session session, Asignaturas asignatura) {
        Transaction transaction = session.beginTransaction();
        Asignaturas asignaturaEnSesion = session.get(Asignaturas.class, asignatura.getIdAsignatura());
        if (asignaturaEnSesion != null) {
            session.delete(asignaturaEnSesion);
        }
        transaction.commit();
    }

    public static void deleteEstudiantes(Session session, Estudiantes estudiante) {
        Transaction transaction = session.beginTransaction();
        Estudiantes estudianteEnSesion = session.get(Estudiantes.class, estudiante.getIdEstudiante());
        if (estudianteEnSesion != null) {
            session.delete(estudianteEnSesion);
        }
        transaction.commit();
    }

    public static void deleteMatriculas(Session session, Matriculas matricula) {
        Transaction transaction = session.beginTransaction();
        Matriculas matriculaEnSesion = session.get(Matriculas.class, matricula.getIdMatricula());
        if (matriculaEnSesion != null) {
            session.delete(matriculaEnSesion);
        }
        transaction.commit();
    }

    public static void deleteHistorialAcademico(Session session, HistorialAcademico historial) {
        Transaction transaction = session.beginTransaction();
        HistorialAcademico historialEnSesion = session.get(HistorialAcademico.class, historial.getIdHistorial());
        if (historialEnSesion != null) {
            session.delete(historialEnSesion);
        }
        transaction.commit();
    }

    public static void deleteAsistencia(Session session, Asistencia asistencia) {
        Transaction transaction = session.beginTransaction();
        Asistencia asistenciaEnSesion = session.get(Asistencia.class, asistencia.getIdAsistencia());
        if (asistenciaEnSesion != null) {
            session.delete(asistenciaEnSesion);
        }
        transaction.commit();
    }

    public static void deleteEventos(Session session, Eventos evento) {
        Transaction transaction = session.beginTransaction();
        Eventos eventoEnSesion = session.get(Eventos.class, evento.getIdEvento());
        if (eventoEnSesion != null) {
            session.delete(eventoEnSesion);
        }
        transaction.commit();
    }

    public static void deleteEstudiantesEventos(Session session, EstudiantesEventos estudianteEvento) {
        Transaction transaction = session.beginTransaction();

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
    }


    public static void deleteHorarios(Session session, Horarios horario) {
        Transaction transaction = session.beginTransaction();
        Horarios horarioEnSesion = session.get(Horarios.class, horario.getIdHorario());
        if (horarioEnSesion != null) {
            session.delete(horarioEnSesion);
        }
        transaction.commit();
    }

    public static void deleteBecas(Session session, Becas beca) {
        Transaction transaction = session.beginTransaction();
        Becas becaEnSesion = session.get(Becas.class, beca.getIdBeca());
        if (becaEnSesion != null) {
            session.delete(becaEnSesion);
        }
        transaction.commit();
    }

    public static void deleteConvalidaciones(Session session, Convalidaciones convalidacion) {
        Transaction transaction = session.beginTransaction();
        Convalidaciones convalidacionEnSesion = session.get(Convalidaciones.class, convalidacion.getIdConvalidacion());
        if (convalidacionEnSesion != null) {
            session.delete(convalidacionEnSesion);
        }
        transaction.commit();
    }

    public static void deleteAdministradores(Session session, Administradores administrador) {
        Transaction transaction = session.beginTransaction();
        Administradores administradorEnSesion = session.get(Administradores.class, administrador.getIdAdministrador());
        if (administradorEnSesion != null) {
            session.delete(administradorEnSesion);
        }
        transaction.commit();
    }
}
