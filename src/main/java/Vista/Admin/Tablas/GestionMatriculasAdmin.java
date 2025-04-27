package Vista.Admin.Tablas;

import Mapeo.Matriculas;
import Vista.Admin.Anadir.FormularioMatriculasAdmin;
import Vista.Admin.Modificar.ActualizarMatriculasAdmin;
import Vista.Util.Boton;

import javax.swing.*;
import javax.swing.plaf.basic.BasicScrollBarUI;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Arrays;
import java.util.Objects;

import static Controlador.Controlador.listaMatriculas;

public class GestionMatriculasAdmin extends JPanel {
    private JTable tablaMatriculas;
    private JButton btnAgregar;
    private DefaultTableModel modelo;
    private JPopupMenu popupMenu;
    private JTableHeader header;

    public GestionMatriculasAdmin() {
        setLayout(new BorderLayout());
        initGUI();
        initEventos();
        cargarMatriculasAdmin();
    }

    private void initGUI() {
        initPanelSuperior();
        initTabla();
        initPopupMenu();
    }

    private void initEventos() {
       btnAgregar.addActionListener(e -> new FormularioMatriculasAdmin());

        header.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int column = header.columnAtPoint(e.getPoint());
                TableRowSorter<?> sorter = (TableRowSorter<?>) tablaMatriculas.getRowSorter();
                if (column >= 0 && sorter != null) {
                    SortOrder currentOrder = sorter.getSortKeys().isEmpty() ? null : sorter.getSortKeys().get(0).getSortOrder();
                    SortOrder newOrder = currentOrder == SortOrder.DESCENDING ? SortOrder.ASCENDING : SortOrder.DESCENDING;
                    sorter.setSortKeys(Arrays.asList(new RowSorter.SortKey(column, newOrder)));
                }
            }
        });

        tablaMatriculas.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                int row = tablaMatriculas.rowAtPoint(e.getPoint());
                if (row >= 0) {
                    tablaMatriculas.setSelectionBackground(new Color(245, 156, 107, 204));
                }
            }
        });

        tablaMatriculas.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int row = tablaMatriculas.rowAtPoint(e.getPoint());
                if (row >= 0) {
                    tablaMatriculas.setRowSelectionInterval(row, row);
                    if (SwingUtilities.isRightMouseButton(e)) {
                        int visibleHeight = tablaMatriculas.getVisibleRect().height;
                        int clickY = e.getY();
                        if (clickY > visibleHeight - 100) {
                            popupMenu.show(tablaMatriculas, e.getX(), e.getY() - 80);
                        } else {
                            popupMenu.show(tablaMatriculas, e.getX(), e.getY());
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

        JLabel titulo = new JLabel("Colegio Salesiano San Francisco de Sales - EDUCATIVA", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 24));
        titulo.setBorder(BorderFactory.createEmptyBorder(25, 10, 30, 10));
        panelSuperior.add(titulo, BorderLayout.NORTH);

        JPanel panelBoton = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelBoton.setOpaque(false);

        ImageIcon icono = new ImageIcon(Objects.requireNonNull(getClass().getResource("/icons/anadir.png")));
        icono.setImage(icono.getImage().getScaledInstance(24, 24, Image.SCALE_SMOOTH));

        btnAgregar = new Boton("Agregar Matrícula", Boton.ButtonType.PRIMARY);
        btnAgregar.setIcon(icono);
        btnAgregar.setPreferredSize(new Dimension(180, 30));
        btnAgregar.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));

        panelBoton.add(btnAgregar);
        panelSuperior.add(panelBoton, BorderLayout.SOUTH);

        add(panelSuperior, BorderLayout.NORTH);
    }

    private void initTabla() {
        String[] columnas = {"Estudiante", "Curso", "Fecha de Matrícula", "Estado", "Objeto"};
        modelo = new DefaultTableModel(null, columnas);

        tablaMatriculas = new JTable(modelo) {
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

        TableColumn columnaOculta = tablaMatriculas.getColumnModel().getColumn(tablaMatriculas.getColumnCount()-1);
        columnaOculta.setMinWidth(0);
        columnaOculta.setMaxWidth(0);
        columnaOculta.setPreferredWidth(0);
        columnaOculta.setResizable(false);

        tablaMatriculas.setRowSorter(new TableRowSorter<>(modelo));
        tablaMatriculas.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value,
                                                           boolean isSelected, boolean hasFocus, int row, int column) {
                super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));
                return this;
            }
        });

        tablaMatriculas.setShowGrid(false);
        tablaMatriculas.setIntercellSpacing(new Dimension(0, 0));
        tablaMatriculas.setRowHeight(30);
        tablaMatriculas.setSelectionBackground(new Color(200, 220, 240));
        tablaMatriculas.setSelectionForeground(Color.BLACK);
        tablaMatriculas.setFont(new Font("Arial", Font.PLAIN, 14));

        header = tablaMatriculas.getTableHeader();
        header.setFont(new Font("Arial", Font.BOLD, 14));
        header.setBackground(new Color(251, 234, 230));
        header.setForeground(new Color(70, 70, 70));
        header.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(210, 180, 170)),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));

        JScrollPane scroll = new JScrollPane(tablaMatriculas);
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
        modificarItembtn.addActionListener(e -> modificarMatricula());

        Boton eliminarItembtn = new Boton("Eliminar", Boton.ButtonType.DELETE);
        configurarBotonPopup(eliminarItembtn);
        eliminarItembtn.addActionListener(e -> eliminarMatricula());

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

    private void modificarMatricula() {
        int fila = tablaMatriculas.getSelectedRow();
        if (fila != -1) {
            int filaModelo = tablaMatriculas.convertRowIndexToModel(fila);
            Matriculas matriculaSeleccionada = (Matriculas) modelo.getValueAt(filaModelo, tablaMatriculas.getColumnCount() - 1);
            new ActualizarMatriculasAdmin(matriculaSeleccionada);
        }
    }

    private void eliminarMatricula() {
        int fila = tablaMatriculas.getSelectedRow();
        if (fila != -1) {
            int confirmar = JOptionPane.showConfirmDialog(null, "¿Eliminar matrícula?", "Confirmar", JOptionPane.YES_NO_OPTION);
            if (confirmar == JOptionPane.YES_OPTION) {
                modelo.removeRow(fila);
            }
        }
    }

    private void cargarMatriculasAdmin() {
        modelo.setRowCount(0);
        for (Matriculas matricula : listaMatriculas) {
            Object[] fila = {
                    matricula.getEstudiante().getNombre() + " " + matricula.getEstudiante().getApellido(),
                    matricula.getCurso().getNombre(), // Asumiendo que el curso tiene un método getNombre()
                    matricula.getFechaMatricula().toString(),
                    matricula.getEstado().name(),
                    matricula
            };
            modelo.addRow(fila);
        }
    }
}
