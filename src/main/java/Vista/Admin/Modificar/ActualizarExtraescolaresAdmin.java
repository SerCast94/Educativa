//package Vista.Admin.Modificar;
//
//import Controlador.Controlador;
//import Mapeo.Extraescolares;
//import Mapeo.Profesores;
//import Vista.Admin.VistaPrincipalAdmin;
//import Vista.Util.Boton;
//import Vista.Util.CustomDialog;
//
//import javax.swing.*;
//import java.awt.*;
//import java.util.List;
//
//import static Vista.Util.EstiloComponentes.*;
//
//public class ActualizarExtraescolaresAdmin extends JFrame {
//    private Container panel;
//    private GridBagLayout gLayout;
//    private GridBagConstraints gbc;
//    private JButton btnAceptar = new Boton("Actualizar", Boton.ButtonType.PRIMARY);
//    private JButton btnCancelar = new Boton("Cancelar", Boton.ButtonType.DELETE);
//
//    private JLabel lblNombre = new JLabel("Nombre: ");
//    private JLabel lblDescripcion = new JLabel("Descripción: ");
//    private JLabel lblTipo = new JLabel("Tipo: ");
//    private JLabel lblProfesor = new JLabel("Profesor: ");
//
//    private JTextField txtNombre = crearTextField();
//    private JTextField txtDescripcion = crearTextField();
//    private JComboBox<Extraescolares.TipoExtraescolar> cmbTipo = new JComboBox<>(Extraescolares.TipoExtraescolar.values());
//    private JComboBox<Profesores> cmbProfesor = new JComboBox<>();
//
//    private Extraescolares extraescolar;
//
//    public ActualizarExtraescolaresAdmin(Extraescolares extraescolar) {
//        this.extraescolar = extraescolar;
//        initGUI();
//        initEventos();
//        cargarProfesores();
//        cargarDatosExtraescolar();
//    }
//
//    private void initGUI() {
//        setTitle("Actualizar Extraescolar");
//        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//        setSize(600, 550);
//        setLocationRelativeTo(null);
//
//        panel = this.getContentPane();
//        panel.setBackground(new Color(251, 234, 230));
//        gLayout = new GridBagLayout();
//        gbc = new GridBagConstraints();
//        panel.setLayout(gLayout);
//        gbc.fill = GridBagConstraints.HORIZONTAL;
//        gbc.insets = new Insets(5, 5, 5, 5);
//
//        JLabel titulo = new JLabel("Actualizar Actividad Extraescolar", SwingConstants.CENTER);
//        titulo.setFont(new Font("Arial", Font.BOLD, 24));
//        titulo.setForeground(new Color(70, 70, 70));
//        titulo.setBorder(BorderFactory.createEmptyBorder(10, 0, 20, 0));
//        gbc.gridwidth = 2;
//        agregarComponente(titulo, 0, 0);
//        gbc.gridwidth = 1;
//
//        customizeComboBox(cmbTipo);
//        customizeComboBox(cmbProfesor);
//
//        agregarComponente(lblNombre, 1, 0);
//        setBordeNaranja(txtNombre);
//        agregarComponente(txtNombre, 1, 1);
//
//        agregarComponente(lblDescripcion, 2, 0);
//        setBordeNaranja(txtDescripcion);
//        agregarComponente(txtDescripcion, 2, 1);
//
//        agregarComponente(lblTipo, 3, 0);
//        setBordeNaranja(cmbTipo);
//        agregarComponente(cmbTipo, 3, 1);
//
//        agregarComponente(lblProfesor, 4, 0);
//        setBordeNaranja(cmbProfesor);
//        agregarComponente(cmbProfesor, 4, 1);
//
//        JPanel panelBotones = new JPanel();
//        panelBotones.setBackground(new Color(251, 234, 230));
//        panelBotones.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
//        btnAceptar.setPreferredSize(new Dimension(100, 40));
//        btnCancelar.setPreferredSize(new Dimension(100, 40));
//        panelBotones.add(btnAceptar);
//        panelBotones.add(btnCancelar);
//
//        gbc.gridx = 0;
//        gbc.gridy = 5;
//        gbc.gridwidth = 2;
//        panel.add(panelBotones, gbc);
//
//        setVisible(true);
//    }
//
//    private void agregarComponente(Component componente, int fila, int columna) {
//        gbc.gridx = columna;
//        gbc.gridy = fila;
//        panel.add(componente, gbc);
//    }
//
//    private void initEventos() {
//        btnCancelar.addActionListener(e -> dispose());
//
//        btnAceptar.addActionListener(e -> {
//            if (txtNombre.getText().trim().isEmpty() ||
//                    txtDescripcion.getText().trim().isEmpty() ||
//                    cmbTipo.getSelectedItem() == null ||
//                    cmbProfesor.getSelectedItem() == null) {
//
//                new CustomDialog(null,"Error", "Todos los campos son obligatorios.","ONLY_OK").setVisible(true);
//                return;
//            }
//
//            try {
//                extraescolar.setNombre(txtNombre.getText().trim());
//                extraescolar.setDescripcion(txtDescripcion.getText().trim());
//                extraescolar.setTipo((Extraescolares.TipoExtraescolar) cmbTipo.getSelectedItem());
//                extraescolar.setProfesor((Profesores) cmbProfesor.getSelectedItem());
//
//                Controlador.actualizarControladorExtraescolar(extraescolar);
//                Controlador.actualizarListaExtraescolares();
//
//                VistaPrincipalAdmin vistaPrincipal = (VistaPrincipalAdmin) VistaPrincipalAdmin.getVistaPrincipal();
//                vistaPrincipal.mostrarVistaActividadesExtraescolares();
//
//                new CustomDialog(null,"Éxito", "Actividad extraescolar actualizada correctamente.","ONLY_OK").setVisible(true);
//                dispose();
//            } catch (Exception ex) {
//                new CustomDialog(null,"Error", "Error al actualizar la actividad extraescolar.","ONLY_OK").setVisible(true);
//                Controlador.rollback();
//            }
//        });
//    }
//
//    private void cargarProfesores() {
//        List<Profesores> profesores = Controlador.getListaProfesores();
//        cmbProfesor.removeAllItems();
//        for (Profesores profesor : profesores) {
//            cmbProfesor.addItem(profesor);
//        }
//    }
//
//    private void cargarDatosExtraescolar() {
//        txtNombre.setText(extraescolar.getNombre());
//        txtDescripcion.setText(extraescolar.getDescripcion());
//        cmbTipo.setSelectedItem(extraescolar.getTipo());
//        cmbProfesor.setSelectedItem(extraescolar.getProfesor());
//    }
//}
