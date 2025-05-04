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
@Table(name = "tutores")
public class Tutores {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tutor")
    private Integer idTutor;

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

    @Column(name = "usuario", nullable = false, unique = true)
    private String usuario;

    @Column(name = "contrasena", nullable = false)
    private String contrasena;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado", nullable = false)
    private EstadoTutor estado;

    @OneToMany(mappedBy = "tutor", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Estudiantes> estudiantes = new ArrayList<>();

    public enum EstadoTutor {
        activo, inactivo
    }

    // CONSTRUCTORES

    /**
     * Constructor por defecto de la clase Tutores.
     */
    public Tutores() {}

    /**
     * Constructor de la clase Tutores con todos los atributos del tutor.
     * @param nombre      Nombre del tutor.
     * @param apellido    Apellido del tutor.
     * @param dni         DNI del tutor.
     * @param email       Correo electrónico del tutor.
     * @param telefono    Teléfono del tutor.
     * @param usuario     Usuario del tutor.
     * @param contrasena  Contraseña del tutor.
     * @param estado      Estado del tutor (activo o inactivo).
     */
    public Tutores(String nombre, String apellido, String dni, String email, String telefono, String usuario, String contrasena, EstadoTutor estado) {
        setNombre(nombre);
        setApellido(apellido);
        setDni(dni);
        setEmail(email);
        setTelefono(telefono);
        setUsuario(usuario);
        setContrasena(contrasena);
        setEstado(estado);
    }

    /**
     * Contructor de la clase Tutores con todos los atributos del tutor excepto contraseña
     * @param nombre      Nombre del tutor.
     * @param apellido    Apellido del tutor.
     * @param dni         DNI del tutor.
     * @param email       Correo electrónico del tutor.
     * @param telefono    Teléfono del tutor.
     * @param usuario     Usuario del tutor.
     * @param estado      Estado del tutor (activo o inactivo).
     */
    public Tutores(String nombre, String apellido, String dni, String email, String telefono, String usuario, EstadoTutor estado) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.email = email;
        this.telefono = telefono;
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
     * Método que devuelve el ID del tutor.
     * @return ID del tutor.
     */
    public Integer getIdTutor() {
        return idTutor;
    }

    /**
     * Método que establece el ID del tutor.
     * @param idTutor ID del tutor.
     */
    public void setIdTutor(Integer idTutor) {
        this.idTutor = idTutor;
    }

    /**
     * Método que devuelve el nombre del tutor.
     * @return Nombre del tutor.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Método que establece el nombre del tutor con validaciones.
     * @param nombre Nombre del tutor.
     */
    public void setNombre(String nombre) {
        if (nombre == null || nombre.trim().isEmpty())
            throw new IllegalArgumentException("El nombre no puede estar vacío.");
        this.nombre = nombre.trim();
    }

    /**
     * Método que devuelve el apellido del tutor.
     * @return Apellido del tutor.
     */
    public String getApellido() {
        return apellido;
    }

    /**
     * Método que establece el apellido del tutor con validaciones.
     * @param apellido Apellido del tutor.
     */
    public void setApellido(String apellido) {
        if (apellido == null || apellido.trim().isEmpty())
            throw new IllegalArgumentException("El apellido no puede estar vacío.");
        this.apellido = apellido.trim();
    }

    /**
     * Método que devuelve el DNI del tutor.
     * @return DNI del tutor.
     */
    public String getDni() {
        return dni;
    }

    /**
     * Método que establece el DNI del tutor con validaciones.
     * @param dni DNI del tutor.
     */
    public void setDni(String dni) {
        if (!validarDNI(dni))
            throw new IllegalArgumentException("El DNI no puede estar vacío.");
        this.dni = dni.trim();
    }

    /**
     * Método que devuelve el correo electrónico del tutor.
     * @return Correo electrónico del tutor.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Método que establece el correo electrónico del tutor con validaciones.
     * @param email Correo electrónico del tutor.
     */
    public void setEmail(String email) {
        if (!validarEmail(email))
            throw new IllegalArgumentException("Formato de email inválido.");
        this.email = email.trim();
    }

    /**
     * Método que devuelve el teléfono del tutor.
     * @return Teléfono del tutor.
     */
    public String getTelefono() {
        return telefono;
    }

    /**
     * Método que establece el teléfono del tutor con validaciones.
     * @param telefono Teléfono del tutor.
     */
    public void setTelefono(String telefono) {
        if (!validarTelefono(telefono)) {
            throw new IllegalArgumentException("El teléfono no puede exceder los 20 caracteres.");
        }
        this.telefono = telefono;
    }

    /**
     * Método que devuelve el usuario del tutor.
     * @return Usuario del tutor.
     */
    public String getUsuario() {
        return usuario;
    }

    /**
     * Método que establece el usuario del tutor con validaciones.
     * @param usuario Usuario del tutor.
     */
    public void setUsuario(String usuario) {
        if (usuario == null || usuario.trim().isEmpty())
            throw new IllegalArgumentException("El usuario no puede estar vacío.");
        this.usuario = usuario.trim();
    }

    /**
     * Método que devuelve la contraseña del tutor.
     * @return Contraseña del tutor.
     */
    public String getContrasena() {
        return contrasena;
    }

    /**
     * Método que establece la contraseña del tutor con validaciones.
     * @param contrasena Contraseña del tutor.
     */
    public void setContrasena(String contrasena) {
        if (!validarContrasena(contrasena)) {
            throw new IllegalArgumentException("La contraseña debe tener al menos 6 caracteres.");
        }
        this.contrasena = contrasena;
    }

    /**
     * Método que devuelve el estado del tutor.
     * @return Estado del tutor.
     */
    public EstadoTutor getEstado() {
        return estado;
    }

    /**
     * Método que establece el estado del tutor con validaciones.
     * @param estado Estado del tutor.
     */
    public void setEstado(EstadoTutor estado) {
        if (estado == null)
            throw new IllegalArgumentException("El estado no puede ser nulo.");
        this.estado = estado;
    }

    /**
     * Método que devuelve la lista de estudiantes asignados al tutor.
     * @return Lista de estudiantes.
     */
    public List<Estudiantes> getEstudiantes() {
        return estudiantes;
    }

    /**
     * Método que establece la lista de estudiantes asignados al tutor.
     * @param estudiantes Lista de estudiantes.
     */
    public void setEstudiantes(List<Estudiantes> estudiantes) {
        this.estudiantes = estudiantes;
    }

    /**
     * Método que devuelve el nombre completo del tutor.
     * @return Nombre completo del tutor.
     */
    @Override
    public String toString() {
        return nombre + " " + apellido;
    }
}
