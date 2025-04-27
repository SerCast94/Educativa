package Vista.Admin.Modificar;

import Controlador.Controlador;
import Mapeo.Profesores;
import Vista.Admin.VistaPrincipalAdmin;
import Vista.Util.Boton;

import javax.swing.*;
import java.awt.*;

import static BackUtil.Encriptador.encryptMD5;
import static Vista.Util.EstiloComponentes.*;

public class ActualizarProfesoresAdmin extends JFrame {
    private Container panel;
    private GridBagLayout gLayout;
    private GridBagConstraints gbc;

    private JButton btnAceptar = new Boton("Actualizar", Boton.ButtonType.PRIMARY);
    private JButton btnCancelar = new Boton("Cancelar", Boton.ButtonType.DELETE);

    private JLabel lblDNI = new JLabel("DNI: ");
    private JLabel lblNombre = new JLabel("Nombre: ");
    private JLabel lblApellido = new JLabel("Apellido: ");
    private JLabel lblPassword = new JLabel("Contraseña: ");
    private JLabel lblEmail = new JLabel("Email: ");
    private JLabel lblTelefono = new JLabel("Teléfono: ");
    private JLabel lblDireccion = new JLabel("Dirección: ");
    private JLabel lblEstado = new JLabel("Estado: ");
    private JLabel lblUsuario = new JLabel("Usuario: ");

    private JTextField txtDNI = crearTextField();
    private JTextField txtNombre = crearTextField();
    private JTextField txtApellido = crearTextField();
    private JPasswordField txtPassword = crearPasswordField();
    private JTextField txtEmail = crearTextField();
    private JTextField txtTelefono = crearTextField();
    private JTextField txtDireccion = crearTextField();
    private JTextField txtUsuario = crearTextField();

    private JComboBox<String> cmbEstado = new JComboBox<>(new String[]{"activo", "inactivo"});

    private Profesores profesor;

    public ActualizarProfesoresAdmin(Profesores profesor) {
        this.profesor = profesor;
        initGUI();
        initEventos();
        cargarDatosProfesor();
    }

    private void cargarDatosProfesor() {
        txtDNI.setText(profesor.getDni());
        txtNombre.setText(profesor.getNombre());
        txtApellido.setText(profesor.getApellido());
        txtUsuario.setText(profesor.getUsuario());
        txtPassword.setText("");
        txtEmail.setText(profesor.getEmail());
        txtTelefono.setText(profesor.getTelefono());
        txtDireccion.setText(profesor.getDireccion());
        cmbEstado.setSelectedItem(profesor.getEstado().name().toLowerCase());
    }

    private void initGUI() {
        setTitle("Actualizar Profesor");
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

        JLabel titulo = new JLabel("Actualizar Profesor", SwingConstants.CENTER);
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

        agregarComponente(lblUsuario, 4, 0);
        setBordeNaranja(txtUsuario);
        agregarComponente(txtUsuario, 4, 1);

        agregarComponente(lblPassword, 5, 0);
        setBordeNaranja(txtPassword);
        agregarComponente(txtPassword, 5, 1);

        agregarComponente(lblEmail, 6, 0);
        setBordeNaranja(txtEmail);
        agregarComponente(txtEmail, 6, 1);

        agregarComponente(lblTelefono, 7, 0);
        setBordeNaranja(txtTelefono);
        agregarComponente(txtTelefono, 7, 1);

        agregarComponente(lblDireccion, 8, 0);
        setBordeNaranja(txtDireccion);
        agregarComponente(txtDireccion, 8, 1);

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
                    txtUsuario.getText().trim().isEmpty() ||
                    txtEmail.getText().trim().isEmpty() ||
                    txtTelefono.getText().trim().isEmpty() ||
                    txtDireccion.getText().trim().isEmpty()) {

                JOptionPane.showMessageDialog(null, "Todos los campos son obligatorios.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            String nuevaPassword = new String(txtPassword.getPassword());

            profesor.setNombre(txtNombre.getText().trim());
            profesor.setApellido(txtApellido.getText().trim());
            profesor.setDni(txtDNI.getText().trim());
            profesor.setEmail(txtEmail.getText().trim());
            profesor.setTelefono(txtTelefono.getText().trim());
            profesor.setDireccion(txtDireccion.getText().trim());
            profesor.setUsuario(txtUsuario.getText().trim());
            profesor.setEstado(Profesores.EstadoProfesor.valueOf(cmbEstado.getSelectedItem().toString()));

            if (!nuevaPassword.isEmpty()) {
                profesor.setContrasena(encryptMD5(nuevaPassword));
            }

            try {
                Controlador.actualizarControladorProfesor(profesor);
                Controlador.actualizarListaProfesores();

                VistaPrincipalAdmin vistaPrincipal = (VistaPrincipalAdmin) VistaPrincipalAdmin.getVistaPrincipal();
                vistaPrincipal.mostrarVistaProfesores();

                JOptionPane.showMessageDialog(null, "Profesor actualizado correctamente");
                dispose();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Error al actualizar profesor", "Error", JOptionPane.ERROR_MESSAGE);
                Controlador.rollback();
            }
        });
    }
}
