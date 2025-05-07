package Vista.Admin.Tablas;

import Controlador.Controlador;
import Mapeo.Extraescolares;
import Vista.Util.Boton;
import Vista.Util.CustomDatePicker;
import Vista.Util.CustomDialog;
import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import java.time.LocalDate;
import java.util.*;
import static Controlador.Controlador.listaExtraescolares;

/**
 * Clase que representa la gestión de reservas de extraescolares.
 * Permite agregar, modificar y eliminar reservas de extraescolares.
 */
public class GestionExtraescolaresAdmin extends JPanel {
    private JPanel panelSuperior;
    private JLabel titulo;
    private JPanel panelBoton;
    private JTable tablaReservas;
    private DefaultTableModel modelo;
    private JTableHeader header;
    private CustomDatePicker datePicker;
    private JButton btnReservar;
    private final Map<String, Extraescolares> reservas = new HashMap<>();
    private final String[] pistas = {"Pista Fútbol", "Pista Baloncesto", "Aula Usos Múltiples", "Santuario"};
    private final String[] horas = {"16:00", "17:00", "18:00", "19:00", "20:00", "21:00"};
    private JPopupMenu popupMenu;

    /**
     * Constructor de la clase GestionExtraescolaresAdmin.
     * Inicializa la interfaz gráfica y carga las reservas de extraescolares.
     */
    public GestionExtraescolaresAdmin() {
        setLayout(new BorderLayout());
        initGUI();
        initEventos();
        cargarTablaConFecha(LocalDate.now());
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
     * Método para incializar el panel superior de la interfaz.
     */
    private void initPanelSuperior() {
        panelSuperior = new JPanel(new BorderLayout());
        panelSuperior.setBorder(BorderFactory.createEmptyBorder(20, 10, 10, 10));
        panelSuperior.setBackground(new Color(251, 234, 230));

        titulo = new JLabel("Gestión de Reservas de Instalaciones", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 24));
        titulo.setBorder(BorderFactory.createEmptyBorder(25, 10, 30, 10));
        panelSuperior.add(titulo, BorderLayout.NORTH);

        panelBoton = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelBoton.setOpaque(false);

        btnReservar = new Boton("Reservar Hora", Boton.tipoBoton.PRIMARY);
        btnReservar.setPreferredSize(new Dimension(160, 30));

        datePicker = new CustomDatePicker();
        datePicker.setPreferredSize(new Dimension(150, 30));

        panelBoton.add(new JLabel("Fecha:"));
        panelBoton.add(datePicker);
        panelBoton.add(Box.createHorizontalStrut(10));
        panelBoton.add(btnReservar);

        panelSuperior.add(panelBoton, BorderLayout.SOUTH);
        add(panelSuperior, BorderLayout.NORTH);
    }

    /**
     * Método para inicializar la tabla de las reservas de extraescolares.
     */
    private void initTabla() {
        modelo = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        modelo.addColumn("Hora");
        for (String pista : pistas) {
            modelo.addColumn(pista);
        }

        tablaReservas = new JTable(modelo) {
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

        tablaReservas.setRowHeight(30);
        tablaReservas.setShowGrid(false);
        tablaReservas.setSelectionBackground(new Color(200, 220, 240));
        tablaReservas.setIntercellSpacing(new Dimension(0, 0));
        tablaReservas.setFont(new Font("Arial", Font.PLAIN, 14));

        header = tablaReservas.getTableHeader();
        header.setFont(new Font("Arial", Font.BOLD, 14));
        header.setBackground(new Color(255, 204, 153));
        header.setForeground(new Color(70, 70, 70));
        header.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(210, 180, 170)));

        JScrollPane scroll = new JScrollPane(tablaReservas);
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
        panelConMargen.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 10));
        panelConMargen.add(scroll, BorderLayout.CENTER);

        add(panelConMargen, BorderLayout.CENTER);
    }

    /**
     * Método para inicializar los eventos de la interfaz.
     */
    private void initEventos() {
        btnReservar.addActionListener(e -> reservar());

        datePicker.addDateChangeListener(e -> {
            LocalDate fecha = datePicker.getDate();

            if (fecha != null) {
                cargarTablaConFecha(fecha);
            }
        });

        tablaReservas.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int fila = tablaReservas.rowAtPoint(e.getPoint());
                if (fila >= 0) {
                    tablaReservas.setRowSelectionInterval(fila, fila);
                    if (SwingUtilities.isRightMouseButton(e)) {
                        popupMenu.show(tablaReservas, e.getX(), e.getY());
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

        Boton eliminarItembtn = new Boton("Eliminar", Boton.tipoBoton.DELETE);
        configurarBotonPopup(eliminarItembtn);
        eliminarItembtn.addActionListener(e -> eliminarReserva());

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
     * Método para eliminar una reserva de extraescolares en la tabla.
     * Pide confirmación al usuario antes de eliminar.
     */
    private void eliminarReserva() {
        int fila = tablaReservas.getSelectedRow();
        int columna = tablaReservas.getSelectedColumn();

        if (fila != -1 && columna > 0) {
            String hora = (String) modelo.getValueAt(fila, 0);
            String pista = tablaReservas.getColumnName(columna);
            LocalDate fecha = datePicker.getDate();
            String fechaStr = fecha != null ? fecha.toString() : "";

            String clave = hora + "-" + pista + "-" + fechaStr;

            Extraescolares reservaSeleccionada = reservas.get(clave);

            if (reservaSeleccionada != null) {
                new CustomDialog(null, "Eliminar Reserva", "¿Está seguro de que desea eliminar esta reserva?", "OK_CANCEL").setVisible(true);

                if (CustomDialog.isAceptar()) {

                    Controlador.eliminarControladorExtraescolar(reservaSeleccionada);
                    Controlador.actualizarListaExtraescolares();
                    cargarTablaConFecha(fecha);

                    new CustomDialog(null, "Reserva Eliminada", "Reserva eliminada correctamente.", "ONLY_OK").setVisible(true);
                } else {
                    new CustomDialog(null, "Acción Cancelada", "Acción cancelada por el usuario.", "ONLY_OK").setVisible(true);
                }
            }
        }
    }

    /**
     * Método para cargar los datos de las reservas de extraescolares en la tabla.
     * Se obtienen los datos de las reservas de extraescolares en la tabla.
     * @param fecha
     */
    private void cargarTablaConFecha(LocalDate fecha) {
        modelo.setRowCount(0);
        String fechaStr = fecha.toString();

        reservas.clear();

        for (Extraescolares extraescolar : listaExtraescolares) {
            LocalDate fechaReserva = extraescolar.getFechaReserva().toLocalDate();

            // Comparo las fechas
            if (fechaReserva.equals(fecha)) {
                String horaFormateada = extraescolar.getHora().toString().substring(0, 5);
                String clave = horaFormateada + "-" + extraescolar.getPista() + "-" + extraescolar.getFechaReserva();
                reservas.put(clave, extraescolar);
            }
        }

        // Lleno la tabla con los datos
        for (String hora : horas) {
            Object[] fila = new Object[pistas.length + 1];
            fila[0] = hora;
            for (int i = 0; i < pistas.length; i++) {
                String clave = hora + "-" + pistas[i] + "-" + fechaStr;
                Extraescolares reserva = reservas.get(clave);
                fila[i + 1] = reserva != null ? "Reservado" : "Libre";
            }
            modelo.addRow(fila);
        }

        modelo.fireTableDataChanged();
        tablaReservas.repaint();
    }

    /**
     * Crea una reserva para el dia hora y pista seleccionada.
     */
    private void reservar() {
        int fila = tablaReservas.getSelectedRow();
        int columna = tablaReservas.getSelectedColumn();

        if (fila == -1 || columna <= 0) {
            new CustomDialog(null, "Selección Incorrecta", "Selecciona una hora y una pista válida.", "ONLY_OK").setVisible(true);
            return;
        }

        LocalDate fecha = datePicker.getDate();
        if (fecha == null) {
            new CustomDialog(null, "Fecha no seleccionada", "Por favor, selecciona una fecha.", "ONLY_OK").setVisible(true);
            return;
        }

        String fechaStr = fecha.toString();
        String hora = (String) modelo.getValueAt(fila, 0);
        String pista = tablaReservas.getColumnName(columna);
        String clave = hora + "-" + pista + "-" + fechaStr;


        if (reservas.containsKey(clave)) {
            new CustomDialog(null, "Reserva Existente", "Esta hora ya está reservada para esta pista.", "ONLY_OK").setVisible(true);
        } else {
            Extraescolares nuevaReserva = new Extraescolares();
            nuevaReserva.setFechaReserva(java.sql.Date.valueOf(fechaStr));
            nuevaReserva.setHora(java.sql.Time.valueOf(hora + ":00"));
            nuevaReserva.setPista(pista);

            Controlador.insertarControladorExtraescolar(nuevaReserva);
            Controlador.actualizarListaExtraescolares();
            cargarTablaConFecha(fecha);

            new CustomDialog(null, "Reserva Confirmada", "Reserva confirmada: " + pista + " a las " + hora, "ONLY_OK").setVisible(true);
        }
    }
}