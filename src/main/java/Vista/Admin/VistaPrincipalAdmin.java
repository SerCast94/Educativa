package Vista.Admin;

import BackUtil.MonitorInactividad;
import Vista.Admin.Modificar.ActualizarAdministradoresAdmin;
import Vista.Admin.Tablas.*;
import Vista.Util.CustomDialog;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import static Vista.Util.EstiloComponentes.establecerIcono;


import Vista.LoginGUI;


/**
 * VistaPrincipalAdmin es la clase principal de la interfaz gráfica para el administrador.
 * Desde esta clase se muestra un menú lateral y se permite la navegación entre diferentes vistas.
 */
public class VistaPrincipalAdmin extends JFrame {
    private MenuLateralAdmin menu;
    private JPanel principalPanel;
    private static VistaPrincipalAdmin instancia;
    private MonitorInactividad temporizadorInactividad;


    /**
     * Constructor de la clase VistaPrincipalAdmin.
     * Inicializa la ventana principal y configura el menú lateral.
     */
    public VistaPrincipalAdmin() {
        setTitle("Colegio Salesiano San Francisco de Sales - EDUCATIVA");
        setSize(1920, 1080);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        establecerIcono(this);
        setLayout(new BorderLayout());
        principalPanel = new JPanel(new BorderLayout());

        menu = new MenuLateralAdmin(new MenuListener());
        add(menu, BorderLayout.WEST);
        add(principalPanel, BorderLayout.CENTER);
        menu.setVisible(true);
        mostrarVistaDashboardAdmin();
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
     * @return instancia de VistaPrincipalAdmin.
     */
    public static JFrame getVistaPrincipal() {
        return instancia;
    }

    /**
     * Método que muestra la vista del dashboard del administrador.
     */
    public  void mostrarVistaDashboardAdmin() {
        principalPanel.removeAll();
        principalPanel.add(new DashboardAdmin());
        principalPanel.revalidate();
        principalPanel.repaint();
    }

    /**
     * Método que muestra la vista de gestión de estudiantes.
     */
    public void mostrarVistaEstudiantes() {
        principalPanel.removeAll();
        principalPanel.add(new GestionEstudiantesAdmin());
        principalPanel.revalidate();
        principalPanel.repaint();
    }

    /**
     * Método que muestra la vista de gestión de profesores.
     */
    public void mostrarVistaProfesores() {
        principalPanel.removeAll();
        principalPanel.add(new GestionProfesoresAdmin());
        principalPanel.revalidate();
        principalPanel.repaint();
    }
    /**
     * Método que muestra la vista de gestión de matrículas.
     */
    public void mostrarVistaMatriculas() {
        principalPanel.removeAll();
        principalPanel.add(new GestionMatriculasAdmin());
        principalPanel.revalidate();
        principalPanel.repaint();
    }

    /**
     * Método que muestra la vista de gestión de cursos.
     */
    public void mostrarVistaCursos() {
        principalPanel.removeAll();
        principalPanel.add(new GestionCursosAdmin());
        principalPanel.revalidate();
        principalPanel.repaint();
    }

    /**
     * Método que muestra la vista de gestión de asignaturas.
     */
    public void mostrarVistaAsignaturas(){
        principalPanel.removeAll();
        principalPanel.add(new GestionAsignaturasAdmin());
        principalPanel.revalidate();
        principalPanel.repaint();
    }

    /**
     * Método que muestra la vista de gestión de historial académico.
     */
    public void mostrarVistaHistorialAcademico() {
        principalPanel.removeAll();
        principalPanel.add(new GestionHistorialAcademicoAdmin());
        principalPanel.revalidate();
        principalPanel.repaint();
    }

    /**
     * Método que muestra la vista de gestión de asistencia.
     */
    public void mostrarVistaAsistencia() {
        principalPanel.removeAll();
        principalPanel.add(new GestionAsistenciaAdmin());
        principalPanel.revalidate();
        principalPanel.repaint();
    }

    /**
     * Método que muestra la vista de gestión de eventos.
     */
    public void mostrarVistaEventos() {
        principalPanel.removeAll();
        principalPanel.add(new GestionEventosAdmin());
        principalPanel.revalidate();
        principalPanel.repaint();
    }

    /**
     * Método que muestra la vista de gestión de horarios.
     */
    public void mostrarVistaHorarios() {
        principalPanel.removeAll();
        principalPanel.add(new GestionHorarioAdmin());
        principalPanel.revalidate();
        principalPanel.repaint();
    }

    /**
     * Método que muestra la vista de gestión de actividades extraescolares.
     */
    public void mostrarVistaActividadesExtraescolares() {
        principalPanel.removeAll();
        principalPanel.add(new GestionExtraescolaresAdmin());
        principalPanel.revalidate();
        principalPanel.repaint();
    }

    /**
     * Método que muestra la vista de gestión de tutores.
     */
    public void mostrarVistaTutores() {
        principalPanel.removeAll();
        principalPanel.add(new GestionTutoresAdmin());
        principalPanel.revalidate();
        principalPanel.repaint();
    }

    /**
     * Método que muestra la vista de gestión de becas.
     */
    public void mostrarVistaBecas() {
        principalPanel.removeAll();
        principalPanel.add(new GestionBecasAdmin());
        principalPanel.revalidate();
        principalPanel.repaint();
    }

    /**
     * Método que muestra la vista de gestión de convalidaciones.
     */
    public void mostrarVistaConvalidaciones() {
        principalPanel.removeAll();
        principalPanel.add(new GestionConvalidacionesAdmin());
        principalPanel.revalidate();
        principalPanel.repaint();
    }

    /**
     * Método que muestra la vista de reportes.
     */
    public void mostrarVistaReportes() {
        principalPanel.removeAll();
        principalPanel.add(new Reportes());
        principalPanel.revalidate();
        principalPanel.repaint();
    }

    /**
     * Método que muestra la vista de modificación de perfil del administrador.
     */
    public void mostrarVistaModificarPerfilAdmin() {
        new ActualizarAdministradoresAdmin();
    }

    /**
     * Clase interna que maneja los eventos de los botones del menú lateral.
     */
    private class MenuListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            Object objetoFuente = e.getSource();

            if (objetoFuente instanceof JButton) {
                JButton botonMenu = (JButton) objetoFuente;
                String accionMenu = botonMenu.getText().trim();

                switch (accionMenu) {
                    case "Dashboard":
                        mostrarVistaDashboardAdmin();
                        break;
                    case "Estudiantes":
                        mostrarVistaEstudiantes();
                        break;
                    case "Profesores":
                        mostrarVistaProfesores();
                        break;
                    case "Matriculas":
                        mostrarVistaMatriculas();
                        break;
                    case "Cursos":
                        mostrarVistaCursos();
                        break;
                    case "Asignaturas":
                        mostrarVistaAsignaturas();
                        break;
                    case "Historial Académico":
                        mostrarVistaHistorialAcademico();
                        break;
                    case "Asistencia":
                        mostrarVistaAsistencia();
                        break;
                    case "Eventos":
                        mostrarVistaEventos();
                        break;
                    case "Horarios":
                        mostrarVistaHorarios();
                        break;
                    case "Act. Extraescolares":
                        mostrarVistaActividadesExtraescolares();
                        break;
                    case "Tutores":
                        mostrarVistaTutores();
                        break;
                    case "Becas":
                        mostrarVistaBecas();
                        break;
                    case "Convalidaciones":
                        mostrarVistaConvalidaciones();
                        break;
                    case "Reportes":
                        mostrarVistaReportes();
                        break;
                    case "Modificar Perfil":
                        mostrarVistaModificarPerfilAdmin();
                        break;
                    default:
                        new CustomDialog(null, "Error", "Funcionalidad no implementada", "OK_CANCEL");
                        break;
                }
            } else if ("Dashboard".equals(e.getActionCommand())) {
                mostrarVistaDashboardAdmin();
            } else {
                new CustomDialog(null, "Error", "Evento no reconocido", "OK_CANCEL");
            }
        }
    }
}