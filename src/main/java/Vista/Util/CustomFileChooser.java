package Vista.Util;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.util.Locale;

public class CustomFileChooser {

    // Método para configurar el Look and Feel y personalizar colores
    public static void applyNimbusLookAndFeel() {
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");

            // Personalizar los colores para tonos naranjas
            Locale.setDefault(new Locale("es", "ES"));
            UIManager.put("control", new Color(251, 234, 230));  // Fondo de componentes
            UIManager.put("nimbusBase", new Color(255, 122, 51));  // Naranja base
            UIManager.put("nimbusFocus", new Color(255, 174, 91));  // Naranja claro
            UIManager.put("nimbusLightBackground", new Color(255, 204, 153));  // Fondo claro
            UIManager.put("nimbusBorder", new Color(190, 132, 80));  // Bordes
        } catch (UnsupportedLookAndFeelException | ClassNotFoundException | InstantiationException |
                 IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    // Método para crear y personalizar un JFileChooser
    public static JFileChooser createFileChooser(String dialogTitle, String approveButtonText) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle(dialogTitle);
        fileChooser.setApproveButtonText(approveButtonText);
        fileChooser.setApproveButtonToolTipText("Guardar archivo seleccionado");
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        return fileChooser;
    }
}