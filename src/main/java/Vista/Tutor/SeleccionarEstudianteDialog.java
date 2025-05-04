package Vista.Tutor;

import Controlador.ControladorLogin;
import Mapeo.Estudiantes;
import Mapeo.Tutores;
import Vista.Util.Boton;
import Vista.Util.CustomDialog;

import javax.swing.*;
import java.awt.*;
import java.util.List;

import static Controlador.ControladorLogin.estudianteLogeado;
import static Controlador.ControladorLogin.setEstudianteLogeado;
import static Controlador.ControladorTutor.getListaEstudiantesDelTutor;
import static Vista.Util.EstiloComponentes.*;

public class SeleccionarEstudianteDialog extends JDialog {

    private Estudiantes estudianteSeleccionado;

    private JComboBox<Estudiantes> cmbEstudiantes = new JComboBox<>();
    private JButton btnAceptar = new Boton("Aceptar", Boton.ButtonType.PRIMARY);
    private JButton btnCancelar = new Boton("Cancelar", Boton.ButtonType.DELETE);
    private GridBagConstraints gbc;
    private Container panel;

    public SeleccionarEstudianteDialog(Tutores tutorLogeado) {
        initGUI();
        cargarEstudiantes(tutorLogeado);
        initEventos();
    }

    private void initGUI() {
        setSize(400, 200);
        setTitle("Seleccionar Estudiante");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        panel = getContentPane();
        panel.setBackground(new Color(251, 234, 230));
        panel.setLayout(new GridBagLayout());
        gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel lblTitulo = new JLabel("Seleccione un Estudiante", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 20));
        lblTitulo.setForeground(new Color(70, 70, 70));
        gbc.gridwidth = 2;
        agregarComponente(lblTitulo, 0, 0);
        gbc.gridwidth = 1;

        customizeComboBox(cmbEstudiantes);
        setBordeNaranja(cmbEstudiantes);
        agregarComponente(cmbEstudiantes, 1, 0);

        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        panelBotones.setBackground(new Color(251, 234, 230));
        btnAceptar.setPreferredSize(new Dimension(100, 40));
        btnCancelar.setPreferredSize(new Dimension(100, 40));
        panelBotones.add(btnAceptar);
        panelBotones.add(btnCancelar);

        gbc.gridwidth = 2;
        agregarComponente(panelBotones, 2, 0);
    }

    private void agregarComponente(Component comp, int fila, int col) {
        gbc.gridx = col;
        gbc.gridy = fila;
        panel.add(comp, gbc);
    }

    private void initEventos() {

        if(estudianteLogeado != null){
            cmbEstudiantes.setSelectedItem(estudianteLogeado);
        }

        btnCancelar.addActionListener(e -> dispose());

        btnAceptar.addActionListener(e ->  establecerPerfilEstudianteValido());

    }

    private void cargarEstudiantes(Tutores tutorLogeado) {
        List<Estudiantes> lista = getListaEstudiantesDelTutor(tutorLogeado);
        cmbEstudiantes.removeAllItems();
        for (Estudiantes est : lista) {
            cmbEstudiantes.addItem(est);
        }
    }

    public Estudiantes getEstudianteSeleccionado() {
        return estudianteSeleccionado;
    }

    private void establecerPerfilEstudianteValido(){

        Estudiantes seleccionado = (Estudiantes) cmbEstudiantes.getSelectedItem();
        if (seleccionado != null) {
            estudianteSeleccionado = seleccionado;
            setEstudianteLogeado(seleccionado);
            dispose();
        } else {
            new CustomDialog(null, "Advertencia", "Debe seleccionar un estudiante.", "ONLY_OK").setVisible(true);
        }
    }
}