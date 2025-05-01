package Mapeo;

import jakarta.persistence.*;
import java.sql.Time;

@Entity
@Table(name = "horarios")
public class Horarios {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_horario")
    private Integer idHorario;

    @ManyToOne
    @JoinColumn(name = "id_asignatura", nullable = false)
    private Asignaturas asignatura;

    @Enumerated(EnumType.STRING)
    @Column(name = "dia_semana", nullable = false)
    private DiaSemana diaSemana;

    @Column(name = "hora_inicio", nullable = false)
    private Time horaInicio;

    @Column(name = "hora_fin", nullable = false)
    private Time horaFin;

    @ManyToOne
    @JoinColumn(name = "id_profesor", nullable = false)
    private Profesores profesor;

    public enum DiaSemana {
        lunes, martes, miercoles, jueves, viernes, sabado, domingo
    }

    // Constructor por defecto
    public Horarios() {}

    // Constructor con uso de setters para validación
    public Horarios(Asignaturas asignatura, DiaSemana diaSemana, Time horaInicio, Time horaFin, Profesores profesor) {
        setAsignatura(asignatura);
        setDiaSemana(diaSemana);
        setHoraInicio(horaInicio);
        setHoraFin(horaFin);
        setProfesor(profesor);
    }

    // Getters y Setters

    public Integer getIdHorario() {
        return idHorario;
    }

    public void setIdHorario(Integer idHorario) {
        this.idHorario = idHorario;
    }

    public Asignaturas getAsignatura() {
        return asignatura;
    }

    public void setAsignatura(Asignaturas asignatura) {
        if (asignatura == null) {
            throw new IllegalArgumentException("La asignatura no puede ser null.");
        }
        this.asignatura = asignatura;
    }

    public DiaSemana getDiaSemana() {
        return diaSemana;
    }

    public void setDiaSemana(DiaSemana diaSemana) {
        if (diaSemana == null) {
            throw new IllegalArgumentException("El día de la semana no puede ser null.");
        }
        this.diaSemana = diaSemana;
    }

    public Time getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(Time horaInicio) {
        if (horaInicio == null) {
            throw new IllegalArgumentException("La hora de inicio no puede ser null.");
        }
        if (this.horaFin != null && horaInicio.after(this.horaFin)) {
            throw new IllegalArgumentException("La hora de inicio no puede ser posterior a la hora de fin.");
        }
        this.horaInicio = horaInicio;
    }

    public Time getHoraFin() {
        return horaFin;
    }

    public void setHoraFin(Time horaFin) {
        if (horaFin == null) {
            throw new IllegalArgumentException("La hora de fin no puede ser null.");
        }
        if (this.horaInicio != null && horaFin.before(this.horaInicio)) {
            throw new IllegalArgumentException("La hora de fin no puede ser anterior a la hora de inicio.");
        }
        this.horaFin = horaFin;
    }

    public Profesores getProfesor() {
        return profesor;
    }

    public void setProfesor(Profesores profesor) {
        if (profesor == null) {
            throw new IllegalArgumentException("El profesor no puede ser null.");
        }
        this.profesor = profesor;
    }
}
