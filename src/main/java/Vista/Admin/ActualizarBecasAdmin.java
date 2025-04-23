package Vista.Admin;

import Controlador.Controlador;
import Mapeo.Becas;
import Mapeo.Estudiantes;
import Vista.Util.Boton;

import javax.swing.*;
import java.awt.*;
import java.util.List;

import static Vista.Util.EstiloComponentes.*;

public class ActualizarBecasAdmin extends JFrame {
    private Container panel;
    private GridBagLayout gLayout;
    private GridBagConstraints gbc;
    private JButton btnAceptar = new Boton("Actualizar", Boton.ButtonType.PRIMARY);
    private JButton btnCancelar = new Boton("Cancelar", Boton.ButtonType.DELETE);

    private JLabel lblTipo = new JLabel("Tipo:");
    private JLabel lblMonto = new JLabel("Monto:");
    private JLabel lblEstudiante = new JLabel("Estudiante:");
    private JLabel lblEstado = new JLabel("Estado:");

    private JTextField txtTipo = crearTextField();
    private JTextField txtMonto = crearTextField();
    private JComboBox<Estudiantes> cmbEstudiante = new JComboBox<>();
    private JComboBox<String> cmbEstado = new JComboBox<>(new String[]{"activa", "inactiva"});

    private Becas beca;

    public ActualizarBecasAdmin(Becas beca) {
        this.beca = beca;
        initGUI();
        initEventos();
        cargarEstudiantes();
        cargarDatosBeca();
    }

    private void cargarDatosBeca() {
//        txtTipo.setText(beca.getTipo());
//        txtMonto.setText(String.valueOf(beca.getMonto()));
//        cmbEstado.setSelectedItem(beca.getEstado().name());
//        cmbEstudiante.setSelectedItem(beca.getEstudiante());
    }

    private void initGUI() {
        setTitle("Actualizar Beca");
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

        JLabel titulo = new JLabel("Actualizar Beca", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 24));
        titulo.setBorder(BorderFactory.createEmptyBorder(10, 0, 20, 0));
        titulo.setForeground(new Color(70, 70, 70));
        gbc.gridwidth = 2;
        agregarComponente(titulo, 0, 0);
        gbc.gridwidth = 1;

        customizeComboBox(cmbEstado);
        customizeComboBox(cmbEstudiante);

        agregarComponente(lblTipo, 1, 0);
        setBordeNaranja(txtTipo);
        agregarComponente(txtTipo, 1, 1);

        agregarComponente(lblMonto, 2, 0);
        setBordeNaranja(txtMonto);
        agregarComponente(txtMonto, 2, 1);

        agregarComponente(lblEstudiante, 3, 0);
        setBordeNaranja(cmbEstudiante);
        agregarComponente(cmbEstudiante, 3, 1);

        agregarComponente(lblEstado, 4, 0);
        setBordeNaranja(cmbEstado);
        agregarComponente(cmbEstado, 4, 1);

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
            if (txtTipo.getText().trim().isEmpty() ||
                    txtMonto.getText().trim().isEmpty() ||
                    cmbEstudiante.getSelectedItem() == null) {

                JOptionPane.showMessageDialog(null, "Todos los campos son obligatorios.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

//            beca.setTipo(txtTipo.getText().trim());
//            beca.setMonto(Double.parseDouble(txtMonto.getText().trim()));
//            beca.setEstudiante((Estudiantes) cmbEstudiante.getSelectedItem());
//            beca.setEstado(Becas.EstadoBeca.valueOf(cmbEstado.getSelectedItem().toString()));

            try {
                Controlador.actualizarControladorBeca(beca);
                Controlador.actualizarListaBecas();

                VistaPrincipalAdmin vistaPrincipal = (VistaPrincipalAdmin) VistaPrincipalAdmin.getVistaPrincipal();
                vistaPrincipal.mostrarVistaBecas();

                JOptionPane.showMessageDialog(null, "Beca actualizada correctamente");
                dispose();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Error al actualizar beca", "Error", JOptionPane.ERROR_MESSAGE);
                Controlador.rollback();
            }
        });
    }

    private void cargarEstudiantes() {
        List<Estudiantes> estudiantes = Controlador.getListaEstudiantes();
        cmbEstudiante.removeAllItems();
        for (Estudiantes estudiante : estudiantes) {
            cmbEstudiante.addItem(estudiante);
        }
    }
}