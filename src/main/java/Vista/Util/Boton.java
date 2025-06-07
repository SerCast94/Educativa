package Vista.Util;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Clase de botón personalizada que extiende JButton.
 * Esta clase permite crear botones con diferentes estilos y efectos de hover.
 */
public class Boton extends JButton {

    /**
     * Enum para definir los tipos de botones.
     */
    public enum tipoBoton {
        PRIMARY, SECONDARY, DELETE
    }

    /**
     * Constructor de la clase Boton.
     * @param texto El texto que se mostrará en el botón.
     * @param tipo  El tipo de botón (PRIMARY, SECONDARY, DELETE).
     */
    public Boton(String texto, tipoBoton tipo) {
        super(texto);
        setFont(new Font("Segoe UI", Font.BOLD, 14));
        setFocusPainted(false);
        setBorder(new RedondearBordes(12));
        setForeground(Color.BLACK);
        setCursor(new Cursor(Cursor.HAND_CURSOR));
        setContentAreaFilled(false);
        setOpaque(false);
        setBorderPainted(false);
        switch (tipo) {
            case PRIMARY:
                setBackground(Color.decode("#F59C6B"));
                efectoHover(Color.decode("#F59C6B"), Color.decode("#EF654A"));
                break;
            case SECONDARY:
                setBackground(Color.decode("#F6C6B3"));
                setForeground(Color.BLACK);
                efectoHover(Color.decode("#F6C6B3"), Color.decode("#F59C6B"));
                break;
            case DELETE:
                setBackground(Color.decode("#D93C3C"));
                efectoHover(Color.decode("#D93C3C"), new Color(200, 50, 50));
                break;
        }

        setMargin(new Insets(10, 20, 10, 20));
    }

    /**
     * Método para aplicar un efecto hover al botón.
     * @param normalColor Color normal del botón.
     * @param hoverColor Color del botón al pasar el ratón.
     */
    private void efectoHover(Color normalColor, Color hoverColor) {
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

    /**
     * Clase interna que implementa un borde redondeado para el botón.
     */
    private static class RedondearBordes implements Border {
        private int radio;

        /**
         * Constructor que recibe el radio del borde redondeado.
         * @param radio Radio del borde en píxeles.
         */
        RedondearBordes(int radio) {
            this.radio = radio;
        }

        /**
         * Define los márgenes del borde.
         * @param componente Componente asociado.
         * @return Objeto Insets con el tamaño del borde.
         */
        public Insets getBorderInsets(Component componente) {
            return new Insets(radio + 1, radio + 1, radio + 2, radio);
        }

        /**
         * Indica si el borde es opaco.
         * @return true si es opaco.
         */
        public boolean isBorderOpaque() {
            return true;
        }

        /**
         * Dibuja el borde redondeado.
         * @param componente      Componente donde se pinta.
         * @param grafico      Objeto Graphics.
         * @param x      Coordenada X de inicio.
         * @param y      Coordenada Y de inicio.
         * @param ancho  Ancho del borde.
         * @param alto Alto del borde.
         */
        public void paintBorder(Component componente, Graphics grafico, int x, int y, int ancho, int alto) {
            grafico.drawRoundRect(x, y, ancho - 1, alto - 1, radio, radio);
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