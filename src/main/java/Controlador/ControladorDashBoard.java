package Controlador;

import Mapeo.*;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import static Controlador.Controlador.getListaEstudiantesEventos;
import static Controlador.Controlador.getListaEventos;

/**
 * ControladorDashBoard es una clase que contiene métodos estáticos para obtener información
 * para el dashboard.
 */
public class ControladorDashBoard {

    //DASHBOARD DE ADMINISTRADOR

    /**
     * Devuelve el número total de alumnos.
     * @return el número total de alumnos.
     */
    public static int numeroAlumnos() {
        return Controlador.getListaEstudiantes().size();
    }

    /**
     * Devuelve el número total de becas inactivas.
     * @return el número total de becas inactivas.
     */
    public static int becasInactivas() {
        int contador = 0;
        for (int i = 0; i < Controlador.getListaBecas().size(); i++) {
            if (Controlador.getListaBecas().get(i).getEstadoBeca().toString().trim().equalsIgnoreCase("inactivo")) {
                contador++;
            }
        }
        return contador;
    }

    /**
     * Devuelve el número total de alumnos sin beca.
     * @return el número total de alumnos sin beca.
     */
    public static int numAlumnosSinBeca() {
        return Controlador.getListaEstudiantes().size() - Controlador.getListaBecas().size();
    }

    /**
     * Devuelve el número total de alumnos con beca.
     * @return el número total de alumnos con beca.
     */
    public static int numAlumnosConBeca() {
        return Controlador.getListaBecas().size();
    }

    /**
     * Devuelve el número total de alumnos sin tutor, osea son mayores de edad.
     * @return el número total de alumnos sin tutor o mayores de edad.
     */
    public static int AlumnosSintutor() {
        int contador = 0;
        for (int i = 0; i < Controlador.getListaEstudiantes().size(); i++) {
            if (Controlador.getListaEstudiantes().get(i).getTutor() == null) {
                contador++;
            }
        }
        return contador;
    }

    /**
     * Devuelve el número total de alumnos con tutor, osea son menores de edad.
     * @return el número total de alumnos con tutor o menores de edad.
     */
    public static int AlumnosConTutor() {
        int contador = 0;
        for (int i = 0; i < Controlador.getListaEstudiantes().size(); i++) {
            if (Controlador.getListaEstudiantes().get(i).getTutor() != null) {
                contador++;
            }
        }
        return contador;
    }

    /**
     * Devuelve el número total de profesores.
     * @return el número total de profesores.
     */
    public static int numeroProfesores() {
        return Controlador.getListaProfesores().size();
    }

    /**
     * Devuelve el tutor con mas estudiantes.
     * @return el tutor con mas estudiantes.
     */
    public static Profesores profesorConMasEstudiantes() {
        Map<String, Integer> contador = new HashMap<>();

        for (Matriculas matriculas : Controlador.getListaMatriculas()) {
            String nombreProfesor = matriculas.getCurso().getProfesor().getNombre();
            contador.put(nombreProfesor, contador.getOrDefault(nombreProfesor, 0) + 1);
        }

        Profesores profesorTop = null;
        int max = 0;

        for (Map.Entry<String, Integer> entry : contador.entrySet()) {
            if (entry.getValue() > max) {
                max = entry.getValue();
                for (Profesores prof : Controlador.getListaProfesores()) {
                    if (prof.getNombre().equals(entry.getKey())) {
                        profesorTop = prof;
                        break;
                    }
                }
            }
        }
        return profesorTop;
    }

    /**
     * Devuelve el número total de matrículas.
     * @return el número total de matrículas.
     */
    public static int numMatriculas() {
        return Controlador.getListaMatriculas().size();
    }

    /**
     * Devuelve el número total de matrículas inactivas.
     * @return el número total de matrículas inactivas.
     */
    public static int matriculasInactivas() {
        int contador = 0;
        for (int i = 0; i < Controlador.getListaMatriculas().size(); i++) {
            if (Controlador.getListaMatriculas().get(i).getEstado().toString().equalsIgnoreCase("inactivo")) {
                contador++;
            }
        }
        return contador;
    }

    /**
     * Devuelve el número total de estudiantes sin matrícula activa.
     * @return el número total de estudiantes sin matrícula activa.
     */
    public static int numAlumnosSinMatricula() {
        return Controlador.getListaMatriculas().size() - Controlador.getListaBecas().size();
    }

    /**
     * Devuelve el promedio de notas de todos los estudiantes.
     * @return el promedio de notas de todos los estudiantes.
     */
    public static BigDecimal promedioNotas() {
        BigDecimal suma = BigDecimal.ZERO;
        for (int i = 0; i < Controlador.getListaHistorialAcademico().size(); i++) {
            suma = suma.add(Controlador.getListaHistorialAcademico().get(i).getNotaFinal());
        }
        return suma.divide(BigDecimal.valueOf(Controlador.getListaHistorialAcademico().size()), 2, BigDecimal.ROUND_HALF_UP);
    }

    /**
     * Devuelve el estudiante con la mayor nota.
     * @return el estudiante con la mayor nota.
     */
    public static Estudiantes mayorNota() {
        BigDecimal mayorNota = BigDecimal.ZERO;
        int posicion = 0;
        for (int i = 0; i < Controlador.getListaHistorialAcademico().size(); i++) {
            if (Controlador.getListaHistorialAcademico().get(i).getNotaFinal().compareTo(mayorNota) > 0) {
                mayorNota = Controlador.getListaHistorialAcademico().get(i).getNotaFinal();
                posicion = i;
            }
        }
        return Controlador.getListaHistorialAcademico().get(posicion).getEstudiante();
    }

    /**
     * Devuelve el número total de alumnos en recuperación.
     * @return el número total de alumnos en recuperación.
     */
    public static int numAlumnosRecuperacion() {
        int contador = 0;
        for (int i = 0; i < Controlador.getListaHistorialAcademico().size(); i++) {
            if (Controlador.getListaHistorialAcademico().get(i).getNotaFinal().compareTo(BigDecimal.valueOf(5)) < 0) {
                contador++;
            }
        }
        return contador;
    }

    /**
     * Devuelve el número total de alumnos con asistencia no justificada.
     * @return el número total de alumnos con asistencia no justificada.
     */
    public static int numAsistenciaNoJustificada() {
        int contador = 0;
        for (int i = 0; i < Controlador.getListaAsistencia().size(); i++) {
            if (Controlador.getListaAsistencia().get(i).getJustificado().equals(false)) {
                contador++;
            }
        }
        return contador;
    }


    //DASHBOARD DE PROFESOR

    /**
     * Devuelve el número total de alumnos que tiene un profesor.
     * @param profesor el profesor del que se quiere saber el número de alumnos.
     * @return el número total de alumnos que tiene un profesor.
     */
    public static int numeroAlumnosProfesor(Profesores profesor) {
        int contador = 0;
        for (int i = 0; i < Controlador.getListaMatriculas().size(); i++) {
            if (Controlador.getListaMatriculas().get(i).getCurso().getProfesor().equals(profesor)) {
                contador++;
            }
        }
        return contador;
    }

    /**
     * Devuelve el número total de alumnos en recuperación que tiene un profesor.
     * @param profesor el profesor del que se quiere saber el número de alumnos en recuperación.
     * @return el número total de alumnos en recuperación que tiene un profesor.
     */
    public static int numAlumnosRecuperacionProfesor(Profesores profesor) {
        int contador = 0;
        for (int i = 0; i < Controlador.getListaHistorialAcademico().size(); i++) {
            HistorialAcademico historial = Controlador.getListaHistorialAcademico().get(i);
            if (historial.getAsignatura().getProfesor().equals(profesor) && historial.getNotaFinal().compareTo(BigDecimal.valueOf(5)) < 0) {
                contador++;
            }
        }
        return contador;
    }

    /**
     * Devuelve el promedio de notas de los estudiantes de un profesor
     * @param profesor el profesor del que se quiere saber el promedio de notas.
     * @return el promedio de notas de los estudiantes de un profesor.
     */
    public static BigDecimal promedioNotasProfesor(Profesores profesor) {
        BigDecimal sumaNotas = BigDecimal.ZERO;
        int contadorNotas = 0;

        for (int i = 0; i < Controlador.getListaHistorialAcademico().size(); i++) {
            HistorialAcademico historial = Controlador.getListaHistorialAcademico().get(i);
            if (historial.getAsignatura().getProfesor().equals(profesor)) {
                sumaNotas = sumaNotas.add(historial.getNotaFinal());
                contadorNotas++;
            }
        }

        BigDecimal promedio;
        if (contadorNotas > 0) {
            promedio = sumaNotas.divide(BigDecimal.valueOf(contadorNotas), 2, BigDecimal.ROUND_HALF_UP);
        } else {
            promedio = BigDecimal.ZERO;
        }

        return promedio;
    }

    /**
     * Devuelve el número total de alumnos que tienen una nota sobresaliente.
     * @param profesor el profesor del que se quiere saber el número de alumnos con nota sobresaliente.
     * @return el número total de alumnos que tienen una nota sobresaliente.
     */
    public static int numAlumnosSobresalienteProfesor(Profesores profesor) {
        int contador = 0;
        for (int i = 0; i < Controlador.getListaHistorialAcademico().size(); i++) {
            HistorialAcademico historial = Controlador.getListaHistorialAcademico().get(i);
            if (historial.getAsignatura().getProfesor().equals(profesor) && historial.getNotaFinal().compareTo(BigDecimal.valueOf(9)) >= 0) {
                contador++;
            }
        }
        return contador;
    }

    /**
     * Devuelve el número de asistencias no justificadas de los estudiantes del profesor
     * @param profesor el profesor del que se quiere saber el número de asistencias no justificadas.
     * @return el número total de asistencias no justificadas de los estudiantes del profesor.
     */
    public static int numAsistenciaNoJustificadaProfesor(Profesores profesor) {
        int contador = 0;
        for (int i = 0; i < Controlador.getListaAsistencia().size(); i++) {
            if (Controlador.getListaAsistencia().get(i).getCurso().getProfesor().equals(profesor) && Controlador.getListaAsistencia().get(i).getJustificado().equals(false)) {
                contador++;
            }
        }
        return contador;
    }

    /**
     * Devuelve el numero de horas de clase que imparte el profesor a la semana
     * @param profesor el profesor del que se quiere saber el número de horas de clase.
     * @return el número total de horas de clase que imparte el profesor a la semana.
     */
    public static int numHorasTrabajadasProfesor(Profesores profesor){
       int horasTrabajadas = 0;
       horasTrabajadas = profesor.getHorarios().size();


        return horasTrabajadas;
    }

    //DASHBOARD DE ESTUDIANTE

    /**
     * Devuelve la nota media de las asignaturas de un estudiante.
     * @param estudiante el estudiante del que se quiere saber la nota media.
     * @return la nota media de las asignaturas de un estudiante.
     */
    public static int notaMediaAsignaturasEstudiante(Estudiantes estudiante) {
        BigDecimal suma = BigDecimal.ZERO;
        int contador = 0;
        for (int i = 0; i < Controlador.getListaHistorialAcademico().size(); i++) {
            if (Controlador.getListaHistorialAcademico().get(i).getEstudiante().equals(estudiante)) {
                suma = suma.add(Controlador.getListaHistorialAcademico().get(i).getNotaFinal());
                contador++;
            }
        }
        return contador > 0 ? suma.divide(BigDecimal.valueOf(contador), 2, BigDecimal.ROUND_HALF_UP).intValue() : 0;
    }

    /**
     * Devuelve el número de asignaturas suspensadas de un estudiante.
     * @param estudiante el estudiante del que se quiere saber el número de asignaturas suspensadas.
     * @return el número de asignaturas suspensadas de un estudiante.
     */
    public static int numAsignaturasSuspensasEstudiante(Estudiantes estudiante) {
        int contador = 0;
        for (int i = 0; i < Controlador.getListaHistorialAcademico().size(); i++) {
            if (Controlador.getListaHistorialAcademico().get(i).getEstudiante().equals(estudiante) && Controlador.getListaHistorialAcademico().get(i).getNotaFinal().compareTo(BigDecimal.valueOf(5)) < 0) {
                contador++;
            }
        }
        return contador;
    }

    /**
     * Devuelve el número de asignaturas aprobadas de un estudiante.
     * @param estudiante el estudiante del que se quiere saber el número de asignaturas aprobadas.
     * @return el número de asignaturas aprobadas de un estudiante.
     */
    public static int numAsignaturasAprobadasEstudiante(Estudiantes estudiante) {
        int contador = 0;
        for (int i = 0; i < Controlador.getListaHistorialAcademico().size(); i++) {
            if (Controlador.getListaHistorialAcademico().get(i).getEstudiante().equals(estudiante) && Controlador.getListaHistorialAcademico().get(i).getNotaFinal().compareTo(BigDecimal.valueOf(5)) >= 0) {
                contador++;
            }
        }
        return contador;
    }

    /**
     * Devuelve el número de faltas sin justificar de un estudiante.
     * @param estudiante el estudiante del que se quiere saber el número de faltas sin justificar.
     * @return el número de faltas sin justificar de un estudiante.
     */
    public static int numFaltasSinJustificarEstudiante(Estudiantes estudiante) {
        int contador = 0;
        for (int i = 0; i < Controlador.getListaAsistencia().size(); i++) {
            if (Controlador.getListaAsistencia().get(i).getEstudiante().equals(estudiante) && Controlador.getListaAsistencia().get(i).getJustificado().equals(false)) {
                contador++;
            }
        }
        return contador;
    }

    /**
     * Devuelve el número de faltas justificadas de un estudiante.
     * @param estudiante el estudiante del que se quiere saber el número de faltas justificadas.
     * @return el número de faltas justificadas de un estudiante.
     */
    public static int numFaltasJustificadasEstudiante(Estudiantes estudiante) {
        int contador = 0;
        for (int i = 0; i < Controlador.getListaAsistencia().size(); i++) {
            if (Controlador.getListaAsistencia().get(i).getEstudiante().equals(estudiante) && Controlador.getListaAsistencia().get(i).getJustificado().equals(true)) {
                contador++;
            }
        }
        return contador;
    }

    /**
     * Devuelve el número de eventos por confirmar de un estudiante.
     * @param estudiante el estudiante del que se quiere saber el número de eventos por confirmar.
     * @return el número de eventos por confirmar de un estudiante.
     */
    public static int numEventosPorConfirmarEstudiante(Estudiantes estudiante) {
        int eventosActivos = getListaEventos().size();
        for(Eventos eventos : getListaEventos()) {
            for(EstudiantesEventos ee : getListaEstudiantesEventos()) {
                if(ee.getEstudiante().equals(estudiante) && ee.getEvento().equals(eventos)) {
                    eventosActivos--;
                }
            }
        }
        return eventosActivos;
    }
}