package Vista.Admin;

import Mapeo.Estudiantes;
import Vista.Boton;
import javax.swing.*;
import javax.swing.border.Border;
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

        JPanel panelSuperior = new JPanel(new BorderLayout());
        panelSuperior.setBorder(BorderFactory.createEmptyBorder(20, 10, 10, 10));
        JLabel titulo = new JLabel("Colegio Salesiano San Francisco de Sales - EDUCATIVA", SwingConstants.CENTER);
        titulo.setBorder(BorderFactory.createEmptyBorder(25, 10, 30, 10));
        titulo.setFont(new Font("Arial", Font.BOLD, 24));
        panelSuperior.add(titulo, BorderLayout.NORTH);
        panelSuperior.setBackground(new Color(251, 234, 230));

        JPanel panelBoton = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelBoton.setOpaque(false);

        ImageIcon icono = new ImageIcon(Objects.requireNonNull(getClass().getResource("/icons/anadir.png")));
        icono.setImage(icono.getImage().getScaledInstance(24, 24, Image.SCALE_SMOOTH));

        btnAgregar = new Boton("Agregar Estudiante", Boton.ButtonType.PRIMARY);
        btnAgregar.setIcon(icono);
        btnAgregar.setPreferredSize(new Dimension(180, 30));
        btnAgregar.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        btnAgregar.addActionListener(e -> {
            new FormularioEstudiantesAdmin(null);
        });

        panelBoton.add(btnAgregar);
        panelSuperior.add(panelBoton, BorderLayout.SOUTH);

        add(panelSuperior, BorderLayout.NORTH);

        String[] columnas = {"Nombre", "Apellido", "DNI", "Fecha de nacimiento", "Dirección", "Teléfono", "Email", "Fecha matrícula", "Tutor legal", "Usuario", "Estado"};
        modelo = new DefaultTableModel(null, columnas);

        tablaEstudiantes = new JTable(modelo) {
            @Override
            public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
                Component c = super.prepareRenderer(renderer, row, column);

                if (!isRowSelected(row)) {
                    c.setBackground(row % 2 == 0 ? new Color(255, 255, 255) : new Color(245, 245, 245));
                }

                if (isRowSelected(row)) {
                    c.setBackground(new Color(200, 220, 240));
                    c.setForeground(Color.BLACK);
                } else {
                    c.setForeground(new Color(70, 70, 70));
                }

                return c;
            }


        };
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(modelo);
        tablaEstudiantes.setRowSorter(sorter);

        header = tablaEstudiantes.getTableHeader();
        header.setDefaultRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value,
                                                           boolean isSelected, boolean hasFocus, int row, int column) {
                super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

                setBackground((new Color(241, 198, 177)));
                setFont(new Font("Arial", Font.BOLD, 14));
                Border border = BorderFactory.createCompoundBorder(
                        BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(210, 180, 170)),
                        BorderFactory.createEmptyBorder(5, 10, 5, 10)
                );

                setBorder(border);

                setIcon(null);

                if (sorter != null && !sorter.getSortKeys().isEmpty()) {
                    SortOrder sortOrder = sorter.getSortKeys().get(0).getSortOrder();
                    int modelColumn = table.convertColumnIndexToModel(column);

                    if (sorter.getSortKeys().get(0).getColumn() == modelColumn) {
                        switch (sortOrder) {
                            case ASCENDING:
                                setIcon(new ImageIcon(Objects.requireNonNull(getClass().getResource("/icons/up.png"))));
                                break;
                            case DESCENDING:
                                setIcon(new ImageIcon(Objects.requireNonNull(getClass().getResource("/icons/down.png"))));
                                break;
                        }
                    }
                }

                setHorizontalTextPosition(SwingConstants.LEFT);
                setIconTextGap(10);

                return this;
            }
        });


        header.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int column = header.columnAtPoint(e.getPoint());
                if (column >= 0) {
                    SortOrder sortOrder = sorter.getSortKeys().isEmpty() ||
                            sorter.getSortKeys().get(0).getSortOrder() == SortOrder.DESCENDING ?
                            SortOrder.ASCENDING : SortOrder.DESCENDING;
                    sorter.setSortKeys(Arrays.asList(new RowSorter.SortKey(column, sortOrder)));
                }
            }
        });

        tablaEstudiantes.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value,
                                                           boolean isSelected, boolean hasFocus, int row, int column) {
                super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

                setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));
                return this;
            }
        });

        tablaEstudiantes.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                int row = tablaEstudiantes.rowAtPoint(e.getPoint());
                if (row >= 0) {
                    tablaEstudiantes.setSelectionBackground(new Color(180, 200, 220));
                }
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
        header.setBackground(new Color(251, 234, 230));
        header.setForeground(new Color(70, 70, 70));
        header.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(210, 180, 170)),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));


        JScrollPane scroll = new JScrollPane(tablaEstudiantes);
        add(scroll, BorderLayout.CENTER);
        scroll.setBorder(BorderFactory.createEmptyBorder());
        scroll.getViewport().setBackground(Color.WHITE);
        scroll.setOpaque(false);


        cargarEstudiantesAdmin();

        popupMenu = new JPopupMenu() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                g2.setColor(new Color(240, 240, 240, 220));
                g2.fillRoundRect(0, 0, getWidth()-50, getHeight(), 12, 12);

                g2.setColor(new Color(200, 200, 200, 150));
                g2.drawRoundRect(0, 0, getWidth()-50, getHeight()-1, 12, 12);

                g2.dispose();
            }
        };

        popupMenu.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));
        popupMenu.setOpaque(false);

        Boton modificarItembtn = new Boton("Modificar", Boton.ButtonType.PRIMARY);
        modificarItembtn.setPreferredSize(new Dimension(150, 30));
        modificarItembtn.setContentAreaFilled(false);
        modificarItembtn.setBorderPainted(false);
        modificarItembtn.setFocusPainted(false);
        modificarItembtn.setOpaque(false);

        Boton eliminarItembtn = new Boton(" Eliminar  ", Boton.ButtonType.DELETE);
        eliminarItembtn.setPreferredSize(new Dimension(150, 30));
        eliminarItembtn.setContentAreaFilled(false);
        eliminarItembtn.setBorderPainted(false);
        eliminarItembtn.setFocusPainted(false);
        eliminarItembtn.setOpaque(false);

        modificarItembtn.setActionCommand("Modificar");
        eliminarItembtn.setActionCommand("Eliminar");
        modificarItembtn.addActionListener(e -> modificarEstudiante());
        eliminarItembtn.addActionListener(e -> eliminarEstudiante());

        popupMenu.add(modificarItembtn);
        popupMenu.add(Box.createVerticalStrut(5));
        popupMenu.add(eliminarItembtn);

        UIManager.put("PopupMenu.border", BorderFactory.createEmptyBorder());
        UIManager.put("PopupMenu.background", new Color(0, 0, 0, 0));

        tablaEstudiantes.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int row = tablaEstudiantes.rowAtPoint(e.getPoint());
                tablaEstudiantes.setRowSelectionInterval(row, row);
                if (SwingUtilities.isRightMouseButton(e)) {
                    popupMenu.show(tablaEstudiantes, e.getX(), e.getY());
                }
            }
        });
    }

    private void modificarEstudiante() {
        int fila = tablaEstudiantes.getSelectedRow();
        if (fila != -1) {
            new FormularioEstudiantesAdmin(modelo.getDataVector().elementAt(fila));
        }
    }

    private void eliminarEstudiante() {
        int fila = tablaEstudiantes.getSelectedRow();
        if (fila != -1) {
            int confirmar = JOptionPane.showConfirmDialog(null, "¿Eliminar estudiante?", "Confirmar", JOptionPane.YES_NO_OPTION);
            if (confirmar == JOptionPane.YES_OPTION) {
                modelo.removeRow(fila);
            }
        }
    }

    private void cargarEstudiantesAdmin(){

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
                        estudiante.getEstado()
                };
                modelo.addRow(fila);
            }
    }
}