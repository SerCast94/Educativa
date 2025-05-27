package Vista.Estudiante.Modificar;

import Controlador.Controlador;
import Mapeo.Estudiantes;
import Mapeo.Tutores;
import Vista.Util.Boton;
import Vista.Util.CustomDatePicker;
import Vista.Util.CustomDialog;
import javax.swing.*;
import java.awt.*;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import static BackUtil.Encriptador.encryptMD5;
import static Controlador.Controlador.actualizarControladorEstudiante;
import static Vista.Util.EstiloComponentes.*;

/**
 * Clase que representa la ventana para actualizar los datos de un estudiante.
 */
public class ActualizarEstudiantesEstudiante extends JFrame {
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
    private JLabel lblPassword = new JLabel("Contraseña: ");
    private JLabel lblFechaNacimiento = new JLabel("Fecha de Nacimiento: ");
    private JLabel lblEmail = new JLabel("Email: ");
    private JLabel lblTelefono = new JLabel("Teléfono: ");
    private JLabel lblDireccion = new JLabel("Dirección: ");
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
    private JComboBox<Tutores> cmbTutor = new JComboBox<>();
    private CustomDatePicker datePickerNacimiento = new CustomDatePicker();
    private CustomDatePicker datePickerMatricula = new CustomDatePicker();
    private Estudiantes estudiante;

    /**
     * Constructor de la clase ActualizarEstudiantesEstudiante.
     * Inicializa la interfaz gráfica y carga los datos del estudiante.

     * @param estudiante Estudiante que contiene los datos del estudiante logeado.
     */
    public ActualizarEstudiantesEstudiante(Estudiantes estudiante) {
        this.estudiante = estudiante;
        initGUI();
        initEventos();
        cargarTutores();
        cargarDatosEstudiante();
    }

    /**
     * Carga los datos del estudiante en los campos de texto.
     */
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
        cmbTutor.setSelectedItem(estudiante.getTutor());
    }

    /**
     * Método para inicializar los componentes gráficos principales.
     */
    private void initGUI() {
        setTitle("Actualizar Estudiante");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        establecerIcono(this);
        setSize(600, 700);
        setLocationRelativeTo(null);

        panel = this.getContentPane();
        panel.setBackground(new Color(251, 234, 230));
        gLayout = new GridBagLayout();
        gbc = new GridBagConstraints();
        panel.setLayout(gLayout);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);

        titulo = new JLabel("Actualizar Estudiante", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 24));
        titulo.setBorder(BorderFactory.createEmptyBorder(10, 0, 20, 0));
        titulo.setForeground(new Color(70, 70, 70));
        gbc.gridwidth = 2;
        agregarComponente(titulo, 0, 0);
        gbc.gridwidth = 1;

        personalizarComboBox(cmbTutor);

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


        panelBotones = new JPanel();
        panelBotones.setBackground(new Color(251, 234, 230));
        panelBotones.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        btnAceptar.setPreferredSize(new Dimension(100, 40));
        btnAceptar.setText("Actualizar");
        btnCancelar.setPreferredSize(new Dimension(100, 40));
        panelBotones.add(btnAceptar);
        panelBotones.add(btnCancelar);

        gbc.gridx = 0;
        gbc.gridy = 14;
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

        btnAceptar.addActionListener(e -> actualizarEstudianteValido());
    }

    /**
     * Método para cargar los tutores en el combo box.
     */
    private void cargarTutores() {
        List<Tutores> tutores = Controlador.getListaTutores();
        cmbTutor.removeAllItems();
        for (Tutores tutor : tutores) {
            cmbTutor.addItem(tutor);
        }
    }

    /**
     * Método para validar y actualizar los datos del estudiante.
     */
    private void actualizarEstudianteValido() {
        String nombre = txtNombre.getText().trim();
        String apellido = txtApellido.getText().trim();
        String dni = txtDNI.getText().trim();
        String direccion = txtDireccion.getText().trim();
        Tutores tutor = (Tutores) cmbTutor.getSelectedItem();
        String usuario = txtUsuario.getText().trim();
        String correo = txtEmail.getText().trim();
        String telefono = txtTelefono.getText().trim();
        String contrasena = new String(txtPassword.getPassword()).trim();
        LocalDate fechaNacimiento = datePickerNacimiento.getDate();
        LocalDate fechaMatricula = datePickerMatricula.getDate();

        if (nombre.isEmpty() || apellido.isEmpty() || dni.isEmpty() || usuario.isEmpty() ||
            correo.isEmpty() || telefono.isEmpty() || fechaNacimiento == null || fechaMatricula == null
            || direccion.isEmpty() || tutor == null) {
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

            if (!contrasena.isEmpty()) {
                estudiante.setContrasena(encryptMD5(contrasena));
            }

            actualizarControladorEstudiante(estudiante);
            Controlador.actualizarListaEstudiantes();

            dispose();

            new CustomDialog(null, "Éxito", "Estudiante actualizado correctamente.", "ONLY_OK").setVisible(true);
            dispose();
        } catch (Exception ex) {
            new CustomDialog(null, "Error", "Error al actualizar estudiante: " + ex.getMessage(), "ONLY_OK").setVisible(true);
        }
    }
}