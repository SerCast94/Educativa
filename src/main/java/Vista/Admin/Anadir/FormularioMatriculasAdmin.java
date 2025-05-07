package Vista.Admin.Anadir;

import Controlador.Controlador;
import Mapeo.Cursos;
import Mapeo.Estudiantes;
import Mapeo.Matriculas;
import Vista.Admin.VistaPrincipalAdmin;
import Vista.Util.Boton;
import Vista.Util.CustomDatePicker;
import Vista.Util.CustomDialog;
import javax.swing.*;
import java.awt.*;
import java.sql.Date;
import java.util.List;
import static Controlador.Controlador.actualizarListaMatriculas;
import static Controlador.Controlador.insertarControladorMatricula;
import static Vista.Util.EstiloComponentes.*;

/**
 * Clase que representa el formulario para registrar nuevas matrículas.
 * Permite al administrador ingresar los datos de una matrícula.
 */
public class FormularioMatriculasAdmin extends JFrame {
    private Container panel;
    private GridBagLayout gLayout;
    private GridBagConstraints gbc;
    private JLabel titulo;
    private JPanel panelBotones;
    private JLabel lblEstudiante = new JLabel("Estudiante:");
    private JLabel lblCurso = new JLabel("Curso:");
    private JLabel lblFechaMatricula = new JLabel("Fecha de Matrícula:");
    private JLabel lblEstado = new JLabel("Estado:");
    private JComboBox<Estudiantes> cmbEstudiantes = new JComboBox<>();
    private JComboBox<Cursos> cmbCursos = new JComboBox<>();
    private CustomDatePicker datePickerMatricula = new CustomDatePicker();
    private JComboBox<String> cmbEstado = new JComboBox<>(new String[]{"activo", "inactivo"});
    private JButton btnAceptar = new Boton("Aceptar", Boton.tipoBoton.PRIMARY);
    private JButton btnCancelar = new Boton("Cancelar", Boton.tipoBoton.DELETE);

    /**
     * Constructor de la clase FormularioMatriculasAdmin.
     * Inicializa la interfaz gráfica, los eventos y carga la lista de estudiantes y cursos.
     */
    public FormularioMatriculasAdmin() {
        initGUI();
        initEventos();
        cargarEstudiantes();
        cargarCursos();
    }

    /**
     * Método para inicializar los componentes gráficos principales.
     */
    private void initGUI() {
        setTitle("Registrar Matrícula");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null);

        panel = this.getContentPane();
        panel.setBackground(new Color(251, 234, 230));
        gLayout = new GridBagLayout();
        gbc = new GridBagConstraints();
        panel.setLayout(gLayout);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10);

        titulo = new JLabel("Registrar Matrícula", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 22));
        titulo.setForeground(new Color(70, 70, 70));
        titulo.setBorder(BorderFactory.createEmptyBorder(10, 0, 20, 0));
        gbc.gridwidth = 2;
        agregarComponente(titulo, 0, 0);
        gbc.gridwidth = 1;

        personalizarComboBox(cmbEstudiantes);
        personalizarComboBox(cmbCursos);
        personalizarComboBox(cmbEstado);
        EspaciadoEnDatePicker(datePickerMatricula);

        agregarComponente(lblEstudiante, 1, 0);
        agregarComponente(cmbEstudiantes, 1, 1);

        agregarComponente(lblCurso, 2, 0);
        agregarComponente(cmbCursos, 2, 1);

        agregarComponente(lblFechaMatricula, 3, 0);
        agregarComponente(datePickerMatricula, 3, 1);

        agregarComponente(lblEstado, 4, 0);
        agregarComponente(cmbEstado, 4, 1);

        panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        panelBotones.setBackground(new Color(251, 234, 230));
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

        btnAceptar.addActionListener(e -> insertarMatriculaValida());
    }

    /**
     * Método para cargar la lista de estudiantes en el combo box.
     */
    private void cargarEstudiantes() {
        List<Estudiantes> estudiantes = Controlador.getListaEstudiantes();
        cmbEstudiantes.removeAllItems();
        for (Estudiantes e : estudiantes) {
            cmbEstudiantes.addItem(e);
        }
    }

    /**
     * Método para cargar la lista de cursos en el combo box.
     */
    private void cargarCursos() {
        List<Cursos> cursos = Controlador.getListaCursos();
        cmbCursos.removeAllItems();
        for (Cursos c : cursos) {
            cmbCursos.addItem(c);
        }
    }

    /**
     * Método que valida los campos e inserta una nueva matricula.
     */
    private void insertarMatriculaValida(){

        if (cmbEstudiantes.getSelectedItem() == null ||
                cmbCursos.getSelectedItem() == null ||
                datePickerMatricula.getDate() == null ||
                cmbEstado.getSelectedItem() == null) {

            new CustomDialog(null,"Error", "Todos los campos son obligatorios.","ONLY_OK").setVisible(true);
            return;
        }

        try {
            Matriculas nuevaMatricula = new Matriculas(
                    (Estudiantes) cmbEstudiantes.getSelectedItem(),
                    (Cursos) cmbCursos.getSelectedItem(),
                    Date.valueOf(datePickerMatricula.getDate()),
                    Matriculas.EstadoMatricula.valueOf(cmbEstado.getSelectedItem().toString())
            );

            insertarControladorMatricula(nuevaMatricula);
            actualizarListaMatriculas();

            VistaPrincipalAdmin vistaPrincipal = (VistaPrincipalAdmin) VistaPrincipalAdmin.getVistaPrincipal();
            vistaPrincipal.mostrarVistaMatriculas();

            new CustomDialog(null,"Éxito", "Matrícula registrada correctamente.","ONLY_OK").setVisible(true);
            dispose();
        } catch (Exception ex) {
            new CustomDialog(null,"Error", "Error al registrar la matrícula: " + ex.getMessage(), "ONLY_OK").setVisible(true);
            Controlador.rollback();
        }
    }
}

