package Vista.Admin.Anadir;

import Controlador.Controlador;
import Mapeo.Asignaturas;
import Mapeo.Convalidaciones;
import Mapeo.Convalidaciones.EstadoConvalidacion;
import Mapeo.Estudiantes;
import Vista.Admin.VistaPrincipalAdmin;
import Vista.Util.CustomDatePicker;
import Vista.Util.Boton;
import Vista.Util.CustomDialog;
import javax.swing.*;
import java.awt.*;
import java.util.List;
import static Vista.Util.EstiloComponentes.*;

/**
 * Clase que representa el formulario para agregar nuevas convalidaciónes.
 * Permite seleccionar ingresar los datos de una nueva convalidación.
 */
public class FormularioConvalidacionesAdmin extends JFrame {
    private Container panel;
    private GridBagLayout gLayout;
    private GridBagConstraints gbc;
    private JLabel titulo;
    private JPanel panelBotones;
    private JButton btnAceptar = new Boton("Aceptar", Boton.tipoBoton.PRIMARY);
    private JButton btnCancelar = new Boton("Cancelar", Boton.tipoBoton.DELETE);
    private JLabel lblEstudiante = new JLabel("Estudiante: ");
    private JLabel lblCursoOriginal = new JLabel("Asignatura Original: ");
    private JLabel lblFecha = new JLabel("Fecha de Convalidación: ");
    private JLabel lblEstado = new JLabel("Estado: ");
    private JLabel lblComentarios = new JLabel("Comentarios: ");
    private JComboBox<Estudiantes> cmbEstudiante = new JComboBox<>();
    private JComboBox<Asignaturas> cmbAsignaturaOriginal = new JComboBox<>();
    private JComboBox<String> cmbEstado = new JComboBox<>(new String[]{"Aprobada", "Pendiente", "Rechazada"});
    private JTextField txtComentarios = crearTextField();
    private CustomDatePicker datePickerConvalidacion = new CustomDatePicker();

    /**
     * Constructor de la clase FormularioConvalidacionesAdmin.
     * Inicializa la interfaz gráfica, los eventos y carga la lista de estudiantes y asignaturas.
     */
    public FormularioConvalidacionesAdmin() {
        initGUI();
        cargarEstudiantes();
        cargarAsignaturas();
        initEventos();
    }

    /*
     * Método para inicializar los componentes gráficos principales.
     */
    private void initGUI() {
        setTitle("Agregar Convalidación");
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

        titulo = new JLabel("Agregar Convalidación", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 24));
        titulo.setBorder(BorderFactory.createEmptyBorder(10, 0, 20, 0));
        titulo.setForeground(new Color(70, 70, 70));
        gbc.gridwidth = 2;
        agregarComponente(titulo, 0, 0);
        gbc.gridwidth = 1;

        personalizarComboBox(cmbEstudiante);
        personalizarComboBox(cmbAsignaturaOriginal);
        personalizarComboBox(cmbEstado);

        agregarComponente(lblEstudiante, 1, 0);
        agregarComponente(cmbEstudiante, 1, 1);

        agregarComponente(lblCursoOriginal, 2, 0);
        agregarComponente(cmbAsignaturaOriginal, 2, 1);

        agregarComponente(lblFecha, 3, 0);
        EspaciadoEnDatePicker(datePickerConvalidacion);
        agregarComponente(datePickerConvalidacion, 3, 1);

        agregarComponente(lblEstado, 4, 0);
        agregarComponente(cmbEstado, 4, 1);

        agregarComponente(lblComentarios, 5, 0);
        agregarComponente(txtComentarios, 5, 1);

        panelBotones = new JPanel();
        panelBotones.setBackground(new Color(251, 234, 230));
        panelBotones.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        panelBotones.add(btnAceptar);
        panelBotones.add(btnCancelar);

        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 2;
        panel.add(panelBotones, gbc);

        setVisible(true);
    }

    /**
     * Método para inicializar los eventos de los botones.
     */
    private void initEventos() {
        btnCancelar.addActionListener(e -> dispose());

        btnAceptar.addActionListener(e -> insertarConvalidacionValida());
    }

    /**
     * Método para agregar un componente al panel con GridBagLayout.
     *
     * @param componente Componente a agregar.
     * @param fila       Fila en la que se agregará.
     * @param columna    Columna en la que se agregará.
     */
    private void agregarComponente(Component componente, int fila, int columna) {
        gbc.gridx = columna;
        gbc.gridy = fila;
        panel.add(componente, gbc);
    }

    /**
     * Método para cargar la lista de estudiantes en el combo box.
     */
    private void cargarEstudiantes() {
        List<Estudiantes> estudiantes = Controlador.getListaEstudiantes();
        cmbEstudiante.removeAllItems();
        for (Estudiantes est : estudiantes) {
            cmbEstudiante.addItem(est);
        }
    }

    /**
     * Método para cargar la lista de asignaturas en el combo box.
     */
    private void cargarAsignaturas(){
        List<Asignaturas> asignaturas = Controlador.getListaAsignaturas();
        cmbAsignaturaOriginal.removeAllItems();
        for (Asignaturas asignatura : asignaturas) {
            cmbAsignaturaOriginal.addItem(asignatura);
        }
    }

    /**
     * Método que valida los campos e inserta una nueva convalidación.
     */
    private void insertarConvalidacionValida(){

        if (cmbEstudiante.getSelectedItem() == null ||
                cmbAsignaturaOriginal.getSelectedItem() == null ||
                datePickerConvalidacion.getDate() == null ||
                cmbEstado.getSelectedItem() == null) {

            new CustomDialog(null,"Error", "Todos los campos son obligatorios.","ONLY_OK").setVisible(true);
            return;
        }
        if (txtComentarios.getText().length() > 255) {
            new CustomDialog(null,"Error", "Los comentarios no pueden exceder los 255 caracteres.","ONLY_OK").setVisible(true);
            return;
        }
        if (datePickerConvalidacion.getDate().isAfter(java.time.LocalDate.now())) {
            new CustomDialog(null,"Error", "La fecha de convalidación no puede ser futura.","ONLY_OK").setVisible(true);
            return;
        }

        try {
            Convalidaciones nuevaConvalidacion = new Convalidaciones(
                    (Estudiantes) cmbEstudiante.getSelectedItem(),
                    (Asignaturas) cmbAsignaturaOriginal.getSelectedItem(),
                    java.sql.Date.valueOf(datePickerConvalidacion.getDate()),
                    EstadoConvalidacion.valueOf(cmbEstado.getSelectedItem().toString()),
                    txtComentarios.getText().trim()
            );

            Controlador.insertarControladorConvalidacion(nuevaConvalidacion);
            Controlador.actualizarListaConvalidaciones();

            VistaPrincipalAdmin vistaPrincipal = (VistaPrincipalAdmin) VistaPrincipalAdmin.getVistaPrincipal();
            vistaPrincipal.mostrarVistaConvalidaciones();

            new CustomDialog(null,"Éxito", "Convalidación registrada correctamente.","ONLY_OK").setVisible(true);
            dispose();
        } catch (IllegalArgumentException ex) {
            new CustomDialog(null,"Error", "El estado seleccionado no es válido.","ONLY_OK").setVisible(true);
        } catch (Exception ex) {
            new CustomDialog(null,"Error", "Error al registrar la convalidación: " + ex.getMessage(),"ONLY_OK").setVisible(true);
            Controlador.rollback();
        }
    }
}