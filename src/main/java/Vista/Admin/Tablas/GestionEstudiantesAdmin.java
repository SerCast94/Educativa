package Vista.Admin.Tablas;

import Controlador.Controlador;
import Mapeo.Estudiantes;
import Vista.Admin.Anadir.FormularioEstudiantesAdmin;
import Vista.Admin.Modificar.ActualizarEstudiantesAdmin;
import Vista.Admin.VistaPrincipalAdmin;
import Vista.Util.Boton;
import Vista.Util.CustomDialog;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Arrays;
import java.util.Objects;

import static Controlador.Controlador.listaEstudiantes;

public class GestionEstudiantesAdmin extends JPanel {
    private JTable tablaEstudiantes;
    private JButton btnAgregar;
    private DefaultTableModel modelo;
    private JPopupMenu popupMenu;
    private JTableHeader header;

    public GestionEstudiantesAdmin() {
        setLayout(new BorderLayout());
        initGUI();
        initEventos();
        cargarEstudiantesAdmin();
    }

    private void initGUI() {
        initPanelSuperior();
        initTabla();
        initPopupMenu();
    }

    private void initEventos() {
        btnAgregar.addActionListener(e -> new FormularioEstudiantesAdmin());

        header.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int column = header.columnAtPoint(e.getPoint());
                TableRowSorter<?> sorter = (TableRowSorter<?>) tablaEstudiantes.getRowSorter();
                if (column >= 0 && sorter != null) {
                    SortOrder currentOrder = sorter.getSortKeys().isEmpty() ? null : sorter.getSortKeys().get(0).getSortOrder();
                    SortOrder newOrder = currentOrder == SortOrder.DESCENDING ? SortOrder.ASCENDING : SortOrder.DESCENDING;
                    sorter.setSortKeys(Arrays.asList(new RowSorter.SortKey(column, newOrder)));
                }
            }
        });

        tablaEstudiantes.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                int row = tablaEstudiantes.rowAtPoint(e.getPoint());
                if (row >= 0) {
                    tablaEstudiantes.setSelectionBackground(new Color(245, 156, 107, 204));
                }
            }
        });

        tablaEstudiantes.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int row = tablaEstudiantes.rowAtPoint(e.getPoint());
                if (row >= 0) {
                    tablaEstudiantes.setRowSelectionInterval(row, row);
                    if (SwingUtilities.isRightMouseButton(e)) {
                        // Verificar si el clic está en la parte baja de la tabla
                        int visibleHeight = tablaEstudiantes.getVisibleRect().height;
                        int clickY = e.getY();
                        if (clickY > visibleHeight - 100) { // Ajustar si está cerca del borde inferior
                            popupMenu.show(tablaEstudiantes, e.getX(), e.getY() - 80);
                        } else {
                            popupMenu.show(tablaEstudiantes, e.getX(), e.getY());
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

        btnAgregar = new Boton("Agregar Estudiante", Boton.ButtonType.PRIMARY);
        btnAgregar.setIcon(icono);
        btnAgregar.setPreferredSize(new Dimension(180, 30));
        btnAgregar.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));

        panelBoton.add(btnAgregar);
        panelSuperior.add(panelBoton, BorderLayout.SOUTH);

        add(panelSuperior, BorderLayout.NORTH);
    }

    private void initTabla() {
        String[] columnas = {"Nombre", "Apellido", "DNI", "Fecha de nacimiento", "Dirección", "Teléfono", "Email", "Fecha matrícula", "Tutor legal", "Usuario", "Estado","Objeto"};
        modelo = new DefaultTableModel(null, columnas);

        tablaEstudiantes = new JTable(modelo) {
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


        TableColumn columnaOculta = tablaEstudiantes.getColumnModel().getColumn(tablaEstudiantes.getColumnCount()-1);
        columnaOculta.setMinWidth(0);
        columnaOculta.setMaxWidth(0);
        columnaOculta.setPreferredWidth(0);
        columnaOculta.setResizable(false);

        tablaEstudiantes.setRowSorter(new TableRowSorter<>(modelo));

        tablaEstudiantes.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value,
                                                           boolean isSelected, boolean hasFocus, int row, int column) {
                if (value instanceof Estudiantes && column == 0) {
                    value = ((Estudiantes) value).getNombre();
                }
                return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            }
        });

        tablaEstudiantes.setShowGrid(false);
        tablaEstudiantes.setIntercellSpacing(new Dimension(0, 0));
        tablaEstudiantes.setRowHeight(30);
        tablaEstudiantes.setSelectionBackground(new Color(200, 220, 240));
        tablaEstudiantes.setSelectionForeground(Color.BLACK);
        tablaEstudiantes.setFont(new Font("Arial", Font.PLAIN, 14));

        header = tablaEstudiantes.getTableHeader();
        header.setFont(new Font("Arial", Font.BOLD, 14));
        header.setBackground(new Color(255, 204, 153));
        header.setForeground(new Color(70, 70, 70));
        header.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(210, 180, 170)),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));

        JScrollPane scroll = new JScrollPane(tablaEstudiantes);
        scroll.getViewport().setBackground(Color.WHITE);
        scroll.setOpaque(false);

        // Personalización de la barra de desplazamiento
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
        modificarItembtn.addActionListener(e -> modificarEstudiante());

        Boton eliminarItembtn = new Boton("Eliminar", Boton.ButtonType.DELETE);
        configurarBotonPopup(eliminarItembtn);
        eliminarItembtn.addActionListener(e -> eliminarEstudiante());

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

    private void modificarEstudiante() {
        int fila = tablaEstudiantes.getSelectedRow();
        if (fila != -1) {
            int filaModelo = tablaEstudiantes.convertRowIndexToModel(fila);
            Estudiantes estudianteSeleccionado = (Estudiantes) modelo.getValueAt(filaModelo, tablaEstudiantes.getColumnCount()-1);
            new ActualizarEstudiantesAdmin(estudianteSeleccionado);
        }
    }

    private void eliminarEstudiante() {
        int fila = tablaEstudiantes.getSelectedRow();
        if (fila != -1) {
            new CustomDialog(null, "Eliminar Estudiante", "¿Está seguro de que desea eliminar este estudiante?", "OK_CANCEL").setVisible(true);

            if (CustomDialog.isAceptar()) {
                int filaModelo = tablaEstudiantes.convertRowIndexToModel(fila);
                Estudiantes estudianteSeleccionado = (Estudiantes) modelo.getValueAt(filaModelo, tablaEstudiantes.getColumnCount() - 1);
                Controlador.eliminarControladorEstudiante(estudianteSeleccionado);
                Controlador.actualizarListaEstudiantes();

                VistaPrincipalAdmin vistaPrincipalAdmin = (VistaPrincipalAdmin) VistaPrincipalAdmin.getVistaPrincipal();
                vistaPrincipalAdmin.mostrarVistaEstudiantes();
                new CustomDialog(null, "Estudiante Eliminado", "Estudiante eliminado correctamente.", "ONLY_OK").setVisible(true);

            } else {
                new CustomDialog(null, "Acción Cancelada", "Acción cancelada por el usuario.", "ONLY_OK").setVisible(true);
            }
        }
    }

    private void cargarEstudiantesAdmin() {
        modelo.setRowCount(0);
        for (Estudiantes estudiante : listaEstudiantes) {
            Object[] fila = {
                    estudiante.getNombre(),
                    estudiante.getApellido(),
                    estudiante.getDni(),
                    estudiante.getFechaNacimiento().toString(),
                    estudiante.getDireccion(),
                    estudiante.getTelefono(),
                    estudiante.getEmail(),
                    estudiante.getFechaMatricula().toString(),
                    estudiante.getTutor().getNombre() + " " + estudiante.getTutor().getApellido(),
                    estudiante.getUsuario(),
                    estudiante.getEstado(),
                    estudiante
            };
            modelo.addRow(fila);
        }
    }
}
