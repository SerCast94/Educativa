package Vista.Admin;

import javax.swing.*;
import java.awt.*;
import java.util.Vector;

public class FormularioEstudiantesAdmin extends JDialog {
    public FormularioEstudiantesAdmin(Vector datos) {
        setTitle(datos == null ? "Agregar Estudiante" : "Modificar Estudiante");
        setModal(true);
        setSize(400, 500);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(0, 2));

        add(new JLabel("Nombre:"));
        JTextField campoNombre = new JTextField(datos != null ? datos.get(0).toString() : "");
        add(campoNombre);

        add(new JLabel("Apellido:"));
        JTextField campoApellido = new JTextField(datos != null ? datos.get(1).toString() : "");
        add(campoApellido);

        // ... Agrega todos los campos necesarios igual que arriba

        JButton btnGuardar = new JButton("Guardar");
        add(btnGuardar);

        btnGuardar.addActionListener(e -> {
            // Aquí iría la lógica para insertar o modificar en la BD
            dispose();
        });

        setVisible(true);
    }
}
