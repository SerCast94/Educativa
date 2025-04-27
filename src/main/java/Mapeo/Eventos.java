package Mapeo;
import jakarta.persistence.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "eventos")
public class Eventos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_evento")
    private Integer idEvento;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "fecha_inicio", nullable = false)
    private Date fechaInicio;

    @Column(name = "fecha_fin", nullable = false)
    private Date fechaFin;

    @Column(name = "ubicacion")
    private String ubicacion;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_evento", nullable = false)
    private TipoEvento tipoEvento;

    public enum TipoEvento {
        academico, deportivo, religioso
    }

    @OneToMany(mappedBy = "evento", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<EstudiantesEventos> estudiantesEventos = new ArrayList<>();


    // Constructor
    public Eventos() {
    }

    public Eventos(String nombre, String descripcion, Date fechaInicio, Date fechaFin, String ubicacion, TipoEvento tipoEvento) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.ubicacion = ubicacion;
        this.tipoEvento = tipoEvento;
    }

    // Getters y Setters

    public Integer getIdEvento() {
        return idEvento;
    }

    public void setIdEvento(Integer idEvento) {
        this.idEvento = idEvento;
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

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public TipoEvento getTipoEvento() {
        return tipoEvento;
    }

    public void setTipoEvento(TipoEvento tipoEvento) {
        this.tipoEvento = tipoEvento;
    }

    public List<EstudiantesEventos> getEstudiantesEventos() {
        return estudiantesEventos;
    }

    public void setEstudiantesEventos(List<EstudiantesEventos> estudiantesEventos) {
        this.estudiantesEventos = estudiantesEventos;
    }
}
