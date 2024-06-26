package com.example.TPO.view;

import com.example.TPO.Utils;
import com.example.TPO.controller.PacienteController;

import javax.swing.*;
import javax.swing.text.NumberFormatter;
import java.awt.*;
import java.text.NumberFormat;

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
        setLayout(new BorderLayout(10, 10));

        JLabel titulo = new JLabel("Crear Paciente", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 24));
        add(titulo, BorderLayout.NORTH);

        JPanel formPanel = new JPanel(new GridLayout(6, 2, 10, 10));

        formPanel.add(new JLabel("DNI:"));
        dniField = new JTextField();
        formPanel.add(dniField);

        formPanel.add(new JLabel("Nombre:"));
        nombreField = new JTextField();
        formPanel.add(nombreField);

        formPanel.add(new JLabel("Domicilio:"));
        domicilioField = new JTextField();
        formPanel.add(domicilioField);

        formPanel.add(new JLabel("Email:"));
        emailField = new JTextField();
        formPanel.add(emailField);

        formPanel.add(new JLabel("Sexo:"));
        sexoField = new JTextField();
        formPanel.add(sexoField);

        formPanel.add(new JLabel("Edad:"));
        edadField = Utils.createFormattedTextField();
        formPanel.add(edadField);

        add(formPanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        JButton btnGuardar = new JButton("Guardar");
        buttonPanel.add(btnGuardar);
        add(buttonPanel, BorderLayout.SOUTH);

        btnGuardar.addActionListener(e -> guardarPaciente());
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
