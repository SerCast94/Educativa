package Vista;

import Controlador.Controlador;
import Controlador.ControladorLogin;
import Mapeo.Estudiantes;
import Mapeo.Administradores;
import Mapeo.Profesores;
import Mapeo.Tutores;
import Vista.Admin.VistaPrincipalAdmin;
import Vista.Estudiante.VistaPrincipalEstudiante;
import Vista.Profesor.VistaPrincipalProfesor;
import Vista.Tutor.SeleccionarEstudianteDialog;
import Vista.Tutor.VistaPrincipalTutor;
import Vista.Util.Boton;
import Vista.Util.CustomDialog;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Objects;
import static BackUtil.Encriptador.encryptMD5;
import static Controlador.ControladorLogin.tutorLogeado;

/**
 * Clase que representa la interfaz gráfica de inicio de sesión.
 * Esta clase se encarga de crear la ventana de inicio de sesión y gestionar
 * los eventos relacionados con el inicio de sesión.
 */
public class loginGUI extends JFrame {
    private JFrame ventana;
    private JPanel panelPrincipal;
    private JPanel panelIzquierda;
    private JPanel panelDerecha;
    private JPanel PanelSuperior;
    private JPanel centroIzquierdaPanel;
    private JTextField campoUsuario;
    private JPasswordField campoPassword;
    private Boton botonIngresar;
    private JLabel logoLabel;
    private JLabel nombreCentroLabel;
    private JLabel descripcionLabel;
    private JLabel olvidoPasswordLabel;
    private JLabel nombreAppLabel;
    private JLabel loginTitulo;
    private JLabel usuarioLabel;
    private JLabel passwordLabel;
    static Controlador controlador;
    static ControladorLogin controladorLogin;

    /**
     * Constructor de la clase loginGUI.
     * Inicializa la ventana de inicio de sesión y carga las listas necesarias.
     */
    public loginGUI() {
        controlador = new Controlador();
        controlador.cargarListas();
        controladorLogin = new ControladorLogin();
        initGUI();
        initEventos();
    }

    /**
     * Método que devuelve el controlador.
     * @return
     */
    public static Controlador getControlador() {
        return controlador;
    }

    /**
     * Método que devuelve el controlador de login.
     * @return
     */
    public static ControladorLogin getControladorLogin() {
        return controladorLogin;
    }

    /**
     * Método que inicializa la interfaz gráfica de inicio de sesión.
     */
    public void initGUI() {

        ventana = new JFrame();
        ventana.setTitle("Login - Colegio Salesiano San Francisco de Sales");
        ventana.setSize(1920, 1080);
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setLocationRelativeTo(null);
        ventana.setLayout(new BorderLayout());

        // Panel superior para el nombre de la aplicación
        PanelSuperior = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                int width = getWidth();
                int height = getHeight();
                g.setColor(new Color(241, 198, 177));
                g.fillRect(0, 0, width / 2, height);
                g.setColor(Color.WHITE);
                g.fillRect(width / 2, 0, width / 2, height);
            }
        };

        PanelSuperior.setLayout(new BorderLayout());
        PanelSuperior.setBorder(BorderFactory.createEmptyBorder(10, 10, 0, 10));
        nombreAppLabel = new JLabel("Educativa");
        nombreAppLabel.setFont(new Font("Arial", Font.BOLD, 16));
        nombreAppLabel.setForeground(new Color(100, 100, 100));
        nombreAppLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        PanelSuperior.add(nombreAppLabel, BorderLayout.EAST);
        ventana.add(PanelSuperior, BorderLayout.NORTH);

        // Panel principal dividido en dos columnas
        panelPrincipal = new JPanel(new GridLayout(1, 2));
        ventana.add(panelPrincipal, BorderLayout.CENTER);

        // Panel izquierdo
        panelIzquierda = new JPanel(new BorderLayout());
        panelIzquierda.setBackground(new Color(241, 198, 177));
        panelIzquierda.setBorder(BorderFactory.createEmptyBorder(60, 60, 60, 60));
        panelPrincipal.add(panelIzquierda);

        logoLabel = new JLabel(new ImageIcon(Objects.requireNonNull(getClass().getResource("/img/logo.png"))), SwingConstants.CENTER);
        logoLabel.setFont(new Font("Arial", Font.BOLD, 48));
        logoLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 30, 0));

        nombreCentroLabel = new JLabel("<html><div style='text-align: center;'>" +
                "Colegio Salesiano San Francisco de Sales" +
                "</div></html>", SwingConstants.CENTER);

        nombreCentroLabel.setFont(new Font("Arial", Font.BOLD, 24));

        descripcionLabel = new JLabel("<html><div style='text-align: justify; text-justify: inter-word;'>" +
                "Un colegio Salesiano es una institución educativa inspirada en el método educativo salesiano." +
                " Los colegios salesianos tienen al centro de su identidad la figura de Don Bosco, sacerdote," +
                " educador y escritor italiano del siglo XIX, quien desarrolló un método educativo que pone" +
                " al alumno al centro de toda actividad educativa y formativa. Este método tiene como objetivo" +
                " promover y comprometer a todas las personas del docente y de la comunidad de la que forma parte," +
                " con una visión propia de pensamiento, de vida, actividad y compromiso práctico." +
                "</div></html>", SwingConstants.CENTER);

        descripcionLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        descripcionLabel.setBorder(BorderFactory.createEmptyBorder(30, 0, 0, 0));

        centroIzquierdaPanel = new JPanel(new BorderLayout());
        centroIzquierdaPanel.add(nombreCentroLabel, BorderLayout.CENTER);
        centroIzquierdaPanel.add(descripcionLabel, BorderLayout.SOUTH);
        centroIzquierdaPanel.setOpaque(false);

        panelIzquierda.add(logoLabel, BorderLayout.NORTH);
        panelIzquierda.add(centroIzquierdaPanel, BorderLayout.CENTER);

        // Panel derecho
        panelDerecha = new JPanel(new GridBagLayout());
        panelDerecha.setBackground(Color.WHITE);
        panelDerecha.setBorder(BorderFactory.createEmptyBorder(20, 80, 0, 80));
        panelPrincipal.add(panelDerecha);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        gbc.insets = new Insets(10, 0, 10, 0);

        // Título LOGIN
        loginTitulo = new JLabel("LOGIN", SwingConstants.CENTER);
        loginTitulo.setFont(new Font("Arial", Font.BOLD, 28));
        loginTitulo.setBorder(BorderFactory.createEmptyBorder(0, 0, 30, 0));
        panelDerecha.add(loginTitulo, gbc);

        // Usuario
        usuarioLabel = new JLabel("Usuario");
        usuarioLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        panelDerecha.add(usuarioLabel, gbc);
        campoUsuario = new JTextField();
        campoUsuario.setFont(new Font("Arial", Font.PLAIN, 14));
        campoUsuario.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.GRAY),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)
        ));
        panelDerecha.add(campoUsuario, gbc);
        panelDerecha.add(Box.createVerticalStrut(20), gbc);

        // Contraseña
        passwordLabel = new JLabel("Contraseña");
        passwordLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        panelDerecha.add(passwordLabel, gbc);
        campoPassword = new JPasswordField();
        campoPassword.setFont(new Font("Arial", Font.PLAIN, 14));
        campoPassword.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.GRAY),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)
        ));
        panelDerecha.add(campoPassword, gbc);

        // ¿Olvidaste tu contraseña?
        olvidoPasswordLabel = new JLabel("¿Olvidaste tu contraseña?", SwingConstants.CENTER);
        olvidoPasswordLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        olvidoPasswordLabel.setForeground(new Color(100, 100, 100));
        panelDerecha.add(olvidoPasswordLabel, gbc);
        panelDerecha.add(Box.createVerticalStrut(30), gbc);

        // Botón
        botonIngresar = new Boton("Ingresar",Boton.ButtonType.PRIMARY);
        botonIngresar.setFont(new Font("Arial", Font.BOLD, 16));
        botonIngresar.setBackground(new Color(230, 108, 81));
        botonIngresar.setForeground(Color.BLACK);
        botonIngresar.setFocusPainted(false);
        botonIngresar.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        panelDerecha.add(botonIngresar, gbc);

        ventana.setVisible(true);
    }

    /**
     * Método que inicializa los eventos de la interfaz gráfica.
     */
    public void initEventos() {
        botonIngresar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (funcionLogin()) ventana.dispose();
            }
        });

        olvidoPasswordLabel.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
            new CustomDialog(ventana, "Información", "Acuda a la Secretaría del centro para restaurar su contraseña", "ONLY_OK").setVisible(true);            }
        });

        campoUsuario.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (funcionLogin()) ventana.dispose();
            }
        });

        campoPassword.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (funcionLogin()) ventana.dispose();
            }
        });
    }

    /**
     * Método que comprueba el login del usuario.
     * @return true si el login es correcto, false en caso contrario.
     */
    private boolean funcionLogin() {
        String usuario = campoUsuario.getText();
        String password = new String(campoPassword.getPassword());
        String passwordHash = encryptMD5(password);

        if (getControladorLogin().comprobarLogin(usuario, passwordHash) == 1) {
            for (Estudiantes estudiante : Controlador.getListaEstudiantes()) {
                if (estudiante.getUsuario().equals(usuario) && estudiante.getContrasena().equals(passwordHash)) {

                    new CustomDialog(ventana, "Bienvenido", "Bienvenido, " + estudiante.getNombre(), "ONLY_OK").setVisible(true);
                    ventana.dispose();
                    abrirVentanaPrincipalEstudiante();
                    return true;
                }
            }
        }else if (getControladorLogin().comprobarLogin(usuario, passwordHash) == 2) {

            for (Profesores profesor : Controlador.getListaProfesores()) {
                if (profesor.getUsuario().equals(usuario) && profesor.getContrasena().equals(passwordHash)) {

                    new CustomDialog(ventana, "Bienvenido", "Bienvenido, " + profesor.getNombre(), "ONLY_OK").setVisible(true);
                    ventana.dispose();
                    abrirVentanaPrincipalProfesor();
                    return true;
                }
            }
        }else if (getControladorLogin().comprobarLogin(usuario, passwordHash) == 3) {
            for (Administradores admin : Controlador.getListaAdministradores()) {
                if (admin.getUsuario().equals(usuario) && admin.getContrasena().equals(passwordHash)) {

                    new CustomDialog(null,"Bienvenido","Bienvenido, " + admin.toString(),"ONLY_OK").setVisible(true);
                    ventana.dispose();
                    abrirVentanaPrincipalAdmin();
                    return true;
                }
            }
        }else if (getControladorLogin().comprobarLogin(usuario, passwordHash) == 4) {
            for (Tutores tutor : Controlador.getListaTutores()) {
                if (tutor.getUsuario().equals(usuario) && tutor.getContrasena().equals(passwordHash)) {

                    new CustomDialog(ventana, "Bienvenido", "Bienvenido, " + tutor.getNombre(), "ONLY_OK").setVisible(true);
                    abrirVentanaPrincipalTutor();
                    return true;
                }
            }
        }
        new CustomDialog(ventana, "Error", "Usuario o contraseña incorrectos", "ONLY_OK").setVisible(true);
        return false;
    }

    /**
     * Método para abrir la ventana principal del rol de estudiante.
     */
    private void abrirVentanaPrincipalEstudiante() {
        new VistaPrincipalEstudiante();
        dispose();
    }

    /**
     * Método para abrir la ventana principal del rol de administrador.
     */
    private void abrirVentanaPrincipalAdmin() {
        new VistaPrincipalAdmin();
        dispose();
    }

    /**
     * Método para abrir la ventana principal del rol de profesor.
     */
    private void abrirVentanaPrincipalProfesor() {
        new VistaPrincipalProfesor();
        dispose();
    }

    /**
     * Método para abrir la ventana principal del rol de tutor.
     * Este método muestra un diálogo para seleccionar un perfil de estudiante a gestionar.
     */
    private void abrirVentanaPrincipalTutor() {
        SeleccionarEstudianteDialog dialog = new SeleccionarEstudianteDialog(tutorLogeado);
        dialog.setModal(true);
        dialog.setVisible(true);

        Estudiantes estudiante = dialog.getEstudianteSeleccionado();

        if (estudiante != null) {
            new VistaPrincipalTutor();
            dispose();
        } else {
            new CustomDialog(ventana, "Advertencia", "Debe seleccionar un estudiante para continuar.", "ONLY_OK").setVisible(true);
        }
    }
}