package Mapeo;

import jakarta.persistence.*;
import java.sql.Date;

/**
 * Clase que representa una convalidación de asignatura en el sistema.
 * Contiene información sobre el estudiante, la asignatura original,
 * la fecha de convalidación, el estado de la convalidación y comentarios adicionales.
 */
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

    // CONSTRUCTORES

    /**
     * Constructor por defecto de la clase Convalidaciones.
     */
    public Convalidaciones() {
    }

    /**
     * Constructor de la clase Convalidaciones con todos los atributos de la convalidación.
     *
     * @param estudiante          Estudiante al que se le realiza la convalidación.
     * @param asignaturaOriginal  Asignatura original que se está convalidando.
     * @param fechaConvalidacion  Fecha de la convalidación.
     * @param estadoConvalidacion Estado de la convalidación.
     * @param comentarios         Comentarios adicionales sobre la convalidación.
     */
    public Convalidaciones(Estudiantes estudiante, Asignaturas asignaturaOriginal, Date fechaConvalidacion, EstadoConvalidacion estadoConvalidacion, String comentarios) {
        setEstudiante(estudiante);
        setAsignaturaOriginal(asignaturaOriginal);
        setFechaConvalidacion(fechaConvalidacion);
        setEstadoConvalidacion(estadoConvalidacion);
        setComentarios(comentarios);
    }

    // GETTERS Y SETTERS CON VALIDACIONES

    /**
     * Obtiene el ID de la convalidación.
     * @return ID de la convalidación.
     */
    public Integer getIdConvalidacion() {
        return idConvalidacion;
    }

    /**
     * Establece el ID de la convalidación.
     * @param idConvalidacion ID de la convalidación.
     */
    public void setIdConvalidacion(Integer idConvalidacion) {
        this.idConvalidacion = idConvalidacion;
    }

    /**
     * Obtiene el estudiante asociado a la convalidación.
     * @return Estudiante asociado a la convalidación.
     */
    public Estudiantes getEstudiante() {
        return estudiante;
    }

    /**
     * Establece el estudiante asociado a la convalidación con validaciones.
     * @param estudiante Estudiante asociado a la convalidación.
     */
    public void setEstudiante(Estudiantes estudiante) {
        if (estudiante == null) {
            throw new IllegalArgumentException("El estudiante no puede ser nulo.");
        }
        this.estudiante = estudiante;
    }

    /**
     * Obtiene la asignatura original asociada a la convalidación.
     * @return Asignatura original asociada a la convalidación.
     */
    public Asignaturas getAsignaturaOriginal() {
        return asignaturaOriginal;
    }

    /**
     * Establece la asignatura original asociada a la convalidación con validaciones.
     * @param asignaturaOriginal Asignatura original asociada a la convalidación.
     */
    public void setAsignaturaOriginal(Asignaturas asignaturaOriginal) {
        if (asignaturaOriginal == null) {
            throw new IllegalArgumentException("La asignatura original no puede ser nula.");
        }
        this.asignaturaOriginal = asignaturaOriginal;
    }

    /**
     * Obtiene la fecha de la convalidación.
     * @return Fecha de la convalidación.
     */
    public Date getFechaConvalidacion() {
        return fechaConvalidacion;
    }

    /**
     * Establece la fecha de la convalidación con validaciones.
     * @param fechaConvalidacion Fecha de la convalidación.
     */
    public void setFechaConvalidacion(Date fechaConvalidacion) {
        if (fechaConvalidacion == null) {
            throw new IllegalArgumentException("La fecha de convalidación no puede ser nula.");
        }
        this.fechaConvalidacion = fechaConvalidacion;
    }

    /**
     * Obtiene el estado de la convalidación.
     * @return Estado de la convalidación.
     */
    public EstadoConvalidacion getEstadoConvalidacion() {
        return estadoConvalidacion;
    }

    /**
     * Establece el estado de la convalidación con validaciones.
     * @param estadoConvalidacion Estado de la convalidación.
     */
    public void setEstadoConvalidacion(EstadoConvalidacion estadoConvalidacion) {
        if (estadoConvalidacion == null) {
            throw new IllegalArgumentException("El estado de la convalidación no puede ser nulo.");
        }
        this.estadoConvalidacion = estadoConvalidacion;
    }

    /**
     * Obtiene los comentarios adicionales sobre la convalidación.
     * @return Comentarios adicionales sobre la convalidación.
     */
    public String getComentarios() {
        return comentarios;
    }

    /**
     * Establece los comentarios adicionales sobre la convalidación con validaciones.
     * @param comentarios Comentarios adicionales sobre la convalidación.
     */
    public void setComentarios(String comentarios) {
        if (comentarios != null && comentarios.length() > 255) {
            throw new IllegalArgumentException("Los comentarios no pueden exceder los 255 caracteres.");
        }
        this.comentarios = comentarios;
    }
}