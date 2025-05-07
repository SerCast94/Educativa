package Vista.Estudiante.Tablas;

import Mapeo.Asignaturas;
import Mapeo.CursosAsignaturas;
import Mapeo.Profesores;
import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Arrays;
import java.util.List;
import static Controlador.Controlador.listaProfesores;
import static Controlador.ControladorLogin.estudianteLogeado;

/**
 * Clase que representa la gestión de profesores en la vista de estudiante.
 * Ofrece información de contacto de los distintos profesores que imparten clase al estudiante.
 */
public class GestionProfesoresEstudiante extends JPanel {
    private JTable tablaProfesores;
    private DefaultTableModel modelo;
    private JTableHeader header;
    private JPanel panelSuperior;
    private JLabel titulo;
    private JPanel panelBoton;

    /**
     * Constructor de la clase GestionProfesoresEstudiante.
     * Inicializa la interfaz gráfica y carga los profesores del estudiante.
     */
    public GestionProfesoresEstudiante() {
        setLayout(new BorderLayout());
        initGUI();
        initEventos();
        cargarProfesoresEstudiante();
    }

    /**
     * Método para inicializar la interfaz gráfica.
     */
    private void initGUI() {
        initPanelSuperior();
        initTabla();
    }

    /**
     * Método para inicializar el panel superior de la interfaz.
     */
    private void initPanelSuperior() {
        panelSuperior = new JPanel(new BorderLayout());
        panelSuperior.setBorder(BorderFactory.createEmptyBorder(20, 10, 10, 10));
        panelSuperior.setBackground(new Color(251, 234, 230));

        titulo = new JLabel("Colegio Salesiano San Francisco de Sales - Profesores", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 24));
        titulo.setBorder(BorderFactory.createEmptyBorder(25, 10, 30, 10));
        panelSuperior.add(titulo, BorderLayout.NORTH);

        panelBoton = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelBoton.setOpaque(false);

        panelSuperior.add(panelBoton, BorderLayout.SOUTH);

        add(panelSuperior, BorderLayout.NORTH);
    }

    /**
     * Método para inicializar la tabla de profesores.
     */
    private void initTabla() {
        String[] columnas = {"Nombre", "Apellido", "Email", "Teléfono","Objeto"};
             modelo = new DefaultTableModel(null, columnas) {
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
        };
        tablaProfesores = new JTable(modelo) {
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

        TableColumn columnaOculta = tablaProfesores.getColumnModel().getColumn(tablaProfesores.getColumnCount()-1);
        columnaOculta.setMinWidth(0);
        columnaOculta.setMaxWidth(0);
        columnaOculta.setPreferredWidth(0);
        columnaOculta.setResizable(false);

        tablaProfesores.setRowSorter(new TableRowSorter<>(modelo));
        tablaProfesores.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value,
                                                           boolean isSelected, boolean hasFocus, int row, int column) {
                super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));
                return this;
            }
        });

        tablaProfesores.setShowGrid(false);
        tablaProfesores.setIntercellSpacing(new Dimension(0, 0));
        tablaProfesores.setRowHeight(30);
        tablaProfesores.setSelectionBackground(new Color(200, 220, 240));
        tablaProfesores.setSelectionForeground(Color.BLACK);
        tablaProfesores.setFont(new Font("Arial", Font.PLAIN, 14));

        header = tablaProfesores.getTableHeader();
        header.setFont(new Font("Arial", Font.BOLD, 14));
        header.setBackground(new Color(255, 204, 153));
        header.setForeground(new Color(70, 70, 70));
        header.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(210, 180, 170)),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));

        JScrollPane scroll = new JScrollPane(tablaProfesores);
        scroll.getViewport().setBackground(Color.WHITE);
        scroll.setOpaque(false);

        JScrollBar verticalScrollBar = scroll.getVerticalScrollBar();
        verticalScrollBar.setUI(new javax.swing.plaf.basic.BasicScrollBarUI() {
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
        panelConMargen.setBorder(BorderFactory.createEmptyBorder(30, 0, 10, 10));
        panelConMargen.add(scroll, BorderLayout.CENTER);

        add(panelConMargen, BorderLayout.CENTER);
    }

    /**
     * Método para inicializar los eventos de la interfaz.
     */
    private void initEventos() {

        header.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int column = header.columnAtPoint(e.getPoint());
                TableRowSorter<?> sorter = (TableRowSorter<?>) tablaProfesores.getRowSorter();
                if (column >= 0 && sorter != null) {
                    SortOrder currentOrder = sorter.getSortKeys().isEmpty() ? null : sorter.getSortKeys().get(0).getSortOrder();
                    SortOrder newOrder = currentOrder == SortOrder.DESCENDING ? SortOrder.ASCENDING : SortOrder.DESCENDING;
                    sorter.setSortKeys(Arrays.asList(new RowSorter.SortKey(column, newOrder)));
                }
            }
        });

        tablaProfesores.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                int row = tablaProfesores.rowAtPoint(e.getPoint());
                if (row >= 0) {
                    tablaProfesores.setSelectionBackground(new Color(245, 156, 107, 204));
                }
            }
        });
    }

    /**
     * Método para cargar los profesores que imparten clase al estudiante.
     * Se obtienen los datos de los profesores y se añaden a la tabla.
     */
    private void cargarProfesoresEstudiante() {
        modelo.setRowCount(0);
        if (estudianteLogeado.getMatriculas() != null && !estudianteLogeado.getMatriculas().isEmpty()) {
            for (Profesores profesor : listaProfesores) {
                boolean imparteClase = false;
                List<CursosAsignaturas> cursoAsignaturas = estudianteLogeado.getMatriculas().get(0).getCurso().getCursosAsignaturas();

                for (CursosAsignaturas cursoAsignatura : cursoAsignaturas) {
                    Asignaturas asignatura = cursoAsignatura.getAsignatura();
                    if (asignatura.getProfesor().equals(profesor)) {
                        imparteClase = true;
                        break;
                    }
                }

                if (imparteClase) {
                    Object[] fila = {
                            profesor.getNombre(),
                            profesor.getApellido(),
                            profesor.getEmail(),
                            profesor.getTelefono(),
                            profesor
                    };
                    modelo.addRow(fila);
                }
            }
        }
    }

}

