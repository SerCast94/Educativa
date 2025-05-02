package Vista;

import Controlador.Controlador;
import Controlador.ControladorLogin;
import Mapeo.Estudiantes;
import Mapeo.Administradores;
import Mapeo.Profesores;
import Vista.Admin.VistaPrincipalAdmin;
import Vista.Estudiante.VistaPrincipalEstudiante;
import Vista.Profesor.VistaPrincipalProfesor;
import Vista.Util.Boton;
import Vista.Util.CustomDialog;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

import static BackUtil.Encriptador.encryptMD5;

public class loginGUI extends JFrame {
    private JFrame frame;
    private JPanel mainPanel, leftPanel, rightPanel;
    private JTextField campoUsuario;
    private JPasswordField campoPassword;
    private Boton botonIngresar;
    private JLabel logoLabel, nombreCentroLabel, descripcionLabel, olvidoPasswordLabel, appNameLabel;
    static Controlador controlador;
    static ControladorLogin controladorLogin;

    public loginGUI() {

        controlador = new Controlador();
        controlador.cargarListas();
        controladorLogin = new ControladorLogin();
        initGUI();
        initEventos();
    }

    public static Controlador getControlador() {
        return controlador;
    }

    public static ControladorLogin getControladorLogin() {
        return controladorLogin;
    }

    public void initGUI() {

        frame = new JFrame();
        frame.setTitle("Login - Colegio Salesiano San Francisco de Sales");
        frame.setSize(1920, 1080);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout());

        // Panel superior para el nombre de la aplicación
        JPanel topPanel = new JPanel() {
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
        topPanel.setLayout(new BorderLayout());
        topPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 0, 10));
        appNameLabel = new JLabel("Educativa");
        appNameLabel.setFont(new Font("Arial", Font.BOLD, 16));
        appNameLabel.setForeground(new Color(100, 100, 100));
        appNameLabel.setHorizontalAlignment(SwingConstants.RIGHT); // Alinear a la derecha
        topPanel.add(appNameLabel, BorderLayout.EAST); // Agregar al lado derecho del panel
        frame.add(topPanel, BorderLayout.NORTH);

        // Panel principal dividido en dos columnas
        mainPanel = new JPanel(new GridLayout(1, 2));
        frame.add(mainPanel, BorderLayout.CENTER);

        // Panel izquierdo (logo y descripción)
        leftPanel = new JPanel(new BorderLayout());
        leftPanel.setBackground(new Color(241, 198, 177));
        leftPanel.setBorder(BorderFactory.createEmptyBorder(60, 60, 60, 60));
        mainPanel.add(leftPanel);

        logoLabel = new JLabel(new ImageIcon(Objects.requireNonNull(getClass().getResource("/img/logo.png"))), SwingConstants.CENTER);
        logoLabel.setFont(new Font("Arial", Font.BOLD, 48));
        logoLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 30, 0));

        nombreCentroLabel = new JLabel("<html><div style='text-align: center;'>Colegio Salesiano San Francisco de Sales</div></html>", SwingConstants.CENTER);
        nombreCentroLabel.setFont(new Font("Arial", Font.BOLD, 24));

        descripcionLabel = new JLabel("<html><div style='text-align: justify; text-justify: inter-word;'>Un colegio Salesiano es una institución educativa inspirada en el método educativo salesiano. Los colegios salesianos tienen al centro de su identidad la figura de Don Bosco, sacerdote, educador y escritor italiano del siglo XIX, quien desarrolló un método educativo que pone al alumno al centro de toda actividad educativa y formativa. Este método tiene como objetivo promover y comprometer a todas las personas del docente y de la comunidad de la que forma parte, con una visión propia de pensamiento, de vida, actividad y compromiso práctico.</div></html>", SwingConstants.CENTER);
        descripcionLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        descripcionLabel.setBorder(BorderFactory.createEmptyBorder(30, 0, 0, 0));

        JPanel centerLeftPanel = new JPanel(new BorderLayout());
        centerLeftPanel.add(nombreCentroLabel, BorderLayout.CENTER);
        centerLeftPanel.add(descripcionLabel, BorderLayout.SOUTH);
        centerLeftPanel.setOpaque(false);

        leftPanel.add(logoLabel, BorderLayout.NORTH);
        leftPanel.add(centerLeftPanel, BorderLayout.CENTER);

        // Panel derecho (formulario de login)
        rightPanel = new JPanel(new GridBagLayout());
        rightPanel.setBackground(Color.WHITE);
        rightPanel.setBorder(BorderFactory.createEmptyBorder(20, 80, 0, 80));
        mainPanel.add(rightPanel);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        gbc.insets = new Insets(10, 0, 10, 0);

        // Título LOGIN
        JLabel loginTitle = new JLabel("LOGIN", SwingConstants.CENTER);
        loginTitle.setFont(new Font("Arial", Font.BOLD, 28));
        loginTitle.setBorder(BorderFactory.createEmptyBorder(0, 0, 30, 0));
        rightPanel.add(loginTitle, gbc);

        // Etiqueta Usuario
        JLabel usuarioLabel = new JLabel("Usuario");
        usuarioLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        rightPanel.add(usuarioLabel, gbc);

        // Campo Usuario
        campoUsuario = new JTextField();
        campoUsuario.setFont(new Font("Arial", Font.PLAIN, 14));
        campoUsuario.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.GRAY),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)
        ));
        rightPanel.add(campoUsuario, gbc);

        // Espacio
        rightPanel.add(Box.createVerticalStrut(20), gbc);

        // Etiqueta Contraseña
        JLabel passwordLabel = new JLabel("Contraseña");
        passwordLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        rightPanel.add(passwordLabel, gbc);

        // Campo Contraseña - más ancho
        campoPassword = new JPasswordField();
        campoPassword.setFont(new Font("Arial", Font.PLAIN, 14));
        campoPassword.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.GRAY),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)
        ));
        rightPanel.add(campoPassword, gbc);

        // ¿Olvidaste tu contraseña?
        olvidoPasswordLabel = new JLabel("¿Olvidaste tu contraseña?", SwingConstants.CENTER);
        olvidoPasswordLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        olvidoPasswordLabel.setForeground(new Color(100, 100, 100));
        rightPanel.add(olvidoPasswordLabel, gbc);

        // Espacio
        rightPanel.add(Box.createVerticalStrut(30), gbc);

        // Botón Ingresar
        botonIngresar = new Boton("Ingresar",Boton.ButtonType.PRIMARY);
        botonIngresar.setFont(new Font("Arial", Font.BOLD, 16));
        botonIngresar.setBackground(new Color(230, 108, 81));
        botonIngresar.setForeground(Color.BLACK);
        botonIngresar.setFocusPainted(false);
        botonIngresar.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        rightPanel.add(botonIngresar, gbc);

        frame.setVisible(true);
    }

    public void initEventos() {
        botonIngresar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (funcionLogin()) frame.dispose();
            }
        });

        olvidoPasswordLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
            new CustomDialog(frame, "Información", "Acuda a la Secretaría del centro para restaurar su contraseña", "ONLY_OK").setVisible(true);            }
        });

        campoUsuario.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (funcionLogin()) frame.dispose();
            }
        });

        campoPassword.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (funcionLogin()) frame.dispose();
            }
        });
    }

    private boolean funcionLogin() {
        String usuario = campoUsuario.getText();
        String password = new String(campoPassword.getPassword());
        String passwordHash = encryptMD5(password);
        Estudiantes estudianteLogeado = null;
        Administradores adminLogeado = null;
        Profesores profesorLogeado = null;
        if (getControladorLogin().comprobarLogin(usuario, passwordHash) == 1) {
            for (Estudiantes estudiante : Controlador.getListaEstudiantes()) {
                if (estudiante.getUsuario().equals(usuario) && estudiante.getContrasena().equals(passwordHash)) {
                    estudianteLogeado = estudiante;
                    new CustomDialog(frame, "Bienvenido", "Bienvenido, " + estudiante.getNombre(), "ONLY_OK").setVisible(true);
                    frame.dispose();
                    abrirVentanaPrincipalEstudiante(estudianteLogeado);
                    return true;
                }
            }
        }else if (getControladorLogin().comprobarLogin(usuario, passwordHash) == 2) {

            for (Profesores profesor : Controlador.getListaProfesores()) {
                if (profesor.getUsuario().equals(usuario) && profesor.getContrasena().equals(passwordHash)) {
                    profesorLogeado = profesor;
                    new CustomDialog(frame, "Bienvenido", "Bienvenido, " + profesor.getNombre(), "ONLY_OK").setVisible(true);
                    frame.dispose();
                    abrirVentanaPrincipalProfesor(profesorLogeado);
                    return true;
                }
            }
        }else if (getControladorLogin().comprobarLogin(usuario, passwordHash) == 3) {
            for (Administradores admin : Controlador.getListaAdministradores()) {
                if (admin.getUsuario().equals(usuario) && admin.getContrasena().equals(passwordHash)) {
                    adminLogeado = admin;
                    new CustomDialog(null,"Bienvenido","Bienvenido, " + admin.toString(),"ONLY_OK").setVisible(true);
                    frame.dispose();
                    abrirVentanaPrincipalAdmin(adminLogeado);
                    return true;
                }
            }
        }
        new CustomDialog(frame, "Error", "Usuario o contraseña incorrectos", "ONLY_OK").setVisible(true);
        return false;
    }

    private void abrirVentanaPrincipalEstudiante(Estudiantes estudiante) {
        new VistaPrincipalEstudiante(estudiante);
        dispose();
    }

    private void abrirVentanaPrincipalAdmin(Administradores admin) {
        new VistaPrincipalAdmin(admin);
        dispose();
    }

    private void abrirVentanaPrincipalProfesor(Profesores profesor) {
        new VistaPrincipalProfesor(profesor);
        dispose();
    }
}