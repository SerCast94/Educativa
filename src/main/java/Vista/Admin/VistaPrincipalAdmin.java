package Vista.Admin;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VistaPrincipalAdmin extends JFrame {
    private MenuLateralAdmin menu;
    private JPanel contentPanel;

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
        setVisible(true);
    }

    private void mostrarVistaDashboardAdmin() {
        contentPanel.removeAll();
        contentPanel.add(new DashboardAdmin());
        contentPanel.revalidate();
        contentPanel.repaint();
    }

    private void mostrarVistaEstudiantes() {
        contentPanel.removeAll();
        contentPanel.add(new GestionEstudiantesAdmin());
        contentPanel.revalidate();
        contentPanel.repaint();
    }

    private void mostrarVistaProfesores() {
        contentPanel.removeAll();
        contentPanel.add(new DashboardAdmin());
        contentPanel.revalidate();
        contentPanel.repaint();
    }
    private void mostrarVistaMatriculas() {
        contentPanel.removeAll();
        contentPanel.add(new DashboardAdmin());
        contentPanel.revalidate();
        contentPanel.repaint();
    }

    private void mostrarVistaCursos() {
        contentPanel.removeAll();
        contentPanel.add(new DashboardAdmin());
        contentPanel.revalidate();
        contentPanel.repaint();
    }

    private void mostrarVistaHistorialAcademico() {
        contentPanel.removeAll();
        contentPanel.add(new DashboardAdmin());
        contentPanel.revalidate();
        contentPanel.repaint();
    }

    private void mostrarVistaAsistencia() {
        contentPanel.removeAll();
        contentPanel.add(new DashboardAdmin());
        contentPanel.revalidate();
        contentPanel.repaint();
    }

    private void mostrarVistaEventos() {
        contentPanel.removeAll();
        contentPanel.add(new DashboardAdmin());
        contentPanel.revalidate();
        contentPanel.repaint();
    }

    private void mostrarVistaHorarios() {
        contentPanel.removeAll();
        contentPanel.add(new DashboardAdmin());
        contentPanel.revalidate();
        contentPanel.repaint();
    }

    private void mostrarVistaActividadesExtraescolares() {
        contentPanel.removeAll();
        contentPanel.add(new DashboardAdmin());
        contentPanel.revalidate();
        contentPanel.repaint();
    }

    private void mostrarVistaTutores() {
        contentPanel.removeAll();
        contentPanel.add(new DashboardAdmin());
        contentPanel.revalidate();
        contentPanel.repaint();
    }

    private void mostrarVistaBecas() {
        contentPanel.removeAll();
        contentPanel.add(new DashboardAdmin());
        contentPanel.revalidate();
        contentPanel.repaint();
    }

    private void mostrarVistaConvalidaciones() {
        contentPanel.removeAll();
        contentPanel.add(new DashboardAdmin());
        contentPanel.revalidate();
        contentPanel.repaint();
    }

    private void mostrarVistaReportes() {
        contentPanel.removeAll();
        contentPanel.add(new DashboardAdmin());
        contentPanel.revalidate();
        contentPanel.repaint();
    }

    private class MenuListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            Object source = e.getSource(); // Obtiene el origen del evento

            if (source instanceof JButton) {
                JButton button = (JButton) source; // Realiza el cast solo si es un JButton
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
                    default:
                        JOptionPane.showMessageDialog(null, "Funcionalidad no implementada");
                        break;
                }
            } else if ("Dashboard".equals(e.getActionCommand())) {
                // Maneja el evento del logo aquí
                mostrarVistaDashboardAdmin();
            } else {
                JOptionPane.showMessageDialog(null, "Evento no reconocido");
            }
        }
    }

    public static void main(String[] args) {
        new VistaPrincipalAdmin();
    }
}
