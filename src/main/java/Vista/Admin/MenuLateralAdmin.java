package Vista.Admin;

import Vista.Boton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

public class MenuLateralAdmin extends JPanel {
    private Map<String, JButton> botonesMenu;
    private JPanel panelBotones;

    public MenuLateralAdmin(ActionListener listener) {
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(250, 0));

        // Panel superior para el logo
        JPanel panelLogo = new JPanel();
        panelLogo.setPreferredSize(new Dimension(250, 150));
        panelLogo.setBackground(new Color(251, 234, 230));


        JLabel logoLabel = new JLabel(new ImageIcon( Objects.requireNonNull(getClass().getResource("/img/logomini.png"))));
        logoLabel.setFont(new Font("Arial", Font.BOLD, 14));
        logoLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                listener.actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "Dashboard"));
            }

            @Override
            public void mouseEntered(java.awt.event.MouseEvent e) {
                logoLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }
        });
        panelLogo.setLayout(new BorderLayout());
        panelLogo.add(logoLabel, BorderLayout.CENTER);

        // Panel de botones
        panelBotones = new JPanel();
        panelBotones.setLayout(new BoxLayout(panelBotones, BoxLayout.Y_AXIS));
        panelBotones.setBackground(new Color(251, 234, 230));


        botonesMenu = new LinkedHashMap<>();

        String[][] opciones = {
                {"   Estudiantes", "student"},
                {"   Profesores", "teacher"},
                {"   Matriculas", "book"},
                {"   Cursos", "course"},
                {"   Historial Académico", "history"},
                {"   Asistencia", "attendance"},
                {"   Eventos", "event"},
                {"   Horarios", "schedule"},
                {"   Act. Extraescolares", "sports"},
                {"   Tutores", "group"},
                {"   Becas", "scholarship"},
                {"   Convalidaciones", "approval"},
                {"   Reportes", "report"}
        };

        for (String[] opcion : opciones) {
            // Cargar y redimensionar icono
            ImageIcon icono = new ImageIcon(Objects.requireNonNull(getClass().getResource("/icons/" + opcion[1] + ".png")));
            icono.setImage(icono.getImage().getScaledInstance(24, 24, Image.SCALE_SMOOTH));

            // Crear botón
            Boton boton = new Boton(opcion[0], Boton.ButtonType.PRIMARY);
            boton.setIcon(icono);
            boton.setAlignmentX(Component.CENTER_ALIGNMENT);
            boton.setPreferredSize(new Dimension(230, 40));
            boton.setMinimumSize(new Dimension(230, 40));
            boton.setMaximumSize(new Dimension(230, 40));
            boton.setHorizontalAlignment(SwingConstants.LEFT);
            boton.setActionCommand(opcion[0]);
            boton.addActionListener(listener);
            botonesMenu.put(opcion[0], boton);
            panelBotones.add(Box.createVerticalStrut(5));
            panelBotones.add(boton);
        }

        JPanel espaciador = new JPanel();
        espaciador.setBackground(new Color(251, 234, 230));


        add(panelLogo, BorderLayout.NORTH);
        add(panelBotones, BorderLayout.CENTER);
        add(espaciador, BorderLayout.SOUTH);
    }

    public JButton getBoton(String nombre) {
        return botonesMenu.get(nombre);
    }
}