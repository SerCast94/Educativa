package Vista.Admin;

import Controlador.Controlador;
import Mapeo.Convalidaciones;
import Mapeo.Estudiantes;
import Vista.Util.Boton;

import javax.swing.*;
import java.awt.*;
import java.util.List;

import static Vista.Util.EstiloComponentes.*;

public class ActualizarConvalidacionesAdmin extends JFrame {
    private Container panel;
    private GridBagLayout gLayout;
    private GridBagConstraints gbc;
    private JButton btnAceptar = new Boton("Actualizar", Boton.ButtonType.PRIMARY);
    private JButton btnCancelar = new Boton("Cancelar", Boton.ButtonType.DELETE);

    private JLabel lblAsignatura = new JLabel("Asignatura:");
    private JLabel lblEstudiante = new JLabel("Estudiante:");
    private JLabel lblEstado = new JLabel("Estado:");

    private JTextField txtAsignatura = crearTextField();
    private JComboBox<Estudiantes> cmbEstudiante = new JComboBox<>();
    private JComboBox<String> cmbEstado = new JComboBox<>(new String[]{"pendiente", "aprobada", "rechazada"});

    private Convalidaciones convalidacion;

    public ActualizarConvalidacionesAdmin(Convalidaciones convalidacion) {
        this.convalidacion = convalidacion;
        initGUI();
        initEventos();
        cargarEstudiantes();
        cargarDatosConvalidacion();
    }

    private void cargarDatosConvalidacion() {
//        txtAsignatura.setText(convalidacion.getAsignatura());
//        cmbEstado.setSelectedItem(convalidacion.getEstado().name());
//        cmbEstudiante.setSelectedItem(convalidacion.getEstudiante());
    }

    private void initGUI() {
        setTitle("Actualizar Convalidación");
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

        JLabel titulo = new JLabel("Actualizar Convalidación", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 24));
        titulo.setBorder(BorderFactory.createEmptyBorder(10, 0, 20, 0));
        titulo.setForeground(new Color(70, 70, 70));
        gbc.gridwidth = 2;
        agregarComponente(titulo, 0, 0);
        gbc.gridwidth = 1;

        customizeComboBox(cmbEstado);
        customizeComboBox(cmbEstudiante);

        agregarComponente(lblAsignatura, 1, 0);
        setBordeNaranja(txtAsignatura);
        agregarComponente(txtAsignatura, 1, 1);

        agregarComponente(lblEstudiante, 2, 0);
        setBordeNaranja(cmbEstudiante);
        agregarComponente(cmbEstudiante, 2, 1);

        agregarComponente(lblEstado, 3, 0);
        setBordeNaranja(cmbEstado);
        agregarComponente(cmbEstado, 3, 1);

        JPanel panelBotones = new JPanel();
        panelBotones.setBackground(new Color(251, 234, 230));
        panelBotones.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        btnAceptar.setPreferredSize(new Dimension(100, 40));
        btnCancelar.setPreferredSize(new Dimension(100, 40));
        panelBotones.add(btnAceptar);
        panelBotones.add(btnCancelar);

        gbc.gridx = 0;
        gbc.gridy = 4;
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
            if (txtAsignatura.getText().trim().isEmpty() || cmbEstudiante.getSelectedItem() == null) {
                JOptionPane.showMessageDialog(null, "Todos los campos son obligatorios.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

//            convalidacion.setAsignatura(txtAsignatura.getText().trim());
//            convalidacion.setEstudiante((Estudiantes) cmbEstudiante.getSelectedItem());
//            convalidacion.setEstado(Convalidaciones.EstadoConvalidacion.valueOf(cmbEstado.getSelectedItem().toString()));

            try {
                Controlador.actualizarControladorConvalidacion(convalidacion);
                Controlador.actualizarListaConvalidaciones();

                VistaPrincipalAdmin vistaPrincipal = (VistaPrincipalAdmin) VistaPrincipalAdmin.getVistaPrincipal();
                vistaPrincipal.mostrarVistaConvalidaciones();

                JOptionPane.showMessageDialog(null, "Convalidación actualizada correctamente");
                dispose();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Error al actualizar convalidación", "Error", JOptionPane.ERROR_MESSAGE);
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