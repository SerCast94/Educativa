package Controlador;

import BBDD.*;
import org.hibernate.Session;
import java.util.List;
import Mapeo.*;

public class Controlador {

    static Session session;

    static public List<Tutores> listaTutores;
    static public List<Profesores> listaProfesores;
    static public List<Extraescolares> listaExtraescolares;
    static public List<Cursos> listaCursos;
    static public List<Asignaturas> listaAsignaturas;
    static public List<Estudiantes> listaEstudiantes;
    static public List<Matriculas> listaMatriculas;
    static public List<HistorialAcademico> listaHistorialAcademico;
    static public List<Asistencia> listaAsistencia;
    static public List<Eventos> listaEventos;
    static public List<EstudiantesEventos> listaEstudiantesEventos;
    static public List<Horarios> listaHorarios;
    static public List<Becas> listaBecas;
    static public List<Convalidaciones> listaConvalidaciones;
    static public List<Administradores> listaAdministradores;

    public Controlador() {
        this.session = Conexion.crearConexion();
    }

    /**
     * Método que carga las listas de la base de datos
     * para manejar los datos en la aplicación
     * sin necesidad de estar consultando la base de datos
     */
    public void cargarListas() {
        listaTutores = Consultas.selectTutores();
        listaProfesores = Consultas.selectProfesores();
        listaExtraescolares = Consultas.selectExtraescolares();
        listaCursos = Consultas.selectCursos();
        listaAsignaturas = Consultas.selectAsignaturas();
        listaEstudiantes = Consultas.selectEstudiantes();
        listaMatriculas = Consultas.selectMatriculas();
        listaHistorialAcademico = Consultas.selectHistorialAcademico();
        listaAsistencia = Consultas.selectAsistencia();
        listaEventos = Consultas.selectEventos();
        listaEstudiantesEventos = Consultas.selectEstudiantesEventos();
        listaHorarios = Consultas.selectHorarios();
        listaBecas = Consultas.selectBecas();
        listaConvalidaciones = Consultas.selectConvalidaciones();
        listaAdministradores = Consultas.selectAdministradores();
    }

    //Actualizar listas

    public static void actualizarListaEstudiantes() {
        listaEstudiantes = Consultas.selectEstudiantes();
    }

    public static void actualizarListaTutores() {
        listaTutores = Consultas.selectTutores();
    }

    public static void actualizarListaProfesores() {
        listaProfesores = Consultas.selectProfesores();
    }

    public static void actualizarListaExtraescolares() {
        listaExtraescolares = Consultas.selectExtraescolares();
    }

    public static void actualizarListaCursos() {
        listaCursos = Consultas.selectCursos();
    }

    public static void actualizarListaAsignaturas() {
        listaAsignaturas = Consultas.selectAsignaturas();
    }

    public static void actualizarListaMatriculas() {
        listaMatriculas = Consultas.selectMatriculas();
    }

    public static void actualizarListaHistorialAcademico() {
        listaHistorialAcademico = Consultas.selectHistorialAcademico();
    }

    public static void actualizarListaAsistencia() {
        listaAsistencia = Consultas.selectAsistencia();
    }

    public static void actualizarListaEventos() {
        listaEventos = Consultas.selectEventos();
    }

    public static void actualizarListaEstudiantesEventos() {
        listaEstudiantesEventos = Consultas.selectEstudiantesEventos();
    }

    public static void actualizarListaHorarios() {
        listaHorarios = Consultas.selectHorarios();
    }

    public static void actualizarListaBecas() {
        listaBecas = Consultas.selectBecas();
    }

    public static void actualizarListaConvalidaciones() {
        listaConvalidaciones = Consultas.selectConvalidaciones();
    }

    public static void actualizarListaAdministradores() {
        listaAdministradores = Consultas.selectAdministradores();
    }


    // Getters y Setters

    public static Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    public static List<Tutores> getListaTutores() {
        return listaTutores;
    }

    public static void setListaTutores(List<Tutores> listaTutores) {
        Controlador.listaTutores = listaTutores;
    }

    public static List<Profesores> getListaProfesores() {
        return listaProfesores;
    }

    public static void setListaProfesores(List<Profesores> listaProfesores) {
        Controlador.listaProfesores = listaProfesores;
    }

    public static List<Extraescolares> getListaExtraescolares() {
        return listaExtraescolares;
    }

    public static void setListaExtraescolares(List<Extraescolares> listaExtraescolares) {
        Controlador.listaExtraescolares = listaExtraescolares;
    }

    public static List<Cursos> getListaCursos() {
        return listaCursos;
    }

    public static void setListaCursos(List<Cursos> listaCursos) {
        Controlador.listaCursos = listaCursos;
    }

    public static List<Estudiantes> getListaEstudiantes() {
        return listaEstudiantes;
    }

    public static void setListaEstudiantes(List<Estudiantes> listaEstudiantes) {
        Controlador.listaEstudiantes = listaEstudiantes;
    }

    public static List<Matriculas> getListaMatriculas() {
        return listaMatriculas;
    }

    public static void setListaMatriculas(List<Matriculas> listaMatriculas) {
        Controlador.listaMatriculas = listaMatriculas;
    }

    public static List<HistorialAcademico> getListaHistorialAcademico() {
        return listaHistorialAcademico;
    }

    public static void setListaHistorialAcademico(List<HistorialAcademico> listaHistorialAcademico) {
        Controlador.listaHistorialAcademico = listaHistorialAcademico;
    }

    public static List<Asistencia> getListaAsistencia() {
        return listaAsistencia;
    }

    public static void setListaAsistencia(List<Asistencia> listaAsistencia) {
        Controlador.listaAsistencia = listaAsistencia;
    }

    public static List<Eventos> getListaEventos() {
        return listaEventos;
    }

    public static void setListaEventos(List<Eventos> listaEventos) {
        Controlador.listaEventos = listaEventos;
    }

    public static List<EstudiantesEventos> getListaEstudiantesEventos() {
        return listaEstudiantesEventos;
    }

    public static void setListaEstudiantesEventos(List<EstudiantesEventos> listaEstudiantesEventos) {
        Controlador.listaEstudiantesEventos = listaEstudiantesEventos;
    }

    public static List<Horarios> getListaHorarios() {
        return listaHorarios;
    }

    public static void setListaHorarios(List<Horarios> listaHorarios) {
        Controlador.listaHorarios = listaHorarios;
    }

    public static List<Becas> getListaBecas() {
        return listaBecas;
    }

    public static void setListaBecas(List<Becas> listaBecas) {
        Controlador.listaBecas = listaBecas;
    }

    public static List<Convalidaciones> getListaConvalidaciones() {
        return listaConvalidaciones;
    }

    public static void setListaConvalidaciones(List<Convalidaciones> listaConvalidaciones) {
        Controlador.listaConvalidaciones = listaConvalidaciones;
    }

    public static List<Administradores> getListaAdministradores() {
        return listaAdministradores;
    }

    public static void setListaAdministradores(List<Administradores> listaAdministradores) {
        Controlador.listaAdministradores = listaAdministradores;
    }

    public static List<Asignaturas> getListaAsignaturas() {
        return listaAsignaturas;
    }

    public static void setListaAsignaturas(List<Asignaturas> listaAsignaturas) {
        Controlador.listaAsignaturas = listaAsignaturas;
    }


    // Métodos de inserción

    public static void insertarControladorEstudiante(Estudiantes nuevoEstudiante) {
        Inserciones.insertarEstudiante(session,nuevoEstudiante);
    }

    public static void insertarControladorTutor(Tutores nuevoTutor) {
        Inserciones.insertarTutores(session, nuevoTutor);
    }

    public static void insertarControladorProfesor(Profesores nuevoProfesor) {
        Inserciones.insertarProfesor(session, nuevoProfesor);
    }

    public static void insertarControladorExtraescolar(Extraescolares nuevoExtraescolar) {
        Inserciones.insertarExtraescolares(session, nuevoExtraescolar);
    }

    public static void insertarControladorCurso(Cursos nuevoCurso) {
        Inserciones.insertarCurso(session, nuevoCurso);
    }

    public static void insertarControladorAsignatura(Asignaturas nuevaAsignatura) {
        Inserciones.insertarAsignatura(session, nuevaAsignatura);
    }

    public static void insertarControladorMatricula(Matriculas nuevaMatricula) {
        Inserciones.insertarMatricula(session, nuevaMatricula);
    }

    public static void insertarControladorHistorialAcademico(HistorialAcademico nuevoHistorial) {
        Inserciones.insertarHistorialAcademico(session, nuevoHistorial);
    }

    public static void insertarControladorAsistencia(Asistencia nuevaAsistencia) {
        Inserciones.insertarAsistencia(session, nuevaAsistencia);
    }

    public static void insertarControladorEvento(Eventos nuevoEvento) {
        Inserciones.insertarEventos(session, nuevoEvento);
    }

    public static void insertarControladorEstudianteEvento(EstudiantesEventos nuevoEstEv) {
        Inserciones.insertarEstudiantesEventos(session, nuevoEstEv);
    }

    public static void insertarControladorHorario(Horarios nuevoHorario) {
        Inserciones.insertarHorarios(session, nuevoHorario);
    }

    public static void insertarControladorBeca(Becas nuevaBeca) {
        Inserciones.insertarBeca(session, nuevaBeca);
    }

    public static void insertarControladorConvalidacion(Convalidaciones nuevaConvalidacion) {
        Inserciones.insertarConvalidacion(session, nuevaConvalidacion);
    }



    //Metodos de actualización

    public static void actualizarControladorEstudiante(Estudiantes nuevoEstudiante) {
        Actualizaciones.actualizarEstudiante(session,nuevoEstudiante);
    }

    public static void actualizarControladorTutor(Tutores tutorActualizado) {
        Actualizaciones.actualizarTutor(session, tutorActualizado);
    }

    public static void actualizarControladorProfesor(Profesores profesorActualizado) {
        Actualizaciones.actualizarProfesor(session, profesorActualizado);
    }

    public static void actualizarControladorExtraescolar(Extraescolares extraescolarActualizado) {
        Actualizaciones.actualizarExtraescolar(session, extraescolarActualizado);
    }

    public static void actualizarControladorCurso(Cursos cursoActualizado) {
        Actualizaciones.actualizarCurso(session, cursoActualizado);
    }

    public static void actualizarControladorAsignatura(Asignaturas asignaturaActualizada) {
        Actualizaciones.actualizarAsignatura(session, asignaturaActualizada);
    }

    public static void actualizarControladorMatricula(Matriculas matriculaActualizada) {
        Actualizaciones.actualizarMatricula(session, matriculaActualizada);
    }

    public static void actualizarControladorHistorialAcademico(HistorialAcademico historialActualizado) {
        Actualizaciones.actualizarHistorialAcademico(session, historialActualizado);
    }

    public static void actualizarControladorAsistencia(Asistencia asistenciaActualizada) {
        Actualizaciones.actualizarAsistencia(session, asistenciaActualizada);
    }

    public static void actualizarControladorEvento(Eventos eventoActualizado) {
        Actualizaciones.actualizarEvento(session, eventoActualizado);
    }

    public static void actualizarControladorEstudianteEvento(EstudiantesEventos estEvActualizado) {
        Actualizaciones.actualizarEstudianteEvento(session, estEvActualizado);
    }

    public static void actualizarControladorHorario(Horarios horarioActualizado) {
        Actualizaciones.actualizarHorario(session, horarioActualizado);
    }

    public static void actualizarControladorBeca(Becas becaActualizada) {
        Actualizaciones.actualizarBeca(session, becaActualizada);
    }

    public static void actualizarControladorConvalidacion(Convalidaciones convalidacionActualizada) {
        Actualizaciones.actualizarConvalidacion(session, convalidacionActualizada);
    }

    public static void actualizarControladorAdministrador(Administradores administradorActualizado) {
        Actualizaciones.actualizarAdministrador(session, administradorActualizado);
    }

    //Métodos de eliminación

    public static void eliminarControladorEstudiante(Estudiantes estudiante) {
        Eliminaciones.deleteEstudiantes(session, estudiante);
    }

    public static void eliminarControladorTutor(Tutores tutor) {
        Eliminaciones.deleteTutores(session, tutor);
    }

    public static void eliminarControladorProfesor(Profesores profesor) {
        Eliminaciones.deleteProfesores(session, profesor);
    }

    public static void eliminarControladorExtraescolar(Extraescolares extraescolar) {
        Eliminaciones.deleteExtraescolares(session, extraescolar);
    }

    public static void eliminarControladorCurso(Cursos curso) {
        Eliminaciones.deleteCursos(session, curso);
    }

    public static void eliminarControladorAsignatura(Asignaturas asignatura) {
        Eliminaciones.deleteAsignaturas(session, asignatura);
    }

    public static void eliminarControladorMatricula(Matriculas matricula) {
        Eliminaciones.deleteMatriculas(session, matricula);
    }

    public static void eliminarControladorHistorialAcademico(HistorialAcademico historial) {
        Eliminaciones.deleteHistorialAcademico(session, historial);
    }

    public static void eliminarControladorAsistencia(Asistencia asistencia) {
        Eliminaciones.deleteAsistencia(session, asistencia);
    }

    public static void eliminarControladorEvento(Eventos evento) {
        Eliminaciones.deleteEventos(session, evento);
    }

    public static void eliminarControladorEstudianteEvento(EstudiantesEventos estudianteEvento) {
        Eliminaciones.deleteEstudiantesEventos(session, estudianteEvento);
    }

    public static void eliminarControladorHorario(Horarios horario) {
        Eliminaciones.deleteHorarios(session, horario);
    }

    public static void eliminarControladorBeca(Becas beca) {
        Eliminaciones.deleteBecas(session, beca);
    }

    public static void eliminarControladorConvalidacion(Convalidaciones convalidacion) {
        Eliminaciones.deleteConvalidaciones(session, convalidacion);
    }

    public static void eliminarControladorAdministrador(Administradores administrador) {
        Eliminaciones.deleteAdministradores(session, administrador);
    }

    /**
     * Metodo rollback para deshacer los cambios en la base de datos
     */
    public static void rollback() {
        session.getTransaction().rollback();
    }
}