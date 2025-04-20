package Mapeo;

import jakarta.persistence.*;

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

    public enum EstadoAsignatura {
        activa, inactiva
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
}
