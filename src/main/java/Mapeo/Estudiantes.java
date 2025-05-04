package Mapeo;

import jakarta.persistence.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Clase que representa a un estudiante en el sistema.
 * Contiene información personal, de contacto y académica del estudiante.
 *
 */
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

    // CONSTRUCTORES

    /**
     * Constructor por defecto de la clase Estudiantes.
     */
    public Estudiantes() {
    }

    /**
     * Constructor de la clase Estudiantes con todos los atributos del estudiante.
     * @param nombre          Nombre del estudiante.
     * @param apellido        Apellido del estudiante.
     * @param dni             DNI del estudiante.
     * @param fechaNacimiento Fecha de nacimiento del estudiante.
     * @param direccion       Dirección del estudiante.
     * @param telefono        Teléfono del estudiante.
     * @param email           Correo electrónico del estudiante.
     * @param fechaMatricula  Fecha de matrícula del estudiante.
     * @param tutor           Tutor del estudiante.
     * @param usuario         Usuario del estudiante.
     * @param contrasena      Contraseña del estudiante.
     * @param estado          Estado del estudiante (activo/inactivo).
     */
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

    /**
     * Constructor de la clase Estudiantes con todos los atributos del estudiante excepto contraseña.
     * @param nombre          Nombre del estudiante.
     * @param apellido        Apellido del estudiante.
     * @param dni             DNI del estudiante.
     * @param fechaNacimiento Fecha de nacimiento del estudiante.
     * @param direccion       Dirección del estudiante.
     * @param telefono        Teléfono del estudiante.
     * @param email           Correo electrónico del estudiante.
     * @param fechaMatricula  Fecha de matrícula del estudiante.
     * @param tutor           Tutor del estudiante.
     * @param usuario         Usuario del estudiante.
     * @param estado          Estado del estudiante (activo/inactivo).
     */
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

    //VALIDACIONES

    /**
     * Método para validar el DNI español.
     * @param dni El DNI a validar.
     * @return true si el DNI es válido, false en caso contrario.
     */
    static public boolean validarDNI(String dni) {

        if (dni == null || dni.length() != 9) {
            return false;
        }

        // Separa numeros y letras
        String numero = dni.substring(0, 8);
        char letra = dni.charAt(8);

        // Valida números
        if (!numero.matches("\\d{8}")) {
            return false;
        }

        // Valida letra
        String letrasValidas = "TRWAGMYFPDXBNJZSQVHLCKE";

        // El índice se calcula como el resto de la división del número entre 23
        int indice = Integer.parseInt(numero) % 23;

        // Comparar la letra del DNI con la letra esperada
        return letra == letrasValidas.charAt(indice);
    }

    /**
     * Método para validar el formato de un email.
     * @param email El email a validar.
     * @return true si el email es válido, false en caso contrario.
     */
    static public boolean validarEmail(String email) {
        if (email == null || email.length() > 100) {
            return false;
        }

        String regex = "^[A-Za-z0-9+_.-]+@(.+)$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    /**
     * Método para validar el formato de un teléfono.
     * @param telefono El teléfono a validar.
     * @return true si el teléfono es válido, false en caso contrario.
     */
    static public boolean validarTelefono(String telefono) {
        return telefono != null && telefono.matches("^\\+?\\d{7,15}$");
    }

    /**
     * Método para validar el formato de la contraseña.
     * @param contrasena  La contraseña a validar.
     * @return true si la contraseña es válida, false en caso contrario.
     */
    static public boolean validarContrasena(String contrasena) {
        return contrasena != null && contrasena.length() >= 6;
    }

    // GETTERS Y SETTERS

    /**
     * Método que devuelve el ID del estudiante.
     * @return ID del estudiante.
     */
    public Integer getIdEstudiante() {
        return idEstudiante;
    }

    /**
     * Método que establece el ID del estudiante.
     * @param idEstudiante ID del estudiante.
     */
    public void setIdEstudiante(Integer idEstudiante) {
        this.idEstudiante = idEstudiante;
    }

    /**
     * Método que devuelve el nombre del estudiante.
     * @return Nombre del estudiante.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Método que establece el nombre del estudiante con validaciones.
     * @param nombre Nombre del estudiante.
     */
    public void setNombre(String nombre) {
        if (nombre != null && nombre.length() <= 100) {
            this.nombre = nombre;
        } else {
            throw new IllegalArgumentException("El nombre no debe exceder los 100 caracteres.");
        }
    }

    /**
     * Método que devuelve el apellido del estudiante.
     * @return Apellido del estudiante.
     */
    public String getApellido() {
        return apellido;
    }

    /**
     * Método que establece el apellido del estudiante con validaciones.
     * @param apellido Apellido del estudiante.
     */
    public void setApellido(String apellido) {
        if (apellido != null && apellido.length() <= 100) {
            this.apellido = apellido;
        } else {
            throw new IllegalArgumentException("El apellido no debe exceder los 100 caracteres.");
        }
    }

    /**
     * Método que devuelve el DNI del estudiante.
     * @return DNI del estudiante.
     */
    public String getDni() {
        return dni;
    }

    /**
     * Método que establece el DNI del estudiante con validaciones.
     * @param dni DNI del estudiante.
     */
    public void setDni(String dni) {
        if (!validarDNI(dni)) {
            throw new IllegalArgumentException("El DNI debe tener entre 8 y 20 dígitos.");
        }
        this.dni = dni;
    }

    /**
     * Método que devuelve la fecha de nacimiento del estudiante.
     * @return Fecha de nacimiento del estudiante.
     */
    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    /**
     * Método que establece la fecha de nacimiento del estudiante con validaciones.
     * @param fechaNacimiento Fecha de nacimiento del estudiante.
     */
    public void setFechaNacimiento(Date fechaNacimiento) {
        if (fechaNacimiento.before(new Date(System.currentTimeMillis()))) {
            this.fechaNacimiento = fechaNacimiento;
        } else {
            throw new IllegalArgumentException("La fecha de nacimiento no puede ser futura.");
        }
    }

    /**
     * Método que devuelve la dirección del estudiante.
     * @return Dirección del estudiante.
     */
    public String getDireccion() {
        return direccion;
    }

    /**
     * Método que establece la dirección del estudiante.
     * @param direccion Dirección del estudiante.
     */
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    /**
     * Método que devuelve el teléfono del estudiante.
     * @return Teléfono del estudiante.
     */
    public String getTelefono() {
        return telefono;
    }

    /**
     * Método que establece el teléfono del estudiante con validaciones.
     * @param telefono Teléfono del estudiante.
     */
    public void setTelefono(String telefono) {
        if (!validarTelefono(telefono)) {
            throw new IllegalArgumentException("El teléfono no puede exceder los 20 caracteres.");
        }
        this.telefono = telefono;
    }

    /**
     * Método que devuelve el correo electrónico del estudiante.
     * @return Correo electrónico del estudiante.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Método que establece el correo electrónico del estudiante con validaciones.
     * @param email Correo electrónico del estudiante.
     */
    public void setEmail(String email) {
        if (!validarEmail(email)) {
            throw new IllegalArgumentException("El email no tiene un formato válido.");
        }
        this.email = email;
    }

    /**
     * Método que devuelve la fecha de matrícula del estudiante.
     * @return Fecha de matrícula del estudiante.
     */
    public Date getFechaMatricula() {
        return fechaMatricula;
    }

    /**
     * Método que establece la fecha de matrícula del estudiante con validaciones.
     * @param fechaMatricula Fecha de matrícula del estudiante.
     */
    public void setFechaMatricula(Date fechaMatricula) {
        this.fechaMatricula = fechaMatricula;
    }

    /**
     * Método que devuelve el tutor del estudiante.
     * @return Tutor del estudiante.
     */
    public Tutores getTutor() {
        return tutor;
    }

    /**
     * Método que establece el tutor del estudiante con validaciones.
     * @param tutor Tutor del estudiante.
     */
    public void setTutor(Tutores tutor) {
        this.tutor = tutor;
    }

    /**
     * Método que devuelve el usuario del estudiante.
     * @return Usuario del estudiante.
     */
    public String getUsuario() {
        return usuario;
    }

    /**
     * Método que establece el usuario del estudiante con validaciones.
     * @param usuario Usuario del estudiante.
     */
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    /**
     * Método que devuelve la contraseña del estudiante.
     * @return Contraseña del estudiante.
     */
    public String getContrasena() {
        return contrasena;
    }

    /**
     * Método que establece la contraseña del estudiante con validaciones.
     * @param contrasena Contraseña del estudiante.
     */
    public void setContrasena(String contrasena) {
        if (!validarContrasena(contrasena)) {
            throw new IllegalArgumentException("La contraseña debe tener al menos 6 caracteres.");
        }
        this.contrasena = contrasena;
    }

    /**
     * Método que devuelve el estado del estudiante.
     * @return Estado del estudiante.
     */
    public EstadoEstudiante getEstado() {
        return estado;
    }

    /**
     * Método que establece el estado del estudiante con validaciones.
     * @param estado Estado del estudiante.
     */
    public void setEstado(EstadoEstudiante estado) {
        this.estado = estado;
    }

    /**
     * Método que devuelve la lista de matrículas del estudiante.
     * @return Lista de matrículas del estudiante.
     */
    public List<Matriculas> getMatriculas() {
        return matriculas;
    }

    /**
     * Método que establece la lista de matrículas del estudiante.
     * @param matriculas Lista de matrículas del estudiante.
     */
    public void setMatriculas(List<Matriculas> matriculas) {
        this.matriculas = matriculas;
    }

    /**
     * Método que devuelve la lista de asistencias del estudiante.
     * @return Lista de asistencias del estudiante.
     */
    public List<Asistencia> getAsistencias() {
        return asistencias;
    }

    /**
     * Método que establece la lista de asistencias del estudiante.
     * @param asistencias Lista de asistencias del estudiante.
     */
    public void setAsistencias(List<Asistencia> asistencias) {
        this.asistencias = asistencias;
    }

    /**
     * Método que devuelve la lista de becas del estudiante.
     * @return Lista de becas del estudiante.
     */
    public List<Becas> getBecas() {
        return becas;
    }

    /**
     * Método que establece la lista de becas del estudiante.
     * @param becas Lista de becas del estudiante.
     */
    public void setBecas(List<Becas> becas) {
        this.becas = becas;
    }

    /**
     * Método que devuelve la lista de convalidaciones del estudiante.
     * @return Lista de convalidaciones del estudiante.
     */
    public List<Convalidaciones> getConvalidaciones() {
        return convalidaciones;
    }

    /**
     * Método que establece la lista de convalidaciones del estudiante.
     * @param convalidaciones Lista de convalidaciones del estudiante.
     */
    public void setConvalidaciones(List<Convalidaciones> convalidaciones) {
        this.convalidaciones = convalidaciones;
    }

    /**
     * Método que devuelve la lista de eventos del estudiante.
     * @return Lista de eventos del estudiante.
     */
    public List<EstudiantesEventos> getEstudiantesEventos() {
        return estudiantesEventos;
    }

    /**
     * Método que establece la lista de eventos del estudiante.
     * @param estudiantesEventos Lista de eventos del estudiante.
     */
    public void setEstudiantesEventos(List<EstudiantesEventos> estudiantesEventos) {
        this.estudiantesEventos = estudiantesEventos;
    }

    /**
     * Método que devuelve la lista de historiales académicos del estudiante.
     * @return Lista de historiales académicos del estudiante.
     */
    public List<HistorialAcademico> getHistorialAcademico() {
        return historialAcademico;
    }

    /**
     * Método que establece la lista de historiales académicos del estudiante.
     * @param historialAcademico Lista de historiales académicos del estudiante.
     */
    public void setHistorialAcademico(List<HistorialAcademico> historialAcademico) {
        this.historialAcademico = historialAcademico;
    }

    /**
     * Método que devuelve nombre completo del estudiante.
     * @return Nombre completo del estudiante.
     */
    @Override
    public String toString() {
        return nombre + " " + apellido;
    }
}
