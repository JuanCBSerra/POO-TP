package com.example.TPO.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EliminarPacientePanel extends JPanel {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField dniBuscarField;
    private JButton btnBuscar;
    private JButton btnEliminar;
    private JLabel nombreLabel;
    private JLabel apellidoLabel;

    public EliminarPacientePanel() {
        setLayout(new BorderLayout());

        JLabel titulo = new JLabel("Eliminar Paciente", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 24));
        add(titulo, BorderLayout.NORTH);

        JPanel buscarPanel = new JPanel(new GridLayout(1, 3, 10, 10));
        buscarPanel.add(new JLabel("DNI:"));
        dniBuscarField = new JTextField();
        buscarPanel.add(dniBuscarField);
        btnBuscar = new JButton("Buscar");
        buscarPanel.add(btnBuscar);

        add(buscarPanel, BorderLayout.NORTH);

        JPanel infoPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        infoPanel.add(new JLabel("Nombre:"));
        nombreLabel = new JLabel();
        infoPanel.add(nombreLabel);

        infoPanel.add(new JLabel("Apellido:"));
        apellidoLabel = new JLabel();
        infoPanel.add(apellidoLabel);

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
        String dni = dniBuscarField.getText();

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

        // Simulación para el ejemplo:
        if (dni.equals("12345678")) {
            nombreLabel.setText("Juan");
            apellidoLabel.setText("Pérez");
            btnEliminar.setEnabled(true);
        } else {
            JOptionPane.showMessageDialog(this, "Paciente no encontrado.", "Error", JOptionPane.ERROR_MESSAGE);
            btnEliminar.setEnabled(false);
        }
    }

    private void eliminarPaciente() {
        String dni = dniBuscarField.getText();

        // Aquí puedes llamar a los métodos correspondientes para eliminar el paciente
        // Por ejemplo: pacienteService.eliminarPaciente(dni);

        JOptionPane.showMessageDialog(this, "Paciente eliminado con éxito.");
        nombreLabel.setText("");
        apellidoLabel.setText("");
        dniBuscarField.setText("");
        btnEliminar.setEnabled(false);
    }
}