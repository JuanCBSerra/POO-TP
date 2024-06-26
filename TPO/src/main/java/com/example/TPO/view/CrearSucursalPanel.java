package com.example.TPO.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CrearSucursalPanel extends JPanel {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField numeroField;
	private JTextField direccionField;
	private JTextField respTecnicoField;

    public CrearSucursalPanel() {
        setLayout(new BorderLayout());

        JLabel titulo = new JLabel("Crear Sucursal", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 24));
        add(titulo, BorderLayout.NORTH);

        JPanel formPanel = new JPanel(new GridLayout(4, 2, 10, 10));

        formPanel.add(new JLabel("Numero:"));
        numeroField = new JTextField();
        formPanel.add(numeroField);

        formPanel.add(new JLabel("Direccion:"));
        direccionField = new JTextField();
        formPanel.add(direccionField);

        formPanel.add(new JLabel("Responsable Tecnico:"));
        respTecnicoField = new JTextField();
        formPanel.add(respTecnicoField);

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
       
        // Aquí puedes llamar a los métodos correspondientes para crear el paciente
        // Por ejemplo: Paciente nuevoPaciente = new Paciente(nombre, apellido, dni);
        // pacienteService.crearPaciente(nuevoPaciente);

        JOptionPane.showMessageDialog(this, "Paciente creado con éxito.");
    }
}