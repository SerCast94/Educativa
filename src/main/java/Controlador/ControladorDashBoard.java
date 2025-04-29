package Controlador;

import Mapeo.Estudiantes;
import Mapeo.Matriculas;
import Mapeo.Profesores;
import java.util.HashMap;
import java.util.Map;

public class ControladorDashBoard {

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

    public static double promedioNotas() {
        double suma = 0;
        for (int i = 0; i < Controlador.getListaHistorialAcademico().size(); i++) {
            suma += Controlador.getListaHistorialAcademico().get(i).getNotaFinal();
        }
        return suma / Controlador.getListaHistorialAcademico().size();
    }

    public static Estudiantes mayorNota() {
        double mayorNota = 0;
        int posicion = 0;
        for (int i = 0; i < Controlador.getListaHistorialAcademico().size(); i++) {
            if (Controlador.getListaHistorialAcademico().get(i).getNotaFinal() > mayorNota) {
                mayorNota = Controlador.getListaHistorialAcademico().get(i).getNotaFinal();
                posicion = i;
            }
        }
        return Controlador.getListaHistorialAcademico().get(posicion).getEstudiante();
    }

    public static int numAlumnosRecuperacion() {
        int contador = 0;
        for (int i = 0; i < Controlador.getListaHistorialAcademico().size(); i++) {
            if (Controlador.getListaHistorialAcademico().get(i).getNotaFinal() < 5) {
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

}
