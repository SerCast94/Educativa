package Vista.Admin.Modificar;

import Controlador.Controlador;
import Mapeo.Asignaturas;
import Mapeo.Convalidaciones;
import Mapeo.Estudiantes;
import Vista.Admin.VistaPrincipalAdmin;
import Vista.Util.Boton;
import Vista.Util.CustomDatePicker;
import Vista.Util.CustomDialog;
import javax.swing.*;
import java.awt.*;
import java.sql.Date;
import java.util.List;

import static Vista.Util.EstiloComponentes.*;

/**
 * Clase para actualizar los datos de una convalidación en la interfaz de administración.
 */
public class ActualizarConvalidacionesAdmin extends JFrame {
    private Container panel;
    private GridBagLayout gLayout;
    private GridBagConstraints gbc;
    private JLabel titulo;
    private JPanel panelBotones;
    private JButton btnAceptar = new Boton("Actualizar", Boton.tipoBoton.PRIMARY);
    private JButton btnCancelar = new Boton("Cancelar", Boton.tipoBoton.DELETE);
    private JLabel lblEstudiante = new JLabel("Estudiante: ");
    private JLabel lblAsignaturaOriginal = new JLabel("Asignatura Original: ");
    private JLabel lblFecha = new JLabel("Fecha de Convalidación: ");
    private JLabel lblEstado = new JLabel("Estado: ");
    private JLabel lblComentarios = new JLabel("Comentarios: ");
    private JComboBox<Estudiantes> cmbEstudiante = new JComboBox<>();
    private JComboBox<Asignaturas> cmbAsignaturaOriginal = new JComboBox<>();
    private JComboBox<String> cmbEstado = new JComboBox<>(new String[]{"Aprobada", "Pendiente", "Rechazada"});
    private JTextField txtComentarios = crearTextField();
    private CustomDatePicker datePickerConvalidacion = new CustomDatePicker();
    private Convalidaciones convalidacion;

    /**
     * Constructor de la clase ActualizarConvalidacionesAdmin.
     * Inicializa la interfaz gráfica y carga los datos de la convalidación.
     * @param convalidacion Convalidación a actualizar.
     */
    public ActualizarConvalidacionesAdmin(Convalidaciones convalidacion) {
        this.convalidacion = convalidacion;
        initGUI();
        initEventos();
        cargarEstudiantes();
        cargarAsignaturas();
        cargarDatosConvalidacion();
    }

    /**
     * Método para inicializar los componentes gráficos principales.
     */
    private void initGUI() {
        setTitle("Actualizar Convalidación");
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

        titulo = new JLabel("Actualizar Convalidación", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 24));
        titulo.setBorder(BorderFactory.createEmptyBorder(10, 0, 20, 0));
        titulo.setForeground(new Color(70, 70, 70));
        gbc.gridwidth = 2;
        agregarComponente(titulo, 0, 0);
        gbc.gridwidth = 1;

        personalizarComboBox(cmbEstudiante);
        cmbEstudiante.setPreferredSize(new Dimension(200,cmbEstudiante.getPreferredSize().height));
        personalizarComboBox(cmbAsignaturaOriginal);
        cmbAsignaturaOriginal.setPreferredSize(new Dimension(200,cmbAsignaturaOriginal.getPreferredSize().height));
        personalizarComboBox(cmbEstado);
        cmbEstado.setPreferredSize(new Dimension(200,cmbEstado.getPreferredSize().height));

        setBordeNaranja(txtComentarios);
        txtComentarios.setPreferredSize(new Dimension(200, txtComentarios.getPreferredSize().height));

        EspaciadoEnDatePicker(datePickerConvalidacion);
        datePickerConvalidacion.setPreferredSize(new Dimension(200, datePickerConvalidacion.getPreferredSize().height));

        agregarComponente(lblEstudiante, 1, 0);
        agregarComponente(cmbEstudiante, 1, 1);

        agregarComponente(lblAsignaturaOriginal, 2, 0);
        agregarComponente(cmbAsignaturaOriginal, 2, 1);

        agregarComponente(lblFecha, 3, 0);
        agregarComponente(datePickerConvalidacion, 3, 1);

        agregarComponente(lblEstado, 4, 0);
        agregarComponente(cmbEstado, 4, 1);

        agregarComponente(lblComentarios, 5, 0);
        agregarComponente(txtComentarios, 5, 1);

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
        btnAceptar.addActionListener(e -> actualizarConvalidacionValida());
    }

    /**
     * Carga los datos de la convalidación en los campos de la interfaz.
     */
    private void cargarDatosConvalidacion() {
        cmbEstudiante.setSelectedItem(convalidacion.getEstudiante());
        cmbAsignaturaOriginal.setSelectedItem(convalidacion.getAsignaturaOriginal());
        if (convalidacion.getFechaConvalidacion() != null) {
            datePickerConvalidacion.setDate(convalidacion.getFechaConvalidacion().toLocalDate());
        }
        cmbEstado.setSelectedItem(convalidacion.getEstadoConvalidacion().name());
        txtComentarios.setText(convalidacion.getComentarios());
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
     * Método para cargar las asignaturas en el combo box.
     */
    private void cargarAsignaturas() {
        List<Asignaturas> asignaturas = Controlador.getListaAsignaturas();
        cmbAsignaturaOriginal.removeAllItems();
        for (Asignaturas asignatura : asignaturas) {
            cmbAsignaturaOriginal.addItem(asignatura);
        }
    }

    /**
     * Método para validar y actualizar los datos de la convalidación.
     */
    private void actualizarConvalidacionValida() {
        if (cmbEstudiante.getSelectedItem() == null ||
                cmbAsignaturaOriginal.getSelectedItem() == null ||
                datePickerConvalidacion.getDate() == null ||
                cmbEstado.getSelectedItem() == null) {

            new CustomDialog(null, "Error", "Todos los campos son obligatorios.", "ONLY_OK").setVisible(true);
            return;
        }

        if (txtComentarios.getText().length() > 255) {
            new CustomDialog(null, "Error", "Los comentarios no pueden exceder los 255 caracteres.", "ONLY_OK").setVisible(true);
            return;
        }

        if (datePickerConvalidacion.getDate().isAfter(java.time.LocalDate.now())) {
            new CustomDialog(null, "Error", "La fecha de convalidación no puede ser futura.", "ONLY_OK").setVisible(true);
            return;
        }

        try {
            convalidacion.setEstudiante((Estudiantes) cmbEstudiante.getSelectedItem());
            convalidacion.setAsignaturaOriginal((Asignaturas) cmbAsignaturaOriginal.getSelectedItem());
            convalidacion.setFechaConvalidacion(Date.valueOf(datePickerConvalidacion.getDate()));
            convalidacion.setEstadoConvalidacion(Convalidaciones.EstadoConvalidacion.valueOf(cmbEstado.getSelectedItem().toString()));
            convalidacion.setComentarios(txtComentarios.getText().trim());

            Controlador.actualizarControladorConvalidacion(convalidacion);
            Controlador.actualizarListaConvalidaciones();

            VistaPrincipalAdmin vistaPrincipal = (VistaPrincipalAdmin) VistaPrincipalAdmin.getVistaPrincipal();
            vistaPrincipal.mostrarVistaConvalidaciones();

            new CustomDialog(null, "Éxito", "Convalidación actualizada correctamente.", "ONLY_OK").setVisible(true);
            dispose();
        } catch (IllegalArgumentException ex) {
            new CustomDialog(null, "Error", "El estado seleccionado no es válido.", "ONLY_OK").setVisible(true);
        } catch (Exception ex) {
            new CustomDialog(null, "Error", "Error al actualizar la convalidación: " + ex.getMessage(), "ONLY_OK").setVisible(true);
            Controlador.rollback();
        }
    }
}
