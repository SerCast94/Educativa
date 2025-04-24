package Vista.Admin;

import Controlador.Controlador;
import Mapeo.HistorialAcademico;
import Mapeo.Estudiantes;
import Mapeo.Cursos;
import Vista.Util.Boton;
import Vista.Util.CustomDatePicker;

import javax.swing.*;
import java.awt.*;
import java.sql.Date;
import java.util.List;

import static Vista.Util.EstiloComponentes.*;

public class ActualizarHistorialAcademicoAdmin extends JFrame {
    private Container panel;
    private GridBagLayout gLayout;
    private GridBagConstraints gbc;

    private JLabel lblEstudiante = new JLabel("Estudiante:");
    private JLabel lblCurso = new JLabel("Curso:");
    private JLabel lblNotaFinal = new JLabel("Nota Final:");
    private JLabel lblFechaAprobacion = new JLabel("Fecha Aprobación:");
    private JLabel lblComentarios = new JLabel("Comentarios:");

    private JComboBox<Estudiantes> cmbEstudiante = new JComboBox<>();
    private JComboBox<Cursos> cmbCurso = new JComboBox<>();
    private JTextField txtNotaFinal = crearTextField();
    private CustomDatePicker dateAprobacion = new CustomDatePicker();
    private JTextField txtComentarios = crearTextField();

    private JButton btnAceptar = new Boton("Actualizar", Boton.ButtonType.PRIMARY);
    private JButton btnCancelar = new Boton("Cancelar", Boton.ButtonType.DELETE);

    private HistorialAcademico historial;

    public ActualizarHistorialAcademicoAdmin(HistorialAcademico historial) {
        this.historial = historial;
        initGUI();
        initEventos();
        cargarEstudiantes();
        cargarCursos();
        cargarDatosHistorial();
    }

    private void initGUI() {
        setTitle("Actualizar Historial Académico");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(600, 450);
        setLocationRelativeTo(null);

        panel = this.getContentPane();
        panel.setBackground(new Color(251, 234, 230));
        gLayout = new GridBagLayout();
        gbc = new GridBagConstraints();
        panel.setLayout(gLayout);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10);

        JLabel titulo = new JLabel("Actualizar Historial Académico", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 22));
        titulo.setForeground(new Color(70, 70, 70));
        titulo.setBorder(BorderFactory.createEmptyBorder(10, 0, 20, 0));
        gbc.gridwidth = 2;
        agregarComponente(titulo, 0, 0);
        gbc.gridwidth = 1;

        customizeComboBox(cmbEstudiante);
        customizeComboBox(cmbCurso);
        setBordeNaranja(txtNotaFinal);
        setBordeNaranja(txtComentarios);
        EspaciadoEnDatePicker(dateAprobacion);

        agregarComponente(lblEstudiante, 1, 0);
        agregarComponente(cmbEstudiante, 1, 1);

        agregarComponente(lblCurso, 2, 0);
        agregarComponente(cmbCurso, 2, 1);

        agregarComponente(lblNotaFinal, 3, 0);
        agregarComponente(txtNotaFinal, 3, 1);

        agregarComponente(lblFechaAprobacion, 4, 0);
        agregarComponente(dateAprobacion, 4, 1);

        agregarComponente(lblComentarios, 5, 0);
        agregarComponente(txtComentarios, 5, 1);

        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        panelBotones.setBackground(new Color(251, 234, 230));
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

    private void cargarDatosHistorial() {
        cmbEstudiante.setSelectedItem(historial.getEstudiante());
        cmbCurso.setSelectedItem(historial.getCurso());
        txtNotaFinal.setText(String.valueOf(historial.getNotaFinal()));
        dateAprobacion.setDate(historial.getFechaAprobacion().toLocalDate());
        txtComentarios.setText(historial.getComentarios());
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
                    cmbCurso.getSelectedItem() == null ||
                    txtNotaFinal.getText().trim().isEmpty() ||
                    dateAprobacion.getDate() == null ||
                    txtComentarios.getText().trim().isEmpty()) {

                JOptionPane.showMessageDialog(null, "Todos los campos obligatorios deben ser completados.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            try {
                historial.setEstudiante((Estudiantes) cmbEstudiante.getSelectedItem());
                historial.setCurso((Cursos) cmbCurso.getSelectedItem());
                historial.setNotaFinal(Double.parseDouble(txtNotaFinal.getText().trim()));
                historial.setFechaAprobacion(Date.valueOf(dateAprobacion.getDate()));
                historial.setComentarios(txtComentarios.getText().trim());

                Controlador.actualizarControladorHistorialAcademico(historial);
                Controlador.actualizarListaHistorialAcademico();

                VistaPrincipalAdmin vistaPrincipal = (VistaPrincipalAdmin) VistaPrincipalAdmin.getVistaPrincipal();
                vistaPrincipal.mostrarVistaHistorialAcademico();

                JOptionPane.showMessageDialog(null, "Historial académico actualizado correctamente.");
                dispose();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "La calificación debe ser un número válido.", "Error", JOptionPane.ERROR_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Error al actualizar historial académico.", "Error", JOptionPane.ERROR_MESSAGE);
                Controlador.rollback();
            }
        });
    }

    private void cargarEstudiantes() {
        List<Estudiantes> estudiantes = Controlador.getListaEstudiantes();
        cmbEstudiante.removeAllItems();
        for (Estudiantes e : estudiantes) {
            cmbEstudiante.addItem(e);
        }
    }

    private void cargarCursos() {
        List<Cursos> cursos = Controlador.getListaCursos();
        cmbCurso.removeAllItems();
        for (Cursos c : cursos) {
            cmbCurso.addItem(c);
        }
    }
}
