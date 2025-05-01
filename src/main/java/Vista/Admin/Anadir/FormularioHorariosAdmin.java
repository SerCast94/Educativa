package Vista.Admin.Anadir;

import Controlador.Controlador;
import Mapeo.*;
import Vista.Admin.VistaPrincipalAdmin;
import Vista.Util.Boton;
import Vista.Util.CustomDialog;

import javax.swing.*;
import java.awt.*;
import java.sql.Time;
import java.util.Calendar;
import java.util.List;

import static Controlador.Controlador.insertarControladorHorario;
import static Vista.Util.CustomSpinnerDate.crearHoraSpinner;
import static Vista.Util.EstiloComponentes.*;

public class FormularioHorariosAdmin extends JFrame {
    private Container panel;
    private GridBagLayout gLayout;
    private GridBagConstraints gbc;
    private JButton btnAceptar = new Boton("Aceptar", Boton.ButtonType.PRIMARY);
    private JButton btnCancelar = new Boton("Cancelar", Boton.ButtonType.DELETE);

    private JLabel lblAsignatura = new JLabel("Asignatura: ");
    private JLabel lblDiaSemana = new JLabel("Día de la Semana: ");
    private JLabel lblHoraInicio = new JLabel("Hora de Inicio: ");
    private JLabel lblHoraFin = new JLabel("Hora de Fin: ");
    private JLabel lblProfesor = new JLabel("Profesor: ");

    private JComboBox<Asignaturas> cmbAsignatura = new JComboBox<>();
    private JComboBox<Horarios.DiaSemana> cmbDiaSemana = new JComboBox<>(Horarios.DiaSemana.values());
    private JSpinner spnHoraInicio = crearHoraSpinner();
    private JSpinner spnHoraFin = crearHoraSpinner();
    private JComboBox<Profesores> cmbProfesor = new JComboBox<>();

    public FormularioHorariosAdmin() {
        initGUI();
        initEventos();
        cargarAsignaturas();
        cargarProfesores();
    }

    private void initGUI() {
        setTitle("Registrar Horario");
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

        JLabel titulo = new JLabel("Registrar Horario", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 24));
        titulo.setForeground(new Color(70, 70, 70));
        titulo.setBorder(BorderFactory.createEmptyBorder(10, 0, 20, 0));
        gbc.gridwidth = 2;
        agregarComponente(titulo, 0, 0);
        gbc.gridwidth = 1;

        customizeComboBox(cmbAsignatura);
        customizeComboBox(cmbDiaSemana);
        customizeComboBox(cmbProfesor);

        agregarComponente(lblAsignatura, 1, 0);
        setBordeNaranja(cmbAsignatura);
        agregarComponente(cmbAsignatura, 1, 1);

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

        JPanel panelBotones = new JPanel();
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

    private void agregarComponente(Component componente, int fila, int columna) {
        gbc.gridx = columna;
        gbc.gridy = fila;
        panel.add(componente, gbc);
    }

    private void initEventos() {
        btnCancelar.addActionListener(e -> dispose());

        btnAceptar.addActionListener(e -> insertarHorarioValido());
    }


    private void cargarAsignaturas() {
        List<Asignaturas> asignaturas = Controlador.getListaAsignaturas();
        cmbAsignatura.removeAllItems();
        for (Asignaturas asignatura : asignaturas) {
            cmbAsignatura.addItem(asignatura);
        }
    }

    private void cargarProfesores() {
        List<Profesores> profesores = Controlador.getListaProfesores();
        cmbProfesor.removeAllItems();
        for (Profesores profesor : profesores) {
            cmbProfesor.addItem(profesor);
        }
    }

    private void insertarHorarioValido() {

        if (cmbAsignatura.getSelectedItem() == null ||
                cmbDiaSemana.getSelectedItem() == null ||
                spnHoraInicio.getValue() == null ||
                spnHoraFin.getValue() == null ||
                cmbProfesor.getSelectedItem() == null) {

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
            java.util.Date horaInicioDate = (java.util.Date) spnHoraInicio.getValue();
            java.util.Date horaFinDate = (java.util.Date) spnHoraFin.getValue();
            Calendar calendar = Calendar.getInstance();

            // Configurar horaInicio con segundos en 00
            calendar.setTime(horaInicioDate);
            calendar.set(Calendar.SECOND, 0);
            calendar.set(Calendar.MILLISECOND, 0);
            Time horaInicio = new Time(calendar.getTimeInMillis());

            // Configurar horaFin con segundos en 00
            calendar.setTime(horaFinDate);
            calendar.set(Calendar.SECOND, 0);
            calendar.set(Calendar.MILLISECOND, 0);
            Time horaFin = new Time(calendar.getTimeInMillis());

            Horarios nuevoHorario = new Horarios(
                    (Asignaturas) cmbAsignatura.getSelectedItem(),
                    (Horarios.DiaSemana) cmbDiaSemana.getSelectedItem(),
                    horaInicio,
                    horaFin,
                    (Profesores) cmbProfesor.getSelectedItem()
            );

            insertarControladorHorario(nuevoHorario);
            Controlador.actualizarListaHorarios();

            VistaPrincipalAdmin vistaPrincipal = (VistaPrincipalAdmin) VistaPrincipalAdmin.getVistaPrincipal();
            vistaPrincipal.mostrarVistaHorarios();

            new CustomDialog(null, "Éxito", "Horario registrado correctamente.", "ONLY_OK").setVisible(true);
            dispose();
        } catch (Exception ex) {
            new CustomDialog(null,"Error", "Error al registrar el horario.","ONLY_OK").setVisible(true);
            Controlador.rollback();
        }
    }
}