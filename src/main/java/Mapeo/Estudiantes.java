package Mapeo;
import jakarta.persistence.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "estudiantes")
public class Estudiantes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_estudiante")
    private Integer idEstudiante;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Column(name = "apellido", nullable = false)
    private String apellido;

    @Column(name = "dni", nullable = false)
    private String dni;

    @Column(name = "fecha_nacimiento", nullable = false)
    private Date fechaNacimiento;

    @Column(name = "direccion")
    private String direccion;

    @Column(name = "telefono")
    private String telefono;

    @Column(name = "email")
    private String email;

    @Column(name = "fecha_matricula", nullable = false)
    private Date fechaMatricula;

    @ManyToOne
    @JoinColumn(name = "id_tutor")
    private Tutores tutor;

    @Column(name = "usuario", nullable = false, unique = true)
    private String usuario;

    @Column(name = "contrasena", nullable = false)
    private String contrasena;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado", nullable = false)
    private EstadoEstudiante estado;

    public enum EstadoEstudiante {
        activo, inactivo
    }

    @OneToMany(mappedBy = "estudiante", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Matriculas> matriculas = new ArrayList<>();

    @OneToMany(mappedBy = "estudiante", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Asistencia> asistencias = new ArrayList<>();

    @OneToMany(mappedBy = "estudiante", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Becas> becas = new ArrayList<>();

    @OneToMany(mappedBy = "estudiante", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Convalidaciones> convalidaciones = new ArrayList<>();

    @OneToMany(mappedBy = "estudiante", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<EstudiantesEventos> estudiantesEventos = new ArrayList<>();

    @OneToMany(mappedBy = "estudiante", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<HistorialAcademico> historialAcademico = new ArrayList<>();

    // Constructor
    public Estudiantes() {
    }

    public Estudiantes(String nombre, String apellido, String dni, Date fechaNacimiento, String direccion,
                       String telefono, String email, Date fechaMatricula, Tutores tutor, String usuario,
                       String contrasena, EstadoEstudiante estado) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.fechaNacimiento = fechaNacimiento;
        this.direccion = direccion;
        this.telefono = telefono;
        this.email = email;
        this.fechaMatricula = fechaMatricula;
        this.tutor = tutor;
        this.usuario = usuario;
        this.contrasena = contrasena;
        this.estado = estado;
    }

    public Estudiantes(String nombre, String apellido, String dni, Date fechaNacimiento, String direccion,
                       String telefono, String email, Date fechaMatricula, Tutores tutor, String usuario,
                       EstadoEstudiante estado) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.fechaNacimiento = fechaNacimiento;
        this.direccion = direccion;
        this.telefono = telefono;
        this.email = email;
        this.fechaMatricula = fechaMatricula;
        this.tutor = tutor;
        this.usuario = usuario;
        this.estado = estado;
    }

    // Getters y Setters

    public Integer getIdEstudiante() {
        return idEstudiante;
    }

    public void setIdEstudiante(Integer idEstudiante) {
        this.idEstudiante = idEstudiante;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getFechaMatricula() {
        return fechaMatricula;
    }

    public void setFechaMatricula(Date fechaMatricula) {
        this.fechaMatricula = fechaMatricula;
    }

    public Tutores getTutor() {
        return tutor;
    }

    public void setTutor(Tutores tutor) {
        this.tutor = tutor;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public EstadoEstudiante getEstado() {
        return estado;
    }

    public void setEstado(EstadoEstudiante estado) {
        this.estado = estado;
    }

    public List<Matriculas> getMatriculas() {
        return matriculas;
    }

    public void setMatriculas(List<Matriculas> matriculas) {
        this.matriculas = matriculas;
    }

    public List<Asistencia> getAsistencias() {
        return asistencias;
    }

    public void setAsistencias(List<Asistencia> asistencias) {
        this.asistencias = asistencias;
    }

    public List<Becas> getBecas() {
        return becas;
    }

    public void setBecas(List<Becas> becas) {
        this.becas = becas;
    }

    public List<Convalidaciones> getConvalidaciones() {
        return convalidaciones;
    }

    public void setConvalidaciones(List<Convalidaciones> convalidaciones) {
        this.convalidaciones = convalidaciones;
    }

    public List<EstudiantesEventos> getEstudiantesEventos() {
        return estudiantesEventos;
    }

    public void setEstudiantesEventos(List<EstudiantesEventos> estudiantesEventos) {
        this.estudiantesEventos = estudiantesEventos;
    }

    public List<HistorialAcademico> getHistorialAcademico() {
        return historialAcademico;
    }

    public void setHistorialAcademico(List<HistorialAcademico> historialAcademico) {
        this.historialAcademico = historialAcademico;
    }

    @Override
    public String toString() {
        return nombre + " " + apellido;
    }

}

