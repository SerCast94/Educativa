package Vista.Admin.Modificar;

import Controlador.Controlador;
import Mapeo.Administradores;
import Mapeo.Profesores;
import Vista.Admin.VistaPrincipalAdmin;
import Vista.Util.Boton;
import Vista.Util.CustomDialog;

import javax.swing.*;
import java.awt.*;

import static BackUtil.Encriptador.encryptMD5;
import static Controlador.Controlador.getListaAdministradores;
import static Vista.Util.EstiloComponentes.*;

public class ActualizarAdministradoresAdmin extends JFrame {
    private Container panel;
    private GridBagLayout gLayout;
    private GridBagConstraints gbc;
    private JButton btnAceptar = new Boton("Actualizar", Boton.ButtonType.PRIMARY);
    private JButton btnCancelar = new Boton("Cancelar", Boton.ButtonType.DELETE);

    private JLabel lblNombre = new JLabel("Nombre:");
    private JLabel lblApellido = new JLabel("Apellido:");
    private JLabel lblDni = new JLabel("DNI:");
    private JLabel lblEmail = new JLabel("Email:");
    private JLabel lblTelefono = new JLabel("Teléfono:");
    private JLabel lblUsuario = new JLabel("Usuario:");
    private JLabel lblPassword = new JLabel("Contraseña:");
    private JLabel lblEstado = new JLabel("Estado:");

    private JTextField txtNombre = crearTextField();
    private JTextField txtApellido = crearTextField();
    private JTextField txtDni = crearTextField();
    private JTextField txtEmail = crearTextField();
    private JTextField txtTelefono = crearTextField();
    private JTextField txtUsuario = crearTextField();
    private JPasswordField txtPassword = crearPasswordField();
    private JComboBox<String> cmbEstado = new JComboBox<>(new String[]{"activo", "inactivo"});

    private Administradores administrador;

    public ActualizarAdministradoresAdmin() {
        this.administrador = VistaPrincipalAdmin.usuarioLogeado;
        initGUI();
        initEventos();
        cargarDatosAdministrador();
    }

    private void cargarDatosAdministrador() {
        txtNombre.setText(administrador.getNombre());
        txtApellido.setText(administrador.getApellido());
        txtDni.setText(administrador.getDni());
        txtEmail.setText(administrador.getEmail());
        txtTelefono.setText(administrador.getTelefono());
        txtUsuario.setText(administrador.getUsuario());
        txtPassword.setText("");  // No mostrar la contraseña actual
        cmbEstado.setSelectedItem(administrador.getEstado().name());
    }

    private void initGUI() {
        setTitle("Actualizar Administrador");
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

        JLabel titulo = new JLabel("Actualizar Administrador", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 24));
        titulo.setBorder(BorderFactory.createEmptyBorder(10, 0, 20, 0));
        titulo.setForeground(new Color(70, 70, 70));
        gbc.gridwidth = 2;
        agregarComponente(titulo, 0, 0);
        gbc.gridwidth = 1;

        customizeComboBox(cmbEstado);

        agregarComponente(lblNombre, 1, 0);
        setBordeNaranja(txtNombre);
        agregarComponente(txtNombre, 1, 1);

        agregarComponente(lblApellido, 2, 0);
        setBordeNaranja(txtApellido);
        agregarComponente(txtApellido, 2, 1);

        agregarComponente(lblDni, 3, 0);
        setBordeNaranja(txtDni);
        agregarComponente(txtDni, 3, 1);

        agregarComponente(lblEmail, 4, 0);
        setBordeNaranja(txtEmail);
        agregarComponente(txtEmail, 4, 1);

        agregarComponente(lblTelefono, 5, 0);
        setBordeNaranja(txtTelefono);
        agregarComponente(txtTelefono, 5, 1);

        agregarComponente(lblUsuario, 6, 0);
        setBordeNaranja(txtUsuario);
        agregarComponente(txtUsuario, 6, 1);

        agregarComponente(lblPassword, 7, 0);
        setBordeNaranja(txtPassword);
        agregarComponente(txtPassword, 7, 1);

        agregarComponente(lblEstado, 8, 0);
        setBordeNaranja(cmbEstado);
        agregarComponente(cmbEstado, 8, 1);

        JPanel panelBotones = new JPanel();
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

    private void agregarComponente(Component componente, int fila, int columna) {
        gbc.gridx = columna;
        gbc.gridy = fila;
        panel.add(componente, gbc);
    }

    private void initEventos() {
        btnCancelar.addActionListener(e -> dispose());

        btnAceptar.addActionListener(e -> actualizarAdministradorValido());
    }

    private void actualizarAdministradorValido() {
        String nombre = txtNombre.getText().trim();
        String apellido = txtApellido.getText().trim();
        String dni = txtDni.getText().trim();
        String telefono = txtTelefono.getText().trim();
        String correo = txtEmail.getText().trim();
        String usuario = txtUsuario.getText().trim();
        String nuevaPassword = new String(txtPassword.getPassword());
        Administradores.EstadoAdministrador estado = Administradores.EstadoAdministrador.valueOf(cmbEstado.getSelectedItem().toString());

        // Validaciones de campos obligatorios
        if (nombre.isEmpty() || apellido.isEmpty() || dni.isEmpty() || telefono.isEmpty() || correo.isEmpty() || usuario.isEmpty() || estado == null) {
            new CustomDialog(null, "Error", "Todos los campos son obligatorios.", "ONLY_OK").setVisible(true);
            return;
        }

        // Validaciones específicas
        if (!Administradores.validarDNI(dni)) {
            new CustomDialog(null, "Error", "El DNI ingresado no es válido.", "ONLY_OK").setVisible(true);
            return;
        }

        if (!Administradores.validarEmail(correo)) {
            new CustomDialog(null, "Error", "El correo electrónico no es válido.", "ONLY_OK").setVisible(true);
            return;
        }

        if (!Administradores.validarTelefono(telefono)) {
            new CustomDialog(null, "Error", "El teléfono ingresado no es válido.", "ONLY_OK").setVisible(true);
            return;
        }

        if (!nuevaPassword.isEmpty() && !Administradores.validarContrasena(nuevaPassword)) {
            new CustomDialog(null, "Error", "La contraseña debe tener al menos 6 caracteres.", "ONLY_OK").setVisible(true);
            return;
        }

        // Si todo es válido, actualizar el administrador
        administrador.setNombre(nombre);
        administrador.setApellido(apellido);
        administrador.setDni(dni);
        administrador.setTelefono(telefono);
        administrador.setEmail(correo);
        administrador.setUsuario(usuario);
        administrador.setEstado(estado);

        if (!nuevaPassword.isEmpty()) {
            administrador.setContrasena(encryptMD5(nuevaPassword));
        }

        try {
            Controlador.actualizarControladorAdministrador(administrador);
            Controlador.actualizarListaAdministradores();

            new CustomDialog(null, "Éxito", "Administrador actualizado correctamente.", "ONLY_OK").setVisible(true);
            dispose();
        } catch (Exception ex) {
            new CustomDialog(null, "Error", "Error al actualizar administrador: " + ex.getMessage(), "ONLY_OK").setVisible(true);
            Controlador.rollback();
        }
    }
}
