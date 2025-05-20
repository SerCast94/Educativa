package Vista.Admin.Anadir;

import Controlador.Controlador;
import Mapeo.Estudiantes;
import Mapeo.Tutores;
import Vista.Admin.VistaPrincipalAdmin;
import Vista.Util.CustomDatePicker;
import Vista.Util.Boton;
import Vista.Util.CustomDialog;
import org.hibernate.NonUniqueObjectException;
import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.util.List;
import static BackUtil.Encriptador.encryptMD5;
import static Controlador.Controlador.insertarControladorEstudiante;
import static Vista.Util.EstiloComponentes.*;

/**
 * Formulario para agregar estudiantes en la vista de administración.
 * Permite ingresar los datos de un nuevo estudiante.
 */
public class FormularioEstudiantesAdmin extends JFrame {
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

    /**
     * Constructor de la clase FormularioEstudiantesAdmin.
     * Inicializa la interfaz gráfica, los eventos y carga la lista de tutores.
     */
    public FormularioEstudiantesAdmin() {
        initGUI();
        initEventos();
        cargarTutores();
    }

    /**
     * Método para inicializar los componentes gráficos principales.
     */
    private void initGUI() {
        setTitle("Agregar Estudiante");
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

        titulo = new JLabel("Agregar Estudiante", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 24));
        titulo.setBorder(BorderFactory.createEmptyBorder(10, 0, 20, 0));
        titulo.setForeground(new Color(70, 70, 70));
        gbc.gridwidth = 2;
        agregarComponente(titulo, 0, 0);
        gbc.gridwidth = 1;

        personalizarComboBox(cmbEstado);
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

        agregarComponente(lblEstado, 13, 0);
        setBordeNaranja(cmbEstado);
        agregarComponente(cmbEstado, 13, 1);

        panelBotones = new JPanel();
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

    /**
     * Método para agregar un componente al panel con la posición especificada.
     * @param componente Componente a agregar.
     * @param fila       Fila en la que se agregará.
     * @param columna    Columna en la que se agregará.
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

        btnAceptar.addActionListener(e ->insertarEstudianteValido());
    }

    /**
     * Método para cargar la lista de tutores en el combo box.
     */
    private void cargarTutores() {
        List<Tutores> tutores = Controlador.getListaTutores();
        cmbTutor.removeAllItems();
        for (Tutores tutor : tutores) {
            cmbTutor.addItem(tutor);
        }
    }

    /**
     * Método que valida los campos e inserta un nuevo estudiante.
     */
    private void insertarEstudianteValido() {

        String nombre = txtNombre.getText().trim();
        String apellido = txtApellido.getText().trim();
        String dni = txtDNI.getText().trim();
        String usuario = txtUsuario.getText().trim();
        String contrasena = new String(txtPassword.getPassword()).trim();
        LocalDate fechaNacimiento = datePickerNacimiento.getDate();
        LocalDate fechaMatricula = datePickerMatricula.getDate();
        String correo = txtEmail.getText().trim();
        String telefono = txtTelefono.getText().trim();
        String direccion = txtDireccion.getText().trim();
        Tutores tutor = (Tutores) cmbTutor.getSelectedItem();
        Estudiantes.EstadoEstudiante estado = Estudiantes.EstadoEstudiante.valueOf(cmbEstado.getSelectedItem().toString().toLowerCase());

        // Validaciones de campos obligatorios
        if (nombre.isEmpty() || apellido.isEmpty() || dni.isEmpty() || usuario.isEmpty() || contrasena.isEmpty()
                || fechaNacimiento == null || fechaMatricula == null || correo.isEmpty()
                || telefono.isEmpty() || direccion.isEmpty() || tutor == null) {
            new CustomDialog(null, "Error", "Todos los campos son obligatorios.", "ONLY_OK").setVisible(true);
            return;
        }

        // Validaciones específicas
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

        if (!Estudiantes.validarContrasena(contrasena)) {
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

        // Si todo es válido, crear el estudiante
        Estudiantes nuevoEstudiante = new Estudiantes(
                nombre,
                apellido,
                dni,
                java.sql.Date.valueOf(fechaNacimiento),
                direccion,
                telefono,
                correo,
                java.sql.Date.valueOf(fechaMatricula),
                tutor,
                usuario,
                encryptMD5(contrasena),
                estado
        );

        try {
            insertarControladorEstudiante(nuevoEstudiante);
            Controlador.actualizarListaEstudiantes();

            VistaPrincipalAdmin vistaPrincipal = (VistaPrincipalAdmin) VistaPrincipalAdmin.getVistaPrincipal();
            vistaPrincipal.mostrarVistaEstudiantes();

            new CustomDialog(null, "Éxito", "Estudiante agregado correctamente.", "ONLY_OK").setVisible(true);
            dispose();
        } catch (NonUniqueObjectException ex) {
            new CustomDialog(null, "Error", "El DNI ya existe.", "ONLY_OK").setVisible(true);
            Controlador.rollback();
        } catch (Exception ex) {
            new CustomDialog(null, "Error", "Error al agregar estudiante: " + ex.getMessage(), "ONLY_OK").setVisible(true);
        }
    }

}



