package Mapeo;

import jakarta.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "matriculas")
public class Matriculas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_matricula")
    private Integer idMatricula;

    @ManyToOne
    @JoinColumn(name = "id_estudiante", nullable = false)
    private Estudiantes estudiante;

    @ManyToOne
    @JoinColumn(name = "id_curso", nullable = false)
    private Cursos curso;

    @Column(name = "fecha_matricula", nullable = false)
    private Date fechaMatricula;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado", nullable = false)
    private EstadoMatricula estado;

    public enum EstadoMatricula {
        activo, inactivo
    }

    // Constructor
    public Matriculas() {
    }

    public Matriculas(Estudiantes estudiante, Cursos curso, Date fechaMatricula, EstadoMatricula estado) {
        this.estudiante = estudiante;
        this.curso = curso;
        this.fechaMatricula = fechaMatricula;
        this.estado = estado;
    }

    // Getters y Setters

    public Integer getIdMatricula() {
        return idMatricula;
    }

    public void setIdMatricula(Integer idMatricula) {
        this.idMatricula = idMatricula;
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

    public Date getFechaMatricula() {
        return fechaMatricula;
    }

    public void setFechaMatricula(Date fechaMatricula) {
        this.fechaMatricula = fechaMatricula;
    }

    public EstadoMatricula getEstado() {
        return estado;
    }

    public void setEstado(EstadoMatricula estado) {
        this.estado = estado;
    }
}

