package Mapeo;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

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

    // Constructor
    public Cursos() {
    }

    public Cursos(String nombre, String descripcion, Profesores profesor, EstadoCurso estado) {
        setNombre(nombre);
        setDescripcion(descripcion);
        setProfesor(profesor);
        setEstado(estado);
    }

    // Getters y Setters con validaciones

    public Integer getIdCurso() {
        return idCurso;
    }

    public void setIdCurso(Integer idCurso) {
        this.idCurso = idCurso;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre del curso no puede ser nulo o vacío.");
        }
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        if (descripcion != null && descripcion.length() > 255) {
            throw new IllegalArgumentException("La descripción no puede exceder los 255 caracteres.");
        }
        this.descripcion = descripcion;
    }

    public Profesores getProfesor() {
        return profesor;
    }

    public void setProfesor(Profesores profesor) {
        if (profesor == null) {
            throw new IllegalArgumentException("El profesor no puede ser nulo.");
        }
        this.profesor = profesor;
    }

    public EstadoCurso getEstado() {
        return estado;
    }

    public void setEstado(EstadoCurso estado) {
        if (estado == null) {
            throw new IllegalArgumentException("El estado del curso no puede ser nulo.");
        }
        this.estado = estado;
    }

    public List<Asistencia> getAsistencias() {
        return asistencias;
    }

    public void setAsistencias(List<Asistencia> asistencias) {
        this.asistencias = asistencias;
    }

    public List<CursosAsignaturas> getCursosAsignaturas() {
        return cursosAsignaturas;
    }

    public void setCursosAsignaturas(List<CursosAsignaturas> cursosAsignaturas) {
        this.cursosAsignaturas = cursosAsignaturas;
    }

    public List<Matriculas> getMatriculas() {
        return matriculas;
    }

    public void setMatriculas(List<Matriculas> matriculas) {
        this.matriculas = matriculas;
    }

    // ToString

    @Override
    public String toString() {
        return nombre;
    }
}
