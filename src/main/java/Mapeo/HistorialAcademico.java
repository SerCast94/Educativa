package Mapeo;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.sql.Date;

/**
 * Clase que representa el historial académico de un estudiante.
 * Contiene información sobre el estudiante, la asignatura, la nota final,
 * la fecha de aprobación y comentarios adicionales.
 */
@Entity
@Table(name = "historialacademico")
public class HistorialAcademico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_historial")
    private Integer idHistorial;

    @ManyToOne
    @JoinColumn(name = "id_estudiante", nullable = false)
    private Estudiantes estudiante;

    @ManyToOne
    @JoinColumn(name = "id_asignatura", nullable = false)
    private Asignaturas asignatura;

    @Column(name = "nota_final", precision = 5, scale = 2)
    private BigDecimal notaFinal;

    @Column(name = "fecha_aprobacion")
    private Date fechaAprobacion;

    @Column(name = "comentarios", columnDefinition = "TEXT")
    private String comentarios;

    // CONSTRUCTORES

    /**
     * Constructor por defecto de la clase HistorialAcademico.
     */
    public HistorialAcademico() {}

    /**
     * Constructor de la clase HistorialAcademico con todos los atributos del historial académico.
     * @param estudiante      Estudiante al que pertenece el historial académico.
     * @param asignatura      Asignatura asociada al historial académico.
     * @param notaFinal       Nota final obtenida en la asignatura.
     * @param fechaAprobacion Fecha de aprobación de la asignatura.
     * @param comentarios     Comentarios adicionales sobre el historial académico.
     */
    public HistorialAcademico(Estudiantes estudiante, Asignaturas asignatura, BigDecimal notaFinal, Date fechaAprobacion, String comentarios) {
        setEstudiante(estudiante);
        setAsignatura(asignatura);
        setNotaFinal(notaFinal);
        setFechaAprobacion(fechaAprobacion);
        setComentarios(comentarios);
    }

    // GETTERS Y SETTERS

    /**
     * Método que devuelve el ID del historial académico.
     * @return ID del historial académico.
     */
    public Integer getIdHistorial() {
        return idHistorial;
    }

    /**
     * Método que establece el ID del historial académico.
     * @param idHistorial ID del historial académico.
     */
    public void setIdHistorial(Integer idHistorial) {
        this.idHistorial = idHistorial;
    }

    /**
     * Método que devuelve el estudiante asociado al historial académico.
     * @return Estudiante asociado al historial académico.
     */
    public Estudiantes getEstudiante() {
        return estudiante;
    }

    /**
     * Método que establece el estudiante asociado al historial académico.
     * @param estudiante Estudiante asociado al historial académico.
     */
    public void setEstudiante(Estudiantes estudiante) {
        this.estudiante = estudiante;
    }

    /**
     * Método que devuelve la asignatura asociada al historial académico.
     * @return Asignatura asociada al historial académico.
     */
    public Asignaturas getAsignatura() {
        return asignatura;
    }

    /**
     * Método que establece la asignatura asociada al historial académico.
     * @param asignatura Asignatura asociada al historial académico.
     */
    public void setAsignatura(Asignaturas asignatura) {
        this.asignatura = asignatura;
    }

    /**
     * Método que devuelve la nota final obtenida en la asignatura.
     * @return Nota final obtenida en la asignatura.
     */
    public BigDecimal getNotaFinal() {
        return notaFinal;
    }

    /**
     * Método que establece la nota final obtenida en la asignatura.
     * @param notaFinal Nota final obtenida en la asignatura.
     */
    public void setNotaFinal(BigDecimal notaFinal) {
        this.notaFinal = notaFinal;
    }

    /**
     * Método que devuelve la fecha de aprobación de la asignatura.
     * @return Fecha de aprobación de la asignatura.
     */
    public Date getFechaAprobacion() {
        return fechaAprobacion;
    }

    /**
     * Método que establece la fecha de aprobación de la asignatura.
     * @param fechaAprobacion Fecha de aprobación de la asignatura.
     */
    public void setFechaAprobacion(Date fechaAprobacion) {
        this.fechaAprobacion = fechaAprobacion;
    }

    /**
     * Método que devuelve los comentarios adicionales sobre el historial académico.
     * @return Comentarios adicionales sobre el historial académico.
     */
    public String getComentarios() {
        return comentarios;
    }

    /**
     * Método que establece los comentarios adicionales sobre el historial académico.
     * @param comentarios Comentarios adicionales sobre el historial académico.
     */
    public void setComentarios(String comentarios) {
        this.comentarios = comentarios;
    }
}