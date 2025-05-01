package Vista.Profesor.Anadir;

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

import static Controlador.Controlador.actualizarListaAsistencia;
import static Controlador.Controlador.insertarControladorAsistencia;
import static Vista.Util.EstiloComponentes.*;

public class FormularioAsistenciaProfesor extends JFrame {
    private Container panel;
    private GridBagLayout gLayout;
    private GridBagConstraints gbc;
    private JButton btnAceptar = new Boton("Aceptar", Boton.ButtonType.PRIMARY);
    private JButton btnCancelar = new Boton("Cancelar", Boton.ButtonType.DELETE);

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

    public FormularioAsistenciaProfesor() {
        initGUI();
        initEventos();
        cargarEstudiantes();
        cargarCursos();
    }

    private void initGUI() {
        setTitle("Registrar Asistencia");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null);

        panel = this.getContentPane();
        panel.setBackground(new Color(251, 234, 230));
        gLayout = new GridBagLayout();
        gbc = new GridBagConstraints();
        panel.setLayout(gLayout);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);

        JLabel titulo = new JLabel("Registrar Asistencia", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 24));
        titulo.setForeground(new Color(70, 70, 70));
        titulo.setBorder(BorderFactory.createEmptyBorder(10, 0, 20, 0));
        gbc.gridwidth = 2;
        agregarComponente(titulo, 0, 0);
        gbc.gridwidth = 1;

        customizeComboBox(cmbEstudiante);
        customizeComboBox(cmbCurso);
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

        btnAceptar.addActionListener(e -> insertarAsistenciaValida());

        cmbCurso.addActionListener(e -> cargarEstudiantes());
    }

    private void cargarEstudiantes() {
        cmbEstudiante.removeAllItems();

        List<Estudiantes> estudiantes = Controlador.getListaEstudiantes();

        // estudiantes por el curso del profesor
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

        //  lista filtrada por apellido usando Collator para ignorar mayúsculas, minúsculas y acentos
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
    }

    private void cargarCursos() {
        List<Cursos> cursos = Controlador.getListaCursos();
        cmbCurso.removeAllItems();

        for (Cursos c : cursos) {
            if(c.getProfesor().equals(VistaPrincipalProfesor.usuarioLogeado)){
                cmbCurso.addItem(c);
            }
        }
    }

    private void insertarAsistenciaValida(){
        if (cmbEstudiante.getSelectedItem() == null ||
            cmbCurso.getSelectedItem() == null ||
            datePicker.getDate() == null){
            new CustomDialog(null,"Error", "Todos los campos son obligatorios.","ONLY_OK").setVisible(true);
            return;
        }

        if (txtMotivoAusencia.getText().trim().length() > 255) {
            new CustomDialog(null,"Error", "El motivo de ausencia no puede exceder los 255 caracteres.","ONLY_OK").setVisible(true);
            return;
        }

        Asistencia nuevaAsistencia = new Asistencia(
                (Estudiantes) cmbEstudiante.getSelectedItem(),
                (Cursos) cmbCurso.getSelectedItem(),
                Date.valueOf(datePicker.getDate()),
                chkJustificado.isSelected(),
                txtMotivoAusencia.getText().trim()
        );
        try {
            insertarControladorAsistencia(nuevaAsistencia);
            actualizarListaAsistencia();

            VistaPrincipalProfesor vistaPrincipalProfesor = (VistaPrincipalProfesor) VistaPrincipalProfesor.getVistaPrincipal();
            vistaPrincipalProfesor.mostrarVistaAsistencia();

            new CustomDialog(null,"Éxito", "Asistencia registrada correctamente.","ONLY_OK").setVisible(true);
            dispose();
        } catch (Exception ex) {
            new CustomDialog(null,"Error", "Error al registrar la asistencia.","ONLY_OK").setVisible(true);
            Controlador.rollback();
        }
    }
}