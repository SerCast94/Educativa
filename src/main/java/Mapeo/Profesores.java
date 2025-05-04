package Mapeo;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Clase que representa un profesor en el sistema.
 * Contiene información sobre el profesor, su estado y sus asignaturas.
 */
@Entity
@Table(name = "profesores")
public class Profesores {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_profesor")
    private Integer idProfesor;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Column(name = "apellido", nullable = false)
    private String apellido;

    @Column(name = "dni", nullable = false)
    private String dni;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "telefono")
    private String telefono;

    @Column(name = "direccion")
    private String direccion;

    @Column(name = "usuario", nullable = false, unique = true)
    private String usuario;

    @Column(name = "contrasena", nullable = false)
    private String contrasena;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado", nullable = false)
    private EstadoProfesor estado;

    @OneToMany(mappedBy = "profesor", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Asignaturas> asignaturas = new ArrayList<>();

    @OneToMany(mappedBy = "profesor", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Horarios> horarios = new ArrayList<>();


    public enum EstadoProfesor {
        activo, inactivo
    }

    // CONSTRUCTORES
    /**
     * Constructor por defecto de la clase Profesores.
     */
    public Profesores() {}

    /**
     * Constructor de la clase Profesores con todos los atributos del profesor.
     * @param nombre      Nombre del profesor.
     * @param apellido    Apellido del profesor.
     * @param dni         DNI del profesor.
     * @param email       Correo electrónico del profesor.
     * @param telefono    Teléfono del profesor.
     * @param direccion   Dirección del profesor.
     * @param usuario     Usuario del profesor.
     * @param contrasena  Contraseña del profesor.
     * @param estado      Estado del profesor (activo o inactivo).
     */
    public Profesores(String nombre, String apellido, String dni, String email, String telefono, String direccion, String usuario, String contrasena, EstadoProfesor estado) {
        setNombre(nombre);
        setApellido(apellido);
        setDni(dni);
        setEmail(email);
        setTelefono(telefono);
        setDireccion(direccion);
        setUsuario(usuario);
        setContrasena(contrasena);
        setEstado(estado);
    }

    /**
     * Constructor de la clase Profesores con ID con todos los atributos excepto la contraseña.
     * @param nombre      Nombre del profesor.
     * @param apellido    Apellido del profesor.
     * @param dni         DNI del profesor.
     * @param email       Correo electrónico del profesor.
     * @param telefono    Teléfono del profesor.
     * @param direccion   Dirección del profesor.
     * @param usuario     Usuario del profesor.
     * @param estado      Estado del profesor (activo o inactivo).
     */
    public Profesores(String nombre, String apellido, String dni, String email, String telefono, String direccion, String usuario, EstadoProfesor estado) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.email = email;
        this.telefono = telefono;
        this.direccion = direccion;
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

    // GETTERS Y SETTERS CON VALIDACIONES

    /**
     * Método que devuelve el ID del profesor.
     * @return ID del profesor.
     */
    public Integer getIdProfesor() {
        return idProfesor;
    }

    /**
     * Método que establece el ID del profesor.
     * @param idProfesor ID del profesor.
     */
    public void setIdProfesor(Integer idProfesor) {
        this.idProfesor = idProfesor;
    }

    /**
     * Método que devuelve el nombre del profesor.
     * @return Nombre del profesor.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Método que establece el nombre del profesor con validaciones.
     * @param nombre Nombre del profesor.
     */
    public void setNombre(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre no puede ser nulo o vacío.");
        }
        this.nombre = nombre.trim();
    }

    /**
     * Método que devuelve el apellido del profesor.
     * @return Apellido del profesor.
     */
    public String getApellido() {
        return apellido;
    }

    /**
     * Método que establece el apellido del profesor con validaciones.
     * @param apellido Apellido del profesor.
     */
    public void setApellido(String apellido) {
        if (apellido == null || apellido.trim().isEmpty()) {
            throw new IllegalArgumentException("El apellido no puede ser nulo o vacío.");
        }
        this.apellido = apellido.trim();
    }

    /**
     * Método que devuelve el DNI del profesor.
     * @return DNI del profesor.
     */
    public String getDni() {
        return dni;
    }

    /**
     * Método que establece el DNI del profesor con validaciones.
     * @param dni DNI del profesor.
     */
    public void setDni(String dni) {
        if (!validarDNI(dni)) {
            throw new IllegalArgumentException("El DNI no puede ser nulo o vacío.");
        }
        this.dni = dni.trim();
    }

    /**
     * Método que devuelve el correo electrónico del profesor.
     * @return Correo electrónico del profesor.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Método que establece el correo electrónico del profesor con validaciones.
     * @param email Correo electrónico del profesor.
     */
    public void setEmail(String email) {
        if (!validarEmail(email)) {
            throw new IllegalArgumentException("El email no es válido.");
        }
        this.email = email.trim();
    }

    /**
     * Método que devuelve el teléfono del profesor.
     * @return Teléfono del profesor.
     */
    public String getTelefono() {
        return telefono;
    }

    /**
     * Método que establece el teléfono del profesor con validaciones.
     * @param telefono Teléfono del profesor.
     */
    public void setTelefono(String telefono) {
        if (!validarTelefono(telefono)) {
            throw new IllegalArgumentException("El teléfono no puede exceder los 20 caracteres.");
        }
        this.telefono = telefono;
    }

    /**
     * Método que devuelve la dirección del profesor.
     * @return Dirección del profesor.
     */
    public String getDireccion() {
        return direccion;
    }

    /**
     * Método que establece la dirección del profesor con validaciones.
     * @param direccion Dirección del profesor.
     */
    public void setDireccion(String direccion) {
        this.direccion = (direccion != null) ? direccion.trim() : null;
    }

    /**
     * Método que devuelve el usuario del profesor.
     * @return Usuario del profesor.
     */
    public String getUsuario() {
        return usuario;
    }

    /**
     * Método que establece el usuario del profesor con validaciones.
     * @param usuario Usuario del profesor.
     */
    public void setUsuario(String usuario) {
        if (usuario == null || usuario.trim().isEmpty()) {
            throw new IllegalArgumentException("El usuario no puede ser nulo o vacío.");
        }
        this.usuario = usuario.trim();
    }

    /**
     * Método que devuelve la contraseña del profesor.
     * @return Contraseña del profesor.
     */
    public String getContrasena() {
        return contrasena;
    }

    /**
     * Método que establece la contraseña del profesor con validaciones.
     * @param contrasena Contraseña del profesor.
     */
    public void setContrasena(String contrasena) {
        if (!validarContrasena(contrasena)) {
            throw new IllegalArgumentException("La contraseña debe tener al menos 6 caracteres.");
        }
        this.contrasena = contrasena;
    }

    /**
     * Método que devuelve el estado del profesor.
     * @return Estado del profesor.
     */
    public EstadoProfesor getEstado() {
        return estado;
    }

    /**
     * Método que establece el estado del profesor con validaciones.
     * @param estado Estado del profesor.
     */
    public void setEstado(EstadoProfesor estado) {
        if (estado == null) {
            throw new IllegalArgumentException("El estado no puede ser nulo.");
        }
        this.estado = estado;
    }

    /**
     * Método que devuelve la lista de asignaturas del profesor.
     * @return Lista de asignaturas del profesor.
     */
    public List<Asignaturas> getAsignaturas() {
        return asignaturas;
    }

    /**
     * Método que establece la lista de asignaturas del profesor.
     * @param asignaturas Lista de asignaturas del profesor.
     */
    public void setAsignaturas(List<Asignaturas> asignaturas) {
        this.asignaturas = asignaturas;
    }

    /**
     * Método que devuelve la lista de horarios del profesor.
     * @return Lista de horarios del profesor.
     */
    public List<Horarios> getHorarios() {
        return horarios;
    }

    /**
     * Método que establece la lista de horarios del profesor.
     * @param horarios Lista de horarios del profesor.
     */
    public void setHorarios(List<Horarios> horarios) {
        this.horarios = horarios;
    }

    /**
     * Método que devuelve el nombre completo del profesor.
     * @return Nombre completo del profesor.
     */
    @Override
    public String toString() {
        return nombre + " " + apellido;
    }
}
