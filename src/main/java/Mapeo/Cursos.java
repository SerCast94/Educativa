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

    @Column(name = "descripcion")
    private String descripcion;

    @ManyToOne
    @JoinColumn(name = "id_profesor")
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
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.profesor = profesor;
        this.estado = estado;
    }

    // Getters y Setters

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
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Profesores getProfesor() {
        return profesor;
    }

    public void setProfesor(Profesores profesor) {
        this.profesor = profesor;
    }

    public EstadoCurso getEstado() {
        return estado;
    }

    public void setEstado(EstadoCurso estado) {
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



    //ToString

    @Override
    public String toString() {
        return nombre;
    }
}

