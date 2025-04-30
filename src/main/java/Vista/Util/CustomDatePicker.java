package Vista.Util;

import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.components.DatePickerSettings;
import com.github.lgooddatepicker.components.CalendarPanel;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionListener;

public class CustomDatePicker extends DatePicker {

    public CustomDatePicker() {
        super(buildSettings());
        stylizeTextField();
        stylizeButton();
        overridePopupStyle();

    }

    private static DatePickerSettings buildSettings() {
        DatePickerSettings settings = new DatePickerSettings();
        settings.setAllowEmptyDates(false);
        settings.setFormatForDatesCommonEra("MM/dd/yyyy");
        settings.setFontValidDate(new Font("Arial", Font.PLAIN, 14));
        settings.setFontInvalidDate(new Font("Arial", Font.PLAIN, 14));

        Color fondoBase = new Color(251, 234, 230);
        Color bordeNaranja = new Color(245, 156, 107);
        Color textoPrincipal = Color.BLACK;
        Color fondoSeleccion = new Color(245, 156, 107);
        Color fechaSeleccionada = new Color(245, 156, 107);

        settings.setColor(DatePickerSettings.DateArea.BackgroundClearLabel, fondoBase);
        settings.setColor(DatePickerSettings.DateArea.BackgroundMonthAndYearMenuLabels, fondoBase);
        settings.setColor(DatePickerSettings.DateArea.BackgroundMonthAndYearNavigationButtons, fondoBase);
        settings.setColor(DatePickerSettings.DateArea.BackgroundCalendarPanelLabelsOnHover, fondoSeleccion);
        settings.setColor(DatePickerSettings.DateArea.BackgroundOverallCalendarPanel, fondoBase);
        settings.setColor(DatePickerSettings.DateArea.BackgroundTodayLabel, fondoBase);
        settings.setColor(DatePickerSettings.DateArea.BackgroundTopLeftLabelAboveWeekNumbers, fondoSeleccion);

        settings.setColor(DatePickerSettings.DateArea.CalendarBackgroundNormalDates, Color.WHITE);
        settings.setColor(DatePickerSettings.DateArea.CalendarBackgroundSelectedDate, fechaSeleccionada);
        settings.setColor(DatePickerSettings.DateArea.CalendarBackgroundVetoedDates, Color.LIGHT_GRAY);
        settings.setColor(DatePickerSettings.DateArea.CalendarBorderSelectedDate, bordeNaranja);
        settings.setColor(DatePickerSettings.DateArea.CalendarDefaultBackgroundHighlightedDates, fondoSeleccion);
        settings.setColor(DatePickerSettings.DateArea.CalendarDefaultTextHighlightedDates, textoPrincipal);
        settings.setColorBackgroundWeekNumberLabels(bordeNaranja,false );
        settings.setColorBackgroundWeekdayLabels(bordeNaranja,false);
        settings.setColor(DatePickerSettings.DateArea.CalendarTextNormalDates, textoPrincipal);
        settings.setColor(DatePickerSettings.DateArea.CalendarTextWeekdays, textoPrincipal);
        settings.setColor(DatePickerSettings.DateArea.CalendarTextWeekNumbers, textoPrincipal);

        settings.setColor(DatePickerSettings.DateArea.TextClearLabel, textoPrincipal);
        settings.setColor(DatePickerSettings.DateArea.TextMonthAndYearMenuLabels, textoPrincipal);
        settings.setColor(DatePickerSettings.DateArea.TextMonthAndYearNavigationButtons, textoPrincipal);
        settings.setColor(DatePickerSettings.DateArea.TextTodayLabel, textoPrincipal);
        settings.setColor(DatePickerSettings.DateArea.TextCalendarPanelLabelsOnHover, textoPrincipal);

        settings.setColor(DatePickerSettings.DateArea.TextFieldBackgroundDisallowedEmptyDate, fondoBase);
        settings.setColor(DatePickerSettings.DateArea.TextFieldBackgroundInvalidDate, Color.WHITE);
        settings.setColor(DatePickerSettings.DateArea.TextFieldBackgroundValidDate, Color.WHITE);
        settings.setColor(DatePickerSettings.DateArea.TextFieldBackgroundVetoedDate, Color.WHITE);
        settings.setColor(DatePickerSettings.DateArea.TextFieldBackgroundDisabled, fondoBase);

        settings.setColor(DatePickerSettings.DateArea.DatePickerTextInvalidDate, Color.RED);
        settings.setColor(DatePickerSettings.DateArea.DatePickerTextValidDate, textoPrincipal);
        settings.setColor(DatePickerSettings.DateArea.DatePickerTextVetoedDate, textoPrincipal);
        settings.setColor(DatePickerSettings.DateArea.DatePickerTextDisabled, fondoBase);

        return settings;
    }

    private void stylizeTextField() {
        JTextField textField = this.getComponentDateTextField();
        textField.setFont(new Font("Arial", Font.PLAIN, 14));
        textField.setBackground(Color.WHITE);
        textField.setForeground(new Color(50, 50, 50));
        textField.setBorder(BorderFactory.createLineBorder(new Color(245, 156, 107), 1));
    }

    private void stylizeButton() {
        JButton button = this.getComponentToggleCalendarButton();
        button.setBackground(new Color(245, 156, 107));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }

    private void overridePopupStyle() {
        this.getComponentToggleCalendarButton().addActionListener(e -> {
            SwingUtilities.invokeLater(this::applyPopupStyle);
        });
    }

    private void applyPopupStyle() {
        SwingUtilities.invokeLater(() -> {
            for (Window window : Window.getWindows()) {
                if (window instanceof JDialog dialog && dialog.isVisible()) {
                    Component[] components = dialog.getComponents();
                    for (Component comp : components) {
                        if (comp instanceof JPanel panel) {
                            CalendarPanel calendarPanel = findCalendarPanel(panel);
                            if (calendarPanel != null) {
                                styleCalendarPanel(calendarPanel);
                                styleCalendarTable(calendarPanel);
                            }
                        }
                    }
                }
            }
        });
    }

    private CalendarPanel findCalendarPanel(Container container) {
        for (Component comp : container.getComponents()) {
            if (comp instanceof CalendarPanel panel) {
                return panel;
            } else if (comp instanceof Container nested) {
                CalendarPanel found = findCalendarPanel(nested);
                if (found != null) return found;
            }
        }
        return null;
    }

    private void styleCalendarPanel(CalendarPanel calendarPanel) {
        calendarPanel.setBackground(new Color(251, 234, 230));
        calendarPanel.setBorder(BorderFactory.createLineBorder(new Color(245, 156, 107), 1));
        calendarPanel.setFont(new Font("Arial", Font.PLAIN, 13));

        for (Component c : calendarPanel.getComponents()) {
            if (c instanceof JButton btn) {
                btn.setBackground(new Color(245, 156, 107));
                btn.setForeground(Color.WHITE);
                btn.setFont(new Font("Arial", Font.BOLD, 12));
                btn.setFocusPainted(false);
                btn.setBorder(BorderFactory.createEmptyBorder(4, 6, 4, 6));
            }
        }
        calendarPanel.repaint();
    }

    private void styleCalendarTable(CalendarPanel calendarPanel) {
        JTable table = findJTable(calendarPanel);
        if (table != null) {
            table.setBackground(new Color(251, 234, 230));
            table.setForeground(new Color(50, 50, 50));
            table.setFont(new Font("Arial", Font.PLAIN, 13));
            table.setGridColor(new Color(245, 156, 107));
            table.setRowHeight(28);
            table.setShowGrid(true);

            // Header
            JTableHeader header = table.getTableHeader();
            header.setDefaultRenderer(new DefaultTableCellRenderer() {
                @Override
                public Component getTableCellRendererComponent(JTable table, Object value,
                                                               boolean isSelected, boolean hasFocus,
                                                               int row, int column) {
                    JLabel label = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                    label.setOpaque(true); // <- ¡esto es clave!
                    label.setBackground(new Color(245, 156, 107));  // Fondo anaranjado
                    label.setForeground(Color.WHITE);               // Texto blanco
                    label.setFont(new Font("Arial", Font.BOLD, 13));
                    label.setHorizontalAlignment(SwingConstants.CENTER);
                    label.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 1, new Color(230, 130, 90))); // Opcional: bordes entre columnas
                    return label;
                }
            });
            header.setOpaque(false);

        }
    }

    private JTable findJTable(Container container) {
        for (Component c : container.getComponents()) {
            if (c instanceof JTable table) {
                return table;
            } else if (c instanceof Container nested) {
                JTable found = findJTable(nested);
                if (found != null) return found;
            }
        }
        return null;
    }

    public void addActionListener(ActionListener actionListener) {

    }
}
