package Mapeo;

import jakarta.persistence.*;

@Entity
@Table(name = "estudiantes_eventos")
@IdClass(EstudiantesEventosId.class) // Se usa una clase auxiliar para la clave primaria compuesta
public class EstudiantesEventos {

    @Id
    @ManyToOne
    @JoinColumn(name = "id_estudiante", nullable = false)
    private Estudiantes estudiante;  // Relación muchos a uno con Estudiantes

    @Id
    @ManyToOne
    @JoinColumn(name = "id_evento", nullable = false)
    private Eventos evento;  // Relación muchos a uno con Eventos

    @Column(name = "comentario")
    private String comentario;  // Comentario del estudiante sobre el evento

    @Column(name = "confirmado")
    private Boolean confirmado;  // Confirmación de asistencia

    // Constructor por defecto
    public EstudiantesEventos() {}

    // Constructor con parámetros
    public EstudiantesEventos(Estudiantes estudiante, Eventos evento, String comentario, Boolean confirmado) {
        this.estudiante = estudiante;
        this.evento = evento;
        this.comentario = comentario;
        this.confirmado = confirmado;
    }

    // Métodos hashCode y equals
    @Override
    public int hashCode() {
        return super.hashCode();  // Puedes personalizar esto si lo necesitas
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);  // Puedes personalizar esto si lo necesitas
    }

    // Getters y Setters

    public Estudiantes getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(Estudiantes estudiante) {
        if (estudiante != null) {
            this.estudiante = estudiante;
        } else {
            throw new IllegalArgumentException("El estudiante no puede ser nulo.");
        }
    }

    public Eventos getEvento() {
        return evento;
    }

    public void setEvento(Eventos evento) {
        if (evento != null) {
            this.evento = evento;
        } else {
            throw new IllegalArgumentException("El evento no puede ser nulo.");
        }
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        if (comentario != null && comentario.length() <= 255) {
            this.comentario = comentario;
        } else if (comentario != null && comentario.length() > 255) {
            throw new IllegalArgumentException("El comentario no puede exceder los 255 caracteres.");
        } else {
            this.comentario = null;  // Permitir que el comentario sea nulo si no se establece
        }
    }

    public Boolean getConfirmado() {
        return confirmado;
    }

    public void setConfirmado(Boolean confirmado) {
        if (confirmado != null) {
            this.confirmado = confirmado;
        } else {
            throw new IllegalArgumentException("El campo 'confirmado' no puede ser nulo.");
        }
    }

}
