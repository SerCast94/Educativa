package Mapeo;

import jakarta.persistence.*;

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

    // Constructor por defecto
    public CursosAsignaturas() {}

    // Constructor con parámetros
    public CursosAsignaturas(Cursos curso, Asignaturas asignatura) {
        setCurso(curso);
        setAsignatura(asignatura);
    }

    // Getters y Setters
    public Cursos getCurso() {
        return curso;
    }

    public void setCurso(Cursos curso) {
        if (curso == null) {
            throw new IllegalArgumentException("El curso no puede ser nulo.");
        } else {
            this.curso = curso;
        }
    }

    public Asignaturas getAsignatura() {
        return asignatura;
    }

    public void setAsignatura(Asignaturas asignatura) {
        if (asignatura == null) {
            throw new IllegalArgumentException("La asignatura no puede ser nula.");
        } else {
            this.asignatura = asignatura;
        }
    }



    // Override de toString si es necesario (opcional)
    @Override
    public String toString() {
        return "Curso: " + curso.getNombre() + " - Asignatura: " + asignatura.getNombre();
    }
}
