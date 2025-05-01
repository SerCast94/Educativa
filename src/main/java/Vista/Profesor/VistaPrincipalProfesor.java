package Vista.Profesor;

import Mapeo.Profesores;
import Vista.Profesor.Modificar.ActualizarProfesoresProfesor;
import Vista.Profesor.Tablas.GestionAsistenciaProfesor;
import Vista.Profesor.Tablas.GestionHistorialAcademicoProfesor;
import Vista.Profesor.Tablas.GestionHorarioProfesor;
import Vista.Util.CustomDialog;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VistaPrincipalProfesor extends JFrame {
    private MenuLateralProfesor menu;
    private JPanel contentPanel;
    private static VistaPrincipalProfesor instancia;
    public static Profesores usuarioLogeado;

    public VistaPrincipalProfesor(Profesores profesor) {
        this.usuarioLogeado = profesor;
        setTitle("Colegio Salesiano San Francisco de Sales - EDUCATIVA");
        setSize(1920, 1080);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        contentPanel = new JPanel(new BorderLayout());

        menu = new MenuLateralProfesor(new MenuListener());
        add(menu, BorderLayout.WEST);
        add(contentPanel, BorderLayout.CENTER);
        menu.setVisible(true);
        mostrarVistaDashboardProfesor();
        instancia = this;
        setVisible(true);
    }

    public static JFrame getVistaPrincipal() {
        return instancia;
    }

    public  void mostrarVistaDashboardProfesor() {
        contentPanel.removeAll();
        contentPanel.add(new DashboardProfesor());
        contentPanel.revalidate();
        contentPanel.repaint();
    }

    public void mostrarVistaHistorialAcademico() {
        contentPanel.removeAll();
        contentPanel.add(new GestionHistorialAcademicoProfesor());
        contentPanel.revalidate();
        contentPanel.repaint();
    }

    public void mostrarVistaAsistencia() {
        contentPanel.removeAll();
        contentPanel.add(new GestionAsistenciaProfesor());
        contentPanel.revalidate();
        contentPanel.repaint();
    }

    public void mostrarVistaHorarios() {
        contentPanel.removeAll();
        contentPanel.add(new GestionHorarioProfesor());
        contentPanel.revalidate();
        contentPanel.repaint();
    }


    public void mostrarVistaModificarPerfilProfesor() {
        new ActualizarProfesoresProfesor(usuarioLogeado);
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