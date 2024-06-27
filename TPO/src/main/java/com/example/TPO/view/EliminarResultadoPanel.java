package com.example.TPO.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EliminarResultadoPanel extends JPanel {
    private static final long serialVersionUID = 1L;
    private JTextField dniBuscarField;
    private JButton btnBuscar;
    private JButton btnEliminar;
    private JLabel nombreLabel;
    private JLabel apellidoLabel;

    public EliminarResultadoPanel() {
        setLayout(new BorderLayout());

        JLabel titulo = new JLabel("Eliminar Paciente", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 24));
        add(titulo, BorderLayout.NORTH);

        JPanel buscarPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        buscarPanel.add(new JLabel("DNI:"));
        dniBuscarField = new JTextField(20);
        buscarPanel.add(dniBuscarField);
        btnBuscar = new JButton("Buscar");
        btnBuscar.setBackground(new Color(144, 202, 249));
        buscarPanel.add(btnBuscar);

        add(buscarPanel, BorderLayout.CENTER);

        JPanel infoPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        infoPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Añadir espacio alrededor del panel

        infoPanel.add(new JLabel("Nombre:"));
        nombreLabel = new JLabel();
        infoPanel.add(nombreLabel);

        infoPanel.add(new JLabel("Apellido:"));
        apellidoLabel = new JLabel();
        infoPanel.add(apellidoLabel);

        btnEliminar = new JButton("Eliminar");
        btnEliminar.setBackground(new Color(255, 102, 102)); // Rojo claro
        infoPanel.add(btnEliminar);

        add(infoPanel, BorderLayout.SOUTH);

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

        // Simulación de búsqueda de paciente por DNI
        // Aquí deberías implementar la lógica real para buscar el paciente por su DNI
        // y cargar los datos en los labels correspondientes.
        // Por ejemplo:
        // Paciente paciente = pacienteService.buscarPorDni(dni);
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
