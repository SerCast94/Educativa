package Mapeo;

import java.io.Serializable;
import java.util.Objects;

public class CursosAsignaturasId implements Serializable {
    private Integer curso;
    private Integer asignatura;

    public CursosAsignaturasId() {}

    public CursosAsignaturasId(Integer curso, Integer asignatura) {
        this.curso = curso;
        this.asignatura = asignatura;
    }

    // equals() y hashCode()

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CursosAsignaturasId)) return false;
        CursosAsignaturasId that = (CursosAsignaturasId) o;
        return Objects.equals(curso, that.curso) &&
                Objects.equals(asignatura, that.asignatura);
    }

    @Override
    public int hashCode() {
        return Objects.hash(curso, asignatura);
    }
}
