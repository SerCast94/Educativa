package Vista.Admin.Anadir;

import Controlador.Controlador;
import Mapeo.Asignaturas;
import Mapeo.Cursos;
import Mapeo.Estudiantes;
import Mapeo.HistorialAcademico;
import Vista.Admin.VistaPrincipalAdmin;
import Vista.Util.Boton;
import Vista.Util.CustomDatePicker;
import Vista.Util.CustomDialog;

import javax.swing.*;
import java.awt.*;
import java.math.BigDecimal;
import java.util.List;

import static Vista.Util.EstiloComponentes.*;

public class FormularioHistorialAcademicoAdmin extends JFrame {
    private Container panel;
    private GridBagLayout gLayout;
    private GridBagConstraints gbc;

    private JLabel lblEstudiante = new JLabel("Estudiante:");
    private JLabel lblCurso = new JLabel("Curso:");
    private JLabel lblNotaFinal = new JLabel("Nota Final:");
    private JLabel lblFechaAprobacion = new JLabel("Fecha Aprobación:");
    private JLabel lblComentarios = new JLabel("Comentarios:");

    private JComboBox<Estudiantes> cmbEstudiante = new JComboBox<>();
    private JComboBox<Cursos> cmbAsignaturas = new JComboBox<>();
    private JTextField txtNotaFinal = crearTextField();
    private CustomDatePicker dateAprobacion = new CustomDatePicker();
    private JTextField txtComentarios = crearTextField();

    private JButton btnAceptar = new Boton("Aceptar", Boton.ButtonType.PRIMARY);
    private JButton btnCancelar = new Boton("Cancelar", Boton.ButtonType.DELETE);

    public FormularioHistorialAcademicoAdmin() {
        initGUI();
        initEventos();
        cargarEstudiantes();
        cargarAsignaturas();
    }

    private void initGUI() {
        setTitle("Agregar Historial Académico");
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

        JLabel titulo = new JLabel("Agregar Historial Académico", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 22));
        titulo.setForeground(new Color(70, 70, 70));
        titulo.setBorder(BorderFactory.createEmptyBorder(10, 0, 20, 0));
        gbc.gridwidth = 2;
        agregarComponente(titulo, 0, 0);
        gbc.gridwidth = 1;

        customizeComboBox(cmbEstudiante);
        customizeComboBox(cmbAsignaturas);
        setBordeNaranja(txtNotaFinal);
        setBordeNaranja(txtComentarios);

        EspaciadoEnDatePicker(dateAprobacion);

        agregarComponente(lblEstudiante, 1, 0);
        agregarComponente(cmbEstudiante, 1, 1);

        agregarComponente(lblCurso, 2, 0);
        agregarComponente(cmbAsignaturas, 2, 1);

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

    private void agregarComponente(Component componente, int fila, int columna) {
        gbc.gridx = columna;
        gbc.gridy = fila;
        panel.add(componente, gbc);
    }

    private void initEventos() {
        btnCancelar.addActionListener(e -> dispose());

        btnAceptar.addActionListener(e -> {
            if (cmbEstudiante.getSelectedItem() == null ||
                    cmbAsignaturas.getSelectedItem() == null ||
                    txtNotaFinal.getText().trim().isEmpty() ||
                    dateAprobacion.getText().trim().isEmpty() ||
                    txtComentarios.getText().trim().isEmpty()) {

                new CustomDialog(null,"Error", "Todos los campos son obligatorios.","ONLY_OK").setVisible(true);
                return;
            }

            try {
                HistorialAcademico nuevoHistorial = new HistorialAcademico(
                        (Estudiantes) cmbEstudiante.getSelectedItem(),
                        (Asignaturas) cmbAsignaturas.getSelectedItem(),
                        new BigDecimal(txtNotaFinal.getText().trim()),
                        java.sql.Date.valueOf(dateAprobacion.getDate()),
                        txtComentarios.getText().trim()
                );

                Controlador.insertarControladorHistorialAcademico(nuevoHistorial);
                Controlador.actualizarListaHistorialAcademico();

                VistaPrincipalAdmin vistaPrincipal = (VistaPrincipalAdmin) VistaPrincipalAdmin.getVistaPrincipal();
                vistaPrincipal.mostrarVistaHistorialAcademico();

                new CustomDialog(null,"Éxito", "Historial académico registrado correctamente.","ONLY_OK").setVisible(true);
                dispose();
            } catch (NumberFormatException ex) {
                new CustomDialog(null,"Error", "La calificación debe ser un número válido.","ONLY_OK").setVisible(true);
            } catch (Exception ex) {
                new CustomDialog(null,"Error", "Error al registrar el historial académico.","ONLY_OK").setVisible(true);
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

    private void cargarAsignaturas() {
        List<Cursos> cursos = Controlador.getListaCursos();
        cmbAsignaturas.removeAllItems();
        for (Cursos c : cursos) {
            cmbAsignaturas.addItem(c);
        }
    }
}
