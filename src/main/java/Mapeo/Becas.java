package Mapeo;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.sql.Date;

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

    // Constructor
    public Becas() {
    }

    public Becas(Estudiantes estudiante, TipoBeca tipoBeca, Double monto, Date fechaAsignacion, EstadoBeca estadoBeca, String comentarios) {
        setEstudiante(estudiante);
        setTipoBeca(tipoBeca);
        setMonto(monto);
        setFechaAsignacion(fechaAsignacion);
        setEstadoBeca(estadoBeca);
        setComentarios(comentarios);
    }

    // Getters y Setters con validaciones

    public Integer getIdBeca() {
        return idBeca;
    }

    public void setIdBeca(Integer idBeca) {
        this.idBeca = idBeca;
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

    public TipoBeca getTipoBeca() {
        return tipoBeca;
    }

    public void setTipoBeca(TipoBeca tipoBeca) {
        if (tipoBeca == null) {
            throw new IllegalArgumentException("El tipo de beca no puede ser nulo.");
        }
        this.tipoBeca = tipoBeca;
    }

    public BigDecimal getMonto() {
        return monto;
    }

    public void setMonto(Double monto) {
        if (monto == null || monto < 0) {
            throw new IllegalArgumentException("El monto debe ser un valor positivo.");
        }
        this.monto = BigDecimal.valueOf(monto).setScale(2, BigDecimal.ROUND_HALF_UP); // Para asegurar dos decimales
    }

    public Date getFechaAsignacion() {
        return fechaAsignacion;
    }

    public void setFechaAsignacion(Date fechaAsignacion) {
        if (fechaAsignacion == null) {
            throw new IllegalArgumentException("La fecha de asignación no puede ser nula.");
        }
        this.fechaAsignacion = fechaAsignacion;
    }

    public EstadoBeca getEstadoBeca() {
        return estadoBeca;
    }

    public void setEstadoBeca(EstadoBeca estadoBeca) {
        if (estadoBeca == null) {
            throw new IllegalArgumentException("El estado de la beca no puede ser nulo.");
        }
        this.estadoBeca = estadoBeca;
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
