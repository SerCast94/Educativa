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
        setNombre(nombre);
        setDescripcion(descripcion);
        setFechaInicio(fechaInicio);
        setFechaFin(fechaFin);
        setUbicacion(ubicacion);
        setTipoEvento(tipoEvento);
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
        if (nombre != null && nombre.length() <= 100) {
            this.nombre = nombre;
        } else {
            throw new IllegalArgumentException("El nombre no puede ser nulo y no puede exceder los 100 caracteres.");
        }
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        if (descripcion != null && descripcion.length() <= 255) {
            this.descripcion = descripcion;
        } else if (descripcion != null && descripcion.length() > 255) {
            throw new IllegalArgumentException("La descripción no puede exceder los 255 caracteres.");
        } else {
            this.descripcion = null;
        }
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
        if (fechaFin != null && fechaFin.before(fechaInicio)) {
            throw new IllegalArgumentException("La fecha de fin no puede ser anterior a la fecha de inicio.");
        }
        this.fechaFin = fechaFin;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        if (ubicacion != null && ubicacion.length() <= 255) {
            this.ubicacion = ubicacion;
        } else if (ubicacion != null && ubicacion.length() > 255) {
            throw new IllegalArgumentException("La ubicación no puede exceder los 255 caracteres.");
        } else {
            this.ubicacion = null;
        }
    }

    public TipoEvento getTipoEvento() {
        return tipoEvento;
    }

    public void setTipoEvento(TipoEvento tipoEvento) {
        if (tipoEvento != null) {
            this.tipoEvento = tipoEvento;
        } else {
            throw new IllegalArgumentException("El tipo de evento no puede ser nulo.");
        }
    }

    public List<EstudiantesEventos> getEstudiantesEventos() {
        return estudiantesEventos;
    }

    public void setEstudiantesEventos(List<EstudiantesEventos> estudiantesEventos) {
        this.estudiantesEventos = estudiantesEventos;
    }


    @Override
    public String toString() {
        return nombre;
    }
}
