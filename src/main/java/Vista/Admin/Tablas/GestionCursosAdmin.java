package Vista.Admin.Tablas;

import Controlador.Controlador;
import Mapeo.Cursos;
import Vista.Admin.Anadir.FormularioCursoAdmin;
import Vista.Admin.Modificar.ActualizarCursosAdmin;
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
import static Controlador.Controlador.listaCursos;

/**
 * Clase que representa la vista de gestión de cursos para el administrador.
 * Permite agregar, modificar y eliminar cursos.
 */
public class GestionCursosAdmin extends JPanel {

    private JPanel panelSuperior;
    private JLabel titulo;
    private JPanel panelBoton;
    private ImageIcon icono;
    private JTable tablaCursos;
    private JButton btnAgregar;
    private DefaultTableModel modelo;
    private JPopupMenu popupMenu;
    private JTableHeader header;

    /**
     * Constructor de la clase GestionCursosAdmin.
     * Inicializa la interfaz gráfica y carga los cursos.
     */
    public GestionCursosAdmin() {
        setLayout(new BorderLayout());
        initGUI();
        initEventos();
        cargarCursosAdmin();
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

        titulo = new JLabel("Colegio Salesiano San Francisco de Sales - Cursos", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 24));
        titulo.setBorder(BorderFactory.createEmptyBorder(25, 10, 30, 10));
        panelSuperior.add(titulo, BorderLayout.NORTH);

        panelBoton = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelBoton.setOpaque(false);

        icono = new ImageIcon(Objects.requireNonNull(getClass().getResource("/icons/anadir.png")));
        icono.setImage(icono.getImage().getScaledInstance(24, 24, Image.SCALE_SMOOTH));

        btnAgregar = new Boton("Agregar Curso", Boton.tipoBoton.PRIMARY);
        btnAgregar.setIcon(icono);
        btnAgregar.setPreferredSize(new Dimension(180, 30));
        btnAgregar.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));

        panelBoton.add(btnAgregar);
        panelSuperior.add(panelBoton, BorderLayout.SOUTH);

        add(panelSuperior, BorderLayout.NORTH);
    }

    /**
     * Método para inicializar la tabla de cursos.
     */
    private void initTabla() {
        String[] columnas = {"Nombre", "Descripción", "Profesor", "Estado", "Objeto"};
        modelo = new DefaultTableModel(null, columnas) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        tablaCursos = new JTable(modelo) {
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

        TableColumn columnaOculta = tablaCursos.getColumnModel().getColumn(tablaCursos.getColumnCount()-1);
        columnaOculta.setMinWidth(0);
        columnaOculta.setMaxWidth(0);
        columnaOculta.setPreferredWidth(0);
        columnaOculta.setResizable(false);

        tablaCursos.setRowSorter(new TableRowSorter<>(modelo));
        tablaCursos.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value,
                                                           boolean isSelected, boolean hasFocus, int row, int column) {
                super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));
                return this;
            }
        });

        tablaCursos.setShowGrid(false);
        tablaCursos.setIntercellSpacing(new Dimension(0, 0));
        tablaCursos.setRowHeight(30);
        tablaCursos.setSelectionBackground(new Color(200, 220, 240));
        tablaCursos.setSelectionForeground(Color.BLACK);
        tablaCursos.setFont(new Font("Arial", Font.PLAIN, 14));

        header = tablaCursos.getTableHeader();
        header.setFont(new Font("Arial", Font.BOLD, 14));
        header.setBackground(new Color(255, 204, 153));
        header.setForeground(new Color(70, 70, 70));
        header.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(210, 180, 170)),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));

        JScrollPane scroll = new JScrollPane(tablaCursos);
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
        btnAgregar.addActionListener(e -> new FormularioCursoAdmin());

        header.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int column = header.columnAtPoint(e.getPoint());
                TableRowSorter<?> sorter = (TableRowSorter<?>) tablaCursos.getRowSorter();
                if (column >= 0 && sorter != null) {
                    SortOrder currentOrder = sorter.getSortKeys().isEmpty() ? null : sorter.getSortKeys().get(0).getSortOrder();
                    SortOrder newOrder = currentOrder == SortOrder.DESCENDING ? SortOrder.ASCENDING : SortOrder.DESCENDING;
                    sorter.setSortKeys(Arrays.asList(new RowSorter.SortKey(column, newOrder)));
                }
            }
        });

        tablaCursos.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                int row = tablaCursos.rowAtPoint(e.getPoint());
                if (row >= 0) {
                    tablaCursos.setSelectionBackground(new Color(245, 156, 107, 204));
                }
            }
        });

        tablaCursos.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int row = tablaCursos.rowAtPoint(e.getPoint());
                if (row >= 0) {
                    tablaCursos.setRowSelectionInterval(row, row);
                    if (SwingUtilities.isRightMouseButton(e)) {
                        // Verificar si el clic está en la parte baja de la tabla
                        int visibleHeight = tablaCursos.getVisibleRect().height;
                        int clickY = e.getY();
                        if (clickY > visibleHeight - 100) { // Ajustar si está cerca del borde inferior
                            popupMenu.show(tablaCursos, e.getX(), e.getY() - 80);
                        } else {
                            popupMenu.show(tablaCursos, e.getX(), e.getY());
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
        modificarItembtn.addActionListener(e -> modificarCurso());

        Boton eliminarItembtn = new Boton("Eliminar", Boton.tipoBoton.DELETE);
        configurarBotonPopup(eliminarItembtn);
        eliminarItembtn.addActionListener(e -> eliminarCurso());

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
     * Método para modificar un curso seleccionado en la tabla.
     * Abre un formulario para editar el curso.
     */
    private void modificarCurso() {
        int fila = tablaCursos.getSelectedRow();
        if (fila != -1) {
            int filaModelo = tablaCursos.convertRowIndexToModel(fila);
            Cursos cursoSeleccionado = (Cursos) modelo.getValueAt(filaModelo, tablaCursos.getColumnCount() - 1);
            new ActualizarCursosAdmin(cursoSeleccionado);
        }
    }

    /**
     * Método para eliminar un curso seleccionado en la tabla.
     * Pide confirmación antes de eliminar el curso.
     */
    private void eliminarCurso() {
        int fila = tablaCursos.getSelectedRow();
        if (fila != -1) {
            new CustomDialog(null, "Eliminar Curso", "¿Está seguro de que desea eliminar este curso?", "OK_CANCEL").setVisible(true);

            if (CustomDialog.isAceptar()) {
                int filaModelo = tablaCursos.convertRowIndexToModel(fila);
                Cursos cursoSeleccionado = (Cursos) modelo.getValueAt(filaModelo, tablaCursos.getColumnCount() - 1);
                Controlador.eliminarControladorCurso(cursoSeleccionado);
                Controlador.actualizarListaCursos();

                VistaPrincipalAdmin vistaPrincipalAdmin = (VistaPrincipalAdmin) VistaPrincipalAdmin.getVistaPrincipal();
                vistaPrincipalAdmin.mostrarVistaCursos();
                new CustomDialog(null, "Curso Eliminado", "Curso eliminado correctamente.", "ONLY_OK").setVisible(true);

            } else {
                new CustomDialog(null, "Acción Cancelada", "Acción cancelada por el usuario.", "ONLY_OK").setVisible(true);
            }
        }
    }

    /**
     * Método para cargar los cursos en la tabla.
     * Se obtienen los cursos de la lista y se añaden a la tabla.
     */
    private void cargarCursosAdmin() {
        modelo.setRowCount(0);
        for (Cursos curso : listaCursos) {
            String nombreProfesor = curso.getProfesor() != null
                    ? curso.getProfesor().getNombre() + " " + curso.getProfesor().getApellido()
                    : "Sin asignar";

            Object[] fila = {
                    curso.getNombre(),
                    curso.getDescripcion(),
                    nombreProfesor,
                    curso.getEstado().name(),
                    curso
            };
            modelo.addRow(fila);
        }
    }
}
