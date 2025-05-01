package Mapeo;

import jakarta.persistence.*;
import java.sql.Date;
import java.sql.Time;

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

    // Constructor


    public Extraescolares() {
    }

    public Extraescolares(Date fechaReserva, Time hora, String pista) {
        setFechaReserva(fechaReserva);
        setHora(hora);
        setPista(pista);
    }

    // Getters y setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getFechaReserva() {
        return fechaReserva;
    }

    public void setFechaReserva(Date fechaReserva) {
        if (fechaReserva == null) {
            throw new IllegalArgumentException("La fecha de reserva no puede ser nula.");
        }
        this.fechaReserva = fechaReserva;
    }

    public Time getHora() {
        return hora;
    }

    public void setHora(Time hora) {
        if (hora == null) {
            throw new IllegalArgumentException("La hora no puede ser nula.");
        }
        this.hora = hora;
    }

    public String getPista() {
        return pista;
    }

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
