package Vista.Admin.Anadir;

import Controlador.Controlador;
import Mapeo.Asignaturas;
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

/**
 * Clase que representa el formulario para agregar un historial académico.
 * Permite al administrador ingresar los datos de un historial académico.
 */
public class FormularioHistorialAcademicoAdmin extends JFrame {
    private Container panel;
    private GridBagLayout gLayout;
    private GridBagConstraints gbc;
    private JLabel titulo;
    private JPanel panelBotones;
    private JLabel lblEstudiante = new JLabel("Estudiante:");
    private JLabel lblAsignatura = new JLabel("Asignatura:");
    private JLabel lblNotaFinal = new JLabel("Nota Final:");
    private JLabel lblFechaAprobacion = new JLabel("Fecha:");
    private JLabel lblComentarios = new JLabel("Comentarios:");
    private JComboBox<Estudiantes> cmbEstudiante = new JComboBox<>();
    private JComboBox<Asignaturas> cmbAsignaturas = new JComboBox<>();
    private JTextField txtNotaFinal = crearTextField();
    private CustomDatePicker dateAprobacion = new CustomDatePicker();
    private JTextField txtComentarios = crearTextField();
    private JButton btnAceptar = new Boton("Aceptar", Boton.tipoBoton.PRIMARY);
    private JButton btnCancelar = new Boton("Cancelar", Boton.tipoBoton.DELETE);

    /**
     * Constructor de la clase FormularioHistorialAcademicoAdmin.
     * Inicializa la interfaz gráfica, los eventos y carga la lista de estudiantes y asignaturas.
     */
    public FormularioHistorialAcademicoAdmin() {
        initGUI();
        initEventos();
        cargarEstudiantes();
        cargarAsignaturas();
    }

    /**
     * Método para inicializar los componentes gráficos principales.
     */
    private void initGUI() {
        setTitle("Agregar Historial Académico");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        establecerIcono(this);
        setSize(600, 450);
        setLocationRelativeTo(null);

        panel = this.getContentPane();
        panel.setBackground(new Color(251, 234, 230));
        gLayout = new GridBagLayout();
        gbc = new GridBagConstraints();
        panel.setLayout(gLayout);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10);

        titulo = new JLabel("Agregar Historial Académico", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 22));
        titulo.setForeground(new Color(70, 70, 70));
        titulo.setBorder(BorderFactory.createEmptyBorder(10, 0, 20, 0));
        gbc.gridwidth = 2;
        agregarComponente(titulo, 0, 0);
        gbc.gridwidth = 1;

        personalizarComboBox(cmbEstudiante);
        personalizarComboBox(cmbAsignaturas);
        setBordeNaranja(txtNotaFinal);
        setBordeNaranja(txtComentarios);

        EspaciadoEnDatePicker(dateAprobacion);

        agregarComponente(lblEstudiante, 1, 0);
        agregarComponente(cmbEstudiante, 1, 1);

        agregarComponente(lblAsignatura, 2, 0);
        agregarComponente(cmbAsignaturas, 2, 1);

        agregarComponente(lblNotaFinal, 3, 0);
        agregarComponente(txtNotaFinal, 3, 1);

        agregarComponente(lblFechaAprobacion, 4, 0);
        agregarComponente(dateAprobacion, 4, 1);

        agregarComponente(lblComentarios, 5, 0);
        agregarComponente(txtComentarios, 5, 1);

        panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
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

    /**
     * Método para agregar un componente al panel principal con las restricciones de diseño.
     * @param componente El componente a agregar.
     * @param fila La fila donde se agregará.
     * @param columna La columna donde se agregará.
     */
    private void agregarComponente(Component componente, int fila, int columna) {
        gbc.gridx = columna;
        gbc.gridy = fila;
        panel.add(componente, gbc);
    }

    /**
     * Método para inicializar los eventos de los botones.
     */
    private void initEventos() {
        btnCancelar.addActionListener(e -> dispose());

        btnAceptar.addActionListener(e -> insertarHistorialAcademicoValido());
    }

    /**
     * Método para carlar la lista de estudiantes en el combo box.
     */
    private void cargarEstudiantes() {
        List<Estudiantes> estudiantes = Controlador.getListaEstudiantes();
        cmbEstudiante.removeAllItems();
        for (Estudiantes e : estudiantes) {
            cmbEstudiante.addItem(e);
        }
    }

    /**
     * Método para carlar la lista de asignaturas en el combo box.
     */
    private void cargarAsignaturas() {
        List<Asignaturas> asignaturas = Controlador.getListaAsignaturas();
        cmbAsignaturas.removeAllItems();
        for (Asignaturas a : asignaturas) {
            cmbAsignaturas.addItem(a);
        }
    }

    /**
     * Método que valida los campos e inserta un nuevo historial académico.
     */
    private void insertarHistorialAcademicoValido(){

        if (cmbEstudiante.getSelectedItem() == null ||
                cmbAsignaturas.getSelectedItem() == null ||
                txtNotaFinal.getText().trim().isEmpty() ||
                dateAprobacion.getText().trim().isEmpty() ||
                txtComentarios.getText().trim().isEmpty()) {

            new CustomDialog(null,"Error", "Todos los campos son obligatorios.","ONLY_OK").setVisible(true);
            return;
        }

        try {
            BigDecimal notaFinal = new BigDecimal(txtNotaFinal.getText().trim());
            if (notaFinal.compareTo(BigDecimal.ZERO) < 0 || notaFinal.compareTo(new BigDecimal("10")) > 0) {
                new CustomDialog(null, "Error", "La calificación debe estar entre 0 y 10.", "ONLY_OK").setVisible(true);
                return;
            }
        } catch (NumberFormatException ex) {
            new CustomDialog(null, "Error", "La calificación debe ser un número válido.", "ONLY_OK").setVisible(true);
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
    }
}