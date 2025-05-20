package Vista.Estudiante;

import BackUtil.MonitorInactividad;
import Vista.Estudiante.Modificar.ActualizarEstudiantesEstudiante;
import Vista.Estudiante.Tablas.*;
import Vista.Util.CustomDialog;
import Vista.LoginGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import static Controlador.ControladorLogin.estudianteLogeado;
import static Vista.Util.EstiloComponentes.establecerIcono;

/**
 * VistaPrincipalEstudiante es la clase principal de la interfaz gráfica para el estudiante.
 * desde esta clase se muestra un menú lateral y se permite la navegación entre diferentes vistas.
 */
public class VistaPrincipalEstudiante extends JFrame {
    private MenuLateralEstudiante menu;
    private JPanel contentPanel;
    private static VistaPrincipalEstudiante instancia;
    private MonitorInactividad temporizadorInactividad;


    /**
     * Constructor de la clase VistaPrincipalEstudiante.
     * Inicializa la ventana principal y configura el menú lateral.
     */
    public VistaPrincipalEstudiante() {
        setTitle("Colegio Salesiano San Francisco de Sales - EDUCATIVA");
        setSize(1920, 1080);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        establecerIcono(this);
        setLayout(new BorderLayout());
        contentPanel = new JPanel(new BorderLayout());

        menu = new MenuLateralEstudiante(new MenuListener());
        add(menu, BorderLayout.WEST);
        add(contentPanel, BorderLayout.CENTER);
        menu.setVisible(true);
        mostrarVistaDashboardEstudiante();
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
     * Método que devuelve la instancia de la ventana principal
     * @return instancia de VistaPrincipalEstudiante.
     */
    public static JFrame getVistaPrincipal() {
        return instancia;
    }

    /**
     * Método que muestra la vista del dashboard del estudiante.
     */
    public  void mostrarVistaDashboardEstudiante() {
        contentPanel.removeAll();
        contentPanel.add(new DashboardEstudiante());
        contentPanel.revalidate();
        contentPanel.repaint();
    }

    /**
     * Método que muestra la vista del historial académico.
     */
    public void mostrarVistaHistorialAcademico() {
        contentPanel.removeAll();
        contentPanel.add(new GestionHistorialAcademicoEstudiante());
        contentPanel.revalidate();
        contentPanel.repaint();
    }

    /**
     * Método que muestra la vista de la asistencia.
     */
    public void mostrarVistaAsistencia() {
        contentPanel.removeAll();
        contentPanel.add(new GestionAsistenciaEstudiante());
        contentPanel.revalidate();
        contentPanel.repaint();
    }

    /**
     * Método que muestra la vista de los horarios
     */
    public void mostrarVistaHorarios() {
        contentPanel.removeAll();
        contentPanel.add(new GestionHorarioEstudiante());
        contentPanel.revalidate();
        contentPanel.repaint();
    }

    /**
     * Método que muestra la vista de los eventos o excursiones.
     */
    public void mostrarVistaEventos() {
        contentPanel.removeAll();
        contentPanel.add(new GestionEventosEstudiante());
        contentPanel.revalidate();
        contentPanel.repaint();
    }

    /**
     * Método que muestra la vista de los profesores.
     */
    public void mostrarVistaProfesores() {
        contentPanel.removeAll();
        contentPanel.add(new GestionProfesoresEstudiante());
        contentPanel.revalidate();
        contentPanel.repaint();
    }

    /**
     * Método que muestra la vista de modificación de perfil del estudiante.
     */
    public void mostrarVistaModificarPerfilEstudiante() {
        new ActualizarEstudiantesEstudiante(estudianteLogeado);
    }

    /**
     * Clase interna que maneja los eventos de los botones del menú lateral.
     */
    private class MenuListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            Object source = e.getSource();

            if (source instanceof JButton) {
                JButton botonMenu = (JButton) source;
                String accionMenu = botonMenu.getText().trim();

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
                    case "Eventos":
                        mostrarVistaEventos();
                        break;
                    case "Profesores":
                        mostrarVistaProfesores();
                        break;
                    case "Modificar Perfil":
                        mostrarVistaModificarPerfilEstudiante();
                        break;
                    default:
                        new CustomDialog(null, "Error", "Funcionalidad no implementada", "OK_CANCEL");
                        break;
                }
            } else if ("Dashboard".equals(e.getActionCommand())) {
                mostrarVistaDashboardEstudiante();
            } else {
                new CustomDialog(null, "Error", "Evento no reconocido", "OK_CANCEL");
            }
        }
    }
}