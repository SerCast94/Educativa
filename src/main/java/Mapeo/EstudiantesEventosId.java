package Mapeo;

import java.io.Serializable;
import java.util.Objects;

public class EstudiantesEventosId implements Serializable {
    private Integer estudiante;
    private Integer evento;

    public EstudiantesEventosId() {
    }

    public EstudiantesEventosId(Integer estudiante, Integer evento) {
        this.estudiante = estudiante;
        this.evento = evento;
    }

    // equals() y hashCode()

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EstudiantesEventosId)) return false;
        EstudiantesEventosId that = (EstudiantesEventosId) o;
        return Objects.equals(estudiante, that.estudiante) &&
                Objects.equals(evento, that.evento);
    }

    @Override
    public int hashCode() {
        return Objects.hash(estudiante, evento);
    }
}
