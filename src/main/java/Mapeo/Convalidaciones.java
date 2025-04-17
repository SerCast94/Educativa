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
    @JoinColumn(name = "id_curso_original", nullable = false)
    private Cursos cursoOriginal;

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

    public Cursos getCursoOriginal() {
        return cursoOriginal;
    }

    public void setCursoOriginal(Cursos cursoOriginal) {
        this.cursoOriginal = cursoOriginal;
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
