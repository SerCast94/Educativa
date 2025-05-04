package Mapeo;

import jakarta.persistence.*;

/**
 * Clase que representa la relación entre Cursos y Asignaturas en el sistema.
 * Esta clase es una entidad que mapea la tabla "cursos_asignaturas" en la base de datos.
 */
@Entity
@Table(name = "estudiantes_eventos")
@IdClass(EstudiantesEventosId.class)
public class EstudiantesEventos {

    @Id
    @ManyToOne
    @JoinColumn(name = "id_estudiante", nullable = false)
    private Estudiantes estudiante;

    @Id
    @ManyToOne
    @JoinColumn(name = "id_evento", nullable = false)
    private Eventos evento;

    @Column(name = "comentario")
    private String comentario;

    @Column(name = "confirmado")
    private Boolean confirmado;

    // CONSTRUCTORES
    /**
     * Constructor por defecto de la clase EstudiantesEventos.
     */
    public EstudiantesEventos() {}

    /**
     * Constructor de la clase EstudiantesEventos con todos los atributos de la relación.
     * @param estudiante Estudiante asociado al evento.
     * @param evento     Evento asociado al estudiante.
     * @param comentario Comentario adicional sobre la relación.
     * @param confirmado Indica si la relación está confirmada.
     */
    public EstudiantesEventos(Estudiantes estudiante, Eventos evento, String comentario, Boolean confirmado) {
        this.estudiante = estudiante;
        this.evento = evento;
        this.comentario = comentario;
        this.confirmado = confirmado;
    }

    // GETTERS Y SETTERS CON VALIDACIONES

    /**
     * Obtiene el estudiante asociado al evento.
     * @return El estudiante asociado.
     */
    public Estudiantes getEstudiante() {
        return estudiante;
    }

    /**
     * Establece el estudiante asociado al evento con validación.
     * @param estudiante El estudiante a establecer.
     */
    public void setEstudiante(Estudiantes estudiante) {
        if (estudiante != null) {
            this.estudiante = estudiante;
        } else {
            throw new IllegalArgumentException("El estudiante no puede ser nulo.");
        }
    }

    /**
     * Obtiene el evento asociado al estudiante.
     * @return El evento asociado.
     */
    public Eventos getEvento() {
        return evento;
    }

    /**
     * Establece el evento asociado al estudiante con validación.
     * @param evento El evento a establecer.
     */
    public void setEvento(Eventos evento) {
        if (evento != null) {
            this.evento = evento;
        } else {
            throw new IllegalArgumentException("El evento no puede ser nulo.");
        }
    }

    /**
     * Obtiene el comentario adicional sobre la relación.
     * @return El comentario asociado.
     */
    public String getComentario() {
        return comentario;
    }

    /**
     * Establece el comentario adicional sobre la relación con validación.
     * @param comentario El comentario a establecer.
     */
    public void setComentario(String comentario) {
        if (comentario != null && comentario.length() <= 255) {
            this.comentario = comentario;
        } else if (comentario != null && comentario.length() > 255) {
            throw new IllegalArgumentException("El comentario no puede exceder los 255 caracteres.");
        } else {
            this.comentario = null;  // Permito que el comentario sea nulo si no se establece ya que es un campo no obligatorio
        }
    }

    /**
     * Obtiene el estado de confirmación de la relación.
     * @return true si está confirmado, false en caso contrario.
     */
    public Boolean getConfirmado() {
        return confirmado;
    }

    /**
     * Establece el estado de confirmación de la relación con validación.
     * @param confirmado El estado de confirmación a establecer.
     */
    public void setConfirmado(Boolean confirmado) {
        if (confirmado != null) {
            this.confirmado = confirmado;
        } else {
            throw new IllegalArgumentException("El campo 'confirmado' no puede ser nulo.");
        }
    }
}