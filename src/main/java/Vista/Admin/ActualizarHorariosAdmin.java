package Vista.Admin;

import Controlador.Controlador;
import Mapeo.Horarios;
import Mapeo.Cursos;
import Vista.Util.Boton;

import javax.swing.*;
import java.awt.*;
import java.util.List;

import static Vista.Util.EstiloComponentes.*;

public class ActualizarHorariosAdmin extends JFrame {
    private Container panel;
    private GridBagLayout gLayout;
    private GridBagConstraints gbc;
    private JButton btnAceptar = new Boton("Actualizar", Boton.ButtonType.PRIMARY);
    private JButton btnCancelar = new Boton("Cancelar", Boton.ButtonType.DELETE);

    private JLabel lblDia = new JLabel("Día:");
    private JLabel lblHoraInicio = new JLabel("Hora Inicio:");
    private JLabel lblHoraFin = new JLabel("Hora Fin:");
    private JLabel lblCurso = new JLabel("Curso:");
    private JLabel lblEstado = new JLabel("Estado:");

    private JTextField txtDia = crearTextField();
    private JTextField txtHoraInicio = crearTextField();
    private JTextField txtHoraFin = crearTextField();
    private JComboBox<Cursos> cmbCurso = new JComboBox<>();
    private JComboBox<String> cmbEstado = new JComboBox<>(new String[]{"activo", "inactivo"});

    private Horarios horario;

    public ActualizarHorariosAdmin(Horarios horario) {
        this.horario = horario;
        initGUI();
        initEventos();
        cargarCursos();
        cargarDatosHorario();
    }

    private void cargarDatosHorario() {
//        txtDia.setText(horario.getDia());
//        txtHoraInicio.setText(horario.getHoraInicio().toString());
//        txtHoraFin.setText(horario.getHoraFin().toString());
//        cmbEstado.setSelectedItem(horario.getEstado().name());
//        cmbCurso.setSelectedItem(horario.getCurso());
    }

    private void initGUI() {
        setTitle("Actualizar Horario");
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

        JLabel titulo = new JLabel("Actualizar Horario", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 24));
        titulo.setBorder(BorderFactory.createEmptyBorder(10, 0, 20, 0));
        titulo.setForeground(new Color(70, 70, 70));
        gbc.gridwidth = 2;
        agregarComponente(titulo, 0, 0);
        gbc.gridwidth = 1;

        customizeComboBox(cmbEstado);
        customizeComboBox(cmbCurso);

        agregarComponente(lblDia, 1, 0);
        setBordeNaranja(txtDia);
        agregarComponente(txtDia, 1, 1);

        agregarComponente(lblHoraInicio, 2, 0);
        setBordeNaranja(txtHoraInicio);
        agregarComponente(txtHoraInicio, 2, 1);

        agregarComponente(lblHoraFin, 3, 0);
        setBordeNaranja(txtHoraFin);
        agregarComponente(txtHoraFin, 3, 1);

        agregarComponente(lblCurso, 4, 0);
        setBordeNaranja(cmbCurso);
        agregarComponente(cmbCurso, 4, 1);

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
            if (txtDia.getText().trim().isEmpty() ||
                    txtHoraInicio.getText().trim().isEmpty() ||
                    txtHoraFin.getText().trim().isEmpty() ||
                    cmbCurso.getSelectedItem() == null) {

                JOptionPane.showMessageDialog(null, "Todos los campos son obligatorios.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

//            horario.setDia(txtDia.getText().trim());
//            horario.setHoraInicio(java.sql.Time.valueOf(txtHoraInicio.getText().trim()));
//            horario.setHoraFin(java.sql.Time.valueOf(txtHoraFin.getText().trim()));
//            horario.setCurso((Cursos) cmbCurso.getSelectedItem());
//            horario.setEstado(Horarios.EstadoHorario.valueOf(cmbEstado.getSelectedItem().toString()));

            try {
                Controlador.actualizarControladorHorario(horario);
                Controlador.actualizarListaHorarios();

                VistaPrincipalAdmin vistaPrincipal = (VistaPrincipalAdmin) VistaPrincipalAdmin.getVistaPrincipal();
                vistaPrincipal.mostrarVistaHorarios();

                JOptionPane.showMessageDialog(null, "Horario actualizado correctamente");
                dispose();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Error al actualizar horario", "Error", JOptionPane.ERROR_MESSAGE);
                Controlador.rollback();
            }
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