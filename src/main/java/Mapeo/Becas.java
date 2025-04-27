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
    private Double monto;

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
        this.estudiante = estudiante;
        this.tipoBeca = tipoBeca;
        this.monto = monto;
        this.fechaAsignacion = fechaAsignacion;
        this.estadoBeca = estadoBeca;
        this.comentarios = comentarios;
    }

    // Getters y Setters

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
        this.estudiante = estudiante;
    }

    public TipoBeca getTipoBeca() {
        return tipoBeca;
    }

    public void setTipoBeca(TipoBeca tipoBeca) {
        this.tipoBeca = tipoBeca;
    }

    public Double getMonto() {
        return monto;
    }

    public void setMonto(Double monto) {
        this.monto = monto;
    }

    public Date getFechaAsignacion() {
        return fechaAsignacion;
    }

    public void setFechaAsignacion(Date fechaAsignacion) {
        this.fechaAsignacion = fechaAsignacion;
    }

    public EstadoBeca getEstadoBeca() {
        return estadoBeca;
    }

    public void setEstadoBeca(EstadoBeca estadoBeca) {
        this.estadoBeca = estadoBeca;
    }

    public String getComentarios() {
        return comentarios;
    }

    public void setComentarios(String comentarios) {
        this.comentarios = comentarios;
    }


}

