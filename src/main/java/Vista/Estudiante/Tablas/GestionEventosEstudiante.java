package Vista.Estudiante.Tablas;

import Controlador.Controlador;
import Mapeo.EstudiantesEventos;
import Mapeo.Eventos;
import Vista.Estudiante.Anadir.FormularioEstudiantesEventoEstudiante;
import Vista.Estudiante.Modificar.ActualizarEstudiantesEventoEstudiante;
import Vista.Estudiante.VistaPrincipalEstudiante;
import Vista.Util.Boton;
import Vista.Util.CustomDialog;

import javax.swing.*;
import javax.swing.plaf.basic.BasicScrollBarUI;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static Controlador.Controlador.listaEventos;
import static Controlador.ControladorLogin.estudianteLogeado;
import static Vista.Util.EstiloComponentes.checkPersonalizadoGris;

public class GestionEventosEstudiante extends JPanel {
    private JTable tablaEventos;
    private JButton btnAgregar;
    private DefaultTableModel modelo;
    private JPopupMenu popupMenu;
    private JTableHeader header;

    public GestionEventosEstudiante() {
        setLayout(new BorderLayout());
        initGUI();
        initEventos();
        cargarEventosEstudiante();
    }

    private void initGUI() {
        initPanelSuperior();
        initTabla();
        initPopupMenu();
    }

    private void initEventos() {
        btnAgregar.addActionListener(e -> inscribirseEvento());

        header.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int column = header.columnAtPoint(e.getPoint());
                TableRowSorter<?> sorter = (TableRowSorter<?>) tablaEventos.getRowSorter();
                if (column >= 0 && sorter != null) {
                    SortOrder currentOrder = sorter.getSortKeys().isEmpty() ? null : sorter.getSortKeys().get(0).getSortOrder();
                    SortOrder newOrder = currentOrder == SortOrder.DESCENDING ? SortOrder.ASCENDING : SortOrder.DESCENDING;
                    sorter.setSortKeys(Arrays.asList(new RowSorter.SortKey(column, newOrder)));
                }
            }
        });

        tablaEventos.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                int row = tablaEventos.rowAtPoint(e.getPoint());
                if (row >= 0) {
                    tablaEventos.setSelectionBackground(new Color(245, 156, 107, 204));
                }
            }
        });

        tablaEventos.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int row = tablaEventos.rowAtPoint(e.getPoint());
                if (row >= 0) {
                    tablaEventos.setRowSelectionInterval(row, row);
                    if (SwingUtilities.isRightMouseButton(e)) {
                        // Verificar si el clic está en la parte baja de la tabla
                        int visibleHeight = tablaEventos.getVisibleRect().height;
                        int clickY = e.getY();
                        if (clickY > visibleHeight - 100) { // Ajustar si está cerca del borde inferior
                            popupMenu.show(tablaEventos, e.getX(), e.getY() - 80);
                        } else {
                            popupMenu.show(tablaEventos, e.getX(), e.getY());
                        }
                    }
                }
            }
        });

    }

    private void initPanelSuperior() {
        JPanel panelSuperior = new JPanel(new BorderLayout());
        panelSuperior.setBorder(BorderFactory.createEmptyBorder(20, 10, 10, 10));
        panelSuperior.setBackground(new Color(251, 234, 230));

        JLabel titulo = new JLabel("Colegio Salesiano San Francisco de Sales - Eventos", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 24));
        titulo.setBorder(BorderFactory.createEmptyBorder(25, 10, 30, 10));
        panelSuperior.add(titulo, BorderLayout.NORTH);

        JPanel panelBoton = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelBoton.setOpaque(false);

        ImageIcon icono = new ImageIcon(Objects.requireNonNull(getClass().getResource("/icons/anadir.png")));
        icono.setImage(icono.getImage().getScaledInstance(24, 24, Image.SCALE_SMOOTH));

        btnAgregar = new Boton("Inscribirse", Boton.ButtonType.PRIMARY);
        btnAgregar.setIcon(icono);
        btnAgregar.setPreferredSize(new Dimension(160, 30));
        btnAgregar.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));

        panelBoton.add(btnAgregar);
        panelSuperior.add(panelBoton, BorderLayout.SOUTH);

        add(panelSuperior, BorderLayout.NORTH);
    }

    private void initTabla() {
        String[] columnas = {"Nombre", "Descripción", "Fecha Inicio", "Fecha Fin", "Ubicación", "Tipo", "Inscrito", "Objeto"};
        modelo = new DefaultTableModel(null, columnas) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        tablaEventos = new JTable(modelo) {
            @Override
            public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
                Component c = super.prepareRenderer(renderer, row, column);
                if (!isRowSelected(row)) {
                    c.setBackground(row % 2 == 0 ? new Color(255, 255, 255) : new Color(245, 245, 245));
                }
                c.setForeground(isRowSelected(row) ? Color.BLACK : new Color(70, 70, 70));
                return c;
            }
        };

        tablaEventos.getColumnModel().getColumn(6).setCellRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                JCheckBox checkBox = new JCheckBox();
                checkBox.setHorizontalAlignment(SwingConstants.CENTER);
                checkBox.setSelected(value != null && value.equals("Sí"));
                checkPersonalizadoGris(checkBox);

                checkBox.setEnabled(true);
                checkBox.setFocusable(false);
                checkBox.setRequestFocusEnabled(false);
                checkBox.setRolloverEnabled(false);
                checkBox.setOpaque(true);
                checkBox.setBackground(isSelected ? table.getSelectionBackground() : table.getBackground());
                return checkBox;
            }
        });

        TableColumn columnaOculta = tablaEventos.getColumnModel().getColumn(tablaEventos.getColumnCount()-1);
        columnaOculta.setMinWidth(0);
        columnaOculta.setMaxWidth(0);
        columnaOculta.setPreferredWidth(0);
        columnaOculta.setResizable(false);

        tablaEventos.setRowSorter(new TableRowSorter<>(modelo));
        tablaEventos.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value,
                                                           boolean isSelected, boolean hasFocus, int row, int column) {
                super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));
                return this;
            }
        });

        tablaEventos.setShowGrid(false);
        tablaEventos.setIntercellSpacing(new Dimension(0, 0));
        tablaEventos.setRowHeight(30);
        tablaEventos.setSelectionBackground(new Color(200, 220, 240));
        tablaEventos.setSelectionForeground(Color.BLACK);
        tablaEventos.setFont(new Font("Arial", Font.PLAIN, 14));

        header = tablaEventos.getTableHeader();
        header.setFont(new Font("Arial", Font.BOLD, 14));
        header.setBackground(new Color(255, 204, 153));
        header.setForeground(new Color(70, 70, 70));
        header.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(210, 180, 170)),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));

        JScrollPane scroll = new JScrollPane(tablaEventos);
        scroll.getViewport().setBackground(Color.WHITE);
        scroll.setOpaque(false);

        // Personalización de la barra de desplazamiento
        JScrollBar verticalScrollBar = scroll.getVerticalScrollBar();
        verticalScrollBar.setUI(new BasicScrollBarUI() {
            @Override
            protected JButton createDecreaseButton(int orientation) {
                JButton button = super.createDecreaseButton(orientation);
                button.setBackground(new Color(251, 234, 230));
                return button;
            }

            @Override
            protected JButton createIncreaseButton(int orientation) {
                JButton button = super.createIncreaseButton(orientation);
                button.setBackground(new Color(251, 234, 230));
                return button;
            }

            @Override
            protected void paintTrack(Graphics g, JComponent c, Rectangle r) {
                Graphics2D g2 = (Graphics2D) g;
                g2.setColor(new Color(251, 234, 230));
                g2.fillRoundRect(r.x, r.y, r.width, r.height, 10, 10);
            }

            @Override
            protected void paintThumb(Graphics g, JComponent c, Rectangle r) {
                Graphics2D g2 = (Graphics2D) g;
                g2.setColor(new Color(245, 156, 107));
                g2.fillRoundRect(r.x, r.y, r.width, r.height, 10, 10);
            }
        });

        JPanel panelConMargen = new JPanel(new BorderLayout());
        panelConMargen.setBackground(new Color(0xFBEAE6));
        panelConMargen.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 10));
        panelConMargen.add(scroll, BorderLayout.CENTER);

        add(panelConMargen, BorderLayout.CENTER);
    }

    private void initPopupMenu() {
        popupMenu = new JPopupMenu() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                g2.setColor(new Color(240, 240, 240, 220));
                g2.fillRoundRect(0, 0, getWidth() - 50, getHeight(), 12, 12);

                g2.setColor(new Color(200, 200, 200, 150));
                g2.drawRoundRect(0, 0, getWidth() - 50, getHeight() - 1, 12, 12);
                g2.dispose();
            }
        };
        popupMenu.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));
        popupMenu.setOpaque(false);

        Boton modificarItembtn = new Boton("Modificar", Boton.ButtonType.PRIMARY);
        configurarBotonPopup(modificarItembtn);
        modificarItembtn.addActionListener(e -> modificarEvento());

        Boton eliminarItembtn = new Boton("Eliminar", Boton.ButtonType.DELETE);
        configurarBotonPopup(eliminarItembtn);
        eliminarItembtn.addActionListener(e -> eliminarEvento());

        popupMenu.add(modificarItembtn);
        popupMenu.add(Box.createVerticalStrut(5));
        popupMenu.add(eliminarItembtn);

        UIManager.put("PopupMenu.border", BorderFactory.createEmptyBorder());
        UIManager.put("PopupMenu.background", new Color(0, 0, 0, 0));
    }

    private void configurarBotonPopup(Boton boton) {
        boton.setPreferredSize(new Dimension(150, 30));
        boton.setContentAreaFilled(false);
        boton.setBorderPainted(false);
        boton.setFocusPainted(false);
        boton.setOpaque(false);
    }


    private void inscribirseEvento(){
        int fila = tablaEventos.getSelectedRow();
        if (fila != -1) {
            int filaModelo = tablaEventos.convertRowIndexToModel(fila);
            Eventos eventoSeleccionado = (Eventos) modelo.getValueAt(filaModelo, tablaEventos.getColumnCount() - 1);
              new FormularioEstudiantesEventoEstudiante(estudianteLogeado, eventoSeleccionado);
        }
    }


    private void modificarEvento() {
        int fila = tablaEventos.getSelectedRow();
        if (fila != -1) {
            int filaModelo = tablaEventos.convertRowIndexToModel(fila);
            Eventos eventoSeleccionado = (Eventos) modelo.getValueAt(filaModelo, tablaEventos.getColumnCount() - 1);
             new ActualizarEstudiantesEventoEstudiante(estudianteLogeado, eventoSeleccionado);
        }
    }


    private void eliminarEvento() {
        int fila = tablaEventos.getSelectedRow();
        if (fila != -1) {
            new CustomDialog(null, "Eliminar Evento", "¿Está seguro de que desea eliminar este evento?", "OK_CANCEL").setVisible(true);

            if (CustomDialog.isAceptar()) {
                int filaModelo = tablaEventos.convertRowIndexToModel(fila);
                Eventos eventoSeleccionado = (Eventos) modelo.getValueAt(filaModelo, tablaEventos.getColumnCount() - 1);
                List<EstudiantesEventos> listaEstudiantesEventos = Controlador.getListaEstudiantesEventos();
                for (EstudiantesEventos estudianteEvento : listaEstudiantesEventos) {
                    if (estudianteEvento.getEvento().equals(eventoSeleccionado)) {
                        Controlador.eliminarControladorEstudianteEvento(estudianteEvento);
                    }else new CustomDialog(null, "Error", "No se pudo eliminar el evento.", "ONLY_OK").setVisible(true);
                }
                Controlador.actualizarListaEstudiantesEventos();

                VistaPrincipalEstudiante vistaPrincipalEstudiante = (VistaPrincipalEstudiante) VistaPrincipalEstudiante.getVistaPrincipal();
                vistaPrincipalEstudiante.mostrarVistaEventos();
                new CustomDialog(null, "Evento Eliminado", "Inscripción eliminada correctamente.", "ONLY_OK").setVisible(true);

            } else {
                new CustomDialog(null, "Acción Cancelada", "Acción cancelada por el usuario.", "ONLY_OK").setVisible(true);
            }
        }
    }

    private void cargarEventosEstudiante() {
        modelo.setRowCount(0);
        if (estudianteLogeado != null) {
            for (Eventos evento : listaEventos) {
                boolean inscrito = false;

                // Obtener la lista de EstudiantesEventos para el evento actual
                List<EstudiantesEventos> listaEstudiantesEventos = Controlador.getListaEstudiantesEventos();

                // Verificar si el usuario logeado está inscrito en el evento y tiene "confirmado" en true
                for (EstudiantesEventos estudianteEvento : listaEstudiantesEventos) {
                    if (estudianteEvento.getEstudiante().equals(estudianteLogeado)
                            && estudianteEvento.getEvento().equals(evento)
                            && Boolean.TRUE.equals(estudianteEvento.getConfirmado())) {
                        inscrito = true;
                        break;
                    }
                }

                Object[] fila = {
                        evento.getNombre(),
                        evento.getDescripcion() != null ? evento.getDescripcion() : "-",
                        evento.getFechaInicio().toString(),
                        evento.getFechaFin().toString(),
                        evento.getUbicacion() != null ? evento.getUbicacion() : "-",
                        evento.getTipoEvento().toString().toUpperCase(),
                        inscrito ? "Sí" : "No",
                        evento
                };
                modelo.addRow(fila);
            }
        }
    }
}