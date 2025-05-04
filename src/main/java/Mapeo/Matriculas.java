package Mapeo;

import jakarta.persistence.*;
import java.sql.Date;

/**
 * Clase que representa una matrícula de un estudiante en un curso.
 * Contiene información sobre el estudiante, el curso, la fecha de matrícula
 * y el estado de la matrícula.
 */
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

    // CONSTRUCTORES

    /**
     * Constructor por defecto de la clase Matriculas.
     */
    public Matriculas() {}

    /**
     * Constructor de la clase Matriculas con todos los atributos de la matrícula.
     * @param estudiante     Estudiante que se matricula en el curso.
     * @param curso          Curso en el que se matricula el estudiante.
     * @param fechaMatricula Fecha de la matrícula.
     * @param estado         Estado de la matrícula.
     */
    public Matriculas(Estudiantes estudiante, Cursos curso, Date fechaMatricula, EstadoMatricula estado) {
        setEstudiante(estudiante);
        setCurso(curso);
        setFechaMatricula(fechaMatricula);
        setEstado(estado);
    }

    // GETTERS Y SETTERS

    /**
     * Métodos para obtener y establecer los atributos de la matrícula.
     */
    public Integer getIdMatricula() {
        return idMatricula;
    }

    /**
     * Establece el ID de la matrícula.
     * @param idMatricula ID de la matrícula.
     */
    public void setIdMatricula(Integer idMatricula) {
        this.idMatricula = idMatricula;
    }

    /**
     * Método que devuelve el estudiante asociado a la matrícula.
     * @return Estudiante asociado a la matrícula.
     */
    public Estudiantes getEstudiante() {
        return estudiante;
    }

    /**
     * Método que establece el estudiante asociado a la matrícula con validación.
     * @param estudiante Estudiante asociado a la matrícula.
     */
    public void setEstudiante(Estudiantes estudiante) {
        if (estudiante == null) {
            throw new IllegalArgumentException("El estudiante no puede ser null.");
        }
        this.estudiante = estudiante;
    }

    /**
     * Método que devuelve el curso asociado a la matrícula.
     * @return Curso asociado a la matrícula.
     */
    public Cursos getCurso() {
        return curso;
    }

    /**
     * Método que establece el curso asociado a la matrícula con validación.
     * @param curso Curso asociado a la matrícula.
     */
    public void setCurso(Cursos curso) {
        if (curso == null) {
            throw new IllegalArgumentException("El curso no puede ser null.");
        }
        this.curso = curso;
    }

    /**
     * Método que devuelve la fecha de matrícula.
     * @return Fecha de matrícula.
     */
    public Date getFechaMatricula() {
        return fechaMatricula;
    }

    /**
     * Método que establece la fecha de matrícula con validación.
     * @param fechaMatricula Fecha de matrícula.
     */
    public void setFechaMatricula(Date fechaMatricula) {
        if (fechaMatricula == null) {
            throw new IllegalArgumentException("La fecha de matrícula no puede ser null.");
        }
        this.fechaMatricula = fechaMatricula;
    }

    /**
     * Método que devuelve el estado de la matrícula.
     * @return Estado de la matrícula.
     */
    public EstadoMatricula getEstado() {
        return estado;
    }

    /**
     * Método que establece el estado de la matrícula con validación.
     * @param estado Estado de la matrícula.
     */
    public void setEstado(EstadoMatricula estado) {
        if (estado == null) {
            throw new IllegalArgumentException("El estado no puede ser null.");
        }
        this.estado = estado;
    }
}