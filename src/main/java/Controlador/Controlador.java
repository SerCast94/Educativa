package Controlador;

import BBDD.*;
import org.hibernate.Session;
import java.util.List;
import Mapeo.*;

/**
 * Controlador es la clase encargada de manejar la lógica de negocio
 * y la comunicación con la base de datos.
 * Esta clase contiene métodos para cargar, actualizar, insertar y eliminar
 * datos de las diferentes entidades del sistema. Así como un conjunto de listas que
 * hacen las veces de caché para evitar consultas repetidas a la base de datos.
 */
public class Controlador {

    /**
     * Atributo estático que representa la sesión de la base de datos
     */
    static Session session;

    /**
     * Listas estáticas que representan las diferentes entidades del sistema
     * que se cargan al iniciar la aplicación y actualizan con los cambios.
     */
    static public List<Tutores> listaTutores;
    static public List<Profesores> listaProfesores;
    static public List<Extraescolares> listaExtraescolares;
    static public List<Cursos> listaCursos;
    static public List<Asignaturas> listaAsignaturas;
    static public List<CursosAsignaturas> listaCursosAsignaturas;
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

    /**
     * Constructor de la clase Controlador
     * Inicializa la sesión de la base de datos
     */
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
        listaCursosAsignaturas = Consultas.selectCursosAsignaturas();
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

    //ACTUALIZAR LISTAS

    /**
     * Método para actualizar la lista que contiene la entidad Estudiantes
     */
    public static void actualizarListaEstudiantes() {
        listaEstudiantes = Consultas.selectEstudiantes();
    }

    /**
     * Método para actualizar la lista que contiene la entidad Tutores
     */
    public static void actualizarListaTutores() {
        listaTutores = Consultas.selectTutores();
    }

    /**
     * Método para actualizar la lista que contiene la entidad Profesores
     */
    public static void actualizarListaProfesores() {
        listaProfesores = Consultas.selectProfesores();
    }

    /**
     * Método para actualizar la lista que contiene la entidad Extraescolares
     */
    public static void actualizarListaExtraescolares() {
        listaExtraescolares = Consultas.selectExtraescolares();
    }

    /**
     * Método para actualizar la lista que contiene la entidad Cursos
     */
    public static void actualizarListaCursos() {
        listaCursos = Consultas.selectCursos();
    }

    /**
     * Método para actualizar la lista que contiene la entidad Asignaturas
     */
    public static void actualizarListaAsignaturas() {
        listaAsignaturas = Consultas.selectAsignaturas();
    }

    /**
     * Método para actualizar la lista que contiene la entidad CursosAsignaturas
     */
    public static void actualizarListaCursosAsignaturas() {
        listaCursosAsignaturas = Consultas.selectCursosAsignaturas();
    }

    /**
     * Método para actualizar la lista que contiene la entidad Matriculas
     */
    public static void actualizarListaMatriculas() {
        listaMatriculas = Consultas.selectMatriculas();
    }

    /**
     * Método para actualizar la lista que contiene la entidad HistorialAcademico
     */
    public static void actualizarListaHistorialAcademico() {
        listaHistorialAcademico = Consultas.selectHistorialAcademico();
    }

    /**
     * Método para actualizar la lista que contiene la entidad Asistencia
     */
    public static void actualizarListaAsistencia() {
        listaAsistencia = Consultas.selectAsistencia();
    }

    /**
     * Método para actualizar la lista que contiene la entidad Eventos
     */
    public static void actualizarListaEventos() {
        listaEventos = Consultas.selectEventos();
    }

    /**
     * Método para actualizar la lista que contiene la entidad EstudiantesEventos
     */
    public static void actualizarListaEstudiantesEventos() {
        listaEstudiantesEventos = Consultas.selectEstudiantesEventos();
    }

    /**
     * Método para actualizar la lista que contiene la entidad Horarios
     */
    public static void actualizarListaHorarios() {
        listaHorarios = Consultas.selectHorarios();
    }

    /**
     * Método para actualizar la lista que contiene la entidad Becas
     */
    public static void actualizarListaBecas() {
        listaBecas = Consultas.selectBecas();
    }

    /**
     * Método para actualizar la lista que contiene la entidad Convalidaciones
     */
    public static void actualizarListaConvalidaciones() {
        listaConvalidaciones = Consultas.selectConvalidaciones();
    }

    /**
     * Método para actualizar la lista que contiene la entidad Administradores
     */
    public static void actualizarListaAdministradores() {
        listaAdministradores = Consultas.selectAdministradores();
    }

    // GETTERS Y SETTERS

    /**
     * Método para obtener la sesión de la base de datos
     * @return session
     */
    public static Session getSession() {
        return session;
    }

    /**
     * Método para establecer la sesión de la base de datos
     * @param session
     */
    public void setSession(Session session) {
        this.session = session;
    }

    /**
     * Método para obtener la lista de la entidad Tutores
     * @return listaTutores
     */
    public static List<Tutores> getListaTutores() {
        return listaTutores;
    }

    /**
     * Método para establecer la lista de la entidad Tutores
     * @param listaTutores
     */
    public static void setListaTutores(List<Tutores> listaTutores) {
        Controlador.listaTutores = listaTutores;
    }

    /**
     * Método para obtener la lista de la entidad Profesores
     * @return listaProfesores
     */
    public static List<Profesores> getListaProfesores() {
        return listaProfesores;
    }

    /**
     * Método para establecer la lista de la entidad Profesores
     * @param listaProfesores
     */
    public static void setListaProfesores(List<Profesores> listaProfesores) {
        Controlador.listaProfesores = listaProfesores;
    }

    /**
     * Método para obtener la lista de la entidad Extraescolares
     * @return listaExtraescolares
     */
    public static List<Extraescolares> getListaExtraescolares() {
        return listaExtraescolares;
    }

    /**
     * Método para establecer la lista de la entidad Extraescolares
     * @param listaExtraescolares
     */
    public static void setListaExtraescolares(List<Extraescolares> listaExtraescolares) {
        Controlador.listaExtraescolares = listaExtraescolares;
    }

    /**
     * Método para obtener la lista de la entidad Cursos
     * @return listaCursos
     */
    public static List<Cursos> getListaCursos() {
        return listaCursos;
    }

    /**
     * Método para establecer la lista de la entidad Cursos
     * @param listaCursos
     */
    public static void setListaCursos(List<Cursos> listaCursos) {
        Controlador.listaCursos = listaCursos;
    }

    /**
     * Método para obtener la lista de la entidad CursosAsignaturas
     * @return listaCursosAsignaturas
     */
    public static List<CursosAsignaturas> getListaCursosAsignaturas() {
        return listaCursosAsignaturas;
    }

    /**
     * Método para establecer la lista de la entidad CursosAsignaturas
     * @param listaCursosAsignaturas
     */
    public static void setListaCursosAsignaturas(List<CursosAsignaturas> listaCursosAsignaturas) {
        Controlador.listaCursosAsignaturas = listaCursosAsignaturas;
    }

    /**
     * Método para obtener la lista de la entidad Asignaturas
     * @return listaAsignaturas
     */
    public static List<Estudiantes> getListaEstudiantes() {
        return listaEstudiantes;
    }

    /**
     * Método para establecer la lista de la entidad Asignaturas
     * @param listaEstudiantes
     */
    public static void setListaEstudiantes(List<Estudiantes> listaEstudiantes) {
        Controlador.listaEstudiantes = listaEstudiantes;
    }

    /**
     * Método para obtener la lista de la entidad Matriculas
     * @return listaMatriculas
     */
    public static List<Matriculas> getListaMatriculas() {
        return listaMatriculas;
    }

    /**
     * Método para establecer la lista de la entidad Matriculas
     * @param listaMatriculas
     */
    public static void setListaMatriculas(List<Matriculas> listaMatriculas) {
        Controlador.listaMatriculas = listaMatriculas;
    }

    /**
     * Método para obtener la lista de la entidad HistorialAcademico
     * @return listaHistorialAcademico
     */
    public static List<HistorialAcademico> getListaHistorialAcademico() {
        return listaHistorialAcademico;
    }

    /**
     * Método para establecer la lista de la entidad HistorialAcademico
     * @param listaHistorialAcademico
     */
    public static void setListaHistorialAcademico(List<HistorialAcademico> listaHistorialAcademico) {
        Controlador.listaHistorialAcademico = listaHistorialAcademico;
    }

    /**
     * Método para obtener la lista de la entidad Asistencia
     * @return listaAsistencia
     */
    public static List<Asistencia> getListaAsistencia() {
        return listaAsistencia;
    }

    /**
     * Método para establecer la lista de la entidad Asistencia
     * @param listaAsistencia
     */
    public static void setListaAsistencia(List<Asistencia> listaAsistencia) {
        Controlador.listaAsistencia = listaAsistencia;
    }

    /**
     * Método para obtener la lista de la entidad Eventos
     * @return listaEventos
     */
    public static List<Eventos> getListaEventos() {
        return listaEventos;
    }

    /**
     * Método para establecer la lista de la entidad Eventos
     * @param listaEventos
     */
    public static void setListaEventos(List<Eventos> listaEventos) {
        Controlador.listaEventos = listaEventos;
    }

    /**
     * Método para obtener la lista de la entidad EstudiantesEventos
     * @return listaEstudiantesEventos
     */
    public static List<EstudiantesEventos> getListaEstudiantesEventos() {
        return listaEstudiantesEventos;
    }

    /**
     * Método para establecer la lista de la entidad EstudiantesEventos
     * @param listaEstudiantesEventos
     */
    public static void setListaEstudiantesEventos(List<EstudiantesEventos> listaEstudiantesEventos) {
        Controlador.listaEstudiantesEventos = listaEstudiantesEventos;
    }

    /**
     * Método para obtener la lista de la entidad Horarios
     * @return listaHorarios
     */
    public static List<Horarios> getListaHorarios() {
        return listaHorarios;
    }

    /**
     * Método para establecer la lista de la entidad Horarios
     * @param listaHorarios
     */
    public static void setListaHorarios(List<Horarios> listaHorarios) {
        Controlador.listaHorarios = listaHorarios;
    }

    /**
     * Método para obtener la lista de la entidad Becas
     * @return listaBecas
     */
    public static List<Becas> getListaBecas() {
        return listaBecas;
    }

    /**
     * Método para establecer la lista de la entidad Becas
     * @param listaBecas
     */
    public static void setListaBecas(List<Becas> listaBecas) {
        Controlador.listaBecas = listaBecas;
    }

    /**
     * Método para obtener la lista de la entidad Convalidaciones
     * @return listaConvalidaciones
     */
    public static List<Convalidaciones> getListaConvalidaciones() {
        return listaConvalidaciones;
    }

    /**
     * Método para establecer la lista de la entidad Convalidaciones
     * @param listaConvalidaciones
     */
    public static void setListaConvalidaciones(List<Convalidaciones> listaConvalidaciones) {
        Controlador.listaConvalidaciones = listaConvalidaciones;
    }

    /**
     * Método para obtener la lista de la entidad Administradores
     * @return listaAdministradores
     */
    public static List<Administradores> getListaAdministradores() {
        return listaAdministradores;
    }

    /**
     * Método para establecer la lista de la entidad Administradores
     * @param listaAdministradores
     */
    public static void setListaAdministradores(List<Administradores> listaAdministradores) {
        Controlador.listaAdministradores = listaAdministradores;
    }

    /**
     * Método para obtener la lista de la entidad Asignaturas
     * @return listaAsignaturas
     */
    public static List<Asignaturas> getListaAsignaturas() {
        return listaAsignaturas;
    }

    /**
     * Método para establecer la lista de la entidad Asignaturas
     * @param listaAsignaturas
     */
    public static void setListaAsignaturas(List<Asignaturas> listaAsignaturas) {
        Controlador.listaAsignaturas = listaAsignaturas;
    }


    // MÉTODOS DE INSERCIÓN

    /**
     * Método para insertar un nuevo estudiante en la base de datos
     * @param nuevoEstudiante
     */
    public static void insertarControladorEstudiante(Estudiantes nuevoEstudiante) {
        Inserciones.insertarEstudiante(session,nuevoEstudiante);
    }

    /**
     * Método para insertar un nuevo tutor en la base de datos
     * @param nuevoTutor
     */
    public static void insertarControladorTutor(Tutores nuevoTutor) {
        Inserciones.insertarTutores(session, nuevoTutor);
    }

    /**
     * Método para insertar un nuevo profesor en la base de datos
     * @param nuevoProfesor
     */
    public static void insertarControladorProfesor(Profesores nuevoProfesor) {
        Inserciones.insertarProfesor(session, nuevoProfesor);
    }

    /**
     * Método para insertar una nueva extraescolar en la base de datos
     * @param nuevoExtraescolar
     */
    public static void insertarControladorExtraescolar(Extraescolares nuevoExtraescolar) {
        Inserciones.insertarExtraescolares(session, nuevoExtraescolar);
    }

    /**
     * Método para insertar un nuevo curso en la base de datos
     * @param nuevoCurso
     */
    public static void insertarControladorCurso(Cursos nuevoCurso) {
        Inserciones.insertarCurso(session, nuevoCurso);
    }

    /**
     * Método para insertar una nueva asignatura en la base de datos
     * @param nuevaAsignatura
     */
    public static void insertarControladorAsignatura(Asignaturas nuevaAsignatura) {
        Inserciones.insertarAsignatura(session, nuevaAsignatura);
    }

    /**
     * Método para insertar una nueva matrícula en la base de datos
     * @param nuevaMatricula
     */
    public static void insertarControladorMatricula(Matriculas nuevaMatricula) {
        Inserciones.insertarMatricula(session, nuevaMatricula);
    }

    /**
     * Método para insertar un nuevo historial académico en la base de datos
     * @param nuevoHistorial
     */
    public static void insertarControladorHistorialAcademico(HistorialAcademico nuevoHistorial) {
        Inserciones.insertarHistorialAcademico(session, nuevoHistorial);
    }

    /**
     * Método para insertar una nueva asistencia en la base de datos
     * @param nuevaAsistencia
     */
    public static void insertarControladorAsistencia(Asistencia nuevaAsistencia) {
        Inserciones.insertarAsistencia(session, nuevaAsistencia);
    }

    /**
     * Método para insertar un nuevo evento en la base de datos
     * @param nuevoEvento
     */
    public static void insertarControladorEvento(Eventos nuevoEvento) {
        Inserciones.insertarEventos(session, nuevoEvento);
    }

    /**
     * Método para insertar un nuevo estudiante evento en la base de datos
     * @param nuevoEstEv
     */
    public static void insertarControladorEstudianteEvento(EstudiantesEventos nuevoEstEv) {
        Inserciones.insertarEstudiantesEventos(session, nuevoEstEv);
    }

    /**
     * Método para insertar un nuevo horario en la base de datos
     * @param nuevoHorario
     */
    public static void insertarControladorHorario(Horarios nuevoHorario) {
        Inserciones.insertarHorarios(session, nuevoHorario);
    }

    /**
     * Método para insertar una nueva beca en la base de datos
     * @param nuevaBeca
     */
    public static void insertarControladorBeca(Becas nuevaBeca) {
        Inserciones.insertarBeca(session, nuevaBeca);
    }

    /**
     * Método para insertar una nueva convalidación en la base de datos
     * @param nuevaConvalidacion
     */
    public static void insertarControladorConvalidacion(Convalidaciones nuevaConvalidacion) {
        Inserciones.insertarConvalidacion(session, nuevaConvalidacion);
    }


    //MÉTODOS DE ACTUALIZACIÓN

    /**
     * Método para actualizar un estudiante en la base de datos
     * @param nuevoEstudiante
     */
    public static void actualizarControladorEstudiante(Estudiantes nuevoEstudiante) {
        Actualizaciones.actualizarEstudiante(session,nuevoEstudiante);
    }

    /**
     * Método para actualizar un tutor en la base de datos
     * @param tutorActualizado
     */
    public static void actualizarControladorTutor(Tutores tutorActualizado) {
        Actualizaciones.actualizarTutor(session, tutorActualizado);
    }

    /**
     * Método para actualizar un profesor en la base de datos
     * @param profesorActualizado
     */
    public static void actualizarControladorProfesor(Profesores profesorActualizado) {
        Actualizaciones.actualizarProfesor(session, profesorActualizado);
    }

    /**
     * Método para actualizar una extraescolar en la base de datos
     * @param extraescolarActualizado
     */
    public static void actualizarControladorExtraescolar(Extraescolares extraescolarActualizado) {
        Actualizaciones.actualizarExtraescolar(session, extraescolarActualizado);
    }

    /**
     * Método para actualizar un curso en la base de datos
     * @param cursoActualizado
     */
    public static void actualizarControladorCurso(Cursos cursoActualizado) {
        Actualizaciones.actualizarCurso(session, cursoActualizado);
    }

    /**
     * Método para actualizar una asignatura en la base de datos
     * @param asignaturaActualizada
     */
    public static void actualizarControladorAsignatura(Asignaturas asignaturaActualizada) {
        Actualizaciones.actualizarAsignatura(session, asignaturaActualizada);
    }

    /**
     * Método para actualizar una matrícula en la base de datos
     * @param matriculaActualizada
     */
    public static void actualizarControladorMatricula(Matriculas matriculaActualizada) {
        Actualizaciones.actualizarMatricula(session, matriculaActualizada);
    }

    /**
     * Método para actualizar un historial académico en la base de datos
     * @param historialActualizado
     */
    public static void actualizarControladorHistorialAcademico(HistorialAcademico historialActualizado) {
        Actualizaciones.actualizarHistorialAcademico(session, historialActualizado);
    }

    /**
     * Método para actualizar una asistencia en la base de datos
     * @param asistenciaActualizada
     */
    public static void actualizarControladorAsistencia(Asistencia asistenciaActualizada) {
        Actualizaciones.actualizarAsistencia(session, asistenciaActualizada);
    }

    /**
     * Método para actualizar un evento en la base de datos
     * @param eventoActualizado
     */
    public static void actualizarControladorEvento(Eventos eventoActualizado) {
        Actualizaciones.actualizarEvento(session, eventoActualizado);
    }

    /**
     * Método para actualizar un estudiante evento en la base de datos
     * @param estEvActualizado
     */
    public static void actualizarControladorEstudianteEvento(EstudiantesEventos estEvActualizado) {
        Actualizaciones.actualizarEstudianteEvento(session, estEvActualizado);
    }

    /**
     * Método para actualizar un horario en la base de datos
     * @param horarioActualizado
     */
    public static void actualizarControladorHorario(Horarios horarioActualizado) {
        Actualizaciones.actualizarHorario(session, horarioActualizado);
    }

    /**
     * Método para actualizar una beca en la base de datos
     * @param becaActualizada
     */
    public static void actualizarControladorBeca(Becas becaActualizada) {
        Actualizaciones.actualizarBeca(session, becaActualizada);
    }

    /**
     * Método para actualizar una convalidación en la base de datos
     * @param convalidacionActualizada
     */
    public static void actualizarControladorConvalidacion(Convalidaciones convalidacionActualizada) {
        Actualizaciones.actualizarConvalidacion(session, convalidacionActualizada);
    }

    /**
     * Método para actualizar un administrador en la base de datos
     * @param administradorActualizado
     */
    public static void actualizarControladorAdministrador(Administradores administradorActualizado) {
        Actualizaciones.actualizarAdministrador(session, administradorActualizado);
    }


    //MÉTODOS DE ELIMINACIÓN

    /**
     * Método para eliminar un estudiante de la base de datos
     * @param estudiante
     */
    public static void eliminarControladorEstudiante(Estudiantes estudiante) {
        Eliminaciones.deleteEstudiantes(session, estudiante);
    }

    /**
     * Método para eliminar un tutor de la base de datos
     * @param tutor
     */
    public static void eliminarControladorTutor(Tutores tutor) {
        Eliminaciones.deleteTutores(session, tutor);
    }

    /**
     * Método para eliminar un profesor de la base de datos
     * @param profesor
     */
    public static void eliminarControladorProfesor(Profesores profesor) {
        Eliminaciones.deleteProfesores(session, profesor);
    }

    /**
     * Método para eliminar una extraescolar de la base de datos
     * @param extraescolar
     */
    public static void eliminarControladorExtraescolar(Extraescolares extraescolar) {
        Eliminaciones.deleteExtraescolares(session, extraescolar);
    }

    /**
     * Método para eliminar un curso de la base de datos
     * @param curso
     */
    public static void eliminarControladorCurso(Cursos curso) {
        Eliminaciones.deleteCursos(session, curso);
    }

    /**
     * Método para eliminar una asignatura de la base de datos
     * @param asignatura
     */
    public static void eliminarControladorAsignatura(Asignaturas asignatura) {
        Eliminaciones.deleteAsignaturas(session, asignatura);
    }

    /**
     * Método para eliminar una matrícula de la base de datos
     * @param matricula
     */
    public static void eliminarControladorMatricula(Matriculas matricula) {
        Eliminaciones.deleteMatriculas(session, matricula);
    }

    /**
     * Método para eliminar un historial académico de la base de datos
     * @param historial
     */
    public static void eliminarControladorHistorialAcademico(HistorialAcademico historial) {
        Eliminaciones.deleteHistorialAcademico(session, historial);
    }

    /**
     * Método para eliminar una asistencia de la base de datos
     * @param asistencia
     */
    public static void eliminarControladorAsistencia(Asistencia asistencia) {
        Eliminaciones.deleteAsistencia(session, asistencia);
    }

    /**
     * Método para eliminar un evento de la base de datos
     * @param evento
     */
    public static void eliminarControladorEvento(Eventos evento) {
        Eliminaciones.deleteEventos(session, evento);
    }

    /**
     * Método para eliminar un estudiante evento de la base de datos
     * @param estudianteEvento
     */
    public static void eliminarControladorEstudianteEvento(EstudiantesEventos estudianteEvento) {
        Eliminaciones.deleteEstudiantesEventos(session, estudianteEvento);
    }

    /**
     * Método para eliminar un horario de la base de datos
     * @param horario
     */
    public static void eliminarControladorHorario(Horarios horario) {
        Eliminaciones.deleteHorarios(session, horario);
    }

    /**
     * Método para eliminar una beca de la base de datos
     * @param beca
     */
    public static void eliminarControladorBeca(Becas beca) {
        Eliminaciones.deleteBecas(session, beca);
    }

    /**
     * Método para eliminar una convalidación de la base de datos
     * @param convalidacion
     */
    public static void eliminarControladorConvalidacion(Convalidaciones convalidacion) {
        Eliminaciones.deleteConvalidaciones(session, convalidacion);
    }

    /**
     * Método para eliminar un administrador de la base de datos
     * @param administrador
     */
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