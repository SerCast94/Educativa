package Vista.Admin;

import Controlador.Controlador;
import Mapeo.Cursos;
import Vista.Util.CustomDatePicker;


import Vista.Util.Boton;
import javax.swing.*;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;
import java.awt.*;
import java.util.Date;
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
    private JTextField txtDNI = crearTextField();
    private JTextField txtNombre = crearTextField();
    private JTextField txtApellido = crearTextField();
    private JPasswordField txtPassword = crearPasswordField();
    private JTextField txtEmail = crearTextField();
    private JTextField txtTelefono = crearTextField();
    private JTextField txtDireccion = crearTextField();
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

        JLabel titulo = new JLabel("Agregar Estudiante", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 24));
        titulo.setBorder(BorderFactory.createEmptyBorder(10, 0, 20, 0));
        titulo.setForeground(new Color(70, 70, 70));
        gbc.gridwidth = 2;
        agregarComponente(titulo, 0, 0);
        gbc.gridwidth = 1;

        customizeComboBox(cmbEstado);
        customizeComboBox(cmbCurso);

        // Agregar los componentes con el método `agregarComponente`
        agregarComponente(lblDNI, 1, 0);
        setBordeNaranja(txtDNI);
        agregarComponente(txtDNI, 1, 1);
        agregarComponente(lblNombre, 2, 0);
        setBordeNaranja(txtNombre);
        agregarComponente(txtNombre, 2, 1);
        agregarComponente(lblApellido, 3, 0);
        setBordeNaranja(txtApellido);
        agregarComponente(txtApellido, 3, 1);
        agregarComponente(lblPassword, 4, 0);
        setBordeNaranja(txtPassword);
        agregarComponente(txtPassword, 4, 1);
        agregarComponente(lblFechaNacimiento, 5, 0);

        // Obtener el JTextField del DatePicker (campo donde se muestra la fecha seleccionada)
        JTextField dateTextField = datePicker.getComponentDateTextField();
        dateTextField.setFont(new Font("Arial", Font.PLAIN, 14));
        dateTextField.setEditable(false); // Hacer que el campo no sea editable
        dateTextField.setBackground(Color.WHITE);
        dateTextField.setForeground(new Color(50, 50, 50));
        dateTextField.setBorder(BorderFactory.createLineBorder(new Color(245, 156, 107)));  // Borde naranja

        // Crear un PlainDocument para espacio antes de la fecha
        PlainDocument doc = new PlainDocument() {
            @Override
            public void insertString(int offset, String str, AttributeSet a) throws BadLocationException {
                super.insertString(offset, "  " + str, a);
            }
        };
        // Asignar el PlainDocument al JTextField

        dateTextField.setDocument(doc);

        agregarComponente(datePicker, 5, 1);
        agregarComponente(lblEmail, 6, 0);
        setBordeNaranja(txtEmail);
        agregarComponente(txtEmail, 6, 1);
        agregarComponente(lblIDCurso, 7, 0);
        setBordeNaranja(cmbCurso);
        agregarComponente(cmbCurso, 7, 1);
        agregarComponente(lblTelefono, 8, 0);
        setBordeNaranja(txtTelefono);
        agregarComponente(txtTelefono, 8, 1);
        agregarComponente(lblDireccion, 9, 0);
        setBordeNaranja(txtDireccion);
        agregarComponente(txtDireccion, 9, 1);
        agregarComponente(lblEstado, 10, 0);
        setBordeNaranja(cmbEstado);
        agregarComponente(cmbEstado, 10, 1);

        JPanel panelBotones = new JPanel();
        panelBotones.setBackground(new Color(251, 234, 230));
        panelBotones.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        btnAceptar.setPreferredSize(new Dimension(100, 40));
        btnCancelar.setPreferredSize(new Dimension(100, 40));
        panelBotones.add(btnAceptar);
        panelBotones.add(btnCancelar);

        gbc.gridx = 0;
        gbc.gridy = 11;
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
                    txtPassword.getPassword().length == 0 ||
                    datePicker.getDate() == null ||
                    txtEmail.getText().trim().isEmpty() ||
                    txtTelefono.getText().trim().isEmpty() ||
                    txtDireccion.getText().trim().isEmpty() || cmbCurso.getSelectedItem() == null) {
                JOptionPane.showMessageDialog(null, "Todos los campos son obligatorios.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            Cursos curso = (Cursos) cmbCurso.getSelectedItem();
            Date fechaNacimiento = java.sql.Date.valueOf(datePicker.toString());
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

        cmbCurso.removeAllItems();

        for (Cursos curso : cursos) {
            cmbCurso.addItem(curso);
        }
    }

}
