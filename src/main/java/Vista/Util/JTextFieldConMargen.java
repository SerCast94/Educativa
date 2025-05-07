package Vista.Util;

import javax.swing.*;
import java.awt.*;

/**
 * Clase personalizada para JPasswordField con margen.
 */
public class JTextFieldConMargen extends JTextField {
    /**
     * Constructor que crea un JTextField con un número específico de columnas.
     * @param columnas Número de columnas del campo de texto.
     */
    public JTextFieldConMargen(int columnas) {
        super(columnas);
    }

    /**
     * Método que devuelve los insets (márgenes) del campo de texto.
     * @return Insets con el margen izquierdo.
     */
    @Override
    public Insets getInsets() {
        return new Insets(5, 10, 5, 5);
    }
}

