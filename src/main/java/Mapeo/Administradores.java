package Mapeo;
import jakarta.persistence.*;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

/**
 * Clase que representa a un administrador en el sistema.
 * Contiene información personal y métodos para validar datos.
 */
@Entity
@Table(name = "administradores")
public class Administradores {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_administrador")
    private Integer idAdministrador;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Column(name = "apellido", nullable = false)
    private String apellido;

    @Column(name = "dni", nullable = false, unique = true)
    private String dni;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "telefono")
    private String telefono;

    @Column(name = "usuario", nullable = false, unique = true)
    private String usuario;

    @Column(name = "contrasena", nullable = false)
    private String contrasena;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado", nullable = false)
    private EstadoAdministrador estado;

    public enum EstadoAdministrador {
        activo, inactivo
    }

    // CONSTRUCTORES
    /**
     * Constructor por defecto.
     */
    public Administradores() {
    }

    /**
     * Constructor con todos los parámetros.
     * @param nombre Nombre del administrador.
     * @param apellido Apellido del administrador.
     * @param dni DNI del administrador.
     * @param email Email del administrador.
     * @param telefono Teléfono del administrador.
     * @param usuario Usuario del administrador.
     * @param contrasena Contraseña del administrador.
     * @param estado Estado del administrador.
     */
    public Administradores(String nombre, String apellido, String dni, String email, String telefono, String usuario, String contrasena, EstadoAdministrador estado) {
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
     * Constructor con todos los parámetros excepto la contraseña.
     * @param nombre Nombre del administrador.
     * @param apellido Apellido del administrador.
     * @param dni DNI del administrador.
     * @param email Email del administrador.
     * @param telefono Teléfono del administrador.
     * @param usuario Usuario del administrador.
     * @param estado Estado del administrador.
     */
    public Administradores(String nombre, String apellido, String dni, String email, String telefono, String usuario, EstadoAdministrador estado) {
        setNombre(nombre);
        setApellido(apellido);
        setDni(dni);
        setEmail(email);
        setTelefono(telefono);
        setUsuario(usuario);
        setEstado(estado);
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
     * Método que obtiene el id del administrador.
     * @return idAdministrador El id del administrador.
     */
    public Integer getIdAdministrador() {
        return idAdministrador;
    }

    /**
     * Método que establece el id del administrador.
     * @param idAdministrador El id del administrador.
     */
    public void setIdAdministrador(Integer idAdministrador) {
        this.idAdministrador = idAdministrador;
    }

    /**
     * Método que obtiene el nombre del administrador.
     * @return nombre El nombre del administrador.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Método que establece el nombre del administrador con validaciones.
     * @param nombre El nombre del administrador.
     */
    public void setNombre(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre no puede estar vacío.");
        }
        if (nombre.length() > 100) {
            throw new IllegalArgumentException("El nombre no puede exceder los 100 caracteres.");
        }
        this.nombre = nombre;
    }

    /**
     * Método que obtiene el apellido del administrador.
     * @return apellido El apellido del administrador.
     */
    public String getApellido() {
        return apellido;
    }

    /**
     * Método que establece el apellido del administrador con validaciones.
     * @param apellido El apellido del administrador.
     */
    public void setApellido(String apellido) {
        if (apellido == null || apellido.trim().isEmpty()) {
            throw new IllegalArgumentException("El apellido no puede estar vacío.");
        }
        if (apellido.length() > 100) {
            throw new IllegalArgumentException("El apellido no puede exceder los 100 caracteres.");
        }
        this.apellido = apellido;
    }

    /**
     * Método que obtiene el DNI del administrador.
     * @return dni El DNI del administrador.
     */
    public String getDni() {
        return dni;
    }

    /**
     * Método que establece el DNI del administrador con validaciones.
     * @param dni El DNI del administrador.
     */
    public void setDni(String dni) {
        if (!validarDNI(dni)) {
            throw new IllegalArgumentException("DNI no válido. Debe tener el formato correcto.");
        }
        this.dni = dni;
    }

    /**
     * Método que obtiene el email del administrador.
     * @return email El email del administrador.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Método que establece el email del administrador con validaciones.
     * @param email El email del administrador.
     */
    public void setEmail(String email) {
        if (!validarEmail(email)) {
            throw new IllegalArgumentException("El email no tiene un formato válido.");
        }
        this.email = email;
    }

    /**
     * Método que obtiene el teléfono del administrador.
     * @return telefono El teléfono del administrador.
     */
    public String getTelefono() {
        return telefono;
    }

    /**
     * Método que establece el teléfono del administrador con validaciones.
     * @param telefono El teléfono del administrador.
     */
    public void setTelefono(String telefono) {
        if (!validarTelefono(telefono)) {
            throw new IllegalArgumentException("El teléfono no puede exceder los 20 caracteres.");
        }
        this.telefono = telefono;
    }

    /**
     * Método que obtiene el usuario del administrador.
     * @return usuario El usuario del administrador.
     */
    public String getUsuario() {
        return usuario;
    }

    /**
     * Método que establece el usuario del administrador con validaciones.
     * @param usuario El usuario del administrador.
     */
    public void setUsuario(String usuario) {
        if (usuario == null || usuario.trim().isEmpty()) {
            throw new IllegalArgumentException("El usuario no puede estar vacío.");
        }
        if (usuario.length() > 100) {
            throw new IllegalArgumentException("El usuario no puede exceder los 100 caracteres.");
        }
        this.usuario = usuario;
    }

    /**
     * Método que obtiene la contraseña del administrador.
     * @return contrasena La contraseña del administrador.
     */
    public String getContrasena() {
        return contrasena;
    }

    /**
     * Método que establece la contraseña del administrador con validaciones.
     * @param contrasena La contraseña del administrador.
     */
    public void setContrasena(String contrasena) {
        if (!validarContrasena(contrasena)) {
            throw new IllegalArgumentException("La contraseña debe tener al menos 6 caracteres.");
        }
        this.contrasena = contrasena;
    }

    /**
     * Método que obtiene el estado del administrador.
     * @return estado El estado del administrador.
     */
    public EstadoAdministrador getEstado() {
        return estado;
    }

    /**
     * Método que establece el estado del administrador con validaciones.
     * @param estado El estado del administrador.
     */
    public void setEstado(EstadoAdministrador estado) {
        if (estado == null) {
            throw new IllegalArgumentException("El estado no puede ser nulo.");
        }
        this.estado = estado;
    }

    /**
     * Método que obtiene el estado del administrador como un String.
     * @return estado El estado del administrador como un String.
     */
    @Override
    public String toString() {
        return nombre + " " + apellido;
    }
}
