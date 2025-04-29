package Mapeo;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "asignaturas")
public class Asignaturas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_asignatura")
    private Integer idAsignatura;

    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;

    @Column(name = "descripcion", columnDefinition = "TEXT")
    private String descripcion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_profesor", foreignKey = @ForeignKey(name = "FK_profesor_asignatura"))
    private Profesores profesor;

    @Column(name = "estado", nullable = false)
    @Enumerated(EnumType.STRING)
    private EstadoAsignatura estado;

    @OneToMany(mappedBy = "asignatura", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<CursosAsignaturas> cursosAsignaturas = new ArrayList<>();

    @OneToMany(mappedBy = "asignaturaOriginal", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Convalidaciones> convalidaciones = new ArrayList<>();

    @OneToMany(mappedBy = "asignatura", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Horarios> horarios = new ArrayList<>();

    public enum EstadoAsignatura {
        activa, inactiva
    }



    // Constructor
    public Asignaturas() {
    }
    public Asignaturas(String nombre, String descripcion, Profesores profesor, EstadoAsignatura estado) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.profesor = profesor;
        this.estado = estado;
    }

    // Getters y Setters

    public Integer getIdAsignatura() {
        return idAsignatura;
    }

    public void setIdAsignatura(Integer idAsignatura) {
        this.idAsignatura = idAsignatura;
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

    public EstadoAsignatura getEstado() {
        return estado;
    }

    public void setEstado(EstadoAsignatura estado) {
        this.estado = estado;
    }

    public List<CursosAsignaturas> getCursosAsignaturas() {
        return cursosAsignaturas;
    }

    public void setCursosAsignaturas(List<CursosAsignaturas> cursosAsignaturas) {
        this.cursosAsignaturas = cursosAsignaturas;
    }

    public List<Convalidaciones> getConvalidaciones() {
        return convalidaciones;
    }

    public void setConvalidaciones(List<Convalidaciones> convalidaciones) {
        this.convalidaciones = convalidaciones;
    }

    public List<Horarios> getHorarios() {
        return horarios;
    }

    public void setHorarios(List<Horarios> horarios) {
        this.horarios = horarios;
    }

    @Override
    public String toString() {
        return getNombre();
    }
}
