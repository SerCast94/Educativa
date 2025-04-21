package Vista.Util;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Boton extends JButton {

    public enum ButtonType {
        PRIMARY, SECONDARY, DELETE
    }

    public Boton(String text, ButtonType type) {
        super(text);
        setFont(new Font("Segoe UI", Font.BOLD, 14));
        setFocusPainted(false);
        setBorder(new RoundedBorder(12));
        setForeground(Color.BLACK);
        setCursor(new Cursor(Cursor.HAND_CURSOR));
        setContentAreaFilled(false);
        setOpaque(false);
        setBorderPainted(false);
        switch (type) {
            case PRIMARY:
                setBackground(Color.decode("#F59C6B"));
                setHoverEffect(Color.decode("#F59C6B"), Color.decode("#EF654A"));
                break;
            case SECONDARY:
                setBackground(Color.decode("#F6C6B3"));
                setForeground(Color.BLACK);
                setHoverEffect(Color.decode("#F6C6B3"), Color.decode("#F59C6B"));
                break;
            case DELETE:
                setBackground(Color.decode("#D93C3C"));
                setHoverEffect(Color.decode("#D93C3C"), new Color(200, 50, 50));
                break;
        }

        setMargin(new Insets(10, 20, 10, 20));
    }

    private void setHoverEffect(Color normalColor, Color hoverColor) {
        addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
                setBackground(hoverColor);
            }

            public void mouseExited(MouseEvent evt) {
                setBackground(normalColor);
            }
        });
        setBackground(normalColor);
    }

    private static class RoundedBorder implements Border {
        private int radius;

        RoundedBorder(int radius) {
            this.radius = radius;
        }

        public Insets getBorderInsets(Component c) {
            return new Insets(radius + 1, radius + 1, radius + 2, radius);
        }

        public boolean isBorderOpaque() {
            return true;
        }

        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
            g.drawRoundRect(x, y, width - 1, height - 1, radius, radius);
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int arc = 20;
        int width = getWidth();
        int height = getHeight();

        g2.setColor(getBackground());
        g2.fillRoundRect(0, 0, width, height, arc, arc);

        super.paintComponent(g2);
        g2.dispose();
    }

}

