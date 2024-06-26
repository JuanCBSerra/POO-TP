package com.example.TPO.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CrearPeticionPanel extends JPanel {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    private JTextField obraSocialField;
    private JTextField fechaCargaField;
    private JTextField practicaAsociadaField;
    private JTextField fechaEntregaField;
    
    public CrearPeticionPanel() {
        setLayout(new BorderLayout());

        JLabel titulo = new JLabel("Crear Paciente", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 24));
        add(titulo, BorderLayout.NORTH);

        JPanel formPanel = new JPanel(new GridLayout(4, 2, 10, 10));

        formPanel.add(new JLabel("Obra Social:"));
        obraSocialField = new JTextField();
        formPanel.add(obraSocialField);

        formPanel.add(new JLabel("Fecha de Carga:"));
        fechaCargaField = new JTextField();
        formPanel.add(fechaCargaField);

        formPanel.add(new JLabel("Practica Asociada:"));
        practicaAsociadaField = new JTextField();
        formPanel.add(practicaAsociadaField);
        
        formPanel.add(new JLabel("Fecha Estimada Entrega:"));
        fechaEntregaField = new JTextField();
        formPanel.add(fechaEntregaField);

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