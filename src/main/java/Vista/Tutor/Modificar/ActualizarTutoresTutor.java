package Vista.Tutor.Modificar;

import Controlador.Controlador;
import Mapeo.Tutores;
import Vista.Admin.VistaPrincipalAdmin;
import Vista.Util.Boton;
import Vista.Util.CustomDialog;
import javax.swing.*;
import java.awt.*;
import static BackUtil.Encriptador.encryptMD5;
import static Vista.Util.EstiloComponentes.*;

/**
 * Clase que representa la ventana para actualizar los datos de un tutor en la interfaz del tutor.
 */
public class ActualizarTutoresTutor extends JFrame {
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
    private JLabel lblUsuario = new JLabel("Usuario: ");
    private JLabel lblPassword = new JLabel("Contraseña: ");
    private JLabel lblEmail = new JLabel("Email: ");
    private JLabel lblTelefono = new JLabel("Teléfono: ");
    private JTextField txtDNI = crearTextField();
    private JTextField txtNombre = crearTextField();
    private JTextField txtApellido = crearTextField();
    private JTextField txtUsuario = crearTextField();
    private JPasswordField txtPassword = crearPasswordField();
    private JTextField txtEmail = crearTextField();
    private JTextField txtTelefono = crearTextField();
    private Tutores tutor;

    /**
     * Constructor de la clase ActualizarTutoresTutor.
     * @param tutor El objeto Tutor que se va a actualizar.
     */
    public ActualizarTutoresTutor(Tutores tutor) {
        this.tutor = tutor;
        initGUI();
        initEventos();
        cargarDatosTutor();
    }

    /**
     * Carga los datos del tutor en los campos correspondientes.
     */
    private void cargarDatosTutor() {
        txtDNI.setText(tutor.getDni());
        txtNombre.setText(tutor.getNombre());
        txtApellido.setText(tutor.getApellido());
        txtUsuario.setText(tutor.getUsuario());
        txtPassword.setText("");
        txtEmail.setText(tutor.getEmail());
        txtTelefono.setText(tutor.getTelefono());
    }

    /*
     * Método para inicializar los componentes gráficos principales.
     */
    private void initGUI() {
        setTitle("Actualizar Tutor");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        establecerIcono(this);
        setSize(600, 550);
        setLocationRelativeTo(null);

        panel = this.getContentPane();
        panel.setBackground(new Color(251, 234, 230));
        gLayout = new GridBagLayout();
        gbc = new GridBagConstraints();
        panel.setLayout(gLayout);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);

        titulo = new JLabel("Actualizar Tutor", SwingConstants.CENTER);
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

        panelBotones = new JPanel();
        panelBotones.setBackground(new Color(251, 234, 230));
        panelBotones.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        btnAceptar.setPreferredSize(new Dimension(100, 40));
        btnCancelar.setPreferredSize(new Dimension(100, 40));
        panelBotones.add(btnAceptar);
        panelBotones.add(btnCancelar);

        gbc.gridx = 0;
        gbc.gridy = 9;
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

        btnAceptar.addActionListener(e -> actualizarTutorValido());

    }

    /**
     * Método para validar y actualizar los datos del tutor.
     */
    private void actualizarTutorValido() {
        String nombre = txtNombre.getText().trim();
        String apellido = txtApellido.getText().trim();
        String dni = txtDNI.getText().trim();
        String telefono = txtTelefono.getText().trim();
        String correo = txtEmail.getText().trim();
        String usuario = txtUsuario.getText().trim();
        String nuevaPassword = new String(txtPassword.getPassword());

        if (nombre.isEmpty() || apellido.isEmpty() || dni.isEmpty() || telefono.isEmpty() || correo.isEmpty() || usuario.isEmpty()) {
            new CustomDialog(null, "Error", "Todos los campos son obligatorios.", "ONLY_OK").setVisible(true);
            return;
        }

        if (!Tutores.validarDNI(dni)) {
            new CustomDialog(null, "Error", "El DNI ingresado no es válido.", "ONLY_OK").setVisible(true);
            return;
        }

        if (!Tutores.validarEmail(correo)) {
            new CustomDialog(null, "Error", "El correo electrónico no es válido.", "ONLY_OK").setVisible(true);
            return;
        }

        if (!Tutores.validarTelefono(telefono)) {
            new CustomDialog(null, "Error", "El teléfono ingresado no es válido.", "ONLY_OK").setVisible(true);
            return;
        }

        tutor.setNombre(nombre);
        tutor.setApellido(apellido);
        tutor.setDni(dni);
        tutor.setTelefono(telefono);
        tutor.setEmail(correo);
        tutor.setUsuario(usuario);

        if (!nuevaPassword.isEmpty()) {
            tutor.setContrasena(encryptMD5(nuevaPassword));
        }

        try {
            Controlador.actualizarControladorTutor(tutor);
            Controlador.actualizarListaTutores();

            dispose();

            new CustomDialog(null, "Éxito", "Tutor actualizado correctamente.", "ONLY_OK").setVisible(true);
            dispose();
        } catch (Exception ex) {
            new CustomDialog(null, "Error", "Error al actualizar tutor: " + ex.getMessage(), "ONLY_OK").setVisible(true);
            Controlador.rollback();
        }
    }
}