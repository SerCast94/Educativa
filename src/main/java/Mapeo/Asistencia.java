package Mapeo;
import jakarta.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "asistencia")
public class Asistencia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_asistencia")
    private Integer idAsistencia;

    @ManyToOne
    @JoinColumn(name = "id_estudiante", nullable = false)
    private Estudiantes estudiante;

    @ManyToOne
    @JoinColumn(name = "id_curso", nullable = false)
    private Cursos curso;

    @Column(name = "fecha", nullable = false)
    private Date fecha;

    @Column(name = "justificado")
    private Boolean justificado;

    @Column(name = "motivo_ausencia")
    private String motivoAusencia;

    // Constructor
    public Asistencia() {
    }

    public Asistencia(Estudiantes estudiante, Cursos curso, Date fecha, Boolean asistio, String motivoAusencia) {
        this.estudiante = estudiante;
        this.curso = curso;
        this.fecha = fecha;
        this.justificado = asistio;
        this.motivoAusencia = motivoAusencia;
    }

    // Getters y Setters

    public Integer getIdAsistencia() {
        return idAsistencia;
    }

    public void setIdAsistencia(Integer idAsistencia) {
        this.idAsistencia = idAsistencia;
    }

    public Estudiantes getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(Estudiantes estudiante) {
        this.estudiante = estudiante;
    }

    public Cursos getCurso() {
        return curso;
    }

    public void setCurso(Cursos curso) {
        this.curso = curso;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Boolean getJustificado() {
        return justificado;
    }

    public void setJustificado(Boolean asistio) {
        this.justificado = asistio;
    }

    public String getMotivoAusencia() {
        return motivoAusencia;
    }

    public void setMotivoAusencia(String motivoAusencia) {
        this.motivoAusencia = motivoAusencia;
    }




}

