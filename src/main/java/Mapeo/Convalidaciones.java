package Mapeo;

import jakarta.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "convalidaciones")
public class Convalidaciones {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_convalidacion")
    private Integer idConvalidacion;

    @ManyToOne
    @JoinColumn(name = "id_estudiante", nullable = false)
    private Estudiantes estudiante;

    @ManyToOne
    @JoinColumn(name = "id_asignatura_original", nullable = false)
    private Asignaturas asignaturaOriginal;

    @Column(name = "fecha_convalidacion", nullable = false)
    private Date fechaConvalidacion;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado_convalidacion", nullable = false)
    private EstadoConvalidacion estadoConvalidacion;

    @Column(name = "comentarios")
    private String comentarios;

    public enum EstadoConvalidacion {
        Aprobada, Pendiente, Rechazada
    }

    // Constructor
    public Convalidaciones() {
    }

    public Convalidaciones(Estudiantes estudiante, Asignaturas asignaturaOriginal, Date fechaConvalidacion, EstadoConvalidacion estadoConvalidacion, String comentarios) {
        setEstudiante(estudiante);
        setAsignaturaOriginal(asignaturaOriginal);
        setFechaConvalidacion(fechaConvalidacion);
        setEstadoConvalidacion(estadoConvalidacion);
        setComentarios(comentarios);
    }

    // Getters y Setters con validaciones

    public Integer getIdConvalidacion() {
        return idConvalidacion;
    }

    public void setIdConvalidacion(Integer idConvalidacion) {
        this.idConvalidacion = idConvalidacion;
    }

    public Estudiantes getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(Estudiantes estudiante) {
        if (estudiante == null) {
            throw new IllegalArgumentException("El estudiante no puede ser nulo.");
        }
        this.estudiante = estudiante;
    }

    public Asignaturas getAsignaturaOriginal() {
        return asignaturaOriginal;
    }

    public void setAsignaturaOriginal(Asignaturas asignaturaOriginal) {
        if (asignaturaOriginal == null) {
            throw new IllegalArgumentException("La asignatura original no puede ser nula.");
        }
        this.asignaturaOriginal = asignaturaOriginal;
    }

    public Date getFechaConvalidacion() {
        return fechaConvalidacion;
    }

    public void setFechaConvalidacion(Date fechaConvalidacion) {
        if (fechaConvalidacion == null) {
            throw new IllegalArgumentException("La fecha de convalidación no puede ser nula.");
        }
        this.fechaConvalidacion = fechaConvalidacion;
    }

    public EstadoConvalidacion getEstadoConvalidacion() {
        return estadoConvalidacion;
    }

    public void setEstadoConvalidacion(EstadoConvalidacion estadoConvalidacion) {
        if (estadoConvalidacion == null) {
            throw new IllegalArgumentException("El estado de la convalidación no puede ser nulo.");
        }
        this.estadoConvalidacion = estadoConvalidacion;
    }

    public String getComentarios() {
        return comentarios;
    }

    public void setComentarios(String comentarios) {
        if (comentarios != null && comentarios.length() > 255) {
            throw new IllegalArgumentException("Los comentarios no pueden exceder los 255 caracteres.");
        }
        this.comentarios = comentarios;
    }
}
