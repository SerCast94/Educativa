package Vista.Admin.Modificar;

import Controlador.Controlador;
import Mapeo.Convalidaciones;
import Mapeo.Estudiantes;
import Mapeo.Cursos;
import Vista.Admin.VistaPrincipalAdmin;
import Vista.Util.Boton;
import Vista.Util.CustomDatePicker;

import javax.swing.*;
import java.awt.*;
import java.sql.Date;
import java.util.List;

import static Vista.Util.EstiloComponentes.*;

public class ActualizarConvalidacionesAdmin extends JFrame {
    private Container panel;
    private GridBagLayout gLayout;
    private GridBagConstraints gbc;

    private JButton btnAceptar = new Boton("Actualizar", Boton.ButtonType.PRIMARY);
    private JButton btnCancelar = new Boton("Cancelar", Boton.ButtonType.DELETE);

    private JLabel lblEstudiante = new JLabel("Estudiante:");
    private JLabel lblCursoOriginal = new JLabel("Curso Original:");
    private JLabel lblFecha = new JLabel("Fecha de Convalidación:");
    private JLabel lblEstado = new JLabel("Estado:");
    private JLabel lblComentarios = new JLabel("Comentarios:");

    private JComboBox<Estudiantes> cmbEstudiante = new JComboBox<>();
    private JComboBox<Cursos> cmbCursoOriginal = new JComboBox<>();
    private JComboBox<String> cmbEstado = new JComboBox<>(new String[]{"Aprobada", "Pendiente", "Rechazada"});

    private JTextField txtComentarios = crearTextField();
    private JScrollPane scrollComentarios = new JScrollPane(txtComentarios);
    private CustomDatePicker datePickerConvalidacion = new CustomDatePicker();

    private Convalidaciones convalidacion;

    public ActualizarConvalidacionesAdmin(Convalidaciones convalidacion) {
        this.convalidacion = convalidacion;
        initGUI();
        initEventos();
        cargarEstudiantes();
        cargarCursos();
        cargarDatosConvalidacion();
    }

    private void cargarDatosConvalidacion() {
        cmbEstudiante.setSelectedItem(convalidacion.getEstudiante());
        cmbCursoOriginal.setSelectedItem(convalidacion.getCursoOriginal());
        datePickerConvalidacion.setDate(convalidacion.getFechaConvalidacion().toLocalDate());
        cmbEstado.setSelectedItem(convalidacion.getEstadoConvalidacion().name());
        txtComentarios.setText(convalidacion.getComentarios());
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
        customizeComboBox(cmbCursoOriginal);

        agregarComponente(lblEstudiante, 1, 0);
        agregarComponente(cmbEstudiante, 1, 1);

        agregarComponente(lblCursoOriginal, 2, 0);
        agregarComponente(cmbCursoOriginal, 2, 1);

        agregarComponente(lblFecha, 3, 0);
        EspaciadoEnDatePicker(datePickerConvalidacion);
        agregarComponente(datePickerConvalidacion, 3, 1);

        agregarComponente(lblEstado, 4, 0);
        agregarComponente(cmbEstado, 4, 1);

        agregarComponente(lblComentarios, 5, 0);
        agregarComponente(txtComentarios, 5, 1);

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
            if (cmbEstudiante.getSelectedItem() == null ||
                    cmbCursoOriginal.getSelectedItem() == null ||
                    datePickerConvalidacion.getDate() == null ||
                    cmbEstado.getSelectedItem() == null) {

                JOptionPane.showMessageDialog(this, "Todos los campos obligatorios deben completarse.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            try {
                convalidacion.setEstudiante((Estudiantes) cmbEstudiante.getSelectedItem());
                convalidacion.setCursoOriginal((Cursos) cmbCursoOriginal.getSelectedItem());
                convalidacion.setFechaConvalidacion(Date.valueOf(datePickerConvalidacion.getDate()));
                convalidacion.setEstadoConvalidacion(Convalidaciones.EstadoConvalidacion.valueOf(cmbEstado.getSelectedItem().toString()));
                convalidacion.setComentarios(txtComentarios.getText().trim());

                Controlador.actualizarControladorConvalidacion(convalidacion);
                Controlador.actualizarListaConvalidaciones();

                VistaPrincipalAdmin vistaPrincipal = (VistaPrincipalAdmin) VistaPrincipalAdmin.getVistaPrincipal();
                vistaPrincipal.mostrarVistaConvalidaciones();

                JOptionPane.showMessageDialog(this, "Convalidación actualizada correctamente.");
                dispose();
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(this, "El estado seleccionado no es válido.", "Error", JOptionPane.ERROR_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error al actualizar la convalidación: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
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

    private void cargarCursos() {
        List<Cursos> cursos = Controlador.getListaCursos();
        cmbCursoOriginal.removeAllItems();
        for (Cursos curso : cursos) {
            cmbCursoOriginal.addItem(curso);
        }
    }
}
