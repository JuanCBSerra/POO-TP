package com.example.TPO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PacientesPanel extends JPanel {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public PacientesPanel() {
        setLayout(new BorderLayout());

        JLabel titulo = new JLabel("Administrar Pacientes", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 24));
        add(titulo, BorderLayout.NORTH);

        JPanel botonesPanel = new JPanel();
        botonesPanel.setLayout(new GridLayout(3, 1, 10, 10));

        JButton btnAlta = new JButton("Alta Paciente");
        JButton btnBaja = new JButton("Baja Paciente");
        JButton btnModificacion = new JButton("Modificar Paciente");

        botonesPanel.add(btnAlta);
        botonesPanel.add(btnBaja);
        botonesPanel.add(btnModificacion);

        add(botonesPanel, BorderLayout.CENTER);

        // Agregar ActionListeners a los botones
        btnAlta.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                altaPaciente();
            }
        });

        btnBaja.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                bajaPaciente();
            }
        });

        btnModificacion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                modificarPaciente();
            }
        });
    }

    private void altaPaciente() {
        // Implementar la lógica para dar de alta un paciente
        JOptionPane.showMessageDialog(this, "Función para alta de paciente.");
    }

    private void bajaPaciente() {
        // Implementar la lógica para dar de baja un paciente
        JOptionPane.showMessageDialog(this, "Función para baja de paciente.");
    }

    private void modificarPaciente() {
        // Implementar la lógica para modificar un paciente
        JOptionPane.showMessageDialog(this, "Función para modificar paciente.");
    }
}