package Vista.Admin.Modificar;

import Controlador.Controlador;
import Mapeo.Asignaturas;
import Mapeo.Horarios;
import Mapeo.Profesores;
import Vista.Admin.VistaPrincipalAdmin;
import Vista.Util.Boton;
import Vista.Util.CustomDialog;
import javax.swing.*;
import java.awt.*;
import java.sql.Time;
import java.util.Calendar;
import java.util.List;
import static Vista.Util.CustomSpinnerDate.crearHoraSpinner;
import static Vista.Util.EstiloComponentes.*;

/**
 * Clase para actualizar horarios de asignaturas en la vista de administración.
 */
public class ActualizarHorariosAdmin extends JFrame {
    private Container panel;
    private GridBagLayout gLayout;
    private GridBagConstraints gbc;
    private JLabel titulo;
    private JPanel panelBotones;
    private JButton btnAceptar = new Boton("Actualizar", Boton.tipoBoton.PRIMARY);
    private JButton btnCancelar = new Boton("Cancelar", Boton.tipoBoton.DELETE);
    private JLabel lblCurso = new JLabel("Asignatura:");
    private JLabel lblProfesor = new JLabel("Profesor:");
    private JLabel lblDiaSemana = new JLabel("Día de la Semana:");
    private JLabel lblHoraInicio = new JLabel("Hora de Inicio:");
    private JLabel lblHoraFin = new JLabel("Hora de Fin:");
    private JComboBox<Asignaturas> cmbAsignaturas = new JComboBox<>();
    private JComboBox<Profesores> cmbProfesor = new JComboBox<>();
    private JComboBox<Horarios.DiaSemana> cmbDiaSemana = new JComboBox<>(Horarios.DiaSemana.values());
    private JSpinner spnHoraInicio = crearHoraSpinner();
    private JSpinner spnHoraFin = crearHoraSpinner();
    private Horarios horario;

    /**
     * Constructor de la clase ActualizarHorariosAdmin.
     * Inicializa la interfaz gráfica y carga los datos del horario.
     * @param horario Horario a actualizar.
     */
    public ActualizarHorariosAdmin(Horarios horario) {
        this.horario = horario;
        initGUI();
        initEventos();
        cargarAsignaturas();
        cargarProfesores();
        cargarDatosHorario();
    }

    /*
     * Método para inicializar los componentes gráficos principales.
     */
    private void initGUI() {
        setTitle("Actualizar Horario");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(600, 550);
        setLocationRelativeTo(null);

        panel = this.getContentPane();
        panel.setBackground(new Color(251, 234, 230));
        gLayout = new GridBagLayout();
        gbc = new GridBagConstraints();
        panel.setLayout(gLayout);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);

        titulo = new JLabel("Actualizar Horario", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 24));
        titulo.setForeground(new Color(70, 70, 70));
        titulo.setBorder(BorderFactory.createEmptyBorder(10, 0, 20, 0));
        gbc.gridwidth = 2;
        agregarComponente(titulo, 0, 0);
        gbc.gridwidth = 1;

        personalizarComboBox(cmbAsignaturas);
        personalizarComboBox(cmbProfesor);
        personalizarComboBox(cmbDiaSemana);

        agregarComponente(lblCurso, 1, 0);
        setBordeNaranja(cmbAsignaturas);
        agregarComponente(cmbAsignaturas, 1, 1);

        agregarComponente(lblProfesor, 2, 0);
        setBordeNaranja(cmbProfesor);
        agregarComponente(cmbProfesor, 2, 1);

        agregarComponente(lblDiaSemana, 3, 0);
        setBordeNaranja(cmbDiaSemana);
        agregarComponente(cmbDiaSemana, 3, 1);

        agregarComponente(lblHoraInicio, 4, 0);
        setBordeNaranja(spnHoraInicio);
        agregarComponente(spnHoraInicio, 4, 1);

        agregarComponente(lblHoraFin, 5, 0);
        setBordeNaranja(spnHoraFin);
        agregarComponente(spnHoraFin, 5, 1);

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

        btnAceptar.addActionListener(e -> actualizarHorarioValido());
    }

    /**
     * Método para cargar los datos del horario en los campos correspondientes.
     */
    private void cargarDatosHorario() {
        cmbAsignaturas.setSelectedItem(horario.getAsignatura());
        cmbProfesor.setSelectedItem(horario.getProfesor());
        cmbDiaSemana.setSelectedItem(horario.getDiaSemana());
        spnHoraInicio.setValue(horario.getHoraInicio());
        spnHoraFin.setValue(horario.getHoraFin());
    }

    /**
     * Método para cargar las asignaturas en el combo box.
     */
    private void cargarAsignaturas(){
        List<Asignaturas> asignaturas = Controlador.getListaAsignaturas();
        cmbAsignaturas.removeAllItems();
        for (Asignaturas asignatura : asignaturas) {
            cmbAsignaturas.addItem(asignatura);
        }
    }

    /**
     * Método para cargar los profesores en el combo box.
     */
    private void cargarProfesores() {
        List<Profesores> profesores = Controlador.getListaProfesores();
        cmbProfesor.removeAllItems();
        for (Profesores profesor : profesores) {
            cmbProfesor.addItem(profesor);
        }
    }

    /**
     * Método para validar y actualizar los datos del horario.
     */
    private void actualizarHorarioValido() {

        if (cmbAsignaturas.getSelectedItem() == null ||
                cmbProfesor.getSelectedItem() == null ||
                cmbDiaSemana.getSelectedItem() == null ||
                spnHoraInicio.getValue() == null ||
                spnHoraFin.getValue() == null) {

            new CustomDialog(null,"Error", "Todos los campos son obligatorios.","ONLY_OK").setVisible(true);
            return;
        }
        if (spnHoraInicio.getValue() == null || spnHoraFin.getValue() == null) {
            new CustomDialog(null,"Error", "Las horas de inicio y fin son obligatorias.","ONLY_OK").setVisible(true);
            return;
        }
        if (spnHoraInicio.getValue().equals(spnHoraFin.getValue())) {
            new CustomDialog(null,"Error", "La hora de inicio y la hora de fin no pueden ser iguales.","ONLY_OK").setVisible(true);
            return;
        }

        try {
            Calendar calendar = Calendar.getInstance();

            calendar.setTime((java.util.Date) spnHoraInicio.getValue());
            calendar.set(Calendar.SECOND, 0);
            calendar.set(Calendar.MILLISECOND, 0);
            Time horaInicio = new Time(calendar.getTimeInMillis());

            calendar.setTime((java.util.Date) spnHoraFin.getValue());
            calendar.set(Calendar.SECOND, 0);
            calendar.set(Calendar.MILLISECOND, 0);
            Time horaFin = new Time(calendar.getTimeInMillis());

            horario.setAsignatura((Asignaturas) cmbAsignaturas.getSelectedItem());
            horario.setProfesor((Profesores) cmbProfesor.getSelectedItem());
            horario.setDiaSemana((Horarios.DiaSemana) cmbDiaSemana.getSelectedItem());
            horario.setHoraInicio(horaInicio);
            horario.setHoraFin(horaFin);

            Controlador.actualizarControladorHorario(horario);
            Controlador.actualizarListaHorarios();

            VistaPrincipalAdmin vistaPrincipal = (VistaPrincipalAdmin) VistaPrincipalAdmin.getVistaPrincipal();
            vistaPrincipal.mostrarVistaHorarios();

            new CustomDialog(null,"Éxito", "Horario actualizado correctamente.", "ONLY_OK").setVisible(true);
            dispose();
        } catch (Exception ex) {
            new CustomDialog(null,"Error", "Error al actualizar el horario.","ONLY_OK").setVisible(true);
            Controlador.rollback();
        }
    }
}