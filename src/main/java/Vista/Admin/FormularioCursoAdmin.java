package Vista.Admin;

import Controlador.Controlador;
import Mapeo.Cursos;
import Mapeo.Profesores;
import Vista.Util.Boton;

import javax.swing.*;
import java.awt.*;
import java.util.List;

import static Vista.Util.EstiloComponentes.*;

public class FormularioCursoAdmin extends JFrame {
    private Container panel;
    private GridBagLayout gLayout;
    private GridBagConstraints gbc;

    private JLabel lblNombre = new JLabel("Nombre:");
    private JLabel lblDescripcion = new JLabel("Descripción:");
    private JLabel lblProfesor = new JLabel("Profesor:");
    private JLabel lblEstado = new JLabel("Estado:");

    private JTextField txtNombre = crearTextField();
    private JTextField txtDescripcion = crearTextField();
    private JComboBox<Profesores> cmbProfesor = new JComboBox<>();
    private JComboBox<String> cmbEstado = new JComboBox<>(new String[]{"activo", "inactivo"});

    private JButton btnAceptar = new Boton("Aceptar", Boton.ButtonType.PRIMARY);
    private JButton btnCancelar = new Boton("Cancelar", Boton.ButtonType.DELETE);

    public FormularioCursoAdmin() {
        initGUI();
        initEventos();
        cargarProfesores();
    }

    private void initGUI() {
        setTitle("Agregar Curso");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(500, 400);
        setLocationRelativeTo(null);

        panel = this.getContentPane();
        panel.setBackground(new Color(251, 234, 230));
        gLayout = new GridBagLayout();
        gbc = new GridBagConstraints();
        panel.setLayout(gLayout);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10);

        JLabel titulo = new JLabel("Agregar Curso", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 22));
        titulo.setForeground(new Color(70, 70, 70));
        titulo.setBorder(BorderFactory.createEmptyBorder(10, 0, 20, 0));
        gbc.gridwidth = 2;
        agregarComponente(titulo, 0, 0);
        gbc.gridwidth = 1;

        customizeComboBox(cmbProfesor);
        customizeComboBox(cmbEstado);

        setBordeNaranja(txtNombre);
        setBordeNaranja(txtDescripcion);
        setBordeNaranja(cmbProfesor);
        setBordeNaranja(cmbEstado);

        agregarComponente(lblNombre, 1, 0);
        agregarComponente(txtNombre, 1, 1);

        agregarComponente(lblDescripcion, 2, 0);
        agregarComponente(txtDescripcion, 2, 1);

        agregarComponente(lblProfesor, 3, 0);
        agregarComponente(cmbProfesor, 3, 1);

        agregarComponente(lblEstado, 4, 0);
        agregarComponente(cmbEstado, 4, 1);

        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        panelBotones.setBackground(new Color(251, 234, 230));
        btnAceptar.setPreferredSize(new Dimension(100, 40));
        btnCancelar.setPreferredSize(new Dimension(100, 40));
        panelBotones.add(btnAceptar);
        panelBotones.add(btnCancelar);

        gbc.gridx = 0;
        gbc.gridy = 5;
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
            if (txtNombre.getText().trim().isEmpty() ||
                    txtDescripcion.getText().trim().isEmpty() ||
                    cmbProfesor.getSelectedItem() == null ||
                    cmbEstado.getSelectedItem() == null) {

                JOptionPane.showMessageDialog(null, "Todos los campos son obligatorios.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            Cursos curso = new Cursos();
            curso.setNombre(txtNombre.getText().trim());
            curso.setDescripcion(txtDescripcion.getText().trim());
            curso.setProfesor((Profesores) cmbProfesor.getSelectedItem());
            curso.setEstado(Cursos.EstadoCurso.valueOf(cmbEstado.getSelectedItem().toString()));

            // Guardar el curso en la base de datos

        });
    }

    private void cargarProfesores() {
        List<Profesores> profesores = Controlador.getListaProfesores();
        cmbProfesor.removeAllItems();
        for (Profesores p : profesores) {
            cmbProfesor.addItem(p);
        }
    }
}
