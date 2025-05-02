package Vista.Admin;

import BackUtil.GeneradorReportes;
import Controlador.Controlador;
import Mapeo.Convalidaciones;
import Mapeo.Cursos;
import Mapeo.Estudiantes;
import Vista.Util.Boton;
import Vista.Util.CustomDialog;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;

import static Controlador.ControladorReportes.*;
import static Vista.Util.EstiloComponentes.customizeComboBox;
import static Vista.Util.EstiloComponentes.setBordeNaranja;

public class Reportes extends JPanel {

    private JComboBox<Estudiantes> cbEstudiante;
    private JComboBox<Cursos> cbCurso;
    private JComboBox<Estudiantes> cbEstudianteBeca;
    private JComboBox<Estudiantes> cbEstudianteConvalidacion;

    public Reportes() {
        initGUI();
        cargarEstudiantes();
        cargarCursos();
    }

    private void initGUI() {

        setLayout(new BorderLayout());
        setBackground(new Color(251, 234, 230));

        JPanel panelSuperior = new JPanel(new BorderLayout());
        panelSuperior.setBorder(BorderFactory.createEmptyBorder(20, 10, 10, 10));
        panelSuperior.setBackground(new Color(251, 234, 230));

        JLabel titulo = new JLabel("Colegio Salesiano San Francisco de Sales - Reportes", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 24));
        titulo.setBorder(BorderFactory.createEmptyBorder(25, 10, 30, 10));
        panelSuperior.add(titulo, BorderLayout.CENTER);

        JPanel panelBoton = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelBoton.setOpaque(false);

        // por si quiero boton
//        ImageIcon icono = new ImageIcon(Objects.requireNonNull(getClass().getResource("/icons/anadir.png")));
//        icono.setImage(icono.getImage().getScaledInstance(24, 24, Image.SCALE_SMOOTH));
//        JButton btnAgregar = new Boton("Agregar Estudiante", Boton.ButtonType.PRIMARY);
//        btnAgregar.setIcon(icono);
//        btnAgregar.setPreferredSize(new Dimension(180, 30));
//        btnAgregar.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
//        panelBoton.add(btnAgregar);

        panelSuperior.add(panelBoton, BorderLayout.SOUTH);
        add(panelSuperior, BorderLayout.NORTH);

        JPanel panelConMargen = new JPanel(new BorderLayout());
        panelConMargen.setBackground(new Color(251, 234, 230));
        panelConMargen.setBorder(BorderFactory.createEmptyBorder(30, 0, 10, 10));

        JPanel gridPanel = new JPanel(new GridLayout(2, 2, 10, 10));
        gridPanel.setBackground(Color.WHITE);
        gridPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        cbEstudiante = new JComboBox<>();
        cbCurso = new JComboBox<>();
        cbEstudianteBeca = new JComboBox<>();
        cbEstudianteConvalidacion = new JComboBox<>();

        customizeComboBox(cbEstudiante);
        customizeComboBox(cbCurso);
        customizeComboBox(cbEstudianteBeca);
        customizeComboBox(cbEstudianteConvalidacion);

        gridPanel.add(createOptionPanel("Boletín de notas de Estudiante", cbEstudiante, "Descargar", e -> descargarNotasEstudiante()));
        gridPanel.add(createOptionPanel("Boletín de notas de Clase", cbCurso, "Descargar", e -> descargarNotasClase()));
        gridPanel.add(createOptionPanel("Certificado de Beca para Estudiante", cbEstudianteBeca, "Descargar", e -> descargarBecaEstudiante()));
        gridPanel.add(createOptionPanel("Certificado de Convalidación para Estudiante", cbEstudianteConvalidacion, "Descargar", e -> descargarConvalidacionEstudiante()));

        panelConMargen.add(gridPanel, BorderLayout.CENTER);
        add(panelConMargen, BorderLayout.CENTER);
    }

    private JPanel createOptionPanel(String tituloTexto, JComponent inputComponent, String btnTexto, ActionListener listener) {
        JPanel caja = new JPanel(new GridBagLayout());
        caja.setBackground(new Color(247, 232, 227));
        caja.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(241, 198, 177), 3),
                BorderFactory.createEmptyBorder(15, 15, 15, 15)
        ));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(10, 0, 10, 0);

        JLabel tituloLabel = new JLabel(String.format("<html><div style='text-align:center;width:300px;'>%s</div></html>", tituloTexto));
        tituloLabel.setFont(new Font("Arial", Font.BOLD, 16));
        caja.add(tituloLabel, gbc);

        if (inputComponent != null) {
            gbc.gridy++;
            inputComponent.setPreferredSize(new Dimension(300, 25));
            setBordeNaranja(inputComponent);
            caja.add(inputComponent, gbc);
        }

        gbc.gridy++;
        JButton boton = new Boton(btnTexto, Boton.ButtonType.PRIMARY);
        boton.setPreferredSize(new Dimension(160, 30));
        boton.addActionListener(listener);
        caja.add(boton, gbc);

        return caja;
    }

    private void cargarEstudiantes() {
        List<Estudiantes> estudiantes = Controlador.getListaEstudiantes();

        cbEstudiante.removeAllItems();
        cbEstudianteBeca.removeAllItems();
        cbEstudianteConvalidacion.removeAllItems();

        for (Estudiantes e : estudiantes) {
            cbEstudiante.addItem(e);

            if (e.getBecas() != null && !e.getBecas().isEmpty()) {
                cbEstudianteBeca.addItem(e);
            }

            if (e.getConvalidaciones() != null) {
                boolean tieneConvalidacionAprobada = false;
                for (Convalidaciones convalidacion : e.getConvalidaciones()) {
                    if (convalidacion.getEstadoConvalidacion() == Convalidaciones.EstadoConvalidacion.Aprobada) {
                        tieneConvalidacionAprobada = true;
                        break;
                    }
                }
                if (tieneConvalidacionAprobada) {
                    cbEstudianteConvalidacion.addItem(e);
                }
            }
        }

    }

    private void cargarCursos() {
        List<Cursos> cursos = Controlador.getListaCursos();

        cbCurso.removeAllItems();

        for (Cursos c : cursos) {
            cbCurso.addItem(c);
        }
    }

    // Acciones
    private void descargarNotasEstudiante() {

        if (cbEstudiante.getSelectedIndex() == -1) {
            new CustomDialog(null, "Error", "Por favor, seleccione un estudiante.","ONLY_OK");

        }else {
            Estudiantes estudiante = (Estudiantes) cbEstudiante.getSelectedItem();
            GeneradorReportes.generarBoletin(enviarInfoParaBoletin(estudiante));
        }
    }

    private void descargarNotasClase() {
        Cursos curso = (Cursos) cbCurso.getSelectedItem();

        if(cbCurso.getSelectedIndex() == -1){
            new CustomDialog(null, "Error", "Por favor, seleccione un curso.","ONLY_OK");
        }else{
            GeneradorReportes.generarBoletinesPorCurso(curso);
        }

    }

    private void descargarBecaEstudiante() {

        Estudiantes Estudiante = (Estudiantes) cbEstudianteBeca.getSelectedItem();

        if (Estudiante == null) {
            new CustomDialog(null, "Error", "Por favor, seleccione un estudiante.", "ONLY_OK");
        }else{
            GeneradorReportes.generarCertificadoBeca(enviarInfoParaCertificadoBeca(Estudiante));
        }
    }

    private void descargarConvalidacionEstudiante() {
        Estudiantes alumno = (Estudiantes) cbEstudianteConvalidacion.getSelectedItem();

        if (alumno == null) {
            new CustomDialog(null, "Error", "Por favor, seleccione un estudiante.", "ONLY_OK");
        }else{
            GeneradorReportes.generarCertificadoConvalidacion(enviarInfoParaCertificadoConvalidacion(alumno));
        }
    }
}