package BackUtil;

import Mapeo.Cursos;
import Mapeo.Estudiantes;
import Mapeo.Matriculas;
import Vista.Util.CustomFileChooser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.interactive.form.PDAcroForm;
import org.apache.pdfbox.pdmodel.interactive.form.PDField;
import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

import static Controlador.ControladorBoletin.enviarInfoParaBoletin;

public class GeneradorBoletin {

    static {
        // Configurar el logger para mostrar solo mensajes de error severo
        Logger.getLogger("org.apache.pdfbox").setLevel(Level.SEVERE);
        for (java.util.logging.Handler handler : Logger.getLogger("").getHandlers()) {
            if (handler instanceof ConsoleHandler) {
                handler.setLevel(Level.SEVERE);
            }
        }
    }

    public static void generarBoletin(Map<String, String> datos){
        try {

            // Cargar el archivo PDF con campos
            PDDocument documento = PDDocument.load(new File("src/main/resources/plantillas/plantillaNotas.pdf"));
            // Obtener el formulario interactivo
            PDAcroForm acroForm = documento.getDocumentCatalog().getAcroForm();

            // Configurar campos del formulario
            if (acroForm != null) {
                for (Map.Entry<String, String> entry : datos.entrySet()) {
                    PDField field = acroForm.getField(entry.getKey());
                    if (field != null) {
                        field.setValue(entry.getValue());
                        field.setReadOnly(true);
                    }
                }
            }

            CustomFileChooser.applyNimbusLookAndFeel();

            JFileChooser fileChooser = CustomFileChooser.createFileChooser(
                    "Seleccionar ubicación para guardar el boletín", "Guardar"
            );

            int resultado = fileChooser.showSaveDialog(null);
            if (resultado == JFileChooser.APPROVE_OPTION) {
                File ruta = fileChooser.getSelectedFile();
                try {
                    String nombreArchivo = "Boletin_" + (datos.get("nombre") != null ? datos.get("nombre").trim() : "sin_nombre") + ".pdf";
                    nombreArchivo = nombreArchivo.replaceAll(" ", "_");

                    File archivoFinal = new File(ruta, nombreArchivo);

                    documento.save(archivoFinal.getAbsolutePath());
                    documento.close();

                    JOptionPane.showMessageDialog(null, "Boletín guardado correctamente en: " + archivoFinal.getAbsolutePath(), "Éxito", JOptionPane.INFORMATION_MESSAGE);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void generarBoletinesPorCurso(Cursos curso) {
        if (curso == null || curso.getMatriculas() == null || curso.getMatriculas().isEmpty()) {
            JOptionPane.showMessageDialog(null, "El curso no tiene estudiantes matriculados.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Guardar el LookAndFeel actual
        String lookAndFeelActual = UIManager.getLookAndFeel().getClass().getName();

        // Solicitar la ruta de guardado
        CustomFileChooser.applyNimbusLookAndFeel();
        JFileChooser fileChooser = CustomFileChooser.createFileChooser(
                "Seleccionar ubicación para guardar los boletines", "Seleccionar"
        );

        int resultado = fileChooser.showSaveDialog(null);
        if (resultado != JFileChooser.APPROVE_OPTION) {
            JOptionPane.showMessageDialog(null, "No se seleccionó ninguna ubicación. Operación cancelada.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        File directorioBase = fileChooser.getSelectedFile();
        File directorioCurso = new File(directorioBase, curso.getNombre().replaceAll(" ", "_"));

        if (!directorioCurso.exists() && !directorioCurso.mkdirs()) {
            JOptionPane.showMessageDialog(null, "No se pudo crear la carpeta para el curso.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        for (Matriculas matricula : curso.getMatriculas()) {
            Estudiantes estudiante = matricula.getEstudiante();
            Map<String, String> datos = enviarInfoParaBoletin(estudiante);

            try {
                String nombreArchivo = "Boletin_" + (datos.get("nombre") != null ? datos.get("nombre").trim() : "sin_nombre") + ".pdf";
                nombreArchivo = nombreArchivo.replaceAll(" ", "_");

                File archivoFinal = new File(directorioCurso, nombreArchivo);

                // Cargar el archivo PDF con campos
                PDDocument documento = PDDocument.load(new File("src/main/resources/plantillas/plantillaNotas.pdf"));
                PDAcroForm acroForm = documento.getDocumentCatalog().getAcroForm();

                // Configurar campos del formulario
                if (acroForm != null) {
                    for (Map.Entry<String, String> entry : datos.entrySet()) {
                        PDField field = acroForm.getField(entry.getKey());
                        if (field != null) {
                            field.setValue(entry.getValue());
                            field.setReadOnly(true);
                        }
                    }
                }

                documento.save(archivoFinal.getAbsolutePath());
                documento.close();

            } catch (IOException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error al generar el boletín para: " + datos.get("nombre"), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }

        JOptionPane.showMessageDialog(null, "Boletines generados correctamente en: " + directorioCurso.getAbsolutePath(), "Éxito", JOptionPane.INFORMATION_MESSAGE);
    }

}