package Mapeo;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase que representa una asignatura en el sistema.
 * Contiene información sobre la asignatura, su profesor y su estado.
 */
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

    // CONSTRUCTORES
    /**
     * Constructor por defecto de la clase Asignaturas.
     */
    public Asignaturas() {
    }

    /**
     * Constructor de la clase Asignaturas con todos los atributos de la asignatura.
     * @param nombre nombre de la asignatura
     * @param descripcion descripción de la asignatura
     * @param profesor profesor de la asignatura
     * @param estado estado de la asignatura
     */
    public Asignaturas(String nombre, String descripcion, Profesores profesor, EstadoAsignatura estado) {
        setNombre(nombre);
        setDescripcion(descripcion);
        setProfesor(profesor);
        setEstado(estado);
    }

    // GETTERS Y SETTERS CON VALIDACIONES

    /**
     * Método que devuelve el id de la asignatura.
     * @return idAsignatura
     */
    public Integer getIdAsignatura() {
        return idAsignatura;
    }

    /**
     * Método que establece el id de la asignatura.
     * @param idAsignatura id de la asignatura
     */
    public void setIdAsignatura(Integer idAsignatura) {
        this.idAsignatura = idAsignatura;
    }

    /**
     * Método que devuelve el nombre de la asignatura.
     * @return nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Método que establece el nombre de la asignatura con validaciones.
     * @param nombre nombre de la asignatura
     */
    public void setNombre(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre no puede estar vacío.");
        }
        if (nombre.length() > 100) {
            throw new IllegalArgumentException("El nombre no puede exceder los 100 caracteres.");
        }
        this.nombre = nombre;
    }

    /**
     * Método que devuelve la descripción de la asignatura.
     * @return descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * Método que establece la descripción de la asignatura.
     * @param descripcion descripción de la asignatura
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * Método que devuelve el profesor de la asignatura.
     * @return profesor
     */
    public Profesores getProfesor() {
        return profesor;
    }

    /**
     * Método que establece el profesor de la asignatura con validaciones.
     * @param profesor profesor de la asignatura
     */
    public void setProfesor(Profesores profesor) {
        if (profesor == null) {
            throw new IllegalArgumentException("El profesor no puede ser nulo.");
        }
        this.profesor = profesor;
    }

    /**
     * Método que devuelve el estado de la asignatura.
     * @return estado
     */
    public EstadoAsignatura getEstado() {
        return estado;
    }

    /**
     * Método que establece el estado de la asignatura con validaciones.
     * @param estado estado de la asignatura
     */
    public void setEstado(EstadoAsignatura estado) {
        if (estado == null) {
            throw new IllegalArgumentException("El estado no puede ser nulo.");
        }
        this.estado = estado;
    }

    /**
     * Método que devuelve la lista de cursos y asignaturas.
     * @return cursosAsignaturas
     */
    public List<CursosAsignaturas> getCursosAsignaturas() {
        return cursosAsignaturas;
    }

    /**
     * Método que establece la lista de cursos y asignaturas.
     * @param cursosAsignaturas lista de cursos y asignaturas
     */
    public void setCursosAsignaturas(List<CursosAsignaturas> cursosAsignaturas) {
        this.cursosAsignaturas = cursosAsignaturas;
    }

    /**
     * Método que devuelve la lista de convalidaciones.
     * @return convalidaciones
     */
    public List<Convalidaciones> getConvalidaciones() {
        return convalidaciones;
    }

    /**
     * Método que establece la lista de convalidaciones.
     * @param convalidaciones lista de convalidaciones
     */
    public void setConvalidaciones(List<Convalidaciones> convalidaciones) {
        this.convalidaciones = convalidaciones;
    }

    /**
     * Método que devuelve la lista de horarios.
     * @return horarios
     */
    public List<Horarios> getHorarios() {
        return horarios;
    }

    /**
     * Método que establece la lista de horarios.
     * @param horarios lista de horarios
     */
    public void setHorarios(List<Horarios> horarios) {
        this.horarios = horarios;
    }

    /**
     * Método que devuelve una representación en cadena de la asignatura.
     * @return cadena con el nombre de la asignatura
     */
    @Override
    public String toString() {
        return getNombre();
    }
}
