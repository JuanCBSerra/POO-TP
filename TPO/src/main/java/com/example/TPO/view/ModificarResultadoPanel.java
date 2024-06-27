package com.example.TPO.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ModificarResultadoPanel extends JPanel {
    private static final long serialVersionUID = 1L;

    private JTextField dniBuscarField;
    private JTextField nombreField;
    private JTextField apellidoField;
    private JTextField dniField;
    private JButton btnBuscar;
    private JButton btnGuardar;

    public ModificarResultadoPanel() {
        setLayout(new BorderLayout());

        JLabel titulo = new JLabel("Modificar Paciente", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 24));
        add(titulo, BorderLayout.NORTH);

        JPanel buscarPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        buscarPanel.add(new JLabel("DNI:"));
        dniBuscarField = new JTextField(20);
        buscarPanel.add(dniBuscarField);
        btnBuscar = new JButton("Buscar");
        btnBuscar.setBackground(new Color(144, 202, 249)); // Color celeste
        buscarPanel.add(btnBuscar);

        add(buscarPanel, BorderLayout.CENTER);

        JPanel formPanel = new JPanel(new GridLayout(4, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Espacios alrededor del panel

        formPanel.add(new JLabel("Nombre:"));
        nombreField = new JTextField();
        formPanel.add(nombreField);

        formPanel.add(new JLabel("Apellido:"));
        apellidoField = new JTextField();
        formPanel.add(apellidoField);

        formPanel.add(new JLabel("DNI:"));
        dniField = new JTextField();
        formPanel.add(dniField);

        btnGuardar = new JButton("Guardar");
        btnGuardar.setBackground(new Color(144, 238, 144)); // Color verde claro
        formPanel.add(btnGuardar);

        add(formPanel, BorderLayout.SOUTH);

        // Deshabilitar el panel de formulario hasta que se busque un paciente
        habilitarFormulario(false);

        // Agregar ActionListeners
        btnBuscar.addActionListener(e -> buscarPaciente());
        btnGuardar.addActionListener(e -> guardarPaciente());
    }

    private void habilitarFormulario(boolean habilitar) {
        nombreField.setEnabled(habilitar);
        apellidoField.setEnabled(habilitar);
        dniField.setEnabled(habilitar);
        btnGuardar.setEnabled(habilitar);
    }

    private void buscarPaciente() {
        String dni = dniBuscarField.getText();

        // Implementación simulada para buscar el paciente y cargar datos estáticos
        if (dni.equals("12345678")) {
            nombreField.setText("Juan");
            apellidoField.setText("Pérez");
            dniField.setText("12345678");
            habilitarFormulario(true);
        } else {
            JOptionPane.showMessageDialog(this, "Paciente no encontrado.", "Error", JOptionPane.ERROR_MESSAGE);
            habilitarFormulario(false);
        }
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

        // Implementación simulada para guardar el paciente modificado
        // Aquí iría la lógica para guardar los datos modificados del paciente

        JOptionPane.showMessageDialog(this, "Paciente modificado con éxito.");
    }
}
