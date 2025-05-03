package Vista.Admin;

import Vista.Admin.Modificar.ActualizarAdministradoresAdmin;
import Vista.Admin.Tablas.*;
import Vista.Util.CustomDialog;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VistaPrincipalAdmin extends JFrame {
    private MenuLateralAdmin menu;
    private JPanel contentPanel;
    private static VistaPrincipalAdmin instancia;


    public VistaPrincipalAdmin() {
        setTitle("Colegio Salesiano San Francisco de Sales - EDUCATIVA");
        setSize(1920, 1080);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        contentPanel = new JPanel(new BorderLayout());

        menu = new MenuLateralAdmin(new MenuListener());
        add(menu, BorderLayout.WEST);
        add(contentPanel, BorderLayout.CENTER);
        menu.setVisible(true);
        mostrarVistaDashboardAdmin();
        instancia = this;
        setVisible(true);
    }

    public static JFrame getVistaPrincipal() {
        return instancia;
    }

    public  void mostrarVistaDashboardAdmin() {
        contentPanel.removeAll();
        contentPanel.add(new DashboardAdmin());
        contentPanel.revalidate();
        contentPanel.repaint();
    }

    public void mostrarVistaEstudiantes() {
        contentPanel.removeAll();
        contentPanel.add(new GestionEstudiantesAdmin());
        contentPanel.revalidate();
        contentPanel.repaint();
    }

    public void mostrarVistaProfesores() {
        contentPanel.removeAll();
        contentPanel.add(new GestionProfesoresAdmin());
        contentPanel.revalidate();
        contentPanel.repaint();
    }
    public void mostrarVistaMatriculas() {
        contentPanel.removeAll();
        contentPanel.add(new GestionMatriculasAdmin());
        contentPanel.revalidate();
        contentPanel.repaint();
    }

    public void mostrarVistaCursos() {
        contentPanel.removeAll();
        contentPanel.add(new GestionCursosAdmin());
        contentPanel.revalidate();
        contentPanel.repaint();
    }

    public void mostrarVistaAsignaturas(){
        contentPanel.removeAll();
        contentPanel.add(new GestionAsignaturasAdmin());
        contentPanel.revalidate();
        contentPanel.repaint();
    }

    public void mostrarVistaHistorialAcademico() {
        contentPanel.removeAll();
        contentPanel.add(new GestionHistorialAcademicoAdmin());
        contentPanel.revalidate();
        contentPanel.repaint();
    }

    public void mostrarVistaAsistencia() {
        contentPanel.removeAll();
        contentPanel.add(new GestionAsistenciaAdmin());
        contentPanel.revalidate();
        contentPanel.repaint();
    }

    public void mostrarVistaEventos() {
        contentPanel.removeAll();
        contentPanel.add(new GestionEventosAdmin());
        contentPanel.revalidate();
        contentPanel.repaint();
    }

    public void mostrarVistaHorarios() {
        contentPanel.removeAll();
        contentPanel.add(new GestionHorarioAdmin());
        contentPanel.revalidate();
        contentPanel.repaint();
    }

    public void mostrarVistaActividadesExtraescolares() {
        contentPanel.removeAll();
        contentPanel.add(new GestionExtraescolaresAdmin());
        contentPanel.revalidate();
        contentPanel.repaint();
    }

    public void mostrarVistaTutores() {
        contentPanel.removeAll();
        contentPanel.add(new GestionTutoresAdmin());
        contentPanel.revalidate();
        contentPanel.repaint();
    }

    public void mostrarVistaBecas() {
        contentPanel.removeAll();
        contentPanel.add(new GestionBecasAdmin());
        contentPanel.revalidate();
        contentPanel.repaint();
    }

    public void mostrarVistaConvalidaciones() {
        contentPanel.removeAll();
        contentPanel.add(new GestionConvalidacionesAdmin());
        contentPanel.revalidate();
        contentPanel.repaint();
    }

    public void mostrarVistaReportes() {
        contentPanel.removeAll();
        contentPanel.add(new Reportes());
        contentPanel.revalidate();
        contentPanel.repaint();
    }

    public void mostrarVistaModificarPerfilAdmin() {
        new ActualizarAdministradoresAdmin();
    }



    private class MenuListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            Object source = e.getSource();

            if (source instanceof JButton) {
                JButton button = (JButton) source;
                String actionCommand = button.getText().trim();

                switch (actionCommand) {
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