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

    @Column(name = "fecha_convalidacion")
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
        this.estudiante = estudiante;
        this.asignaturaOriginal = asignaturaOriginal;
        this.fechaConvalidacion = fechaConvalidacion;
        this.estadoConvalidacion = estadoConvalidacion;
        this.comentarios = comentarios;
    }


    // Getters y Setters

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
        this.estudiante = estudiante;
    }

    public Asignaturas getAsignaturaOriginal() {
        return asignaturaOriginal;
    }

    public void setAsignaturaOriginal(Asignaturas asignaturaOriginal) {
        this.asignaturaOriginal = asignaturaOriginal;
    }

    public Date getFechaConvalidacion() {
        return fechaConvalidacion;
    }

    public void setFechaConvalidacion(Date fechaConvalidacion) {
        this.fechaConvalidacion = fechaConvalidacion;
    }

    public EstadoConvalidacion getEstadoConvalidacion() {
        return estadoConvalidacion;
    }

    public void setEstadoConvalidacion(EstadoConvalidacion estadoConvalidacion) {
        this.estadoConvalidacion = estadoConvalidacion;
    }

    public String getComentarios() {
        return comentarios;
    }

    public void setComentarios(String comentarios) {
        this.comentarios = comentarios;
    }


}
