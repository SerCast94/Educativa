package Vista.Admin.Anadir;

import Controlador.Controlador;
import Mapeo.Asistencia;
import Mapeo.Cursos;
import Mapeo.Estudiantes;
import Vista.Admin.VistaPrincipalAdmin;
import Vista.Util.CustomDatePicker;
import Vista.Util.Boton;
import Vista.Util.CustomDialog;
import javax.swing.*;
import java.awt.*;
import java.sql.Date;
import java.util.List;
import static Controlador.Controlador.actualizarListaAsistencia;
import static Controlador.Controlador.insertarControladorAsistencia;
import static Vista.Util.EstiloComponentes.*;

/**
 * Clase que representa el formulario para registrar asistencias.
 * Permite al administrador seleccionar un estudiante, un curso, una fecha y registrar la justificación de la ausencia.
 */
public class FormularioAsistenciaAdmin extends JFrame {
    private Container panel;
    private GridBagLayout gLayout;
    private GridBagConstraints gbc;
    private JLabel titulo;
    private JPanel panelBotones;
    private JButton btnAceptar = new Boton("Aceptar", Boton.tipoBoton.PRIMARY);
    private JButton btnCancelar = new Boton("Cancelar", Boton.tipoBoton.DELETE);
    private JLabel lblEstudiante = new JLabel("Estudiante: ");
    private JLabel lblCurso = new JLabel("Curso: ");
    private JLabel lblFecha = new JLabel("Fecha: ");
    private JLabel lblJustificado = new JLabel("Justificado: ");
    private JLabel lblMotivoAusencia = new JLabel("Motivo de Ausencia: ");
    private JComboBox<Estudiantes> cmbEstudiante = new JComboBox<>();
    private JComboBox<Cursos> cmbCurso = new JComboBox<>();
    private CustomDatePicker datePicker = new CustomDatePicker();
    private JCheckBox chkJustificado = new JCheckBox("  Justificado");
    private JTextField txtMotivoAusencia = crearTextField();

    /**
     * Constructor de la clase FormularioAsistenciaAdmin.
     * Inicializa la interfaz gráfica, los eventos y carga las listas de estudiantes y cursos.
     */
    public FormularioAsistenciaAdmin() {
        initGUI();
        initEventos();
        cargarEstudiantes();
        cargarCursos();
    }

    /**
     * Método para inicializar los componentes gráficos principales.
     */
    private void initGUI() {
        setTitle("Agregar Asistencia");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        establecerIcono(this);
        setSize(600, 400);
        setLocationRelativeTo(null);

        panel = this.getContentPane();
        panel.setBackground(new Color(251, 234, 230));
        gLayout = new GridBagLayout();
        gbc = new GridBagConstraints();
        panel.setLayout(gLayout);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);

        titulo = new JLabel("Agregar Asistencia", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 24));
        titulo.setForeground(new Color(70, 70, 70));
        titulo.setBorder(BorderFactory.createEmptyBorder(10, 0, 20, 0));
        gbc.gridwidth = 2;
        agregarComponente(titulo, 0, 0);
        gbc.gridwidth = 1;

        personalizarComboBox(cmbEstudiante);
        personalizarComboBox(cmbCurso);
        EspaciadoEnDatePicker(datePicker);
        setBordeNaranja(txtMotivoAusencia);

        agregarComponente(lblEstudiante, 1, 0);
        agregarComponente(cmbEstudiante, 1, 1);

        agregarComponente(lblCurso, 2, 0);
        agregarComponente(cmbCurso, 2, 1);

        agregarComponente(lblFecha, 3, 0);
        agregarComponente(datePicker, 3, 1);

        checkPersonalizadoNaranja(chkJustificado);
        chkJustificado.setBackground(new Color(251, 234, 230));


        agregarComponente(lblJustificado, 4, 0);
        agregarComponente(chkJustificado, 4, 1);

        agregarComponente(lblMotivoAusencia, 5, 0);
        agregarComponente(txtMotivoAusencia, 5, 1);

        panelBotones = new JPanel();
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

        btnAceptar.addActionListener(e -> insertarAsistenciaValida());

    }

    /**
     * Método para cargar la lista de estudiantes en el combo box.
     */
    private void cargarEstudiantes() {
        List<Estudiantes> estudiantes = Controlador.getListaEstudiantes();
        cmbEstudiante.removeAllItems();
        for (Estudiantes e : estudiantes) {
            cmbEstudiante.addItem(e);
        }
    }

    /**
     * Método para cargar la lista de cursos en el combo box.
     */
    private void cargarCursos() {
        List<Cursos> cursos = Controlador.getListaCursos();
        cmbCurso.removeAllItems();
        for (Cursos c : cursos) {
            cmbCurso.addItem(c);
        }
    }

    /**
     * Método que valida los campos e inserta un nuevo registro de asistencia.
     */
    private void insertarAsistenciaValida(){
        if (cmbEstudiante.getSelectedItem() == null ||
            cmbCurso.getSelectedItem() == null ||
            datePicker.getDate() == null){
            new CustomDialog(null,"Error", "Todos los campos son obligatorios.","ONLY_OK").setVisible(true);
            return;
        }

        if (txtMotivoAusencia.getText().trim().length() > 255) {
            new CustomDialog(null,"Error", "El motivo de ausencia no puede exceder los 255 caracteres.","ONLY_OK").setVisible(true);
            return;
        }

        Asistencia nuevaAsistencia = new Asistencia(
                (Estudiantes) cmbEstudiante.getSelectedItem(),
                (Cursos) cmbCurso.getSelectedItem(),
                Date.valueOf(datePicker.getDate()),
                (Boolean) chkJustificado.isSelected(),
                txtMotivoAusencia.getText().trim()
        );
        try {
            insertarControladorAsistencia(nuevaAsistencia);
            actualizarListaAsistencia();

            VistaPrincipalAdmin vistaPrincipal = (VistaPrincipalAdmin) VistaPrincipalAdmin.getVistaPrincipal();
            vistaPrincipal.mostrarVistaAsistencia();

            new CustomDialog(null,"Éxito", "Asistencia registrada correctamente.","ONLY_OK").setVisible(true);
            dispose();
        } catch (Exception ex) {
            new CustomDialog(null,"Error", "Error al registrar la asistencia.","ONLY_OK").setVisible(true);
            Controlador.rollback();
        }
    }
}
