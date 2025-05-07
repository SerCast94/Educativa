package Vista.Util;

import javax.swing.*;
import java.awt.*;

/**
 * Clase personalizada para JPasswordField con margen.
 */
public class JPasswordFieldConMargen extends JPasswordField {
    /**
     * Constructor que crea un JPasswordField con un número específico de columnas.
     * @param columnas Número de columnas del campo de contraseña.
     */
    public JPasswordFieldConMargen(int columnas) {
        super(columnas);
    }

    /**
     * Método que devuelve los insets (márgenes) del campo de contraseña.
     * @return Insets con el margen izquierdo.
     */
    @Override
    public Insets getInsets() {
        return new Insets(5, 10, 5, 5);
    }
}
