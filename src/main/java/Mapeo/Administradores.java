package Mapeo;
import jakarta.persistence.*;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

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

    // Constructor por defecto
    public Administradores() {
    }

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

    public Administradores(String nombre, String apellido, String dni, String email, String telefono, String usuario, EstadoAdministrador estado) {
        setNombre(nombre);
        setApellido(apellido);
        setDni(dni);
        setEmail(email);
        setTelefono(telefono);
        setUsuario(usuario);
        setEstado(estado);
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

    public Integer getIdAdministrador() {
        return idAdministrador;
    }

    public void setIdAdministrador(Integer idAdministrador) {
        this.idAdministrador = idAdministrador;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre no puede estar vacío.");
        }
        if (nombre.length() > 100) {
            throw new IllegalArgumentException("El nombre no puede exceder los 100 caracteres.");
        }
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        if (apellido == null || apellido.trim().isEmpty()) {
            throw new IllegalArgumentException("El apellido no puede estar vacío.");
        }
        if (apellido.length() > 100) {
            throw new IllegalArgumentException("El apellido no puede exceder los 100 caracteres.");
        }
        this.apellido = apellido;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        if (!isValidDni(dni)) {
            throw new IllegalArgumentException("DNI no válido. Debe tener el formato correcto.");
        }
        if (dni.length() > 20) {
            throw new IllegalArgumentException("El DNI no puede exceder los 20 caracteres.");
        }
        this.dni = dni;
    }

    private boolean isValidDni(String dni) {
        return dni != null && dni.matches("\\d{8}[A-Za-z]");
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if (email == null || !isValidEmail(email)) {
            throw new IllegalArgumentException("El email no tiene un formato válido.");
        }
        if (email.length() > 100) {
            throw new IllegalArgumentException("El email no puede exceder los 100 caracteres.");
        }
        this.email = email;
    }

    private boolean isValidEmail(String email) {
        String regex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        if (telefono != null && telefono.length() > 20) {
            throw new IllegalArgumentException("El teléfono no puede exceder los 20 caracteres.");
        }
        this.telefono = telefono;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        if (usuario == null || usuario.trim().isEmpty()) {
            throw new IllegalArgumentException("El usuario no puede estar vacío.");
        }
        if (usuario.length() > 100) {
            throw new IllegalArgumentException("El usuario no puede exceder los 100 caracteres.");
        }
        this.usuario = usuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        if (contrasena == null || contrasena.length() < 6) {
            throw new IllegalArgumentException("La contraseña debe tener al menos 6 caracteres.");
        }
        this.contrasena = contrasena;
    }

    public EstadoAdministrador getEstado() {
        return estado;
    }

    public void setEstado(EstadoAdministrador estado) {
        if (estado == null) {
            throw new IllegalArgumentException("El estado no puede ser nulo.");
        }
        this.estado = estado;
    }

    @Override
    public String toString() {
        return nombre + " " + apellido;
    }
}
