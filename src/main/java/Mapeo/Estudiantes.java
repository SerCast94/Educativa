package Mapeo;
import Vista.Util.CustomDialog;
import jakarta.persistence.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

    // Validación de DNI
     static public boolean validarDNI(String dni) {
        // Verificar si el DNI no es nulo ni vacío y tiene exactamente 9 caracteres (8 dígitos + 1 letra)
        if (dni == null || dni.length() != 9) {
            return false;
        }

        // Extraer los 8 primeros caracteres (números) y la última letra
        String numero = dni.substring(0, 8);
        char letra = dni.charAt(8);

        // Verificar que los primeros 8 caracteres son números
        if (!numero.matches("\\d{8}")) {
            return false;
        }

        // Validar la letra
        String letrasValidas = "TRWAGMYFPDXBNJZSQVHLCKE";
        int indice = Integer.parseInt(numero) % 23;

        // Comparar la letra del DNI con la letra esperada
        return letra == letrasValidas.charAt(indice);
    }

    // Validación de correo electrónico
    static public boolean validarEmail(String email) {
        String regex = "^[A-Za-z0-9+_.-]+@(.+)$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    // Validación de teléfono
    static public boolean validarTelefono(String telefono) {
        return telefono != null && telefono.matches("^\\+?\\d{7,15}$");
    }

    // Validación de contraseña
    static public boolean validarContrasena(String contrasena) {
        return contrasena != null && contrasena.length() >= 6;
    }

    // Getters y Setters con validaciones

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
        if (nombre != null && nombre.length() <= 100) {
            this.nombre = nombre;
        } else {
            throw new IllegalArgumentException("El nombre no debe exceder los 100 caracteres.");
        }
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        if (apellido != null && apellido.length() <= 100) {
            this.apellido = apellido;
        } else {
            throw new IllegalArgumentException("El apellido no debe exceder los 100 caracteres.");
        }
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        if (validarDNI(dni)) {
            this.dni = dni;
        } else {
            throw new IllegalArgumentException("El DNI debe tener entre 8 y 20 dígitos.");
        }
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        if (fechaNacimiento.before(new Date(System.currentTimeMillis()))) {
            this.fechaNacimiento = fechaNacimiento;
        } else {
            throw new IllegalArgumentException("La fecha de nacimiento no puede ser futura.");
        }
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
        if (validarTelefono(telefono)) {
            this.telefono = telefono;
        } else {
            throw new IllegalArgumentException("El teléfono no tiene un formato válido.");
        }
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if (validarEmail(email)) {
            this.email = email;
        } else {
            throw new IllegalArgumentException("El email no tiene un formato válido.");
        }
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
        if (validarContrasena(contrasena)) {
            this.contrasena = contrasena;
        } else {
            throw new IllegalArgumentException("La contraseña debe tener al menos 6 caracteres.");
        }
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
