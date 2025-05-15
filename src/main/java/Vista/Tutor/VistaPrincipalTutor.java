package Vista.Tutor;

import BackUtil.MonitorInactividad;
import Controlador.ControladorLogin;
import Mapeo.Estudiantes;
import Vista.Estudiante.DashboardEstudiante;
import Vista.Estudiante.Modificar.ActualizarEstudiantesEstudiante;
import Vista.Estudiante.Tablas.*;
import Vista.Tutor.Modificar.ActualizarTutoresTutor;
import Vista.Util.CustomDialog;
import Vista.LoginGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import static Controlador.ControladorLogin.estudianteLogeado;
import static Controlador.ControladorLogin.tutorLogeado;

/**
 * VistaPrincipalTutor es la clase principal de la interfaz gráfica para el tutor.
 * Desde esta clase se muestra un menú lateral y se permite la navegación entre diferentes vistas.
 */
public class VistaPrincipalTutor extends JFrame {
    private MenuLateralTutor menu;
    private JPanel contentPanel;
    private static VistaPrincipalTutor instancia;
    private MonitorInactividad temporizadorInactividad;


    /**
     * Constructor de la clase VistaPrincipalTutor.
     * Inicializa la ventana principal y configura el menú lateral.
     */
    public VistaPrincipalTutor() {


        setTitle("Colegio Salesiano San Francisco de Sales - EDUCATIVA");
        setSize(1920, 1080);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());
        contentPanel = new JPanel(new BorderLayout());

        menu = new MenuLateralTutor(new MenuListener());
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
     * Método que devuelve la instancia de la ventana principal.
     * @return instancia de VistaPrincipalTutor.
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
     * Método que muestra la vista de gestión del historial académico del estudiante seleccionado.
     */
    public void mostrarVistaHistorialAcademico() {
        contentPanel.removeAll();
        contentPanel.add(new GestionHistorialAcademicoEstudiante());
        contentPanel.revalidate();
        contentPanel.repaint();
    }

    /**
     * Método que muestra la vista de gestión de asistencia del estudiante seleccionado.
     */
    public void mostrarVistaAsistencia() {
        contentPanel.removeAll();
        contentPanel.add(new GestionAsistenciaEstudiante());
        contentPanel.revalidate();
        contentPanel.repaint();
    }

    /**
     * Método que muestra la vista de gestión de horarios del estudiante seleccionado.
     */
    public void mostrarVistaHorarios() {
        contentPanel.removeAll();
        contentPanel.add(new GestionHorarioEstudiante());
        contentPanel.revalidate();
        contentPanel.repaint();
    }

    /**
     * Método que muestra la vista de gestión de eventos del estudiante seleccionado.
     */
    public void mostrarVistaEventos() {
        contentPanel.removeAll();
        contentPanel.add(new GestionEventosEstudiante());
        contentPanel.revalidate();
        contentPanel.repaint();
    }

    /**
     * Método que muestra la vista de gestión de profesores del estudiante seleccionado.
     */
    public void mostrarVistaProfesores() {
        contentPanel.removeAll();
        contentPanel.add(new GestionProfesoresEstudiante());
        contentPanel.revalidate();
        contentPanel.repaint();
    }

    /**
     * Método que abre un dialogo con un combo box para cambiar el estudiante seleccionado.
     */
    public void cambiarPerfilEstudiante(){
        SeleccionarEstudianteDialog dialog = new SeleccionarEstudianteDialog(tutorLogeado);
        dialog.setModal(true);
        dialog.setVisible(true);

        menu.actualizarEstudianteGestionado(ControladorLogin.estudianteLogeado.toString());
        mostrarVistaDashboardEstudiante();

        new CustomDialog(null, "Perfil Modificado", "El perfil del estudiante ha sido modificado correctamente", "OK_CANCEL");
    }

    /**
     * Método que muestra la vista de modificación de perfil del estudiante.
     */
    public void mostrarVistaModificarPerfilEstudiante() {
        new ActualizarEstudiantesEstudiante(estudianteLogeado);
    }

    /**
     * Método que muestra la vista de modificación de perfil del tutor.
     */
    public void mostrarVistaModificarPerfilTutor() {
        new ActualizarTutoresTutor(tutorLogeado);
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
                    case "Cambiar Estudiante":
                        cambiarPerfilEstudiante();
                        break;
                    case "Modificar Estudiante":
                        mostrarVistaModificarPerfilEstudiante();
                        break;
                    case "Modificar Perfil":
                        mostrarVistaModificarPerfilTutor();
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