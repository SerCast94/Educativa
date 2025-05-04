package Mapeo;

import jakarta.persistence.*;
import java.sql.Date;
import java.sql.Time;

/**
 * Clase que representa una reserva de pista para actividades extraescolares.
 * Contiene información sobre la fecha de reserva, la hora y la pista reservada.
 */
@Entity
@Table(name = "extraescolares", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"hora", "pista", "fecha_reserva"})
})
public class Extraescolares {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "fecha_reserva", nullable = false)
    private Date fechaReserva;

    @Column(name = "hora", nullable = false)
    private Time hora;

    @Column(name = "pista", nullable = false, length = 100)
    private String pista;

    // CONSTRUCTORES

    /**
     * Constructor por defecto de la clase Extraescolares.
     */
    public Extraescolares() {
    }

    /**
     * Constructor de la clase Extraescolares con todos los atributos de la reserva.
     * @param fechaReserva Fecha de la reserva.
     * @param hora         Hora de la reserva.
     * @param pista        Pista reservada.
     */
    public Extraescolares(Date fechaReserva, Time hora, String pista) {
        setFechaReserva(fechaReserva);
        setHora(hora);
        setPista(pista);
    }

    // GETTERS Y SETTERS

    /**
     * Método que devuelve el ID de la reserva.
     * @return ID de la reserva.
     */
    public Integer getId() {
        return id;
    }

    /**
     * Método que establece el ID de la reserva.
     * @param id ID de la reserva.
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Método que devuelve la fecha de reserva.
     * @return Fecha de reserva.
     */
    public Date getFechaReserva() {
        return fechaReserva;
    }

    /**
     * Método que establece la fecha de reserva.
     * @param fechaReserva Fecha de reserva.
     */
    public void setFechaReserva(Date fechaReserva) {
        if (fechaReserva == null) {
            throw new IllegalArgumentException("La fecha de reserva no puede ser nula.");
        }
        this.fechaReserva = fechaReserva;
    }

    /**
     * Método que devuelve la hora de reserva.
     * @return Hora de reserva.
     */
    public Time getHora() {
        return hora;
    }

    /**
     * Método que establece la hora de reserva.
     * @param hora Hora de reserva.
     */
    public void setHora(Time hora) {
        if (hora == null) {
            throw new IllegalArgumentException("La hora no puede ser nula.");
        }
        this.hora = hora;
    }

    /**
     * Método que devuelve la pista reservada.
     * @return Pista reservada.
     */
    public String getPista() {
        return pista;
    }

    /**
     * Método que establece la pista reservada con validaciones.
     * @param pista Pista reservada.
     */
    public void setPista(String pista) {
        if (pista == null || pista.trim().isEmpty()) {
            throw new IllegalArgumentException("La pista no puede ser nula o vacía.");
        }
        if (pista.length() > 100) {
            throw new IllegalArgumentException("La pista no puede exceder los 100 caracteres.");
        }
        this.pista = pista;
    }
}