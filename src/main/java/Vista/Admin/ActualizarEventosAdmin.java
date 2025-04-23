package Vista.Admin;

import Controlador.Controlador;
import Mapeo.Eventos;
import Vista.Util.Boton;

import javax.swing.*;
import java.awt.*;
import java.util.List;

import static Vista.Util.EstiloComponentes.*;

public class ActualizarEventosAdmin extends JFrame {
    private Container panel;
    private GridBagLayout gLayout;
    private GridBagConstraints gbc;
    private JButton btnAceptar = new Boton("Actualizar", Boton.ButtonType.PRIMARY);
    private JButton btnCancelar = new Boton("Cancelar", Boton.ButtonType.DELETE);

    private JLabel lblNombre = new JLabel("Nombre:");
    private JLabel lblFecha = new JLabel("Fecha:");
    private JLabel lblDescripcion = new JLabel("Descripción:");
    private JLabel lblEstado = new JLabel("Estado:");

    private JTextField txtNombre = crearTextField();
    private JTextField txtFecha = crearTextField();
    private JTextField txtDescripcion = crearTextField();
    private JComboBox<String> cmbEstado = new JComboBox<>(new String[]{"activo", "inactivo"});

    private Eventos evento;

    public ActualizarEventosAdmin(Eventos evento) {
        this.evento = evento;
        initGUI();
        initEventos();
        cargarDatosEvento();
    }

    private void cargarDatosEvento() {
//        txtNombre.setText(evento.getNombre());
//        txtFecha.setText(evento.getFecha().toString());
//        txtDescripcion.setText(evento.getDescripcion());
//        cmbEstado.setSelectedItem(evento.getEstado().name());
    }

    private void initGUI() {
        setTitle("Actualizar Evento");
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

        JLabel titulo = new JLabel("Actualizar Evento", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 24));
        titulo.setBorder(BorderFactory.createEmptyBorder(10, 0, 20, 0));
        titulo.setForeground(new Color(70, 70, 70));
        gbc.gridwidth = 2;
        agregarComponente(titulo, 0, 0);
        gbc.gridwidth = 1;

        customizeComboBox(cmbEstado);

        agregarComponente(lblNombre, 1, 0);
        setBordeNaranja(txtNombre);
        agregarComponente(txtNombre, 1, 1);

        agregarComponente(lblFecha, 2, 0);
        setBordeNaranja(txtFecha);
        agregarComponente(txtFecha, 2, 1);

        agregarComponente(lblDescripcion, 3, 0);
        setBordeNaranja(txtDescripcion);
        agregarComponente(txtDescripcion, 3, 1);

        agregarComponente(lblEstado, 4, 0);
        setBordeNaranja(cmbEstado);
        agregarComponente(cmbEstado, 4, 1);

        JPanel panelBotones = new JPanel();
        panelBotones.setBackground(new Color(251, 234, 230));
        panelBotones.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        btnAceptar.setPreferredSize(new Dimension(100, 40));
        btnCancelar.setPreferredSize(new Dimension(100, 40));
        panelBotones.add(btnAceptar);
        panelBotones.add(btnCancelar);

        gbc.gridx = 0;
        gbc.gridy = 5;
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
                    txtFecha.getText().trim().isEmpty() ||
                    txtDescripcion.getText().trim().isEmpty()) {

                JOptionPane.showMessageDialog(null, "Todos los campos son obligatorios.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

//            evento.setNombre(txtNombre.getText().trim());
//            evento.setFecha(java.sql.Date.valueOf(txtFecha.getText().trim()));
//            evento.setDescripcion(txtDescripcion.getText().trim());
//            evento.setEstado(Eventos.EstadoEvento.valueOf(cmbEstado.getSelectedItem().toString()));

            try {
                Controlador.actualizarControladorEvento(evento);
                Controlador.actualizarListaEventos();

                VistaPrincipalAdmin vistaPrincipal = (VistaPrincipalAdmin) VistaPrincipalAdmin.getVistaPrincipal();
                vistaPrincipal.mostrarVistaEventos();

                JOptionPane.showMessageDialog(null, "Evento actualizado correctamente");
                dispose();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Error al actualizar evento", "Error", JOptionPane.ERROR_MESSAGE);
                Controlador.rollback();
            }
        });
    }
}