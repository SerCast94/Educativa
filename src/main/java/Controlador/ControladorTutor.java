package Controlador;

import Mapeo.Estudiantes;
import Mapeo.Tutores;

import java.util.List;

public class ControladorTutor {

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
