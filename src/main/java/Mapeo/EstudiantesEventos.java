package Mapeo;
import jakarta.persistence.*;

@Entity
@Table(name = "estudiantes_eventos")
@IdClass(EstudiantesEventosId.class) // <- usar clase auxiliar para la PK compuesta
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

    // Constructor
    public EstudiantesEventos() {
    }

    public EstudiantesEventos(Estudiantes estudiante, Eventos evento, String comentario, Boolean confirmado) {
        this.estudiante = estudiante;
        this.evento = evento;
        this.comentario = comentario;
        this.confirmado = confirmado;
    }

    // Métodos

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }


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

