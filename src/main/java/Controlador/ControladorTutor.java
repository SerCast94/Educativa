package Controlador;

import Mapeo.Estudiantes;
import Mapeo.Tutores;
import java.util.List;

/**
 * Controlador para la gestión de tutores y sus estudiantes.
 * Este controlador se encarga de obtener la lista de estudiantes
 * asignados a un tutor específico.
 */
public class ControladorTutor {

    /**
     * Obtiene la lista de estudiantes asignados a un tutor específico.
     *
     * @param tutorLogeado El tutor del cual se desea obtener la lista de estudiantes.
     * @return Una lista de estudiantes asignados al tutor.
     */
    public static List<Estudiantes> getListaEstudiantesDelTutor(Tutores tutorLogeado) {
        List<Estudiantes> listaEstudiantes = tutorLogeado.getEstudiantes();
        List<Estudiantes> listaEstudiantesDelTutor = new java.util.ArrayList<>();

        for (Estudiantes estudiante : listaEstudiantes) {
            if(estudiante.getTutor().equals(tutorLogeado)){
                listaEstudiantesDelTutor.add(estudiante);
            }
        }
        return listaEstudiantesDelTutor;
    }
}
