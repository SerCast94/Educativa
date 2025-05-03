package Vista.Tutor;

import Controlador.ControladorDashBoard;
import Vista.Estudiante.VistaPrincipalEstudiante;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Objects;

import static Controlador.ControladorLogin.estudianteLogeado;

public class DashboardTutor extends JPanel {

    public DashboardTutor() {
        setLayout(new BorderLayout());
        initPanelSuperior();
        initGridPanel();
    }

    private void initPanelSuperior() {

        JPanel panelSuperior = new JPanel(new BorderLayout());
        panelSuperior.setBorder(BorderFactory.createEmptyBorder(20, 10, 10, 10));
        panelSuperior.setBackground(new Color(251, 234, 230));

        JLabel titulo = new JLabel("Colegio Salesiano San Francisco de Sales - EDUCATIVA", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 24));
        titulo.setBorder(BorderFactory.createEmptyBorder(25, 10, 30, 10));
        panelSuperior.add(titulo, BorderLayout.NORTH);

        JPanel panelBoton = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelBoton.setOpaque(false);

        // por si quiero boton
//        ImageIcon icono = new ImageIcon(Objects.requireNonNull(getClass().getResource("/icons/anadir.png")));
//        icono.setImage(icono.getImage().getScaledInstance(24, 24, Image.SCALE_SMOOTH));
//        JButton btnAgregar = new Boton("Agregar Estudiante", Boton.ButtonType.PRIMARY);
//        btnAgregar.setIcon(icono);
//        btnAgregar.setPreferredSize(new Dimension(180, 30));
//        btnAgregar.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
//        panelBoton.add(btnAgregar);

        panelSuperior.add(panelBoton, BorderLayout.SOUTH);
        add(panelSuperior, BorderLayout.NORTH);
    }

    private void initGridPanel() {
        JPanel panelConMargen = new JPanel(new BorderLayout());
        panelConMargen.setBackground(new Color(251, 234, 230));
        panelConMargen.setBorder(BorderFactory.createEmptyBorder(30, 0, 10, 10));

        JPanel gridPanel = new JPanel(new GridLayout(3, 5, 10, 10));
        gridPanel.setBackground(Color.WHITE);
        gridPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));


        String[][] datos = {
                {"Nota media", String.valueOf(ControladorDashBoard.notaMediaAsignaturasEstudiante(estudianteLogeado)), "average"},
                {"Eventos por confirmar", String.valueOf(ControladorDashBoard.numEventosPorConfirmarEstudiante(estudianteLogeado)), "event"},
                {"Asignaturas suspensas", String.valueOf(ControladorDashBoard.numAsignaturasSuspensasEstudiante(estudianteLogeado)), "attendance"},
                {"Asignaturas aprobadas", String.valueOf(ControladorDashBoard.numAsignaturasAprobadasEstudiante(estudianteLogeado)), "attendance"},
                {"Faltas sin justificar", String.valueOf(ControladorDashBoard.numFaltasSinJustificarEstudiante(estudianteLogeado)), "history"},
                {"Faltas justificadas", String.valueOf(ControladorDashBoard.numFaltasJustificadasEstudiante(estudianteLogeado)), "history"}
        };

        // Crear las cajas con los datos
        for (String[] dato : datos) {
            JPanel caja = crearCaja(dato[0], dato[1], dato[2]);
            gridPanel.add(caja);
        }

        panelConMargen.add(gridPanel, BorderLayout.CENTER);
        add(panelConMargen, BorderLayout.CENTER);
    }

    private JPanel crearCaja(String titulo, String contador, String foto) {
        JPanel caja = new JPanel(new GridBagLayout());
        caja.setBackground(new Color(247, 232, 227)); // Fondo principal
        caja.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(241, 198, 177), 3), // Borde
                BorderFactory.createEmptyBorder(15, 15, 15, 15)
        ));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(10, 0, 10, 0); // Espaciado entre componentes

        // Ícono
        ImageIcon originalIcon = new ImageIcon(Objects.requireNonNull(getClass().getResource("/icons/" + foto + ".png")));
        Image scaledImage = originalIcon.getImage().getScaledInstance(45, 45, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);
        JLabel icono = new JLabel(scaledIcon);
        caja.add(icono, gbc);

        // Título
        gbc.gridy++;
        JLabel tituloLabel = new JLabel(
                "<html><div style='"
                        + "text-align: center;"
                        + "width: 120px;"                   // Ancho fijo (ajústalo según tu diseño)
                        + "display: -webkit-box;"
                        + "-webkit-line-clamp: 2;"          // Limita a 2 líneas
                        + "-webkit-box-orient: vertical;"
                        + "overflow: hidden;"               // Oculta el texto que exceda
                        + "line-height: 1.3;"               // Espaciado entre líneas
                        + "'>"
                        + titulo
                        + "</div></html>"
        );
        tituloLabel.setFont(new Font("Arial", Font.BOLD, 15));
        caja.add(tituloLabel, gbc);

        gbc.gridy++;
        JSeparator separador = new JSeparator(SwingConstants.HORIZONTAL);
        separador.setForeground(new Color(239, 154, 108)); // Color del separador
        separador.setBackground(new Color(239, 154, 108)); // Fondo del separador
        separador.setPreferredSize(new Dimension(150, 2)); // Ancho fijo y grosor
        separador.setMinimumSize(new Dimension(150, 2));   // Tamaño mínimo
        caja.add(separador, gbc);

        // Contador
        gbc.gridy++;
        JLabel contadorLabel = new JLabel(contador);
        contadorLabel.setFont(new Font("Arial", Font.BOLD, 18));
        contadorLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        caja.add(contadorLabel, gbc);

        caja.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                caja.setBackground(new Color(241, 198, 177)); // #F1C6B1 al pasar el mouse
            }
            @Override
            public void mouseExited(MouseEvent e) {
                caja.setBackground(new Color(247, 232, 227)); // Vuelve al color original
            }
        });

        return caja;
    }
}
