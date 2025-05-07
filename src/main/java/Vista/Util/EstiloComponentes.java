package Vista.Util;

import javax.swing.*;
import javax.swing.plaf.basic.BasicComboPopup;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;
import java.awt.*;
import java.util.Objects;

/**
 * Clase que contiene métodos para personalizar componentes Swing.
 */
public class EstiloComponentes {

    /**
     * Método para establecer el borde naranja en un componente.
     * @param componente El componente al que se le aplicará el borde.
     */
    public static void setBordeNaranja(JComponent componente) {
        componente.setBorder(BorderFactory.createLineBorder(new Color(245, 156, 107)));
    }

    /**
     * Método para crear un JTextField con un borde naranja y un margen izquierdo.
     * Para que los textos no se vean tan pegados al borde.
     * @return
     */
    public static JTextFieldConMargen crearTextField() {
        JTextFieldConMargen campoTexto = new JTextFieldConMargen(30);
        EstiloComponentes.setBordeNaranja(campoTexto);
        return campoTexto;
    }

    public static JPasswordFieldConMargen crearPasswordField() {
        JPasswordFieldConMargen campoContrasenia = new JPasswordFieldConMargen(30);
        EstiloComponentes.setBordeNaranja(campoContrasenia);
        return campoContrasenia;
    }

    /**
     * Método para crear un comboBox personalizado.
     * @param comboBox
     */
    public static void personalizarComboBox(JComboBox<?> comboBox) {
        comboBox.setBackground(new Color(245, 156, 107));
        comboBox.setForeground(Color.BLACK);
        comboBox.setFont(new Font("Arial", Font.PLAIN, 14));
        comboBox.setOpaque(true);


        comboBox.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index,
                                                          boolean isSelected, boolean cellHasFocus) {
                JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                label.setFont(new Font("Arial", Font.PLAIN, 14));
                label.setOpaque(true);

                if (isSelected) {
                    label.setBackground(new Color(245, 156, 107));
                    label.setForeground(Color.BLACK);
                } else {
                    label.setBackground(Color.WHITE);
                    label.setForeground(Color.BLACK);
                }

                label.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
                return label;
            }
        });

        BasicComboPopup popup = (BasicComboPopup) comboBox.getAccessibleContext().getAccessibleChild(0);

        JList<?> list = popup.getList();

        JViewport viewport = (JViewport) list.getParent();

        JScrollPane scrollPane = (JScrollPane) viewport.getParent();

        JScrollBar verticalScrollBar = scrollPane.getVerticalScrollBar();
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
    }

    /**
     * Método para que la fecha del datePicker tenga un espaciado.
     * @param datePicker El datePicker al que se le aplicará el espaciado.
     */
    public static void EspaciadoEnDatePicker(CustomDatePicker datePicker) {
        JTextField dateTextField = datePicker.getComponentDateTextField();
        dateTextField.setFont(new Font("Arial", Font.PLAIN, 14));
        dateTextField.setEditable(false);
        dateTextField.setBackground(Color.WHITE);
        dateTextField.setForeground(new Color(50, 50, 50));
        dateTextField.setBorder(BorderFactory.createLineBorder(new Color(245, 156, 107)));

        PlainDocument doc = new PlainDocument() {
            @Override
            public void insertString(int offset, String str, AttributeSet a) throws BadLocationException {
                super.insertString(offset, "  " + str, a);
            }
        };
        dateTextField.setDocument(doc);
    }

    /**
     * Método para personalizar un JCheckBox con iconos personalizados de color naranja.
     * @param checkBox El JCheckBox a personalizar.
     */
    public static void checkPersonalizadoNaranja(JCheckBox checkBox){
        checkBox.setIcon(new ImageIcon(Objects.requireNonNull(EstiloComponentes.class.getResource("/icons/checkoff.png"))));
        checkBox.setSelectedIcon(new ImageIcon(Objects.requireNonNull(EstiloComponentes.class.getResource("/icons/checkon.png"))));
        checkBox.setOpaque(true);
    }

    /**
     * Método para personalizar un JCheckBox con iconos personalizados de color gris.
     * @param checkBox El JCheckBox a personalizar.
     */
    public static void checkPersonalizadoGris(JCheckBox checkBox){
        checkBox.setIcon(new ImageIcon(Objects.requireNonNull(EstiloComponentes.class.getResource("/icons/checkoffGris.png"))));
        checkBox.setSelectedIcon(new ImageIcon(Objects.requireNonNull(EstiloComponentes.class.getResource("/icons/checkonGris.png"))));
        checkBox.setOpaque(true);
    }
}