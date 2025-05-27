package Vista.Profesor.Modificar;

import Controlador.Controlador;
import Mapeo.Profesores;
import Vista.Admin.VistaPrincipalAdmin;
import Vista.Profesor.VistaPrincipalProfesor;
import Vista.Util.Boton;
import Vista.Util.CustomDialog;
import javax.swing.*;
import java.awt.*;
import static BackUtil.Encriptador.encryptMD5;
import static Vista.Util.EstiloComponentes.*;

/**
 * Clase que representa la ventana para actualizar los datos de un profesor en la interfaz de administrador.
 */
public class ActualizarProfesoresProfesor extends JFrame {
    private Container panel;
    private GridBagLayout gLayout;
    private GridBagConstraints gbc;
    private JLabel titulo;
    private JPanel panelBotones;
    private JButton btnAceptar = new Boton("Actualizar", Boton.tipoBoton.PRIMARY);
    private JButton btnCancelar = new Boton("Cancelar", Boton.tipoBoton.DELETE);
    private JLabel lblDNI = new JLabel("DNI: ");
    private JLabel lblNombre = new JLabel("Nombre: ");
    private JLabel lblApellido = new JLabel("Apellido: ");
    private JLabel lblPassword = new JLabel("Contraseña: ");
    private JLabel lblEmail = new JLabel("Email: ");
    private JLabel lblTelefono = new JLabel("Teléfono: ");
    private JLabel lblDireccion = new JLabel("Dirección: ");
    private JLabel lblUsuario = new JLabel("Usuario: ");
    private JTextField txtDNI = crearTextField();
    private JTextField txtNombre = crearTextField();
    private JTextField txtApellido = crearTextField();
    private JPasswordField txtPassword = crearPasswordField();
    private JTextField txtEmail = crearTextField();
    private JTextField txtTelefono = crearTextField();
    private JTextField txtDireccion = crearTextField();
    private JTextField txtUsuario = crearTextField();
    private Profesores profesor;

    /**
     * Constructor de la clase ActualizarProfesoresProfesor.
     * Inicializa la interfaz gráfica y carga los datos del profesor a actualizar.
     * @param profesor El profesor a actualizar.
     */
    public ActualizarProfesoresProfesor(Profesores profesor) {
        this.profesor = profesor;
        initGUI();
        initEventos();
        cargarDatosProfesor();
    }

    /**
     * Método para inicializar los componentes gráficos principales.
     */
    private void cargarDatosProfesor() {
        txtDNI.setText(profesor.getDni());
        txtNombre.setText(profesor.getNombre());
        txtApellido.setText(profesor.getApellido());
        txtUsuario.setText(profesor.getUsuario());
        txtPassword.setText("");
        txtEmail.setText(profesor.getEmail());
        txtTelefono.setText(profesor.getTelefono());
        txtDireccion.setText(profesor.getDireccion());
    }

    /**
     * Método para inicializar los componentes gráficos principales.
     */
    private void initGUI() {
        setTitle("Actualizar Profesor");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        establecerIcono(this);
        setSize(600, 600);
        setLocationRelativeTo(null);

        panel = this.getContentPane();
        panel.setBackground(new Color(251, 234, 230));
        gLayout = new GridBagLayout();
        gbc = new GridBagConstraints();
        panel.setLayout(gLayout);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);

        titulo = new JLabel("Actualizar Profesor", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 24));
        titulo.setBorder(BorderFactory.createEmptyBorder(10, 0, 20, 0));
        titulo.setForeground(new Color(70, 70, 70));
        gbc.gridwidth = 2;
        agregarComponente(titulo, 0, 0);
        gbc.gridwidth = 1;

        agregarComponente(lblDNI, 1, 0);
        setBordeNaranja(txtDNI);
        agregarComponente(txtDNI, 1, 1);

        agregarComponente(lblNombre, 2, 0);
        setBordeNaranja(txtNombre);
        agregarComponente(txtNombre, 2, 1);

        agregarComponente(lblApellido, 3, 0);
        setBordeNaranja(txtApellido);
        agregarComponente(txtApellido, 3, 1);

        agregarComponente(lblUsuario, 4, 0);
        setBordeNaranja(txtUsuario);
        agregarComponente(txtUsuario, 4, 1);

        agregarComponente(lblPassword, 5, 0);
        setBordeNaranja(txtPassword);
        agregarComponente(txtPassword, 5, 1);

        agregarComponente(lblEmail, 6, 0);
        setBordeNaranja(txtEmail);
        agregarComponente(txtEmail, 6, 1);

        agregarComponente(lblTelefono, 7, 0);
        setBordeNaranja(txtTelefono);
        agregarComponente(txtTelefono, 7, 1);

        agregarComponente(lblDireccion, 8, 0);
        setBordeNaranja(txtDireccion);
        agregarComponente(txtDireccion, 8, 1);

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

        btnAceptar.addActionListener(e -> actualizarProfesorValido());
    }

    /**
     * Método para validar y actualizar los datos del profesor.
     */
    private void actualizarProfesorValido() {
        String nombre = txtNombre.getText().trim();
        String apellido = txtApellido.getText().trim();
        String dni = txtDNI.getText().trim();
        String telefono = txtTelefono.getText().trim();
        String correo = txtEmail.getText().trim();
        String direccion = txtDireccion.getText().trim();
        String usuario = txtUsuario.getText().trim();
        String nuevaPassword = new String(txtPassword.getPassword());

        if (nombre.isEmpty() || apellido.isEmpty() || dni.isEmpty() || telefono.isEmpty() || correo.isEmpty() || direccion.isEmpty() || usuario.isEmpty()) {
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

        if (!nuevaPassword.isEmpty() && !Profesores.validarContrasena(nuevaPassword)) {
            new CustomDialog(null, "Error", "La contraseña debe tener al menos 6 caracteres.", "ONLY_OK").setVisible(true);
            return;
        }

        profesor.setNombre(nombre);
        profesor.setApellido(apellido);
        profesor.setDni(dni);
        profesor.setTelefono(telefono);
        profesor.setEmail(correo);
        profesor.setDireccion(direccion);
        profesor.setUsuario(usuario);

        if (!nuevaPassword.isEmpty()) {
            profesor.setContrasena(encryptMD5(nuevaPassword));
        }

        try {
            Controlador.actualizarControladorProfesor(profesor);
            Controlador.actualizarListaProfesores();

            dispose();

            new CustomDialog(null, "Éxito", "Profesor actualizado correctamente.", "ONLY_OK").setVisible(true);
            dispose();
        } catch (Exception ex) {
            new CustomDialog(null, "Error", "Error al actualizar profesor: " + ex.getMessage(), "ONLY_OK").setVisible(true);
            Controlador.rollback();
        }
    }
}
