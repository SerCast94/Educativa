package Vista.Admin;

import Controlador.Controlador;
import Mapeo.HistorialAcademico;
import Mapeo.Estudiantes;
import Vista.Util.Boton;

import javax.swing.*;
import java.awt.*;
import java.util.List;

import static Vista.Util.EstiloComponentes.*;

public class ActualizarHistorialAcademicoAdmin extends JFrame {
    private Container panel;
    private GridBagLayout gLayout;
    private GridBagConstraints gbc;
    private JButton btnAceptar = new Boton("Actualizar", Boton.ButtonType.PRIMARY);
    private JButton btnCancelar = new Boton("Cancelar", Boton.ButtonType.DELETE);

    private JLabel lblCurso = new JLabel("Curso:");
    private JLabel lblNota = new JLabel("Nota:");
    private JLabel lblEstudiante = new JLabel("Estudiante:");
    private JLabel lblEstado = new JLabel("Estado:");

    private JTextField txtCurso = crearTextField();
    private JTextField txtNota = crearTextField();
    private JComboBox<Estudiantes> cmbEstudiante = new JComboBox<>();
    private JComboBox<String> cmbEstado = new JComboBox<>(new String[]{"aprobado", "reprobado", "pendiente"});

    private HistorialAcademico historial;

    public ActualizarHistorialAcademicoAdmin(HistorialAcademico historial) {
        this.historial = historial;
        initGUI();
        initEventos();
        cargarEstudiantes();
        cargarDatosHistorial();
    }

    private void cargarDatosHistorial() {
//        txtCurso.setText(historial.getCurso());
//        txtNota.setText(String.valueOf(historial.getNota()));
//        cmbEstado.setSelectedItem(historial.getEstado().name());
//        cmbEstudiante.setSelectedItem(historial.getEstudiante());
    }

    private void initGUI() {
        setTitle("Actualizar Historial Académico");
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

        JLabel titulo = new JLabel("Actualizar Historial Académico", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 24));
        titulo.setBorder(BorderFactory.createEmptyBorder(10, 0, 20, 0));
        titulo.setForeground(new Color(70, 70, 70));
        gbc.gridwidth = 2;
        agregarComponente(titulo, 0, 0);
        gbc.gridwidth = 1;

        customizeComboBox(cmbEstado);
        customizeComboBox(cmbEstudiante);

        agregarComponente(lblCurso, 1, 0);
        setBordeNaranja(txtCurso);
        agregarComponente(txtCurso, 1, 1);

        agregarComponente(lblNota, 2, 0);
        setBordeNaranja(txtNota);
        agregarComponente(txtNota, 2, 1);

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
            if (txtCurso.getText().trim().isEmpty() ||
                    txtNota.getText().trim().isEmpty() ||
                    cmbEstudiante.getSelectedItem() == null) {

                JOptionPane.showMessageDialog(null, "Todos los campos son obligatorios.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

//            historial.setCurso(txtCurso.getText().trim());
//            historial.setNota(Double.parseDouble(txtNota.getText().trim()));
//            historial.setEstudiante((Estudiantes) cmbEstudiante.getSelectedItem());
//            historial.setEstado(HistorialAcademico.EstadoHistorial.valueOf(cmbEstado.getSelectedItem().toString()));

            try {
//                Controlador.actualizarControladorHistorial(historial);
//                Controlador.actualizarListaHistoriales();

                VistaPrincipalAdmin vistaPrincipal = (VistaPrincipalAdmin) VistaPrincipalAdmin.getVistaPrincipal();
                vistaPrincipal.mostrarVistaHistorialAcademico();

                JOptionPane.showMessageDialog(null, "Historial académico actualizado correctamente");
                dispose();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Error al actualizar historial académico", "Error", JOptionPane.ERROR_MESSAGE);
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