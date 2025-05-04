package Mapeo;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase que representa una asignatura en el sistema.
 * Contiene información sobre la asignatura, su profesor y su estado.
 */
@Entity
@Table(name = "cursos")
public class Cursos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_curso")
    private Integer idCurso;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Column(name = "descripcion", length = 255)
    private String descripcion;

    @ManyToOne
    @JoinColumn(name = "id_profesor", nullable = false)
    private Profesores profesor;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado", nullable = false)
    private EstadoCurso estado;

    @OneToMany(mappedBy = "curso", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Asistencia> asistencias = new ArrayList<>();

    @OneToMany(mappedBy = "curso", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<CursosAsignaturas> cursosAsignaturas = new ArrayList<>();

    @OneToMany(mappedBy = "curso", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Matriculas> matriculas = new ArrayList<>();

    public enum EstadoCurso {
        activo, inactivo
    }

    // CONSTRUCTORES

    /**
     * Constructor por defecto de la clase Cursos.
     */
    public Cursos() {
    }

    /**
     * Constructor de la clase Cursos con todos los atributos de la asignatura.
     * @param nombre       nombre del curso
     * @param descripcion  descripción del curso
     * @param profesor     profesor del curso
     * @param estado       estado del curso
     */
    public Cursos(String nombre, String descripcion, Profesores profesor, EstadoCurso estado) {
        setNombre(nombre);
        setDescripcion(descripcion);
        setProfesor(profesor);
        setEstado(estado);
    }

    // GETTERS Y SETTERS CON VALIDACIONES

    /**
     * Método que devuelve el ID del curso.
     * @return ID del curso.
     */
    public Integer getIdCurso() {
        return idCurso;
    }

    /**
     * Método que establece el ID del curso.
     * @param idCurso ID del curso.
     */
    public void setIdCurso(Integer idCurso) {
        this.idCurso = idCurso;
    }

    /**
     * Método que devuelve el nombre del curso.
     * @return Nombre del curso.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Método que establece el nombre del curso con validaciones.
     * @param nombre Nombre del curso.
     */
    public void setNombre(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre del curso no puede ser nulo o vacío.");
        }
        this.nombre = nombre;
    }

    /**
     * Método que devuelve la descripción del curso.
     * @return Descripción del curso.
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * Método que establece la descripción del curso con validaciones.
     * @param descripcion Descripción del curso.
     */
    public void setDescripcion(String descripcion) {
        if (descripcion != null && descripcion.length() > 255) {
            throw new IllegalArgumentException("La descripción no puede exceder los 255 caracteres.");
        }
        this.descripcion = descripcion;
    }

    /**
     * Método que devuelve el profesor del curso.
     * @return Profesor del curso.
     */
    public Profesores getProfesor() {
        return profesor;
    }

    /**
     * Método que establece el profesor del curso con validaciones.
     * @param profesor Profesor del curso.
     */
    public void setProfesor(Profesores profesor) {
        if (profesor == null) {
            throw new IllegalArgumentException("El profesor no puede ser nulo.");
        }
        this.profesor = profesor;
    }

    /**
     * Método que devuelve el estado del curso.
     * @return Estado del curso.
     */
    public EstadoCurso getEstado() {
        return estado;
    }

    /**
     * Método que establece el estado del curso con validaciones.
     * @param estado Estado del curso.
     */
    public void setEstado(EstadoCurso estado) {
        if (estado == null) {
            throw new IllegalArgumentException("El estado del curso no puede ser nulo.");
        }
        this.estado = estado;
    }

    /**
     * Método que devuelve la lista de asistencias del curso.
     * @return Lista de asistencias del curso.
     */
    public List<Asistencia> getAsistencias() {
        return asistencias;
    }

    /**
     * Método que establece la lista de asistencias del curso.
     * @param asistencias Lista de asistencias del curso.
     */
    public void setAsistencias(List<Asistencia> asistencias) {
        this.asistencias = asistencias;
    }

    /**
     * Método que devuelve la lista de asignaturas del curso.
     * @return Lista de asignaturas del curso.
     */
    public List<CursosAsignaturas> getCursosAsignaturas() {
        return cursosAsignaturas;
    }

    /**
     * Método que establece la lista de asignaturas del curso.
     * @param cursosAsignaturas Lista de asignaturas del curso.
     */
    public void setCursosAsignaturas(List<CursosAsignaturas> cursosAsignaturas) {
        this.cursosAsignaturas = cursosAsignaturas;
    }

    /**
     * Método que devuelve la lista de matrículas del curso.
     * @return Lista de matrículas del curso.
     */
    public List<Matriculas> getMatriculas() {
        return matriculas;
    }

    /**
     * Método que establece la lista de matrículas del curso.
     * @param matriculas Lista de matrículas del curso.
     */
    public void setMatriculas(List<Matriculas> matriculas) {
        this.matriculas = matriculas;
    }

    /**
     * Método que devuelve en una cadena el nombre del curso.
     * @return nombre del curso.
     */
    @Override
    public String toString() {
        return nombre;
    }
}
