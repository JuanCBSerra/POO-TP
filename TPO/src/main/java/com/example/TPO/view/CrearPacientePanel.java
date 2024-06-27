package com.example.TPO.view;

import com.example.TPO.Utils;
import com.example.TPO.controller.PacienteController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CrearPacientePanel extends JPanel {
    private static final long serialVersionUID = 1L;
    private final JTextField nombreField;
    private final JTextField domicilioField;
    private final JTextField dniField;
    private final JTextField emailField;
    private final JTextField sexoField;
    private final JFormattedTextField edadField;

    private final PacienteController pacienteController = PacienteController.getInstance();

    public CrearPacientePanel() {
        setLayout(new BorderLayout());

        JLabel titulo = new JLabel("Crear Paciente", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 24));
        add(titulo, BorderLayout.NORTH);

        JPanel formPanel = new JPanel();
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        addFormRow(formPanel, "DNI:", dniField = new JTextField(20));
        addFormRow(formPanel, "Nombre:", nombreField = new JTextField(20));
        addFormRow(formPanel, "Domicilio:", domicilioField = new JTextField(20));
        addFormRow(formPanel, "Email:", emailField = new JTextField(20));
        addFormRow(formPanel, "Sexo:", sexoField = new JTextField(20));
        addFormRow(formPanel, "Edad:", edadField = Utils.createFormattedTextField());

        formPanel.add(Box.createVerticalGlue()); // Añade espacio flexible antes del botón

        JButton btnGuardar = new JButton("Guardar");
        btnGuardar.setBackground(new Color(144, 238, 144)); // Verde claro
        btnGuardar.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnGuardar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guardarPaciente();
            }
        });

        formPanel.add(btnGuardar);

        add(formPanel, BorderLayout.CENTER);
    }

    private void addFormRow(JPanel panel, String labelText, JTextField textField) {
        JPanel row = new JPanel(new BorderLayout(20, 20));
        row.setMaximumSize(new Dimension(Integer.MAX_VALUE, textField.getPreferredSize().height));
        JLabel label = new JLabel(labelText);
        label.setPreferredSize(new Dimension(200, textField.getPreferredSize().height));
        row.add(label, BorderLayout.WEST);
        row.add(textField, BorderLayout.CENTER);
        panel.add(row);
        panel.add(Box.createVerticalStrut(15)); // Añade un espacio vertical entre las filas
    }

    private void guardarPaciente() {
        String nombre = nombreField.getText();
        String domicilio = domicilioField.getText();
        String dni = dniField.getText();
        String email = emailField.getText();
        String sexo = sexoField.getText();
        String edadText = edadField.getText();

        // Validaciones básicas
        if (nombre.isEmpty() || domicilio.isEmpty() || dni.isEmpty() || email.isEmpty() || sexo.isEmpty() || edadText.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, complete todos los campos.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int edad = Integer.parseInt(edadText);

        pacienteController.agregarPaciente(
                dni,
                nombre,
                domicilio,
                email,
                sexo,
                edad
        );

        JOptionPane.showMessageDialog(this, "Paciente creado con éxito.");
        clearFields();
    }

    private void clearFields() {
        nombreField.setText("");
        domicilioField.setText("");
        dniField.setText("");
        emailField.setText("");
        sexoField.setText("");
        edadField.setValue(null);
    }
}
