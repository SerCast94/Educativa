package Controlador;

import Mapeo.Estudiantes;
import Mapeo.HistorialAcademico;
import Mapeo.Matriculas;
import Mapeo.Profesores;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class ControladorDashBoard {


    //DASHBOARD DE ADMINISTRADOR
    public static int numeroAlumnos() {
        return Controlador.getListaEstudiantes().size();
    }

    public static int becasInactivas() {
        int contador = 0;
        for (int i = 0; i < Controlador.getListaBecas().size(); i++) {
            if (Controlador.getListaBecas().get(i).getEstadoBeca().toString().trim().equalsIgnoreCase("inactivo")) {
                contador++;
            }
        }
        return contador;
    }

    public static int numAlumnosSinBeca() {
        return Controlador.getListaEstudiantes().size() - Controlador.getListaBecas().size();
    }

    public static int numAlumnosConBeca() {
        return Controlador.getListaBecas().size();
    }

    public static int AlumnosSintutor() {
        int contador = 0;
        for (int i = 0; i < Controlador.getListaEstudiantes().size(); i++) {
            if (Controlador.getListaEstudiantes().get(i).getTutor() == null) {
                contador++;
            }
        }
        return contador;
    }

    public static int AlumnosConTutor() {
        int contador = 0;
        for (int i = 0; i < Controlador.getListaEstudiantes().size(); i++) {
            if (Controlador.getListaEstudiantes().get(i).getTutor() != null) {
                contador++;
            }
        }
        return contador;
    }

    public static int numeroProfesores() {
        return Controlador.getListaProfesores().size();
    }

    public static Profesores profesorConMasEstudiantes() {
        Map<String, Integer> contador = new HashMap<>();

        for (Matriculas est : Controlador.getListaMatriculas()) {
            String nombreProfesor = est.getCurso().getProfesor().getNombre();
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

    public static int numMatriculas() {
        return Controlador.getListaMatriculas().size();
    }

    public static int matriculasInactivas() {
        int contador = 0;
        for (int i = 0; i < Controlador.getListaMatriculas().size(); i++) {
            if (Controlador.getListaMatriculas().get(i).getEstado().toString().equalsIgnoreCase("inactivo")) {
                contador++;
            }
        }
        return contador;
    }

    public static int numAlumnosSinMatricula() {
        return Controlador.getListaMatriculas().size() - Controlador.getListaBecas().size();
    }

    public static BigDecimal promedioNotas() {
        BigDecimal suma = BigDecimal.ZERO;
        for (int i = 0; i < Controlador.getListaHistorialAcademico().size(); i++) {
            suma = suma.add(Controlador.getListaHistorialAcademico().get(i).getNotaFinal());
        }
        return suma.divide(BigDecimal.valueOf(Controlador.getListaHistorialAcademico().size()), 2, BigDecimal.ROUND_HALF_UP);
    }

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

    public static int numAlumnosRecuperacion() {
        int contador = 0;
        for (int i = 0; i < Controlador.getListaHistorialAcademico().size(); i++) {
            if (Controlador.getListaHistorialAcademico().get(i).getNotaFinal().compareTo(BigDecimal.valueOf(5)) < 0) {
                contador++;
            }
        }
        return contador;
    }

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

    public static int numeroAlumnosProfesor(Profesores profesor) {
        int contador = 0;
        for (int i = 0; i < Controlador.getListaMatriculas().size(); i++) {
            if (Controlador.getListaMatriculas().get(i).getCurso().getProfesor().equals(profesor)) {
                contador++;
            }
        }
        return contador;
    }

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

        return contadorNotas > 0
                ? sumaNotas.divide(BigDecimal.valueOf(contadorNotas), 2, BigDecimal.ROUND_HALF_UP)
                : BigDecimal.ZERO;
    }

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

    public static int numAsistenciaNoJustificadaProfesor(Profesores profesor) {
        int contador = 0;
        for (int i = 0; i < Controlador.getListaAsistencia().size(); i++) {
            if (Controlador.getListaAsistencia().get(i).getCurso().getProfesor().equals(profesor) && Controlador.getListaAsistencia().get(i).getJustificado().equals(false)) {
                contador++;
            }
        }
        return contador;
    }

    public static int numHorasTrabajadasProfesor(Profesores profesor){
       int horasTrabajadas = 0;
       horasTrabajadas = profesor.getHorarios().size();

        System.out.println(horasTrabajadas);

        return horasTrabajadas;
    }

}
