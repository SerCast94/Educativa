package Vista.Profesor.Tablas;

import Controlador.Controlador;
import Mapeo.Asistencia;
import Vista.Profesor.Anadir.FormularioAsistenciaProfesor;
import Vista.Profesor.Modificar.ActualizarAsistenciaProfesor;
import Vista.Profesor.VistaPrincipalProfesor;
import Vista.Util.Boton;
import Vista.Util.CustomDialog;
import javax.swing.*;
import javax.swing.plaf.basic.BasicScrollBarUI;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Arrays;
import java.util.Objects;
import static Controlador.Controlador.listaAsistencia;
import static Controlador.ControladorLogin.profesorLogeado;
import static Vista.Util.EstiloComponentes.checkPersonalizadoGris;

/**
 * Clase que representa la gestión de asistencias del profesor.
 * Permite visualizar, agregar, modificar y eliminar registros de asistencia.
 */
public class GestionAsistenciaProfesor extends JPanel {
    private JTable tablaAsistencias;
    private JButton btnAgregar;
    private DefaultTableModel modelo;
    private JPopupMenu popupMenu;
    private JTableHeader header;
    private JPanel panelSuperior;
    private JLabel titulo;
    private JPanel panelBoton;
    private ImageIcon icono;


    /**
     * Constructor de la clase GestionAsistenciaProfesor.
     * Inicializa la interfaz gráfica y carga las asistencias.
     */
    public GestionAsistenciaProfesor() {
        setLayout(new BorderLayout());
        initGUI();
        initEventos();
        cargarAsistenciasProfesor();
    }

    /**
     * Método para inicializar la interfaz gráfica.
     */
    private void initGUI() {
        initPanelSuperior();
        initTabla();
        initPopupMenu();
    }

    /**
     * Método para inicializar el panel superior de la interfaz.
     */
    private void initPanelSuperior() {
        panelSuperior = new JPanel(new BorderLayout());
        panelSuperior.setBorder(BorderFactory.createEmptyBorder(20, 10, 10, 10));
        panelSuperior.setBackground(new Color(251, 234, 230));

        titulo = new JLabel("Colegio Salesiano San Francisco de Sales - Asistencias", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 24));
        titulo.setBorder(BorderFactory.createEmptyBorder(25, 10, 30, 10));
        panelSuperior.add(titulo, BorderLayout.NORTH);

        panelBoton = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelBoton.setOpaque(false);

        icono = new ImageIcon(Objects.requireNonNull(getClass().getResource("/icons/anadir.png")));
        icono.setImage(icono.getImage().getScaledInstance(24, 24, Image.SCALE_SMOOTH));

        btnAgregar = new Boton("Agregar Asistencia", Boton.tipoBoton.PRIMARY);
        btnAgregar.setIcon(icono);
        btnAgregar.setPreferredSize(new Dimension(180, 30));
        btnAgregar.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));

        panelBoton.add(btnAgregar);
        panelSuperior.add(panelBoton, BorderLayout.SOUTH);

        add(panelSuperior, BorderLayout.NORTH);
    }

    /**
     * Método para inicializar la tabla de asistencias.
     */
    private void initTabla() {
        String[] columnas = {"Estudiante", "Curso", "Fecha", "Justificado", "Motivo de ausencia", "Objeto"};
        modelo = new DefaultTableModel(null, columnas) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        tablaAsistencias = new JTable(modelo) {
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

        tablaAsistencias.getColumnModel().getColumn(3).setCellRenderer(new DefaultTableCellRenderer() {
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

        TableColumn columnaOculta = tablaAsistencias.getColumnModel().getColumn(tablaAsistencias.getColumnCount() - 1);
        columnaOculta.setMinWidth(0);
        columnaOculta.setMaxWidth(0);
        columnaOculta.setPreferredWidth(0);
        columnaOculta.setResizable(false);

        tablaAsistencias.setRowSorter(new TableRowSorter<>(modelo));
        tablaAsistencias.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value,
                                                           boolean isSelected, boolean hasFocus, int row, int column) {
                super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));
                return this;
            }
        });

        tablaAsistencias.setShowGrid(false);
        tablaAsistencias.setIntercellSpacing(new Dimension(0, 0));
        tablaAsistencias.setRowHeight(30);
        tablaAsistencias.setSelectionBackground(new Color(200, 220, 240));
        tablaAsistencias.setSelectionForeground(Color.BLACK);
        tablaAsistencias.setFont(new Font("Arial", Font.PLAIN, 14));

        header = tablaAsistencias.getTableHeader();
        header.setFont(new Font("Arial", Font.BOLD, 14));
        header.setBackground(new Color(255, 204, 153));
        header.setForeground(new Color(70, 70, 70));
        header.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(210, 180, 170)),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));

        JScrollPane scroll = new JScrollPane(tablaAsistencias);
        scroll.getViewport().setBackground(Color.WHITE);
        scroll.setOpaque(false);

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

    /**
     * Método para inicializar los eventos de la interfaz.
     */
    private void initEventos() {
        btnAgregar.addActionListener(e -> new FormularioAsistenciaProfesor());

        header.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int column = header.columnAtPoint(e.getPoint());
                TableRowSorter<?> sorter = (TableRowSorter<?>) tablaAsistencias.getRowSorter();
                if (column >= 0 && sorter != null) {
                    SortOrder currentOrder = sorter.getSortKeys().isEmpty() ? null : sorter.getSortKeys().get(0).getSortOrder();
                    SortOrder newOrder = currentOrder == SortOrder.DESCENDING ? SortOrder.ASCENDING : SortOrder.DESCENDING;
                    sorter.setSortKeys(Arrays.asList(new RowSorter.SortKey(column, newOrder)));
                }
            }
        });

        tablaAsistencias.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                int row = tablaAsistencias.rowAtPoint(e.getPoint());
                if (row >= 0) {
                    tablaAsistencias.setSelectionBackground(new Color(245, 156, 107, 204));
                }
            }
        });

        tablaAsistencias.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int row = tablaAsistencias.rowAtPoint(e.getPoint());
                if (row >= 0) {
                    tablaAsistencias.setRowSelectionInterval(row, row);
                    if (SwingUtilities.isRightMouseButton(e)) {
                        int visibleHeight = tablaAsistencias.getVisibleRect().height;
                        int clickY = e.getY();
                        if (clickY > visibleHeight - 100) {
                            popupMenu.show(tablaAsistencias, e.getX(), e.getY() - 80);
                        } else {
                            popupMenu.show(tablaAsistencias, e.getX(), e.getY());
                        }
                    }
                }
            }
        });
    }

    /**
     * Método para inicializar el menú emergente (Modificar y eliminar).
     */
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

        Boton modificarItembtn = new Boton("Modificar", Boton.tipoBoton.PRIMARY);
        configurarBotonPopup(modificarItembtn);
        modificarItembtn.addActionListener(e -> modificarAsistencia());

        Boton eliminarItembtn = new Boton("Eliminar", Boton.tipoBoton.DELETE);
        configurarBotonPopup(eliminarItembtn);
        eliminarItembtn.addActionListener(e -> eliminarAsistencia());

        popupMenu.add(modificarItembtn);
        popupMenu.add(Box.createVerticalStrut(5));
        popupMenu.add(eliminarItembtn);

        UIManager.put("PopupMenu.border", BorderFactory.createEmptyBorder());
        UIManager.put("PopupMenu.background", new Color(0, 0, 0, 0));
    }

    /**
     * Método para configurar el botón del menú emergente.
     * @param boton El botón a configurar.
     */
    private void configurarBotonPopup(Boton boton) {
        boton.setPreferredSize(new Dimension(150, 30));
        boton.setContentAreaFilled(false);
        boton.setBorderPainted(false);
        boton.setFocusPainted(false);
        boton.setOpaque(false);
    }

    /**
     * Método para modificar la asistencia seleccionada en la tabla.
     * Abre un formulario para actualizar la asistencia.
     */
    private void modificarAsistencia() {
        int fila = tablaAsistencias.getSelectedRow();
        if (fila != -1) {
            int filaModelo = tablaAsistencias.convertRowIndexToModel(fila);
            Asistencia asistenciaSeleccionada = (Asistencia) modelo.getValueAt(filaModelo, tablaAsistencias.getColumnCount() - 1);
            new ActualizarAsistenciaProfesor(asistenciaSeleccionada);
        }
    }

    /**
     * Método para eliminar la asistencia seleccionada en la tabla.
     * Pide confirmación al usuario antes de eliminar.
     */
    private void eliminarAsistencia() {
        int fila = tablaAsistencias.getSelectedRow();
        if (fila != -1) {
            new CustomDialog(null, "Eliminar Asistencia", "¿Está seguro de que desea eliminar este registro de asistencia?", "OK_CANCEL").setVisible(true);

            if (CustomDialog.isAceptar()) {
                int filaModelo = tablaAsistencias.convertRowIndexToModel(fila);
                Asistencia asistenciaSeleccionada = (Asistencia) modelo.getValueAt(filaModelo, tablaAsistencias.getColumnCount() - 1);
                Controlador.eliminarControladorAsistencia(asistenciaSeleccionada);
                Controlador.actualizarListaAsistencia();

                VistaPrincipalProfesor vistaPrincipalProfesor = (VistaPrincipalProfesor) VistaPrincipalProfesor.getVistaPrincipal();
                vistaPrincipalProfesor.mostrarVistaAsistencia();
                new CustomDialog(null, "Asistencia Eliminada", "Registro de asistencia eliminado correctamente.", "ONLY_OK").setVisible(true);

            } else {
                new CustomDialog(null, "Acción Cancelada", "Acción cancelada por el usuario.", "ONLY_OK").setVisible(true);
            }
        }
    }

    /**
     * Método para cargar las asistencias del profesor en la tabla.
     */
    private void cargarAsistenciasProfesor() {
        modelo.setRowCount(0);
        for (Asistencia asistencia : listaAsistencia) {

            if (asistencia.getCurso().getProfesor().equals(profesorLogeado)){
                Object[] fila = {
                        asistencia.getEstudiante().getNombre() + " " + asistencia.getEstudiante().getApellido(),
                        asistencia.getCurso().getNombre(),
                        asistencia.getFecha().toString(),
                        asistencia.getJustificado() ? "Sí" : "No",
                        asistencia.getMotivoAusencia() != null ? asistencia.getMotivoAusencia() : "-",
                        asistencia
                };
                modelo.addRow(fila);
            }
        }
    }
}