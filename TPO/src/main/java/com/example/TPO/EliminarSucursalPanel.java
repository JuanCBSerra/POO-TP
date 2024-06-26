package com.example.TPO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EliminarSucursalPanel extends JPanel {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField numeroBuscarField;
    private JButton btnBuscar;
    private JButton btnEliminar;
    private JLabel direcLabel;
    private JLabel respLabel;

    public EliminarSucursalPanel() {
        setLayout(new BorderLayout());

        JLabel titulo = new JLabel("Eliminar Sucursal", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 24));
        add(titulo, BorderLayout.NORTH);

        JPanel buscarPanel = new JPanel(new GridLayout(1, 3, 10, 10));
        buscarPanel.add(new JLabel("Numero :"));
        numeroBuscarField = new JTextField();
        buscarPanel.add(numeroBuscarField);
        btnBuscar = new JButton("Buscar");
        buscarPanel.add(btnBuscar);

        add(buscarPanel, BorderLayout.NORTH);

        JPanel infoPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        infoPanel.add(new JLabel("Direccion:"));
        direcLabel = new JLabel();
        infoPanel.add(direcLabel);

        infoPanel.add(new JLabel("Responsable Tecnico:"));
        respLabel = new JLabel();
        infoPanel.add(respLabel);

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

        // Aquí deberías buscar el paciente por su DNI y cargar los datos en los labels
        // Por ejemplo: Paciente paciente = pacienteService.buscarPorDni(dni);
        // if (paciente != null) {
        //     nombreLabel.setText(paciente.getNombre());
        //     apellidoLabel.setText(paciente.getApellido());
        //     btnEliminar.setEnabled(true);
        // } else {
        //     JOptionPane.showMessageDialog(this, "Paciente no encontrado.", "Error", JOptionPane.ERROR_MESSAGE);
        //     btnEliminar.setEnabled(false);
        // }

       
    }

    private void eliminarPaciente() {
    }
}