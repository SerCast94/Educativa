package Vista.Admin.Modificar;

import Controlador.Controlador;
import Mapeo.Becas;
import Mapeo.Estudiantes;
import Vista.Admin.VistaPrincipalAdmin;
import Vista.Util.Boton;
import Vista.Util.CustomDatePicker;
import Vista.Util.CustomDialog;
import javax.swing.*;
import java.awt.*;
import java.util.List;
import static Vista.Util.EstiloComponentes.*;

/**
 * Clase para actualizar los datos de una beca en la interfaz de administración.
 */
public class ActualizarBecasAdmin extends JFrame {
    private Container panel;
    private GridBagLayout gLayout;
    private GridBagConstraints gbc;
    private JLabel titulo;
    private JPanel panelBotones;
    private JButton btnAceptar = new Boton("Actualizar", Boton.tipoBoton.PRIMARY);
    private JButton btnCancelar = new Boton("Cancelar", Boton.tipoBoton.DELETE);
    private JLabel lblEstudiante = new JLabel("Estudiante: ");
    private JLabel lblTipoBeca = new JLabel("Tipo de Beca: ");
    private JLabel lblMonto = new JLabel("Monto: ");
    private JLabel lblFechaAsignacion = new JLabel("Fecha de Asignación: ");
    private JLabel lblEstadoBeca = new JLabel("Estado de Beca: ");
    private JLabel lblComentarios = new JLabel("Comentarios: ");
    private JComboBox<Estudiantes> cmbEstudiantes = new JComboBox<>();
    private JComboBox<Becas.TipoBeca> cmbTipoBeca = new JComboBox<>(Becas.TipoBeca.values());
    private JComboBox<Becas.EstadoBeca> cmbEstadoBeca = new JComboBox<>(Becas.EstadoBeca.values());
    private JTextField txtMonto = crearTextField();
    private JTextField txtComentarios = crearTextField();
    private CustomDatePicker datePickerAsignacion = new CustomDatePicker();
    private Becas beca;

    /**
     * Constructor de la clase ActualizarBecasAdmin.
     * Inicializa la interfaz gráfica y carga los datos de la beca.
     * @param beca Beca a actualizar.
     */
    public ActualizarBecasAdmin(Becas beca) {
        this.beca = beca;
        initGUI();
        initEventos();
        cargarEstudiantes();
        cargarDatosBeca();
    }

    /**
     * Método para inicializar los componentes gráficos principales.
     */
    private void initGUI() {
        setTitle("Actualizar Beca");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        establecerIcono(this);
        setSize(600, 500);
        setLocationRelativeTo(null);

        panel = this.getContentPane();
        panel.setBackground(new Color(251, 234, 230));
        gLayout = new GridBagLayout();
        gbc = new GridBagConstraints();
        panel.setLayout(gLayout);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);

        titulo = new JLabel("Actualizar Beca", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 24));
        titulo.setBorder(BorderFactory.createEmptyBorder(10, 0, 20, 0));
        titulo.setForeground(new Color(70, 70, 70));
        gbc.gridwidth = 2;
        agregarComponente(titulo, 0, 0);
        gbc.gridwidth = 1;

        personalizarComboBox(cmbEstudiantes);
        personalizarComboBox(cmbTipoBeca);
        personalizarComboBox(cmbEstadoBeca);

        agregarComponente(lblEstudiante, 1, 0);
        agregarComponente(cmbEstudiantes, 1, 1);
        agregarComponente(lblTipoBeca, 2, 0);
        agregarComponente(cmbTipoBeca, 2, 1);
        agregarComponente(lblMonto, 3, 0);
        agregarComponente(txtMonto, 3, 1);
        agregarComponente(lblFechaAsignacion, 4, 0);
        agregarComponente(datePickerAsignacion, 4, 1);
        agregarComponente(lblEstadoBeca, 5, 0);
        agregarComponente(cmbEstadoBeca, 5, 1);
        agregarComponente(lblComentarios, 6, 0);
        agregarComponente(txtComentarios, 6, 1);

        panelBotones = new JPanel();
        panelBotones.setBackground(new Color(251, 234, 230));
        panelBotones.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        btnAceptar.setPreferredSize(new Dimension(100, 40));
        btnCancelar.setPreferredSize(new Dimension(100, 40));
        panelBotones.add(btnAceptar);
        panelBotones.add(btnCancelar);

        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.gridwidth = 2;
        panel.add(panelBotones, gbc);

        setVisible(true);
    }

    /**
     * Método para agregar un componente al panel principal con las restricciones de diseño.
     * @param componente El componente a agregar.
     * @param fila       La fila donde se agregará.
     * @param columna    La columna donde se agregará.
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
        btnAceptar.addActionListener(e -> actualizarBecaValida());
    }

    /**
     * Método para cargar los estudiantes en el combo box.
     */
    private void cargarEstudiantes() {
        List<Estudiantes> estudiantes = Controlador.getListaEstudiantes();
        cmbEstudiantes.removeAllItems();
        for (Estudiantes est : estudiantes) {
            cmbEstudiantes.addItem(est);
        }
    }

    /**
     * Carga los datos de la beca en los campos de la interfaz.
     */
    private void cargarDatosBeca() {
        cmbEstudiantes.setSelectedItem(beca.getEstudiante());
        cmbTipoBeca.setSelectedItem(beca.getTipoBeca());
        txtMonto.setText(String.valueOf(beca.getMonto()));
        if (beca.getFechaAsignacion() != null) {
            datePickerAsignacion.setDate(beca.getFechaAsignacion().toLocalDate());
        }
        cmbEstadoBeca.setSelectedItem(beca.getEstadoBeca());
        txtComentarios.setText(beca.getComentarios());
    }

    /**
     * Método para validar y actualizar los datos de la beca.
     */
    private void actualizarBecaValida() {
        if (cmbEstudiantes.getSelectedItem() == null ||
                cmbTipoBeca.getSelectedItem() == null ||
                txtMonto.getText().trim().isEmpty() ||
                datePickerAsignacion.getDate() == null ||
                cmbEstadoBeca.getSelectedItem() == null) {

            new CustomDialog(null, "Error", "Todos los campos son obligatorios.", "ONLY_OK").setVisible(true);
            return;
        }
        if (txtComentarios.getText().length() > 255) {
            new CustomDialog(null, "Error", "Los comentarios no pueden exceder los 255 caracteres.", "ONLY_OK").setVisible(true);
            return;
        }
        if (txtMonto.getText().length() > 10) {
            new CustomDialog(null, "Error", "El monto no puede exceder los 10 caracteres.", "ONLY_OK").setVisible(true);
            return;
        }

        try {
            beca.setEstudiante((Estudiantes) cmbEstudiantes.getSelectedItem());
            beca.setTipoBeca((Becas.TipoBeca) cmbTipoBeca.getSelectedItem());
            beca.setMonto(Double.parseDouble(txtMonto.getText().trim()));
            beca.setFechaAsignacion(java.sql.Date.valueOf(datePickerAsignacion.getDate()));
            beca.setEstadoBeca((Becas.EstadoBeca) cmbEstadoBeca.getSelectedItem());
            beca.setComentarios(txtComentarios.getText().trim());

            Controlador.actualizarControladorBeca(beca);
            Controlador.actualizarListaBecas();

            VistaPrincipalAdmin vistaPrincipal = (VistaPrincipalAdmin) VistaPrincipalAdmin.getVistaPrincipal();
            vistaPrincipal.mostrarVistaBecas();

            new CustomDialog(null, "Éxito", "Beca actualizada correctamente.", "ONLY_OK").setVisible(true);
            dispose();
        } catch (NumberFormatException ex) {
            new CustomDialog(null, "Error", "El monto debe ser un número válido.", "ONLY_OK").setVisible(true);
        } catch (Exception ex) {
            new CustomDialog(null, "Error", "Error al actualizar la beca.", "ONLY_OK").setVisible(true);
            Controlador.rollback();
        }
    }
}