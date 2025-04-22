package Vista.Util;

import javax.swing.*;
import javax.swing.plaf.basic.BasicComboPopup;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;
import java.awt.*;
import java.util.Objects;

import static javax.swing.text.StyleConstants.setBackground;

public class EstiloComponentes {

    public static void setBordeNaranja(JComponent componente) {
        componente.setBorder(BorderFactory.createLineBorder(new Color(245, 156, 107)));
    }


    public static JTextFieldConMargen crearTextField() {
        JTextFieldConMargen tf = new JTextFieldConMargen(30);
        EstiloComponentes.setBordeNaranja(tf);
        return tf;
    }

    public static JPasswordFieldConMargen crearPasswordField() {
        JPasswordFieldConMargen pf = new JPasswordFieldConMargen(30);
        EstiloComponentes.setBordeNaranja(pf);
        return pf;
    }


    public static void customizeComboBox(JComboBox<?> comboBox) {
        comboBox.setBackground(new Color(245, 156, 107));  // Fondo color similar al de los botones
        comboBox.setForeground(Color.BLACK); // Texto negro
        comboBox.setFont(new Font("Arial", Font.PLAIN, 14));
        comboBox.setOpaque(true); // Hacer el fondo opaco


        // Usar un renderer para personalizar el estilo del JComboBox
        comboBox.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index,
                                                          boolean isSelected, boolean cellHasFocus) {
                JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                label.setFont(new Font("Arial", Font.PLAIN, 14));
                label.setOpaque(true);

                if (isSelected) {
                    label.setBackground(new Color(245, 156, 107));  // Color del hover
                    label.setForeground(Color.BLACK);
                } else {
                    label.setBackground(Color.WHITE);
                    label.setForeground(Color.BLACK);
                }

                // Agregar margen izquierdo en la celda del JComboBox
                label.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0)); // 10 píxeles de margen a la izquierda
                return label;
            }
        });

        // Obtener el BasicComboPopup (el popup del JComboBox)
        BasicComboPopup popup = (BasicComboPopup) comboBox.getAccessibleContext().getAccessibleChild(0);

        // Acceder a la lista interna (JList) del popup
        JList<?> list = popup.getList();

        // Obtener el JViewport que contiene la lista
        JViewport viewport = (JViewport) list.getParent();

        // Acceder al JScrollPane que contiene el JViewport
        JScrollPane scrollPane = (JScrollPane) viewport.getParent();

        // Ahora personalizamos la barra de desplazamiento
        JScrollBar verticalScrollBar = scrollPane.getVerticalScrollBar();
        verticalScrollBar.setUI(new javax.swing.plaf.basic.BasicScrollBarUI() {
            @Override
            protected JButton createDecreaseButton(int orientation) {
                JButton button = super.createDecreaseButton(orientation);
                button.setBackground(new Color(251, 234, 230));  // Fondo de los botones de desplazamiento
                return button;
            }

            @Override
            protected JButton createIncreaseButton(int orientation) {
                JButton button = super.createIncreaseButton(orientation);
                button.setBackground(new Color(251, 234, 230));  // Fondo de los botones de desplazamiento
                return button;
            }

            @Override
            protected void paintTrack(Graphics g, JComponent c, Rectangle r) {
                Graphics2D g2 = (Graphics2D) g;
                g2.setColor(new Color(251, 234, 230));  // Color de la pista
                g2.fillRoundRect(r.x, r.y, r.width, r.height, 10, 10);  // Esquinas redondeadas
            }

            @Override
            protected void paintThumb(Graphics g, JComponent c, Rectangle r) {
                Graphics2D g2 = (Graphics2D) g;
                g2.setColor(new Color(245, 156, 107));  // Color del pulgar
                g2.fillRoundRect(r.x, r.y, r.width, r.height, 10, 10);  // Esquinas redondeadas
            }
        });
    }

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

    public static void checkPersonalizado(JCheckBox checkBox){
        checkBox.setIcon(new ImageIcon(Objects.requireNonNull(EstiloComponentes.class.getResource("/icons/checkoff.png"))));
        checkBox.setSelectedIcon(new ImageIcon(Objects.requireNonNull(EstiloComponentes.class.getResource("/icons/checkon.png"))));
        checkBox.setBackground(new Color(251, 234, 230));
    }
}
