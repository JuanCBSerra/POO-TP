package com.example.TPO.view.pacientes;

import com.example.TPO.Utils;
import com.example.TPO.controller.PacienteController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CrearPacientePanel extends JPanel {

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

        formPanel.add(Box.createVerticalGlue());

        JButton btnGuardar = new JButton("Guardar");
        btnGuardar.setBackground(new Color(144, 238, 144));
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
        panel.add(Box.createVerticalStrut(15));
    }

    private void guardarPaciente() {
        String nombre = nombreField.getText();
        String domicilio = domicilioField.getText();
        String dni = dniField.getText();
        String email = emailField.getText();
        String sexo = sexoField.getText();
        String edadText = edadField.getText();

        if (nombre.isEmpty() || domicilio.isEmpty() || dni.isEmpty() || email.isEmpty() || sexo.isEmpty() || edadText.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, complete todos los campos.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Validar que el DNI tenga una longitud de 8 o 9 caracteres

        if (dni.length() < 8 || dni.length() > 9) {
            JOptionPane.showMessageDialog(this, "El DNI debe tener una longitud de 8 o 9 caracteres.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Validar que el nombre solo contenga letras

        if (!nombre.matches("[a-zA-Z\\s]+")) {
            JOptionPane.showMessageDialog(this, "El nombre solo debe contener letras y espacios.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Validar que el sexo sea 'M' o 'F'

        if (!sexo.equalsIgnoreCase("M") && !sexo.equalsIgnoreCase("F")) {
            JOptionPane.showMessageDialog(this, "El sexo debe ser 'M' o 'F'.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Validar que la edad solo contenga números

        int edad;

        try {
            edad = Integer.parseInt(edadText);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "La edad debe ser un número válido.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

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
