package com.example.TPO.view;

import com.example.TPO.Utils;
import com.example.TPO.controller.PacienteController;
import com.example.TPO.model.Paciente;

import javax.swing.*;
import javax.swing.text.NumberFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.util.Optional;

public class ModificarPacientePanel extends JPanel {
    private static final long serialVersionUID = 1L;

    private final JTextField dniBuscarField;
    private final JTextField nombreField;
    private final JTextField domicilioField;
    private final JTextField emailField;
    private final JTextField sexoField;
    private final JFormattedTextField edadField;
    private final JButton btnGuardar;

    private final PacienteController pacienteController = PacienteController.getInstance();

    public ModificarPacientePanel() {
        setLayout(new BorderLayout());

        JLabel titulo = new JLabel("Modificar Paciente", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 24));
        add(titulo, BorderLayout.NORTH);

        JPanel buscarPanel = new JPanel(new GridLayout(1, 3, 10, 10));
        buscarPanel.add(new JLabel("DNI:"));
        dniBuscarField = new JTextField();
        buscarPanel.add(dniBuscarField);
        JButton btnBuscar = new JButton("Buscar");
        buscarPanel.add(btnBuscar);

        add(buscarPanel, BorderLayout.NORTH);

        JPanel formPanel = new JPanel(new GridLayout(6, 2, 10, 10));

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

        btnGuardar = new JButton("Guardar");
        formPanel.add(btnGuardar);

        add(formPanel, BorderLayout.CENTER);

        habilitarFormulario(false);

        btnBuscar.addActionListener(e -> buscarPaciente());
        btnGuardar.addActionListener(e -> guardarPaciente());
    }

    private void habilitarFormulario(boolean habilitar) {
        nombreField.setEnabled(habilitar);
        domicilioField.setEnabled(habilitar);
        emailField.setEnabled(habilitar);
        sexoField.setEnabled(habilitar);
        edadField.setEnabled(habilitar);

        btnGuardar.setEnabled(habilitar);
    }

    private void buscarPaciente() {
        String dni = dniBuscarField.getText();
        Optional<Paciente> paciente = pacienteController.buscarPacientePorDni(dni);

        if (paciente.isPresent()) {
            Paciente p = paciente.get();
            nombreField.setText(p.getNombre());
            domicilioField.setText(p.getDomicilio());
            emailField.setText(p.getEmail());
            sexoField.setText(p.getSexo());
            edadField.setValue(p.getEdad());

            habilitarFormulario(true);
        } else {
            JOptionPane.showMessageDialog(this, "Paciente no encontrado.", "Error", JOptionPane.ERROR_MESSAGE);
            limpiarCampos();
            habilitarFormulario(false);
        }
    }

    private void guardarPaciente() {
        String nombre = nombreField.getText();
        String domicilio = domicilioField.getText();
        String email = emailField.getText();
        String sexo = sexoField.getText();
        int edad = (int) edadField.getValue();
        String dni = dniBuscarField.getText();

        if (nombre.isEmpty() || domicilio.isEmpty() || email.isEmpty() || sexo.isEmpty() || edad <= 0) {
            JOptionPane.showMessageDialog(this, "Por favor, complete todos los campos correctamente.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        pacienteController.modificarPaciente(dni, nombre, domicilio, email, sexo, edad);

        JOptionPane.showMessageDialog(this, "Paciente modificado con éxito.");
        limpiarCampos();
        habilitarFormulario(false);
    }

    private void limpiarCampos() {
        nombreField.setText("");
        domicilioField.setText("");
        emailField.setText("");
        sexoField.setText("");
        edadField.setValue(null);
    }
}
