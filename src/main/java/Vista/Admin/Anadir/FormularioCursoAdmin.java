package Vista.Admin.Anadir;

import Controlador.Controlador;
import Mapeo.Cursos;
import Mapeo.Profesores;
import Vista.Admin.VistaPrincipalAdmin;
import Vista.Util.Boton;
import Vista.Util.CustomDialog;
import javax.swing.*;
import java.awt.*;
import java.util.List;
import static Controlador.Controlador.actualizarListaCursos;
import static Controlador.Controlador.insertarControladorCurso;
import static Vista.Util.EstiloComponentes.*;

/**
 * Clase que representa el formulario para agregar un nuevo curso.
 * Permite al administrador ingresar los datos del curso.
 */
public class FormularioCursoAdmin extends JFrame {
    private Container panel;
    private GridBagLayout gLayout;
    private GridBagConstraints gbc;
    private JLabel titulo;
    private JPanel panelBotones;
    private JLabel lblNombre = new JLabel("Nombre:");
    private JLabel lblDescripcion = new JLabel("Descripción:");
    private JLabel lblProfesor = new JLabel("Profesor:");
    private JLabel lblEstado = new JLabel("Estado:");
    private JTextField txtNombre = crearTextField();
    private JTextField txtDescripcion = crearTextField();
    private JComboBox<Profesores> cmbProfesor = new JComboBox<>();
    private JComboBox<String> cmbEstado = new JComboBox<>(new String[]{"activo", "inactivo"});
    private JButton btnAceptar = new Boton("Aceptar", Boton.tipoBoton.PRIMARY);
    private JButton btnCancelar = new Boton("Cancelar", Boton.tipoBoton.DELETE);


    /**
     * Constructor de la clase FormularioCursoAdmin.
     * Inicializa la interfaz gráfica, los eventos y carga la lista de profesores.
     */
    public FormularioCursoAdmin() {
        initGUI();
        initEventos();
        cargarProfesores();
    }

    /**
     * Método para inicializar los componentes gráficos principales.
     */
    private void initGUI() {
        setTitle("Agregar Curso");
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
        gbc.insets = new Insets(10, 10, 10, 10);

        titulo = new JLabel("Agregar Curso", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 22));
        titulo.setForeground(new Color(70, 70, 70));
        titulo.setBorder(BorderFactory.createEmptyBorder(10, 0, 20, 0));
        gbc.gridwidth = 2;
        agregarComponente(titulo, 0, 0);
        gbc.gridwidth = 1;

        personalizarComboBox(cmbProfesor);
        personalizarComboBox(cmbEstado);

        setBordeNaranja(txtNombre);
        setBordeNaranja(txtDescripcion);
        setBordeNaranja(cmbProfesor);
        setBordeNaranja(cmbEstado);

        agregarComponente(lblNombre, 1, 0);
        agregarComponente(txtNombre, 1, 1);

        agregarComponente(lblDescripcion, 2, 0);
        agregarComponente(txtDescripcion, 2, 1);

        agregarComponente(lblProfesor, 3, 0);
        agregarComponente(cmbProfesor, 3, 1);

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
     * Método para agregar un componente al panel con GridBagLayout.
     * @param componente El componente a agregar.
     * @param fila       La fila en la que se agregará.
     * @param columna    La columna en la que se agregará.
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

        btnAceptar.addActionListener(e -> insertarCursoValido());
    }

    /**
     * Método para cargar la lista de profesores en el comb box.
     */
    private void cargarProfesores() {
        List<Profesores> profesores = Controlador.getListaProfesores();
        cmbProfesor.removeAllItems();
        for (Profesores p : profesores) {
            cmbProfesor.addItem(p);
        }
    }

    /**
     * Método que valida los campos e inserta un nuevo curso.
     */
    private void insertarCursoValido(){

        if (txtNombre.getText().trim().isEmpty() ||
                txtDescripcion.getText().trim().isEmpty() ||
                cmbProfesor.getSelectedItem() == null ||
                cmbEstado.getSelectedItem() == null) {

            new CustomDialog(null,"Error", "Todos los campos son obligatorios.","ONLY_OK").setVisible(true);
            return;
        }
        if (txtNombre.getText().length() > 100) {
            new CustomDialog(null,"Error", "El nombre no puede exceder los 100 caracteres.","ONLY_OK").setVisible(true);
            return;
        }
        if (txtDescripcion.getText().length() > 255) {
            new CustomDialog(null,"Error", "La descripción no puede exceder los 255 caracteres.","ONLY_OK").setVisible(true);
            return;
        }

        Cursos nuevoCurso = new Cursos(
                txtNombre.getText().trim(),
                txtDescripcion.getText().trim(),
                (Profesores) cmbProfesor.getSelectedItem(),
                Cursos.EstadoCurso.valueOf(cmbEstado.getSelectedItem().toString())
        );

        try {
            insertarControladorCurso(nuevoCurso);
            actualizarListaCursos();

            VistaPrincipalAdmin vistaPrincipal = (VistaPrincipalAdmin) VistaPrincipalAdmin.getVistaPrincipal();
            vistaPrincipal.mostrarVistaCursos();

            new CustomDialog(null,"Éxito", "Curso registrado correctamente.","ONLY_OK").setVisible(true);
            dispose();
        } catch (Exception ex) {
            new CustomDialog(null,"Error", "Error al registrar el curso: " , "ONLY_OK").setVisible(true);
            Controlador.rollback();
        }
    }
}
