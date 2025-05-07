package Vista.Util;

import javax.swing.*;
import java.awt.*;
import java.util.Locale;

/**
 * Clase personalizada para JFileChooser con un Look and Feel específico y colores personalizados.
 */
public class CustomFileChooser {

    /**
     * Aplica el Look and Feel Nimbus y personaliza los colores del UIManager.
     */
    public static void applyNimbusLookAndFeel() {
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");

            Locale.setDefault(new Locale("es", "ES"));
            UIManager.put("control", new Color(251, 234, 230));
            UIManager.put("nimbusBase", new Color(255, 122, 51));
            UIManager.put("nimbusFocus", new Color(255, 174, 91));
            UIManager.put("nimbusLightBackground", new Color(255, 204, 153));
            UIManager.put("nimbusBorder", new Color(190, 132, 80));
        } catch (UnsupportedLookAndFeelException | ClassNotFoundException | InstantiationException |
                 IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    /**
     * Método principal para crear el JFileChooser personalizado.
     */
    public static JFileChooser createFileChooser(String dialogTitle, String approveButtonText) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle(dialogTitle);
        fileChooser.setApproveButtonText(approveButtonText);
        fileChooser.setApproveButtonToolTipText("Guardar archivo seleccionado");
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        return fileChooser;
    }
}