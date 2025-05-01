package Controlador;

import Mapeo.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.List;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class ControladorReportes {

    static Map<String, String> datosBoletin = new HashMap<>();
    static Map<String, String> datosBeca = new HashMap<>();
    static Map<String, String> datosConvalidacion = new HashMap<>();

    public static Map<String, String> enviarInfoParaBoletin(Estudiantes estudiante) {


        datosBoletin.put("nombre", estudiante.getNombre() + " " + estudiante.getApellido());
        datosBoletin.put("curso", estudiante.getMatriculas().get(0).getCurso().getNombre());
        datosBoletin.put("tutor", estudiante.getMatriculas().get(0).getCurso().getProfesor().toString());

        List<HistorialAcademico> historialAcademico = estudiante.getHistorialAcademico();

        for (int i = 0; i < historialAcademico.size() || i == 5; i++) {
            HistorialAcademico historial = historialAcademico.get(i);
            datosBoletin.put("asignatura" + (i + 1), historial.getAsignatura().getNombre());
            datosBoletin.put("nota" + (i + 1), String.valueOf(historial.getNotaFinal()));
        }

        datosBoletin.put("media", calcularMedia(estudiante));
        datosBoletin.put("faltasjustificadas", String.valueOf(obtenerFaltasJustificadas(estudiante)));
        datosBoletin.put("faltasinjustificadas", String.valueOf(obtenerFaltasInjustificadas(estudiante)));
        datosBoletin.put("comentarios", historialAcademico.isEmpty() ? "" : historialAcademico.get(0).getComentarios());
        datosBoletin.put("today", java.time.LocalDate.now().toString());

        return datosBoletin;
    }


    private static long obtenerFaltasJustificadas(Estudiantes estudiante) {
        int faltasJustificadas = 0;
        for (Asistencia asistencia : estudiante.getAsistencias()) {
            if (asistencia.getJustificado()) {
                faltasJustificadas++;
            }
        }
        return faltasJustificadas;
    }

    private static long obtenerFaltasInjustificadas(Estudiantes estudiante) {
        int faltasInjustificadas = 0;
        for (Asistencia asistencia : estudiante.getAsistencias()) {
            if (!asistencia.getJustificado()) {
                faltasInjustificadas++;
            }
        }
        return faltasInjustificadas;
    }

    private static String calcularMedia(Estudiantes estudiante) {
        BigDecimal sumaNotas = BigDecimal.ZERO;
        int cantidadNotas = 0;

        for (HistorialAcademico historial : estudiante.getHistorialAcademico()) {
            sumaNotas = sumaNotas.add(historial.getNotaFinal());
            cantidadNotas++;
        }

        BigDecimal media;
        if (cantidadNotas > 0) {
            media = sumaNotas.divide(BigDecimal.valueOf(cantidadNotas), 2, BigDecimal.ROUND_HALF_UP);
        } else {
            media = BigDecimal.ZERO;
        }

        return media.toString();
    }

    public static Map<String, String> enviarInfoParaCertificadoBeca(Estudiantes estudiante) {

        datosBeca.put("nombreAdmin", Controlador.getListaAdministradores().get(0).toString());
        datosBeca.put("nombreEstudiante", estudiante.toString());
        datosBeca.put("dni", estudiante.getDni());
        datosBeca.put("monto", estudiante.getBecas().get(0).getMonto().toString() + " €");
        datosBeca.put("motivo", estudiante.getBecas().get(0).getTipoBeca().toString());
        datosBeca.put("curso", estudiante.getMatriculas().get(0).getCurso().getNombre());

        LocalDate hoy = LocalDate.now();
        datosBeca.put("dia", String.valueOf(hoy.getDayOfMonth()));
        datosBeca.put("mes", hoy.getMonth().getDisplayName(TextStyle.FULL, new Locale("es", "ES")));
        datosBeca.put("anio", String.valueOf(hoy.getYear()));

        return datosBeca;
    }

    public static Map<String, String> enviarInfoParaCertificadoConvalidacion(Estudiantes estudiante) {
        Map<String, String> datosConvalidacion = new HashMap<>();

        datosConvalidacion.put("NombreAdmin", Controlador.getListaAdministradores().get(0).toString());
        datosConvalidacion.put("nombreEstudiante", estudiante.toString());
        datosConvalidacion.put("dni", estudiante.getDni());
        String asignatura = estudiante.getConvalidaciones().get(0).getAsignaturaOriginal().getNombre();
        int longitud = 40;
        if (asignatura.length() > longitud) {
            asignatura = asignatura.substring(0, longitud) + "...";
        }
        datosConvalidacion.put("asignatura", asignatura);
        LocalDate hoy = LocalDate.now();
        datosConvalidacion.put("dia", String.valueOf(hoy.getDayOfMonth()));
        datosConvalidacion.put("mes", hoy.getMonth().getDisplayName(TextStyle.FULL, new Locale("es", "ES")));
        datosConvalidacion.put("anio", String.valueOf(hoy.getYear()));
        datosConvalidacion.put("nombreAdmin", Controlador.getListaAdministradores().get(0).toString());


        return datosConvalidacion;
    }



}