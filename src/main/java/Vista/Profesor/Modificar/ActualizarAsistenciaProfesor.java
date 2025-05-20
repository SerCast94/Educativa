package Vista.Profesor.Modificar;

import Controlador.Controlador;
import Mapeo.Asistencia;
import Mapeo.Cursos;
import Mapeo.Estudiantes;
import Mapeo.Matriculas;
import Vista.Profesor.VistaPrincipalProfesor;
import Vista.Util.Boton;
import Vista.Util.CustomDatePicker;
import Vista.Util.CustomDialog;
import javax.swing.*;
import java.awt.*;
import java.sql.Date;
import java.text.Collator;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import static Controlador.ControladorLogin.profesorLogeado;
import static Vista.Util.EstiloComponentes.*;

/**
 * Clase que representa la ventana para actualizar la asistencia de un estudiante.
 */
public class ActualizarAsistenciaProfesor extends JFrame {
    private Container panel;
    private GridBagLayout gLayout;
    private GridBagConstraints gbc;
    private JButton btnAceptar = new Boton("Actualizar", Boton.tipoBoton.PRIMARY);
    private JButton btnCancelar = new Boton("Cancelar", Boton.tipoBoton.DELETE);
    private JLabel titulo;
    private JPanel panelBotones;
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
    private Asistencia asistencia;

    /**
     * Constructor de la clase ActualizarAsistenciaProfesor.
     * @param asistencia La asistencia a actualizar.
     */
    public ActualizarAsistenciaProfesor(Asistencia asistencia) {
        this.asistencia = asistencia;
        initGUI();
        initEventos();
        cargarCursos();
        cargarEstudiantes();
        cargarDatosAsistencia();
    }

    /**
     * Método para inicializar los componentes gráficos principales.
     */
    private void initGUI() {
        setTitle("Actualizar Asistencia");
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

        titulo = new JLabel("Actualizar Asistencia", SwingConstants.CENTER);
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

        btnAceptar.addActionListener(e -> actualizarAsistenciaValida());
    }

    /**
     * Método para cargar los datos de la asistencia en los campos correspondientes.
     */
    private void cargarDatosAsistencia() {
        cmbEstudiante.setSelectedItem(asistencia.getEstudiante());
        cmbCurso.setSelectedItem(asistencia.getCurso());
        datePicker.setDate(asistencia.getFecha().toLocalDate());
        chkJustificado.setSelected(asistencia.getJustificado());
        txtMotivoAusencia.setText(asistencia.getMotivoAusencia());
    }

    /**
     * Método para cargar los estudiantes en el combo box según el curso seleccionado y los ordena
     * alfabéticamente por apellido para que sea más fácil de encontrar.
     */
    private void cargarEstudiantes() {
        cmbEstudiante.removeAllItems();

        List<Estudiantes> estudiantes = Controlador.getListaEstudiantes();

        List<Estudiantes> estudiantesFiltrados = new ArrayList<>();
        for (Estudiantes e : estudiantes) {
            if (e.getMatriculas() != null) {
                for (Matriculas matricula : e.getMatriculas()) {
                    if (matricula.getCurso().equals(cmbCurso.getSelectedItem())) {
                        estudiantesFiltrados.add(e);
                        break;
                    }
                }
            }
        }

        Collator collator = Collator.getInstance(new Locale("es", "ES"));
        collator.setStrength(Collator.PRIMARY);
        for (int i = 0; i < estudiantesFiltrados.size() - 1; i++) {
            for (int j = i + 1; j < estudiantesFiltrados.size(); j++) {
                if (collator.compare(estudiantesFiltrados.get(i).getApellido(), estudiantesFiltrados.get(j).getApellido()) > 0) {
                    Estudiantes temp = estudiantesFiltrados.get(i);
                    estudiantesFiltrados.set(i, estudiantesFiltrados.get(j));
                    estudiantesFiltrados.set(j, temp);
                }
            }
        }

        for (Estudiantes e : estudiantesFiltrados) {
            cmbEstudiante.addItem(e);
        }

        if (asistencia != null && asistencia.getEstudiante() != null) {
            cmbEstudiante.setSelectedItem(asistencia.getEstudiante());
        }
    }

    /**
     * Método para cargar los cursos en el combo box según el profesor logueado.
     */
    private void cargarCursos() {
        List<Cursos> cursos = Controlador.getListaCursos();
        cmbCurso.removeAllItems();

        for (Cursos c : cursos) {
            if(c.getProfesor().equals(profesorLogeado)){
                cmbCurso.addItem(c);
            }
        }
    }

    /**
     * Método para validar y actualizar los datos de la asistencia.
     */
    private void actualizarAsistenciaValida(){

        if (cmbEstudiante.getSelectedItem() == null ||
                cmbCurso.getSelectedItem() == null ||
                datePicker.getDate() == null) {

            new CustomDialog(null,"Error", "Todos los campos son obligatorios.","ONLY_OK").setVisible(true);
            return;
        }

        if (txtMotivoAusencia.getText().trim().length() > 255) {
            new CustomDialog(null,"Error", "El motivo de ausencia no puede exceder los 255 caracteres.","ONLY_OK").setVisible(true);
            return;
        }

        asistencia.setEstudiante((Estudiantes) cmbEstudiante.getSelectedItem());
        asistencia.setCurso((Cursos) cmbCurso.getSelectedItem());
        asistencia.setFecha(Date.valueOf(datePicker.getDate()));
        asistencia.setJustificado(chkJustificado.isSelected());
        asistencia.setMotivoAusencia(txtMotivoAusencia.getText().trim());

        try {
            Controlador.actualizarControladorAsistencia(asistencia);

            VistaPrincipalProfesor vistaPrincipalProfesor = (VistaPrincipalProfesor) VistaPrincipalProfesor.getVistaPrincipal();
            vistaPrincipalProfesor.mostrarVistaAsistencia();

            new CustomDialog(null,"Éxito", "Asistencia actualizada correctamente.","ONLY_OK").setVisible(true);
            dispose();
        } catch (Exception ex) {
            new CustomDialog(null,"Error", "Error al actualizar la asistencia.","ONLY_OK").setVisible(true);
            Controlador.rollback();
        }
    }
}
