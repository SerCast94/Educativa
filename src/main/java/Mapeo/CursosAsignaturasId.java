package Mapeo;

import java.io.Serializable;
import java.util.Objects;

public class CursosAsignaturasId implements Serializable {

    private Integer curso;
    private Integer asignatura;

    public CursosAsignaturasId() {
    }

    public CursosAsignaturasId(Integer curso, Integer asignatura) {
        this.curso = curso;
        this.asignatura = asignatura;
    }

    // Getters y Setters
    public Integer getCurso() {
        return curso;
    }

    public void setCurso(Integer curso) {
        this.curso = curso;
    }

    public Integer getAsignatura() {
        return asignatura;
    }

    public void setAsignatura(Integer asignatura) {
        this.asignatura = asignatura;
    }

    // Métodos equals y hashCode para la clave compuesta
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CursosAsignaturasId that = (CursosAsignaturasId) o;
        return Objects.equals(curso, that.curso) && Objects.equals(asignatura, that.asignatura);
    }

    @Override
    public int hashCode() {
        return Objects.hash(curso, asignatura);
    }
}
