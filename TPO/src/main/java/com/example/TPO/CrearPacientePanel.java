package com.example.TPO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CrearPacientePanel extends JPanel {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField nombreField;
    private JTextField apellidoField;
    private JTextField dniField;

    public CrearPacientePanel() {
        setLayout(new BorderLayout());

        JLabel titulo = new JLabel("Crear Paciente", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 24));
        add(titulo, BorderLayout.NORTH);

        JPanel formPanel = new JPanel(new GridLayout(4, 2, 10, 10));

        formPanel.add(new JLabel("Nombre:"));
        nombreField = new JTextField();
        formPanel.add(nombreField);

        formPanel.add(new JLabel("Apellido:"));
        apellidoField = new JTextField();
        formPanel.add(apellidoField);

        formPanel.add(new JLabel("DNI:"));
        dniField = new JTextField();
        formPanel.add(dniField);

        JButton btnGuardar = new JButton("Guardar");
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