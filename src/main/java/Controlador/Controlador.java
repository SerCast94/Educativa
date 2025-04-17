package Controlador;

import BBDD.Conexion;
import org.hibernate.Session;
import java.util.List;
import Mapeo.*;
import BBDD.Consultas;

public class Controlador {

    static Session session;

    static public List<Tutores> listaTutores;
    static public List<Profesores> listaProfesores;
    static public List<Extraescolares> listaExtraescolares;
    static public List<Cursos> listaCursos;
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
}
