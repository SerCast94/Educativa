package Mapeo;

import jakarta.persistence.*;
import java.sql.Date;

/**
 * Clase que representa la asistencia de un estudiante a un curso.
 * Contiene información sobre el estudiante, el curso, la fecha de asistencia,
 * si la asistencia está justificada y el motivo de ausencia.
 */
@Entity
@Table(name = "asistencia")
public class Asistencia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_asistencia")
    private Integer idAsistencia;

    @ManyToOne
    @JoinColumn(name = "id_estudiante", nullable = false)
    private Estudiantes estudiante;

    @ManyToOne
    @JoinColumn(name = "id_curso", nullable = false)
    private Cursos curso;

    @Column(name = "fecha", nullable = false)
    private Date fecha;

    @Column(name = "justificado")
    private Boolean justificado;

    @Column(name = "motivo_ausencia")
    private String motivoAusencia;

    // CONSTRUCTORES

    /**
     * Constructor por defecto de la clase Asistencia.
     */
    public Asistencia() {
    }

    /**
     * Constructor de la clase Asistencia con todos los atributos de la asistencia.
     * @param estudiante     Estudiante que asistió al curso.
     * @param curso          Curso al que asistió el estudiante.
     * @param fecha          Fecha de la asistencia.
     * @param justificado    Indica si la asistencia está justificada.
     * @param motivoAusencia Motivo de la ausencia, si aplica.
     */
    public Asistencia(Estudiantes estudiante, Cursos curso, Date fecha, Boolean justificado, String motivoAusencia) {
        setEstudiante(estudiante);
        setCurso(curso);
        setFecha(fecha);
        setJustificado(justificado);
        setMotivoAusencia(motivoAusencia);
    }

    // Getters y Setters con validaciones

    /**
     * Método que devuelve el ID de la asistencia.
     * @return ID de la asistencia.
     */
    public Integer getIdAsistencia() {
        return idAsistencia;
    }

    /**
     * Método que establece el ID de la asistencia.
     * @param idAsistencia ID de la asistencia.
     */
    public void setIdAsistencia(Integer idAsistencia) {
        this.idAsistencia = idAsistencia;
    }

    /**
     * Método que devuelve el estudiante asociado a la asistencia.
     * @return Estudiante asociado a la asistencia.
     */
    public Estudiantes getEstudiante() {
        return estudiante;
    }

    /**
     * Método que establece el estudiante asociado a la asistencia con validaciones.
     * @param estudiante Estudiante asociado a la asistencia.
     */
    public void setEstudiante(Estudiantes estudiante) {
        if (estudiante == null) {
            throw new IllegalArgumentException("El estudiante no puede ser nulo.");
        }
        this.estudiante = estudiante;
    }

    /**
     * Método que devuelve el curso asociado a la asistencia.
     * @return Curso asociado a la asistencia.
     */
    public Cursos getCurso() {
        return curso;
    }

    /**
     * Método que establece el curso asociado a la asistencia con validaciones.
     * @param curso Curso asociado a la asistencia.
     */
    public void setCurso(Cursos curso) {
        if (curso == null) {
            throw new IllegalArgumentException("El curso no puede ser nulo.");
        }
        this.curso = curso;
    }

    /**
     * Método que devuelve la fecha de la asistencia.
     * @return Fecha de la asistencia.
     */
    public Date getFecha() {
        return fecha;
    }

    /**
     * Método que establece la fecha de la asistencia con validaciones.
     * @param fecha Fecha de la asistencia.
     */
    public void setFecha(Date fecha) {
        if (fecha == null) {
            throw new IllegalArgumentException("La fecha no puede ser nula.");
        }
        this.fecha = fecha;
    }

    /**
     * Método que devuelve si la asistencia está justificada.
     * @return true si la asistencia está justificada, false en caso contrario.
     */
    public Boolean getJustificado() {
        return justificado;
    }

    /**
     * Método que establece si la asistencia está justificada.
     * @param justificado true si la asistencia está justificada, false en caso contrario.
     */
    public void setJustificado(Boolean justificado) {
        this.justificado = justificado;
    }

    /**
     * Método que devuelve el motivo de ausencia.
     * @return Motivo de ausencia.
     */
    public String getMotivoAusencia() {
        return motivoAusencia;
    }

    /**
     * Método que establece el motivo de ausencia con validaciones.
     * @param motivoAusencia Motivo de ausencia.
     */
    public void setMotivoAusencia(String motivoAusencia) {
        if (motivoAusencia != null && motivoAusencia.length() > 255) {
            throw new IllegalArgumentException("El motivo de ausencia no puede exceder los 255 caracteres.");
        }
        this.motivoAusencia = motivoAusencia;
    }
}
