package Vista.Profesor.Tablas;

import BackUtil.GeneradorHorario;
import Mapeo.Horarios;
import Vista.Util.Boton;
import javax.swing.*;
import javax.swing.plaf.basic.BasicScrollBarUI;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Arrays;
import java.util.Objects;
import static Controlador.Controlador.listaHorarios;
import static Controlador.ControladorLogin.profesorLogeado;

/**
 * Clase que representa la gestión del horario del profesor.
 * Permite visualizar y descargar el horario del profesor logueado.
 */
public class GestionHorarioProfesor extends JPanel {
    private JTable tablaHorarios;
    private JButton btnDescargar;
    private DefaultTableModel modelo;
    private JTableHeader header;
    private JPanel panelSuperior;
    private JLabel titulo;
    private JPanel panelBoton;
    private ImageIcon icono;

    /**
     * Constructor de la clase GestionHorarioProfesor.
     * Inicializa la interfaz gráfica y carga los horarios del profesor.
     */
    public GestionHorarioProfesor() {
        setLayout(new BorderLayout());
        initGUI();
        initEventos();
        cargarHorariosProfesor();
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

        titulo = new JLabel("Colegio Salesiano San Francisco de Sales - Horario", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 24));
        titulo.setBorder(BorderFactory.createEmptyBorder(25, 10, 30, 10));
        panelSuperior.add(titulo, BorderLayout.NORTH);

        panelBoton = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelBoton.setOpaque(false);

        icono = new ImageIcon(Objects.requireNonNull(getClass().getResource("/icons/anadir.png")));
        icono.setImage(icono.getImage().getScaledInstance(24, 24, Image.SCALE_SMOOTH));

        btnDescargar = new Boton("Descargar Horario", Boton.tipoBoton.PRIMARY);
        btnDescargar.setIcon(icono);
        btnDescargar.setPreferredSize(new Dimension(180, 30));
        btnDescargar.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));

        panelBoton.add(btnDescargar);
        panelSuperior.add(panelBoton, BorderLayout.SOUTH);

        add(panelSuperior, BorderLayout.NORTH);
    }

    /**
     * Método para inicializar la tabla de los horarios del profesor.
     */
    private void initTabla() {
        String[] columnas = {"Asignatura", "Día de la semana", "Hora Inicio", "Hora Fin", "Profesor","Objeto"};
               modelo = new DefaultTableModel(null, columnas) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        tablaHorarios = new JTable(modelo) {
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

        TableColumn columnaOculta = tablaHorarios.getColumnModel().getColumn(tablaHorarios.getColumnCount()-1);
        columnaOculta.setMinWidth(0);
        columnaOculta.setMaxWidth(0);
        columnaOculta.setPreferredWidth(0);
        columnaOculta.setResizable(false);

        tablaHorarios.setRowSorter(new TableRowSorter<>(modelo));
        tablaHorarios.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value,
                                                           boolean isSelected, boolean hasFocus, int row, int column) {
                super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));
                return this;
            }
        });

        tablaHorarios.setShowGrid(false);
        tablaHorarios.setIntercellSpacing(new Dimension(0, 0));
        tablaHorarios.setRowHeight(30);
        tablaHorarios.setSelectionBackground(new Color(200, 220, 240));
        tablaHorarios.setSelectionForeground(Color.BLACK);
        tablaHorarios.setFont(new Font("Arial", Font.PLAIN, 14));

        header = tablaHorarios.getTableHeader();
        header.setFont(new Font("Arial", Font.BOLD, 14));
        header.setBackground(new Color(255, 204, 153));
        header.setForeground(new Color(70, 70, 70));
        header.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(210, 180, 170)),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));

        JScrollPane scroll = new JScrollPane(tablaHorarios);
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
        btnDescargar.addActionListener(e -> GeneradorHorario.exportarHorarioProfesorAXML(profesorLogeado.getHorarios()));


        header.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int column = header.columnAtPoint(e.getPoint());
                TableRowSorter<?> sorter = (TableRowSorter<?>) tablaHorarios.getRowSorter();
                if (column >= 0 && sorter != null) {
                    SortOrder currentOrder = sorter.getSortKeys().isEmpty() ? null : sorter.getSortKeys().get(0).getSortOrder();
                    SortOrder newOrder = currentOrder == SortOrder.DESCENDING ? SortOrder.ASCENDING : SortOrder.DESCENDING;
                    sorter.setSortKeys(Arrays.asList(new RowSorter.SortKey(column, newOrder)));
                }
            }
        });

        tablaHorarios.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                int row = tablaHorarios.rowAtPoint(e.getPoint());
                if (row >= 0) {
                    tablaHorarios.setSelectionBackground(new Color(245, 156, 107, 204));
                }
            }
        });
    }

    /**
     * Método para cargar los horarios del profesor logueado en la tabla.
     */
    private void cargarHorariosProfesor() {
        modelo.setRowCount(0);

        for (Horarios horario : listaHorarios) {
            if (horario.getAsignatura().getProfesor().equals(profesorLogeado)) {
                Object[] fila = {
                        horario.getAsignatura().getNombre(),
                        horario.getDiaSemana() ,
                        horario.getHoraInicio().toString(),
                        horario.getHoraFin().toString(),
                        horario.getProfesor().getNombre() + " " + horario.getProfesor().getApellido(),
                        horario
                };
                modelo.addRow(fila);
            }
        }
    }
}