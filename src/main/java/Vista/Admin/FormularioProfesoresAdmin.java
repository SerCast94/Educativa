package Vista.Admin;

import Mapeo.Profesores;
import Vista.Util.Boton;

import javax.swing.*;
import java.awt.*;

import static Vista.Util.EstiloComponentes.*;

public class FormularioProfesoresAdmin extends JFrame {
    private Container panel;
    private GridBagLayout gLayout;
    private GridBagConstraints gbc;

    private JButton btnAceptar = new Boton("Aceptar", Boton.ButtonType.PRIMARY);
    private JButton btnCancelar = new Boton("Cancelar", Boton.ButtonType.DELETE);

    private JLabel lblDNI = new JLabel("DNI: ");
    private JLabel lblNombre = new JLabel("Nombre: ");
    private JLabel lblApellido = new JLabel("Apellido: ");
    private JLabel lblEmail = new JLabel("Email: ");
    private JLabel lblTelefono = new JLabel("Teléfono: ");
    private JLabel lblDireccion = new JLabel("Dirección: ");
    private JLabel lblUsuario = new JLabel("Usuario: ");
    private JLabel lblContrasena = new JLabel("Contraseña: ");
    private JLabel lblEstado = new JLabel("Estado: ");

    private JTextField txtDNI = crearTextField();
    private JTextField txtNombre = crearTextField();
    private JTextField txtApellido = crearTextField();
    private JTextField txtEmail = crearTextField();
    private JTextField txtTelefono = crearTextField();
    private JTextField txtDireccion = crearTextField();
    private JTextField txtUsuario = crearTextField();
    private JPasswordField txtContrasena = crearPasswordField();
    private JComboBox<Profesores.EstadoProfesor> cmbEstado = new JComboBox<>(Profesores.EstadoProfesor.values());

    public FormularioProfesoresAdmin() {
        initGUI();
        initEventos();
    }

    private void initGUI() {
        setTitle("Agregar Profesor");
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

        JLabel titulo = new JLabel("Agregar Profesor", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 24));
        titulo.setBorder(BorderFactory.createEmptyBorder(10, 0, 20, 0));
        titulo.setForeground(new Color(70, 70, 70));
        gbc.gridwidth = 2;
        agregarComponente(titulo, 0, 0);
        gbc.gridwidth = 1;

        customizeComboBox(cmbEstado);

        agregarComponente(lblDNI, 1, 0);
        setBordeNaranja(txtDNI);
        agregarComponente(txtDNI, 1, 1);

        agregarComponente(lblNombre, 2, 0);
        setBordeNaranja(txtNombre);
        agregarComponente(txtNombre, 2, 1);

        agregarComponente(lblApellido, 3, 0);
        setBordeNaranja(txtApellido);
        agregarComponente(txtApellido, 3, 1);

        agregarComponente(lblEmail, 4, 0);
        setBordeNaranja(txtEmail);
        agregarComponente(txtEmail, 4, 1);

        agregarComponente(lblTelefono, 5, 0);
        setBordeNaranja(txtTelefono);
        agregarComponente(txtTelefono, 5, 1);

        agregarComponente(lblDireccion, 6, 0);
        setBordeNaranja(txtDireccion);
        agregarComponente(txtDireccion, 6, 1);

        agregarComponente(lblUsuario, 7, 0);
        setBordeNaranja(txtUsuario);
        agregarComponente(txtUsuario, 7, 1);

        agregarComponente(lblContrasena, 8, 0);
        setBordeNaranja(txtContrasena);
        agregarComponente(txtContrasena, 8, 1);

        agregarComponente(lblEstado, 9, 0);
        setBordeNaranja(cmbEstado);
        agregarComponente(cmbEstado, 9, 1);

        JPanel panelBotones = new JPanel();
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
                    txtEmail.getText().trim().isEmpty() ||
                    txtUsuario.getText().trim().isEmpty() ||
                    txtContrasena.getPassword().length == 0) {
                JOptionPane.showMessageDialog(null, "Los campos obligatorios no pueden estar vacíos.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            String dni = txtDNI.getText().trim();
            String nombre = txtNombre.getText().trim();
            String apellido = txtApellido.getText().trim();
            String email = txtEmail.getText().trim();
            String telefono = txtTelefono.getText().trim();
            String direccion = txtDireccion.getText().trim();
            String usuario = txtUsuario.getText().trim();
            String contrasena = new String(txtContrasena.getPassword());
            Profesores.EstadoProfesor estado = (Profesores.EstadoProfesor) cmbEstado.getSelectedItem();

            // Aquí se agregaría la lógica para guardar el profesor en la base de datos
        });
    }
}