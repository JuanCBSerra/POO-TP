package com.example.TPO.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CrearUsuarioPanel extends JPanel {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private JTextField nombreField;
    private JTextField apellidoField;
    private JTextField dniField;
    private JTextField fecNacField;
    private JTextField usuarioField;
    private JTextField correoField;

    public CrearUsuarioPanel() {
        setLayout(new BorderLayout());

        JLabel titulo = new JLabel("Crear Usuario", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 24));
        add(titulo, BorderLayout.NORTH);

        JPanel formPanel = new JPanel();
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));
        formPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        addFormRow(formPanel, "Usuario:", usuarioField = new JTextField(20));
        addFormRow(formPanel, "Nombre:", nombreField = new JTextField(20));
        addFormRow(formPanel, "Apellido:", apellidoField = new JTextField(20));
        addFormRow(formPanel, "Correo:", correoField = new JTextField(20));
        addFormRow(formPanel, "DNI:", dniField = new JTextField(20));
        addFormRow(formPanel, "Fecha Nacimiento:", fecNacField = new JTextField(20));

        JButton btnGuardar = new JButton("Guardar");
        btnGuardar.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnGuardar.setBackground(new Color(144, 238, 144));
        formPanel.add(btnGuardar);

        add(formPanel, BorderLayout.CENTER);

        // Agregar ActionListener al botón Guardar
        btnGuardar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guardarPaciente();
            }
        });
    }

    private void addFormRow(JPanel panel, String labelText, JTextField textField) {
        JPanel row = new JPanel(new BorderLayout(20, 20));
        row.setMaximumSize(new Dimension(Integer.MAX_VALUE, textField.getPreferredSize().height));
        JLabel label = new JLabel(labelText);
        label.setPreferredSize(new Dimension(120, textField.getPreferredSize().height));
        row.add(label, BorderLayout.WEST);
        row.add(textField, BorderLayout.CENTER);
        panel.add(row);
        panel.add(Box.createVerticalStrut(20)); // Añade un espacio vertical entre las filas
    }

    private void guardarPaciente() {
        String nombre = nombreField.getText();
        String apellido = apellidoField.getText();
        String dni = dniField.getText();

        // Validaciones básicas
        if (nombre.isEmpty() || apellido.isEmpty() || dni.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, complete todos los campos.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Aquí puedes llamar a los métodos correspondientes para crear el paciente
        // Por ejemplo: Paciente nuevoPaciente = new Paciente(nombre, apellido, dni);
        // pacienteService.crearPaciente(nuevoPaciente);

        JOptionPane.showMessageDialog(this, "Paciente creado con éxito.");
    }
}
