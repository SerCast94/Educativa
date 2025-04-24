package Vista.Admin;

import Controlador.Controlador;
import Mapeo.Horarios;
import Mapeo.Cursos;
import Mapeo.Profesores;
import Vista.Util.Boton;

import javax.swing.*;
import java.awt.*;
import java.sql.Time;
import java.util.Calendar;
import java.util.List;

import static Vista.Util.CustomSpinnerDate.crearHoraSpinner;
import static Vista.Util.EstiloComponentes.*;

public class ActualizarHorariosAdmin extends JFrame {
    private Container panel;
    private GridBagLayout gLayout;
    private GridBagConstraints gbc;
    private JButton btnAceptar = new Boton("Actualizar", Boton.ButtonType.PRIMARY);
    private JButton btnCancelar = new Boton("Cancelar", Boton.ButtonType.DELETE);

    private JLabel lblCurso = new JLabel("Curso:");
    private JLabel lblProfesor = new JLabel("Profesor:");
    private JLabel lblDiaSemana = new JLabel("Día de la Semana:");
    private JLabel lblHoraInicio = new JLabel("Hora de Inicio:");
    private JLabel lblHoraFin = new JLabel("Hora de Fin:");

    private JComboBox<Cursos> cmbCurso = new JComboBox<>();
    private JComboBox<Profesores> cmbProfesor = new JComboBox<>();
    private JComboBox<Horarios.DiaSemana> cmbDiaSemana = new JComboBox<>(Horarios.DiaSemana.values());
    private JSpinner spnHoraInicio = crearHoraSpinner();
    private JSpinner spnHoraFin = crearHoraSpinner();

    private Horarios horario;

    public ActualizarHorariosAdmin(Horarios horario) {
        this.horario = horario;
        initGUI();
        initEventos();
        cargarCursos();
        cargarProfesores();
        cargarDatosHorario();
    }

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

        JLabel titulo = new JLabel("Actualizar Horario", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 24));
        titulo.setForeground(new Color(70, 70, 70));
        titulo.setBorder(BorderFactory.createEmptyBorder(10, 0, 20, 0));
        gbc.gridwidth = 2;
        agregarComponente(titulo, 0, 0);
        gbc.gridwidth = 1;

        customizeComboBox(cmbCurso);
        customizeComboBox(cmbProfesor);
        customizeComboBox(cmbDiaSemana);

        agregarComponente(lblCurso, 1, 0);
        setBordeNaranja(cmbCurso);
        agregarComponente(cmbCurso, 1, 1);

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
        gbc.gridy = 6;
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
                    cmbProfesor.getSelectedItem() == null ||
                    cmbDiaSemana.getSelectedItem() == null ||
                    spnHoraInicio.getValue() == null ||
                    spnHoraFin.getValue() == null) {

                JOptionPane.showMessageDialog(null, "Todos los campos obligatorios deben estar completos.", "Error", JOptionPane.ERROR_MESSAGE);
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

                horario.setCurso((Cursos) cmbCurso.getSelectedItem());
                horario.setProfesor((Profesores) cmbProfesor.getSelectedItem());
                horario.setDiaSemana((Horarios.DiaSemana) cmbDiaSemana.getSelectedItem());
                horario.setHoraInicio(horaInicio);
                horario.setHoraFin(horaFin);

                Controlador.actualizarControladorHorario(horario);
                Controlador.actualizarListaHorarios();

                VistaPrincipalAdmin vistaPrincipal = (VistaPrincipalAdmin) VistaPrincipalAdmin.getVistaPrincipal();
                vistaPrincipal.mostrarVistaHorarios();

                JOptionPane.showMessageDialog(null, "Horario actualizado correctamente.");
                dispose();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Error al actualizar el horario.", "Error", JOptionPane.ERROR_MESSAGE);
                Controlador.rollback();
            }
        });
    }

    private void cargarDatosHorario() {
        cmbCurso.setSelectedItem(horario.getCurso());
        cmbProfesor.setSelectedItem(horario.getProfesor());
        cmbDiaSemana.setSelectedItem(horario.getDiaSemana());
        spnHoraInicio.setValue(horario.getHoraInicio());
        spnHoraFin.setValue(horario.getHoraFin());
    }

    private void cargarCursos() {
        List<Cursos> cursos = Controlador.getListaCursos();
        cmbCurso.removeAllItems();
        for (Cursos curso : cursos) {
            cmbCurso.addItem(curso);
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