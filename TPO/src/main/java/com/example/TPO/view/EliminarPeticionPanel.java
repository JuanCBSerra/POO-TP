package com.example.TPO.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EliminarPeticionPanel extends JPanel {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField pacienteBuscarField;
    private JButton btnBuscar;
    private JButton btnEliminar;
    private JLabel obraSocialLabel;

    public EliminarPeticionPanel() {
        setLayout(new BorderLayout());

        JLabel titulo = new JLabel("Eliminar Paciente", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 24));
        add(titulo, BorderLayout.NORTH);

        JPanel buscarPanel = new JPanel(new GridLayout(1, 3, 10, 10));
        buscarPanel.add(new JLabel("Paciente:"));
        pacienteBuscarField = new JTextField();
        buscarPanel.add(pacienteBuscarField);
        btnBuscar = new JButton("Buscar");
        buscarPanel.add(btnBuscar);

        add(buscarPanel, BorderLayout.NORTH);

        JPanel infoPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        infoPanel.add(new JLabel("Obra Social:"));
        obraSocialLabel = new JLabel();
        infoPanel.add(obraSocialLabel);


        btnEliminar = new JButton("Eliminar");
        infoPanel.add(btnEliminar);

        add(infoPanel, BorderLayout.CENTER);

        // Deshabilitar botón Eliminar hasta que se busque un paciente
        btnEliminar.setEnabled(false);

        // Agregar ActionListeners
        btnBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarPaciente();
            }
        });

        btnEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eliminarPaciente();
            }
        });
    }

    private void buscarPaciente() {
    }

    private void eliminarPaciente() {
        
    }
}