package Mapeo;

import jakarta.persistence.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase que representa un evento o excursión
 * Contiene información sobre el evento, su nombre, descripción,
 */
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

    // CONSTRUCTORES

    /**
     * Constructor por defecto de la clase Eventos.
     */
    public Eventos() {
    }

    /**
     * Constructor de la clase Eventos con todos los atributos del evento.
     * @param nombre       Nombre del evento.
     * @param descripcion  Descripción del evento.
     * @param fechaInicio  Fecha de inicio del evento.
     * @param fechaFin     Fecha de fin del evento.
     * @param ubicacion    Ubicación del evento.
     * @param tipoEvento   Tipo de evento (académico, deportivo, religioso).
     */
    public Eventos(String nombre, String descripcion, Date fechaInicio, Date fechaFin, String ubicacion, TipoEvento tipoEvento) {
        setNombre(nombre);
        setDescripcion(descripcion);
        setFechaInicio(fechaInicio);
        setFechaFin(fechaFin);
        setUbicacion(ubicacion);
        setTipoEvento(tipoEvento);
    }

    // GETTERS Y SETTERS CON VALIDACIONES

    /**
     * Método que devuelve el id del evento.
     * @return idEvento
     */
    public Integer getIdEvento() {
        return idEvento;
    }

    /**
     * Método que establece el id del evento.
     * @param idEvento id del evento
     */
    public void setIdEvento(Integer idEvento) {
        this.idEvento = idEvento;
    }

    /**
     * Método que devuelve el nombre del evento.
     * @return nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Método que establece el nombre del evento con validaciones.
     * @param nombre nombre del evento
     */
    public void setNombre(String nombre) {
        if (nombre != null && nombre.length() <= 100) {
            this.nombre = nombre;
        } else {
            throw new IllegalArgumentException("El nombre no puede ser nulo y no puede exceder los 100 caracteres.");
        }
    }

    /**
     * Método que devuelve la descripción del evento.
     * @return descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * Método que establece la descripción del evento con validaciones.
     * @param descripcion descripción del evento
     */
    public void setDescripcion(String descripcion) {
        if (descripcion != null && descripcion.length() <= 255) {
            this.descripcion = descripcion;
        } else if (descripcion != null && descripcion.length() > 255) {
            throw new IllegalArgumentException("La descripción no puede exceder los 255 caracteres.");
        } else {
            this.descripcion = null;
        }
    }

    /**
     * Método que devuelve la fecha de inicio del evento.
     * @return fechaInicio
     */
    public Date getFechaInicio() {
        return fechaInicio;
    }

    /**
     * Método que establece la fecha de inicio del evento con validaciones.
     * @param fechaInicio fecha de inicio del evento
     */
    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    /**
     * Método que devuelve la fecha de fin del evento.
     * @return fechaFin
     */
    public Date getFechaFin() {
        return fechaFin;
    }

    /**
     * Método que establece la fecha de fin del evento con validaciones.
     * @param fechaFin fecha de fin del evento
     */
    public void setFechaFin(Date fechaFin) {
        if (fechaFin != null && fechaFin.before(fechaInicio)) {
            throw new IllegalArgumentException("La fecha de fin no puede ser anterior a la fecha de inicio.");
        }
        this.fechaFin = fechaFin;
    }

    /**
     * Método que devuelve la ubicación del evento.
     * @return ubicacion
     */
    public String getUbicacion() {
        return ubicacion;
    }

    /**
     * Método que establece la ubicación del evento con validaciones.
     * @param ubicacion ubicación del evento
     */
    public void setUbicacion(String ubicacion) {
        if (ubicacion != null && ubicacion.length() <= 255) {
            this.ubicacion = ubicacion;
        } else if (ubicacion != null && ubicacion.length() > 255) {
            throw new IllegalArgumentException("La ubicación no puede exceder los 255 caracteres.");
        } else {
            this.ubicacion = null;
        }
    }

    /**
     * Método que devuelve el tipo de evento.
     * @return tipoEvento
     */
    public TipoEvento getTipoEvento() {
        return tipoEvento;
    }

    /**
     * Método que establece el tipo de evento con validaciones.
     * @param tipoEvento tipo de evento
     */
    public void setTipoEvento(TipoEvento tipoEvento) {
        if (tipoEvento != null) {
            this.tipoEvento = tipoEvento;
        } else {
            throw new IllegalArgumentException("El tipo de evento no puede ser nulo.");
        }
    }

    /**
     * Método que devuelve la lista de estudiantes que asisten al evento.
     * @return estudiantesEventos
     */
    public List<EstudiantesEventos> getEstudiantesEventos() {
        return estudiantesEventos;
    }

    /**
     * Método que establece la lista de estudiantes que asisten al evento.
     * @param estudiantesEventos lista de estudiantes que asisten al evento
     */
    public void setEstudiantesEventos(List<EstudiantesEventos> estudiantesEventos) {
        this.estudiantesEventos = estudiantesEventos;
    }

    /** Método que muestra el nombre del evento.
     * @return nombre
     */
    @Override
    public String toString() {
        return nombre;
    }
}
