package Vista.Admin.Modificar;

import Controlador.Controlador;
import Mapeo.Tutores;
import Vista.Admin.VistaPrincipalAdmin;
import Vista.Util.Boton;
import Vista.Util.CustomDialog;
import javax.swing.*;
import java.awt.*;
import static BackUtil.Encriptador.encryptMD5;
import static Vista.Util.EstiloComponentes.*;

public class ActualizarTutoresAdmin extends JFrame {
    private Container panel;
    private GridBagLayout gLayout;
    private GridBagConstraints gbc;
    private JButton btnAceptar = new Boton("Actualizar", Boton.ButtonType.PRIMARY);
    private JButton btnCancelar = new Boton("Cancelar", Boton.ButtonType.DELETE);
    private JLabel lblDNI = new JLabel("DNI: ");
    private JLabel lblNombre = new JLabel("Nombre: ");
    private JLabel lblApellido = new JLabel("Apellido: ");
    private JLabel lblUsuario = new JLabel("Usuario: ");
    private JLabel lblPassword = new JLabel("Contraseña: ");
    private JLabel lblEmail = new JLabel("Email: ");
    private JLabel lblTelefono = new JLabel("Teléfono: ");
    private JLabel lblEstado = new JLabel("Estado: ");
    private JTextField txtDNI = crearTextField();
    private JTextField txtNombre = crearTextField();
    private JTextField txtApellido = crearTextField();
    private JTextField txtUsuario = crearTextField();
    private JPasswordField txtPassword = crearPasswordField();
    private JTextField txtEmail = crearTextField();
    private JTextField txtTelefono = crearTextField();
    private JComboBox<Tutores.EstadoTutor> cmbEstado = new JComboBox<>(Tutores.EstadoTutor.values());
    private Tutores tutor;

    public ActualizarTutoresAdmin(Tutores tutor) {
        this.tutor = tutor;
        initGUI();
        initEventos();
        cargarDatosTutor();
    }

    private void cargarDatosTutor() {
        txtDNI.setText(tutor.getDni());
        txtNombre.setText(tutor.getNombre());
        txtApellido.setText(tutor.getApellido());
        txtUsuario.setText(tutor.getUsuario());
        txtPassword.setText("");
        txtEmail.setText(tutor.getEmail());
        txtTelefono.setText(tutor.getTelefono());
        cmbEstado.setSelectedItem(tutor.getEstado());
    }

    private void initGUI() {
        setTitle("Actualizar Tutor");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(600, 550);
        setLocationRelativeTo(null);

        panel = this.getContentPane();
        panel.setBackground(new Color(251, 234, 230));
        gLayout = new GridBagLayout();
        gbc = new GridBagConstraints();
        panel.setLayout(gLayout);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);

        JLabel titulo = new JLabel("Actualizar Tutor", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 24));
        titulo.setBorder(BorderFactory.createEmptyBorder(10, 0, 20, 0));
        titulo.setForeground(new Color(70, 70, 70));
        gbc.gridwidth = 2;
        agregarComponente(titulo, 0, 0);
        gbc.gridwidth = 1;

        customizeComboBox(cmbEstado);

        agregarCampo(lblDNI, txtDNI, 1);
        agregarCampo(lblNombre, txtNombre, 2);
        agregarCampo(lblApellido, txtApellido, 3);
        agregarCampo(lblUsuario, txtUsuario, 4);
        agregarCampo(lblPassword, txtPassword, 5);
        agregarCampo(lblEmail, txtEmail, 6);
        agregarCampo(lblTelefono, txtTelefono, 7);
        agregarCampo(lblEstado, cmbEstado, 8);

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

    private void agregarCampo(JLabel label, Component campo, int fila) {
        agregarComponente(label, fila, 0);
        setBordeNaranja((JComponent) campo);
        agregarComponente(campo, fila, 1);
    }

    private void agregarComponente(Component componente, int fila, int columna) {
        gbc.gridx = columna;
        gbc.gridy = fila;
        panel.add(componente, gbc);
    }

    private void initEventos() {
        btnCancelar.addActionListener(e -> dispose());

        btnAceptar.addActionListener(e -> actualizarTutorValido());
    }


    private void actualizarTutorValido() {
        String nombre = txtNombre.getText().trim();
        String apellido = txtApellido.getText().trim();
        String dni = txtDNI.getText().trim();
        String telefono = txtTelefono.getText().trim();
        String correo = txtEmail.getText().trim();
        String usuario = txtUsuario.getText().trim();
        String nuevaPassword = new String(txtPassword.getPassword());
        Tutores.EstadoTutor estado = Tutores.EstadoTutor.valueOf(cmbEstado.getSelectedItem().toString());

        // Validaciones de campos obligatorios
        if (nombre.isEmpty() || apellido.isEmpty() || dni.isEmpty() || telefono.isEmpty() || correo.isEmpty() || usuario.isEmpty() || estado == null) {
            new CustomDialog(null, "Error", "Todos los campos son obligatorios.", "ONLY_OK").setVisible(true);
            return;
        }

        // Validaciones específicas
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

        // Si todo es válido, actualizar el tutor
        tutor.setNombre(nombre);
        tutor.setApellido(apellido);
        tutor.setDni(dni);
        tutor.setTelefono(telefono);
        tutor.setEmail(correo);
        tutor.setUsuario(usuario);
        tutor.setEstado(estado);

        if (!nuevaPassword.isEmpty()) {
            tutor.setContrasena(encryptMD5(nuevaPassword));
        }

        try {
            Controlador.actualizarControladorTutor(tutor);
            Controlador.actualizarListaTutores();

            VistaPrincipalAdmin vistaPrincipal = (VistaPrincipalAdmin) VistaPrincipalAdmin.getVistaPrincipal();
            vistaPrincipal.mostrarVistaTutores();

            new CustomDialog(null, "Éxito", "Tutor actualizado correctamente.", "ONLY_OK").setVisible(true);
            dispose();
        } catch (Exception ex) {
            new CustomDialog(null, "Error", "Error al actualizar tutor: " + ex.getMessage(), "ONLY_OK").setVisible(true);
            Controlador.rollback();
        }
    }
}