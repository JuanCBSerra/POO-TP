package com.example.TPO.view.pacientes;

import com.example.TPO.DTO.PacienteDTO;
import com.example.TPO.controller.PacienteController;

import javax.swing.*;
import java.awt.*;
import java.util.Optional;

public class EliminarPacientePanel extends JPanel {

    private final JTextField dniBuscarField;
    private final JButton btnEliminar;
    private final JLabel nombreLabel;

    private final PacienteController pacienteController = PacienteController.getInstance();

    public EliminarPacientePanel() {
        setLayout(new BorderLayout());

        JLabel titulo = new JLabel("Eliminar Paciente", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 24));
        add(titulo, BorderLayout.NORTH);

        JPanel buscarPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        buscarPanel.add(new JLabel("DNI:"));
        dniBuscarField = new JTextField(20);
        buscarPanel.add(dniBuscarField);
        JButton btnBuscar = new JButton("Buscar");
        btnBuscar.setBackground(new Color(144, 202, 249));
        buscarPanel.add(btnBuscar);

        add(buscarPanel, BorderLayout.CENTER);

        JPanel infoPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        infoPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        infoPanel.add(new JLabel("Nombre:"));
        nombreLabel = new JLabel();
        infoPanel.add(nombreLabel);

        btnEliminar = new JButton("Eliminar");
        btnEliminar.setBackground(new Color(255, 102, 102));
        infoPanel.add(btnEliminar);

        add(infoPanel, BorderLayout.SOUTH);

        btnEliminar.setEnabled(false);

        btnBuscar.addActionListener(e -> buscarPaciente());

        btnEliminar.addActionListener(e -> eliminarPaciente());
    }

    private void buscarPaciente() {
        String dni = dniBuscarField.getText();

        Optional<PacienteDTO> paciente = pacienteController.getPaciente(dni);

        if (paciente.isPresent()) {
            nombreLabel.setText(paciente.get().getNombre());
            btnEliminar.setEnabled(true);
        } else {
            JOptionPane.showMessageDialog(this, "Paciente no encontrado.", "Error", JOptionPane.ERROR_MESSAGE);
            nombreLabel.setText("");
            btnEliminar.setEnabled(false);
        }
    }

    private void eliminarPaciente() {
        String dni = dniBuscarField.getText();

        boolean pacienteEliminado = pacienteController.eliminarPaciente(dni);

        if (pacienteEliminado) {
            JOptionPane.showMessageDialog(this, "Paciente eliminado con éxito.");
        } else {
            JOptionPane.showMessageDialog(this, "No se pudo eliminar al paciente", "Error", JOptionPane.ERROR_MESSAGE);
        }

        nombreLabel.setText("");
        dniBuscarField.setText("");
        btnEliminar.setEnabled(false);
    }
}
