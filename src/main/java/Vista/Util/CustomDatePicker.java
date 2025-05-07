package Vista.Util;

import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.components.DatePickerSettings;
import com.github.lgooddatepicker.components.CalendarPanel;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import java.awt.*;

/**
 * La clase CustomDatePicker extiende la clase DatePicker de la biblioteca LGoodDatePicker.
 * Esta clase se utiliza para crear un selector de fecha personalizado con un diseño específico.
 */
public class CustomDatePicker extends DatePicker {

    /**
     * Constructor de la clase CustomDatePicker.
     * Inicializa el selector de fecha con configuraciones personalizadas.
     */
    public CustomDatePicker() {
        super(establecerConfiguracion());
        stylizeTextField();
        stylizeButton();
        overridePopupStyle();

    }

    /**
     * Método estático para construir las configuraciones del selector de fecha.
     * @return Configuraciones personalizadas para el selector de fecha.
     */
    private static DatePickerSettings establecerConfiguracion() {
        DatePickerSettings configuracion = new DatePickerSettings();
        configuracion.setAllowEmptyDates(false);
        configuracion.setFormatForDatesCommonEra("MM/dd/yyyy");
        configuracion.setFontValidDate(new Font("Arial", Font.PLAIN, 14));
        configuracion.setFontInvalidDate(new Font("Arial", Font.PLAIN, 14));

        Color fondoBase = new Color(251, 234, 230);
        Color bordeNaranja = new Color(245, 156, 107);
        Color textoPrincipal = Color.BLACK;
        Color fondoSeleccion = new Color(245, 156, 107);
        Color fechaSeleccionada = new Color(245, 156, 107);

        configuracion.setColor(DatePickerSettings.DateArea.BackgroundClearLabel, fondoBase);
        configuracion.setColor(DatePickerSettings.DateArea.BackgroundMonthAndYearMenuLabels, fondoBase);
        configuracion.setColor(DatePickerSettings.DateArea.BackgroundMonthAndYearNavigationButtons, fondoBase);
        configuracion.setColor(DatePickerSettings.DateArea.BackgroundCalendarPanelLabelsOnHover, fondoSeleccion);
        configuracion.setColor(DatePickerSettings.DateArea.BackgroundOverallCalendarPanel, fondoBase);
        configuracion.setColor(DatePickerSettings.DateArea.BackgroundTodayLabel, fondoBase);
        configuracion.setColor(DatePickerSettings.DateArea.BackgroundTopLeftLabelAboveWeekNumbers, fondoSeleccion);

        configuracion.setColor(DatePickerSettings.DateArea.CalendarBackgroundNormalDates, Color.WHITE);
        configuracion.setColor(DatePickerSettings.DateArea.CalendarBackgroundSelectedDate, fechaSeleccionada);
        configuracion.setColor(DatePickerSettings.DateArea.CalendarBackgroundVetoedDates, Color.LIGHT_GRAY);
        configuracion.setColor(DatePickerSettings.DateArea.CalendarBorderSelectedDate, bordeNaranja);
        configuracion.setColor(DatePickerSettings.DateArea.CalendarDefaultBackgroundHighlightedDates, fondoSeleccion);
        configuracion.setColor(DatePickerSettings.DateArea.CalendarDefaultTextHighlightedDates, textoPrincipal);
        configuracion.setColorBackgroundWeekNumberLabels(bordeNaranja,false );
        configuracion.setColorBackgroundWeekdayLabels(bordeNaranja,false);
        configuracion.setColor(DatePickerSettings.DateArea.CalendarTextNormalDates, textoPrincipal);
        configuracion.setColor(DatePickerSettings.DateArea.CalendarTextWeekdays, textoPrincipal);
        configuracion.setColor(DatePickerSettings.DateArea.CalendarTextWeekNumbers, textoPrincipal);

        configuracion.setColor(DatePickerSettings.DateArea.TextClearLabel, textoPrincipal);
        configuracion.setColor(DatePickerSettings.DateArea.TextMonthAndYearMenuLabels, textoPrincipal);
        configuracion.setColor(DatePickerSettings.DateArea.TextMonthAndYearNavigationButtons, textoPrincipal);
        configuracion.setColor(DatePickerSettings.DateArea.TextTodayLabel, textoPrincipal);
        configuracion.setColor(DatePickerSettings.DateArea.TextCalendarPanelLabelsOnHover, textoPrincipal);

        configuracion.setColor(DatePickerSettings.DateArea.TextFieldBackgroundDisallowedEmptyDate, fondoBase);
        configuracion.setColor(DatePickerSettings.DateArea.TextFieldBackgroundInvalidDate, Color.WHITE);
        configuracion.setColor(DatePickerSettings.DateArea.TextFieldBackgroundValidDate, Color.WHITE);
        configuracion.setColor(DatePickerSettings.DateArea.TextFieldBackgroundVetoedDate, Color.WHITE);
        configuracion.setColor(DatePickerSettings.DateArea.TextFieldBackgroundDisabled, fondoBase);

        configuracion.setColor(DatePickerSettings.DateArea.DatePickerTextInvalidDate, Color.RED);
        configuracion.setColor(DatePickerSettings.DateArea.DatePickerTextValidDate, textoPrincipal);
        configuracion.setColor(DatePickerSettings.DateArea.DatePickerTextVetoedDate, textoPrincipal);
        configuracion.setColor(DatePickerSettings.DateArea.DatePickerTextDisabled, fondoBase);

        return configuracion;
    }

    /**
     * Método para estilizar el campo de texto del selector de fecha.
     */
    private void stylizeTextField() {
        JTextField campoTexto = this.getComponentDateTextField();
        campoTexto.setFont(new Font("Arial", Font.PLAIN, 14));
        campoTexto.setBackground(Color.WHITE);
        campoTexto.setForeground(new Color(50, 50, 50));
        campoTexto.setBorder(BorderFactory.createLineBorder(new Color(245, 156, 107), 1));
    }

    /**
     * Método para estilizar el botón del selector de fecha.
     */
    private void stylizeButton() {
        JButton boton = this.getComponentToggleCalendarButton();
        boton.setBackground(new Color(245, 156, 107));
        boton.setForeground(Color.WHITE);
        boton.setFocusPainted(false);
        boton.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        boton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }

    /**
     * Método para estilizar el popup del selector de fecha.
     */
    private void overridePopupStyle() {
        this.getComponentToggleCalendarButton().addActionListener(e -> {
            SwingUtilities.invokeLater(this::applyPopupStyle);
        });
    }

    /**
     * Método para aplicar el estilo al popup del selector de fecha.
     */
    private void applyPopupStyle() {
        SwingUtilities.invokeLater(() -> {
            for (Window ventana : Window.getWindows()) {
                if (ventana instanceof JDialog dialog && dialog.isVisible()) {
                    Component[] componentes = dialog.getComponents();
                    for (Component componente : componentes) {
                        if (componente instanceof JPanel panel) {
                            CalendarPanel calendarPanel = encontrarPanelCalendario(panel);
                            if (calendarPanel != null) {
                                estiloPanelCalendario(calendarPanel);
                                estiloTablaCalendario(calendarPanel);
                            }
                        }
                    }
                }
            }
        });
    }

    /**
     * Método para encontrar el panel de calendario dentro de un contenedor.
     * @param contenedor El contenedor en el que buscar.
     * @return El panel de calendario encontrado, o null si no se encuentra.
     */
    private CalendarPanel encontrarPanelCalendario(Container contenedor) {
        for (Component componente : contenedor.getComponents()) {
            if (componente instanceof CalendarPanel panel) {
                return panel;
            } else if (componente instanceof Container anidado) {
                CalendarPanel found = encontrarPanelCalendario(anidado);
                if (found != null) return found;
            }
        }
        return null;
    }

    /**
     * Método para estilizar el panel de calendario.
     * @param panelCalendario El panel de calendario a estilizar.
     */
    private void estiloPanelCalendario(CalendarPanel panelCalendario) {
        panelCalendario.setBackground(new Color(251, 234, 230));
        panelCalendario.setBorder(BorderFactory.createLineBorder(new Color(245, 156, 107), 1));
        panelCalendario.setFont(new Font("Arial", Font.PLAIN, 13));

        for (Component componente : panelCalendario.getComponents()) {
            if (componente instanceof JButton boton) {
                boton.setBackground(new Color(245, 156, 107));
                boton.setForeground(Color.WHITE);
                boton.setFont(new Font("Arial", Font.BOLD, 12));
                boton.setFocusPainted(false);
                boton.setBorder(BorderFactory.createEmptyBorder(4, 6, 4, 6));
            }
        }
        panelCalendario.repaint();
    }

    /**
     * Método para estilizar la tabla del calendario.
     * @param panelCalendario El panel de calendario que contiene la tabla.
     */
    private void estiloTablaCalendario(CalendarPanel panelCalendario) {
        JTable tabla = encontrarJTable(panelCalendario);
        if (tabla != null) {
            tabla.setBackground(new Color(251, 234, 230));
            tabla.setForeground(new Color(50, 50, 50));
            tabla.setFont(new Font("Arial", Font.PLAIN, 13));
            tabla.setGridColor(new Color(245, 156, 107));
            tabla.setRowHeight(28);
            tabla.setShowGrid(true);

            JTableHeader cabecera = tabla.getTableHeader();
            cabecera.setDefaultRenderer(new DefaultTableCellRenderer() {
                @Override
                public Component getTableCellRendererComponent(JTable table, Object value,
                                                               boolean isSelected, boolean hasFocus,
                                                               int row, int column) {
                    JLabel label = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                    label.setOpaque(true);
                    label.setBackground(new Color(245, 156, 107));
                    label.setForeground(Color.WHITE);
                    label.setFont(new Font("Arial", Font.BOLD, 13));
                    label.setHorizontalAlignment(SwingConstants.CENTER);
                    label.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 1, new Color(230, 130, 90))); // Opcional: bordes entre columnas
                    return label;
                }
            });
            cabecera.setOpaque(false);

        }
    }

    /**
     * Método para encontrar la tabla dentro de un contenedor.
     * @param contenedor El contenedor en el que buscar.
     * @return La tabla encontrada, o null si no se encuentra.
     */
    private JTable encontrarJTable(Container contenedor) {
        for (Component componente : contenedor.getComponents()) {
            if (componente instanceof JTable tabla) {
                return tabla;
            } else if (componente instanceof Container anidado) {
                JTable encontrado = encontrarJTable(anidado);
                if (encontrado != null) return encontrado;
            }
        }
        return null;
    }

}