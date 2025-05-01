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

    public Asistencia(Estudiantes estudiante, Cursos curso, Date fecha, Boolean justificado, String motivoAusencia) {
        setEstudiante(estudiante);
        setCurso(curso);
        setFecha(fecha);
        setJustificado(justificado);
        setMotivoAusencia(motivoAusencia);
    }

    // Getters y Setters con validaciones

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
        if (estudiante == null) {
            throw new IllegalArgumentException("El estudiante no puede ser nulo.");
        }
        this.estudiante = estudiante;
    }

    public Cursos getCurso() {
        return curso;
    }

    public void setCurso(Cursos curso) {
        if (curso == null) {
            throw new IllegalArgumentException("El curso no puede ser nulo.");
        }
        this.curso = curso;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        if (fecha == null) {
            throw new IllegalArgumentException("La fecha no puede ser nula.");
        }
        this.fecha = fecha;
    }

    public Boolean getJustificado() {
        return justificado;
    }

    public void setJustificado(Boolean justificado) {
        this.justificado = justificado;
    }

    public String getMotivoAusencia() {
        return motivoAusencia;
    }

    public void setMotivoAusencia(String motivoAusencia) {
        if (motivoAusencia != null && motivoAusencia.length() > 255) {
            throw new IllegalArgumentException("El motivo de ausencia no puede exceder los 255 caracteres.");
        }
        this.motivoAusencia = motivoAusencia;
    }
}
