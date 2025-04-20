package Util;

import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.components.DatePickerSettings;
import com.github.lgooddatepicker.components.CalendarPanel;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.lang.reflect.Field;

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
            header.setBackground(new Color(245, 156, 107));
            header.setForeground(Color.WHITE);
            header.setFont(new Font("Arial", Font.BOLD, 13));

            // Renderer para las celdas
            table.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
                @Override
                public Component getTableCellRendererComponent(JTable table, Object value,
                                                               boolean isSelected, boolean hasFocus,
                                                               int row, int column) {
                    Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                    c.setBackground(new Color(251, 234, 230));
                    c.setForeground(new Color(50, 50, 50));
                    setHorizontalAlignment(SwingConstants.CENTER);
                    return c;
                }
            });
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
}
