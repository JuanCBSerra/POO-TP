package com.example.TPO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CrearPracticasPanel extends JPanel {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField codigoField;
    private JTextField nombreField;
    private JTextField grupoField;
    private JTextField valoresCriticosField;
    private JTextField valoresReservadosField;
    private JTextField horasResultadoField;

    public CrearPracticasPanel() {
        setLayout(new BorderLayout());

        JLabel titulo = new JLabel("Crear Practica", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 24));
        add(titulo, BorderLayout.NORTH);

        JPanel formPanel = new JPanel(new GridLayout(4, 2, 10, 10));

    	formPanel.add(new JLabel("Codigo:"));
        codigoField = new JTextField();
        formPanel.add(codigoField);

        formPanel.add(new JLabel("Nombre :"));
        nombreField = new JTextField();
        formPanel.add(nombreField);

        formPanel.add(new JLabel("Grupo:"));
        grupoField = new JTextField();
        formPanel.add(grupoField);
        
        formPanel.add(new JLabel("Valores Criticos:"));
        valoresCriticosField = new JTextField();
        formPanel.add(valoresCriticosField);

        formPanel.add(new JLabel("Valores Reservados:"));
        valoresReservadosField = new JTextField();
        formPanel.add(valoresReservadosField);

        formPanel.add(new JLabel("Horas Resultado:"));
        horasResultadoField = new JTextField();
        formPanel.add(horasResultadoField);

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