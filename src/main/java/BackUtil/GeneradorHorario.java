package BackUtil;

import Mapeo.CursosAsignaturas;
import Mapeo.Horarios;
import Vista.Util.CustomDialog;
import Vista.Util.CustomFileChooser;
import javax.swing.*;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

/**
 * Clase que genera un horario en formato XML y HTML
 * Usado para exportar el horario de los profesores y el horario general
 */

public class GeneradorHorario {


    /**
     * Genera un horario en formato XML y HTML para estudiantes
     * @param horarios lista de horarios a exportar
     */
    public static void exportarHorarioAXML(List<Horarios> horarios) {
        String lookAndFeelActual = UIManager.getLookAndFeel().getClass().getName();

        try {
            CustomFileChooser.aplicarEstiloFileChooser();

            CustomFileChooser.traducirCustomFileChooser();

            JFileChooser fileChooser = CustomFileChooser.crearFileChooser("Selecciona dónde guardar el horario");

            int resultado = fileChooser.showSaveDialog(null);

            if (resultado == JFileChooser.APPROVE_OPTION) {
                File ruta = fileChooser.getSelectedFile();
                File archivoXML = new File(ruta, "horario.xml");
                File archivoHTML = new File(ruta, "horario.html");

                StringBuilder xml = new StringBuilder();
                xml.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
                xml.append("<horario>\n");
                for (Horarios horario : horarios) {
                    xml.append("  <entrada>\n");
                    xml.append("    <dia>").append(horario.getDiaSemana()).append("</dia>\n");
                    xml.append("    <tramo_horario>").append(horario.getHoraInicio()).append(" - ").append(horario.getHoraFin()).append("</tramo_horario>\n");
                    xml.append("    <asignatura>").append(horario.getAsignatura().getNombre()).append("</asignatura>\n");
                    xml.append("    <profesor>").append(horario.getProfesor().getNombre() + " " + horario.getProfesor().getApellido()).append("</profesor>\n");
                    xml.append("  </entrada>\n");
                }
                xml.append("</horario>");

                try (BufferedWriter out = new BufferedWriter(new FileWriter(archivoXML))) {
                    out.write(xml.toString());
                }

                obtenerHTML(archivoXML.getAbsolutePath(), archivoHTML.getAbsolutePath());

                Files.delete(Path.of(archivoXML.getAbsolutePath()));

                new CustomDialog(null, "Éxito", "Horario exportado correctamente en: " + archivoHTML.getAbsolutePath(), "ONLY_OK").setVisible(true);
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

    /**
     * Genera un horario en formato XML y HTML para profesores
     * @param horarios lista de horarios a exportar
     */

    public static void exportarHorarioProfesorAXML(List<Horarios> horarios) {
        String lookAndFeelActual = UIManager.getLookAndFeel().getClass().getName();

        try {
            CustomFileChooser.aplicarEstiloFileChooser();
            CustomFileChooser.traducirCustomFileChooser();

            JFileChooser fileChooser = CustomFileChooser.crearFileChooser("Selecciona dónde guardar el horario");
            int resultado = fileChooser.showSaveDialog(null);

            if (resultado == JFileChooser.APPROVE_OPTION) {
                File ruta = fileChooser.getSelectedFile();
                File archivoXML = new File(ruta, "horario.xml");
                File archivoHTML = new File(ruta, "horario.html");

                StringBuilder xml = new StringBuilder();
                xml.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
                xml.append("<horario>\n");
                for (Horarios horario : horarios) {
                    xml.append("  <entrada>\n");
                    xml.append("    <dia>").append(horario.getDiaSemana()).append("</dia>\n");
                    xml.append("    <tramo_horario>").append(horario.getHoraInicio()).append(" - ").append(horario.getHoraFin()).append("</tramo_horario>\n");
                    xml.append("    <asignatura>").append(horario.getAsignatura().getNombre()).append("</asignatura>\n");
                    for (CursosAsignaturas ca : horario.getAsignatura().getCursosAsignaturas()) {
                        xml.append("    <curso>").append(ca.getCurso().getNombre()).append("</curso>\n");
                    }
                    xml.append("  </entrada>\n");
                }
                xml.append("</horario>");

                try (BufferedWriter out = new BufferedWriter(new FileWriter(archivoXML))) {
                    out.write(xml.toString());
                }

                obtenerProfesorHTML(archivoXML.getAbsolutePath(), archivoHTML.getAbsolutePath());

                Files.delete(Path.of(archivoXML.getAbsolutePath()));

                new CustomDialog(null, "Éxito", "Horario del profesor exportado correctamente en: " + archivoHTML.getAbsolutePath(), "ONLY_OK").setVisible(true);
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

    /**
     * Genera un archivo HTML a partir de un archivo XML usando una plantilla XSLT para estudiantes
     * @param rutaXML ruta del archivo XML
     * @param rutaHTML ruta del archivo HTML a generar
     */

    public static void obtenerHTML(String rutaXML, String rutaHTML) {
        try {
            File archivoXML = new File(rutaXML);
            File archivoXSLT = new File("src/main/resources/xslt/transformHorarios.xslt");
            File archivoSalida = new File(rutaHTML);

            TransformerFactory factory = TransformerFactory.newInstance();
            Transformer transformer = factory.newTransformer(new StreamSource(archivoXSLT));
            transformer.transform(new StreamSource(archivoXML), new StreamResult(archivoSalida));
        } catch (TransformerException e) {
            e.printStackTrace();
        }
    }

    /**
     * Genera un archivo HTML a partir de un archivo XML usando una plantilla XSLT para profesores
     * @param rutaXML ruta del archivo XML
     * @param rutaHTML ruta del archivo HTML a generar
     */

    public static void obtenerProfesorHTML(String rutaXML, String rutaHTML) {
        try {
            File archivoXML = new File(rutaXML);
            File archivoXSLT = new File("src/main/resources/xslt/transformHorariosProfesor.xslt");
            File archivoSalida = new File(rutaHTML);

            TransformerFactory factory = TransformerFactory.newInstance();
            Transformer transformer = factory.newTransformer(new StreamSource(archivoXSLT));
            transformer.transform(new StreamSource(archivoXML), new StreamResult(archivoSalida));
        } catch (TransformerException e) {
            e.printStackTrace();
        }
    }
}
