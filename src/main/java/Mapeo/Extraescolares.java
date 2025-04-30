package Mapeo;

import jakarta.persistence.*;

@Entity
@Table(name = "extraescolares")
public class Extraescolares {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "fecha_reserva")
    private String fechaReserva;

    @Column(name = "hora")
    private String hora;

    @Column(name = "pista")
    private String pista;

    // Getters y setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFechaReserva() {
        return fechaReserva;
    }

    public void setFechaReserva(String fechaReserva) {
        this.fechaReserva = fechaReserva;
    }

    public String getHora() {
        return hora.substring(0, 5);
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getPista() {
        return pista;
    }

    public void setPista(String pista) {
        this.pista = pista;
    }
}