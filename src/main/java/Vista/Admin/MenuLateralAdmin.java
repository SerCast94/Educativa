package Vista.Admin;

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
 * MenuLateralAdmin es la clase que representa el menú lateral de la interfaz gráfica para el administrador.
 * Contiene botones para navegar entre diferentes secciones de la aplicación.
 */
public class MenuLateralAdmin extends JPanel {
    private Map<String, JButton> botonesMenu;
    private JPanel panelBotones;
    private JPanel panelLogo;
    private JLabel logoLabel;
    private ImageIcon iconoSuperior;
    private Boton boton;
    private JPanel panelInferior;
    private Boton botonModificarPerfil;
    private ImageIcon iconoInferior;
    private JLabel usuarioLogeado;
    private JPanel usuarioPanel;

    /**
     * Constructor de la clase MenuLateralAdmin.
     * Inicializa el menú lateral y sus componentes.
     * @param listener ActionListener para manejar los eventos de los botones.
     */
    public MenuLateralAdmin(ActionListener listener) {
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(250, 0));

        panelLogo = new JPanel();
        panelLogo.setPreferredSize(new Dimension(250, 150));
        panelLogo.setBackground(new Color(251, 234, 230));

        logoLabel = new JLabel(new ImageIcon( Objects.requireNonNull(getClass().getResource("/img/logomini.png"))));
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
                {"   Estudiantes", "student"},
                {"   Profesores", "teacher"},
                {"   Matriculas", "book"},
                {"   Cursos", "course"},
                {"   Asignaturas", "asignatura"},
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

            iconoSuperior = new ImageIcon(Objects.requireNonNull(getClass().getResource("/icons/" + opcion[1] + ".png")));
            iconoSuperior.setImage(iconoSuperior.getImage().getScaledInstance(24, 24, Image.SCALE_SMOOTH));

            boton = new Boton(opcion[0], Boton.tipoBoton.PRIMARY);
            boton.setIcon(iconoSuperior);
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


        panelInferior = new JPanel();
        panelInferior.setLayout(new BoxLayout(panelInferior, BoxLayout.Y_AXIS));
        panelInferior.setBackground(new Color(251, 234, 230));
        panelInferior.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        botonModificarPerfil = new Boton("   Modificar Perfil", Boton.tipoBoton.PRIMARY);
        iconoInferior = new ImageIcon(Objects.requireNonNull(getClass().getResource("/icons/opciones.png")));
        iconoInferior.setImage(iconoInferior.getImage().getScaledInstance(24, 24, Image.SCALE_SMOOTH));
        botonModificarPerfil.setIcon(iconoInferior);
        botonModificarPerfil.setAlignmentX(Component.CENTER_ALIGNMENT);
        botonModificarPerfil.setPreferredSize(new Dimension(230, 40));
        botonModificarPerfil.setMinimumSize(new Dimension(230, 40));
        botonModificarPerfil.setMaximumSize(new Dimension(230, 40));
        botonModificarPerfil.setHorizontalAlignment(SwingConstants.LEFT);
        botonModificarPerfil.setActionCommand("ModificarPerfil");
        botonModificarPerfil.addActionListener(listener);

        panelInferior.add(Box.createVerticalStrut(10));
        panelInferior.add(botonModificarPerfil);

        usuarioLogeado = new JLabel("Usuario: " + ControladorLogin.adminLogeado);
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
}