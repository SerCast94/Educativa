package Vista.Admin;

import Controlador.Controlador;
import Mapeo.Cursos;
import Util.CustomDatePicker;
import Vista.Boton;
import javax.swing.*;
import java.awt.*;
import java.util.Date;
import java.util.List;
import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.components.DatePickerSettings;


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
    private JTextField txtDNI = new JTextField(30);
    private JTextField txtNombre = new JTextField(30);
    private JTextField txtApellido = new JTextField(30);
    private JPasswordField txtPassword = new JPasswordField(30);
    private JTextField txtEmail = new JTextField(30);
    private JTextField txtTelefono = new JTextField(30);
    private JTextField txtDireccion = new JTextField(30);
    private JComboBox<String> cmbEstado = new JComboBox<>(new String[]{"activo", "inactivo"});
    private JComboBox<Cursos> cmbCurso = new JComboBox<>();
    private CustomDatePicker datePicker = new CustomDatePicker();

    public FormularioEstudiantesAdmin() {
        initGUI();
        initEventos();
        cargarCursos();
    }

    private void initGUI() {
        setTitle("Agregar Estudiante");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(600, 600);
        setLocationRelativeTo(null);

        panel = this.getContentPane();
        panel.setBackground(new Color(251, 234, 230)); // Color de fondo del panel
        gLayout = new GridBagLayout();
        gbc = new GridBagConstraints();
        panel.setLayout(gLayout);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);

        // Agregar título centrado
        JLabel titulo = new JLabel("Agregar Estudiante", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 24));
        titulo.setBorder(BorderFactory.createEmptyBorder(10, 0, 20, 0));
        titulo.setForeground(new Color(70, 70, 70));
        gbc.gridwidth = 2;
        agregarComponente(titulo, 0, 0);
        gbc.gridwidth = 1;

        // Personalizar el JComboBox
        cmbEstado.setBackground(new Color(245, 156, 107));  // Fondo color similar al de los botones
        cmbEstado.setForeground(Color.WHITE); // Texto blanco
        cmbEstado.setFont(new Font("Arial", Font.PLAIN, 14));
        cmbEstado.setOpaque(true); // Hacer el fondo opaco

        cmbCurso.setBackground(new Color(245, 156, 107));  // Fondo color similar al de los botones
        cmbCurso.setForeground(Color.WHITE); // Texto blanco
        cmbCurso.setFont(new Font("Arial", Font.PLAIN, 14));
        cmbCurso.setOpaque(true); // Hacer el fondo opaco


        // Agregar los componentes con el método `agregarComponente`
        agregarComponente(lblDNI, 1, 0);
        agregarComponente(txtDNI, 1, 1);
        agregarComponente(lblNombre, 2, 0);
        agregarComponente(txtNombre, 2, 1);
        agregarComponente(lblApellido, 3, 0);
        agregarComponente(txtApellido, 3, 1);
        agregarComponente(lblPassword, 4, 0);
        agregarComponente(txtPassword, 4, 1);
        agregarComponente(lblFechaNacimiento, 5, 0);


        DatePickerSettings settings = new DatePickerSettings();
        settings.setFormatForDatesCommonEra("MM/dd/yyyy");  // Formato
        settings.setFontValidDate(new Font("Arial", Font.PLAIN, 14));
        settings.setFontInvalidDate(new Font("Arial", Font.PLAIN, 14));

        datePicker.setSettings(settings);

        JTextField dateTextField = datePicker.getComponentDateTextField();
        dateTextField.setFont(new Font("Arial", Font.PLAIN, 14));
        dateTextField.setBackground(Color.WHITE);
        dateTextField.setForeground(new Color(50, 50, 50));
        dateTextField.setBorder(BorderFactory.createLineBorder(new Color(245, 156, 107)));

        JButton calendarButton = datePicker.getComponentToggleCalendarButton();
        calendarButton.setBackground(new Color(245, 156, 107)); // Naranja pastel
        calendarButton.setForeground(Color.WHITE);
        calendarButton.setFocusPainted(false);
        calendarButton.setBorder(BorderFactory.createLineBorder(new Color(245, 156, 107)));

        datePicker.getComponentDateTextField().setBackground(Color.WHITE);
        datePicker.getComponentDateTextField().setFont(new Font("Arial", Font.PLAIN, 14));
        datePicker.getComponentDateTextField().setForeground(Color.DARK_GRAY);

        calendarButton = datePicker.getComponentToggleCalendarButton();
        calendarButton.setBackground(new Color(245, 156, 107));
        calendarButton.setForeground(Color.WHITE);
        calendarButton.setFocusPainted(false);
        calendarButton.setBorder(BorderFactory.createLineBorder(new Color(245, 156, 107)));

        agregarComponente(datePicker, 5, 1);

        agregarComponente(lblEmail, 6, 0);
        agregarComponente(txtEmail, 6, 1);
        agregarComponente(lblIDCurso, 7, 0);
        agregarComponente(cmbCurso, 7, 1);
        agregarComponente(lblTelefono, 8, 0);
        agregarComponente(txtTelefono, 8, 1);
        agregarComponente(lblDireccion, 9, 0);
        agregarComponente(txtDireccion, 9, 1);
        agregarComponente(lblEstado, 10, 0);
        agregarComponente(cmbEstado, 10, 1);
        agregarComponente(btnAceptar, 11, 0);
        agregarComponente(btnCancelar, 11, 1);

        // Cambiar color de los botones
        btnAceptar.setBackground(new Color(245, 156, 107));  // Fondo del botón Aceptar
        btnAceptar.setForeground(Color.WHITE);  // Texto blanco
        btnCancelar.setBackground(new Color(220, 90, 90));  // Fondo del botón Cancelar
        btnCancelar.setForeground(Color.WHITE);  // Texto blanco

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
                    txtPassword.getPassword().length == 0 ||
                    datePicker.getDate() == null ||  // Verificar si se ha seleccionado una fecha
                    txtEmail.getText().trim().isEmpty() ||
                    txtTelefono.getText().trim().isEmpty() ||
                    txtDireccion.getText().trim().isEmpty() || cmbCurso.getSelectedItem() == null) {
                JOptionPane.showMessageDialog(null, "Todos los campos son obligatorios.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            Cursos curso = (Cursos) cmbCurso.getSelectedItem();
            Date fechaNacimiento = java.sql.Date.valueOf(datePicker.toString());  // Convertir la fecha seleccionada a Date

            String dni = txtDNI.getText().trim();
            String nombre = txtNombre.getText().trim();
            String apellido = txtApellido.getText().trim();
            String password = new String(txtPassword.getPassword());
            String email = txtEmail.getText().trim();
            String telefono = txtTelefono.getText().trim();
            String direccion = txtDireccion.getText().trim();
            String estado = (String) cmbEstado.getSelectedItem();

            // Aquí iría el código para guardar el estudiante
        });
    }

    private void cargarCursos() {
        List<Cursos> cursos = Controlador.getListaCursos();

        // Limpiar el ComboBox antes de cargar los nuevos datos
        cmbCurso.removeAllItems();

        // Cargar los cursos en el ComboBox
        for (Cursos curso : cursos) {
            //cmbCurso.addItem(curso);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            FormularioEstudiantesAdmin formulario = new FormularioEstudiantesAdmin();
            formulario.setVisible(true);
        });
    }
}
