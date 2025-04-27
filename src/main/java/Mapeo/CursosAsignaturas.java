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

    // Constructor
    public CursosAsignaturas() {}

    public CursosAsignaturas(Cursos curso, Asignaturas asignatura) {
        this.curso = curso;
        this.asignatura = asignatura;
    }

    // Getters y Setters

    public Cursos getCurso() {
        return curso;
    }

    public void setCurso(Cursos curso) {
        this.curso = curso;
    }

    public Asignaturas getAsignatura() {
        return asignatura;
    }

    public void setAsignatura(Asignaturas asignatura) {
        this.asignatura = asignatura;
    }


}
