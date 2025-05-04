package Mapeo;

import java.io.Serializable;
import java.util.Objects;

/**
 * Clase que representa la clave compuesta para la relación entre Cursos y Asignaturas.
 * Esta clase es utilizada como identificador en la tabla CursosAsignaturas.
 */
public class EstudiantesEventosId implements Serializable {
    private Integer estudiante;
    private Integer evento;

    // CONSTRUCTORES
    /**
     * Constructor por defecto de la clase EstudiantesEventosId.
     */
    public EstudiantesEventosId() {
    }

    /**
     * Constructor de la clase EstudiantesEventosId con los atributos estudiante y evento.
     * @param estudiante ID del estudiante asociado al evento.
     * @param evento     ID del evento asociado al estudiante.
     */
    public EstudiantesEventosId(Integer estudiante, Integer evento) {
        setEstudiante(estudiante);
        setEvento(evento);
    }

    // GETTERS Y SETTERS

    /**
     * Obtiene el ID del estudiante.
     * @return ID del estudiante.
     */
    public Integer getEstudiante() {
        return estudiante;
    }

    /**
     * Establece el ID del estudiante con validaciones.
     * @param estudiante ID del estudiante.
     */
    public void setEstudiante(Integer estudiante) {
        if (estudiante == null) {
            throw new IllegalArgumentException("El estudiante no puede ser nulo.");
        }else{
            this.estudiante = estudiante;
        }
    }

    /**
     * Obtiene el ID del evento.
     * @return ID del evento.
     */
    public Integer getEvento() {
        return evento;
    }

    /**
     * Establece el ID del evento con validaciones.
     * @param evento ID del evento.
     */
    public void setEvento(Integer evento) {
        if (evento == null) {
            throw new IllegalArgumentException("El evento no puede ser nulo.");
        }else{
            this.evento = evento;
        }
    }

    //MÉTODOS EQUAL Y HASHCODE

    /**
     * Compara dos objetos EstudiantesEventosId para verificar si son iguales.
     * @param o Objeto a comparar.
     * @return true si son iguales, false en caso contrario.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EstudiantesEventosId)) return false;
        EstudiantesEventosId that = (EstudiantesEventosId) o;
        return Objects.equals(estudiante, that.estudiante) &&
                Objects.equals(evento, that.evento);
    }

    /**
     * Devuelve el código hash del objeto EstudiantesEventosId.
     * @return Código hash del objeto.
     */
    @Override
    public int hashCode() {
        return Objects.hash(estudiante, evento);
    }
}
