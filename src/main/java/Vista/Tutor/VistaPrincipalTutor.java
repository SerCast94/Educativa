package Vista.Tutor;

import Mapeo.Estudiantes;
import Mapeo.Tutores;
import Vista.Estudiante.DashboardEstudiante;
import Vista.Estudiante.Modificar.ActualizarEstudiantesEstudiante;
import Vista.Estudiante.Tablas.*;
import Vista.Estudiante.VistaPrincipalEstudiante;
import Vista.Tutor.Modificar.ActualizarTutoresTutor;
import Vista.Util.CustomDialog;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import static Controlador.ControladorLogin.estudianteLogeado;
import static Controlador.ControladorLogin.tutorLogeado;

public class VistaPrincipalTutor extends JFrame {
    private MenuLateralTutor menu;
    private JPanel contentPanel;
    private static VistaPrincipalTutor instancia;



    public VistaPrincipalTutor() {


        setTitle("Colegio Salesiano San Francisco de Sales - EDUCATIVA");
        setSize(1920, 1080);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        contentPanel = new JPanel(new BorderLayout());

        menu = new MenuLateralTutor(new MenuListener());
        add(menu, BorderLayout.WEST);
        add(contentPanel, BorderLayout.CENTER);
        menu.setVisible(true);
        mostrarVistaDashboardEstudiante();
        instancia = this;
        setVisible(true);
    }

    public static JFrame getVistaPrincipal() {
        return instancia;
    }

    public  void mostrarVistaDashboardEstudiante() {
        contentPanel.removeAll();
        contentPanel.add(new DashboardEstudiante());
        contentPanel.revalidate();
        contentPanel.repaint();
    }

    public void mostrarVistaHistorialAcademico() {
        contentPanel.removeAll();
        contentPanel.add(new GestionHistorialAcademicoEstudiante());
        contentPanel.revalidate();
        contentPanel.repaint();
    }

    public void mostrarVistaAsistencia() {
        contentPanel.removeAll();
        contentPanel.add(new GestionAsistenciaEstudiante());
        contentPanel.revalidate();
        contentPanel.repaint();
    }

    public void mostrarVistaHorarios() {
        contentPanel.removeAll();
        contentPanel.add(new GestionHorarioEstudiante());
        contentPanel.revalidate();
        contentPanel.repaint();
    }

    public void mostrarVistaEventos() {
        contentPanel.removeAll();
        contentPanel.add(new GestionEventosEstudiante());
        contentPanel.revalidate();
        contentPanel.repaint();
    }

    public void mostrarVistaProfesores() {
        contentPanel.removeAll();
        contentPanel.add(new GestionProfesoresEstudiante());
        contentPanel.revalidate();
        contentPanel.repaint();
    }

    public void cambiarPerfilEstudiante(){
        SeleccionarEstudianteDialog dialog = new SeleccionarEstudianteDialog(tutorLogeado);
        dialog.setModal(true);
        dialog.setVisible(true);
        mostrarVistaDashboardEstudiante();
        new CustomDialog(null, "Perfil Modificado", "El perfil del estudiante ha sido modificado correctamente", "OK_CANCEL");
    }

    public void mostrarVistaModificarPerfilEstudiante() {
        new ActualizarEstudiantesEstudiante(estudianteLogeado);
    }


    public void mostrarVistaModificarPerfilTutor() {
        new ActualizarTutoresTutor(tutorLogeado);
    }

    private class MenuListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            Object source = e.getSource();

            if (source instanceof JButton) {
                JButton button = (JButton) source;
                String actionCommand = button.getText().trim();

                switch (actionCommand) {
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