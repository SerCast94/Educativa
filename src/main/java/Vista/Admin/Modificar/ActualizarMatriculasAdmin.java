package Vista.Admin.Modificar;

import Controlador.Controlador;
import Mapeo.Matriculas;
import Mapeo.Cursos;
import Mapeo.Estudiantes;
import Vista.Admin.VistaPrincipalAdmin;
import Vista.Util.Boton;
import javax.swing.*;
import java.awt.*;
import java.util.List;
import static Vista.Util.EstiloComponentes.*;
import Vista.Util.CustomDatePicker;
import Vista.Util.CustomDialog;
import java.sql.Date;

/**
 * Clase para actualizar matrículas desde la vista de administrador.
 */
public class ActualizarMatriculasAdmin extends JFrame {
    private Container panel;
    private GridBagLayout gLayout;
    private GridBagConstraints gbc;
    private JLabel titulo;
    private JPanel panelBotones;
    private JButton btnAceptar = new Boton("Actualizar", Boton.ButtonType.PRIMARY);
    private JButton btnCancelar = new Boton("Cancelar", Boton.ButtonType.DELETE);
    private JLabel lblEstudiante = new JLabel("Estudiante:");
    private JLabel lblCurso = new JLabel("Curso:");
    private JLabel lblEstado = new JLabel("Estado:");
    private JLabel lblFechaMatricula = new JLabel("Fecha de Matrícula:");
    private JComboBox<Estudiantes> cmbEstudiante = new JComboBox<>();
    private JComboBox<Cursos> cmbCurso = new JComboBox<>();
    private JComboBox<String> cmbEstado = new JComboBox<>(new String[]{"activo", "inactivo"});
    private CustomDatePicker datePickerMatricula = new CustomDatePicker();
    private Matriculas matricula;

    /**
     * Constructor de la clase ActualizarMatriculasAdmin.
     * Inicializa la interfaz gráfica y carga los datos de la matrícula.
     * @param matricula Matrícula a actualizar.
     */
    public ActualizarMatriculasAdmin(Matriculas matricula) {
        this.matricula = matricula;
        initGUI();
        initEventos();
        cargarEstudiantes();
        cargarCursos();
        cargarDatosMatricula();
    }

    /**
     * Carga los datos de la matrícula en los campos de texto.
     */
    private void cargarDatosMatricula() {
        cmbEstudiante.setSelectedItem(matricula.getEstudiante());
        cmbCurso.setSelectedItem(matricula.getCurso());
        cmbEstado.setSelectedItem(matricula.getEstado().name());
        datePickerMatricula.setDate(matricula.getFechaMatricula().toLocalDate());
    }

    /**
     * Método para inicializar los componentes gráficos principales.
     */
    private void initGUI() {
        setTitle("Actualizar Matrícula");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null);

        panel = this.getContentPane();
        panel.setBackground(new Color(251, 234, 230));
        gLayout = new GridBagLayout();
        gbc = new GridBagConstraints();
        panel.setLayout(gLayout);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);

        titulo = new JLabel("Actualizar Matrícula", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 24));
        titulo.setBorder(BorderFactory.createEmptyBorder(10, 0, 20, 0));
        titulo.setForeground(new Color(70, 70, 70));
        gbc.gridwidth = 2;
        agregarComponente(titulo, 0, 0);
        gbc.gridwidth = 1;

        customizeComboBox(cmbEstado);
        customizeComboBox(cmbEstudiante);
        customizeComboBox(cmbCurso);
        EspaciadoEnDatePicker(datePickerMatricula);

        agregarComponente(lblEstudiante, 1, 0);
        setBordeNaranja(cmbEstudiante);
        agregarComponente(cmbEstudiante, 1, 1);

        agregarComponente(lblCurso, 2, 0);
        setBordeNaranja(cmbCurso);
        agregarComponente(cmbCurso, 2, 1);

        agregarComponente(lblFechaMatricula, 3, 0);
        agregarComponente(datePickerMatricula, 3, 1);

        agregarComponente(lblEstado, 4, 0);
        setBordeNaranja(cmbEstado);
        agregarComponente(cmbEstado, 4, 1);

        panelBotones = new JPanel();
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

        btnAceptar.addActionListener(e -> actualizarMatriculaValida());
    }

    /**
     * Método para cargar los estudiantes en el combo box.
     */
    private void cargarEstudiantes() {
        List<Estudiantes> estudiantes = Controlador.getListaEstudiantes();
        cmbEstudiante.removeAllItems();
        for (Estudiantes estudiante : estudiantes) {
            cmbEstudiante.addItem(estudiante);
        }
    }

    /**
     * Método para cargar los cursos en el combo box.
     */
    private void cargarCursos() {
        List<Cursos> cursos = Controlador.getListaCursos();
        cmbCurso.removeAllItems();
        for (Cursos curso : cursos) {
            cmbCurso.addItem(curso);
        }
    }

    /**
     * Método para actualizar la matrícula con los datos ingresados.
     */
    private void actualizarMatriculaValida(){

        if (cmbEstudiante.getSelectedItem() == null ||
                cmbCurso.getSelectedItem() == null ||
                datePickerMatricula.getDate() == null ||
                cmbEstado.getSelectedItem() == null) {

            new CustomDialog(null,"Error", "Todos los campos son obligatorios.","ONLY_OK").setVisible(true);
            return;
        }

        try {
            matricula.setEstudiante((Estudiantes) cmbEstudiante.getSelectedItem());
            matricula.setCurso((Cursos) cmbCurso.getSelectedItem());
            matricula.setEstado(Matriculas.EstadoMatricula.valueOf(cmbEstado.getSelectedItem().toString()));
            matricula.setFechaMatricula(Date.valueOf(datePickerMatricula.getDate()));

            Controlador.actualizarControladorMatricula(matricula);
            Controlador.actualizarListaMatriculas();

            VistaPrincipalAdmin vistaPrincipal = (VistaPrincipalAdmin) VistaPrincipalAdmin.getVistaPrincipal();
            vistaPrincipal.mostrarVistaMatriculas();

            new CustomDialog(null,"Éxito", "Matrícula actualizada correctamente.","ONLY_OK").setVisible(true);

            dispose();
        } catch (Exception ex) {
            new CustomDialog(null,"Error", "Error al actualizar matrícula: " + ex.getMessage(), "ONLY_OK").setVisible(true);
            Controlador.rollback();
        }

    }
}