package Vista.Admin.Tablas;

import Controlador.Controlador;
import Mapeo.Asignaturas;
import Mapeo.Cursos;
import Mapeo.CursosAsignaturas;
import Vista.Admin.Anadir.FormularioAsignaturasAdmin;
import Vista.Admin.Modificar.ActualizarAsignaturasAdmin;
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

import static Controlador.Controlador.*;

/**
 * Clase que representa la vista de gestión de todas las asignaturas mediante una tabla.
 * Permite agregar, modificar y eliminar asignaturas.
 */
public class GestionAsignaturasAdmin extends JPanel {

    private JPanel panelSuperior;
    private JLabel titulo;
    private JPanel panelBoton;
    private ImageIcon icono;
    private JTable tablaAsignaturas;
    private JButton btnAgregar;
    private DefaultTableModel modelo;
    private JPopupMenu popupMenu;
    private JTableHeader header;

    /**
     * Constructor de la clase GestionAsignaturasAdmin.
     * Inicializa la interfaz gráfica y carga las asignaturas.
     */
    public GestionAsignaturasAdmin() {
        setLayout(new BorderLayout());
        initGUI();
        initEventos();
        cargarAsignaturasAdmin();
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
     * Método para inicializar el panel superior de la interfaz gráfica.
     */
    private void initPanelSuperior() {
        panelSuperior = new JPanel(new BorderLayout());
        panelSuperior.setBorder(BorderFactory.createEmptyBorder(20, 10, 10, 10));
        panelSuperior.setBackground(new Color(251, 234, 230));

        titulo = new JLabel("Colegio Salesiano San Francisco de Sales - Asignaturas", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 24));
        titulo.setBorder(BorderFactory.createEmptyBorder(25, 10, 30, 10));
        panelSuperior.add(titulo, BorderLayout.NORTH);

        panelBoton = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelBoton.setOpaque(false);

        icono = new ImageIcon(Objects.requireNonNull(getClass().getResource("/icons/anadir.png")));
        icono.setImage(icono.getImage().getScaledInstance(24, 24, Image.SCALE_SMOOTH));

        btnAgregar = new Boton("Agregar Asignatura", Boton.tipoBoton.PRIMARY);
        btnAgregar.setIcon(icono);
        btnAgregar.setPreferredSize(new Dimension(180, 30));
        btnAgregar.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));

        panelBoton.add(btnAgregar);
        panelSuperior.add(panelBoton, BorderLayout.SOUTH);

        add(panelSuperior, BorderLayout.NORTH);
    }

    /**
     * Método para inicializar la tabla de asignaturas.
     */
    private void initTabla() {
        String[] columnas = {"Nombre", "Descripción", "Profesor", "Curso", "Estado", "Objeto"};
        modelo = new DefaultTableModel(null, columnas) {
             @Override
             public boolean isCellEditable(int row, int column) {
                 return false;
             }
        };

        tablaAsignaturas = new JTable(modelo) {
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

        TableColumn columnaOculta = tablaAsignaturas.getColumnModel().getColumn(tablaAsignaturas.getColumnCount()-1);
        columnaOculta.setMinWidth(0);
        columnaOculta.setMaxWidth(0);
        columnaOculta.setPreferredWidth(0);
        columnaOculta.setResizable(false);

        tablaAsignaturas.setRowSorter(new TableRowSorter<>(modelo));
        tablaAsignaturas.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
             @Override
             public Component getTableCellRendererComponent(JTable table, Object value,
                                                            boolean isSelected, boolean hasFocus, int row, int column) {
                 super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                 setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));
                 return this;
             }
        });

        tablaAsignaturas.setShowGrid(false);
        tablaAsignaturas.setIntercellSpacing(new Dimension(0, 0));
        tablaAsignaturas.setRowHeight(30);
        tablaAsignaturas.setSelectionBackground(new Color(200, 220, 240));
        tablaAsignaturas.setSelectionForeground(Color.BLACK);
        tablaAsignaturas.setFont(new Font("Arial", Font.PLAIN, 14));

        header = tablaAsignaturas.getTableHeader();
        header.setFont(new Font("Arial", Font.BOLD, 14));
        header.setBackground(new Color(255, 204, 153));
        header.setForeground(new Color(70, 70, 70));
        header.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(210, 180, 170)),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));

        JScrollPane scroll = new JScrollPane(tablaAsignaturas);
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

    /**
     * Método para inicializar los eventos de la interfaz gráfica.
     */
    private void initEventos() {
        btnAgregar.addActionListener(e -> new FormularioAsignaturasAdmin());

        header.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int column = header.columnAtPoint(e.getPoint());
                TableRowSorter<?> sorter = (TableRowSorter<?>) tablaAsignaturas.getRowSorter();
                if (column >= 0 && sorter != null) {
                    SortOrder currentOrder = sorter.getSortKeys().isEmpty() ? null : sorter.getSortKeys().get(0).getSortOrder();
                    SortOrder newOrder = currentOrder == SortOrder.DESCENDING ? SortOrder.ASCENDING : SortOrder.DESCENDING;
                    sorter.setSortKeys(Arrays.asList(new RowSorter.SortKey(column, newOrder)));
                }
            }
        });

        tablaAsignaturas.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                int row = tablaAsignaturas.rowAtPoint(e.getPoint());
                if (row >= 0) {
                    tablaAsignaturas.setSelectionBackground(new Color(245, 156, 107, 204));
                }
            }
        });

        tablaAsignaturas.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int row = tablaAsignaturas.rowAtPoint(e.getPoint());
                if (row >= 0) {
                    tablaAsignaturas.setRowSelectionInterval(row, row);
                    if (SwingUtilities.isRightMouseButton(e)) {
                        int visibleHeight = tablaAsignaturas.getVisibleRect().height;
                        int clickY = e.getY();
                        if (clickY > visibleHeight - 100) {
                            popupMenu.show(tablaAsignaturas, e.getX(), e.getY() -80);
                        } else {
                            popupMenu.show(tablaAsignaturas, e.getX(), e.getY());
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
        modificarItembtn.addActionListener(e -> modificarAsignatura());

        Boton eliminarItembtn = new Boton("Eliminar", Boton.tipoBoton.DELETE);
        configurarBotonPopup(eliminarItembtn);
        eliminarItembtn.addActionListener(e -> eliminarAsignatura());

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
     * Método para modificar una asignatura seleccionada en la tabla.
     * Abre un formulario para editar la asignatura.
     */
    private void modificarAsignatura() {
        int fila = tablaAsignaturas.getSelectedRow();
        if (fila != -1) {
            int filaModelo = tablaAsignaturas.convertRowIndexToModel(fila);
            Asignaturas asignaturaSeleccionada = (Asignaturas) modelo.getValueAt(filaModelo, tablaAsignaturas.getColumnCount()-1);
           new ActualizarAsignaturasAdmin(asignaturaSeleccionada);
        }
    }

    /**
     * Método para eliminar una asignatura seleccionada en la tabla.
     * Pide confirmación al usuario antes de eliminar.
     */
    private void eliminarAsignatura() {
        int fila = tablaAsignaturas.getSelectedRow();
        if (fila != -1) {
            new CustomDialog(null, "Eliminar Asignatura", "¿Está seguro de que desea eliminar esta asignatura?", "OK_CANCEL").setVisible(true);

            if (CustomDialog.isAceptar()) {
                Asignaturas asignaturaSeleccionada = (Asignaturas) modelo.getValueAt(fila, tablaAsignaturas.getColumnCount() - 1);
                Controlador.eliminarControladorAsignatura(asignaturaSeleccionada);
                Controlador.actualizarListaAsignaturas();

                VistaPrincipalAdmin vistaPrincipalAdmin = (VistaPrincipalAdmin) VistaPrincipalAdmin.getVistaPrincipal();
                vistaPrincipalAdmin.mostrarVistaAsignaturas();
                new CustomDialog(null, "Asignatura Eliminada", "Asignatura " + asignaturaSeleccionada.toString() + " eliminada correctamente.", "ONLY_OK").setVisible(true);

            } else {
                new CustomDialog(null, "Acción Cancelada", "Acción cancelada por el usuario.", "ONLY_OK").setVisible(true);
            }
        }
    }

    /**
     * Método para cargar las asignaturas en la tabla.
     * Se obtienen los datos de las asignaturas y se añaden a la tabla.
     */
    private void cargarAsignaturasAdmin() {
        modelo.setRowCount(0);
        for (Asignaturas asignatura : listaAsignaturas) {

            String nombreCurso = "Sin Curso";

            for (CursosAsignaturas cursoAsignaturas : listaCursosAsignaturas) {
                if (Objects.equals(cursoAsignaturas.getAsignatura(), asignatura)) {

                    for (Cursos curso : listaCursos) {
                        if (Objects.equals(curso, cursoAsignaturas.getCurso())) {
                            nombreCurso = curso.getNombre();
                            break;
                        }
                    }
                    break;
                }
            }

            Object[] fila = {
                    asignatura.getNombre(),
                    asignatura.getDescripcion(),
                    asignatura.getProfesor().toString(),
                    nombreCurso,
                    asignatura.getEstado().toString(),
                    asignatura
            };
            modelo.addRow(fila);
        }
    }

}
