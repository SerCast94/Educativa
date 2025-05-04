package Mapeo;

import jakarta.persistence.*;

/**
 * Clase que representa la relación entre Cursos y Asignaturas en el sistema.
 * Esta clase es una entidad que mapea la tabla "cursos_asignaturas" en la base de datos.
 */
@Entity
@Table(name = "cursos_asignaturas")
@IdClass(CursosAsignaturasId.class)
public class CursosAsignaturas {

    @Id
    @ManyToOne
    @JoinColumn(name = "id_curso", nullable = false, foreignKey = @ForeignKey(name = "FK_curso_asignatura"))
    private Cursos curso;

    @Id
    @ManyToOne
    @JoinColumn(name = "id_asignatura", nullable = false, foreignKey = @ForeignKey(name = "FK_asignatura_curso"))
    private Asignaturas asignatura;

    // CONSTRUCTORES

    /**
     * Constructor por defecto de la clase CursosAsignaturas.
     */
    public CursosAsignaturas() {}

    /**
     * Constructor de la clase CursosAsignaturas con los atributos curso y asignatura.
     * @param curso      Curso asociado a la asignatura.
     * @param asignatura Asignatura asociada al curso.
     */
    public CursosAsignaturas(Cursos curso, Asignaturas asignatura) {
        setCurso(curso);
        setAsignatura(asignatura);
    }

    // GETTERS Y SETTERS CON VALIDACIONES

    /**
     * Obtiene el curso asociado a la asignatura.
     * @return El curso asociado.
     */
    public Cursos getCurso() {
        return curso;
    }

    /**
     * Establece el curso asociado a la asignatura.
     * @param curso El curso a establecer.
     */
    public void setCurso(Cursos curso) {
        if (curso == null) {
            throw new IllegalArgumentException("El curso no puede ser nulo.");
        } else {
            this.curso = curso;
        }
    }

    /**
     * Obtiene la asignatura asociada al curso.
     * @return La asignatura asociada.
     */
    public Asignaturas getAsignatura() {
        return asignatura;
    }

    /**
     * Establece la asignatura asociada al curso.
     * @param asignatura La asignatura a establecer.
     */
    public void setAsignatura(Asignaturas asignatura) {
        if (asignatura == null) {
            throw new IllegalArgumentException("La asignatura no puede ser nula.");
        } else {
            this.asignatura = asignatura;
        }
    }

    /**
     * Método toString que devuelve cadena con curso y asignatura.
     * @return Cadena que representa la relación entre curso y asignatura.
     */
    @Override
    public String toString() {
        return "Curso: " + curso.getNombre() + " - Asignatura: " + asignatura.getNombre();
    }
}
