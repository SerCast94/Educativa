package Vista.Admin;

import Controlador.Controlador;
import Mapeo.Becas;
import Mapeo.Estudiantes;
import Vista.Util.Boton;
import Vista.Util.CustomDatePicker;

import javax.swing.*;
import java.awt.*;
import java.util.List;

import static Vista.Util.EstiloComponentes.*;

public class FormularioBecasAdmin extends JFrame {

    private Container panel;
    private GridBagLayout gLayout;
    private GridBagConstraints gbc;

    private JButton btnAceptar = new Boton("Aceptar", Boton.ButtonType.PRIMARY);
    private JButton btnCancelar = new Boton("Cancelar", Boton.ButtonType.DELETE);

    private JLabel lblEstudiante = new JLabel("Estudiante: ");
    private JLabel lblTipoBeca = new JLabel("Tipo de Beca: ");
    private JLabel lblMonto = new JLabel("Monto: ");
    private JLabel lblFechaAsignacion = new JLabel("Fecha de Asignación: ");
    private JLabel lblEstadoBeca = new JLabel("Estado de Beca: ");
    private JLabel lblComentarios = new JLabel("Comentarios: ");

    private JComboBox<Estudiantes> cmbEstudiantes = new JComboBox<>();
    private JComboBox<Becas.TipoBeca> cmbTipoBeca = new JComboBox<>(Becas.TipoBeca.values());
    private JComboBox<Becas.EstadoBeca> cmbEstadoBeca = new JComboBox<>(Becas.EstadoBeca.values());
    private JTextField txtMonto = crearTextField();
    private JTextArea txtComentarios = new JTextArea(3, 20);
    private JScrollPane scrollComentarios = new JScrollPane(txtComentarios);
    private CustomDatePicker datePickerAsignacion = new CustomDatePicker();

    public FormularioBecasAdmin() {
        initGUI();
        initEventos();
        cargarEstudiantes();
    }

    private void initGUI() {
        setTitle("Asignar Beca");
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

        JLabel titulo = new JLabel("Asignar Beca", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 24));
        titulo.setBorder(BorderFactory.createEmptyBorder(10, 0, 20, 0));
        titulo.setForeground(new Color(70, 70, 70));
        gbc.gridwidth = 2;
        agregarComponente(titulo, 0, 0);
        gbc.gridwidth = 1;

        customizeComboBox(cmbEstadoBeca);
        customizeComboBox(cmbTipoBeca);
        customizeComboBox(cmbEstudiantes);

        agregarComponente(lblEstudiante, 1, 0);
        agregarComponente(cmbEstudiantes, 1, 1);
        agregarComponente(lblTipoBeca, 2, 0);
        agregarComponente(cmbTipoBeca, 2, 1);
        agregarComponente(lblMonto, 3, 0);
        agregarComponente(txtMonto, 3, 1);
        agregarComponente(lblFechaAsignacion, 4, 0);
        agregarComponente(datePickerAsignacion, 4, 1);
        agregarComponente(lblEstadoBeca, 5, 0);
        agregarComponente(cmbEstadoBeca, 5, 1);

        agregarComponente(lblComentarios, 6, 0);
        txtComentarios.setLineWrap(true);
        txtComentarios.setWrapStyleWord(true);
        setBordeNaranja(scrollComentarios);
        agregarComponente(scrollComentarios, 6, 1);

        // Panel de botones
        JPanel panelBotones = new JPanel();
        panelBotones.setBackground(new Color(251, 234, 230));
        panelBotones.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        btnAceptar.setPreferredSize(new Dimension(100, 40));
        btnCancelar.setPreferredSize(new Dimension(100, 40));
        panelBotones.add(btnAceptar);
        panelBotones.add(btnCancelar);

        gbc.gridx = 0;
        gbc.gridy = 7;
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
            if (cmbEstudiantes.getSelectedItem() == null ||
                    cmbTipoBeca.getSelectedItem() == null ||
                    txtMonto.getText().trim().isEmpty() ||
                    datePickerAsignacion.getDate() == null ||
                    cmbEstadoBeca.getSelectedItem() == null) {

                JOptionPane.showMessageDialog(null, "Todos los campos obligatorios deben ser completados.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            // Guardar Beca
        });
    }

    private void cargarEstudiantes() {
        List<Estudiantes> estudiantes = Controlador.getListaEstudiantes();
        cmbEstudiantes.removeAllItems();
        for (Estudiantes est : estudiantes) {
            cmbEstudiantes.addItem(est);
        }
    }
}
