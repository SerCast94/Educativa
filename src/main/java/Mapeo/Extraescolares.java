package Mapeo;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "extraescolares")
public class Extraescolares {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_extraescolar")
    private Integer idExtraescolar;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Column(name = "descripcion")
    private String descripcion;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo", nullable = false)
    private TipoExtraescolar tipo;

    @ManyToOne
    @JoinColumn(name = "id_profesor")
    private Profesores profesor;

    public enum TipoExtraescolar {
        academico, deportivo, religioso
    }

    @OneToMany(mappedBy = "extraescolar", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Horarios> horarios = new ArrayList<>();


    // Constructor
    public Extraescolares() {
    }

    public Extraescolares(String nombre, String descripcion, TipoExtraescolar tipo, Profesores profesor) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.tipo = tipo;
        this.profesor = profesor;
    }

    // Getters y Setters

    public Integer getIdExtraescolar() {
        return idExtraescolar;
    }

    public void setIdExtraescolar(Integer idExtraescolar) {
        this.idExtraescolar = idExtraescolar;
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

    public TipoExtraescolar getTipo() {
        return tipo;
    }

    public void setTipo(TipoExtraescolar tipo) {
        this.tipo = tipo;
    }

    public Profesores getProfesor() {
        return profesor;
    }

    public void setProfesor(Profesores profesor) {
        this.profesor = profesor;
    }

    public List<Horarios> getHorarios() {
        return horarios;
    }

    public void setHorarios(List<Horarios> horarios) {
        this.horarios = horarios;
    }

    @Override
    public String toString() {
        return nombre + " (" + tipo + ")";
    }
}

