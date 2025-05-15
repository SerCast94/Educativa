package Vista.Tutor;

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

/**
 * SeleccionarEstudianteDialog es una ventana de diálogo que permite al tutor seleccionar un estudiante.
 * Esta clase extiende JDialog y proporciona una interfaz gráfica simole para la el perfil del estudiante.
 */
public class SeleccionarEstudianteDialog extends JDialog {
    private Estudiantes estudianteSeleccionado;
    private JComboBox<Estudiantes> cmbEstudiantes = new JComboBox<>();
    private JButton btnAceptar = new Boton("Aceptar", Boton.tipoBoton.PRIMARY);
    private JButton btnCancelar = new Boton("Cancelar", Boton.tipoBoton.DELETE);
    private GridBagConstraints gbc;
    private Container panel;
    private JLabel lblTitulo;
    private JPanel panelBotones;

    /**
     * Constructor de la clase SeleccionarEstudianteDialog.
     * Inicializa la ventana de diálogo y carga los estudiantes del tutor logueado.
     * @param tutorLogeado El tutor que está logueado en el sistema.
     */
    public SeleccionarEstudianteDialog(Tutores tutorLogeado) {
        initGUI();
        cargarEstudiantes(tutorLogeado);
        initEventos();
    }

    /**
     * Método para inicializar los componentes gráficos principales.
     */
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

        lblTitulo = new JLabel("Seleccione un Estudiante", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 20));
        lblTitulo.setForeground(new Color(70, 70, 70));
        gbc.gridwidth = 2;
        agregarComponente(lblTitulo, 0, 0);
        gbc.gridwidth = 1;

        personalizarComboBox(cmbEstudiantes);
        setBordeNaranja(cmbEstudiantes);
        agregarComponente(cmbEstudiantes, 1, 0);

        panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        panelBotones.setBackground(new Color(251, 234, 230));
        btnAceptar.setPreferredSize(new Dimension(100, 40));
        btnCancelar.setPreferredSize(new Dimension(100, 40));
        panelBotones.add(btnAceptar);
        panelBotones.add(btnCancelar);

        gbc.gridwidth = 2;
        agregarComponente(panelBotones, 2, 0);
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
     * Método para inicializar los eventos de los componentes.
     */
    private void initEventos() {

        if(estudianteLogeado != null){
            cmbEstudiantes.setSelectedItem(estudianteLogeado);
        }

        btnCancelar.addActionListener(e -> dispose());

        btnAceptar.addActionListener(e ->  establecerPerfilEstudianteValido());

    }

    /**
     * Método para cargar los estudiantes del tutor logueado en el combo box.
     * @param tutorLogeado El tutor que está logueado en el sistema.
     */
    private void cargarEstudiantes(Tutores tutorLogeado) {
        List<Estudiantes> lista = getListaEstudiantesDelTutor(tutorLogeado);
        cmbEstudiantes.removeAllItems();
        for (Estudiantes est : lista) {
            cmbEstudiantes.addItem(est);
        }
    }

    /**
     * Método para obtener el estudiante seleccionado.
     * @return El estudiante seleccionado.
     */
    public Estudiantes getEstudianteSeleccionado() {
        return estudianteSeleccionado;
    }

    /**
     * Método para validar y establecer el estudiante seleccionado.
     */
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