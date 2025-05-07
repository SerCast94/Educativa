package Vista.Admin.Anadir;

import Controlador.Controlador;
import Mapeo.Profesores;
import Vista.Admin.VistaPrincipalAdmin;
import Vista.Util.Boton;
import Vista.Util.CustomDialog;
import javax.swing.*;
import java.awt.*;
import static BackUtil.Encriptador.encryptMD5;
import static Vista.Util.EstiloComponentes.*;

/**
 * Clase que representa el formulario para agregar nuevos profesores.
 * Permite al administrador ingresar los datos de un profesor.
 */
public class FormularioProfesoresAdmin extends JFrame {
    private Container panel;
    private GridBagLayout gLayout;
    private GridBagConstraints gbc;
    private JLabel titulo;
    private JPanel panelBotones;
    private JButton btnAceptar = new Boton("Aceptar", Boton.tipoBoton.PRIMARY);
    private JButton btnCancelar = new Boton("Cancelar", Boton.tipoBoton.DELETE);
    private JLabel lblDNI = new JLabel("DNI: ");
    private JLabel lblNombre = new JLabel("Nombre: ");
    private JLabel lblApellido = new JLabel("Apellido: ");
    private JLabel lblEmail = new JLabel("Email: ");
    private JLabel lblTelefono = new JLabel("Teléfono: ");
    private JLabel lblDireccion = new JLabel("Dirección: ");
    private JLabel lblUsuario = new JLabel("Usuario: ");
    private JLabel lblContrasena = new JLabel("Contraseña: ");
    private JLabel lblEstado = new JLabel("Estado: ");
    private JTextField txtDNI = crearTextField();
    private JTextField txtNombre = crearTextField();
    private JTextField txtApellido = crearTextField();
    private JTextField txtEmail = crearTextField();
    private JTextField txtTelefono = crearTextField();
    private JTextField txtDireccion = crearTextField();
    private JTextField txtUsuario = crearTextField();
    private JPasswordField txtContrasena = crearPasswordField();
    private JComboBox<Profesores.EstadoProfesor> cmbEstado = new JComboBox<>(Profesores.EstadoProfesor.values());

    /**
     * Constructor de la clase FormularioProfesoresAdmin.
     * Inicializa la interfaz gráfica y los eventos.
     */
    public FormularioProfesoresAdmin() {
        initGUI();
        initEventos();
    }

    /**
     * Método para inicializar los componentes gráficos principales.
     */
    private void initGUI() {
        setTitle("Agregar Profesor");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(600, 600);
        setLocationRelativeTo(null);

        panel = this.getContentPane();
        panel.setBackground(new Color(251, 234, 230));
        gLayout = new GridBagLayout();
        gbc = new GridBagConstraints();
        panel.setLayout(gLayout);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);

        titulo = new JLabel("Agregar Profesor", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 24));
        titulo.setBorder(BorderFactory.createEmptyBorder(10, 0, 20, 0));
        titulo.setForeground(new Color(70, 70, 70));
        gbc.gridwidth = 2;
        agregarComponente(titulo, 0, 0);
        gbc.gridwidth = 1;

        personalizarComboBox(cmbEstado);

        agregarComponente(lblDNI, 1, 0);
        setBordeNaranja(txtDNI);
        agregarComponente(txtDNI, 1, 1);

        agregarComponente(lblNombre, 2, 0);
        setBordeNaranja(txtNombre);
        agregarComponente(txtNombre, 2, 1);

        agregarComponente(lblApellido, 3, 0);
        setBordeNaranja(txtApellido);
        agregarComponente(txtApellido, 3, 1);

        agregarComponente(lblEmail, 4, 0);
        setBordeNaranja(txtEmail);
        agregarComponente(txtEmail, 4, 1);

        agregarComponente(lblTelefono, 5, 0);
        setBordeNaranja(txtTelefono);
        agregarComponente(txtTelefono, 5, 1);

        agregarComponente(lblDireccion, 6, 0);
        setBordeNaranja(txtDireccion);
        agregarComponente(txtDireccion, 6, 1);

        agregarComponente(lblUsuario, 7, 0);
        setBordeNaranja(txtUsuario);
        agregarComponente(txtUsuario, 7, 1);

        agregarComponente(lblContrasena, 8, 0);
        setBordeNaranja(txtContrasena);
        agregarComponente(txtContrasena, 8, 1);

        agregarComponente(lblEstado, 9, 0);
        setBordeNaranja(cmbEstado);
        agregarComponente(cmbEstado, 9, 1);

        panelBotones = new JPanel();
        panelBotones.setBackground(new Color(251, 234, 230));
        panelBotones.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        btnAceptar.setPreferredSize(new Dimension(100, 40));
        btnCancelar.setPreferredSize(new Dimension(100, 40));
        panelBotones.add(btnAceptar);
        panelBotones.add(btnCancelar);

        gbc.gridx = 0;
        gbc.gridy = 10;
        gbc.gridwidth = 2;
        panel.add(panelBotones, gbc);

        setVisible(true);
    }

    /**
     * Método para agregar un componente al panel principal con las restricciones de diseño.
     * @param componente El componente a agregar.
     * @param fila La fila donde se agregará.
     * @param columna La columna donde se agregará.
     */
    private void agregarComponente(Component componente, int fila, int columna) {
        gbc.gridx = columna;
        gbc.gridy = fila;
        panel.add(componente, gbc);
    }

    /**
     * Método para inicializar los eventos de los botones.
     */
    private void initEventos() {
        btnCancelar.addActionListener(e -> dispose());

        btnAceptar.addActionListener(e -> insertarProfesorValido());

    }

    /**
     * Método que valida los campos e inserta un nuevo profesor.
     */
    private void insertarProfesorValido() {
        String nombre = txtNombre.getText().trim();
        String apellido = txtApellido.getText().trim();
        String dni = txtDNI.getText().trim();
        String telefono = txtTelefono.getText().trim();
        String correo = txtEmail.getText().trim();
        String direccion = txtDireccion.getText().trim();
        String usuario = txtUsuario.getText().trim();
        String password = new String(txtContrasena.getPassword());
        Profesores.EstadoProfesor estado = Profesores.EstadoProfesor.valueOf(cmbEstado.getSelectedItem().toString());

        if (nombre.isEmpty() || apellido.isEmpty() || dni.isEmpty() || telefono.isEmpty() || correo.isEmpty() || direccion.isEmpty() || usuario.isEmpty() || password.isEmpty() || estado == null) {
            new CustomDialog(null, "Error", "Todos los campos son obligatorios.", "ONLY_OK").setVisible(true);
            return;
        }

        if (!Profesores.validarDNI(dni)) {
            new CustomDialog(null, "Error", "El DNI ingresado no es válido.", "ONLY_OK").setVisible(true);
            return;
        }

        if (!Profesores.validarEmail(correo)) {
            new CustomDialog(null, "Error", "El correo electrónico no es válido.", "ONLY_OK").setVisible(true);
            return;
        }

        if (!Profesores.validarTelefono(telefono)) {
            new CustomDialog(null, "Error", "El teléfono ingresado no es válido.", "ONLY_OK").setVisible(true);
            return;
        }

        Profesores nuevoProfesor = new Profesores(nombre, apellido, dni, correo, telefono, direccion, usuario, encryptMD5(password), estado);

        try {
            Controlador.insertarControladorProfesor(nuevoProfesor);
            Controlador.actualizarListaProfesores();

            VistaPrincipalAdmin vistaPrincipal = (VistaPrincipalAdmin) VistaPrincipalAdmin.getVistaPrincipal();
            vistaPrincipal.mostrarVistaProfesores();

            new CustomDialog(null, "Éxito", "Profesor agregado correctamente.", "ONLY_OK").setVisible(true);
            dispose();
        } catch (Exception ex) {
            new CustomDialog(null, "Error", "Error al agregar profesor: " + ex.getMessage(), "ONLY_OK").setVisible(true);
        }
    }
}