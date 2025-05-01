package Mapeo;

import java.io.Serializable;
import java.util.Objects;

public class EstudiantesEventosId implements Serializable {
    private Integer estudiante;
    private Integer evento;

    public EstudiantesEventosId() {
    }

    public EstudiantesEventosId(Integer estudiante, Integer evento) {
        setEstudiante(estudiante);
        setEvento(evento);
    }

    public Integer getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(Integer estudiante) {
        if (estudiante == null) {
            throw new IllegalArgumentException("El estudiante no puede ser nulo.");
        }else{
            this.estudiante = estudiante;
        }
    }

    public Integer getEvento() {
        return evento;
    }

    public void setEvento(Integer evento) {
        if (evento == null) {
            throw new IllegalArgumentException("El evento no puede ser nulo.");
        }else{
            this.evento = evento;
        }
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
