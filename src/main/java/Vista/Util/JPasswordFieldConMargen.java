package Vista.Util;

import javax.swing.*;
import java.awt.*;

// Clase personalizada para JPasswordField con margen
public class JPasswordFieldConMargen extends JPasswordField {
    public JPasswordFieldConMargen(int columnas) {
        super(columnas);
    }

    @Override
    public Insets getInsets() {
        // Agregar margen izquierdo (5 píxeles), mantener los valores predeterminados para otros lados
        return new Insets(5, 10, 5, 5); // top, left, bottom, right
    }
}
