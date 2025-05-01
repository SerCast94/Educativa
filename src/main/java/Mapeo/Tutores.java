package Mapeo;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

    // Constructor por defecto
    public Tutores() {}

    // Constructor principal usando setters
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

    public Integer getIdTutor() {
        return idTutor;
    }

    public void setIdTutor(Integer idTutor) {
        this.idTutor = idTutor;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        if (nombre == null || nombre.trim().isEmpty())
            throw new IllegalArgumentException("El nombre no puede estar vacío.");
        this.nombre = nombre.trim();
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        if (apellido == null || apellido.trim().isEmpty())
            throw new IllegalArgumentException("El apellido no puede estar vacío.");
        this.apellido = apellido.trim();
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        if (dni == null || dni.trim().isEmpty())
            throw new IllegalArgumentException("El DNI no puede estar vacío.");
        this.dni = dni.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if (email == null || !email.matches("^[\\w-.]+@[\\w-]+\\.[a-z]{2,}$"))
            throw new IllegalArgumentException("Formato de email inválido.");
        this.email = email.trim();
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = (telefono != null) ? telefono.trim() : null;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        if (usuario == null || usuario.trim().isEmpty())
            throw new IllegalArgumentException("El usuario no puede estar vacío.");
        this.usuario = usuario.trim();
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        if (contrasena == null || contrasena.length() < 4)
            throw new IllegalArgumentException("La contraseña debe tener al menos 4 caracteres.");
        this.contrasena = contrasena;
    }

    public EstadoTutor getEstado() {
        return estado;
    }

    public void setEstado(EstadoTutor estado) {
        if (estado == null)
            throw new IllegalArgumentException("El estado no puede ser nulo.");
        this.estado = estado;
    }

    public List<Estudiantes> getEstudiantes() {
        return estudiantes;
    }

    public void setEstudiantes(List<Estudiantes> estudiantes) {
        this.estudiantes = estudiantes;
    }

    @Override
    public String toString() {
        return nombre + " " + apellido;
    }
}
