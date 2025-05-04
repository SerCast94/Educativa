package Mapeo;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.sql.Date;

/**
 * Clase que representa una beca en el sistema.
 * Contiene información sobre el estudiante, el tipo de beca, el monto,
 * la fecha de asignación, el estado de la beca y comentarios adicionales.
 */
@Entity
@Table(name = "becas")
public class Becas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_beca")
    private Integer idBeca;

    @ManyToOne
    @JoinColumn(name = "id_estudiante", nullable = false)
    private Estudiantes estudiante;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_beca", nullable = false)
    private TipoBeca tipoBeca;

    @Column(name = "monto", nullable = false)
    private BigDecimal monto;

    @Column(name = "fecha_asignacion", nullable = false)
    private Date fechaAsignacion;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado_beca", nullable = false)
    private EstadoBeca estadoBeca;

    @Column(name = "comentarios")
    private String comentarios;

    public enum TipoBeca {
        rendimiento, deportiva, economia, diversidad
    }

    public enum EstadoBeca {
        activo, inactivo
    }

    // CONSTRUCTORES
    /**
     * Constructor por defecto de la clase Becas.
     */
    public Becas() {
    }

    /**
     * Constructor de la clase Becas con todos los atributos de la beca.
     * @param estudiante       Estudiante al que se le asigna la beca.
     * @param tipoBeca        Tipo de beca asignada.
     * @param monto           Monto de la beca.
     * @param fechaAsignacion Fecha de asignación de la beca.
     * @param estadoBeca      Estado de la beca (activo/inactivo).
     * @param comentarios      Comentarios adicionales sobre la beca.
     */
    public Becas(Estudiantes estudiante, TipoBeca tipoBeca, Double monto, Date fechaAsignacion, EstadoBeca estadoBeca, String comentarios) {
        setEstudiante(estudiante);
        setTipoBeca(tipoBeca);
        setMonto(monto);
        setFechaAsignacion(fechaAsignacion);
        setEstadoBeca(estadoBeca);
        setComentarios(comentarios);
    }

    // GETTERS Y SETTERS CON VALIDACIONES

    /**
     * Método que devuelve el id de la beca.
     * @return idBeca
     */
    public Integer getIdBeca() {
        return idBeca;
    }

    /**
     * Método que establece el id de la beca.
     * @param idBeca id de la beca
     */
    public void setIdBeca(Integer idBeca) {
        this.idBeca = idBeca;
    }

    /**
     * Método que devuelve el estudiante al que se le asigna la beca.
     * @return estudiante
     */
    public Estudiantes getEstudiante() {
        return estudiante;
    }

    /**
     * Método que establece el estudiante al que se le asigna la beca con validaciones.
     * @param estudiante estudiante al que se le asigna la beca
     */
    public void setEstudiante(Estudiantes estudiante) {
        if (estudiante == null) {
            throw new IllegalArgumentException("El estudiante no puede ser nulo.");
        }
        this.estudiante = estudiante;
    }

    /**
     * Método que devuelve el tipo de beca.
     * @return tipoBeca
     */
    public TipoBeca getTipoBeca() {
        return tipoBeca;
    }

    /**
     * Método que establece el tipo de beca con validaciones.
     * @param tipoBeca tipo de beca
     */
    public void setTipoBeca(TipoBeca tipoBeca) {
        if (tipoBeca == null) {
            throw new IllegalArgumentException("El tipo de beca no puede ser nulo.");
        }
        this.tipoBeca = tipoBeca;
    }

    /**
     * Método que devuelve el monto de la beca.
     * @return monto
     */
    public BigDecimal getMonto() {
        return monto;
    }

    /**
     * Método que establece el monto de la beca con validaciones.
     * @param monto monto de la beca
     */
    public void setMonto(Double monto) {
        if (monto == null || monto < 0) {
            throw new IllegalArgumentException("El monto debe ser un valor positivo.");
        }
        this.monto = BigDecimal.valueOf(monto).setScale(2, BigDecimal.ROUND_HALF_UP); // Para asegurar dos decimales
    }

    /**
     * Método que devuelve la fecha de asignación de la beca.
     * @return fechaAsignacion
     */
    public Date getFechaAsignacion() {
        return fechaAsignacion;
    }

    /**
     * Método que establece la fecha de asignación de la beca con validaciones.
     * @param fechaAsignacion fecha de asignación de la beca
     */
    public void setFechaAsignacion(Date fechaAsignacion) {
        if (fechaAsignacion == null) {
            throw new IllegalArgumentException("La fecha de asignación no puede ser nula.");
        }
        this.fechaAsignacion = fechaAsignacion;
    }

    /**
     * Método que devuelve el estado de la beca.
     * @return estadoBeca
     */
    public EstadoBeca getEstadoBeca() {
        return estadoBeca;
    }

    /**
     * Método que establece el estado de la beca con validaciones.
     * @param estadoBeca estado de la beca
     */
    public void setEstadoBeca(EstadoBeca estadoBeca) {
        if (estadoBeca == null) {
            throw new IllegalArgumentException("El estado de la beca no puede ser nulo.");
        }
        this.estadoBeca = estadoBeca;
    }

    /**
     * Método que devuelve los comentarios adicionales sobre la beca.
     * @return comentarios
     */
    public String getComentarios() {
        return comentarios;
    }

    /**
     * Método que establece los comentarios adicionales sobre la beca con validaciones.
     * @param comentarios comentarios adicionales sobre la beca
     */
    public void setComentarios(String comentarios) {
        if (comentarios != null && comentarios.length() > 255) {
            throw new IllegalArgumentException("Los comentarios no pueden exceder los 255 caracteres.");
        }
        this.comentarios = comentarios;
    }
}
