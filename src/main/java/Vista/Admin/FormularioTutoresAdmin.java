package Vista.Admin;

import Mapeo.Tutores;
import Vista.Util.Boton;

import javax.swing.*;
import java.awt.*;

import static Vista.Util.EstiloComponentes.*;

public class FormularioTutoresAdmin extends JFrame {
    private Container panel;
    private GridBagLayout gLayout;
    private GridBagConstraints gbc;

    private JButton btnAceptar = new Boton("Aceptar", Boton.ButtonType.PRIMARY);
    private JButton btnCancelar = new Boton("Cancelar", Boton.ButtonType.DELETE);

    private JLabel lblDNI = new JLabel("DNI: ");
    private JLabel lblNombre = new JLabel("Nombre: ");
    private JLabel lblApellido = new JLabel("Apellido: ");
    private JLabel lblPassword = new JLabel("Contraseña: ");
    private JLabel lblEmail = new JLabel("Email: ");
    private JLabel lblTelefono = new JLabel("Teléfono: ");
    private JLabel lblUsuario = new JLabel("Usuario: ");
    private JLabel lblEstado = new JLabel("Estado: ");

    private JTextField txtDNI = crearTextField();
    private JTextField txtNombre = crearTextField();
    private JTextField txtApellido = crearTextField();
    private JPasswordField txtPassword = crearPasswordField();
    private JTextField txtEmail = crearTextField();
    private JTextField txtTelefono = crearTextField();
    private JTextField txtUsuario = crearTextField();
    private JComboBox<Tutores.EstadoTutor> cmbEstado = new JComboBox<>(Tutores.EstadoTutor.values());

    public FormularioTutoresAdmin() {
        initGUI();
        initEventos();
    }

    private void initGUI() {
        setTitle("Agregar Tutor");
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

        JLabel titulo = new JLabel("Agregar Tutor", SwingConstants.CENTER);
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

        btnAceptar.addActionListener(e -> {
            if (txtDNI.getText().trim().isEmpty() ||
                    txtNombre.getText().trim().isEmpty() ||
                    txtApellido.getText().trim().isEmpty() ||
                    txtUsuario.getText().trim().isEmpty() ||
                    txtPassword.getPassword().length == 0 ||
                    txtEmail.getText().trim().isEmpty()) {

                JOptionPane.showMessageDialog(null, "Todos los campos obligatorios deben ser completados.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Aquí se puede construir un objeto Tutor y guardarlo
            // Ejemplo:
            // Tutores tutor = new Tutores();
            // tutor.setDni(txtDNI.getText().trim());
            // ...
        });
    }
}
