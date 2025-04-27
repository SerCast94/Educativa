package Mapeo;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "historialacademico")
public class HistorialAcademico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_historial")
    private Integer idHistorial;

    @ManyToOne
    @JoinColumn(name = "id_estudiante", nullable = false)
    private Estudiantes estudiante;

    @ManyToOne
    @JoinColumn(name = "id_asignatura", nullable = false)
    private Asignaturas asignatura;

    @Column(name = "nota_final")
    private double notaFinal;

    @Column(name = "fecha_aprobacion")
    private Date fechaAprobacion;

    @Column(name = "comentarios")
    private String comentarios;


    // Constructor
    public HistorialAcademico() {
    }

    public HistorialAcademico(Estudiantes estudiante, Asignaturas asignatura, double notaFinal, Date fechaAprobacion, String comentarios) {
        this.estudiante = estudiante;
        this.asignatura = asignatura;
        this.notaFinal = notaFinal;
        this.fechaAprobacion = fechaAprobacion;
        this.comentarios = comentarios;
    }

    // Getters y Setters

    public Integer getIdHistorial() {
        return idHistorial;
    }

    public void setIdHistorial(Integer idHistorial) {
        this.idHistorial = idHistorial;
    }

    public Estudiantes getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(Estudiantes estudiante) {
        this.estudiante = estudiante;
    }

    public Asignaturas getAsignatura() {
        return asignatura;
    }

    public void setAsignatura(Asignaturas asignatura) {
        this.asignatura = asignatura;
    }

    public double getNotaFinal() {
        return notaFinal;
    }

    public void setNotaFinal(double notaFinal) {
        this.notaFinal = notaFinal;
    }

    public Date getFechaAprobacion() {
        return fechaAprobacion;
    }

    public void setFechaAprobacion(Date fechaAprobacion) {
        this.fechaAprobacion = fechaAprobacion;
    }

    public String getComentarios() {
        return comentarios;
    }

    public void setComentarios(String comentarios) {
        this.comentarios = comentarios;
    }


}

