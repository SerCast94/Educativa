package Vista.Profesor;

import BackUtil.MonitorInactividad;
import Vista.Profesor.Modificar.ActualizarProfesoresProfesor;
import Vista.Profesor.Tablas.GestionAsistenciaProfesor;
import Vista.Profesor.Tablas.GestionHistorialAcademicoProfesor;
import Vista.Profesor.Tablas.GestionHorarioProfesor;
import Vista.Util.CustomDialog;
import Vista.LoginGUI;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import static Controlador.ControladorLogin.profesorLogeado;
import static Vista.Util.EstiloComponentes.establecerIcono;

/**
 * VistaPrincipalProfesor es la clase principal de la interfaz gráfica para el profesor.
 * Desde esta clase se muestra un menú lateral y se permite la navegación entre diferentes vistas.
 */
public class VistaPrincipalProfesor extends JFrame {
    private MenuLateralProfesor menu;
    private JPanel contentPanel;
    private static VistaPrincipalProfesor instancia;
    private MonitorInactividad temporizadorInactividad;


    /**
     * Constructor de la clase VistaPrincipalProfesor.
     * Inicializa la ventana principal y configura el menú lateral.
     */
    public VistaPrincipalProfesor() {
        setTitle("Colegio Salesiano San Francisco de Sales - EDUCATIVA");
        setSize(1920, 1080);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        establecerIcono(this);
        setLayout(new BorderLayout());
        contentPanel = new JPanel(new BorderLayout());

        menu = new MenuLateralProfesor(new MenuListener());
        add(menu, BorderLayout.WEST);
        add(contentPanel, BorderLayout.CENTER);
        menu.setVisible(true);
        mostrarVistaDashboardProfesor();
        instancia = this;
        setVisible(true);
        initEventos();
    }

    /**
     * Método que inicializa los eventos, en concreto volver a la ventana de login al cerrar
     * y manejar la inactividad del usuario.
     */
    void initEventos(){
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                SwingUtilities.invokeLater(() -> {
                    new LoginGUI();
                });
            }
        });

        temporizadorInactividad = new MonitorInactividad(2 * 60 * 1000, () -> {
            SwingUtilities.invokeLater(() -> {
                temporizadorInactividad.detener();
                new CustomDialog(null, "Inactividad", "Has estado inactivo durante 2 minutos. Se cerrará la sesión.", "ONLY_OK").setVisible(true);
                temporizadorInactividad.detener();
                dispose();
            });
        });
    }

    /**
     * Método que devuelve la instancia de la ventana principal.
     * @return instancia de VistaPrincipalProfesor.
     */
    public static JFrame getVistaPrincipal() {
        return instancia;
    }

    /**
     * Método que muestra la vista del dashboard del profesor.
     */
    public  void mostrarVistaDashboardProfesor() {
        contentPanel.removeAll();
        contentPanel.add(new DashboardProfesor());
        contentPanel.revalidate();
        contentPanel.repaint();
    }

    /**
     * Método que muestra la vista de gestión de estudiantes.
     */
    public void mostrarVistaHistorialAcademico() {
        contentPanel.removeAll();
        contentPanel.add(new GestionHistorialAcademicoProfesor());
        contentPanel.revalidate();
        contentPanel.repaint();
    }

    /**
     * Método que muestra la vista de gestión de asistencia.
     */
    public void mostrarVistaAsistencia() {
        contentPanel.removeAll();
        contentPanel.add(new GestionAsistenciaProfesor());
        contentPanel.revalidate();
        contentPanel.repaint();
    }

    /**
     * Método que muestra la vista de gestión de horarios.
     */
    public void mostrarVistaHorarios() {
        contentPanel.removeAll();
        contentPanel.add(new GestionHorarioProfesor());
        contentPanel.revalidate();
        contentPanel.repaint();
    }

    /**
     * Método que muestra la vista de modificación del perfil del profesor.
     */
    public void mostrarVistaModificarPerfilProfesor() {
        new ActualizarProfesoresProfesor(profesorLogeado);
    }


    /**
     * Clase interna que maneja los eventos de los botones del menú lateral.
     */
    private class MenuListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            Object source = e.getSource();

            if (source instanceof JButton) {
                JButton boton = (JButton) source;
                String accionMenu = boton.getText().trim();

                switch (accionMenu) {
                    case "Historial Académico":
                        mostrarVistaHistorialAcademico();
                        break;
                    case "Asistencia":
                        mostrarVistaAsistencia();
                        break;
                    case "Horarios":
                        mostrarVistaHorarios();
                        break;
                    case "Modificar Perfil":
                        mostrarVistaModificarPerfilProfesor();
                        break;
                    default:
                        new CustomDialog(null, "Error", "Funcionalidad no implementada", "OK_CANCEL");
                        break;
                }
            } else if ("Dashboard".equals(e.getActionCommand())) {
                mostrarVistaDashboardProfesor();
            } else {
                new CustomDialog(null, "Error", "Evento no reconocido", "OK_CANCEL");
            }
        }
    }
}