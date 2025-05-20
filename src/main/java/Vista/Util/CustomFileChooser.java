package Vista.Util;

import javax.swing.*;
import java.awt.*;
import java.util.Locale;
import java.util.Objects;

/**
 * Clase personalizada para JFileChooser con un Look and Feel específico y colores personalizados.
 */
public class CustomFileChooser extends JFileChooser {

    /**
     * Aplica el Look and Feel Nimbus y personaliza los colores del UIManager.
     */
    public static void aplicarEstiloFileChooser() {
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
     * Traduce los textos del JFileChooser al español.
     */
    public static void traducirCustomFileChooser(){
        UIManager.put("FileChooser.approveButtonText", "Guardar");
        UIManager.put("FileChooser.saveButtonText", "Guardar");
        UIManager.put("FileChooser.saveDialogTitleText", "Guardar como");
        UIManager.put("FileChooser.cancelButtonTitleText", "Cancelar");
        UIManager.put("FileChooser.cancelButtonText", "Cancelar");
        UIManager.put("FileChooser.lookInLabelText", "Buscar en:");
        UIManager.put("FileChooser.fileNameLabelText", "Nombre de carpeta:");
        UIManager.put("FileChooser.filesOfTypeLabelText", "Tipo de archivo:");
        UIManager.put("FileChooser.upFolderToolTipText", "Subir un nivel");
        UIManager.put("FileChooser.homeFolderToolTipText", "Carpeta personal");
        UIManager.put("FileChooser.newFolderToolTipText", "Crear carpeta");
        UIManager.put("FileChooser.listViewButtonToolTipText", "Vista de lista");
        UIManager.put("FileChooser.detailsViewButtonToolTipText", "Vista de detalles");
        UIManager.put("FileChooser.fileNameHeaderText", "Nombre");
        UIManager.put("FileChooser.fileSizeHeaderText", "Tamaño");
        UIManager.put("FileChooser.fileTypeHeaderText", "Tipo");
        UIManager.put("FileChooser.fileDateHeaderText", "Fecha de modificación");
        UIManager.put("FileChooser.fileNameLabelText", "Nombre de carpeta:");
        UIManager.put("FileChooser.folderNameLabelText", "Nombre de carpeta:");
        UIManager.put("FileChooser.allFilesLabelText", "Todo");
    }

    /**
     * Método principal para crear el JFileChooser personalizado.
     */
    public static CustomFileChooser crearFileChooser(String titulo) {
        CustomFileChooser fileChooser = new CustomFileChooser();
        fileChooser.setDialogTitle(titulo);
        fileChooser.setApproveButtonToolTipText("Guardar archivo seleccionado");
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        fileChooser.setAcceptAllFileFilterUsed(false);
        return fileChooser;
    }

    /**
     * Sobrecarga del método createDialog para establecer un icono personalizado en el JDialog.
     */
    @Override
    protected JDialog createDialog(Component parent) throws HeadlessException {
        JDialog dialog = super.createDialog(parent);
        dialog.setIconImage(new ImageIcon(Objects.requireNonNull(EstiloComponentes.class.getResource("/icons/logo.png"))).getImage());
        return dialog;
    }
}