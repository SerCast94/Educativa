package Vista.Estudiante.Tablas;

import Controlador.Controlador;
import Mapeo.Asistencia;
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
import java.util.Objects;

import static Controlador.Controlador.listaAsistencia;
import static Controlador.ControladorLogin.estudianteLogeado;
import static Vista.Util.EstiloComponentes.checkPersonalizadoGris;

public class GestionAsistenciaEstudiante extends JPanel {
    private JTable tablaAsistencias;
    private DefaultTableModel modelo;
    private JPopupMenu popupMenu;
    private JTableHeader header;

    public GestionAsistenciaEstudiante() {
        setLayout(new BorderLayout());
        initGUI();
        initEventos();
        cargarAsistenciasEstudiante();
    }

    private void initGUI() {
        initPanelSuperior();
        initTabla();
    }

    private void initEventos() {
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
    }

    private void initPanelSuperior() {
        JPanel panelSuperior = new JPanel(new BorderLayout());
        panelSuperior.setBorder(BorderFactory.createEmptyBorder(20, 10, 10, 10));
        panelSuperior.setBackground(new Color(251, 234, 230));

        JLabel titulo = new JLabel("Colegio Salesiano San Francisco de Sales - Asistencias", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 24));
        titulo.setBorder(BorderFactory.createEmptyBorder(25, 10, 30, 10));
        panelSuperior.add(titulo, BorderLayout.NORTH);

        JPanel panelBoton = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelBoton.setOpaque(false);

        panelSuperior.add(panelBoton, BorderLayout.SOUTH);

        add(panelSuperior, BorderLayout.NORTH);
    }

    private void initTabla() {
        String[] columnas = {"Fecha", "Justificado", "Motivo de ausencia", "Objeto"};
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

        //  columna "Justificado"
        tablaAsistencias.getColumnModel().getColumn(1).setCellRenderer(new DefaultTableCellRenderer() {
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
        panelConMargen.setBorder(BorderFactory.createEmptyBorder(30, 0, 10, 10));
        panelConMargen.add(scroll, BorderLayout.CENTER);

        add(panelConMargen, BorderLayout.CENTER);
    }

    private void cargarAsistenciasEstudiante() {
        modelo.setRowCount(0);
        for (Asistencia asistencia : listaAsistencia) {
            if(asistencia.getEstudiante().equals(estudianteLogeado)){
                Object[] fila = {
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
