package Vista.Admin.Modificar;

import Controlador.Controlador;
import Mapeo.Estudiantes;
import Mapeo.Tutores;
import Vista.Admin.VistaPrincipalAdmin;
import Vista.Util.CustomDatePicker;
import Vista.Util.Boton;
import Vista.Util.CustomDialog;
import javax.swing.*;
import java.awt.*;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import static BackUtil.Encriptador.encryptMD5;
import static Controlador.Controlador.actualizarControladorEstudiante;
import static Vista.Util.EstiloComponentes.*;

public class ActualizarEstudiantesAdmin extends JFrame {
    private Container panel;
    private GridBagLayout gLayout;
    private GridBagConstraints gbc;
    private JButton btnAceptar = new Boton("Aceptar", Boton.ButtonType.PRIMARY);
    private JButton btnCancelar = new Boton("Cancelar", Boton.ButtonType.DELETE);
    private JLabel lblDNI = new JLabel("DNI: ");
    private JLabel lblNombre = new JLabel("Nombre: ");
    private JLabel lblApellido = new JLabel("Apellido: ");
    private JLabel lblPassword = new JLabel("Contraseña: ");
    private JLabel lblFechaNacimiento = new JLabel("Fecha de Nacimiento: ");
    private JLabel lblEmail = new JLabel("Email: ");
    private JLabel lblTelefono = new JLabel("Teléfono: ");
    private JLabel lblDireccion = new JLabel("Dirección: ");
    private JLabel lblEstado = new JLabel("Estado: ");
    private JLabel lblFechaMatricula = new JLabel("Fecha de Matrícula: ");
    private JLabel lblUsuario = new JLabel("Usuario: ");
    private JLabel lblTutor = new JLabel("Tutor: ");
    private JTextField txtDNI = crearTextField();
    private JTextField txtNombre = crearTextField();
    private JTextField txtApellido = crearTextField();
    private JPasswordField txtPassword = crearPasswordField();
    private JTextField txtEmail = crearTextField();
    private JTextField txtTelefono = crearTextField();
    private JTextField txtDireccion = crearTextField();
    private JTextField txtUsuario = crearTextField();
    private JComboBox<String> cmbEstado = new JComboBox<>(new String[]{"activo", "inactivo"});
    private JComboBox<Tutores> cmbTutor = new JComboBox<>();
    private CustomDatePicker datePickerNacimiento = new CustomDatePicker();
    private CustomDatePicker datePickerMatricula = new CustomDatePicker();
    private Estudiantes estudiante;

    public ActualizarEstudiantesAdmin(Estudiantes estudiante) {
        this.estudiante = estudiante;
        initGUI();
        initEventos();
        cargarTutores();
        cargarDatosEstudiante();
    }

    private void cargarDatosEstudiante() {
        txtDNI.setText(estudiante.getDni());
        txtNombre.setText(estudiante.getNombre());
        txtApellido.setText(estudiante.getApellido());
        txtUsuario.setText(estudiante.getUsuario());
        txtPassword.setText("");
        datePickerNacimiento.setDate(estudiante.getFechaNacimiento().toLocalDate());
        datePickerMatricula.setDate(estudiante.getFechaMatricula().toLocalDate());
        txtEmail.setText(estudiante.getEmail());
        txtTelefono.setText(estudiante.getTelefono());
        txtDireccion.setText(estudiante.getDireccion());
        cmbEstado.setSelectedItem(estudiante.getEstado().name().toLowerCase());
        cmbTutor.setSelectedItem(estudiante.getTutor());
    }

    private void initGUI() {
        setTitle("Actualizar Estudiante");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(600, 700);
        setLocationRelativeTo(null);

        panel = this.getContentPane();
        panel.setBackground(new Color(251, 234, 230));
        gLayout = new GridBagLayout();
        gbc = new GridBagConstraints();
        panel.setLayout(gLayout);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);

        JLabel titulo = new JLabel("Modificar Estudiante", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 24));
        titulo.setBorder(BorderFactory.createEmptyBorder(10, 0, 20, 0));
        titulo.setForeground(new Color(70, 70, 70));
        gbc.gridwidth = 2;
        agregarComponente(titulo, 0, 0);
        gbc.gridwidth = 1;

        customizeComboBox(cmbEstado);
        customizeComboBox(cmbTutor);

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

        agregarComponente(lblFechaNacimiento, 6, 0);
        EspaciadoEnDatePicker(datePickerNacimiento);
        agregarComponente(datePickerNacimiento, 6, 1);

        agregarComponente(lblFechaMatricula, 7, 0);
        EspaciadoEnDatePicker(datePickerMatricula);
        agregarComponente(datePickerMatricula, 7, 1);

        agregarComponente(lblEmail, 8, 0);
        setBordeNaranja(txtEmail);
        agregarComponente(txtEmail, 8, 1);

        agregarComponente(lblTutor, 10, 0);
        setBordeNaranja(cmbTutor);
        agregarComponente(cmbTutor, 10, 1);

        agregarComponente(lblTelefono, 11, 0);
        setBordeNaranja(txtTelefono);
        agregarComponente(txtTelefono, 11, 1);

        agregarComponente(lblDireccion, 12, 0);
        setBordeNaranja(txtDireccion);
        agregarComponente(txtDireccion, 12, 1);

        agregarComponente(lblEstado, 13, 0);
        setBordeNaranja(cmbEstado);
        agregarComponente(cmbEstado, 13, 1);

        JPanel panelBotones = new JPanel();
        panelBotones.setBackground(new Color(251, 234, 230));
        panelBotones.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        btnAceptar.setPreferredSize(new Dimension(100, 40));
        btnCancelar.setPreferredSize(new Dimension(100, 40));
        panelBotones.add(btnAceptar);
        panelBotones.add(btnCancelar);

        gbc.gridx = 0;
        gbc.gridy = 14;
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

        btnAceptar.setText("Actualizar");

        btnAceptar.addActionListener(e -> actualizarEstudianteValido());
    }

    private void cargarTutores() {
        List<Tutores> tutores = Controlador.getListaTutores();
        cmbTutor.removeAllItems();
        for (Tutores tutor : tutores) {
            cmbTutor.addItem(tutor);
        }
    }

    private void actualizarEstudianteValido() {
        String nombre = txtNombre.getText().trim();
        String apellido = txtApellido.getText().trim();
        String dni = txtDNI.getText().trim();
        String usuario = txtUsuario.getText().trim();
        String correo = txtEmail.getText().trim();
        String telefono = txtTelefono.getText().trim();
        String contrasena = new String(txtPassword.getPassword()).trim();
        LocalDate fechaNacimiento = datePickerNacimiento.getDate();
        LocalDate fechaMatricula = datePickerMatricula.getDate();
        Estudiantes.EstadoEstudiante estado = Estudiantes.EstadoEstudiante.valueOf(cmbEstado.getSelectedItem().toString());

        if (nombre.isEmpty() || apellido.isEmpty() || dni.isEmpty() || usuario.isEmpty() ||
                correo.isEmpty() || telefono.isEmpty() || fechaNacimiento == null || fechaMatricula == null) {
            new CustomDialog(null, "Error", "Todos los campos deben estar completos.", "ONLY_OK").setVisible(true);
            return;
        }

        if (!Estudiantes.validarDNI(dni)) {
            new CustomDialog(null, "Error", "El DNI ingresado no es válido.", "ONLY_OK").setVisible(true);
            return;
        }

        if (!Estudiantes.validarEmail(correo)) {
            new CustomDialog(null, "Error", "El correo electrónico no es válido.", "ONLY_OK").setVisible(true);
            return;
        }

        if (!Estudiantes.validarTelefono(telefono)) {
            new CustomDialog(null, "Error", "El teléfono ingresado no es válido.", "ONLY_OK").setVisible(true);
            return;
        }

        if (!contrasena.isEmpty() && !Estudiantes.validarContrasena(contrasena)) {
            new CustomDialog(null, "Error", "La contraseña debe tener al menos 6 caracteres.", "ONLY_OK").setVisible(true);
            return;
        }

        if (fechaNacimiento.isAfter(LocalDate.now())) {
            new CustomDialog(null, "Error", "La fecha de nacimiento no puede ser en el futuro.", "ONLY_OK").setVisible(true);
            return;
        }

        if (fechaMatricula.isBefore(fechaNacimiento)) {
            new CustomDialog(null, "Error", "La fecha de matrícula no puede ser anterior a la fecha de nacimiento.", "ONLY_OK").setVisible(true);
            return;
        }

        try {
            estudiante.setNombre(nombre);
            estudiante.setApellido(apellido);
            estudiante.setDni(dni);
            estudiante.setUsuario(usuario);
            estudiante.setEmail(correo);
            estudiante.setTelefono(telefono);
            estudiante.setFechaNacimiento(Date.valueOf(fechaNacimiento));
            estudiante.setFechaMatricula(Date.valueOf(fechaMatricula));
            estudiante.setEstado(estado);

            if (!contrasena.isEmpty()) {
                estudiante.setContrasena(encryptMD5(contrasena));
            }

            actualizarControladorEstudiante(estudiante);
            Controlador.actualizarListaEstudiantes();

            VistaPrincipalAdmin vistaPrincipal = (VistaPrincipalAdmin) VistaPrincipalAdmin.getVistaPrincipal();
            vistaPrincipal.mostrarVistaEstudiantes();

            new CustomDialog(null, "Éxito", "Estudiante actualizado correctamente.", "ONLY_OK").setVisible(true);
            dispose();
        } catch (Exception ex) {
            new CustomDialog(null, "Error", "Error al actualizar estudiante: " + ex.getMessage(), "ONLY_OK").setVisible(true);
        }
    }
}