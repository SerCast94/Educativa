package Mapeo;

import jakarta.persistence.*;
import java.sql.Time;


/**
 * Clase que representa un horario de clases en el sistema.
 * Contiene información sobre la asignatura, el día de la semana,
 * la hora de inicio y fin, y el profesor encargado.
 */
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

    // CONSTRUCTORES

    /**
     * Constructor por defecto de la clase Horarios.
     */
    public Horarios() {}

    /**
     * Constructor de la clase Horarios con todos los atributos del horario.
     * @param asignatura  Asignatura a la que pertenece el horario.
     * @param diaSemana   Día de la semana del horario.
     * @param horaInicio  Hora de inicio del horario.
     * @param horaFin     Hora de fin del horario.
     * @param profesor    Profesor encargado del horario.
     */
    public Horarios(Asignaturas asignatura, DiaSemana diaSemana, Time horaInicio, Time horaFin, Profesores profesor) {
        setAsignatura(asignatura);
        setDiaSemana(diaSemana);
        setHoraInicio(horaInicio);
        setHoraFin(horaFin);
        setProfesor(profesor);
    }

    // GETTERS Y SETTERS

    /**
     * Método que devuelve el ID del horario.
     * @return ID del horario.
     */
    public Integer getIdHorario() {
        return idHorario;
    }

    /**
     * Método que establece el ID del horario.
     * @param idHorario ID del horario.
     */
    public void setIdHorario(Integer idHorario) {
        this.idHorario = idHorario;
    }

    /**
     * Método que devuelve la asignatura del horario.
     * @return Asignatura del horario.
     */
    public Asignaturas getAsignatura() {
        return asignatura;
    }

    /**
     * Método que establece la asignatura del horario con validación.
     * @param asignatura Asignatura del horario.
     */
    public void setAsignatura(Asignaturas asignatura) {
        if (asignatura == null) {
            throw new IllegalArgumentException("La asignatura no puede ser null.");
        }
        this.asignatura = asignatura;
    }

    /**
     * Método que devuelve el día de la semana del horario.
     * @return Día de la semana del horario.
     */
    public DiaSemana getDiaSemana() {
        return diaSemana;
    }

    /**
     * Método que establece el día de la semana del horario con validación.
     * @param diaSemana Día de la semana del horario.
     */
    public void setDiaSemana(DiaSemana diaSemana) {
        if (diaSemana == null) {
            throw new IllegalArgumentException("El día de la semana no puede ser null.");
        }
        this.diaSemana = diaSemana;
    }

    /**
     * Método que devuelve la hora de inicio del horario.
     * @return Hora de inicio del horario.
     */
    public Time getHoraInicio() {
        return horaInicio;
    }

    /**
     * Método que establece la hora de inicio del horario con validación.
     * @param horaInicio Hora de inicio del horario.
     */
    public void setHoraInicio(Time horaInicio) {
        if (horaInicio == null) {
            throw new IllegalArgumentException("La hora de inicio no puede ser null.");
        }
        if (this.horaFin != null && horaInicio.after(this.horaFin)) {
            throw new IllegalArgumentException("La hora de inicio no puede ser posterior a la hora de fin.");
        }
        this.horaInicio = horaInicio;
    }

    /**
     * Método que devuelve la hora de fin del horario.
     * @return Hora de fin del horario.
     */
    public Time getHoraFin() {
        return horaFin;
    }

    /**
     * Método que establece la hora de fin del horario con validación.
     * @param horaFin Hora de fin del horario.
     */
    public void setHoraFin(Time horaFin) {
        if (horaFin == null) {
            throw new IllegalArgumentException("La hora de fin no puede ser null.");
        }
        if (this.horaInicio != null && horaFin.before(this.horaInicio)) {
            throw new IllegalArgumentException("La hora de fin no puede ser anterior a la hora de inicio.");
        }
        this.horaFin = horaFin;
    }

    /**
     * Método que devuelve el profesor del horario.
     * @return Profesor del horario.
     */
    public Profesores getProfesor() {
        return profesor;
    }

    /**
     * Método que establece el profesor del horario con validación.
     * @param profesor Profesor del horario.
     */
    public void setProfesor(Profesores profesor) {
        if (profesor == null) {
            throw new IllegalArgumentException("El profesor no puede ser null.");
        }
        this.profesor = profesor;
    }
}
