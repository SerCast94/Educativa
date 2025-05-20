package Vista.Util;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.Objects;

import static Vista.Util.EstiloComponentes.establecerIcono;

/**
 * CustomDialog es una clase que extiende JDialog para crear un cuadro de diálogo personalizado.
 * Permite mostrar mensajes al usuario con diferentes tipos de botones (Aceptar, Aceptar/Cancelar).
 */
public class CustomDialog extends JDialog {

    private static boolean aceptar = false;

    public CustomDialog(Frame padre, String titulo, String texto, String tipoBoton) {
        super(padre, titulo, true);

        aceptar = false;

        Image icono = new ImageIcon(Objects.requireNonNull(EstiloComponentes.class.getResource("/icons/logo.png"))).getImage();
        setIconImage(icono);

        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent e) {
                aceptar = false;
            }
        });

        Color fondoBase = new Color(251, 234, 230);
        Color textoMensaje = new Color(51, 51, 51);

        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(new EmptyBorder(20, 20, 20, 20));
        panel.setBackground(fondoBase);

        JTextArea mensaje = new JTextArea(texto);
        mensaje.setFont(new Font("Arial", Font.PLAIN, 14));
        mensaje.setForeground(textoMensaje);
        mensaje.setBackground(fondoBase);
        mensaje.setWrapStyleWord(true);
        mensaje.setLineWrap(true);
        mensaje.setEditable(false);
        mensaje.setFocusable(false);
        mensaje.setBorder(null);

        int anchoMax = 300;
        mensaje.setSize(new Dimension(anchoMax, Short.MAX_VALUE));

        FontMetrics metrics = mensaje.getFontMetrics(mensaje.getFont());
        int lineHeight = metrics.getHeight();

        String[] words = texto.split("\\s+");
        int lineWidth = 0;
        int lines = 1;

        for (String word : words) {
            int wordWidth = metrics.stringWidth(word + " ");
            if (lineWidth + wordWidth > anchoMax) {
                lines++;
                lineWidth = wordWidth;
            } else {
                lineWidth += wordWidth;
            }
        }

        int altoEstimado = lineHeight * lines + 20;
        mensaje.setPreferredSize(new Dimension(anchoMax, altoEstimado));

        panel.add(mensaje, BorderLayout.CENTER);

        JPanel panelBoton = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        panelBoton.setBackground(fondoBase);

        if (tipoBoton.equalsIgnoreCase("OK_CANCEL")) {
            Boton okBoton = new Boton("Aceptar", Boton.tipoBoton.PRIMARY);
            Boton cancelarBoton = new Boton("Cancelar", Boton.tipoBoton.DELETE);

            okBoton.addActionListener(e -> {
                aceptar = true;
                dispose();
            });

            cancelarBoton.addActionListener(e -> {
                aceptar = false;
                dispose();
            });

            okBoton.addKeyListener(new java.awt.event.KeyAdapter() {
                @Override
                public void keyPressed(java.awt.event.KeyEvent e) {
                    if (e.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER) {
                        okBoton.doClick();
                    }
                }
            });

            panelBoton.add(okBoton);
            panelBoton.add(cancelarBoton);
            getRootPane().setDefaultButton(okBoton);

        } else {
            Boton okBoton = new Boton("Aceptar", Boton.tipoBoton.PRIMARY);
            okBoton.addActionListener(e -> {
                aceptar = true;
                dispose();
            });

            okBoton.addKeyListener(new java.awt.event.KeyAdapter() {
                @Override
                public void keyPressed(java.awt.event.KeyEvent e) {
                    if (e.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER) {
                        okBoton.doClick();
                    }
                }
            });

            panelBoton.add(okBoton);
            getRootPane().setDefaultButton(okBoton);
        }

        panel.add(panelBoton, BorderLayout.SOUTH);

        setContentPane(panel);
        pack();

        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        int maxWidth = screen.width - 100;
        int maxHeight = screen.height - 100;

        Dimension current = getSize();
        setSize(Math.min(current.width, maxWidth), Math.min(current.height, maxHeight));

        setLocationRelativeTo(padre);
    }

    public static boolean isAceptar() {
        return aceptar;
    }

}