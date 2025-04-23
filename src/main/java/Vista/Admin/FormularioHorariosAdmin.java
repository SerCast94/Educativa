package Vista.Admin;

import Controlador.Controlador;
import Mapeo.Horarios;
import Mapeo.Cursos;
import Mapeo.Extraescolares;
import Mapeo.Profesores;
import Vista.Util.CustomDatePicker;
import Vista.Util.Boton;

import javax.swing.*;
import java.awt.*;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
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

    private JLabel lblCurso = new JLabel("Curso: ");
    private JLabel lblExtraescolar = new JLabel("Extraescolar: ");
    private JLabel lblDiaSemana = new JLabel("Día de la Semana: ");
    private JLabel lblHoraInicio = new JLabel("Hora de Inicio: ");
    private JLabel lblHoraFin = new JLabel("Hora de Fin: ");
    private JLabel lblProfesor = new JLabel("Profesor: ");

    private JComboBox<Cursos> cmbCurso = new JComboBox<>();
    private JComboBox<Extraescolares> cmbExtraescolar = new JComboBox<>();
    private JComboBox<Horarios.DiaSemana> cmbDiaSemana = new JComboBox<>(Horarios.DiaSemana.values());
    private JSpinner spnHoraInicio = crearHoraSpinner();
    private JSpinner spnHoraFin = crearHoraSpinner();
    private JComboBox<Profesores> cmbProfesor = new JComboBox<>();

    public FormularioHorariosAdmin() {
        initGUI();
        initEventos();
        cargarCursos();
        cargarExtraescolares();
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

        customizeComboBox(cmbCurso);
        customizeComboBox(cmbExtraescolar);
        customizeComboBox(cmbDiaSemana);
        customizeComboBox(cmbProfesor);

        agregarComponente(lblCurso, 1, 0);
        setBordeNaranja(cmbCurso);
        agregarComponente(cmbCurso, 1, 1);

        agregarComponente(lblExtraescolar, 2, 0);
        setBordeNaranja(cmbExtraescolar);
        agregarComponente(cmbExtraescolar, 2, 1);

        agregarComponente(lblDiaSemana, 3, 0);
        setBordeNaranja(cmbDiaSemana);
        agregarComponente(cmbDiaSemana, 3, 1);

        agregarComponente(lblHoraInicio, 4, 0);
        setBordeNaranja(spnHoraInicio);
        agregarComponente(spnHoraInicio, 4, 1);

        agregarComponente(lblHoraFin, 5, 0);
        setBordeNaranja(spnHoraFin);
        agregarComponente(spnHoraFin, 5, 1);

        agregarComponente(lblProfesor, 6, 0);
        setBordeNaranja(cmbProfesor);
        agregarComponente(cmbProfesor, 6, 1);

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

        btnAceptar.addActionListener(e -> {
            if (cmbCurso.getSelectedItem() == null ||
                    cmbExtraescolar.getSelectedItem() == null ||
                    cmbDiaSemana.getSelectedItem() == null ||
                    spnHoraInicio.getValue() == null ||
                    spnHoraFin.getValue() == null ||
                    cmbProfesor.getSelectedItem() == null) {

                JOptionPane.showMessageDialog(null, "Todos los campos obligatorios deben ser completados.", "Error", JOptionPane.ERROR_MESSAGE);
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
                        (Cursos) cmbCurso.getSelectedItem(),
                        (Extraescolares) cmbExtraescolar.getSelectedItem(),
                        (Horarios.DiaSemana) cmbDiaSemana.getSelectedItem(),
                        horaInicio,
                        horaFin,
                        (Profesores) cmbProfesor.getSelectedItem()
                );

                insertarControladorHorario(nuevoHorario);
                Controlador.actualizarListaHorarios();

                VistaPrincipalAdmin vistaPrincipal = (VistaPrincipalAdmin) VistaPrincipalAdmin.getVistaPrincipal();
                vistaPrincipal.mostrarVistaHorarios();

                JOptionPane.showMessageDialog(null, "Horario registrado correctamente.");
                dispose();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Error al registrar el horario.", "Error", JOptionPane.ERROR_MESSAGE);
                Controlador.rollback();
            }
        });
    }

    private void cargarCursos() {
        List<Cursos> cursos = Controlador.getListaCursos();
        cmbCurso.removeAllItems();
        for (Cursos curso : cursos) {
            cmbCurso.addItem(curso);
        }
    }

    private void cargarExtraescolares() {
        List<Extraescolares> extraescolares = Controlador.getListaExtraescolares();
        cmbExtraescolar.removeAllItems();
        for (Extraescolares extraescolar : extraescolares) {
            cmbExtraescolar.addItem(extraescolar);
        }
    }

    private void cargarProfesores() {
        List<Profesores> profesores = Controlador.getListaProfesores();
        cmbProfesor.removeAllItems();
        for (Profesores profesor : profesores) {
            cmbProfesor.addItem(profesor);
        }
    }
}
