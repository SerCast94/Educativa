package Vista.Tutor;

import Controlador.ControladorLogin;
import Vista.Util.Boton;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

/**
 * MenuLateralTutor es la clase que representa el menú lateral de la interfaz gráfica para el tutor.
 * Contiene botones para navegar entre diferentes secciones de la aplicación.
 */
public class MenuLateralTutor extends JPanel {
    private Map<String, JButton> botonesMenu;
    private JPanel panelBotones;
    private JPanel panelLogo;
    private JLabel logoLabel;
    private ImageIcon icono;
    private ImageIcon iconoInferior;
    private Boton botonSuperior;
    private Boton botonInferior;
    private JPanel panelInferior;
    private JLabel usuarioLogeado;
    private JLabel estudianteGestionado;
    private JPanel usuarioPanel;
    private JPanel estudiantePanel;


    /**
     * Constructor de la clase MenuLateralTutor.
     * Inicializa el menú lateral y sus componentes.
     * @param listener ActionListener para manejar los eventos de los botones.
     */
    public MenuLateralTutor(ActionListener listener) {
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(250, 0));

        panelLogo = new JPanel();
        panelLogo.setPreferredSize(new Dimension(250, 150));
        panelLogo.setBackground(new Color(251, 234, 230));

        logoLabel = new JLabel(new ImageIcon(Objects.requireNonNull(getClass().getResource("/img/logomini.png"))));
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

        panelBotones = new JPanel();
        panelBotones.setLayout(new BoxLayout(panelBotones, BoxLayout.Y_AXIS));
        panelBotones.setBackground(new Color(251, 234, 230));

        botonesMenu = new LinkedHashMap<>();

        String[][] opciones = {
                {"   Historial Académico", "course"},
                {"   Asistencia", "attendance"},
                {"   Horarios", "schedule"},
                {"   Eventos", "event"},
                {"   Profesores", "teacher"},
        };

        for (String[] opcion : opciones) {
            icono = new ImageIcon(Objects.requireNonNull(getClass().getResource("/icons/" + opcion[1] + ".png")));
            icono.setImage(icono.getImage().getScaledInstance(24, 24, Image.SCALE_SMOOTH));

            botonSuperior = new Boton(opcion[0], Boton.tipoBoton.PRIMARY);
            botonSuperior.setIcon(icono);
            botonSuperior.setAlignmentX(Component.CENTER_ALIGNMENT);
            botonSuperior.setPreferredSize(new Dimension(230, 40));
            botonSuperior.setMinimumSize(new Dimension(230, 40));
            botonSuperior.setMaximumSize(new Dimension(230, 40));
            botonSuperior.setHorizontalAlignment(SwingConstants.LEFT);
            botonSuperior.setActionCommand(opcion[0]);
            botonSuperior.addActionListener(listener);
            botonesMenu.put(opcion[0], botonSuperior);
            panelBotones.add(Box.createVerticalStrut(5));
            panelBotones.add(botonSuperior);
        }

        panelInferior = new JPanel();
        panelInferior.setLayout(new BoxLayout(panelInferior, BoxLayout.Y_AXIS));
        panelInferior.setBackground(new Color(251, 234, 230));
        panelInferior.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        String[][] opcionesInferiores = {
                {"   Cambiar Estudiante", "opciones"},
                {"   Modificar Estudiante", "opciones"},
                {"   Modificar Perfil", "opciones"}
        };

        for (String[] opcion : opcionesInferiores) {
            iconoInferior = new ImageIcon(Objects.requireNonNull(getClass().getResource("/icons/" + opcion[1] + ".png")));
            iconoInferior.setImage(iconoInferior.getImage().getScaledInstance(24, 24, Image.SCALE_SMOOTH));

            botonInferior = new Boton(opcion[0], Boton.tipoBoton.PRIMARY);
            botonInferior.setIcon(iconoInferior);
            botonInferior.setAlignmentX(Component.CENTER_ALIGNMENT);
            botonInferior.setPreferredSize(new Dimension(260, 40));
            botonInferior.setMinimumSize(new Dimension(260, 40));
            botonInferior.setMaximumSize(new Dimension(260, 40));
            botonInferior.setHorizontalAlignment(SwingConstants.LEFT);
            botonInferior.setActionCommand(opcion[0]);
            botonInferior.addActionListener(listener);
            botonesMenu.put(opcion[0], botonInferior);
            panelInferior.add(Box.createVerticalStrut(5));
            panelInferior.add(botonInferior);
        }

        estudianteGestionado = new JLabel(" Estudiante: " + ControladorLogin.estudianteLogeado);
        estudianteGestionado.setFont(new Font("Arial", Font.PLAIN, 12));
        estudianteGestionado.setForeground(new Color(0, 0, 0));

        estudiantePanel = new JPanel(new BorderLayout());
        estudiantePanel.setOpaque(false);
        estudiantePanel.setMaximumSize(new Dimension(250, 20));
        estudiantePanel.add(estudianteGestionado, BorderLayout.WEST);
        panelInferior.add(Box.createVerticalStrut(10));
        panelInferior.add(estudiantePanel);

        usuarioLogeado = new JLabel(" Usuario: " + ControladorLogin.tutorLogeado);
        usuarioLogeado.setFont(new Font("Arial", Font.PLAIN, 12));
        usuarioLogeado.setForeground(new Color(0, 0, 0));

        usuarioPanel = new JPanel(new BorderLayout());
        usuarioPanel.setOpaque(false);
        usuarioPanel.setMaximumSize(new Dimension(250, 20));
        usuarioPanel.add(usuarioLogeado, BorderLayout.WEST);
        panelInferior.add(Box.createVerticalStrut(10));
        panelInferior.add(usuarioPanel);



        add(panelLogo, BorderLayout.NORTH);
        add(panelBotones, BorderLayout.CENTER);
        add(panelInferior, BorderLayout.SOUTH);
    }

    public void actualizarEstudianteGestionado(String nuevoEstudiante) {
        estudianteGestionado.setText(" Estudiante: " + nuevoEstudiante);
    }
}
