package BackUtil;

import Mapeo.Cursos;
import Mapeo.Estudiantes;
import Mapeo.Matriculas;
import Vista.Util.CustomDialog;
import Vista.Util.CustomFileChooser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.interactive.form.PDAcroForm;
import org.apache.pdfbox.pdmodel.interactive.form.PDField;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

import static Controlador.ControladorReportes.enviarInfoParaBoletin;

/**
 * Clase que genera reportes en formato PDF
 */
public class GeneradorReportes {

    static {
        Logger.getLogger("org.apache.pdfbox").setLevel(Level.SEVERE);
        for (java.util.logging.Handler manejador : Logger.getLogger("").getHandlers()) {
            if (manejador instanceof ConsoleHandler) {
                manejador.setLevel(Level.SEVERE);
            }
        }
    }

    public static void generarBoletin(Map<String, String> datos) {
        String lookAndFeelActual = UIManager.getLookAndFeel().getClass().getName();

        try {
            CustomFileChooser.aplicarEstiloFileChooser();
            CustomFileChooser.traducirCustomFileChooser();

            InputStream plantillaStream = GeneradorReportes.class.getResourceAsStream("/plantillas/plantillaNotas.pdf");
            if (plantillaStream == null) {
                throw new IOException("No se encontró plantillaNotas.pdf en el classpath");
            }

            PDDocument documento = PDDocument.load(plantillaStream);
            PDAcroForm acroForm = documento.getDocumentCatalog().getAcroForm();

            if (acroForm != null) {
                for (Map.Entry<String, String> entry : datos.entrySet()) {
                    PDField field = acroForm.getField(entry.getKey());
                    if (field != null) {
                        field.setValue(entry.getValue());
                        field.setReadOnly(true);
                    }
                }
            }

            CustomFileChooser fileChooser = CustomFileChooser.crearFileChooser("Seleccionar ubicación para guardar el boletín");
            int resultado = fileChooser.showSaveDialog(null);
            if (resultado == JFileChooser.APPROVE_OPTION) {
                File ruta = fileChooser.getSelectedFile();
                String nombreArchivo = "Boletin_" + (datos.get("nombre") != null ? datos.get("nombre").trim() : "sin_nombre") + ".pdf";
                nombreArchivo = nombreArchivo.replaceAll(" ", "_");

                File archivoFinal = new File(ruta, nombreArchivo);
                documento.save(archivoFinal.getAbsolutePath());
                documento.close();

                new CustomDialog(null, "Éxito", "Boletín guardado correctamente en: " + archivoFinal.getAbsolutePath(), "ONLY_OK").setVisible(true);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                UIManager.setLookAndFeel(lookAndFeelActual);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void generarBoletinesPorCurso(Cursos curso) {
        String lookAndFeelActual = UIManager.getLookAndFeel().getClass().getName();

        if (curso == null || curso.getMatriculas() == null || curso.getMatriculas().isEmpty()) {
            new CustomDialog(null, "Error", "El curso no tiene estudiantes matriculados.", "ONLY_OK").setVisible(true);
            return;
        }

        CustomFileChooser.aplicarEstiloFileChooser();
        CustomFileChooser.traducirCustomFileChooser();
        CustomFileChooser fileChooser = CustomFileChooser.crearFileChooser("Seleccionar ubicación para guardar los boletines");

        int resultado = fileChooser.showSaveDialog(null);
        if (resultado != JFileChooser.APPROVE_OPTION) {
            try {
                UIManager.setLookAndFeel(lookAndFeelActual);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return;
        }

        File directorioBase = fileChooser.getSelectedFile();
        File directorioCurso = new File(directorioBase, curso.getNombre().replaceAll(" ", "_"));

        if (!directorioCurso.exists() && !directorioCurso.mkdirs()) {
            new CustomDialog(null, "Error", "No se pudo crear la carpeta para el curso.", "ONLY_OK").setVisible(true);
            return;
        }

        for (Matriculas matricula : curso.getMatriculas()) {
            Estudiantes estudiante = matricula.getEstudiante();
            Map<String, String> datos = enviarInfoParaBoletin(estudiante);

            try (InputStream plantillaStream = GeneradorReportes.class.getResourceAsStream("/plantillas/plantillaNotas.pdf")) {
                if (plantillaStream == null) {
                    throw new IOException("No se encontró plantillaNotas.pdf en el classpath");
                }

                PDDocument documento = PDDocument.load(plantillaStream);
                PDAcroForm acroForm = documento.getDocumentCatalog().getAcroForm();

                if (acroForm != null) {
                    for (Map.Entry<String, String> entry : datos.entrySet()) {
                        PDField field = acroForm.getField(entry.getKey());
                        if (field != null) {
                            field.setValue(entry.getValue());
                            field.setReadOnly(true);
                        }
                    }
                }

                String nombreArchivo = "Boletin_" + (datos.get("nombre") != null ? datos.get("nombre").trim() : "sin_nombre") + ".pdf";
                nombreArchivo = nombreArchivo.replaceAll(" ", "_");

                File archivoFinal = new File(directorioCurso, nombreArchivo);
                documento.save(archivoFinal.getAbsolutePath());
                documento.close();

            } catch (IOException e) {
                e.printStackTrace();
                new CustomDialog(null, "Error", "Error al generar el boletín para: " + datos.get("nombre"), "ONLY_OK").setVisible(true);
            }
        }

        new CustomDialog(null, "Éxito", "Boletines generados correctamente en: " + directorioCurso.getAbsolutePath(), "ONLY_OK").setVisible(true);
    }

    public static void generarCertificadoBeca(Map<String, String> datos) {
        String lookAndFeelActual = UIManager.getLookAndFeel().getClass().getName();

        try {
            CustomFileChooser.aplicarEstiloFileChooser();
            CustomFileChooser.traducirCustomFileChooser();

            InputStream plantillaStream = GeneradorReportes.class.getResourceAsStream("/plantillas/plantillaBeca.pdf");
            if (plantillaStream == null) {
                throw new IOException("No se encontró plantillaBeca.pdf en el classpath");
            }

            PDDocument documento = PDDocument.load(plantillaStream);
            PDAcroForm acroForm = documento.getDocumentCatalog().getAcroForm();

            if (acroForm != null) {
                for (Map.Entry<String, String> entry : datos.entrySet()) {
                    PDField field = acroForm.getField(entry.getKey());
                    if (field != null) {
                        field.setValue(entry.getValue());
                        field.setReadOnly(true);
                    }
                }
            }

            CustomFileChooser fileChooser = CustomFileChooser.crearFileChooser("Guardar certificado de beca");
            int resultado = fileChooser.showSaveDialog(null);
            if (resultado == JFileChooser.APPROVE_OPTION) {
                File ruta = fileChooser.getSelectedFile();
                String nombreArchivo = "Certificado_Beca_" +
                        (datos.get("nombreEstudiante") != null ? datos.get("nombreEstudiante").trim() : "sin_nombre") + ".pdf";
                nombreArchivo = nombreArchivo.replaceAll(" ", "_");

                File archivoFinal = new File(ruta, nombreArchivo);
                documento.save(archivoFinal.getAbsolutePath());
                documento.close();

                new CustomDialog(null, "Éxito", "Certificado de beca guardado en: " + archivoFinal.getAbsolutePath(), "ONLY_OK").setVisible(true);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                UIManager.setLookAndFeel(lookAndFeelActual);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void generarCertificadoConvalidacion(Map<String, String> datos) {
        String lookAndFeelActual = UIManager.getLookAndFeel().getClass().getName();

        try {
            CustomFileChooser.aplicarEstiloFileChooser();
            CustomFileChooser.traducirCustomFileChooser();

            InputStream plantillaStream = GeneradorReportes.class.getResourceAsStream("/plantillas/plantillaConvalidacion.pdf");
            if (plantillaStream == null) {
                throw new IOException("No se encontró plantillaConvalidacion.pdf en el classpath");
            }

            PDDocument documento = PDDocument.load(plantillaStream);
            PDAcroForm acroForm = documento.getDocumentCatalog().getAcroForm();

            if (acroForm != null) {
                for (Map.Entry<String, String> entry : datos.entrySet()) {
                    PDField field = acroForm.getField(entry.getKey());
                    if (field != null) {
                        field.setValue(entry.getValue());
                        field.setReadOnly(true);
                    }
                }
            }

            CustomFileChooser fileChooser = CustomFileChooser.crearFileChooser("Guardar certificado de convalidación");
            int resultado = fileChooser.showSaveDialog(null);
            if (resultado == JFileChooser.APPROVE_OPTION) {
                File ruta = fileChooser.getSelectedFile();
                String nombreArchivo = "Certificado_Convalidacion_" +
                        (datos.get("nombreEstudiante") != null ? datos.get("nombreEstudiante").trim() : "sin_nombre") + ".pdf";
                nombreArchivo = nombreArchivo.replaceAll(" ", "_");

                File archivoFinal = new File(ruta, nombreArchivo);
                documento.save(archivoFinal.getAbsolutePath());
                documento.close();

                new CustomDialog(null, "Éxito", "Certificado de convalidación guardado en: " + archivoFinal.getAbsolutePath(), "ONLY_OK").setVisible(true);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                UIManager.setLookAndFeel(lookAndFeelActual);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
