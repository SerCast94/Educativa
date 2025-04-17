package Mapeo;
import jakarta.persistence.*;

@Entity
@Table(name = "estudiantes_eventos")
public class EstudiantesEventos {

    @Id
    @ManyToOne
    @JoinColumn(name = "id_estudiante", nullable = false)
    private Estudiantes estudiante;

    @Id
    @ManyToOne
    @JoinColumn(name = "id_evento", nullable = false)
    private Eventos evento;

    @Column(name = "comentario")
    private String comentario;

    @Column(name = "confirmado")
    private Boolean confirmado;

    // Getters y Setters

    public Estudiantes getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(Estudiantes estudiante) {
        this.estudiante = estudiante;
    }

    public Eventos getEvento() {
        return evento;
    }

    public void setEvento(Eventos evento) {
        this.evento = evento;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public Boolean getConfirmado() {
        return confirmado;
    }

    public void setConfirmado(Boolean confirmado) {
        this.confirmado = confirmado;
    }
}

