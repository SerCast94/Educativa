package Vista.Admin;

import Controlador.Controlador;
import Mapeo.Administradores;
import Vista.Util.Boton;

import javax.swing.*;
import java.awt.*;
import java.util.List;

import static Vista.Util.EstiloComponentes.*;

public class ActualizarAdministradoresAdmin extends JFrame {
    private Container panel;
    private GridBagLayout gLayout;
    private GridBagConstraints gbc;
    private JButton btnAceptar = new Boton("Actualizar", Boton.ButtonType.PRIMARY);
    private JButton btnCancelar = new Boton("Cancelar", Boton.ButtonType.DELETE);

    private JLabel lblNombre = new JLabel("Nombre:");
    private JLabel lblApellido = new JLabel("Apellido:");
    private JLabel lblUsuario = new JLabel("Usuario:");
    private JLabel lblEmail = new JLabel("Email:");
    private JLabel lblEstado = new JLabel("Estado:");

    private JTextField txtNombre = crearTextField();
    private JTextField txtApellido = crearTextField();
    private JTextField txtUsuario = crearTextField();
    private JTextField txtEmail = crearTextField();
    private JComboBox<String> cmbEstado = new JComboBox<>(new String[]{"activo", "inactivo"});

    private Administradores administrador;

    public ActualizarAdministradoresAdmin(Administradores administrador) {
        this.administrador = administrador;
        initGUI();
        initEventos();
        cargarDatosAdministrador();
    }

    private void cargarDatosAdministrador() {
        txtNombre.setText(administrador.getNombre());
        txtApellido.setText(administrador.getApellido());
        txtUsuario.setText(administrador.getUsuario());
        txtEmail.setText(administrador.getEmail());
        cmbEstado.setSelectedItem(administrador.getEstado().name());
    }

    private void initGUI() {
        setTitle("Actualizar Administrador");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(600, 500);
        setLocationRelativeTo(null);

        panel = this.getContentPane();
        panel.setBackground(new Color(251, 234, 230));
        gLayout = new GridBagLayout();
        gbc = new GridBagConstraints();
        panel.setLayout(gLayout);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);

        JLabel titulo = new JLabel("Actualizar Administrador", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 24));
        titulo.setBorder(BorderFactory.createEmptyBorder(10, 0, 20, 0));
        titulo.setForeground(new Color(70, 70, 70));
        gbc.gridwidth = 2;
        agregarComponente(titulo, 0, 0);
        gbc.gridwidth = 1;

        customizeComboBox(cmbEstado);

        agregarComponente(lblNombre, 1, 0);
        setBordeNaranja(txtNombre);
        agregarComponente(txtNombre, 1, 1);

        agregarComponente(lblApellido, 2, 0);
        setBordeNaranja(txtApellido);
        agregarComponente(txtApellido, 2, 1);

        agregarComponente(lblUsuario, 3, 0);
        setBordeNaranja(txtUsuario);
        agregarComponente(txtUsuario, 3, 1);

        agregarComponente(lblEmail, 4, 0);
        setBordeNaranja(txtEmail);
        agregarComponente(txtEmail, 4, 1);

        agregarComponente(lblEstado, 5, 0);
        setBordeNaranja(cmbEstado);
        agregarComponente(cmbEstado, 5, 1);

        JPanel panelBotones = new JPanel();
        panelBotones.setBackground(new Color(251, 234, 230));
        panelBotones.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        btnAceptar.setPreferredSize(new Dimension(100, 40));
        btnCancelar.setPreferredSize(new Dimension(100, 40));
        panelBotones.add(btnAceptar);
        panelBotones.add(btnCancelar);

        gbc.gridx = 0;
        gbc.gridy = 6;
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
            if (txtNombre.getText().trim().isEmpty() ||
                    txtApellido.getText().trim().isEmpty() ||
                    txtUsuario.getText().trim().isEmpty() ||
                    txtEmail.getText().trim().isEmpty()) {

                JOptionPane.showMessageDialog(null, "Todos los campos son obligatorios.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            administrador.setNombre(txtNombre.getText().trim());
            administrador.setApellido(txtApellido.getText().trim());
            administrador.setUsuario(txtUsuario.getText().trim());
            administrador.setEmail(txtEmail.getText().trim());
            administrador.setEstado(Administradores.EstadoAdministrador.valueOf(cmbEstado.getSelectedItem().toString()));

            try {
                Controlador.actualizarControladorAdministrador(administrador);
                Controlador.actualizarListaAdministradores();

                JOptionPane.showMessageDialog(null, "Administrador actualizado correctamente");
                dispose();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Error al actualizar administrador", "Error", JOptionPane.ERROR_MESSAGE);
                Controlador.rollback();
            }
        });
    }
}