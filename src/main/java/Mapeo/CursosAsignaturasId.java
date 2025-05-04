package Mapeo;

import java.io.Serializable;
import java.util.Objects;

/**
 * Clase que representa la clave compuesta para la relación entre Cursos y Asignaturas.
 * Esta clase es utilizada como identificador en la tabla CursosAsignaturas.
 */
public class CursosAsignaturasId implements Serializable {

    private Integer curso;
    private Integer asignatura;

    // CONSTRUCTORES

    /**
     * Constructor por defecto de la clase CursosAsignaturasId.
     */
    public CursosAsignaturasId() {
    }

    /**
     * Constructor de la clase CursosAsignaturasId con los atributos curso y asignatura.
     * @param curso      ID del curso asociado a la asignatura.
     * @param asignatura ID de la asignatura asociada al curso.
     */
    public CursosAsignaturasId(Integer curso, Integer asignatura) {
        this.curso = curso;
        this.asignatura = asignatura;
    }

    // GETTERS Y SETTERS

    /**
     * Obtiene el ID del curso.
     * @return ID del curso.
     */
    public Integer getCurso() {
        return curso;
    }

    /**
     * Establece el ID del curso.
     * @param curso ID del curso.
     */
    public void setCurso(Integer curso) {
        this.curso = curso;
    }

    /**
     * Obtiene el ID de la asignatura.
     * @return ID de la asignatura.
     */
    public Integer getAsignatura() {
        return asignatura;
    }

    /**
     * Establece el ID de la asignatura.
     * @param asignatura ID de la asignatura.
     */
    public void setAsignatura(Integer asignatura) {
        this.asignatura = asignatura;
    }


    //MÉTODOS EQUAL Y HASHCODE
    /**
     * Método que compara dos objetos CursosAsignaturasId para verificar si son iguales.
     * @param objeto Objeto a comparar.
     * @return true si son iguales, false en caso contrario.
     */
    @Override
    public boolean equals(Object objeto) {
        if (this == objeto) return true;
        if (objeto == null || getClass() != objeto.getClass()) return false;
        CursosAsignaturasId that = (CursosAsignaturasId) objeto;
        return Objects.equals(curso, that.curso) && Objects.equals(asignatura, that.asignatura);
    }

    /**
     * Método que devuelve el código hash del objeto CursosAsignaturasId.
     * @return Código hash del objeto.
     */
    @Override
    public int hashCode() {
        return Objects.hash(curso, asignatura);
    }
}
