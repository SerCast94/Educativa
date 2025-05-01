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

    // Constructor por defecto
    public Matriculas() {}

    // Constructor usando setters para validación
    public Matriculas(Estudiantes estudiante, Cursos curso, Date fechaMatricula, EstadoMatricula estado) {
        setEstudiante(estudiante);
        setCurso(curso);
        setFechaMatricula(fechaMatricula);
        setEstado(estado);
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
        if (estudiante == null) {
            throw new IllegalArgumentException("El estudiante no puede ser null.");
        }
        this.estudiante = estudiante;
    }

    public Cursos getCurso() {
        return curso;
    }

    public void setCurso(Cursos curso) {
        if (curso == null) {
            throw new IllegalArgumentException("El curso no puede ser null.");
        }
        this.curso = curso;
    }

    public Date getFechaMatricula() {
        return fechaMatricula;
    }

    public void setFechaMatricula(Date fechaMatricula) {
        if (fechaMatricula == null) {
            throw new IllegalArgumentException("La fecha de matrícula no puede ser null.");
        }
        this.fechaMatricula = fechaMatricula;
    }

    public EstadoMatricula getEstado() {
        return estado;
    }

    public void setEstado(EstadoMatricula estado) {
        if (estado == null) {
            throw new IllegalArgumentException("El estado no puede ser null.");
        }
        this.estado = estado;
    }
}
