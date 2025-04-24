package Vista.Admin;

import Controlador.Controlador;
import Mapeo.Extraescolares;
import Mapeo.Profesores;
import Vista.Util.Boton;
import Vista.Util.JTextFieldConMargen;

import javax.swing.*;
import java.awt.*;
import java.util.List;

import static Vista.Util.EstiloComponentes.*;

public class FormularioExtraescolaresAdmin extends JFrame {
    private Container panel;
    private GridBagLayout gLayout;
    private GridBagConstraints gbc;
    private JButton btnAceptar = new Boton("Aceptar", Boton.ButtonType.PRIMARY);
    private JButton btnCancelar = new Boton("Cancelar", Boton.ButtonType.DELETE);

    private JLabel lblNombre = new JLabel("Nombre: ");
    private JLabel lblDescripcion = new JLabel("Descripción: ");
    private JLabel lblTipo = new JLabel("Tipo: ");
    private JLabel lblProfesor = new JLabel("Profesor: ");

    private JTextField txtNombre = crearTextField();
    private JTextField txtDescripcion = crearTextField();
    private JComboBox<Extraescolares.TipoExtraescolar> cmbTipo = new JComboBox<>(Extraescolares.TipoExtraescolar.values());
    private JComboBox<Profesores> cmbProfesor = new JComboBox<>();

    public FormularioExtraescolaresAdmin() {
        initGUI();
        initEventos();
        cargarProfesores();
    }

    private void initGUI() {
        setTitle("Registrar Extraescolar");
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

        JLabel titulo = new JLabel("Registrar Actividad Extraescolar", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 24));
        titulo.setForeground(new Color(70, 70, 70));
        titulo.setBorder(BorderFactory.createEmptyBorder(10, 0, 20, 0));
        gbc.gridwidth = 2;
        agregarComponente(titulo, 0, 0);
        gbc.gridwidth = 1;

        customizeComboBox(cmbProfesor);
        customizeComboBox(cmbTipo);

        agregarComponente(lblNombre, 1, 0);
        setBordeNaranja(txtNombre);
        agregarComponente(txtNombre, 1, 1);

        agregarComponente(lblDescripcion, 2, 0);
        setBordeNaranja(txtDescripcion);
        agregarComponente(txtDescripcion, 2, 1);

        agregarComponente(lblTipo, 3, 0);
        setBordeNaranja(cmbTipo);
        agregarComponente(cmbTipo, 3, 1);

        agregarComponente(lblProfesor, 4, 0);
        setBordeNaranja(cmbProfesor);
        agregarComponente(cmbProfesor, 4, 1);

        JPanel panelBotones = new JPanel();
        panelBotones.setBackground(new Color(251, 234, 230));
        panelBotones.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        btnAceptar.setPreferredSize(new Dimension(100, 40));
        btnCancelar.setPreferredSize(new Dimension(100, 40));
        panelBotones.add(btnAceptar);
        panelBotones.add(btnCancelar);

        gbc.gridx = 0;
        gbc.gridy = 5;
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
                    txtDescripcion.getText().trim().isEmpty() ||
                    cmbTipo.getSelectedItem() == null ||
                    cmbProfesor.getSelectedItem() == null) {

                JOptionPane.showMessageDialog(null, "Todos los campos obligatorios deben ser completados.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            try {
                Extraescolares nuevaExtraescolar = new Extraescolares(
                        txtNombre.getText().trim(),
                        txtDescripcion.getText().trim(),
                        (Extraescolares.TipoExtraescolar) cmbTipo.getSelectedItem(),
                        (Profesores) cmbProfesor.getSelectedItem()
                );

                Controlador.insertarControladorExtraescolar(nuevaExtraescolar);
                Controlador.actualizarListaExtraescolares();

                VistaPrincipalAdmin vistaPrincipal = (VistaPrincipalAdmin) VistaPrincipalAdmin.getVistaPrincipal();
                vistaPrincipal.mostrarVistaActividadesExtraescolares();

                JOptionPane.showMessageDialog(null, "Actividad extraescolar registrada correctamente.");
                dispose();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Error al registrar la actividad extraescolar.", "Error", JOptionPane.ERROR_MESSAGE);
                Controlador.rollback();
            }
        });
    }

    private void cargarProfesores() {
        List<Profesores> profesores = Controlador.getListaProfesores();
        cmbProfesor.removeAllItems();
        for (Profesores profesor : profesores) {
            cmbProfesor.addItem(profesor);
        }
    }
}
