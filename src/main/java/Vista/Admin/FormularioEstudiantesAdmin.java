package Vista.Admin;

import Controlador.Controlador;
import Mapeo.Cursos;
import Mapeo.Tutores;
import Vista.Util.CustomDatePicker;
import Vista.Util.Boton;

import javax.swing.*;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;
import java.awt.*;
import java.util.List;

import static Vista.Util.EstiloComponentes.*;

public class FormularioEstudiantesAdmin extends JFrame {
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
    private JLabel lblIDCurso = new JLabel("ID Curso: ");
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
    private JComboBox<Cursos> cmbCurso = new JComboBox<>();
    private JComboBox<Tutores> cmbTutor = new JComboBox<>();

    private CustomDatePicker datePickerNacimiento = new CustomDatePicker();
    private CustomDatePicker datePickerMatricula = new CustomDatePicker();

    public FormularioEstudiantesAdmin() {
        initGUI();
        initEventos();
        cargarCursos();
        cargarTutores();
    }

    private void initGUI() {
        setTitle("Agregar Estudiante");
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

        JLabel titulo = new JLabel("Agregar Estudiante", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 24));
        titulo.setBorder(BorderFactory.createEmptyBorder(10, 0, 20, 0));
        titulo.setForeground(new Color(70, 70, 70));
        gbc.gridwidth = 2;
        agregarComponente(titulo, 0, 0);
        gbc.gridwidth = 1;

        customizeComboBox(cmbEstado);
        customizeComboBox(cmbCurso);
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

        agregarComponente(lblIDCurso, 9, 0);
        setBordeNaranja(cmbCurso);
        agregarComponente(cmbCurso, 9, 1);

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

        btnAceptar.addActionListener(e -> {
            if (txtDNI.getText().trim().isEmpty() ||
                    txtNombre.getText().trim().isEmpty() ||
                    txtApellido.getText().trim().isEmpty() ||
                    txtUsuario.getText().trim().isEmpty() ||
                    txtPassword.getPassword().length == 0 ||
                    datePickerNacimiento.getDate() == null ||
                    datePickerMatricula.getDate() == null ||
                    txtEmail.getText().trim().isEmpty() ||
                    txtTelefono.getText().trim().isEmpty() ||
                    txtDireccion.getText().trim().isEmpty() ||
                    cmbCurso.getSelectedItem() == null ||
                    cmbTutor.getSelectedItem() == null) {

                JOptionPane.showMessageDialog(null, "Todos los campos son obligatorios.", "Error", JOptionPane.ERROR_MESSAGE);
            }

            // Aquí iría el código para guardar el estudiante con todos los campos
        });
    }

    private void cargarCursos() {
        List<Cursos> cursos = Controlador.getListaCursos();
        cmbCurso.removeAllItems();
        for (Cursos curso : cursos) {
            cmbCurso.addItem(curso);
        }
    }

    private void cargarTutores() {
        List<Tutores> tutores = Controlador.getListaTutores();
        cmbTutor.removeAllItems();
        for (Tutores tutor : tutores) {
            cmbTutor.addItem(tutor);
        }
    }
}
