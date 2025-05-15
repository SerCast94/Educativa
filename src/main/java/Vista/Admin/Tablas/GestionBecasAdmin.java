package Vista.Admin.Tablas;

import Controlador.Controlador;
import Mapeo.Becas;
import Vista.Admin.Anadir.FormularioBecasAdmin;
import Vista.Admin.Modificar.ActualizarBecasAdmin;
import Vista.Admin.VistaPrincipalAdmin;
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
import static Controlador.Controlador.listaBecas;

/**
 * Clase que representa la gestión de becas en la interfaz de administración.
 * Permite visualizar, agregar, modificar y eliminar becas.
 */
public class GestionBecasAdmin extends JPanel {

    private JPanel panelSuperior;
    private JLabel titulo;
    private JPanel panelBoton;
    private ImageIcon icono;
    private JTable tablaBecas;
    private JButton btnAgregar;
    private DefaultTableModel modelo;
    private JPopupMenu popupMenu;
    private JTableHeader header;

    /**
     * Constructor de la clase GestionBecasAdmin.
     * Inicializa la interfaz gráfica y carga las becas existentes.
     */
    public GestionBecasAdmin() {
        setLayout(new BorderLayout());
        initGUI();
        initEventos();
        cargarBecasAdmin();
    }

    /**
     * Método que inicializa la interfaz gráfica.
     */
    private void initGUI() {
        initPanelSuperior();
        initTabla();
        initPopupMenu();
    }

    /**
     * Método que inicializa el panel superior de la interfaz gráfica.
     */
    private void initPanelSuperior() {
        panelSuperior = new JPanel(new BorderLayout());
        panelSuperior.setBorder(BorderFactory.createEmptyBorder(20, 10, 10, 10));
        panelSuperior.setBackground(new Color(251, 234, 230));

        titulo = new JLabel("Colegio Salesiano San Francisco de Sales - Gestión de Becas", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 24));
        titulo.setBorder(BorderFactory.createEmptyBorder(25, 10, 30, 10));
        panelSuperior.add(titulo, BorderLayout.NORTH);

        panelBoton = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelBoton.setOpaque(false);

        icono = new ImageIcon(Objects.requireNonNull(getClass().getResource("/icons/anadir.png")));
        icono.setImage(icono.getImage().getScaledInstance(24, 24, Image.SCALE_SMOOTH));

        btnAgregar = new Boton("Agregar Beca", Boton.tipoBoton.PRIMARY);
        btnAgregar.setIcon(icono);
        btnAgregar.setPreferredSize(new Dimension(180, 30));
        btnAgregar.setBorder(BorderFactory.createEmptyBorder());

        panelBoton.add(btnAgregar);
        panelSuperior.add(panelBoton, BorderLayout.SOUTH);

        add(panelSuperior, BorderLayout.NORTH);
    }

    /**
     * Método que inicializa la tabla de becas.
     */
    private void initTabla() {
        String[] columnas = {"Estudiante", "Tipo de Beca", "Monto", "Fecha Asignación", "Estado", "Comentarios", "Objeto"};
        modelo = new DefaultTableModel(null, columnas) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        tablaBecas = new JTable(modelo) {
            @Override
            public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
                Component c = super.prepareRenderer(renderer, row, column);
                if (!isRowSelected(row)) {
                    c.setBackground(row % 2 == 0 ? Color.WHITE : new Color(245, 245, 245));
                }
                c.setForeground(isRowSelected(row) ? Color.BLACK : new Color(70, 70, 70));
                return c;
            }
        };

        tablaBecas.setRowSorter(new TableRowSorter<>(modelo));
        tablaBecas.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));
                return this;
            }
        });

        TableColumn columnaOculta = tablaBecas.getColumnModel().getColumn(tablaBecas.getColumnCount()-1);
        columnaOculta.setMinWidth(0);
        columnaOculta.setMaxWidth(0);
        columnaOculta.setPreferredWidth(0);
        columnaOculta.setResizable(false);

        tablaBecas.setShowGrid(false);
        tablaBecas.setIntercellSpacing(new Dimension(0, 0));
        tablaBecas.setRowHeight(30);
        tablaBecas.setSelectionBackground(new Color(200, 220, 240));
        tablaBecas.setSelectionForeground(Color.BLACK);
        tablaBecas.setFont(new Font("Arial", Font.PLAIN, 14));

        header = tablaBecas.getTableHeader();
        header.setFont(new Font("Arial", Font.BOLD, 14));
        header.setBackground(new Color(255, 204, 153));
        header.setForeground(new Color(70, 70, 70));
        header.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(210, 180, 170)),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));

        JScrollPane scroll = new JScrollPane(tablaBecas);
        scroll.getViewport().setBackground(Color.WHITE);

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

    /**
     * Método que inicializa los eventos de la interfaz gráfica.
     */
    private void initEventos() {
        btnAgregar.addActionListener(e -> new FormularioBecasAdmin());

        header.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int column = header.columnAtPoint(e.getPoint());
                TableRowSorter<?> sorter = (TableRowSorter<?>) tablaBecas.getRowSorter();
                if (column >= 0 && sorter != null) {
                    SortOrder currentOrder = sorter.getSortKeys().isEmpty() ? null : sorter.getSortKeys().get(0).getSortOrder();
                    SortOrder newOrder = currentOrder == SortOrder.DESCENDING ? SortOrder.ASCENDING : SortOrder.DESCENDING;
                    sorter.setSortKeys(Arrays.asList(new RowSorter.SortKey(column, newOrder)));
                }
            }
        });

        tablaBecas.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                int row = tablaBecas.rowAtPoint(e.getPoint());
                if (row >= 0) {
                    tablaBecas.setSelectionBackground(new Color(245, 156, 107, 204));
                }
            }
        });

        tablaBecas.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int row = tablaBecas.rowAtPoint(e.getPoint());
                if (row >= 0) {
                    tablaBecas.setRowSelectionInterval(row, row);
                    if (SwingUtilities.isRightMouseButton(e)) {
                        int visibleHeight = tablaBecas.getVisibleRect().height;
                        int clickY = e.getY();
                        if (clickY > visibleHeight - 100) {
                            popupMenu.show(tablaBecas, e.getX(), e.getY() - 80);
                        } else {
                            popupMenu.show(tablaBecas, e.getX(), e.getY());
                        }
                    }
                }
            }
        });
    }

    /**
     * Método que inicializa el menú emergente para la tabla de becas.
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
        modificarItembtn.addActionListener(e -> modificarBeca());

        Boton eliminarItembtn = new Boton("Eliminar", Boton.tipoBoton.DELETE);
        configurarBotonPopup(eliminarItembtn);
        eliminarItembtn.addActionListener(e -> eliminarBeca());

        popupMenu.add(modificarItembtn);
        popupMenu.add(Box.createVerticalStrut(5));
        popupMenu.add(eliminarItembtn);
    }

    /**
     * Método que configura el botón del menú emergente.
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
     * Método que modifica una beca seleccionada en la tabla.
     * Abre un formulario para actualizar la beca.
     */
    private void modificarBeca() {
        int fila = tablaBecas.getSelectedRow();
        if (fila != -1) {
            int filaModelo = tablaBecas.convertRowIndexToModel(fila);
            Becas becaSeleccionada = (Becas) modelo.getValueAt(filaModelo, tablaBecas.getColumnCount() - 1);
            new ActualizarBecasAdmin(becaSeleccionada);
        }
    }

    /**
     * Método que elimina una beca seleccionada en la tabla.
     * Pide confirmación al usuario antes de eliminar.
     */
    private void eliminarBeca() {
        int fila = tablaBecas.getSelectedRow();
        if (fila != -1) {
            new CustomDialog(null, "Eliminar Beca", "¿Está seguro de que desea eliminar esta beca?", "OK_CANCEL").setVisible(true);

            if (CustomDialog.isAceptar()) {
                int filaModelo = tablaBecas.convertRowIndexToModel(fila);
                Becas becaSeleccionada = (Becas) modelo.getValueAt(filaModelo, tablaBecas.getColumnCount() - 1);
                Controlador.eliminarControladorBeca(becaSeleccionada);
                Controlador.actualizarListaBecas();

                VistaPrincipalAdmin vistaPrincipalAdmin = (VistaPrincipalAdmin) VistaPrincipalAdmin.getVistaPrincipal();
                vistaPrincipalAdmin.mostrarVistaBecas();
                new CustomDialog(null, "Beca Eliminada", "Beca eliminada correctamente.", "ONLY_OK").setVisible(true);

            } else {
                new CustomDialog(null, "Acción Cancelada", "Acción cancelada por el usuario.", "ONLY_OK").setVisible(true);
            }
        }
    }

    /**
     * Método que carga las becas en la tabla.
     * Se obtienen los datos de la lista de becas y se añaden a la tabla.
     */
    private void cargarBecasAdmin() {
        modelo.setRowCount(0);
        for (Becas beca : listaBecas) {
            Object[] fila = {
                    beca.getEstudiante().getNombre() + " " + beca.getEstudiante().getApellido(),
                    beca.getTipoBeca().toString(),
                    beca.getMonto(),
                    beca.getFechaAsignacion().toString(),
                    beca.getEstadoBeca().toString(),
                    beca.getComentarios(),
                    beca
            };
            modelo.addRow(fila);
        }
    }
}
