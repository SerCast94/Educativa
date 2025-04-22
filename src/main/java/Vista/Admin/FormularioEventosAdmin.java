package Vista.Admin;

import Controlador.Controlador;
import Mapeo.Eventos;
import Vista.Util.CustomDatePicker;
import Vista.Util.Boton;

import javax.swing.*;
import java.awt.*;
import java.sql.Date;
import java.util.List;

import static Controlador.Controlador.actualizarListaEventos;
import static Controlador.Controlador.insertarControladorEvento;
import static Vista.Util.EstiloComponentes.*;

public class FormularioEventosAdmin extends JFrame {
    private Container panel;
    private GridBagLayout gLayout;
    private GridBagConstraints gbc;
    private JButton btnAceptar = new Boton("Aceptar", Boton.ButtonType.PRIMARY);
    private JButton btnCancelar = new Boton("Cancelar", Boton.ButtonType.DELETE);

    private JLabel lblNombre = new JLabel("Nombre: ");
    private JLabel lblDescripcion = new JLabel("Descripción: ");
    private JLabel lblFechaInicio = new JLabel("Fecha de Inicio: ");
    private JLabel lblFechaFin = new JLabel("Fecha de Fin: ");
    private JLabel lblUbicacion = new JLabel("Ubicación: ");
    private JLabel lblTipoEvento = new JLabel("Tipo de Evento: ");

    private JTextField txtNombre = crearTextField();
    private JTextField txtDescripcion = crearTextField();
    private JTextField txtUbicacion = crearTextField();
    private JComboBox<Eventos.TipoEvento> cmbTipoEvento = new JComboBox<>(Eventos.TipoEvento.values());

    private CustomDatePicker datePickerInicio = new CustomDatePicker();
    private CustomDatePicker datePickerFin = new CustomDatePicker();

    public FormularioEventosAdmin() {
        initGUI();
        initEventos();
    }

    private void initGUI() {
        setTitle("Registrar Evento");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(600, 500);
        setLocationRelativeTo(null);

        panel = this.getContentPane();
        panel.setBackground(new Color(251, 234, 230));
        gLayout = new GridBagLayout();
        gbc = new GridBagConstraints();
        panel.setLayout(gLayout);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);

        JLabel titulo = new JLabel("Registrar Evento", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 24));
        titulo.setForeground(new Color(70, 70, 70));
        titulo.setBorder(BorderFactory.createEmptyBorder(10, 0, 20, 0));
        gbc.gridwidth = 2;
        agregarComponente(titulo, 0, 0);
        gbc.gridwidth = 1;

        customizeComboBox(cmbTipoEvento);

        agregarComponente(lblNombre, 1, 0);
        setBordeNaranja(txtNombre);
        agregarComponente(txtNombre, 1, 1);

        agregarComponente(lblDescripcion, 2, 0);
        setBordeNaranja(txtDescripcion);
        agregarComponente(txtDescripcion, 2, 1);

        agregarComponente(lblFechaInicio, 3, 0);
        EspaciadoEnDatePicker(datePickerInicio);
        agregarComponente(datePickerInicio, 3, 1);

        agregarComponente(lblFechaFin, 4, 0);
        EspaciadoEnDatePicker(datePickerFin);
        agregarComponente(datePickerFin, 4, 1);

        agregarComponente(lblUbicacion, 5, 0);
        setBordeNaranja(txtUbicacion);
        agregarComponente(txtUbicacion, 5, 1);

        agregarComponente(lblTipoEvento, 6, 0);
        setBordeNaranja(cmbTipoEvento);
        agregarComponente(cmbTipoEvento, 6, 1);

        JPanel panelBotones = new JPanel();
        panelBotones.setBackground(new Color(251, 234, 230));
        panelBotones.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        btnAceptar.setPreferredSize(new Dimension(100, 40));
        btnCancelar.setPreferredSize(new Dimension(100, 40));
        panelBotones.add(btnAceptar);
        panelBotones.add(btnCancelar);

        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.gridwidth = 2;
        panel.add(panelBotones, gbc);

        setVisible(true);
    }

    private void agregarComponente(Component componente, int fila, int columna) {
        gbc.gridx = columna;
        gbc.gridy = fila;
        panel.add(componente, gbc);
    }

    private void initEventos() {
        btnCancelar.addActionListener(e -> dispose());

        btnAceptar.addActionListener(e -> {
            if (txtNombre.getText().trim().isEmpty() ||
                    txtDescripcion.getText().trim().isEmpty() ||
                    datePickerInicio.getDate() == null ||
                    datePickerFin.getDate() == null ||
                    txtUbicacion.getText().trim().isEmpty() ||
                    cmbTipoEvento.getSelectedItem() == null) {

                JOptionPane.showMessageDialog(null, "Todos los campos obligatorios deben ser completados.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            try {
                Eventos nuevoEvento = new Eventos(
                        txtNombre.getText().trim(),
                        txtDescripcion.getText().trim(),
                        Date.valueOf(datePickerInicio.getDate()),
                        Date.valueOf(datePickerFin.getDate()),
                        txtUbicacion.getText().trim(),
                        (Eventos.TipoEvento) cmbTipoEvento.getSelectedItem()
                );

                insertarControladorEvento(nuevoEvento);
                actualizarListaEventos();

                VistaPrincipalAdmin vistaPrincipal = (VistaPrincipalAdmin) VistaPrincipalAdmin.getVistaPrincipal();
                vistaPrincipal.mostrarVistaEventos();

                JOptionPane.showMessageDialog(null, "Evento registrado correctamente.");
                dispose();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Error al registrar el evento.", "Error", JOptionPane.ERROR_MESSAGE);
                Controlador.rollback();
            }
        });

    }
}
