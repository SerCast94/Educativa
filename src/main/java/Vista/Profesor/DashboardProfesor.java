package Vista.Profesor;

import Controlador.ControladorDashBoard;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Objects;
import static Controlador.ControladorLogin.profesorLogeado;

/**
 * Clase que representa el panel del dashboard para el profesor.
 */
public class DashboardProfesor extends JPanel {

    private JPanel panelSuperior;
    private JLabel titulo;
    private JPanel panelBoton;
    private JPanel panelConMargen;
    private JPanel gridPanel;

    /**
     * Constructor de la clase DashboardProfesor.
     * Inicializa el panel y sus componentes.
     */
    public DashboardProfesor() {
        setLayout(new BorderLayout());
        initPanelSuperior();
        initGridPanel();
    }

    /**
     * Inicializa el panel superior del dashboard.
     */
    private void initPanelSuperior() {

        panelSuperior = new JPanel(new BorderLayout());
        panelSuperior.setBorder(BorderFactory.createEmptyBorder(20, 10, 10, 10));
        panelSuperior.setBackground(new Color(251, 234, 230));

        titulo = new JLabel("Colegio Salesiano San Francisco de Sales - EDUCATIVA", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 24));
        titulo.setBorder(BorderFactory.createEmptyBorder(25, 10, 30, 10));
        panelSuperior.add(titulo, BorderLayout.NORTH);

        panelBoton = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelBoton.setOpaque(false);

        panelSuperior.add(panelBoton, BorderLayout.SOUTH);
        add(panelSuperior, BorderLayout.NORTH);
    }

    /**
     * Inicializa el panel de la cuadrícula que contiene las estadísticas.
     */
    private void initGridPanel() {
        panelConMargen = new JPanel(new BorderLayout());
        panelConMargen.setBackground(new Color(251, 234, 230));
        panelConMargen.setBorder(BorderFactory.createEmptyBorder(30, 0, 10, 10));

        gridPanel = new JPanel(new GridLayout(3, 5, 10, 10));
        gridPanel.setBackground(Color.WHITE);
        gridPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));


        String[][] datos = {
                {"Estudiantes del profesor", String.valueOf(ControladorDashBoard.numeroAlumnosProfesor(profesorLogeado)), "student"},
                {"Estudiantes suspensos", String.valueOf(ControladorDashBoard.numAlumnosRecuperacionProfesor(profesorLogeado)), "attendance"},
                {"Promedio notas", String.valueOf(ControladorDashBoard.promedioNotasProfesor(profesorLogeado)), "average"},
                {"Estudiantes sobresalientes", String.valueOf(ControladorDashBoard.numAlumnosSobresalienteProfesor(profesorLogeado)), "course"},
                {"Faltas Injustificadas", String.valueOf(ControladorDashBoard.numAsistenciaNoJustificadaProfesor(profesorLogeado)), "attendance"},
                {"Horas Semanales", String.valueOf(ControladorDashBoard.numHorasTrabajadasProfesor(profesorLogeado)), "average"}
        };

        for (String[] dato : datos) {
            JPanel caja = crearCaja(dato[0], dato[1], dato[2]);
            gridPanel.add(caja);
        }

        panelConMargen.add(gridPanel, BorderLayout.CENTER);
        add(panelConMargen, BorderLayout.CENTER);
    }

    /**
     * Método que crea una caja con un título, un contador y una imagen.
     * @param titulo Título de la caja.
     * @param contador Contador a mostrar en la caja.
     * @param foto Nombre del archivo de la imagen.
     * @return Un JPanel que representa la caja creada.
     */
    private JPanel crearCaja(String titulo, String contador, String foto) {
        JPanel caja = new JPanel(new GridBagLayout());
        caja.setBackground(new Color(247, 232, 227));
        caja.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(241, 198, 177), 3),
                BorderFactory.createEmptyBorder(15, 15, 15, 15)
        ));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(10, 0, 10, 0);


        ImageIcon originalIcon = new ImageIcon(Objects.requireNonNull(getClass().getResource("/icons/" + foto + ".png")));
        Image scaledImage = originalIcon.getImage().getScaledInstance(45, 45, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);
        JLabel icono = new JLabel(scaledIcon);
        caja.add(icono, gbc);

        gbc.gridy++;
        JLabel tituloLabel = new JLabel(
                "<html><div style='"
                        + "text-align: center;"
                        + "width: 120px;"
                        + "display: -webkit-box;"
                        + "-webkit-line-clamp: 2;"
                        + "-webkit-box-orient: vertical;"
                        + "overflow: hidden;"
                        + "line-height: 1.3;"
                        + "'>"
                        + titulo
                        + "</div></html>"
        );
        tituloLabel.setFont(new Font("Arial", Font.BOLD, 15));
        caja.add(tituloLabel, gbc);

        gbc.gridy++;
        JSeparator separador = new JSeparator(SwingConstants.HORIZONTAL);
        separador.setForeground(new Color(239, 154, 108));
        separador.setBackground(new Color(239, 154, 108));
        separador.setPreferredSize(new Dimension(150, 2));
        separador.setMinimumSize(new Dimension(150, 2));
        caja.add(separador, gbc);

        gbc.gridy++;
        JLabel contadorLabel = new JLabel(contador);
        contadorLabel.setFont(new Font("Arial", Font.BOLD, 18));
        contadorLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        caja.add(contadorLabel, gbc);

        caja.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                caja.setBackground(new Color(241, 198, 177));
            }
            @Override
            public void mouseExited(MouseEvent e) {
                caja.setBackground(new Color(247, 232, 227));
            }
        });

        return caja;
    }
}