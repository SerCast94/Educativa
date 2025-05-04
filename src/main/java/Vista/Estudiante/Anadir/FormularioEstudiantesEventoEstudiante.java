package Vista.Estudiante.Anadir;

import Controlador.Controlador;
import Mapeo.Estudiantes;
import Mapeo.Eventos;
import Mapeo.EstudiantesEventos;
import Vista.Estudiante.VistaPrincipalEstudiante;
import Vista.Util.Boton;
import Vista.Util.CustomDialog;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.util.List;

import static Controlador.Controlador.*;
import static Vista.Util.EstiloComponentes.*;

public class FormularioEstudiantesEventoEstudiante extends JFrame {

    private Container panel;
    private GridBagLayout gLayout;
    private GridBagConstraints gbc;

    private JLabel lblEvento = new JLabel("Evento: ");
    private JLabel lblDescripcion = new JLabel("Descripción:");
    private JLabel lblFechaInicio = new JLabel("Fecha Inicio:");
    private JLabel lblFechaFin = new JLabel("Fecha Fin:");
    private JLabel lblComentarios = new JLabel("Comentarios:");
    private JLabel lblUbicacion = new JLabel("Ubicación:");
    private JLabel lblComentario = new JLabel("Comentario: ");
    private JLabel lblConfirmado = new JLabel("¿Asistirá?: ");

    private JComboBox<Eventos> cmbEvento = new JComboBox<>();
    private JLabel valDescripcion = new JLabel();
    private JLabel valFechaInicio = new JLabel();
    private JLabel valFechaFin = new JLabel();
    private JLabel valTipo = new JLabel();
    private JLabel valUbicacion = new JLabel();
    private JTextField txtComentario = crearTextField();
    private JCheckBox chkConfirmado = new JCheckBox("Sí");

    private JButton btnAceptar = new Boton("Guardar", Boton.ButtonType.PRIMARY);
    private JButton btnCancelar = new Boton("Cancelar", Boton.ButtonType.DELETE);

    private Estudiantes estudiante;
    private Eventos evento;

    public FormularioEstudiantesEventoEstudiante(Estudiantes estudiante, Eventos evento) {
        this.estudiante = estudiante;
        this.evento = evento;
        initGUI();
        cargarEvento();
        initEventos();
    }

    private void initGUI() {
        setTitle("Confirmar Asistencia");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(500, 500);
        setLocationRelativeTo(null);

        panel = this.getContentPane();
        panel.setBackground(new Color(251, 234, 230));
        gLayout = new GridBagLayout();
        gbc = new GridBagConstraints();
        panel.setLayout(gLayout);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10);

        JLabel titulo = new JLabel("Responder a Evento", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 22));
        titulo.setBorder(BorderFactory.createEmptyBorder(10, 0, 20, 0));
        titulo.setForeground(new Color(70, 70, 70));
        gbc.gridwidth = 2;
        agregarComponente(titulo, 0, 0);
        gbc.gridwidth = 1;

        customizeComboBox(cmbEvento);
        agregarComponente(lblEvento, 1, 0);
        agregarComponente(cmbEvento, 1, 1);

        agregarComponente(lblDescripcion, 2, 0);
        agregarComponente(valDescripcion, 2, 1);

        agregarComponente(lblFechaInicio, 3, 0);
        agregarComponente(valFechaInicio, 3, 1);

        agregarComponente(lblFechaFin, 4, 0);
        agregarComponente(valFechaFin, 4, 1);

        agregarComponente(lblComentarios, 5, 0);
        agregarComponente(valTipo, 5, 1);

        agregarComponente(lblUbicacion, 6, 0);
        agregarComponente(valUbicacion, 6, 1);

        agregarComponente(lblComentario, 7, 0);
        setBordeNaranja(txtComentario);
        agregarComponente(txtComentario, 7, 1);

        agregarComponente(lblConfirmado, 8, 0);
        checkPersonalizadoNaranja(chkConfirmado);
        chkConfirmado.setBackground(new Color(251, 234, 230));
        agregarComponente(chkConfirmado, 8, 1);

        JPanel panelBotones = new JPanel();
        panelBotones.setBackground(new Color(251, 234, 230));
        panelBotones.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        btnAceptar.setPreferredSize(new Dimension(120, 40));
        btnCancelar.setPreferredSize(new Dimension(120, 40));
        panelBotones.add(btnAceptar);
        panelBotones.add(btnCancelar);

        gbc.gridx = 0;
        gbc.gridy = 9;
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

        btnAceptar.addActionListener(e -> agregarEstudianteValido());

        cmbEvento.addItemListener(e -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                Eventos seleccionado = (Eventos) cmbEvento.getSelectedItem();
                if (seleccionado != null) {
                    evento = seleccionado;
                    mostrarInformacionEvento(seleccionado);
                }
            }
        });
    }

    private void cargarEvento() {
        cmbEvento.removeAllItems();
        List<Eventos> todosEventos = Controlador.getListaEventos();
        for (Eventos eventoAnadir : todosEventos) {
            cmbEvento.addItem(eventoAnadir);
        }

        cmbEvento.setSelectedItem(evento);
        mostrarInformacionEvento(evento);
    }

    private void mostrarInformacionEvento(Eventos evento) {
        valDescripcion.setText(evento.getDescripcion());
        valFechaInicio.setText(evento.getFechaInicio().toString());
        valFechaFin.setText(evento.getFechaFin().toString());
        valTipo.setText(evento.getTipoEvento().toString());
        valUbicacion.setText(evento.getUbicacion());
    }

    private void agregarEstudianteValido(){

        String comentario = txtComentario.getText().trim();
        Boolean confirmado = (Boolean) chkConfirmado.isSelected();

        if (comentario.length() > 255) {
            new CustomDialog(null, "Error", "El comentario no puede tener más de 255 caracteres.", "ONLY_OK").setVisible(true);
            return;
        }

        if(confirmado) {
            try {
                EstudiantesEventos ee = new EstudiantesEventos(estudiante, evento, comentario, confirmado);

                insertarControladorEstudianteEvento(ee);
                actualizarListaEstudiantesEventos();

                VistaPrincipalEstudiante vistaPrincipal = (VistaPrincipalEstudiante) VistaPrincipalEstudiante.getVistaPrincipal();
                vistaPrincipal.mostrarVistaEventos();

                new CustomDialog(null, "Éxito", "Inscripción en evento realizada correctamente.", "ONLY_OK").setVisible(true);
                dispose();
            } catch (Exception ex) {
                new CustomDialog(null, "Error", "No se pudo inscribir: " + ex.getMessage(), "ONLY_OK").setVisible(true);
            }
        }else new CustomDialog(null, "Error", "No se puede inscribir a un evento sin confirmar asistencia.", "ONLY_OK").setVisible(true);
    }
}
