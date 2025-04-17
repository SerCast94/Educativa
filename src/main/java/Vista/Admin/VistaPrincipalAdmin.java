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
        setSize(1200, 850);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        contentPanel = new JPanel(new BorderLayout());

        menu = new MenuLateralAdmin(new MenuListener());
        add(menu, BorderLayout.WEST);
        add(contentPanel, BorderLayout.CENTER);
        menu.setVisible(true);
        mostrarVistaEstudiantes();

        setVisible(true);
    }

    private void mostrarVistaEstudiantes() {
        contentPanel.removeAll();
        contentPanel.add(new GestionEstudiantesAdmin());
        contentPanel.revalidate();
        contentPanel.repaint();
    }

    private class MenuListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JButton source = (JButton) e.getSource();
            if (source.getText().equals("Estudiantes")) {
                mostrarVistaEstudiantes();
            } else {
                JOptionPane.showMessageDialog(null, "Funcionalidad no implementada");
            }
        }
    }

    public static void main(String[] args) {
        new VistaPrincipalAdmin();
    }
}
